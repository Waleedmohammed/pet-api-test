Feature: Get Pet

  AS A user
  I WANT TO use pet API to get existing pet
  SO THAT I can view its details


  Scenario: Get existing pet from store given petId
    Given New pet with name "TestPetWithGet", age 10 , avatarUrl "TestPetWithGet.html" and category "TestPetWithGet.category"
    When Adding the new pet to the store
    Then The pet is added successfully
    And I can get the added pet with its created data

  @Finding2.2
  Scenario: Get not existing pet from store
    When I try to get not existing Pet
    Then I should get that Pet not found

  @Finding2.4
  Scenario: Get pet from store given not valid petId
    When I try to get Pet with not valid petId
    Then I should get Not valid ID Error


  Scenario: Get pet from store with invalid path
    When I try to get Pet from not valid path
    Then I should get Not found path Error

  @Finding2.4
  Scenario: Get pet from store without providing pet Id
    When I try to get Pet without providing Pet Id
    Then I should get Bad Request Error





