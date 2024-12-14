package com.thetestingacademy.Assertions;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class APITesting021_Assertions {

    //ER == AR

    //HardAssertion

    @Test
    public void test_hardAssertion(){
        System.out.println("Start of the program.");
        Assert.assertTrue(false);
        System.out.println("End of the program");

        Assert.assertEquals("Priya","Priya"); //T
        Assert.assertEquals("Priya","priya"); //F
    }

    @Test
    public void test_softAssertion(){
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(false); //This will not stop execution
        System.out.println("This line will be executed");
        softAssert.assertAll(); //This will report all collected errors
    }
}
