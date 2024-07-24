package com.qa.pet.api.cucumber.stepDefinitions;


import com.qa.pet.api.restassured.deserialization.ValidationErrorResponse;
import com.qa.pet.api.restassured.factory.RequestGenerator;
import com.qa.pet.api.restassured.helper.ApiTestHelpers;
import com.qa.pet.api.restassured.helper.common.TestHelpers;
import com.qa.pet.api.restassured.utils.RestMethods;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;


@Slf4j
public class PostSteps extends BaseSteps {

    @Before()
    public void setUp() {
        restMethods = new RestMethods(apiProperties, apiProperties.getBasePath());
        apiTestHelpers = new ApiTestHelpers(apiProperties);
    }


    @Given("New pet with name {string}, age {int} , avatarUrl {string} and category {string}")
    public void newPetWithNameNameAgeAgeAvatarUrlUrlAndCategoryCategory(String name, Integer age, String url, String category) {
        petData = RequestGenerator.PetDataRequest(name, age, url, category).build();
    }


    @When("Adding the new pet to the store")
    public void addingTheNewPetToTheStore() {
        postPet = executePostPet(petData);
    }

    @Then("The pet is added successfully")
    public void thePetIsAdded() {
        verifyExpectedStatusCode(HttpStatus.SC_CREATED,postResponse.getStatusCode());
        Assertions.assertNotNull(postPet.getId());
    }


    @Then("The pet can not be added")
    public void thePetCanNotBeAdded() {

        String actualErrorMsg = postResponse.as(ValidationErrorResponse.class).getMessage();
        String actualError = postResponse.as(ValidationErrorResponse.class).getError();
        String actualErrorCode = postResponse.as(ValidationErrorResponse.class).getCode();

        Assertions.assertAll(
                () -> Assertions.assertEquals(HttpStatus.SC_BAD_REQUEST, postResponse.getStatusCode(), "Not matching HTTP status Code"),
                () -> Assertions.assertTrue(actualErrorMsg.contains("body must have required property"), "Not matching expected error message"),
                () -> Assertions.assertEquals("Bad Request", actualError, "Not matching expected error"),
                () -> Assertions.assertEquals("FST_ERR_VALIDATION", actualErrorCode, "Not matching expected error code")
        );

    }


    @Given("New pet without name , age {int} , avatarUrl {string} and category {string}")
    public void newPetWithoutNameAgeAgeAvatarUrlUrlAndCategoryCategory(Integer age, String url, String category) {
        petData = RequestGenerator.PetDataRequestWithoutName(age, url, category).build();
    }

    @Given("New pet with name {string} , without age , avatarUrl {string} and category {string}")
    public void newPetWithNameNameNullAgeAvatarUrlUrlAndCategoryCategory(String name, String url, String category) {
        petData = RequestGenerator.PetDataRequestWithoutAge(name, url, category).build();
    }

    @Given("New pet with name {string}, age {int} , avatarUrl {string} and exceeded max Length Category")
    public void newPetWithNameNameAgeAgeAvatarUrlUrlAndExceededMaxLengthCategory(String name, Integer age, String url) {
        petData = RequestGenerator.PetDataRequest(name, age, url, TestHelpers.getRandomAlphabetic(apiProperties.getMaxPetCategoryLength() + 1)).build();
    }

    @Then("The pet can not be added due to validation error")
    public void thePetCanNotBeAddedDueToValidationError() throws Exception {
        verifyExpectedStatusCode(HttpStatus.SC_UNPROCESSABLE_ENTITY,postResponse.getStatusCode());
    }

    @And("I can get the added pet with its created data")
    public void iCanGetTheAddedPetWithItsCreatedData() throws Exception {
        getPetAndValidateResponse();
    }

    @When("I try to update the newly added Pet")
    public void iTryToUpdateTheNewlyAddedPetWithName() {
        String newName = TestHelpers.getRandomAlphabetic(10);
        Integer newAge = Integer.parseInt(TestHelpers.getRandomNumeric(2));
        String newUrl = TestHelpers.getRandomAlphabetic(2) + ".html";
        String newCategory = TestHelpers.getRandomAlphabetic(4);

        petData = RequestGenerator.PetDataRequest(newName, newAge, newUrl, newCategory).build();
        putResponse = restMethods.requestPUT(petData, "petId", postPet.getId());
    }

    @Then("Update Pet Data should be done successfully")
    public void updatePetNameShouldBeDoneSuccessfully() {
        verifyExpectedStatusCode(HttpStatus.SC_OK,putResponse.getStatusCode());
    }

    @And("I can find the Pet with its new name")
    public void iCanFindThePetWithItsNewName() throws Exception {
        getPetAndValidateResponse();
    }

    @When("I try to update Pet using invalid PetId")
    public void iTryToUpdatePetUsingInvalidPetId() {
        petData = RequestGenerator.PetDataRequest("Name", 20, "url", "category").build();
        putResponse = restMethods.requestPUT(petData, "petId", TestHelpers.getRandomAlphabetic(3));
    }

    @Then("Update Pet Data should fail")
    public void updatePetDataShouldFail() {
        verifyExpectedStatusCode(HttpStatus.SC_BAD_REQUEST,putResponse.getStatusCode());
        log.info("Update Pet Failed as Expected !");
    }

    @When("I delete the newly added pet")
    public void iDeleteTheNewlyAddedPet() {
        deleteResponse = restMethods.requestRemove("/pet", "petId", postPet.getId());
    }

    @Then("Pet should be deleted successfully")
    public void petShouldBeDeletedSuccessfully() {
        verifyExpectedStatusCode(HttpStatus.SC_OK,deleteResponse.getStatusCode());
    }

    @And("I can not find the pet afterwards on our API")
    public void iCanNotFindThePetAfterwardsOnOurAPI() throws Exception {
        getResponse = restMethods.requestGET("petId", postPet.getId());
        verifyExpectedStatusCode(HttpStatus.SC_NOT_FOUND,getResponse.getStatusCode());
    }

    @When("I try to delete not existing pet")
    public void iTryToDeleteNotExistingPet() {
        deleteResponse = restMethods.requestRemove("/pet", "petId", Integer.parseInt(TestHelpers.getRandomNumeric(4)));
    }

    @Then("Delete Pet should fail")
    public void deletePetShouldFail() {
        verifyExpectedStatusCode(HttpStatus.SC_NOT_FOUND,deleteResponse.getStatusCode());
    }

    @When("I try to delete pet with invalid Pet Id Value")
    public void iTryToDeletePetWithInvalidPetIdValue() {
        deleteResponse = restMethods.requestRemove("/pet", "petId", TestHelpers.getRandomAlphabetic(4));
    }

    @Then("Delete Pet should fail due to validation error")
    public void deletePetShouldFailDueToValidationError() {
        verifyExpectedStatusCode(HttpStatus.SC_BAD_REQUEST,deleteResponse.getStatusCode());
    }

    @When("Adding the new pet to the store using not valid path")
    public void addingTheNewPetToTheStoreUsingNotValidPath() {
        restMethods = new RestMethods(apiProperties, apiProperties.getBasePath().toUpperCase());
        postPet = executePostPet(petData);
    }

    @Then("Adding Pet should fail with path not found error")
    public void addingPetShouldFailWithPathNotFoundError() {
        verifyExpectedStatusCode(HttpStatus.SC_NOT_FOUND,postResponse.getStatusCode());
    }

    @When("I try to update the newly added Pet with exceeded category value length")
    public void iTryToUpdateTheNewlyAddedPetWithExceededCategoryValueLength() {
        petData = RequestGenerator.PetDataRequest(TestHelpers.getRandomAlphabetic(10),
                Integer.parseInt(TestHelpers.getRandomNumeric(2)),
                TestHelpers.getRandomAlphabetic(10)+".html",
                TestHelpers.getRandomAlphabetic(52)).build();


        putResponse = restMethods.requestPUT(petData, "petId", postPet.getId());
    }

    @When("I try to update the newly added Pet without providing name")
    public void iTryToUpdateTheNewlyAddedPetWithoutProvidingName() {
        petData = RequestGenerator.PetDataRequestWithoutName(
                Integer.parseInt(TestHelpers.getRandomNumeric(2)),
                TestHelpers.getRandomAlphabetic(10)+".html",
                TestHelpers.getRandomAlphabetic(52)).build();

        putResponse = restMethods.requestPUT(petData, "petId", postPet.getId());
    }

    @When("I try to update the newly added Pet without providing age")
    public void iTryToUpdateTheNewlyAddedPetWithoutProvidingAge() {
        petData = RequestGenerator.PetDataRequestWithoutAge(TestHelpers.getRandomAlphabetic(10),
                TestHelpers.getRandomAlphabetic(10)+".html",
                TestHelpers.getRandomAlphabetic(52)).build();

        putResponse = restMethods.requestPUT(petData, "petId", postPet.getId());
    }
}
