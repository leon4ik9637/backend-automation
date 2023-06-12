package api.tdd.goRest;

import api.pojo_classes.GoRest.CreateUserWithLombok;
import api.pojo_classes.GoRest.UpdateUserWithLombok;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.ConfigReader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GoRestSchemaValidation {

    static Logger logger = LogManager.getLogger(GoRestSchemaValidation.class);
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
    public void GoRestCRUDWithLombok() throws JsonProcessingException, FileNotFoundException {

        // Building the post request body with the help of the lombok
        CreateUserWithLombok createUserWithLombok = CreateUserWithLombok
                .builder()
                .name("Tech Global")
                .gender("female")
                .email(faker.internet().emailAddress())
                .status("active")
                // we use build to complete the user
                .build();
//
//        logger.info("GoRest POST schema is validated");
//        logger.debug("GoRest POST schema is not matching with the expected one");

        response = RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", ConfigReader.getProperty("Token"))
                // with the latest versions of the rest assured, we do not need to do serialization
                // when we send the post, put, patch requests
                .body(createUserWithLombok)
                .when().post("/public/v2/users/")
                .then().log().all()
                .assertThat()
                // with the help of RestAssured schema validation library, we can validate the schema
                .body(JsonSchemaValidator.
                        matchesJsonSchema(new FileInputStream("src/test/java/api/json_schema/goRest/gorest_post_schema.json")))
                .extract().response();

//        logger.info("GoRest POST schema is validated");
//        logger.warn("GoRest POST schema is not matching with the expected one");

    }
}
