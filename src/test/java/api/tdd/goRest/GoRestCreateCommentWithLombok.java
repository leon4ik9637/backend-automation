package api.tdd.goRest;

import api.pojo_classes.GoRest.*;
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

import java.util.Arrays;

public class GoRestCreateCommentWithLombok {

    static Logger logger = LogManager.getLogger(GoRestCreateCommentWithLombok.class);
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
    public void createComment(){
        Links links = Links
                .builder()
                .previous(null)
                .current("https://gorest.co.in/public/v1/comments?page=1")
                .next("https://gorest.co.in/public/v1/comments?page=2")
                .build();

        PaginationComment paginationComment = PaginationComment
                .builder()
                .total(2000)
                .pages(211)
                .page(1)
                .limit(5)
                .links(links)
                .build();

        MetaComment metaComment = MetaComment.builder()
                .pagination(paginationComment)
                .build();

        DataComment dataComment0 = DataComment.builder()
                .id(2220)
                .post_id(5585)
                .name("Tech Global")
                .email(faker.internet().emailAddress())
                .body(faker.toString())
                .build();

        DataComment dataComment1 = DataComment.builder()
                .id(2221)
                .post_id(5586)
                .name("Tech Global")
                .email(faker.internet().emailAddress())
                .body(faker.toString())
                .build();

        DataComment dataComment2 = DataComment.builder()
                .id(2222)
                .post_id(5587)
                .name("Tech Global")
                .email(faker.internet().emailAddress())
                .body(faker.toString())
                .build();


        GoRestCommentWithLombok goRestCommentWithLombok = GoRestCommentWithLombok.builder()
                .code(408)
                .meta(metaComment)
                .data(Arrays.asList(dataComment0 ,dataComment1, dataComment2))
                .build();

        response = RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .header("Authorization", ConfigReader.getProperty("Token"))
                // with the latest versions of the rest assured, we do not need to do serialization
                // when we send the post, put, patch requests
                .body(goRestCommentWithLombok)
                .when().post("/public-api/comments")
                .then().log().all().extract().response();


        String actualPrevious = JsonPath.read(response.asString(), "meta.pagination.links.previous");

        logger.info("Previous value is: " + actualPrevious);

        Assert.assertEquals(actualPrevious, null);


    }
}
