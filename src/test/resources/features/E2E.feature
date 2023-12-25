Feature: E2E User functional flows
  This feature includes tests to ensure User scenarios using User, Post, Comment endpoints

  @e2e
  Scenario: User can create a post and comment on it
    Given a post is created with userid "10",title "User creating post" and body "random"
    When a comment is created on the above post with name "User can create a post and comment on it",email "automated_test@gmail.com" and body "lorem ipsum"
    Then response throws status code '201'
    And response has "name" as "User can create a post and comment on it"