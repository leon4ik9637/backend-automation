package api.api_automation_sample;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class APISample {
    public static void main(String[] args) {
        Faker faker = new Faker();
        // Response is interface coming from RestAssured library
        // The interface is not just for the response. It is for both request and response
        Response response;

        response = RestAssured
                // rest assured is using the BDD format in the syntax
                .given().log().all()
                // we need headers to be able to send the request
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer 67b1f5766e9500e1f4504fbd9dfffd2d3792d71784f1d7665949ae0aacdf699f")

                .body("{\n" +
                        "    \"name\": \"Marian Weimann\",\n" +
                        "    \"gender\": \"male\",\n" +
                        "    \"email\": \"" + faker.internet().emailAddress() + "\",\n" +
                        "    \"status\": \"active\"\n" +
                        "}")
                // sending the request with the following url
                .when().post("https://gorest.co.in/public/v2/users")
                //getting the response
                .then().log().all().extract().response();

        int statusCode = response.statusCode();
        //getting the status code
        System.out.println("Status code is = " + statusCode);
        //getting the execution time
        System.out.println("Time for execution is = " + response.getTime());

        int getId = response.jsonPath().getInt("id");
        //getting the id value from response
        System.out.println("Id value from response is = " + getId);

        // GET REQUEST

        response = RestAssured
                .given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer 67b1f5766e9500e1f4504fbd9dfffd2d3792d71784f1d7665949ae0aacdf699f")
                // in the get request there is no body, so we are skipping it
                .when().get("https://gorest.co.in/public/v2/users/" + getId)
                .then().log().all().extract().response();

        String getEmail = response.jsonPath().getString("email");

        //PUT REQUEST

        response = RestAssured
                .given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer 67b1f5766e9500e1f4504fbd9dfffd2d3792d71784f1d7665949ae0aacdf699f")
                .body("{\n" +
                        "    \"name\": \"" + faker.name().fullName() + "\",\n" +
                        "    \"gender\": \"male\",\n" +
                        "    \"email\": \"" + getEmail + "\",\n" +
                        "    \"status\": \"active\"\n" +
                        "}")
                .when().put("https://gorest.co.in/public/v2/users/" + getId)
                .then().log().all().extract().response();

        // DELETE REQUEST
        response = RestAssured
                .given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer 67b1f5766e9500e1f4504fbd9dfffd2d3792d71784f1d7665949ae0aacdf699f")
                .when().delete("https://gorest.co.in/public/v2/users/" + getId)
                .then().log().all().extract().response();

        int deleteStatusCode = response.statusCode();
        System.out.println("Delete status code = " + deleteStatusCode);

    }
}
