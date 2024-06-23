package com.pomTests;

import com.pages.FlipkartHomePage;
import com.pages.Pages;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PomTests {
    Pages pages = Pages.getInstance();
    WebDriver driver = pages.initiateDriver();
    FlipkartHomePage flipkartHomePage = FlipkartHomePage.getInstance(driver);

    @Before
    public void setup() throws Exception {
        pages.loadApplication();
    }


    @Test
    public void Verify_Flipkart_Home_Page() {
        flipkartHomePage.checkElementsOnHomePage();
        Assert.assertTrue(flipkartHomePage.verifyPageTitle().contains("Online"));
    }

    @After
    public void teardown() throws Exception {
        driver.quit();
        Runtime.getRuntime().exec("Taskkill.exe /F /IM chromedriver.exe");
    }
}
