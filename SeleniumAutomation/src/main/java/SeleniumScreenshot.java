import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

/**
 * In this class we will see how to capture screenshot in selenium
 */
public class SeleniumScreenshot {
    static WebDriver driver;

    public static void main(String[] args) throws Exception {
        String path = System.getProperty("user.dir");

        //This line is used to set chrome driver and path to its executable
        System.setProperty("webdriver.chrome.driver", path + "/Drivers/chromedriver.exe");

        // This line is used to initiate chrome driver
        driver = new ChromeDriver();

        //This line is used to launch a site
        driver.get("https://www.google.com");

        //This line is used to maximize browser
        driver.manage().window().maximize();

        //get screenshot of the full web page
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File(path + "/Screenshots/test.jpg"));

        //get screenshot of any specific element
        WebElement searchButton = driver.findElement(By.xpath("//div[@class=\"FPdoLc lJ9FBc\"]//input[@value=\"Google Search\"]"));
        File elementScreenshot = searchButton.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(elementScreenshot, new File(path + "/Screenshots/element.jpg"));
        driver.quit();
    }
}
