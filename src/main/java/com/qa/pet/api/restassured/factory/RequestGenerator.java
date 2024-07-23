package com.qa.pet.api.restassured.factory;

import com.qa.pet.api.restassured.serialization.PetData;

public class RequestGenerator {

    public static PetData.PetDataBuilder PetDataRequest(String name, Integer age, String avatarUrl, String category) {
        return corePetDataRequest(avatarUrl, category)
                .name(name)
                .age(age);
    }

    public static PetData.PetDataBuilder PetDataRequestWithoutName(Integer age, String avatarUrl, String category) {
        return corePetDataRequest(avatarUrl, category)
                .age(age);
    }

    public static PetData.PetDataBuilder PetDataRequestWithoutAge(String name, String avatarUrl, String category) {
        return corePetDataRequest(avatarUrl, category)
                .name(name);
    }

    private static PetData.PetDataBuilder corePetDataRequest(String avatarUrl, String category) {
        return PetData.builder()
                .avatarUrl(avatarUrl)
                .category(category);
    }


}
