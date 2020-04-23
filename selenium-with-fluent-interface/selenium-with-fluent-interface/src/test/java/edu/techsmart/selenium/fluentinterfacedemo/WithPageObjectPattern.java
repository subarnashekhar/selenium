package edu.techsmart.selenium.fluentinterfacedemo;

import edu.techsmart.selenium.pages.CommonVerification;
import edu.techsmart.selenium.pages.CoursePage;
import edu.techsmart.selenium.pages.Homepage;
import edu.techsmart.selenium.pages.SearchPage;
import edu.techsmart.selenium.pages.enums.Role;
import edu.techsmart.selenium.pages.enums.SkillLevels;
import edu.techsmart.selenium.pages.enums.Tab;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import static edu.techsmart.selenium.pages.CoursePage.*;
import static edu.techsmart.selenium.pages.Homepage.getHomepage;
import static edu.techsmart.selenium.pages.SearchPage.getSearchPage;
import static edu.techsmart.selenium.pages.CommonVerification.getCommonVerification;
import static edu.techsmart.selenium.pages.CommonVerification.freeTrialButton;
import static edu.techsmart.selenium.pages.CommonVerification.coursePreviewButton;



public class WithPageObjectPattern extends BaseTestClass {

    /*

    Commenting to use static factory method
    Homepage homepage = new Homepage();
    SearchPage searchPage = new SearchPage();
    CoursePage coursePage = new CoursePage();

     */
    Homepage homepage = getHomepage();
    SearchPage searchPage = getSearchPage();
    CoursePage coursePage = getCoursePage();
    CommonVerification commonVerification = getCommonVerification();


    @Test
    public void searchJavaCourseWithPageObjectPattern() throws Exception{
        homepage.search("java");

        /*
         Here how we will do it with builder pattern
                 searchPage.filterBYSkillLevel("Beginner");
        searchPage.filterByRole("Software Development");
        searchPage.selectCourseTab();
        searchPage.selectCourse("Java Fundamentals: The Java Language");
         */

        /*
        refactoring to use Enum
                searchPage.filterBYSkillLevel("Beginner")
                  .filterByRole("Software Development")
                  .selectCourseTab()
                  .selectCourse("Java Fundamentals: The Java Language");
         */
        searchPage.filterBYSkillLevel(SkillLevels.BEGINNER)
                .filterByRole(Role.SOFTWARE_DEVELOPMENT)
                .selectCourseTab(Tab.COURSES)
                .selectCourse("Java Fundamentals: The Java Language");


        /*
                coursePage.verifyFreeTrialisDisplayed()
                  .verifyCoursePreviewIsDisplayed();
         */

        //Using new pattern
        coursePage.verifyIsDisplayed(freeTrialButton())
                .verifyIsDisplayed(coursePreviewButton());


        //Now these two verification can be common . so it makes sense to put them in a common place where other classes can also use it
        //OR

        commonVerification.verifyIsDisplayed(freeTrialButton())
                .verifyIsDisplayed(coursePreviewButton());


        Thread.sleep(2000);

    }
}
