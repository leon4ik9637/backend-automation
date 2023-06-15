#@api
  Feature: As a QE, I validate the GoRest CRUD operations
    Scenario Outline: Validating the GoRest CRUD operations
      Given Create user with "<name>", "<gender>", email, and "<status>"
      Then Validate that status code is 201
      And Make GET call to get user with "<urlPath>"
      And Validate that status code is 200
      And Updating the user with the following data
        | name   | Batch 3 |
        | status | active  |
      And Validate that status code is 200
      When I delete user
      Then Validate that status code is 204

      Examples: GoRest data
        | name        | gender | status | urlPath           |
        | Tech Global | female | active | /public/v2/users/ |