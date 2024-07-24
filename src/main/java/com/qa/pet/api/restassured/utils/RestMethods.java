package com.qa.pet.api.restassured.utils;


import com.qa.pet.api.restassured.conf.ApiProperties;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;


import static com.qa.pet.api.restassured.utils.RequestSpecs.*;
import static com.qa.pet.api.restassured.utils.ResponseSpecs.getDefaultResponseSpec;

import static io.restassured.RestAssured.given;


public class RestMethods {

    ApiProperties apiProperties;

    RequestSpecification requestSpecification;

    ResponseSpecification responseSpecification;

    public RestMethods(ApiProperties apiProperties, String basPath) {
        this.apiProperties = apiProperties;
        requestSpecification = getDefaultRequestSpec(apiProperties.getBaseUrl(), basPath);
        responseSpecification = getDefaultResponseSpec(ContentType.JSON);
    }


    /**
     * A generic function for the GET request with Integer param
     *
     * @return response of the GET request
     */
    public Response requestGET(String pathParamKey, Integer pathParamValue) {
        return given().log().all().
                relaxedHTTPSValidation().
                spec(requestSpecification).
                pathParam(pathParamKey, pathParamValue).
                when().get("{" + pathParamKey + "}").then().
                spec(responseSpecification).
                extract().response().prettyPeek();
    }


    /**
     * A generic function for the GET request with String param
     * Used for GET negative test cases
     *
     * @return response of the GET request
     */
    public Response requestGET(String pathParamKey, String pathParamValue) {
        return given().log().all().
                relaxedHTTPSValidation().
                spec(requestSpecification).
                pathParam(pathParamKey, pathParamValue).
                when().get("{" + pathParamKey + "}").then().
                spec(responseSpecification).
                extract().response().prettyPeek();
    }

    /**
     * A generic function for the GET request with param
     * Should be used for Get test cases with validating API schema
     *
     * @return response of the GET request
     */
    public Response requestGETAndValidateSchema(String pathParamKey, Integer pathParamValue) {
        return given().log().all().
                relaxedHTTPSValidation().
                spec(requestSpecification).
                pathParam(pathParamKey, pathParamValue).
                when().get("{" + pathParamKey + "}").then().
                spec(responseSpecification).
                body(JsonSchemaValidator.matchesJsonSchemaInClasspath("PetJsonSchema.json")).
                extract().response().prettyPeek();
    }


    /**
     * A generic function for the POST request
     *
     * @return response of the POST request
     */
    public Response requestPOST(Object requestBody) {
        return given().log().all().
                spec(requestSpecification).
                body(requestBody).
                relaxedHTTPSValidation().when().post().then().
                spec(responseSpecification).
                extract().response().prettyPeek();
    }

    /**
     * A generic function for the POST request
     * Used for Updating existing Pet giving Path ParamKey/value
     *
     * @return response of the POST request
     */
    public Response requestPOST(Object requestBody, String pathParamKey, Integer pathParamValue) {
        return given().log().all().
                spec(requestSpecification).
                pathParam(pathParamKey, pathParamValue).
                body(requestBody).
                relaxedHTTPSValidation().when().post("{" + pathParamKey + "}").then().
                spec(responseSpecification).
                extract().response().prettyPeek();
    }

    /**
     * A generic function for the POST request
     * Used for negative test cases for Updating existing Pet giving Path ParamKey/value
     *
     * @return response of the POST request
     */
    public Response requestPOST(Object requestBody, String pathParamKey, String pathParamValue) {
        return given().log().all().
                spec(requestSpecification).
                pathParam(pathParamKey, pathParamValue).
                body(requestBody).
                relaxedHTTPSValidation().when().post("{" + pathParamKey + "}").then().
                spec(responseSpecification).
                extract().response().prettyPeek();
    }

    /**
     * A generic function for the POST request
     * Meant to be used for deleting existing pet giving Path ParamKey/Value
     *
     * @return response of the POST request
     */
    public Response requestRemove(String basePath, String pathParamKey, Integer pathParamValue) {
        requestSpecification = getRequestSpecWithoutConetentType(apiProperties.getBaseUrl(), basePath);
        return given().log().all().
                spec(requestSpecification).
                pathParam(pathParamKey, pathParamValue).
                relaxedHTTPSValidation().when().post("{" + pathParamKey + "}/remove").then().
                extract().response().prettyPeek();
    }

    /**
     * A generic function for the POST request
     * Meant to be used for negative test cases for deleting existing pet giving Path ParamKey/Value
     *
     * @return response of the POST request
     */
    public Response requestRemove(String basePath, String pathParamKey, String pathParamValue) {
        requestSpecification = getRequestSpecWithoutConetentType(apiProperties.getBaseUrl(), basePath);
        return given().log().all().
                spec(requestSpecification).
                pathParam(pathParamKey, pathParamValue).
                relaxedHTTPSValidation().when().post("{" + pathParamKey + "}/remove").then().
                extract().response().prettyPeek();
    }

    /**
     * A generic function for the PUT request
     *
     * @return response of the PUT request
     */
    public Response requestPUT(Object requestBody, String pathParamKey, Integer pathParamValue) {
        return given().log().all().
                spec(requestSpecification).
                pathParam(pathParamKey, pathParamValue).
                body(requestBody).
                relaxedHTTPSValidation().when().put("{" + pathParamKey + "}").then().
                extract().response().prettyPeek();
    }

    /**
     * A generic function for the PUT request
     * Should be used for PUT negative test cases
     *
     * @return response of the PUT request
     */
    public Response requestPUT(Object requestBody, String pathParamKey, String pathParamValue) {
        return given().log().all().
                spec(requestSpecification).
                pathParam(pathParamKey, pathParamValue).
                body(requestBody).
                relaxedHTTPSValidation().when().put("{" + pathParamKey + "}").then().
                extract().response().prettyPeek();
    }


    /**
     * A generic function for the DELETE request
     *
     * @return response of the DELETE request
     */
    public Response requestDELETE(Object requestBody, String pathParamKey, Integer pathParamValue) {
        return given().log().all().
                spec(requestSpecification).
                pathParam(pathParamKey, pathParamValue).
                body(requestBody).
                relaxedHTTPSValidation().when().delete("{" + pathParamKey + "}").then().
                extract().response().prettyPeek();
    }
}
