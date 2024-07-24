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


  Scenario: Update Pet by using invalid PetId
    When I try to update Pet using invalid PetId
    Then Update Pet Data should fail