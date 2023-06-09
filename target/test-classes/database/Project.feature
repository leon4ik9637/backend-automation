Feature: As a QE, I am validating the JDBC Project 01

  @db
  Scenario Outline: Validating the JDBC Project 01
    Given User is able connect to database
    When User send the "<query>" to database and getting list
    Then Validate the employees first and last name who reports to Payam
      | Neena   | Kochhar   |
      | Lex     | De Haan   |
      | Den     | Raphaely  |
      | Matthew | Weiss     |
      | Adam    | Fripp     |
      | Payam   | Kaufling  |
      | Shanta  | Vollman   |
      | Kevin   | Mourgos   |
      | John    | Russell   |
      | Karen   | Partners  |
      | Alberto | Errazuriz |
      | Gerald  | Cambrault |
      | Eleni   | Zlotkey   |
      | Michael | Hartstein |

    Examples: Database query
      | query                                                                                                                      |
      | select first_name, last_name from employees where manager_id = (select manager_id from employees where first_name='Payam') |

