package com.thetestingacademy.PayloadManagement_6Dec.DifficultWay;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class APITesting025_Payload_POJO_auto_byGSON {

    public static void main(String[] args) {
        Booking booking = new Booking();
        booking.setFirstname("Priya");
        booking.setLastname("Ayyanar");
        booking.setTotalprice(123);
        booking.setDepositepaid(true);

        BookingDates bookingDates = new BookingDates();
        bookingDates.setCheckin("2024-01-01");
        bookingDates.setCheckout("2025-01-01");

        booking.setBookingDates(bookingDates);

        booking.setAdditionalneeds("Breakfast");

        System.out.println(booking);

        RequestSpecification requestSpecification;
        ValidatableResponse validatableResponse;
        Response response;

        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com/");
        requestSpecification.basePath("booking");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(booking).log().all();

        response = requestSpecification.when().post();

        Integer bookingID = response.then().extract().path("bookingid");

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);
        System.out.println("Your Booking ID : "+ bookingID);

    }

//    private String firstname;
//    private String lastname;
//    private Integer totalprice;
//    private boolean depositepaid;
//
//    private String additionalneeds;




}
