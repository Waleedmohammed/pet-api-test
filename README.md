# Pet Test
A framework designed to test PET API with 4 endpoints 

* Tools and Frameworks used 
    * JAVA
    * MAVEN
    * Spring boot
    * Junit
    * Cucumber java
    * Rest Assured
      
## Table of Contents 
- [Features](#Features)
- [Framework Structure](#Framework_Structure)
- [What is covered](#What_is_covered)
- [What is not covered](#What_is_not_covered)
- [How To Run Tests](#How_To_Run_Tests)

## Features
* This Project built with Spring boot Framework
* Used Cucumber framework to apply the design of BDD based on Gherkin language
* Visualized Reporting - *Using Cucumber Reports with different extensions
* Using Junit as test runner
* Using maven to build and also run tests via command line
* Using Rest Assured to implement API test cases

## Framework_Structure
1. src/main/java/com/qa/pet/api/restassured/conf
    * here located api properties class
      
2. src/main/java/com/qa/pet/api/restassured/deserialization
    * here located classes for deserializing JSON object to POJO classes

3. src/main/java/com/qa/pet/api/restassured/factory
   * here located all factory classes for rest config & Request generation

4. src/main/java/com/qa/pet/api/restassured/helper
   * here located test classes which contain some test methods to be used as test helpers

5. src/main/java/com/qa/pet/api/restassured/serialization
   * here located test class for serializing POJO class to JSON Object

6. src/main/java/com/qa/pet/api/restassured/utils
   * here located all utils classes
           RequestSpecs class : contain some common config used while sending Json Request using Rest assured
           ResponseSpecs class: contain some common config used for verifying any response received from API
           RestMethods class  : Impelementation of all posible rest HTTP methods with different method signature

7. src/main/resources/application.properties
   * All related application properties to be used for running test cases 
            test.api.base-url : API base URI
            test.api.base-path: API Base Path
            test.api.max-Pet-Category-Length : Max length value which should be accepeted by API schema for Pet Category field
     
8. src/test/java/com/qa/pet/api/cucumber/runner
   * here located cucumber test runner using Junit
  
9. com/qa/pet/api/cucumber/stepDefinitions
    * here located all cucumber step definitions
      
10. src/test/resources
    * here located all cucumber feature files and json schema which should be used for schema validation test


## What_is_covered
This testing framework cover : 
1. QA Functional tests for API HTTP methods (GET/POST/PUT/REMOVE)
2. Happy case scenarios
3. Negative case scenarios
4. Schema request + response validation

## What_is_not_covered
Below testing activities were not covered in scope of this framework:
1. *Security Testing* : it is important testing activity which needs to be considered to make sure that API can handle possible security breaches
   like sql injections . OWASP can be used as reference fo applying some security checks
2. *Performance Testing* : one vital non-functional testing activity which needs to be considered to make sure that our API can handle expected load on Production
3. *Unit Testing* : I observed that there is no unit tests exist on application project , It is really important to have sufficient unit tests 
   this will help to catch potential bugs as much as possible in early development phases
4. *Integration Testing*: The API integration with other interfaces should be tested to make sure that both interfaces can communicate to each other as expected


## How_To_Run_Tests
There are 3 options how you can run the tests:
1. *Docker:* This involves building a Docker image and running it in a container.
2. *maven:* using maven to build and run tests from cmd
3. *Intillij:* for running and debugging tests

### Docker
#### Prerequisites
 * Docker installed
 * fad-qa-task-app image is exist locally (I didn't want to push it to docker hub without having permission)

#### How to
1. Navigate into a root folder of this project in your terminal
2. Run docker compose to build tests docker image and run two docker containers fad-qa-task-app and pet-test-image
   run ``` docker compose up --build -d ```
3. You can runn all tests by running ``` docker exec -it pet-test-image mvn test ```
4. You should be able to check terminal logs for viewing test results and also to browse cucumber HTML URL

### Maven
#### Prerequisites
 * Java installed
 * Maven installed
 * Up and running instance of fad-qa-task-app

#### How to
1. Navigate to src/main/resources/application.properties and change property test.api.base-url from "http://pet-app:3000/api" to "http://localhost:3000/api"
2. Navigate into a root folder of this project in your terminal
3. Run ``` mvn clean install ```
4. You should be able to check terminal logs for viewing test results and also to browse cucumber HTML URL

### IntelliJ
#### Prerequisites
 * Java installed
 * Maven installed
 * Up and running instance of fad-qa-task-app

#### How to
1. Navigate to src/main/resources/application.properties and change property test.api.base-url from "http://pet-app:3000/api" to "http://localhost:3000/api"
2. Run ``` mvn clean install -Dmaven.test.skip=true ```
3. You can run tests from Test runner class "src/test/java/com/qa/pet/api/cucumber/runner/RunCucumberTest.java"


   
