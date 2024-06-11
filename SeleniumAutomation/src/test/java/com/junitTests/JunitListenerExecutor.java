package com.junitTests;

import org.junit.runner.JUnitCore;

/**
 * This class executes a junit test suite with listeners attached to it for reporting purposes
 */
public class JunitListenerExecutor {
    public static void main(String[] args) {
        JUnitCore jUnitCore = new JUnitCore();
        jUnitCore.addListener(new JunitListeners());
        jUnitCore.run(JunitTestSuite.class);
    }
}
