package com.qa.pet.api.tests.getPetTests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.qa.pet.api.restassured.deserialization.GetPet;
import com.qa.pet.api.restassured.deserialization.PostPet;
import com.qa.pet.api.restassured.factory.RequestGenerator;
import com.qa.pet.api.restassured.helper.common.TestHelpers;
import com.qa.pet.api.restassured.serialization.PetData;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


@Slf4j
public class PostPetTests extends TestBase {

    Response response;

    static PetData petData;


    @BeforeAll
    public static void createTestData() {
        petData = RequestGenerator.PetDataRequestWithoutAge("TestPetFW","TestAvatarFW","TestCategoryFW").build();
    }

    @Test()
    public void testCreateValidPetStatusCode200() throws JsonProcessingException {

        PetData petData = RequestGenerator.PetDataRequestWithoutName(20,"TestAvatarFW","TestCategoryFW").build();


        log.info("Request Body is {} ", TestHelpers.formateJsonObject(petData));

        response = restMethods.requestPOST(petData);

        PostPet createdPet = response.as(PostPet.class);
        log.info("Response Body is {} ", TestHelpers.formateJsonObject(createdPet));

        Assertions.assertEquals(HttpStatus.SC_CREATED, response.getStatusCode(),
                "The Create user API status code is incorrect for a valid request body= name: " + petData.getName() + " ,age: " + petData.getAge());

        response = restMethods.requestGET("petId", createdPet.getId());

        GetPet pet = response.as(GetPet.class);
        log.info("Response Body is {} ", TestHelpers.formateJsonObject(pet));
        Assertions.assertEquals("TestPetFW", pet.getName());
        Assertions.assertEquals(20, pet.getAge());
        Assertions.assertEquals("TestAvatarFW", pet.getAvatarUrl());
        Assertions.assertEquals("TestCategoryFW", pet.getCategory());

    }

}
