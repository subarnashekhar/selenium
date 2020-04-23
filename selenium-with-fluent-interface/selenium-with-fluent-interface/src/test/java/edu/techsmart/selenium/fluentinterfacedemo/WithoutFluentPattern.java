package edu.techsmart.selenium.fluentinterfacedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class WithoutFluentPattern {


    WebDriver webDriver ;
    Wait<WebDriver> wait;

    @BeforeSuite
    public void loadDriver(){

        System.setProperty("webdriver.chrome.driver","src/test/resources/chromedriver");
        webDriver = new ChromeDriver();
        webDriver.get("file:///Users/subarnagaine/Documents/selenium/website/HomePage.html");
        wait = new FluentWait<WebDriver>(webDriver)
                .withTimeout(30, TimeUnit.SECONDS)
                .pollingEvery(5,TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);
    }



    @Test
    public void searchJavaCourse() throws Exception{


        WebElement welcomeElement = wait.until(webDriver -> webDriver.findElement(By.id("sitename")));
        Assert.assertEquals("Pluralsight",welcomeElement.getText());

        //Search java course
        //<input type="text" name="q" class="header_search--input" placeholder="What do you want to learn?" autocomplete="off" tabindex="2">
        WebElement search = webDriver.findElement(By.className("header_search--input"));
        search.sendKeys("java");
        search.sendKeys(Keys.ENTER);

        //click on skill level
        //<div class="search-filter-header">Skill Levels</div>
        webDriver.findElement(By.xpath("//div[contains(@class,'search-filter-header') and contains(.,'Skill Levels')]"))
        .click();

        //Wait till beginner text shows up
        //<span class="search-filter-option-text">Beginner</span>
        wait.until(webDriver -> webDriver.findElement(By.xpath("//span[contains(@class ,'search-filter-option-text') and contains(.,'Beginner')]")));

        Thread.sleep(2000);

        //select tab course
        //<a href="#tabs-2" role="presentation" tabindex="-1" class="ui-tabs-anchor" id="ui-id-10">Courses</a>
        webDriver.findElement(By.xpath("//a[contains(@class,'tabs') and contains(.,'Courses')]")).click();

        Thread.sleep(2000);

        //Click on the course
        //<div id="search-results-category-target">
        // <div class="search-result__title">
        //<a href="CoursePage.html">Java Fundamentals: The Java Language</a>
        webDriver.findElement(By.xpath("//div[@id='search-results-category-target']" +
                "//div[@class='search-result columns' and contains(.,'Java Fundamentals: The Java Language')]" +
                "//div[@class='search-result__title']/a")).click();

        Thread.sleep(2000);

        //course page
        //verify that free trail button is displayed
        //<a id="free_trial" href="https://billing.pluralsight.com/individual/checkout/account-details?requestId=8a3004f1-38ad-4768-b831-4464682d0daf&amp;priceOptionId=0023fb4e-4432-4d90-9202-7f9b0de37214&amp;legacyTrackingId=IND-M-PLUS-FT&amp;wid=7010a000001x7Me" target="_blank" class="button button--primary" title="Start free trial now" data-aa-title="course-hero-cta" data-product-sku="IND-M-PLUS-FT">Start a FREE 10-day trial</a>
        Assert.assertTrue(webDriver.findElement(By.xpath("//div[@id='course-page-hero']" +
                "//div[@class='ps-button section' and contains(.,'Start a FREE 10-day trial')]")).isDisplayed());


        //verify second button
        //<a href="https://app.pluralsight.com/player?name=java-fundamentals-core-platform-m0&amp;mode=live&amp;clip=0&amp;course=java-fundamentals-core-platform" class="button button--secondary button-overview_vid button--play" data-aa-title="course-hero-cta-video" target="_blank">Play course overview</a>
        Assert.assertTrue(webDriver.findElement(By.xpath("//div[@id='course-page-hero']" +
                "//div[@class='ps-button section' and contains(.,'Play course overview')]")).isDisplayed());

    }


    @AfterSuite
    public void closeDriver(){
       webDriver.quit();
    }

}
