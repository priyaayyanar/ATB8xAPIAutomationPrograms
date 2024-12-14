package com.thetestingacademy.Assertions;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.hamcrest.Matchers;
import static org.assertj.core.api.Assertions.*;


public class APITesting022_Assertions_RealTimeEx {

    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;
    String token;
    Integer bookingID;

    @Test
    public void test_POST() {
        String payload_POST = "{\n" +
                "    \"firstname\" : \"Priya\",\n" +
                "    \"lastname\" : \"Ayyanar\",\n" +
                "    \"totalprice\" : 100,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2025-01-01\",\n" +
                "        \"checkout\" : \"2026-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Breakfast\"\n" +
                "}";
        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(payload_POST).log().all();

        response = requestSpecification.when().post();
        //Get ValidatableResponse to perform response validation
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        //ValidatableResponse - Interface - Hamcrest RestAssured
        //RestAssured Default - Hamcrest
        //import org.hamcrest.Matchers;
        validatableResponse.body("booking.firstname", Matchers.equalTo("Priya"));
        validatableResponse.body("booking.lastname", Matchers.equalTo("Ayyanar"));
        validatableResponse.body("booking.depositpaid", Matchers.equalTo(true));
        validatableResponse.body("bookingid", Matchers.notNullValue());

        //TestNG Assertion - SoftAssertion vs Hard Assertion

        bookingID = response.then().extract().path("bookingid");
        String firstname = response.then().extract().path("booking.firstname");
        String lastname = response.then().extract().path("booking.lastname");

        Assert.assertNotNull(bookingID);
        Assert.assertEquals(firstname, "Priya");
        Assert.assertEquals(lastname, "Ayyanar");

        //AssertJ - 3rd party Library for Assertion - Widely used nowadays.

        assertThat(bookingID).isNotNull().isPositive().isNotZero();
        assertThat(firstname).isEqualTo("Priya").isNotNull().isNotEmpty().isNotBlank().isAlphanumeric();
    }
}