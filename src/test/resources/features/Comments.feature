Feature: Comments Endpoint
  This feature includes tests that test Comments RESTFul services

  @regression
  Scenario: Fetching invalid comments fails
    Given the jsonPlaceholder api is available
    When "/comments" endpoint is fetched with id '0'
    Then response throws status code '404'


  @regression
  Scenario: Fetching all comments is successful
    Given the jsonPlaceholder api is available
    When comment is fetched with endpoint "/comments"
    Then response throws status code '200'
    And response shows '500' list items


  @smoke
  Scenario: Fetching comments with id is successful
    Given the jsonPlaceholder api is available
    When "/comments" endpoint is fetched with id '2'
    Then response throws status code '200'
    And response has "email" as "Jayne_Kuhic@sydney.com"


  @smoke
  Scenario: Comments creation successful
    Given the jsonPlaceholder api is available
    When comment is created with postid "4",name "automated Test",email "automated_test@gmail.com" and body "lorem ipsum"
    Then response throws status code '201'
    And response has "name" as "automated Test"
    And response has "email" as "automated_test@gmail.com"
    And response has "body" as "lorem ipsum"


  @regression
  Scenario: Update fails for non-existent comment
    Given the jsonPlaceholder api is available
    When comment "501" is updated with postid "50",name "automated Test",email "automated_test@gmail.com" and body "Updated - lorem ipsum"
    Then response throws status code '500'


  @regression
  Scenario: Comment updation successful
    Given the jsonPlaceholder api is available
    When comment "120" is updated with postid "50",name "automated Test",email "automated_test@gmail.com" and body "Updated - body"
    Then response throws status code '200'
    And response has "body" as "Updated - Body"


  @regression
  Scenario: Comment patching successful
    Given the jsonPlaceholder api is available
    When comment "14" is patched only with "body" as "Patching - Only body"
    Then response throws status code '200'
    And response has "body" as "Patching - Only body"


  @regression
  Scenario: Comment deleting successful
    Given the jsonPlaceholder api is available
    When the comment "10" is deleted
    Then response throws status code '200'