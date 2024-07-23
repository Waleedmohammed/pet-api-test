package com.qa.pet.api.tests.getPetTests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.qa.pet.api.PetApiTestApplication;
import com.qa.pet.api.restassured.conf.ApiProperties;
import com.qa.pet.api.restassured.helper.ApiTestHelpers;
import com.qa.pet.api.restassured.utils.RestMethods;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Method;

@Slf4j
@SpringBootTest(classes = PetApiTestApplication.class)
public class TestBase {

    @Autowired
    ApiProperties apiProperties;

    public RestMethods restMethods;

    public ApiTestHelpers apiTestHelpers;

    @BeforeEach
    public void setupReport() {

        restMethods = new RestMethods(apiProperties);

        apiTestHelpers = new ApiTestHelpers(apiProperties);

    }

}
