package com.qa.pet.api.cucumber.stepDefinitions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.pet.api.restassured.conf.ApiProperties;
import com.qa.pet.api.restassured.deserialization.GetPet;
import com.qa.pet.api.restassured.deserialization.PostPet;
import com.qa.pet.api.restassured.factory.RequestGenerator;
import com.qa.pet.api.restassured.helper.ApiTestHelpers;
import com.qa.pet.api.restassured.helper.common.TestHelpers;
import com.qa.pet.api.restassured.serialization.PetData;
import com.qa.pet.api.restassured.utils.RestMethods;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@Slf4j
@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CreatePetSteps {

    @Autowired
    ApiProperties apiProperties;

    public RestMethods restMethods;

    public ApiTestHelpers apiTestHelpers;

    Response postResponse;

    Response getResponse;


    PetData petData;

    PostPet postPet;

    @Before()
    public void setUp() {
        restMethods = new RestMethods(apiProperties);
        apiTestHelpers = new ApiTestHelpers(apiProperties);
    }

    @After
    public void teardown() {
    }

    @Given("New pet with name {string}, age {int} , avatarUrl {string} and category {string}")
    public void newPetWithNameNameAgeAgeAvatarUrlUrlAndCategoryCategory(String name, Integer age, String url, String category) {
        petData = RequestGenerator.PetDataRequest(name, age, url, category).build();
    }


    @When("adding the new pet to the store")
    public void addingTheNewPetToTheStore() throws JsonProcessingException {
        log.info("When I created Pet with Data\n {}", TestHelpers.formateJsonObject(petData));

        postResponse = restMethods.requestPOST(petData);

        postPet = postResponse.as(PostPet.class);
        log.info("I received response as\n {}", TestHelpers.formateJsonObject(postPet));
    }


    @Then("the pet is added successfully")
    public void thePetIsAdded() throws JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        Assertions.assertEquals(HttpStatus.SC_CREATED, postResponse.getStatusCode(), "Not matching HTTP status Code");

        getResponse = restMethods.requestGET("petId", postPet.getId());

        GetPet pet = getResponse.as(GetPet.class);
        log.info("Pet Created with Below Data\n {}", TestHelpers.formateJsonObject(pet));

        Assertions.assertEquals(mapper.readTree(TestHelpers.formateJsonObject(petData)), mapper.readTree(TestHelpers.formateJsonObject(pet)));

    }


    @Then("the pet can not be added")
    public void thePetCanNotBeAdded() {
        Assertions.assertEquals(HttpStatus.SC_BAD_REQUEST, postResponse.getStatusCode(), "Not matching HTTP status Code");
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
        petData = RequestGenerator.PetDataRequest(name, age, url, TestHelpers.getRandomAlphabetic(apiProperties.getMaxPetCategoryLength() + 1))
                .build();
    }

    @Then("the pet can not be added due to validation error")
    public void thePetCanNotBeAddedDueToValidationError() {
        Assertions.assertEquals(HttpStatus.SC_UNPROCESSABLE_ENTITY, postResponse.getStatusCode(), "Not matching HTTP status Code");
    }


    @And("pet API schema verified successfully")
    public void petAPISchemaVerifiedSuccessfully() {
    }
}
