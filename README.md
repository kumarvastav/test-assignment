[![build](https://github.com/kumarvastav/test-assignment/actions/workflows/gradle-build.yml/badge.svg)](https://github.com/kumarvastav/test-assignment/actions/workflows/gradle-build.yml)

# test-assignment
#### API testing using RestAssured, Cucumber, Java tech-stack


## Introduction

The project is demonstration of API testing. Host: https://jsonplaceholder.typicode.com/
Suite consists of user persona's related to the various endpoints i.e. Posts, Comments, Users

1. Make posts: https://jsonplaceholder.typicode.com/posts
2. Comment on posts: https://jsonplaceholder.typicode.com/comments
3. List of users: https://jsonplaceholder.typicode.com/users

##### Tech-stack Used:
- Java
- Cucumber
- Rest-Assured

## Instructions
    1 Clone the repo:
        ~/: git clone git@github.com:kumarvastav/test-assignment.git

    2 Execute tests

        - Run all the tests
            ~/: ./gradlew clean test

        - Run selective tests using tags
            ~/: gradle test -Dcucumber.options="--tags @{TAG_NAME_USED_IN_FEATURES}"
                Ex: gradle test -Dcucumber.options="--tags @smoke"

    3 Report path
        Checkout the path: target/cucumber-report.html/index.html

## Observations

1. The end-points don't support any validation on bad request payload, missing values,mandatory checks
2. Creation/Deletion/Updation is not persistent on server so no concreate E2E could be possible from User standpoint
