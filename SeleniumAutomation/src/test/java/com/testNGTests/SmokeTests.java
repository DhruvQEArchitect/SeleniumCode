package com.testNGTests;

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
}
