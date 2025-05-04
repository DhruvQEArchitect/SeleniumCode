import com.utils.HandleDriversAutomatically;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;

public class HandleSvgElements {

    static HandleDriversAutomatically handleDriversAutomatically = HandleDriversAutomatically.getInstance();
    static WebDriver driver;
    static WebElement svgElement;

    public static void main(String[] args) {
        handleDriversAutomatically.downloadLatestChromeDriver();
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/Drivers/ChromeDriver/chromedriver-win64/chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.w3schools.com/html/html5_svg.asp");
        svgElement = driver.findElement(By.xpath("(//*[local-name()='svg' and @fill = 'none'])[3]"));
        getElementScreenshot(svgElement, "svgElement");
        driver.quit();
    }

    static void getElementScreenshot(WebElement element, String screenshotName) {
        File screenshot = element.getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File(System.getProperty("user.dir") + "/Screenshots/" + screenshotName + ".png"));
        } catch (Exception ex) {
        }
    }
}