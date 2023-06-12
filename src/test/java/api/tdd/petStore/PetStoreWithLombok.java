package api.tdd.petStore;

import api.pojo_classes.petStore.CreatePetOrderWithLombok;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.ConfigReader;

import java.util.Arrays;

public class PetStoreWithLombok {
    Response response;
    Faker faker = new Faker();
    static Logger logger = LogManager.getLogger(PetStoreWithLombok.class);
    CreatePetOrderWithLombok createPetOrderWithLombok;

    int actualId;
    int actualPetId;
    int actualQuantity;
    String actualShipDate;
    String actualStatus;
    boolean actualComplete;

    @BeforeTest
    public void beforeTestStarts(){
        RestAssured.baseURI = ConfigReader.getProperty("PetStoreBaseURI");
    }

    @Test
    public void petStoreCRUDWithLombok() throws JsonProcessingException {
        createPetOrderWithLombok = CreatePetOrderWithLombok
                .builder()
                .id(5)
                .petId(10)
                .quantity(15)
                .shipDate("2023-06-12T15:35:11.702+0000")
                .status("placed")
                .ifCompleted(true)
                .build();

        response = RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .body(createPetOrderWithLombok)
                .when().post("/v2/store/order")
                .then().log().all()
                .assertThat().statusCode(200)
                .extract().response();

        actualId = response.jsonPath().getInt("id");
        actualPetId = response.jsonPath().getInt("petId");
        actualQuantity = response.jsonPath().getInt("quantity");
        actualShipDate = response.jsonPath().getString("shipDate");
        actualStatus = response.jsonPath().getString("status");
        actualComplete = response.jsonPath().getBoolean("complete");

        Object[] actualBody = {actualId, actualPetId, actualQuantity, actualShipDate, actualStatus};
        System.out.println(Arrays.toString(actualBody) + " This is our actual body");
        Object[] expectedBody = {createPetOrderWithLombok.getId(), createPetOrderWithLombok.getPetId(),
        createPetOrderWithLombok.getQuantity(), createPetOrderWithLombok.getShipDate(),
        createPetOrderWithLombok.getStatus()};

        for (int i = 0; i < actualBody.length; i++) {
            Assert.assertEquals(actualBody[i], expectedBody[i]);
        }


        response = RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .when().get("/v2/store/order/" + actualId)
                .then().log().all()
                .assertThat().statusCode(200)
                .extract().response();

        response = RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .when().delete("/v2/store/order/" + actualId)
                .then().log().all()
                .assertThat().statusCode(200)
                .extract().response();

        response = RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .when().get("/v2/store/order/" + actualId)
                .then().log().all()
                .assertThat().statusCode(404)
                .extract().response();

    }

}
