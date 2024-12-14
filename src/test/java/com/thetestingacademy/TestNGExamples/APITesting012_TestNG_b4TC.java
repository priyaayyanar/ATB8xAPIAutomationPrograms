package com.thetestingacademy.TestNGExamples;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class APITesting012_TestNG_b4TC {

    @BeforeTest
    public void get_1Token(){
        System.out.println("1");
    }

    @BeforeTest
    public void get_2BookingID(){
        System.out.println("2");
    }

    @Test
    public void test_PUT(){
        System.out.println("3");
    }

    @AfterTest
    public void closeAllThings(){
        System.out.println("Close");
    }
}
