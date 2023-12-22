Feature: Posts Endpoint
  This feature includes tests that test Post RESTFul services

  @regressionTest
  Scenario: Fetching invalid post fails
    Given the jsonPlaceholder api is available
    When "/posts" endpoint is fetched with id '0'
    Then response throws status code '404'


  @smokeTest
  Scenario: Fetching all posts is successful
    Given the jsonPlaceholder api is available
    When post is fetched with endpoint "/posts"
    Then response throws status code '200'
    And response shows '100' list items


  @regressionTest
  Scenario: Fetching post with id is successful
    Given the jsonPlaceholder api is available
    When "/posts" endpoint is fetched with id '2'
    Then response throws status code '200'
    And response has "title" as "qui est esse"


  @smokeTest
  Scenario: Post creation successful
    Given the jsonPlaceholder api is available
    When post is created with userid "4",title "automated Test" and body "random"
    Then response throws status code '201'
    And response has "title" as "automated Test"
    And response has "body" as "random"


  @regressionTest
  Scenario: Post comments are visible
    Given the jsonPlaceholder api is available
    When post is fetched with id "2" with endpoint "/comments"
    Then response throws status code '200'