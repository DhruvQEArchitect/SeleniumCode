package com.junitTests;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * This class shows how we can implement a junit runner class to trigger a junit test suite
 */
public class JunitRunnerClass {

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(JunitTestSuite.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.getMessage());
        }
        System.out.println(result.wasSuccessful());
    }
}
