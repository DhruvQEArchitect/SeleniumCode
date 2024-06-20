package com.junitTests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class JunitParamTest {

    SumClass sumClass;
    int firstNumber, secondNumber, expectedResult;

    public JunitParamTest(int firstNumber, int secondNumber, int expectedResult) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
        this.expectedResult = expectedResult;
    }

    @Before
    public void before() {
        sumClass = new SumClass();
    }

    @Parameterized.Parameters
    public static Collection asInput() {
        return Arrays.asList(new Object[][]{{1, 2, 3}, {11, 22, 33}, {111, 222, 333}});
    }

    @Test
    public void test() {
        Assert.assertEquals(sumClass.getSum(firstNumber, secondNumber), expectedResult);
    }

}
