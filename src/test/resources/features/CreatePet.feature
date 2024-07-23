Feature: Create New Pet

  AS A user
  I WANT TO use pet API to add pet
  SO THAT I can view it later on

  @schemaTest
  Scenario Outline: Add new pet to store with valid pet data
    Given New pet with name <name>, age <age> , avatarUrl <url> and category <category>
    When adding the new pet to the store
    Then the pet is added successfully
    Examples:
      | name     | age | url      | category      |
      | "NewPet" | 20  | "NewUrl" | "NewCategory" |
      | "NewPet" | 20  | ""       | "NewCategory" |
      | "NewPet" | 20  | "NewUrl" | ""            |

  Scenario Outline: Add new pet to store with missing name
    Given New pet without name , age <age> , avatarUrl <url> and category <category>
    When adding the new pet to the store
    Then the pet can not be added
    Examples:
      | age   | url       | category        |
      | 20    | "NewUrl"  | "NewCategory"   |

  Scenario Outline: Add new pet to store with missing age
    Given New pet with name <name> , without age , avatarUrl <url> and category <category>
    When adding the new pet to the store
    Then the pet can not be added
    Examples:
      | name     |  url       | category        |
      | "NewPet" |  "NewUrl"  | "NewCategory"   |



  Scenario Outline: Add new pet to store with exceeded length category
    Given New pet with name <name>, age <age> , avatarUrl <url> and exceeded max Length Category
    When adding the new pet to the store
    Then the pet can not be added due to validation error
    Examples:
      | name     | age   | url       |
      | "NewPet" | 20    | "NewUrl"  |
