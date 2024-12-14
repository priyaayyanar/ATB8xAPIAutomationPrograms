package com.thetestingacademy.TestNGExamples;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class APITesting017_TestNG_Parameter {

    @Parameters("browser")
    @Test
    public void demo(String value){
        System.out.println("Browser is : "+value);
        //Open the browser and select dadada
        if (value.equalsIgnoreCase("chrome")) {
            System.out.println("Start my Testing");
        }
        if (value.equalsIgnoreCase("firefox")) {
            System.out.println("Start my Firefox");
        }
    }
}
