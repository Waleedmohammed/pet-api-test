package com.qa.pet.api.restassured.deserialization;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class PostPetResponse {

    @JsonProperty("id")
    Integer id;

}

