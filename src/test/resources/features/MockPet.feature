Feature: Handling network issues and server errors

  @MockTCs
  Scenario: Server returns 500 Internal Server Error for GET Request
    Given The server returns a 500 Internal Server Error for get request
    When I make a GET request
    Then I should receive a 500 Internal Server Error

  @MockTCs
  Scenario: Network timeout occurs for GET Request
    Given A network timeout occurs for GET Request
    When I make a GET request
    Then I should receive a timeout error

  @MockTCs
  Scenario: Server returns 500 Internal Server Error for POST Request
    Given The server returns a 500 Internal Server Error for post request
    When I make a POST request
    Then I should receive a 500 Internal Server Error

  @MockTCs
  Scenario: Server returns 500 Internal Server Error for PUT Request
    Given The server returns a 500 Internal Server Error for put request
    When I make a PUT request
    Then I should receive a 500 Internal Server Error


  @MockTCs
  Scenario: Server returns 500 Internal Server Error for DELETE Request
    Given The server returns a 500 Internal Server Error for delete request
    When I make a DELETE request
    Then I should receive a 500 Internal Server Error

