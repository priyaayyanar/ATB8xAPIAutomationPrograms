package com.thetestingacademy.sampleCheck;

public class APITest004_BuilderDesignPattern {
    public APITest004_BuilderDesignPattern step1(){
        System.out.println("Step 1 started");
        System.out.println("Step 1 done");
        return this;
    }
    public APITest004_BuilderDesignPattern step2(){
        System.out.println("Step 2 started");
        System.out.println("Step 2 done");
        return this;
    }
    public APITest004_BuilderDesignPattern step3(String param1){
        System.out.println("Step 3 started");
        System.out.println("Step 3 done "+ param1);
        return this;
    }

    public static void main(String[] args) {
        APITest004_BuilderDesignPattern bp = new APITest004_BuilderDesignPattern();
        bp.step1().step2().step3("Priya");
    }
}
