package stepDef.apiStepDef;

import api.pojo_classes.GoRest.CreateUserWithLombok;
import api.pojo_classes.GoRest.UpdateUserWithLombok;
import com.github.javafaker.Faker;
import com.jayway.jsonpath.JsonPath;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import static utils.Hooks.*;

public class GoRestStepDef {
    static Logger logger = LogManager.getLogger(GoRestStepDef.class);

    Faker faker = new Faker();

    int actualId;

    @Given("Create user with {string}, {string}, email, and {string}")
    public void create_user_with_email_and(String name, String gender, String status) {

        CreateUserWithLombok createUserWithLombok = CreateUserWithLombok.builder()
                .name(name)
                .email(faker.internet().emailAddress())
                .gender(gender)
                .status(status)
                .build();

        response = RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                // with the latest versions of the RestAssured, we do not need to do serialization
                // when we send the post, put, patch requests
                .body(createUserWithLombok)
                .when().post(goRestBaseURL + "/public/v2/users")
                .then().log().all().extract().response();
    }

    @And("Make GET call to get user with {string}")
    public void makeGETCallToGetUserWith(String urlPath) {

        actualId = JsonPath.read(response.asString(), "id");
        response = RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .when().get(goRestBaseURL + urlPath + actualId)
                .then().log().all().extract().response();

    }

    @And("Updating the user with the following data")
    public void updatingTheUserWithTheFollowingData(Map<String, String> data) {

        UpdateUserWithLombok updateUserWithLombok = UpdateUserWithLombok.builder()
                .name(data.get("name"))
                .status(data.get("status"))
                .build();

        response = RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .body(updateUserWithLombok)
                .when().patch(goRestBaseURL + "/public/v2/users/" + actualId)
                .then().log().all().extract().response();
    }

    @When("I delete user")
    public void iDeleteUser() {
        response = RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", token)
                .when().delete(goRestBaseURL + "/public/v2/users/" + actualId)
                .then().log().all().extract().response();

        logger.info("User is deleted with the id: " + actualId);

    }

}
