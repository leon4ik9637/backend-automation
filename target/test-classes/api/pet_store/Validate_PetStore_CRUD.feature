Feature: As a QE, I am validating Pets CRUD Endpoints

  @api
  Scenario Outline: Validating the Pets CRUD Endpoints
#    Sending the Post request to create a pet
    Given Create a pet with the following data and send a POST request
      | categoryId   | <categoryId>   |
      | categoryName | <categoryName> |
      | petName      | <petName>      |
      | photoURL     | my pet URL     |
      | tagId        | <tagId>        |
      | tagName      | <tagName>      |
      | status       | <status>       |
      | petId        | <petId>        |
#    Validating the status code
    And Validate that status code is 200
#    Getting the created pet in
    And I send a GET request to "/v2/pet/"
#    Updating the pet info
    And I send a PUT request with the following data:
      | categoryId   | <categoryId>   |
      | categoryName | <categoryName> |
      | petName      | Jackson        |
      | photoURL     | my pet URL     |
      | tagId        | <tagId>        |
      | tagName      | Jackson        |
      | status       | pending        |
#    Deleting the updated user
    When I send a DELETE request to "/v2/pet/"
##    Then Validate that status code is 200
    Examples:
      | categoryId | petName | tagName | status  | tagId | categoryName | petId |
      | 21         | Bingo   | Bingo   | active | 25    | Dog          | 15    |