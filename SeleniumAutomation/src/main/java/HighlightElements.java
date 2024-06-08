import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * In this class we will see how to highlight elements in selenium
 */
public class HighlightElements {

    static WebDriver driver;

    public static void main(String[] args) {
        String path = System.getProperty("user.dir");

        //This line is used to set chrome driver and path to its executable
        System.setProperty("webdriver.chrome.driver", path + "/Drivers/chromedriver.exe");

        // This line is used to initiate chrome driver
        driver = new ChromeDriver();

        //This line is used to launch a site
        driver.get("https://www.google.com");
        driver.manage().window().maximize();

        WebElement searchButton = driver.findElement(By.xpath("//div[@class=\"FPdoLc lJ9FBc\"]//input[@value=\"Google Search\"]"));

        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;

        javascriptExecutor.executeScript("arguments[0].setAttribute('style','background:yellow; border: 2px solid red;')", searchButton);

        driver.quit();
    }
}
