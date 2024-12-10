package com.thetestingacademy.RESTAssuredBasic.GET;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class APITesting_006_NonBDDStyle {


    @Test
    public void test_NonBDDStyleGET_Positive(){
        RequestSpecification r = RestAssured.given();
        r.baseUri("https://api.zippopotam.us/");
        r.basePath("/IN/388620");
        r.when().log().all().get();
        r.then().log().all().statusCode(200);
    }

    @Test
    public void test_NonBDDStyleGET_Negative(){
        RequestSpecification r = RestAssured.given();
        r.baseUri("https://api.zippopotam.us/");
        r.basePath("/IN/-1");
        r.when().log().all().get();
        r.then().log().all().statusCode(404);
    }
}
