package utils;


import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static utils.ConfigReader.getProperty;

public class Hooks {

    static Logger logger = LogManager.getLogger(Hooks.class);

    public static String goRestBaseURL;
    public static String petStoreBaseURI;
    public static String token;
    public static Response response;

    @Before
    public void setUp(){
        goRestBaseURL = getProperty("GoRestBaseURI");
        petStoreBaseURI = getProperty("PetStoreBaseURI");
        token = getProperty("Token");
    }

    @After
    public void tearDown(){
        logger.info("Ending the test");
    }
}
