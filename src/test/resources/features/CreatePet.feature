Feature: Create New Pet

  AS A user
  I WANT TO use pet API to add pet
  SO THAT I can view it later on

  @Finding2.1
  Scenario Outline: Add new pet to store with valid pet data
    Given New pet with name <name>, age <age> , avatarUrl <url> and category <category>
    When Adding the new pet to the store
    Then The pet is added successfully
    And I can get the added pet with its created data
    Examples:
      | name     | age | url      | category      |
      | "NewPet" | 20  | "NewUrl" | "NewCategory" |
      | "NewPet" | 20  | ""       | "NewCategory" |
      | "NewPet" | 20  | "NewUrl" | ""            |


  Scenario: Add new pet to store with missing name
    Given New pet without name , age 20 , avatarUrl "NewUrl" and category "NewCategory"
    When Adding the new pet to the store
    Then The pet can not be added


  Scenario: Add new pet to store with missing age
    Given New pet with name "NewPet" , without age , avatarUrl "NewUrl" and category "NewCategory"
    When Adding the new pet to the store
    Then The pet can not be added

  @Finding3
  Scenario: Add new pet to store with exceeded length category
    Given New pet with name "NewPet", age 20 , avatarUrl "NewUrl" and exceeded max Length Category
    When Adding the new pet to the store
    Then The pet can not be added due to validation error


  Scenario: Add new pet to store with Invalid path
    Given New pet with name "NewPet", age 20 , avatarUrl "NewUrl" and category "NewCategory"
    When Adding the new pet to the store using not valid path
    Then Adding Pet should fail with path not found error