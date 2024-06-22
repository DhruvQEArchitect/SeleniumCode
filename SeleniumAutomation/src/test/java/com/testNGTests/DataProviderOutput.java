package com.testNGTests;

import org.testng.annotations.DataProvider;

public class DataProviderOutput {

    @DataProvider(name = "values")
    public Object[][] getvalues() {
        return new Object[][]{{"param1"}, {"param2"}};
    }
}
