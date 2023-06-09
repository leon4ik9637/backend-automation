package api.tdd.goRest;

import api.pojo_classes.GoRest.CreateUser;
import api.pojo_classes.GoRest.UpdateUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.ConfigReader;

public class GoRest {
    Response response;
    Faker faker = new Faker();
    ObjectMapper objectMapper = new ObjectMapper();
    int actualId;
    String actualName;
    String actualEmail;
    String actualGender;
    String actualStatus;


    @BeforeSuite
    public void testStarts(){
        System.out.println("The automation test is staring");
    }

    @BeforeTest
    public void beforeTestStarts(){
        // we can assign the base URL to RestAssured.baseURI,
        // and we do not put it when we send the request
        // RestAssured.baseURI = "https://gorest.co.in";
        RestAssured.baseURI = ConfigReader.getProperty("GoRestBaseURI");
    }


    @Test
    public void GoRestCRUD() throws JsonProcessingException {

        //Creating the POJO Object
        CreateUser createUser = new CreateUser();
        // We are able to create object and assign the values to each attribute
        createUser.setName("Tech Global");
        createUser.setGender("female");
        createUser.setEmail(faker.internet().emailAddress());
        createUser.setStatus("active");

        // ObjectMapper is converting the Java Object into the JSON
//        ObjectMapper objectMapper = new ObjectMapper();
                        // Following is the SYNTAX of conversion
        String myBody = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(createUser);
        System.out.println("Printing the converted body by Jackson: " + "\n" + myBody);

        // Post Request
        response = RestAssured
                .given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer 67b1f5766e9500e1f4504fbd9dfffd2d3792d71784f1d7665949ae0aacdf699f")
                .body(myBody)
                .when().post("/public/v2/users")
                .then().log().all()
                // asserting the response header content
                .and().contentType(ContentType.JSON)
                .and().assertThat().statusCode(201)
                .extract().response();

        // validating the response values

        actualId = response.jsonPath().getInt("id");
        actualName = response.jsonPath().getString("name");
        actualEmail = response.jsonPath().getString("email");
        actualGender = response.jsonPath().getString("gender");
        actualStatus = response.jsonPath().getString("status");

        Assert.assertEquals(actualName, createUser.getName());
        Assert.assertEquals(actualGender, createUser.getGender());
        Assert.assertEquals(actualStatus, createUser.getStatus());

        // Get Request

        response = RestAssured
                .given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer 67b1f5766e9500e1f4504fbd9dfffd2d3792d71784f1d7665949ae0aacdf699f")
                .when().get("/public/v2/users/" + actualId)
                .then().log().all().extract().response();

        // the id value is coming from the get response
        int getId = response.jsonPath().getInt("id");
        // the email value is coming from the get response
        String getEmail = response.jsonPath().getString("email");


        org.testng.Assert.assertEquals(actualId, getId, "Asserting the post and get id values");
        org.testng.Assert.assertEquals(actualEmail, getEmail, "Asserting the post and get email values");

        // UPDATE USER

        UpdateUser updateUser = new UpdateUser();
        updateUser.setName("Global Tech");
        updateUser.setEmail(faker.internet().emailAddress());
        updateUser.setGender("male");
        updateUser.setStatus("active");

        String updatedBody = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(updateUser);

        response = RestAssured
                .given().log().all()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer 67b1f5766e9500e1f4504fbd9dfffd2d3792d71784f1d7665949ae0aacdf699f")
                .body(updatedBody)
                .when().put("/public/v2/users/" + actualId)
                .then().log().all().extract().response();

        int updatedId = response.jsonPath().getInt("id");
        String updatedName = response.jsonPath().getString("name");
        org.testng.Assert.assertEquals(actualId, updatedId);
        org.testng.Assert.assertNotEquals(actualName, updatedName);

    }
}
