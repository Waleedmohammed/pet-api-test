package com.qa.pet.api.cucumber.stepDefinitions;


import com.qa.pet.api.restassured.helper.ApiTestHelpers;
import com.qa.pet.api.restassured.helper.common.TestHelpers;
import com.qa.pet.api.restassured.utils.RestMethods;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.HttpStatus;


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
        getResponse = restMethods.requestGET("pet", 20);
    }

    @Then("I should get Not found path Error")
    public void iShouldGetNotFoundPathError() {
        verifyExpectedStatusCode(HttpStatus.SC_NOT_FOUND,getResponse.getStatusCode());
    }

    @When("I try to get Pet without providing Pet Id")
    public void iTryToGetPetWithoutProvidingPetId() {
        getResponse = restMethods.requestGET("pet", "");
    }

    @Then("I should get Bad Request Error")
    public void iShouldGetBadRequestError() {
        verifyExpectedStatusCode(HttpStatus.SC_BAD_REQUEST,getResponse.getStatusCode());
    }
}
