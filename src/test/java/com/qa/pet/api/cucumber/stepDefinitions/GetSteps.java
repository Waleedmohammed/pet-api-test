package com.qa.pet.api.cucumber.stepDefinitions;


import com.qa.pet.api.restassured.helper.ApiTestHelpers;
import com.qa.pet.api.restassured.helper.common.TestHelpers;
import com.qa.pet.api.restassured.utils.RestMethods;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;


public class GetSteps extends BaseSteps {


    @Before()
    public void setUp() {
        restMethods = new RestMethods(apiProperties, apiProperties.getBasePath());
        apiTestHelpers = new ApiTestHelpers(apiProperties);
    }

    @When("I try to get not existing Pet")
    public void iTryToGetNotExistingPet() {
        getResponse = restMethods.requestGET("petId", Integer.parseInt(TestHelpers.getRandomNumeric(4)));
    }

    @Then("I should get that Pet not found")
    public void iShouldGetThatPetNotFound() {
        verifyExpectedStatusCode(HttpStatus.SC_NOT_FOUND,getResponse.getStatusCode());
    }


    @When("I try to get Pet with not valid petId")
    public void iTryToGetPetWithNotValidPetId() {
        getResponse = restMethods.requestGET("petId", TestHelpers.getRandomAlphabetic(3));
    }

    @Then("I should get Not valid ID Error")
    public void iShouldGetNotValidIDError() {
        verifyExpectedStatusCode(HttpStatus.SC_BAD_REQUEST,getResponse.getStatusCode());
    }

    @When("I try to get Pet from not valid path")
    public void iTryToGetPetFromNotValidPath() {
        restMethods = new RestMethods(apiProperties, apiProperties.getBasePath().toUpperCase());
        apiTestHelpers = new ApiTestHelpers(apiProperties);
        getResponse = restMethods.requestGET("pet", 20);
    }

    @Then("I should get Not found path Error")
    public void iShouldGetNotFoundPathError() {
        verifyExpectedStatusCode(HttpStatus.SC_NOT_FOUND,getResponse.getStatusCode());
    }
}
