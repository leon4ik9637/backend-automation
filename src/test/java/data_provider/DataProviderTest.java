package data_provider;

import org.testng.annotations.Test;

public class DataProviderTest {

    // Making the connection to data provider class with the data and class names
    @Test(dataProvider =  "Courses", dataProviderClass = DataProviderTest.class)
    public void testingCourseData(String course, int courseId, String batch) {
        System.out.println(course + "|" + courseId + "|" + batch);
    }
}
