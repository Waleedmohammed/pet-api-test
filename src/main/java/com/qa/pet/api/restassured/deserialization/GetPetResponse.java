package com.qa.pet.api.restassured.deserialization;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class GetPetResponse {

    @JsonProperty("name")
    String name;

    @JsonProperty("age")
    Integer age;

    @JsonProperty("avatarUrl")
    String avatarUrl;

    @JsonProperty("category")
    String category;

}

