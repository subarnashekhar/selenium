package edu.techsmart.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import static edu.techsmart.selenium.pages.DriverFactory.getWebDriverWait;
import static  edu.techsmart.selenium.pages.DriverFactory.getChromeDriver;

public class Homepage {

    private WebDriver webDriver = getChromeDriver();
    private Wait<WebDriver> wait = getWebDriverWait();

    private Homepage(){
        //creating private constructor so that no one can create object of Homepage. rather everyone should invoke static method getHomepage() to get object of homepage
    }

    public static  Homepage getHomepage(){
        return new Homepage();
    }

    public void search(String courseName){
      //Search java course
        //<input type="text" name="q" class="header_search--input" placeholder="What do you want to learn?" autocomplete="off" tabindex="2">
        WebElement search = webDriver.findElement(By.className("header_search--input"));
        search.sendKeys(courseName);
        search.sendKeys(Keys.ENTER);
    }
}
