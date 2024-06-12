package com.junitTests;

import org.junit.*;

/**
 * Creating sample sanity tests with different annotationss
 */
public class JunitSanityTests {

    @BeforeClass
    public static void beforeClass() {
        System.out.println("Execute once before all tests");
    }

    @Before
    public void beforeEachTest() {
        System.out.println("Execute before every test");
    }

    @Test
    public void sanityTest1() {
        System.out.println("Running sanity test 1");
    }

    @Test
    public void sanityTest2() {
        System.out.println("Running sanity test 2");
    }

    @After
    public void afterEachTest() {
        System.out.println("Execute after every test");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("Execute once after all tests");
    }

}
