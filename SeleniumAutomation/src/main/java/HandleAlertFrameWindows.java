import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.LinkedHashSet;
import java.util.Set;

public class HandleAlertFrameWindows {
    /**
     * In this class we are going to see how to handle alerts, frames, multiple windows in selenium
     */

    static WebDriver driver;

    public static void main(String[] args) throws Exception {

        String path = System.getProperty("user.dir");

        //This line is used to set chrome driver and path to its executable
        System.setProperty("webdriver.chrome.driver", path + "/Drivers/chromedriver.exe");

        // This line is used to initiate chrome driver
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        try {
            //This line is used to launch a site
//        driver.get("https://demoqa.com/alerts");
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
//        driver.get("https://demoqa.com/frames");
//        driver.switchTo().frame("frame1");
//        WebElement frame1Text = driver.findElement(By.xpath("//h1[@id='sampleHeading']"));
//        SeleniumClickButtonOperations.scrollIntoElement(frame1Text, driver);
//        System.out.println("Frame1 Text: " + frame1Text.getText());

            /**
             * Switch back to default frame or parent window to handle elements on main webpage outside of frame
             */
//        driver.switchTo().defaultContent();
//        WebElement parentWinText = driver.findElement(By.xpath("//*[contains(text(),'Sample Iframe page There')]"));
//        if (parentWinText.isDisplayed()) {
//            System.out.println("passed");
//        } else System.out.println("failed");

            /**
             * Below snippet will handle a new tab/window opened
             */
            driver.get("https://demoqa.com/browser-windows");
            WebElement newTabButton = driver.findElement(By.xpath("//button[text()=\"New Window\"]"));
            SeleniumClickButtonOperations.scrollIntoElement(newTabButton, driver);
            newTabButton.click();
            LinkedHashSet<String> newTabOpenedId = (LinkedHashSet<String>) driver.getWindowHandles(); // to get the handle of the newly opened tab
            String parentWinId = driver.getWindowHandle();
            for (String childWin : newTabOpenedId) {
                if (!childWin.equals(parentWinId)) {
                    driver.switchTo().window(childWin);
                if (driver.findElement(By.xpath("//*[text()='This is a sample page']")).getText().equals("This is a sample page")) {
                    System.out.println("passed");
                } else System.out.println("failed");
                }
            }

        driver.switchTo().window(parentWinId); //switch back to parent window
        if (driver.getTitle().equals("DEMOQA"))
            System.out.println("switched back to parent window successfully");
        else System.out.println("switching to parent window failed");
        } finally {
            driver.quit();
            Runtime.getRuntime().exec("Taskkill.exe /F /IM chromedriver.exe");
        }

    }
}
