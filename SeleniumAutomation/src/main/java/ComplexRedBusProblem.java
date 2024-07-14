import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.ListIterator;

public class ComplexRedBusProblem {

    static WebDriver driver;

    public static void main(String[] args) throws InterruptedException, IOException {
        String path = System.getProperty("user.dir");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-notifications");

        //This line is used to set chrome driver and path to its executable
        System.setProperty("webdriver.chrome.driver", path + "/Drivers/chromedriver.exe");

        // This line is used to initiate chrome driver
        driver = new ChromeDriver(chromeOptions);
        driver.get("https://www.redbus.in/");
        driver.manage().window().maximize();
        try {

            fetchMonthDetails("October 2024");

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            Runtime.getRuntime().exec("Taskkill.exe /F /IM chromedriver.exe");
        }

    }

    static void fetchMonthDetails(String date) throws InterruptedException {
        WebElement calendar = driver.findElement(By.xpath("//div[@id=\"onwardCal\"]"));
        calendar.click();

        LocalDate currentdate = LocalDate.now();
        String[] temp = date.split(" ");
        String enteredYear = temp[1];
        System.out.println(enteredYear);
        int currentYear = currentdate.getYear();
        int currentMonth = 0;
        if (currentYear == Integer.valueOf(enteredYear)) {
            currentMonth = currentdate.getMonthValue();
        }
        if (currentYear < Integer.valueOf(enteredYear)) {
            currentMonth = 1;
            int i = 0;
            while (i < 1000) {
                driver.findElement(By.xpath("//*[@id=\"onwardCal\"]/div/div[2]/div/div/div[1]/div[3]")).click();
                try {
                    if (driver.findElement(By.xpath("//*[text()='2025']")).isDisplayed()) {
                        System.out.println("Element is displayed");
                        i = Integer.MAX_VALUE;
                    }
                } catch (Exception ex) {
                    i++;
                }
            }
        }

        int monthToGo = 0;


        switch (temp[0].toUpperCase()) {
            case "JANUARY":
                monthToGo = 1;
                break;
            case "FEBRUARY":
                monthToGo = 2;
                break;
            case "MARCH":
                monthToGo = 3;
                break;
            case "APRIL":
                monthToGo = 4;
                break;
            case "MAY":
                monthToGo = 5;
                break;
            case "JUNE":
                monthToGo = 6;
                break;
            case "JULY":
                monthToGo = 7;
                break;
            case "AUGUST":
                monthToGo = 8;
                break;
            case "SEPTEMBER":
                monthToGo = 9;
                break;
            case "OCTOBER":
                monthToGo = 10;
                break;
            case "NOVEMBER":
                monthToGo = 11;
                break;
            case "DECEMBER":
                monthToGo = 12;
                break;
        }
        int forwardMovement = monthToGo - currentMonth;
        System.out.println("forward movement " + forwardMovement);


        for (int j = 0; j < forwardMovement; j++) {
            Thread.sleep(2000);
            driver.findElement(By.xpath("//*[@id=\"onwardCal\"]/div/div[2]/div/div/div[1]/div[3]")).click();
        }
        String holidayText = driver.findElement(By.xpath("//*[@class=\"holiday_count\"]")).getText();
        System.out.println(holidayText);

        List<WebElement> list = driver.findElements(By.xpath("//*[contains(@class,'DayTilesWrapper__RowWrap-sc-19pz9i8-1')]/span/div/span[contains(@class,'bwoYtA')]"));
        ListIterator<WebElement> listIterator = list.listIterator();
        System.out.println("weekend dates are: ");
        while (listIterator.hasNext()) {
            System.out.print(listIterator.next().getText() + " ");
        }

        driver.quit();
    }
}
