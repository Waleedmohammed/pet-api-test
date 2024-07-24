package com.qa.pet.api.restassured.utils;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;


public class ResponseSpecs {

    public static ResponseSpecification getDefaultResponseSpec(ContentType expectedContedType) {
        return new ResponseSpecBuilder()
                .expectContentType(expectedContedType)
                .build();
    }
}
