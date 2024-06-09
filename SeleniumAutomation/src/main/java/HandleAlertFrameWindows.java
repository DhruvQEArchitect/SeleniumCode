import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class HandleAlertFrameWindows {
    /**
     * In this class we are going to see how to handle alerts, frames, multiple windows in selenium
     */

    static WebDriver driver;

    public static void main(String[] args) {

        String path = System.getProperty("user.dir");

        //This line is used to set chrome driver and path to its executable
        System.setProperty("webdriver.chrome.driver", path + "/Drivers/chromedriver.exe");

        // This line is used to initiate chrome driver
        driver = new ChromeDriver();

        //This line is used to launch a site
//        driver.get("https://demoqa.com/alerts");
//        driver.manage().window().maximize();
//
//        WebElement alertButton = driver.findElement(By.xpath("//button[@id='alertButton']"));
//        SeleniumClickButtonOperations.scrollIntoElement(alertButton, driver);
//        alertButton.click();
//
//        //to switch to alert and get text from alert
//        String alertText = driver.switchTo().alert().getText();
//        if (alertText.equals("You clicked a button"))
//            System.out.println("passed");
//        else System.out.println("failed");
//
//        //to accept an alert
//        driver.switchTo().alert().accept();

        /**
         * Below code will handle frame
         */
        driver.get("https://demoqa.com/frames");
        driver.manage().window().maximize();
        driver.switchTo().frame("frame1");
        WebElement frame1Text = driver.findElement(By.xpath("//h1[@id='sampleHeading']"));
        SeleniumClickButtonOperations.scrollIntoElement(frame1Text, driver);
        System.out.println("Frame1 Text: " + frame1Text.getText());

        /**
         * Switch back to default frame or parent window to handle elements on main webpage outside of frame
         */
        driver.switchTo().defaultContent();
        WebElement parentWinText = driver.findElement(By.xpath("//*[contains(text(),'Sample Iframe page There')]"));
        if (parentWinText.isDisplayed()) {
            System.out.println("passed");
        } else System.out.println("failed");

        driver.quit();
    }
}
