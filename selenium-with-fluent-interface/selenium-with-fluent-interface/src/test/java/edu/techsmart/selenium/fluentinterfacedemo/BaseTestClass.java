package edu.techsmart.selenium.fluentinterfacedemo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static edu.techsmart.selenium.pages.DriverFactory.getChromeDriver;
import  static  edu.techsmart.selenium.pages.DriverFactory.getWebDriverWait;

public class BaseTestClass {

    WebDriver webDriver ;
    Wait<WebDriver> wait;

    @BeforeSuite
    public void loadDriver(){

        webDriver = getChromeDriver();
        wait = getWebDriverWait();
        webDriver.get("file:///Users/subarnagaine/Documents/selenium/website/HomePage.html");
        webDriver.manage().window().fullscreen();
        WebElement welcomeElement = wait.until(webDriver -> webDriver.findElement(By.id("sitename")));
        Assert.assertEquals("Pluralsight",welcomeElement.getText());
    }

    @AfterSuite
    public void closeDriver(){
        webDriver.quit();
    }
}
