Feature: Post Creation
  This feature includes tests that test Post RESTFul services

  @smokeTest
  Scenario: User fails to get details of invalid post
    Given the Post creation API is available
    When post is fetched with id '0'
    Then response throws status code '404'


  @smokeTest
  Scenario: User is able to fetch all the posts using the endpoint
    Given the Post creation API is available
    When post is fetched with endpoint "/posts"
    Then response throws status code '200'
    And list of all '100' posts are shown


  @smokeTest
  Scenario: User is able to get details of a specific post
    Given the Post creation API is available
    When post is fetched with id '2'
    Then response throws status code '200'
    And post title "qui est esse" is shown
