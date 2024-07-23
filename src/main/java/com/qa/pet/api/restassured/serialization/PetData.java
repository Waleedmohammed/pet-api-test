package com.qa.pet.api.restassured.serialization;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class PetData {

    private String name;
    private Integer age;
    private String avatarUrl;
    private String category;

}
