Feature: Comments Endpoint
  This feature includes tests that test Comments RESTFul services

  @comments
  Scenario: Fetching invalid comments fails
    Given the jsonPlaceholder api is available
    When "/comments" endpoint is fetched with id '0'
    Then response throws status code '404'


  @comments
  Scenario: Fetching all comments is successful
    Given the jsonPlaceholder api is available
    When comment is fetched with endpoint "/comments"
    Then response throws status code '200'
    And response shows '500' list items


  @comments
  Scenario: Fetching comments with id is successful
    Given the jsonPlaceholder api is available
    When "/comments" endpoint is fetched with id '2'
    Then response throws status code '200'
    And response has "email" as "Jayne_Kuhic@sydney.com"


  @comments
  Scenario: Comments creation successful
    Given the jsonPlaceholder api is available
    When comment is created with postid "4",name "automated Test",email "automated_test@gmail.com" and body "lorem ipsum"
    Then response throws status code '201'
    And response has "name" as "automated Test"
    And response has "email" as "automated_test@gmail.com"
    And response has "body" as "lorem ipsum"