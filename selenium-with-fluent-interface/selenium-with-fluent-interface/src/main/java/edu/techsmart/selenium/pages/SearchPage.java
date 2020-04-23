package edu.techsmart.selenium.pages;

import edu.techsmart.selenium.pages.enums.Role;
import edu.techsmart.selenium.pages.enums.SkillLevels;
import edu.techsmart.selenium.pages.enums.Tab;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;

import static edu.techsmart.selenium.pages.DriverFactory.getChromeDriver;
import static edu.techsmart.selenium.pages.DriverFactory.getWebDriverWait;

public class SearchPage {

    private WebDriver webDriver = getChromeDriver();
    private Wait<WebDriver> wait = getWebDriverWait();

    private SearchPage(){
        //private constructor
    }

    public static SearchPage getSearchPage(){
        return new SearchPage();
    }

  /*
  refactor to use ENUM
      public SearchPage filterBYSkillLevel(String value){
        //click on skill level
        //<div class="search-filter-header">Skill Levels</div>
        webDriver.findElement(By.xpath("//div[contains(@class,'search-filter-header') and contains(.,'Skill Levels')]"))
                .click();

        //Wait till beginner text shows up
        //<span class="search-filter-option-text">Beginner</span>
        wait.until(webDriver -> webDriver.findElement(By.xpath("//span[contains(@class ,'search-filter-option-text') and contains(.,'"+ value +"')]")));

        return this;
    }

    public SearchPage filterByRole(String value){
        webDriver.findElement(By.xpath("//div[contains(@class,'search-filter-header') and contains(.,'Roles')]"))
                .click();

        //Software Development shows up
      // <a href="#" data-label="roles" data-value="software-development" class=""><span class="search-filter-option-text">Software Development</span> (186)</a>
        wait.until(webDriver -> webDriver.findElement(By.xpath("//span[contains(@class ,'search-filter-option-text') and contains(.,'"+ value +"')]")));

        return this;
    }
   */
    public SearchPage filterBYSkillLevel(SkillLevels skillLevels){
        //click on skill level
        //<div class="search-filter-header">Skill Levels</div>
        webDriver.findElement(By.xpath("//div[contains(@class,'search-filter-header') and contains(.,'Skill Levels')]"))
                .click();

        //Wait till beginner text shows up
        //<span class="search-filter-option-text">Beginner</span>
        wait.until(webDriver -> webDriver.findElement(By.xpath("//span[contains(@class ,'search-filter-option-text') and contains(.,'"+ skillLevels +"')]")));

        return this;
    }

    public SearchPage filterByRole(Role role){
        webDriver.findElement(By.xpath("//div[contains(@class,'search-filter-header') and contains(.,'Roles')]"))
                .click();

        //Software Development shows up
      // <a href="#" data-label="roles" data-value="software-development" class=""><span class="search-filter-option-text">Software Development</span> (186)</a>
        wait.until(webDriver -> webDriver.findElement(By.xpath("//span[contains(@class ,'search-filter-option-text') and contains(.,'"+ role +"')]")));

        return this;
    }

    public SearchPage selectCourseTab(Tab tab){
        //select tab course
        //<a href="#tabs-2" role="presentation" tabindex="-1" class="ui-tabs-anchor" id="ui-id-10">Courses</a>
        webDriver.findElement(By.xpath("//a[contains(@class,'tabs') and contains(.,'" + tab + "')]")).click();

        return this;

    }

    public void selectCourse(String courseName){
        //Click on the course
        //<div id="search-results-category-target">
        // <div class="search-result__title">
        //<a href="CoursePage.html">Java Fundamentals: The Java Language</a>
        webDriver.findElement(By.xpath("//div[@id='search-results-category-target']" +
                "//div[@class='search-result columns' and contains(.,'"+ courseName +"')]" +
                "//div[@class='search-result__title']/a")).click();


        //here we do not return the  same object as here we go to the next tab

    }


}
