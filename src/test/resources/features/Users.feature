Feature: Users Endpoint
  This feature includes tests that test Users RESTFul services

  @regression
  Scenario: Fetching invalid post fails
    Given the jsonPlaceholder api is available
    When "/users" endpoint is fetched with id '0'
    Then response throws status code '404'


  @regression
  Scenario: Fetching all users is successful
    Given the jsonPlaceholder api is available
    When user is fetched with endpoint "/users"
    Then response throws status code '200'
    And response shows '10' list items

  @smoke
  Scenario: Fetching a user with id is successful
    Given the jsonPlaceholder api is available
    When "/users" endpoint is fetched with id '9'
    Then response throws status code '200'
    And response has "name" as "Glenna Reichert"

  @smoke
  Scenario: User creation is successful
    Given the jsonPlaceholder api is available
    When a user is created with name "John Smith", username "john_smith", email "john_smith@gmail.com", phone "024-648-3804", website "ambrose.net"
    Then response throws status code '201'
    And response has "name" as "John Smith"

  @regression
  Scenario: Update User fails for non-existent user
    Given the jsonPlaceholder api is available
    When user "112" is updated with name "John Smith", username "john_smith", email "john_smith@gmail.com", phone "024-648-3804", website "ambrose.net"
    Then response throws status code '500'


  @regression
  Scenario: Update User successful
    Given the jsonPlaceholder api is available
    When user "10" is updated with name "William Hills", username "william_hills", email "william_hills@gmail.com", phone "024-648-3804", website "william.net"
    Then response throws status code '200'
    And response has "username" as "william_hills"


  @regression
  Scenario: User patching successful
    Given the jsonPlaceholder api is available
    When user "2" is patched with "name" as "Updated - Name" and "website" as "Updated - website"
    Then response throws status code '200'
    And response has "name" as "Updated - Name"
    And response has "website" as "Updated - website"


  @regression
  Scenario: Delete User successful
    Given the jsonPlaceholder api is available
    When the user "10" is deleted
    Then response throws status code '200'


  @new
  Scenario: User posts are visible
    Given the jsonPlaceholder api is available
    When user is fetched with id "2" using endpoint "/posts"
    Then response throws status code '200'
    And response shows all items with "userId" as '2'