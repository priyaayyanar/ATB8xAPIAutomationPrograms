package com.thetestingacademy.PayloadManagement_6Dec.Ser_DeSer;

import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.*;

public class APITesting027_Demo {

    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;

    @Test
    public void test_Positive(){

        // Step1 - POST
        // URL -> Base URI + base Path
        // HEADER
        // BODY
        // Auth - NO


        // Step 2
        // prepare the Payload ( Object -> JSON String)
        // send the request

        //Step 3
        // Validate Response ( JSON String -> Object)
        // FirstName,
        // Status Code
        // Time Response

        Booking booking = new Booking();
        booking.setFirstname("Priya");
        booking.setLastname("Ayyanar");
        booking.setTotalprice(123);
        booking.setDepositpaid(true);

        Bookingdates bookingdates = new Bookingdates();
        bookingdates.setCheckin("2024-02-01");
        bookingdates.setCheckout("2025-02-01");
        booking.setBookingdates(bookingdates);
        booking.setAdditionalneeds("Breakfast");

        System.out.println(booking);

        //Object -> JSONString  =  Serialization

        Gson gson = new Gson();
        String jsonStringBooking = gson.toJson(booking);
        System.out.println(jsonStringBooking);

        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com/");
        requestSpecification.basePath("booking");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(jsonStringBooking).log().all();

        response = requestSpecification.when().post();
        String jsonResponseString = response.asString();

        //Integer bookingID = response.then().extract().path("bookingid");

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);
        //System.out.println("Your Booking ID : "+ bookingID);

        /*
        -> When Response is very small -
        To verify the Response in 2 ways
        1. extract()
        2. jsonPath().getString()

        -> When Response is very Huge / Complex JSON - then we have to convert them to object
        */

        // String -> Object = DeSerialization

        // To perform DeSer -> we have to create a Response Class (BookingResponse.java) -> Created with the help of jsonschema2pojos.org

        BookingResponse bookingResponse = gson.fromJson(jsonResponseString, BookingResponse.class);

        assertThat(bookingResponse.getBookingid()).isNotZero().isPositive().isNotNull();
        assertThat(bookingResponse.getBooking().getFirstname()).isNotEmpty().isNotBlank().isAlphanumeric().isNotNull().isEqualTo("Priya");
        assertThat(bookingResponse.getBooking().getLastname()).isNotEmpty().isNotBlank().isAlphanumeric().isNotNull().isEqualTo("Ayyanar");
        assertThat(bookingResponse.getBooking().getTotalprice()).isNotNull().isPositive().isNotZero();
        assertThat(bookingResponse.getBooking().getDepositpaid()).isEqualTo(true);
        assertThat(bookingResponse.getBooking().getBookingdates().getCheckin()).isNotNull().isNotEmpty();
        assertThat(bookingResponse.getBooking().getBookingdates().getCheckout()).isNotEmpty().isNotNull();
        assertThat(bookingResponse.getBooking().getAdditionalneeds()).isAlphanumeric().isNotNull().isNotEmpty();

    }
}
