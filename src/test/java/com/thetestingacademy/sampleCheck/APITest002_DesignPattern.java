package com.thetestingacademy.sampleCheck;
import io.restassured.RestAssured;

public class APITest002_DesignPattern {
    public static void main(String[] args) {
        System.out.println("API Testing");

        RestAssured
                .given()
                    .baseUri("https://restful-booker.herokuapp.com")
                    .basePath("/booking/1")
                .when()
                    .get()
                .then()
                    .log().all()
                    .statusCode(200);
    }
}
