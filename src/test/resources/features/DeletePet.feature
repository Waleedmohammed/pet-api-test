Feature: Delete Pet

  AS A user
  I WANT TO use pet API to delete pet
  SO THAT it can not be found on our system at all


  Scenario: Delete Existing Pet
    Given New pet with name "TestPetWithGet", age 10 , avatarUrl "TestPetWithGet.html" and category "TestPetWithGet.category"
    When Adding the new pet to the store
    Then The pet is added successfully
    And I can get the added pet with its created data
    When I delete the newly added pet
    Then Pet should be deleted successfully
    And I can not find the pet afterwards on our API


  Scenario: Delete Not Existing Pet
    When I try to delete not existing pet
    Then Delete Pet should fail


  Scenario: Delete Pet with invalid Pet Id Value
    When I try to delete pet with invalid Pet Id Value
    Then Delete Pet should fail due to validation error