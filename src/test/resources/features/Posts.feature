Feature: Posts Endpoint
  This feature includes tests that test Post RESTFul services

  @regression
  Scenario: Fetching invalid post fails
    Given the jsonPlaceholder api is available
    When "/posts" endpoint is fetched with id '0'
    Then response throws status code '404'


  @regression
  Scenario: Fetching all posts is successful
    Given the jsonPlaceholder api is available
    When post is fetched with endpoint "/posts"
    Then response throws status code '200'
    And response shows '100' list items


  @smoke
  Scenario: Fetching post with id is successful
    Given the jsonPlaceholder api is available
    When "/posts" endpoint is fetched with id '2'
    Then response throws status code '200'
    And response has "title" as "qui est esse"


  @smoke
  Scenario: Post creation successful
    Given the jsonPlaceholder api is available
    When a post is created with userid "4",title "automated Test" and body "random"
    Then response throws status code '201'
    And response has "title" as "automated Test"
    And response has "body" as "random"


  @regression
  Scenario: Update fails for non-existent post
    Given the jsonPlaceholder api is available
    When post "140" is updated with userid "12", title "Updated" and body "Updated - Body"
    Then response throws status code '500'


  @regression
  Scenario: Post updation successful
    Given the jsonPlaceholder api is available
    When post "20" is updated with userid "12", title "Updated" and body "Updated - Body"
    Then response throws status code '200'
    And response has "body" as "Updated - Body"


  @regression
  Scenario: Post patching successful
    Given the jsonPlaceholder api is available
    When post "14" is patched only with "title" as "Patching - Only title"
    Then response throws status code '200'
    And response has "title" as "Patching - Only title"


  @smoke
  Scenario: Delete Post successful
    Given the jsonPlaceholder api is available
    When the post "10" is deleted
    Then response throws status code '200'


  @regression
  Scenario: Post comments are visible
    Given the jsonPlaceholder api is available
    When post is fetched with id "2" using endpoint "/comments"
    Then response throws status code '200'