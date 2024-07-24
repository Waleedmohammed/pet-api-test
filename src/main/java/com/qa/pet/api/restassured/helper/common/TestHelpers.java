package com.qa.pet.api.restassured.helper.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.qa.pet.api.restassured.deserialization.PostPetResponse;
import org.apache.commons.lang3.RandomStringUtils;

public class TestHelpers {

    /**
     *
     * @param postPetResponse: get pet response deserialized object
     * @return: id of created pet
     */
    public static Integer getPetID(PostPetResponse postPetResponse){
        return postPetResponse.getId();
    }

    /**
     * Generate random numeric string with given length
     * Can be used for amount generation, so usage of '0' is restricted to avoid leading 0
     * <p/>
     *
     * @param length Length of generated string
     * @return Random string
     */
    public static String getRandomNumeric(Integer length) {
        return RandomStringUtils.random(length, "123456789");
    }

    public static String formateJsonObject(Object jsonObject) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        String bodyJsonString = mapper.writeValueAsString(jsonObject);
        Object formatedJsonObject = mapper.readValue(bodyJsonString, Object.class);
        return mapper.writeValueAsString(formatedJsonObject);
    }

    /**
     * Generate random alphabetic string with given length
     * <p/>
     *
     * @param length Length of generated string
     * @return Random string
     */
    public static String getRandomAlphabetic(int length) {
        return RandomStringUtils.randomAlphabetic(length);
    }
}
