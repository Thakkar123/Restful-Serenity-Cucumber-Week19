Feature: Testing restfulbooker website with different methods


  @SMOKE
  Scenario: Check if User can get the authentication
    Given User is on HomePage of Restfulbooker
    When User sends a POST  request with username and password
    Then User must get back a valid token as response

  @SMOKE
  Scenario: Check if User can get all booking ids
    Given User is on HomePage of Restfulbooker
    When User sends a GET request to bookings  endpoint
    Then User must get back a valid status code 200

  @SMOKE
  Scenario: Check if User can get a specific booking id
    Given User is on HomePage of Restfulbooker
    When User sends a GET request to booking  endpoint
    Then User must get back a valid status code as 200

  @SMOKE
  Scenario: Check if User can create a booking

    When User sends a POST request  using payload for booking
    Then User must get back a valid status code 201 as response

  @SMOKE
  Scenario: Check if User can update the booking
    Given User is on HomePage of Restfulbooker
    When User sends a PUT request to booking  endpoint
    Then User must get back a valid status code 200 and verify if booking is updated

  @SMOKE
  Scenario: Check if User can delete a booking

    When User sends a delete request  using of booking
    Then User must get back a valid status code 200 as response and verify if booking is deleted.


