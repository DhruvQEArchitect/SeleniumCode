import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class SeleniumWaits {
    /**
     * in this class we will see types of waits available in selenium
     */
    static WebDriver driver;

    public static void main(String[] args) throws InterruptedException {

        String path = System.getProperty("user.dir");

        //This line is used to set chrome driver and path to its executable
        System.setProperty("webdriver.chrome.driver", path + "/Drivers/chromedriver.exe");

        // This line is used to initiate chrome driver
        driver = new ChromeDriver();

        //This line is used to launch a site
        driver.get("https://demoqa.com/alerts");

        driver.manage().window().maximize();

        WebElement timerButton = driver.findElement(By.xpath("//button[@id=\"timerAlertButton\"]"));
        SeleniumClickButtonOperations.scrollIntoElement(timerButton, driver);
        timerButton.click();

        //Fluent wait, with polling of 1 sec
        Wait wait = new FluentWait(driver).withTimeout(Duration.ofSeconds(20)).
                pollingEvery(Duration.ofSeconds(1)).ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.titleIs("DEMOQA"));

        //Explicit wait, waiting for the alert to be visible
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        webDriverWait.until(ExpectedConditions.alertIsPresent());

        driver.switchTo().alert();
        //implicit wait
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


        driver.quit();
    }
}
