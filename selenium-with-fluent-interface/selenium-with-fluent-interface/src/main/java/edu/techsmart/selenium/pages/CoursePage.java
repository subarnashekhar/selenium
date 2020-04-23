package edu.techsmart.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import static edu.techsmart.selenium.pages.DriverFactory.getChromeDriver;

public class CoursePage {

    private WebDriver webDriver = getChromeDriver();

    private CoursePage(){
        //making private constructor
    }

    public static CoursePage getCoursePage(){
        return  new CoursePage();
    }

    /*
    Refactoring course page

    As lot of things are used twice here like assert and isDisplayed()

    public CoursePage verifyFreeTrialisDisplayed(){
        //course page
        //verify that free trail button is displayed
        //<a id="free_trial" href="https://billing.pluralsight.com/individual/checkout/account-details?requestId=8a3004f1-38ad-4768-b831-4464682d0daf&amp;priceOptionId=0023fb4e-4432-4d90-9202-7f9b0de37214&amp;legacyTrackingId=IND-M-PLUS-FT&amp;wid=7010a000001x7Me" target="_blank" class="button button--primary" title="Start free trial now" data-aa-title="course-hero-cta" data-product-sku="IND-M-PLUS-FT">Start a FREE 10-day trial</a>
        Assert.assertTrue(webDriver.findElement(By.xpath("//div[@id='course-page-hero']" +
                "//div[@class='ps-button section' and contains(.,'Start a FREE 10-day trial')]")).isDisplayed());

        return this;

    }

    public CoursePage verifyCoursePreviewIsDisplayed(){
        //verify second button
        //<a href="https://app.pluralsight.com/player?name=java-fundamentals-core-platform-m0&amp;mode=live&amp;clip=0&amp;course=java-fundamentals-core-platform" class="button button--secondary button-overview_vid button--play" data-aa-title="course-hero-cta-video" target="_blank">Play course overview</a>
        Assert.assertTrue(webDriver.findElement(By.xpath("//div[@id='course-page-hero']" +
                "//div[@class='ps-button section' and contains(.,'Play course overview')]")).isDisplayed());

        return this;
    }
    */


    public CoursePage verifyIsDisplayed(By element){
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
