Feature: Catalog

  Scenario Outline: Product catalog
    Given I open web browser
    When I navigate to product index page
    Then Page has products

    Examples:
      |
      |



