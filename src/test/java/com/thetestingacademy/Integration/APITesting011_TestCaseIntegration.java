package com.thetestingacademy.Integration;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class APITesting011_TestCaseIntegration {

    //Create a Token
    //Create a booking
    //Perform a PUT request
    //Verify that PUT is success by GET request
    //Delete the ID
    //Verify it doesn't exist by GET request

    RequestSpecification requestSpecification; //Instance Variable
    Response response; //Instance Variable
    ValidatableResponse validatableResponse;//Instance Variable

    //Creating Instance Variable to make reuse of them.
    String token;
    String bookingID;

    public String getToken(){
        String payload = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";

        requestSpecification = RestAssured.given();
            requestSpecification.baseUri("https://restful-booker.herokuapp.com");
            requestSpecification.basePath("/auth");
            requestSpecification.contentType(ContentType.JSON).log().all();
            requestSpecification.body(payload);

        //when - Response
        Response response = requestSpecification.when().post();

        //then - Validatable Response
        ValidatableResponse validatableResponse = response.then();

        //Extract the token
        token = response.jsonPath().getString("token");
        System.out.println(token);

        return token;
    }

    public String getBookingID(){
        String payload_POST = "{\n" +
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
            requestSpecification.baseUri("https://restful-booker.herokuapp.com");
            requestSpecification.basePath("/booking/");
            requestSpecification.contentType(ContentType.JSON);
            requestSpecification.body(payload_POST).log().all();

        Response response = requestSpecification.when().post();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        bookingID = response.jsonPath().getString("bookingid");
        System.out.println(bookingID);
        return bookingID;
    }


    @Test(priority = 1)
    public void test_update_request_put(){

        token = getToken();
        bookingID = getBookingID();
        System.out.println(token);
        System.out.println(bookingID);

        String payloadPUT= "{\n" +
                "    \"firstname\" : \"Pranu\",\n" +
                "    \"lastname\" : \"Shan\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : false,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2024-01-01\",\n" +
                "        \"checkout\" : \"2024-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Lunch\"\n" +
                "}";

        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com/");
        requestSpecification.basePath("/booking/"+bookingID);
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.cookie("token",token);
        requestSpecification.body(payloadPUT).log().all();

        response = requestSpecification.when().put();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

    }

    @Test(priority = 2)
    public void test_update_request_get(){
        System.out.println(bookingID);
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking/"+bookingID);
        response = requestSpecification.when().log().all().get();
        response.then().log().all().statusCode(200);

        String firstname = response.jsonPath().getString("firstname");
        Assert.assertEquals(firstname,"Pranu");
    }

    @Test(priority = 3)
    public void test_delete_booking(){
        System.out.println(bookingID);
        System.out.println(token);

        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com/");
        requestSpecification.basePath("/booking/"+bookingID);
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.cookie("token",token);

        response = requestSpecification.when().delete();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(201); // #TODO #1 -Delete Bug
    }

    @Test(priority = 4)
    public void test_after_delete_request_get(){
        requestSpecification.baseUri("https://restful-booker.herokuapp.com/");
        requestSpecification.basePath("/booking/"+bookingID);
        response = requestSpecification.when().log().all().get();
        requestSpecification.then().log().all().statusCode(404);
    }


}
