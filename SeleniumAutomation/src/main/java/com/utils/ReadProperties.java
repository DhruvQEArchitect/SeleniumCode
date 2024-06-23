package com.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class ReadProperties {
    Properties properties;
    String path = System.getProperty("user.dir");

    public static ReadProperties instance = null;

    public static ReadProperties getInstance() {
        if (instance == null)
            instance = new ReadProperties();
        return instance;
    }

    public String getPropValue(String key) throws Exception {
        properties = new Properties();
        properties.load(new FileInputStream(path + "/src/main/resources/Test.properties"));
        return properties.getProperty(key);
    }
}
