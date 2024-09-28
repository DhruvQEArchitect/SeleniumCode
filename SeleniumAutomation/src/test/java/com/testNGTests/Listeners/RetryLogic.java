package com.testNGTests.Listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;


/**
 * This implements IRetryAnalyzer which is used to run the tests
 * based on a retry logic before failing the test
 */
public class RetryLogic implements IRetryAnalyzer {

    int retryCount = 0;

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < 3) {
            retryCount++;
            return true;
        }
        return false;
    }
}
