import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class DynamicWebTables {

    /**
     * In this class we are going to see how to handle dynamic webtable in selenium
     */

    static WebDriver driver;

    public static void main(String[] args) {
        String path = System.getProperty("user.dir");

        //This line is used to set chrome driver and path to its executable
        System.setProperty("webdriver.chrome.driver", path + "/Drivers/chromedriver.exe");

        // This line is used to initiate chrome driver
        driver = new ChromeDriver();

        //This line is used to launch a site
        driver.get("https://demo.guru99.com/test/web-table-element.php");
        driver.manage().window().maximize();

        List<WebElement> rows = driver.findElements(By.xpath("//table[@class='dataTable']//tbody//tr"));
        for (WebElement eachRow : rows) {
            System.out.println(eachRow.getText());
        }

        driver.quit();
    }
}
