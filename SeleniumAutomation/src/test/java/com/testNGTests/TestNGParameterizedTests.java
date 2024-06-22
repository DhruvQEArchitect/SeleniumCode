package com.testNGTests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * this class shows how to use data provider in testng tests and how can we use parameters in testng tests
 */

public class TestNGParameterizedTests {

    @DataProvider(name = "userName")
    public Object[][] getUserName() {
        return new Object[][]{{"user1"}, {"user2"}};
    }

    @Test(dataProvider = "userName")
    public void test1(String userName) {
        System.out.println("Executing test with user: " + userName);
    }

    @Test(dataProvider = "values", dataProviderClass = DataProviderOutput.class)
    public void test2(String values) {
        System.out.println("Execute test with data from data provider class: " + values);
    }
}
