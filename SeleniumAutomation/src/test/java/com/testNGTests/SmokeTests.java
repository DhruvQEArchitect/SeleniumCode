package com.testNGTests;

import com.testNGTests.Listeners.RetryLogic;
import org.junit.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Smoke tests using TestNG annotations
 */
public class SmokeTests {

    @Test
    public void smokeTest1() {
        System.out.println("Executing smoke test 1");
    }

    @Test
    public void smokeTest2() {
        System.out.println("Executing smoke test 2");
    }

    @Test(groups = "shakedown")
    @Parameters({"environment"})
    public void shakedownTest(@Optional("default") String environment) {
        System.out.println("Executing shakedown test before smoke tests in environment: " + environment);
    }

    @Test(expectedExceptions = NullPointerException.class)
    public void expectionTest() {
        String str = null;
        System.out.println(str.length());
        System.out.println("Testing exception test");

    }

    /**
     * This test will not fail until the final counter
     * in the retry logic class is reached beyond its limit
     */
    @Test(retryAnalyzer = RetryLogic.class)
    public void retryTestCase() {
        Assert.assertEquals(true, false);
    }
}
