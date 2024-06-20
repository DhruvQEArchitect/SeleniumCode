import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

/***
 * In this class we will see how to handle shadow root element in selenium
 */
public class HandleShadowRootElement {

    static WebDriver driver;

    public static void main(String[] args) {
        String path = System.getProperty("user.dir");

        //This line is used to set chrome driver and path to its executable
        System.setProperty("webdriver.chrome.driver", path + "/Drivers/chromedriver.exe");

        // This line is used to initiate chrome driver
        driver = new ChromeDriver();

        //This line is used to launch a site
        driver.get("chrome://history/");
        driver.manage().window().maximize();

        /**
         * First we will reach to the element from where shadow root element starts and then we will start locating the
         element by using css selectors, shadow root element works with css selectors **/

        driver.findElement(By.cssSelector("history-app")).getShadowRoot().findElement(By.cssSelector("#toolbar")).getShadowRoot()
                .findElement(By.cssSelector("#mainToolbar")).getShadowRoot().findElement(By.cssSelector("#search")).getShadowRoot()
                .findElement(By.cssSelector("#searchInput")).sendKeys("test");
        driver.quit();
    }
}
