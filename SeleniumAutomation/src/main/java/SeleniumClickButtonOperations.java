import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumClickButtonOperations {

    static WebDriver driver;

    public static void main(String[] args) {
        String path = System.getProperty("user.dir");

        //This line is used to set chrome driver and path to its executable
        System.setProperty("webdriver.chrome.driver", path + "/Drivers/chromedriver.exe");

        // This line is used to initiate chrome driver
        driver = new ChromeDriver();

        //This line is used to launch a site
        driver.get("https://demoqa.com/buttons");

        //This line is used to maximize browser
        driver.manage().window().maximize();

        //To quit all instances opened by the driver during the run , we use driver.quit();
        driver.quit();
    }
}
