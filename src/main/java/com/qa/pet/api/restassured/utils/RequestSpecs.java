package com.qa.pet.api.restassured.utils;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

import static com.qa.pet.api.restassured.factory.ConfigFactory.getDefaultConfig;

public class RequestSpecs {

    public static RequestSpecification getDefaultRequestSpec(String baseUrl,String basePath){
        return new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .setBasePath(basePath)
                .addHeader("Content-Type","application/json")
                .setConfig(getDefaultConfig())
                .build();
    }

    public static RequestSpecification getRequestSpecWithoutConetentType(String baseUrl,String basePath){
        return new RequestSpecBuilder()
                .setBaseUri(baseUrl)
                .setBasePath(basePath)
                .noContentType()
                .setConfig(getDefaultConfig())
                .build();
    }

}
