package edu.techsmart.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import static edu.techsmart.selenium.pages.DriverFactory.getChromeDriver;

public class CommonVerification {

    protected WebDriver webDriver = getChromeDriver();

    private CommonVerification(){
        //Private constructor
    }

    public static CommonVerification getCommonVerification(){
        return new CommonVerification();
    }


    public CommonVerification verifyIsDisplayed(By element){
        Assert.assertTrue(webDriver.findElement(element).isDisplayed());
        return this;
    }


    public static By freeTrialButton(){
        return By.xpath("//div[@id='course-page-hero']" +
                "//div[@class='ps-button section' and contains(.,'Start a FREE 10-day trial')]");
    }

    public static By coursePreviewButton(){
        return By.xpath("//div[@id='course-page-hero']" +
                "//div[@class='ps-button section' and contains(.,'Play course overview')]");
    }

    public static By someOtherButton(){
        return By.xpath("something else check");
    }

}
