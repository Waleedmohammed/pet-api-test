package com.qa.pet.api.restassured.factory;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.config.*;
import io.restassured.path.json.mapper.factory.Jackson2ObjectMapperFactory;


public class ConfigFactory {
    public static RestAssuredConfig getDefaultConfig() {

        return RestAssured.config()
                .redirect(RedirectConfig.redirectConfig().maxRedirects(1))
                .objectMapperConfig(ObjectMapperConfig.objectMapperConfig().jackson2ObjectMapperFactory(getDefaultMapper()));
    }


    private static Jackson2ObjectMapperFactory getDefaultMapper() {
        return (type, s) -> {
            ObjectMapper om = new ObjectMapper();
            om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return om;
        };
    }
}
