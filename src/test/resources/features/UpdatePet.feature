Feature: Update Pet

  AS A user
  I WANT TO use pet API to Update existing pet
  SO THAT I can view its updated details

  Scenario: Update existing Pet with Valid Data
    Given New pet with name "TestPetWithGet", age 10 , avatarUrl "TestPetWithGet.html" and category "TestPetWithGet.category"
    When Adding the new pet to the store
    Then The pet is added successfully
    When I try to update the newly added Pet
    Then Update Pet Data should be done successfully
    And I can find the Pet with its new name

  @Finding2.4
  Scenario: Update Pet by using invalid PetId
    When I try to update Pet using invalid PetId
    Then Update Pet Data should fail

  @Finding3
  Scenario: Update existing Pet with exceeded length category
    Given New pet with name "TestPetWithGet", age 10 , avatarUrl "TestPetWithGet.html" and category "TestPetWithGet.category"
    When Adding the new pet to the store
    Then The pet is added successfully
    When I try to update the newly added Pet with exceeded category value length
    Then Update Pet Data should fail

  Scenario: Update existing Pet with missing name
    Given New pet with name "TestPetWithGet", age 10 , avatarUrl "TestPetWithGet.html" and category "TestPetWithGet.category"
    When Adding the new pet to the store
    Then The pet is added successfully
    When I try to update the newly added Pet without providing name
    Then Update Pet Data should fail


  Scenario: Update existing Pet with missing age
    Given New pet with name "TestPetWithGet", age 10 , avatarUrl "TestPetWithGet.html" and category "TestPetWithGet.category"
    When Adding the new pet to the store
    Then The pet is added successfully
    When I try to update the newly added Pet without providing age
    Then Update Pet Data should fail