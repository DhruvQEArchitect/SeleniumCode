package com.testNGTests;

import com.testNGTests.Listeners.TestNGListeners;
import org.testng.annotations.Ignore;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * Sanity tests using TestNG annotations
 */
@Listeners(TestNGListeners.class)
public class SanityTests {

    @Test(priority = 0, dependsOnGroups = "shakedown", description = "Sanity test case 1", testName = "First sanity verification test")
    public void sanityTest1() {
        System.out.println("Executing sanity test 1");
    }

    @Test(priority = 2)
    public void sanityTest2() {
        System.out.println("Executing sanity test 2");
    }

    @Test(priority = 1, enabled = false)
    public void sanityTest3() {
        System.out.println("Executing sanity test 3");
    }

    @Ignore
    @Test(priority = 3)
    public void sanityTest4() {
        System.out.println("Executing sanity test 4");
    }

    @Test(groups = "shakedown")
    public void shakedownTest() {
        System.out.println("Execute shakedown tests after code deployment");
    }
}
