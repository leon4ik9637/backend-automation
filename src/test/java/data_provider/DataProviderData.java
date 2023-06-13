package data_provider;

import org.testng.annotations.DataProvider;

public class DataProviderData {

    @DataProvider(name = "Courses")
    public static Object[][] getDataFromDataProvider(){

        return new Object[][] {
                {"Java", 1, "Batch4"},
                {"Selenium", 2, "Batch4"},
                {"Appium", 3, "Batch4"},
                {"SQL", 4, "Batch4"},
                {"API", 5, "Batch4"}
        };
    }
}
