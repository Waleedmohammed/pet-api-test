package com.qa.pet.api.restassured.helper.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.qa.pet.api.restassured.deserialization.PostPet;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.List;
import java.util.Random;

public class TestHelpers {

    /**
     *
     * @param postPetResponse: get pet response deserialized object
     * @return: id of created pet
     */
    public static Integer getPetID(PostPet postPetResponse){
        return postPetResponse.getId();
    }

    /**
     *
     * @param min
     * @param max
     * @param userIDs: the list of userIDs returned from the get all users API
     * @param fromTheList: a flag to indicate weather the calling function wants the rand number to be excluded/included if it is present in the userIDs list
     * @return: a random number between min & max
     */
    public static String getRandomNumber(int min, int max, List<String> userIDs, boolean fromTheList){
        Random random = new Random();
        int randomNum;
        if(fromTheList){
            // keep generating random numbers as long as the generated number is not in userIDs list to get a random number from the list
            do {
                randomNum = random.nextInt(max + min) + min;
            } while (!userIDs.contains(String.valueOf(randomNum)));
        }
        else {
            // keep generating random numbers as long as the generated number exists in userIDs list to get a new random number that does not exist in the list
            do {
                randomNum = random.nextInt(max + min) + min;
            } while (userIDs.contains(String.valueOf(randomNum)));
        }
        return String.valueOf(randomNum);
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
