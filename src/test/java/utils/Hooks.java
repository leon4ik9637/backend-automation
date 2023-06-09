package utils;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static utils.ConfigReader.getProperty;

public class Hooks {

    static Logger logger = LogManager.getLogger(Hooks.class);

    public static String goRestBaseURL;
    public static String token;

    @Before
    public void setUp(){
        goRestBaseURL= getProperty("GoRestBaseURI");
        token = getProperty("Token");
    }

    @After
    public void tearDown(){
        logger.info("Ending the test");
    }
}
