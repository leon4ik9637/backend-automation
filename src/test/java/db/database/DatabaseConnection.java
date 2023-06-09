package db.database;

import org.testng.Assert;

import java.sql.*;

public class DatabaseConnection {

    public static void main(String[] args) throws SQLException {

        /**
         * In order to connect to the database, we need URL, username, password and query
         * This can be your interview question
         */

        String url = "jdbc:oracle:thin:@batch4db1.cup7q3kvh5as.us-east-2.rds.amazonaws.com:1521/ORCL";
        String username = "techglobal";
        String password = "techglobal123!";
        String query = "select * from employees";

        // Creating the connection to the database with the parameters
        Connection connection = DriverManager.getConnection(url, username, password);
        System.out.println("Database connection is successful");

        // Statement keeps the connection between program
        // and the data source (database) for sending sql queries
        Statement statement = connection.createStatement();

        // Result set is sending the query to the database and get the result
        ResultSet resultSet = statement.executeQuery(query);

        // ResulSetMetaData gives the information about the table.
        // You cannot simply print out the column values. We need to put them into iterations.
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

        System.out.println("Number of the column: " + resultSetMetaData.getColumnCount());
        System.out.println("Column name: " + resultSetMetaData.getColumnName(1));
        System.out.println("Column display size : " + resultSetMetaData.getColumnDisplaySize(1));

        // You cannot print the value. You need a loop to get each row values
//        String first_name = resultSet.getString("FIRST_NAME");

        System.out.println("\n" + "First Name                  Last Name");
        while (resultSet.next()) {

            String firstName = resultSet.getString("FIRST_NAME"); // FIRST_NAME is the column name
//            System.out.println("First name of the employees: " + firstName);
            String lastName = resultSet.getString("LAST_NAME"); // LAST_NAME is the column name
//            System.out.println("Last name of the employees: " + lastName);
            System.out.println(firstName + "                          " + lastName);

            // Validating the data from the table
            if (firstName.equals("David") && lastName.equals("Austin")) {
                String actualName = firstName;
                Assert.assertEquals(actualName, "David");
                System.out.println("Actual name: " + firstName + "  " + lastName);
                break;
            }
        }
    }
}
