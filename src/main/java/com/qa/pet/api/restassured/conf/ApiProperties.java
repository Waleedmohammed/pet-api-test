package com.qa.pet.api.restassured.conf;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "test.api")
public class ApiProperties {

    private String baseUrl;
    private Integer maxPetCategoryLength;

}
