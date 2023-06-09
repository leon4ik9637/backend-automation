Feature: As a QE, I validate that I can connect to DB

  @db
  Scenario Outline: Validating the min salary from the employee table
    Given User is able connect to database
    When User send the "<query>" to database
    Then Validate the <salary>
    Examples: Database query
      | query                             | salary |
      | select min(salary) from employees | 2101   |

#    Scenario Outline: Validate connecting to db
#      Given User is able connect to database
#      When User send the "<query>" to database
#      Then Validate the <salary>
#      Examples: Database query
#        | query                             | salary |
#        | select min(salary) from employees | 2100   |

      # Everyone! Please do not push your code from master