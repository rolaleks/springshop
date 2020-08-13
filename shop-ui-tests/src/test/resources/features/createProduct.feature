Feature: CreateProduct

  Scenario Outline: Product catalog
    Given I open admin web browser
    When I navigate to login page
    And I provide username as "<username>" and password as "<password>"
    And I click on login button
    When I navigate to product create page
    And I provide product title as "<title>" and description as "<description>" and cost as "<cost>"
    And I click on submit button
    Then Then product title should be "<title>"

    Examples:
      | username | password | title | description | cost |
      | user | 1ac3c653-e703-4638-b3f7-6e647b7af60c | product uitest2 | product uitest desc | 999 |



