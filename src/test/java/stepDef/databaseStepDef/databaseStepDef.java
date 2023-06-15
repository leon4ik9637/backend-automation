package stepDef.databaseStepDef;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.datatable.DataTable;
import org.testng.Assert;
import utils.DBUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static utils.DBUtil.executeQuery;

public class databaseStepDef {
    static String classQuery;
    static List<List<Object>> actualQuery = new ArrayList<>();

    @Given("User is able connect to database")
    public void user_is_able_connect_to_database() {
        DBUtil.createDBConnection();
    }

    @When("User send the {string} to database")
    public void user_send_the_to_database(String query) {
        classQuery = query;
        executeQuery(classQuery);
        System.out.println("Query execution is successful!");
    }

    @Then("Validate the {int}")
    public void validate_the(Integer expectedSalary) {
        Object actualMinSalary = DBUtil.getCellValue(classQuery);
        Assert.assertEquals(actualMinSalary, new BigDecimal(expectedSalary));
    }

    @When("User send the {string} to database and getting list")
    public void userSendTheToDatabaseAndGettingList(String query) {
        actualQuery = DBUtil.getQueryResultList(query);
    }

    @Then("Validate the employees first and last name who reports to Payam")
    public void validateTheEmployeesFirstAndLastNameWhoReportsToPayam(DataTable dataTable) {

        List<List<String>> listFromFeature = dataTable.asLists();


        for (int i = 0; i < listFromFeature.size(); i++) {
            Assert.assertEquals(listFromFeature.get(i), actualQuery.get(i));
            System.out.println(" data from the feature " + listFromFeature.get(i) + " data from the db " + actualQuery.get(i));
        }

    }
}


