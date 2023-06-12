package api.tdd.goRest;

import api.pojo_classes.GoRest.CreateUserWithLombok;
import api.pojo_classes.GoRest.UpdateUserWithLombok;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import log4jAppender.Log4jAppender;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.ConfigReader;

public class GoRestWithLombok {

    static Logger logger = LogManager.getLogger(GoRestWithLombok.class);
    Response response;
    Faker faker = new Faker();
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
    public void GoRestCRUDWithLombok() throws JsonProcessingException {

        // Building the post request body with the help of the lombok
        CreateUserWithLombok createUserWithLombok = CreateUserWithLombok
                .builder()
                .name("Hola Señior")
                .gender("female")
                .email(faker.internet().emailAddress())
                .status("active")
                // we use build to complete the user
                .build();

        response = RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", ConfigReader.getProperty("Token"))
                // with the latest versions of the rest assured, we do not need to do serialization
                // when we send the post, put, patch requests
                .body(createUserWithLombok)
                .when().post("/public/v2/users/")
                .then().log().all().extract().response();

//        CreateUserWithLombok updateUserWithLombok = CreateUserWithLombok
//                .builder()
//                .name("John Doe")
//                .gender("male")
//                .email(faker.internet().emailAddress())
//                .status("active")
//                .build();

        actualId = response.jsonPath().getInt("id");
        actualName = response.jsonPath().getString("name");
        actualEmail = response.jsonPath().getString("email");

        UpdateUserWithLombok updateUserWithLombok = UpdateUserWithLombok
                .builder()
                .email(faker.internet().emailAddress())
                .build();

        response = RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", ConfigReader.getProperty("Token"))
                .body(updateUserWithLombok)
                .when().patch("/public/v2/users/" + actualId)
                .then().log().all().extract().response();

        response = RestAssured.given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", ConfigReader.getProperty("Token"))
                .when().get("/public/v2/users/" + actualId)
                .then().log().all()
                .extract().response();

        response = RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", ConfigReader.getProperty("Token"))
                .when().delete("/public/v2/users/" + actualId)
                .then().log().all()
                .and().assertThat().statusCode(204)
                .extract().response();

        logger.info("I am done with my testing in the class");



    }
}
