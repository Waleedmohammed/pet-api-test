package com.qa.pet.api.restassured.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.qa.pet.api.restassured.conf.ApiProperties;
import com.qa.pet.api.restassured.serialization.PetData;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.qa.pet.api.restassured.utils.RequestSpecs.getDefaultRequestSpec;
import static com.qa.pet.api.restassured.utils.ResponseSpecs.getDefaultResponseSpec;

import static io.restassured.RestAssured.given;

@Slf4j
@Component
public class RestMethods {

    ApiProperties apiProperties;

    RequestSpecification requestSpecification;

    ResponseSpecification responseSpecification;

    public RestMethods(ApiProperties apiProperties) {
        this.apiProperties = apiProperties;
        requestSpecification = getDefaultRequestSpec(apiProperties.getBaseUrl());
        responseSpecification = getDefaultResponseSpec(201, ContentType.JSON);
    }

    /**
     * A generic function for the GET request
     *
     * @return response of the GET request
     */
    public Response requestGET() {
        return given().
                relaxedHTTPSValidation().
                spec(requestSpecification).
                when().get().then().
                extract().response();
    }

    /**
     * A generic function for the GET request
     *
     * @return response of the GET request
     */
    public Response requestGET(String path) {
        return given().
                relaxedHTTPSValidation().
                spec(requestSpecification).
                when().get(path).then()
                .extract().response();
    }

    /**
     * A generic function for the GET request with param
     *
     * @return response of the GET request
     */
    public Response requestGET(String pathParamKey, Integer pathParamValue) {
        return given().
                relaxedHTTPSValidation().
                spec(requestSpecification).
                pathParam(pathParamKey, pathParamValue).
                when().get("{" + pathParamKey + "}").then()
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("PetJsonSchema.json"))
                .extract().response().prettyPeek();
    }


    /**
     * A generic function for the POST request
     *
     * @return response of the POST request
     */
    public Response requestPOST(PetData requestBody) {
        return given().
                spec(requestSpecification).
                body(requestBody).
                relaxedHTTPSValidation().when().post().prettyPeek();
    }

    /**
     * A generic function for the PUT request
     *
     * @return response of the PUT request
     */
    public Response requestPUT(PetData requestBody, String path) {
        return given().
                spec(requestSpecification).
                body(requestBody).
                relaxedHTTPSValidation().when().put();
    }


    /**
     * A generic function for the PUT request
     *
     * @return response of the PUT request
     */
    public Response requestDELETE(String path) {
        return given().
                spec(requestSpecification).
                relaxedHTTPSValidation().when().delete(path);
    }
}
