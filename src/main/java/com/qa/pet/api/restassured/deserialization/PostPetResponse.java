package com.qa.pet.api.restassured.deserialization;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@NoArgsConstructor
public class PostPetResponse {

    @JsonProperty("id")
    Integer id;

}

