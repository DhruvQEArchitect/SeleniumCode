import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

        //We use WebElement to locate elements on a website to perform operations on it
        WebElement clickMeButton = driver.findElement(By.xpath("//*[text()=\"Click Me\"]"));

        scrollIntoElement(clickMeButton);
        clickMeButton.click();

        /**
         * getText() method is used to get text from the webpage element which we can locate by writing xpath
         */
        String verifyText = driver.findElement(By.xpath("//*[text()=\"You have done a dynamic click\"]")).getText();
        if (verifyText.equals("You have done a dynamic click")) {
            System.out.println("Test case passed");
        } else {
            System.out.println("Test case failed");
        }

        //To quit all instances opened by the driver during the run , we use driver.quit();
        driver.quit();
    }

    static void scrollIntoElement(WebElement element) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }
}
