import com.utils.HandleDriversAutomatically;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class DynamicWebTables {

    /**
     * In this class we are going to see how to handle dynamic webtable in selenium
     */

    static WebDriver driver;
    static JavascriptExecutor javascriptExecutor;
    static HandleDriversAutomatically handleDriversAutomatically = new HandleDriversAutomatically();

    public static void main(String[] args) {
        String path = System.getProperty("user.dir");
        handleDriversAutomatically.downloadLatestChromeDriver();
        //This line is used to set chrome driver and path to its executable
        System.setProperty("webdriver.chrome.driver", path + "/Drivers/ChromeDriver/chromedriver-win64/chromedriver.exe");

        // This line is used to initiate chrome driver
        driver = new ChromeDriver();

        //This line is used to launch a site
        driver.get("https://demo.guru99.com/test/web-table-element.php");
        driver.manage().window().maximize();
        setZoomLevel(1.5);

        List<WebElement> rows = driver.findElements(By.xpath("//table[@class='dataTable']//tbody//tr"));
        for (WebElement eachRow : rows) {
            System.out.println(eachRow.getText());
        }

        driver.quit();
    }

    static void setZoomLevel(double zoomLevel) {
        javascriptExecutor = ((JavascriptExecutor) driver);
        javascriptExecutor.executeScript("document.body.style.zoom='" + zoomLevel + "'");
    }
}
