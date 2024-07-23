package com.qa.pet.api.restassured.deserialization;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetPet {
    public GetPet(String name, Integer age, String avatarUrl, String category) {
        this.name = name;
        this.age = age;
        this.avatarUrl = avatarUrl;
        this.category = category;
    }

    @JsonProperty("name")
    private String name;

    @JsonProperty("age")
    private Integer age;

    @JsonProperty("avatarUrl")
    private String avatarUrl;

    @JsonProperty("category")
    private String category;

}

