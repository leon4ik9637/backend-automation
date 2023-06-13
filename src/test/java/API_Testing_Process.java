public class API_Testing_Process {
    /*
    1. I go through and understand the requirements from technical documentation
    In the technical documentation, we have:
        1. Urls
        2. Path params, Query Params
        3. Required headers
        4. Request and response body
        5. Response headers
        6. Required and non-required attributes (fields)
        7. Expected response times

    1. I also check the swagger documentation to see if that API requested is on my swagger.
    2. I start writing the test cases based on the requirements, and I have my test
    cases review meeting with my PO, System architect, developer, and couple of
    testers from my team or different teams. That helps me to make sure that I
    cover all the functionalities and that there are no redundant test cases.
    3. After development was completed, I validate the functionalities with Postman,
    and I validate:
        1. Status code (200, 201, 204, 400, 401, 403, 500)
        2. Headers (Content Type)
        3. Response attributes and values
        4. And I perform schema validation to ensure that the attributes' data types
           and format of the response body are based on the requirements.
        5. And Response time.
        6. I do parameterization with postman
        7. I can do chaining with postman
        8. I can perform DDT testing with postman with CSV and JSON files
    4. I can automate the test even before the development is completed with the
    help of the "shift-left" strategy. Just start developing my automation
    scripts based on the requirements. Once the functionality is ready, I just need
    to add more. Then the automation will be completed. That helps me to be able to
    finish manual and automation testing within Sprint.

    5. My framework is built with Maven, and the Programming language is Java.
        1. I use TestNG to execute and assert my tests.
        2. I store dependencies and plugins in the pom.xml
        3. I use the RestAssured library to automate the API testing
        4. I generate the cucumber reports to see the test results
        5. I have an util class in my util package to make writing script easy and increase
        the re-usability
        6. I have a resource folder for my feature files
        7. I have step definitions class
        8. I have POJO (Plain Old Java Object) classes
        9. In my framework, I use:
            1. Lombok to eliminate getters and setters when I build my request body and
            get the date from the POJO classes
            2. Object Mapper class, coming from the Jackson library, to serialize the Java
            object to the JSON object and de-serialize the JSON to Java object, But
            lately RestAssured library is taking care of the serialization
            3. Log4j for logging the test execution
            4. Hamcrest library for asserting the test. I like it because it is ease to read,
            and also I can use it ti validate the response values in the Response object
            5. Jayway library is to read the value from the response body
            6. Apache POI to ready the values from the Excel file
            7. I also use DataProvider to parameterize the variables, and I am able to read
            different data sets for the same test. I iterate my test for Data-Driven Test.
            8. I also can do SCHEMA validation with json-schema-validation dependency
            coming from the RestAssured library.




     */
}
