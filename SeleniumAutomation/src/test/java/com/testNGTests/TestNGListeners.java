package com.testNGTests;

import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNGListeners implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test failed: " + result.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test start: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test success: " + result.getName());
    }
    
}
