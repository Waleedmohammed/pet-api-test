package com.qa.pet.api.restassured.serialization;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PetData {

    private String name;
    private Integer age;
    private String avatarUrl;
    private String category;

}
