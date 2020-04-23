package edu.techsmart.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class DriverFactory {

    private static WebDriver webDriver;
    private static Wait<WebDriver> wait;

    private DriverFactory(){

    }

    public static WebDriver getChromeDriver(){
        if(webDriver==null){
            System.setProperty("webdriver.chrome.driver","src/test/resources/chromedriver");
            webDriver = new ChromeDriver();
        }

        return webDriver;
    }

    public  static  Wait<WebDriver>  getWebDriverWait(){
        if(wait == null){
            wait = new FluentWait<WebDriver>(webDriver)
                    .withTimeout(30, TimeUnit.SECONDS)
                    .pollingEvery(5,TimeUnit.SECONDS)
                    .ignoring(NoSuchElementException.class);
        }

        return wait;
    }
}
