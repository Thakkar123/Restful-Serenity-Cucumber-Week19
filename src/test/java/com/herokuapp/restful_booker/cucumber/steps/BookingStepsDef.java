package com.herokuapp.restful_booker.cucumber.steps;

import com.herokuapp.restful_booker.bookinginfo.Bookingsteps;
import com.herokuapp.restful_booker.utils.TestUtils;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;

import java.util.HashMap;

public class BookingStepsDef {
    static String username = "admin";
    static String password = "password123";
    static int id;
    static String token = "";
    static String firstname = "newname" + TestUtils.getRandomValue();
    static String lastname = "newlastname" + TestUtils.getRandomValue();
    static int totalprice = 1000;
    static Boolean depositpaid = true;
    static String additionalneeds = "Breakfast";


    static ValidatableResponse response;

    @Steps
    Bookingsteps bookingSteps;

    @Given("^User is on HomePage of Restfulbooker$")
    public void userIsOnHomePageOfRestfulbooker() {
    }

    @When("^User sends a GET request to booking  endpoint$")
    public void userSendsAGETRequestToBookingEndpoint() {
        response = bookingSteps.getAllBookingIds();
    }

    @Then("^User must get back a valid status code (\\d+)$")
    public void userMustGetBackAValidStatusCode(int arg0) {
        response.statusCode(200);
    }


    @When("^User sends a POST  request with username and password$")
    public void userSendsAPOSTRequestWithUsernameAndPassword() {
        bookingSteps.getAuthentication(username,password).log().all();

    }

    @Then("^User must get back a valid token as response$")
    public void userMustGetBackAValidTokenAsResponse() {
    }

    @When("^User sends a GET request to bookings  endpoint$")
    public void userSendsAGETRequestToBookingsEndpoint() {
        response = bookingSteps.getAllBookingIds();
    }

    @Then("^User must get back a valid status code as (\\d+)$")
    public void userMustGetBackAValidStatusCodeAs(int arg0) {
        response.statusCode(200);
    }

    @When("^User sends a POST request  using payload for booking$")
    public void userSendsAPOSTRequestUsingPayloadForBooking() {
        HashMap<Object, Object> bookingsDates = new HashMap<>();
        bookingsDates.put("checkin", "2018-01-10");
        bookingsDates.put("checkout", "2019-01-12");
       response = bookingSteps.createBooking(firstname, lastname, totalprice, depositpaid, bookingsDates, additionalneeds);
        response.log().all().statusCode(200);
        id = response.extract().path("bookingid");
        System.out.println(id);
    }

    @Then("^User must get back a valid status code (\\d+) as response$")
    public void userMustGetBackAValidStatusCodeAsResponse(int arg0) {
        response.statusCode(200);

    }

//    @When("^User sends a PUT request to booking  endpoint$")
//    public void userSendsAPUTRequestToBookingEndpoint() {
//        firstname = firstname+"updated";
//        lastname = lastname ="updated";
//        response = bookingSteps.updateBooking(id,firstname, lastname, totalprice, depositpaid,bookingDates,additionalneeds);
//        response.log().all().statusCode(200);
//    }

    @Then("^User must get back a valid status code (\\d+) and verify if booking is updated$")
    public void userMustGetBackAValidStatusCodeAndVerifyIfBookingIsUpdated(int arg0) {
    }

    @When("^User sends a delete request  using of booking$")
    public void userSendsADeleteRequestUsingOfBooking() {
        id=369;
        response = bookingSteps.deleteBooking(id, token);
        response.log().all().statusCode(201);
    }

    @Then("^User must get back a valid status code (\\d+) as response and verify if booking is deleted\\.$")
    public void userMustGetBackAValidStatusCodeAsResponseAndVerifyIfBookingIsDeleted(int arg0) {
        response = bookingSteps.getBookingById(id).log().all().statusCode(404);
    }
}
