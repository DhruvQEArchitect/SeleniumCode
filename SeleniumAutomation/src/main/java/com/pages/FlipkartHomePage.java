package com.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * This pages package contains all pages related to the application which we are trying to automate using
 * page object model and page factory concept
 */

public class FlipkartHomePage {

    /**
     * We use @FindBy annotation to locate element and when we use it
     * we dont have to use findelement or elements method to locate element
     */

    Pages pages = Pages.getInstance();
    static WebDriver driver;

    static FlipkartHomePage instance = null;

    public static FlipkartHomePage getInstance(WebDriver driver) {
        if (instance == null)
            instance = new FlipkartHomePage(driver);
        return instance;
    }

    //
    private FlipkartHomePage() {

    }

    public FlipkartHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    /**
     * Just for the testing purpose we will test a few elements only
     */
    @FindBy(xpath = "//a[@title=\"Flipkart\"]")
    WebElement flipkartLogo;

    @FindBy(xpath = "//input[@title=\"Search for Products, Brands and More\"]")
    WebElement searchBar;

    public void checkElementsOnHomePage() {
        pages.isElementVisible(flipkartLogo);
        pages.isElementVisible(searchBar);
    }

    public String verifyPageTitle() {
        return pages.getPageTitle();
    }


}
