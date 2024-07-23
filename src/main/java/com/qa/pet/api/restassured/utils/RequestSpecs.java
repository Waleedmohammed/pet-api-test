package com.qa.pet.api.restassured.utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import static com.qa.pet.api.restassured.factory.ConfigFactory.getDefaultConfig;

public class RequestSpecs {

    public static RequestSpecification getDefaultRequestSpec(String baseUrl){
        return new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .addHeader("Content-Type","application/json")
                .setConfig(getDefaultConfig())
                .build();
    }

}
