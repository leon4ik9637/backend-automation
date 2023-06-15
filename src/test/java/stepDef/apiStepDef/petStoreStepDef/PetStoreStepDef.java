package stepDef.apiStepDef.petStoreStepDef;

import api.pojo_classes.GoRest.UpdateUserWithLombok;
import api.pojo_classes.petStore.Category;
import api.pojo_classes.petStore.CreatePet;
import api.pojo_classes.petStore.Tags;
import com.github.javafaker.Faker;
import com.jayway.jsonpath.JsonPath;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.Map;

import static utils.Hooks.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class PetStoreStepDef {
    static Logger logger = LogManager.getLogger(PetStoreStepDef.class);
    Faker faker = new Faker();

    int actualId;
    int expectedPetId;

    @Given("Create a pet with the following data and send a POST request")
    public void create_a_pet_with_the_following_data_and_send_a_post_request(Map<String, String> petCreate) {
        Category category = Category.builder()
                .id(Integer.parseInt(petCreate.get("categoryId")))
                .name(petCreate.get("categoryName"))
                .build();

        Tags tags = Tags.builder()
                .id(Integer.parseInt(petCreate.get("tagId")))
                .name(petCreate.get("tagName"))
                .build();

        CreatePet createPetPojo = CreatePet.builder()
                .id(Integer.parseInt(petCreate.get("petId")))
                .category(category)
                .name(petCreate.get("petName"))
                .photoUrls(Arrays.asList(petCreate.get("photoURL")))
                .tags(Arrays.asList(tags))
                .status(petCreate.get("status"))
                .build();
        expectedPetId = createPetPojo.getId();

        response = RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .body(createPetPojo)
                .when().post(petStoreBaseURI + "/v2/pet")
                .then().log().all()
                .assertThat().statusCode(200)
                .extract().response();


    }


    @And("I send a GET request to {string}")
    public void iSendAGETRequestTo(String urlPath) {
        actualId = JsonPath.read(response.asString(), "id");
        response = RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .with().get(petStoreBaseURI + urlPath + actualId)
                .then().log().all()
                .assertThat().statusCode(200)
                .extract().response();

        logger.info("Validating the Pet ID");
        logger.debug("Actual Pet ID should be " + expectedPetId + " we found " + actualId);
        // asserting the petId with hamcrest
        assertThat(
                "I am expecting the pet ID = " + expectedPetId,
                actualId,
                is(expectedPetId)
        );

    }

    @And("I send a PUT request with the following data:")
    public void iSendAPUTRequestWithTheFollowingData(Map <String, String> updatePet) {
        UpdateUserWithLombok updateUserWithLombok = UpdateUserWithLombok
                .builder()
                .name(updatePet.get("petName"))
                .status("pending")
                .build();

        response = RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .body(updateUserWithLombok)
                .when().patch(petStoreBaseURI + "/v2/pet/" + actualId)
                .then().log().all()
                .assertThat().statusCode(405)
                .extract().response();

        response = RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .when().get(petStoreBaseURI + "/v2/pet/" + actualId)
                .then().log().all()
                .assertThat().statusCode(200)
                .extract().response();



    }

    @When("I send a DELETE request to {string}")
    public void iSendADELETERequestTo(String urlPath) {
        response = RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .when().delete(petStoreBaseURI + urlPath + actualId)
                .then().log().all()
                .assertThat().statusCode(200)
                .extract().response();

        logger.info("User with the id \"" + actualId + "\" is deleted");
    }
}
