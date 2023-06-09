package db.database;

import org.testng.annotations.Test;
import utils.DBUtil;

import java.util.List;

public class TestDBUtil {
//    public static void main(String[] args) {
//        DBUtil.createDBConnection();
//        DBUtil.executeQuery("select * from countries");
//      List<List<Object>> result= DBUtil.getQueryResultList("select first_name, last_name from employees");
//        System.out.println(result);
//    }

    // Validate that user is able to connect database and fetch the data
    @Test
    public void executeDatabase() {
        DBUtil.createDBConnection();
//        DBUtil.executeQuery("select * from countries");
        List<List<Object>> result = DBUtil.getQueryResultList("select first_name, last_name from employees");
        System.out.println(result);
    }
}
