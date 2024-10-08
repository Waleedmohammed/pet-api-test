package com.qa.pet.api.cucumber.stepDefinitions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.pet.api.restassured.conf.ApiProperties;
import com.qa.pet.api.restassured.deserialization.GetPetResponse;
import com.qa.pet.api.restassured.deserialization.PostPetResponse;
import com.qa.pet.api.restassured.helper.TestHelpers;
import com.qa.pet.api.restassured.serialization.PetData;
import com.qa.pet.api.restassured.utils.RestMethods;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
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
public class BaseSteps {

    @Autowired
    ApiProperties apiProperties;

    RestMethods restMethods;

    public Response postResponse;
    public Response getResponse;
    public Response putResponse;
    public Response deleteResponse;

    public PetData petData;

    public PostPetResponse postPet;


    protected void getPetAndValidateResponse() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        getResponse = restMethods.requestGETAndValidateSchema("petId", postPet.getId());

        if (getResponse.getStatusCode() <= HttpStatus.SC_CREATED) {
            GetPetResponse pet = getResponse.as(GetPetResponse.class);
            Assertions.assertAll(
                    () -> Assertions.assertEquals(mapper.readTree(TestHelpers.formateJsonObject(petData)), mapper.readTree(TestHelpers.formateJsonObject(pet))),
                    () -> Assertions.assertEquals(HttpStatus.SC_OK, getResponse.getStatusCode())
            );
        } else {
            throw new Exception("Get Pet not success");
        }
    }

    protected void verifyExpectedStatusCode(Integer expectedStatusCode, Integer actualStatusCode) {
        Assertions.assertEquals(expectedStatusCode, actualStatusCode, "Expected status code should be " + expectedStatusCode + " however it is " + actualStatusCode + " !... Please raise critical bug");
    }

    protected PostPetResponse executePostPet(Object requestBody) {

        postResponse = restMethods.requestPOST(requestBody);

        if (postResponse.getStatusCode() <= HttpStatus.SC_CREATED) {
            return postResponse.as(PostPetResponse.class);
        } else {
            return null;
        }
    }
}
