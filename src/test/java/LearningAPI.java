public class LearningAPI {

    /*
    API 1
    API (Application Programming Interface)
    API - Communication between two technologies
    - API allows two technologies to talk to each other
    HTTP - hyper-text transfer protocol - It is a protocol (rules) to communicate between application through the web.
    HTTPs - Secure format of the HTTP
    HTTP METHODS (CRUD Operations)
    - CREATE - POST - Create the new data (resources) in the server/DB
    - READ     - GET - Retrieve information that already exists in the server
    - UPDATE - PUT OR PATCH - Modifying information that already exists
    - DELETE  - DELETE - Removing the data already exists on the server or DB
     Interview Questions:
    How to store your API’s calls in your Postman?
    - In the collection
    What is the difference between PUT and PATCH methods?
    - PUT updates all the data regardless of the change you made in the request body.
    - PATCH updates only the data that was changed in the request body

    HTTP Status Codes
    200 - OK 201 - Created
    202 - Accepted
    204 - Not Content
    400 - Bad Request - The server cannot or will not process the request due to something
    that is perceived to be a client error. You are missing the data in the request or misplace the data.
    Data type does not match with the expected on the request
    401 - Unauthorized - Client provides no credentials or invalid credentials
    403 - Forbidden - Has valid credentials but not enough privileges to perform an action on a resource.
    You don’t have access to visit this website
    404 - Not Found - The server can not find the requested resource. In the browser,
    this means the URL is not recognized
    500 - Server is down

    PARAMETERS

    Domain -> URL for webpage 
    Path Parameters -> (PP) - After the domain and before the question mark
    Query Parameters -> (QP)

    ———Domain—|-path p—|-Query p
    https://reqres.in/api/users?page=2

    Request Body parameters
    {
        "name": "Leon Kuchman",
        "job": "Software Engineer in Test"
    }
    name is key and it is called attribute or field or parameters
    Tech Global is a value of the attribute of name


    Why we use APIs? - We use the APIs to exchange the data between the applications or any other technologies
    For example: When you register the application, your personal data
    is created into database. This can happen only
    with the help of API. So, any specific API takes your personal data
    to database.

    Why we test APIs?
    To make sure that API is transferring the data based on the
    requirements and regulations

    JSON:
    JSON(JavaScript Object Notation) is a lightweight data interchange format that is now
    being used as a profound and efficient way of gathering, collecting, or share data among
    applications and interfaces
    SYNTAX OF THE JSON {“name”:”TechGlobal”}
    : —> is separating key and value
    Strings in JSON must be written in double quotes
    All the keys in the JSON body is String and it is with the double quotation

    Numbers: values are not in quotations
    Boolean for true and false

    We have key and value pairs
    { // it is holding the object
        “name”: “TechGlobal”,	 // comma is separating key and value pairs between data
        “job”: “Software Engineer in Test”,
        “id”: “841”,
        “createdAt”: “2022-07-13T23:15:11.966Z”
    }
    JSON objects are surrounded by curly braces {}.
    JSON objects are written in key/value pairs.
    Keys myst be strings, and values must be a valid JSON data type like
    string, number, object, array, boolean or null.
    Keys and values are separated by a colon.
    Each key/value pair is separated by a comma.

    NESTED OBJECT
    There is a parent object and there are also child objects in the JSON body

    Xml
    - is an extensible markup language that is designed to store data.
    - it is popularly used for transferring data.
    - it is case-sensitive. XML allows you to define markup elements and
    - generate customized markup language.
    - An element is a basic unit in the XML language.
    - The extension of XML file is .xml.
    XML FeaturesL
    * Xml tags are not predefined. You need to define your customized tags.
    * Carries data, not displaying data

    Web Service APIs:
    ALL THE APIS WORKING OVER THE WEB IS CALLED Web Service APIs
    There are different types of web services APIs:
    SOAP:
    - it is a protocol which was designed to ensure that programs built on different platforms exchange data easily
    - SOAP stands for Simple Object Access Protocol
    SOAP API is able to carry LESS data with the same speed and capacity

    REST is a procedure for developing any type of the API
    REST stands for Representational State Transfer
    If the web service is developed by the rest procedure then it is called Restful Web Service. Restful
    APIs are mostly using JSON formal.

    Restful API is able to carry more data with the same speed and capacity

    With the RestFull APIs we are able to transfer more data than the SOAP APIs
    RestFull APIs are used mostly for public usage
    SOAP APIs are used for the Secure transactions for Bank, Government
    or Financial applications.

    INTERVIEW QUESTION:
    MICRO SERVICES? What do you know about micro services? Have you work with it?

    JSON - JavaScript Object Notation
    - It is lightweight data format used by different languages.
    - It helps share data between application and interfaces.

    Web Services -> It is an API. It can be developed by both SOAP and
    RestFull APIs.
    SOAP --> is a protocol which was designed to ensure that program is built on different
    platform and programming languages could exchange data in an easy manner
    - It is slow when transferring data between applications
    - It is used for secure transactions
    - XML Data type is string and we need to convert to different data types
    - Developers need to convert xml to JavaScript
    - xml is hard to read

    REST
    - It is a principle or procedure to develop the APIs
    - if developer is using the REST principles and develops the WEB SERVICE APIs  then it is called RESTFull API
    - works with media components, files, or even objects on a particular hardware device
    - any web service that is defined on the principles of REST can be called a RestFull web service
    - REST stands for Representational State Transfer

    HTTP Methods
    CRUD Operations
    Create 	- Post
    Read	- Get
    Update	- Put - Patch
    Delete	- Delete

    RESPONSE STATUS CODES
    200 - OK
    201 - CREATED
    204 - NO CONTENT - WHEN YOU DELETE THE DATA FROM THE DATA SOURCE
    302 - FOUND
    304 - MODIFIED
    400 - BAD REQUEST - IT MEANS THAT YOUR REQUEST DATA IS NOT PROPER (URL)
    401 - UNAUTHORIZED - YOU CANNOT LOGIN SOMEHOW (WRONG CREDENTIALS)
    403 - FORBIDDEN - PRIVILEGE (NO ACCESS TO LOGIN)
    404 - NOT FOUND - THE ENDPOINT IS NOT AVAILABLE
    500 - SERVER IS DOWN

    API -> Application Programming Interface
    It is a program to help us exchange the data between technologies

    Interview Question:
    How is your API testing process?
    - I am reading the requirements and based on that I start writing my test cases. My test cases include all positive and negative test cases.
    Example: POSITIVE SCENARIOS:
            1. Validate GoRest GET request with “URL”
            2. Validate 200 status code for GoRest GET Request
            NEGATIVE SCENARIOS:
            13 Validate 404 GoRest POST request with “URL (missing path on the URL)”
            Given I prepare the POST request body
            When I send the POST request with the missing path 'https://gorest.co.in/users'
            Then I should get 404 status code

    I also validate JSON Schema.

    SCHEMA:
    1. The schema tells web servers which protocol to use when it accesses a page on your website
    2. A schema is metadata-data about how data is structured.
    3. API schemas provide machine-readable structure documentation that, together with regular documentation
    4. With the help of JSON schema test I can validate the DATA TYPE of the attribute and format of the API response body.

    Steps for Schema Validation
    1. Copy your response from postman
    2. On this page https://jsonschema.net/ login and paste it to left side
    3. Hit the submit button and copy the document from the right side
    4. Paste it to Postman test as following and follow the ready method to test it with the schema

    SWAGGER
    Swagger is an API documentation where we understand the functionalities of the APIs
    Positive Scenario:
    1. Validate that you are able to create Pet with POST request
    Given I am generating the POST request body
    When I send the POST request 'https://petstore.swagger.io/v2/pet' with request body
    Then the Pet should be created
    And the status code should be 200

    I also validate the JSON schema
    And I am able to do DDT with using CSV and JSON file
    I can create variables on my collection level with the help of collection run, I can execute my fuke

    For the Postman you know:
    1. Parameterize
    2. Chaining
    3. JSON Schema Validation
    4. Response values validation
    5. Data Driven Test with CSV and JSON files
    6. With the help of Chaining, I can automate the flow
    7. Validating the status codes and headers


    Negative Scenario
    Validate 404 status code when Post request has mistake in the URL path

    RECAP:
    - Schema validation
    - DDT with CSV and JSON files
    - Swagger - it is documentation for RestFull APIs

    ---REST ASSURED---
    - It is Java library for testing the RestFull web
    services (Restful Web Service: The API is developed
    by REST structure, and it works over the web)
    - It sends and invokes the response the RestFull
    APIs
    - It can be used for XML and JSON based web services
    - It supports POST, GET, PUT/PATCH, DELETE etc.
    - It can be integrated with TestNG and JUnit
    - It is like BDD

    POJO
    - Plain Old Java Object
    - POJOs are used for increasing the readability and
    re-usability of the program. POJOs have gained the
    most acceptance because they are easy to write and
    understand
    - POJO classes are extensively used for creating JSON
    and XML payloads for API.
    - POJO gives you flexibility in creating and
    manipulating data in simple ways



















     */
}
