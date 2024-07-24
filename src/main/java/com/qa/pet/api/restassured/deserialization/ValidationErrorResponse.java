package com.qa.pet.api.restassured.deserialization;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ValidationErrorResponse {

    @JsonProperty("statusCode")
    Integer statusCode;

    @JsonProperty("code")
    String code;

    @JsonProperty("error")
    String error;

    @JsonProperty("message")
    String message;

}
