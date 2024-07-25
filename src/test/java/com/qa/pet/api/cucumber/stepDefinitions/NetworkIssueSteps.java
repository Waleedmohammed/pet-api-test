package com.qa.pet.api.cucumber.stepDefinitions;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@WireMockTest
public class NetworkIssueSteps extends BaseSteps {

    private WireMockServer wireMockServer;
    private Response response;

    @Before("@MockTCs")
    public void setup() {
        wireMockServer = new WireMockServer(WireMockConfiguration.wireMockConfig().port(apiProperties.getMockPort()));
        wireMockServer.start();
        WireMock.configureFor(apiProperties.getMockHost(), apiProperties.getMockPort());
    }

    @After("@MockTCs")
    public void teardown() {
        wireMockServer.stop();
    }

    @Given("The server returns a {int} Internal Server Error for get request")
    public void theServerReturnsAInternalServerErrorForGetRequest(int statusCode) {
        stubFor(get(urlEqualTo("/api/pet/2000"))
                .willReturn(aResponse()
                        .withStatus(statusCode)
                        .withBody("Internal Server Error")));
    }

    @Given("The server returns a {int} Internal Server Error for post request")
    public void theServerReturnsAInternalServerErrorForPostRequest(int statusCode) {
        stubFor(post(urlEqualTo("/api/pet/2000"))
                .willReturn(aResponse()
                        .withStatus(statusCode)
                        .withBody("Internal Server Error")));
    }

    @When("I make a GET request")
    public void theClientMakesAGETRequestTo() {
        response = RestAssured.get("http://" + apiProperties.getMockHost() + ":" + apiProperties.getMockPort() + "/api/pet/2000");

    }

    @Then("I should receive a {int} Internal Server Error")
    public void theClientShouldReceiveAInternalServerError(int statusCode) {
        log.info("Received Status code = {}",response.getStatusCode());
        assertEquals(statusCode, response.getStatusCode());
    }

    @Given("A network timeout occurs for GET Request")
    public void aNetworkTimeoutOccursForGETRequest() {
        stubFor(get(urlEqualTo("/api/pet/2000"))
                .willReturn(aResponse()
                        .withFixedDelay(7000))); // Simulate delay to cause timeout
    }

    @Then("I should receive a timeout error")
    public void theClientShouldReceiveATimeoutError() {
        // Assuming timeout is set to 2 seconds for RestAssured
        RestAssured.config = RestAssured.config().httpClient(HttpClientConfig.httpClientConfig().setParam("http.socket.timeout", 4000));
        assertEquals(0, response.getStatusCode()); // 0 indicates no response due to timeout
    }

    @When("I make a POST request")
    public void iMakeAPOSTRequest() {
        response = RestAssured.post("http://" + apiProperties.getMockHost() + ":" + apiProperties.getMockPort() + "/api/pet/2000");
    }

    @Given("The server returns a {int} Internal Server Error for put request")
    public void theServerReturnsAInternalServerErrorForPutRequest(int statusCode) {
        stubFor(put(urlEqualTo("/api/pet/2000"))
                .willReturn(aResponse()
                        .withStatus(statusCode)
                        .withBody("Internal Server Error")));
    }

    @When("I make a PUT request")
    public void iMakeAPUTRequest() {
        response = RestAssured.put("http://" + apiProperties.getMockHost() + ":" + apiProperties.getMockPort() + "/api/pet/2000");
    }

    @Given("The server returns a {int} Internal Server Error for delete request")
    public void theServerReturnsAInternalServerErrorForDeleteRequest(int statusCode) {
        stubFor(delete(urlEqualTo("/api/pet/2000"))
                .willReturn(aResponse()
                        .withStatus(statusCode)
                        .withBody("Internal Server Error")));
    }

    @When("I make a DELETE request")
    public void iMakeADELETERequest() {
        response = RestAssured.delete("http://" + apiProperties.getMockHost() + ":" + apiProperties.getMockPort() + "/api/pet/2000");
    }
}
