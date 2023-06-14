package api.tdd.petStore;

import api.pojo_classes.petStore.Category;
import api.pojo_classes.petStore.CreatePet;
import api.pojo_classes.petStore.Tags;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.ConfigReader;

import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AddPetToStoreWithLombok {
    static Logger logger = LogManager.getLogger(AddPetToStoreWithLombok.class);
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
        RestAssured.baseURI = ConfigReader.getProperty("PetStoreBaseURI");
    }

    @Test
    public void addPetToPetStore() {

        // Building the category request object
        Category category = Category.builder()
                .id(1)
                .name("dog")
                .build();

        // Building the Tags request list
        Tags tags0 = Tags.builder()
                .id(15)
                .name("MyDogTag")
                .build();

        // Building the Tags request list
        Tags tags1 = Tags.builder()
                .id(16)
                .name("MyPuppyTag")
                .build();


        CreatePet createPet = CreatePet.builder()
                .id(9)
                .category(category)
                .name("Snow")
                .photoUrls(Arrays.asList("My dog's URL"))
                .tags(Arrays.asList(tags0, tags1))
                .status("available")
                .build();

        response = RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .body(createPet)
                .when().post("/v2/pet")
                .then().log().all()
                .assertThat().statusCode(200)
                .extract().response();

        int actualTagsId = response.jsonPath().getInt("tags[0].id");
        int expectedTagsId = tags0.getId();

        logger.error(actualTagsId + " should be matching with the expected one " + expectedTagsId);

        // first line is the reason why we are doing the assertion
        assertThat("I am expecting to get tags Id: " + tags0.getId(),
                // I need actual value to be able to assert with the expected one
                actualTagsId,
                // Checking if the expected value is matching with the actual
                is(tags0.getId()));


        logger.info("Actual tagsId is = " + actualTagsId);
        logger.info("(\"Actual tagsId is = \" + actualTagsId);\n" +
                "        logger.info(\"Expected tagsId is = " + expectedTagsId);




    }


}
