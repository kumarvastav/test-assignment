Feature: Post Creation
  This feature includes tests that test Post RESTFul services

  @smokeTest
  Scenario: Fetching invalid post fails
    Given the Post creation API is available
    When post is fetched with id '0'
    Then response throws status code '404'


  @smokeTest
  Scenario: Fetching all posts is successful
    Given the Post creation API is available
    When post is fetched with endpoint "/posts"
    Then response throws status code '200'
    And list of all '100' posts are shown


  @smokeTest
  Scenario: Fetching post with id is successful
    Given the Post creation API is available
    When post is fetched with id '2'
    Then response throws status code '200'
    And post title "qui est esse" is shown


  @smokeTest
  Scenario: Post creation successful
    Given the Post creation API is available
    When post is created with userid "4",title "automated Test" and body "random"
    Then response throws status code '201'
    And post title "automated Test" is shown


  @smokeTest
  Scenario: Post comments are visible
    Given the Post creation API is available
    When post is fetched with id "2" with endpoint "/comments"
    Then response throws status code '200'