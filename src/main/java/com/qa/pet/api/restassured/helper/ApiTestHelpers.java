package com.qa.pet.api.restassured.helper;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.qa.pet.api.restassured.conf.ApiProperties;
import com.qa.pet.api.restassured.deserialization.GetPetResponse;
import com.qa.pet.api.restassured.deserialization.PostPetResponse;
import com.qa.pet.api.restassured.serialization.PetData;
import com.qa.pet.api.restassured.utils.RestMethods;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ApiTestHelpers {

    ApiProperties apiProperties;
    RestMethods restMethods;

    public ApiTestHelpers(ApiProperties apiProperties) {
        this.apiProperties = apiProperties;
        restMethods = new RestMethods(apiProperties, apiProperties.getBasePath());
    }

    public GetPetResponse getDeserializedPet(Integer petId) {
        Response response = restMethods.requestGET("petId", petId);
        return response.as(GetPetResponse.class);
    }

    public PostPetResponse getPetIdDeserialized(PetData petData) throws JsonProcessingException {
        Response response = restMethods.requestPOST(petData);
        return response.as(PostPetResponse.class);
    }

}
