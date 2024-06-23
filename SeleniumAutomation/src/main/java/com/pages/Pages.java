package com.pages;


import com.utils.ReadProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * This is the base class for all pages which contains all the basic elements operation like clicking/checking visibility
 * switching frames etc
 */
public class Pages {

    static WebDriver driver;
    static Pages instance = null;
    ReadProperties readProperties = ReadProperties.getInstance();

    public static Pages getInstance() {
        if (instance == null)
            instance = new Pages();
        return instance;
    }

    private Pages() {

    }

    public WebDriver initiateDriver() {
        String path = System.getProperty("user.dir");
        System.setProperty("webdriver.chrome.driver", path + "/Drivers/chromedriver.exe");
        driver = new ChromeDriver();
        return driver;
    }

    public void loadApplication() throws Exception {
        driver.get(readProperties.getPropValue("app.url"));
    }

    public boolean isElementVisible(WebElement element) {
        return element.isDisplayed();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public void clickOnElement(WebElement element) {
        element.click();
    }

    public void typeText(WebElement element, String text) {
        element.sendKeys(text);
    }

}
