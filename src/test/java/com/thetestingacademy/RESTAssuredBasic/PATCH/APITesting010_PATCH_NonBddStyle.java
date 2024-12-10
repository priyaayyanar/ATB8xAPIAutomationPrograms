package com.thetestingacademy.RESTAssuredBasic.PATCH;

import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class APITesting010_PATCH_NonBddStyle {

    RequestSpecification requestSpecification;

    @Description("Verify the PATCH Request for the RESTful Booker")
    @Test
    public void test_put_nonbdd(){

        /*Requirement
        Booking ID
        Token
        Payload
        content type
         */

        String token = "3ea9c66e4de30e7";
        String booking_id = "1197";
        String payloadPATCH = "{\n" +
                "        \"firstname\": \"Priya\",\n" +
                "        \"lastname\": \"Pranu\",\n" +
                "        \"totalprice\": 111,\n" +
                "        \"depositpaid\": true,\n" +
                "        \"bookingdates\": {\n" +
                "            \"checkin\": \"2018-01-01\",\n" +
                "            \"checkout\": \"2019-01-01\"\n" +
                "        },\n" +
                "        \"additionalneeds\": \"Breakfast\"\n" +
                "    }";

        RequestSpecification requestSpecification = RestAssured.given();

            requestSpecification.baseUri("https://restful-booker.herokuapp.com");
            requestSpecification.basePath("/booking/"+booking_id);
            requestSpecification.contentType(ContentType.JSON);
            requestSpecification.cookie("token",token);
           // requestSpecification.auth().preemptive().basic("admin","password123");
            requestSpecification.body(payloadPATCH).log().all();

        Response response = requestSpecification.when().patch();

        ValidatableResponse validatableResponse = response.then().log().all();

        validatableResponse.statusCode(200);
    }
}
