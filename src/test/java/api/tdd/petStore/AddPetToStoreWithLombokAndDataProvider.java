package api.tdd.petStore;

import api.pojo_classes.petStore.Category;
import api.pojo_classes.petStore.CreatePet;
import api.pojo_classes.petStore.Tags;
import com.github.javafaker.Faker;
import com.jayway.jsonpath.JsonPath;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utils.ConfigReader;
import utils.DataProviderUtil;

import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AddPetToStoreWithLombokAndDataProvider {

    static Logger logger = LogManager.getLogger(AddPetToStoreWithLombokAndDataProvider.class);
    Response response;
    Faker faker = new Faker();
//    int actualId;
//    String actualName;
//    String actualEmail;
//    String actualGender;
//    String actualStatus;


    @BeforeSuite
    public void testStarts(){
        System.out.println("The automation test is staring");
    }

    @BeforeTest
    public void beforeTestStarts(){
        RestAssured.baseURI = ConfigReader.getProperty("PetStoreBaseURI");
    }

    @Test (dataProvider = "DataFromExcel", dataProviderClass = DataProviderUtil.class)
    public void addPetWithDataProvider(String petId, String categoryId, String categoryName, String petName, String tagId, String tagName, String status){

        Category category = Category.builder().id(Integer.parseInt(categoryId)).name(categoryName).build();

        Tags tags = Tags.builder().id(Integer.parseInt(tagId)).name(tagName).build();

        CreatePet createPet = CreatePet.builder()
                .id(Integer.parseInt(petId))
                .category(category)
                .name(petName)
                .photoUrls(Arrays.asList("My dog's URL"))
                .tags(Arrays.asList(tags))
                .status(status)
                .build();

        response = RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .body(createPet)
                .when().post("/v2/pet")
                .then().log().all()
                .assertThat().statusCode(200)
                .extract().response();

        // getting the value from the response with JayWay
        int actualPetIdWithJayWay = JsonPath.read(response.asString(), "id");
        System.out.println("Actual Pet id with JayWay is = " + actualPetIdWithJayWay);

        // Getting the value from the response with the RestAssured
        int actualPetIdWithRestAssured = response.jsonPath().getInt("id");
        System.out.println("Actual pet id with RestAssured is = " + actualPetIdWithRestAssured);

        int actualCategoryIdWithJayWay = JsonPath.read(response.asString(), "category.id");
        System.out.println("Actual Category id with JayWay is = " + actualCategoryIdWithJayWay);

        int actualTagIdWithJayWay = JsonPath.read(response.asString(), "tags[0].id");
        System.out.println("Actual Tags id with JayWay is = " + actualTagIdWithJayWay);

        // This variable is used to fail the test
        int failingTest = 10;

        // LOGGER MESSAGES SHOULD BE BEFORE THE ASSERTIONS
        logger.info("I am asserting actual and expected value");
        // Logging the test data with the Log4j
        logger.debug("Expected pet id should be " + Integer.parseInt(petId) + " but we found " + actualPetIdWithJayWay);

        // Asserting the result with Hamcrest
        assertThat(
                "I am expecting the Pet Id " + Integer.parseInt(petId), // this petId is coming from Excel
                actualPetIdWithJayWay,
                is(Integer.parseInt(petId))
        );

        // Asserting with TestNG
        Assert.assertEquals(actualPetIdWithJayWay, Integer.parseInt(petId));


    }
}
