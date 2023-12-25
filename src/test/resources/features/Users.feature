Feature: Users Endpoint
  This feature includes tests that test Users RESTFul services

  @users
  Scenario: Fetching invalid post fails
    Given the jsonPlaceholder api is available
    When "/users" endpoint is fetched with id '0'
    Then response throws status code '404'


  @users
  Scenario: Fetching all users is successful
    Given the jsonPlaceholder api is available
    When user is fetched with endpoint "/users"
    Then response throws status code '200'
    And response shows '10' list items