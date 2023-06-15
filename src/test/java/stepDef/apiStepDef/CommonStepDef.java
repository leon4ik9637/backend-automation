package stepDef.apiStepDef;

import io.cucumber.java.en.Given;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static utils.Hooks.response;

public class CommonStepDef {

    static Logger logger = LogManager.getLogger(CommonStepDef.class);

    int actualStatusCode;

    @Given("Validate that status code is {int}")
    public void validate_that_status_code_is(Integer expectedStatusCode) {

        // Getting the status code from the most recent response body
        actualStatusCode = response.statusCode();

        assertThat(
                "Expect response status code: " + expectedStatusCode,
                actualStatusCode,
                is(expectedStatusCode)
        );

        logger.info("Expected status code is " + expectedStatusCode + ", we found " + actualStatusCode);

    }
}
