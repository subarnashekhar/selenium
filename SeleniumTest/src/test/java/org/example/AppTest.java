package org.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static org.junit.Assert.*;


/**
 * Unit test for simple App.
 */
public class AppTest 
{



    @BeforeAll
    public static void setUp(){

    }

    @Test
    public void pageTitleTest() {
        WebDriver webDriver ;
        System.setProperty("webdriver.chrome.driver","src/test/resources/drivers/chromedriver");
        webDriver = new ChromeDriver();
        webDriver.get("http://demo-store.seleniumacademy.com/");
        assertEquals("Madison Island",webDriver.getTitle());
        webDriver.quit();
    }

    @Test
    public void getAllHyperLinks() {
        WebDriver webDriver ;
        System.setProperty("webdriver.chrome.driver","src/test/resources/drivers/chromedriver");
        webDriver = new ChromeDriver();
        webDriver.get("http://demo-store.seleniumacademy.com/");
        List<WebElement> links = webDriver.findElements(By.tagName("a"));
        System.out.println("Number of Hyperlinks " + links.size());
        links.stream()
                .filter(elm -> elm.getText().length() > 0)
                .forEach(elm -> System.out.println(elm.getText()));
        webDriver.quit();
    }

    @Test
    public void searchItemsTest() {
        WebDriver webDriver ;
        System.setProperty("webdriver.chrome.driver","src/test/resources/drivers/chromedriver");
        webDriver = new ChromeDriver();
        webDriver.get("http://demo-store.seleniumacademy.com/");
//<input id="search" type="search" name="q" value="" class="input-text required-entry" maxlength="128" placeholder="Search entire store here..." autocomplete="off">
        WebElement searchBox = webDriver.findElement(By.id("search"));
        System.out.println("Is SearchBox visible" + searchBox.isDisplayed());
        if(searchBox.isDisplayed()){
            searchBox.sendKeys("Phones");
        }

        webDriver.quit();
    }

    @Test
    public void getListofBooksTest() {
        WebDriver webDriver ;
        System.setProperty("webdriver.chrome.driver","src/test/resources/drivers/chromedriver");
        webDriver = new ChromeDriver();
        webDriver.get("http://demo-store.seleniumacademy.com/");
//<input id="search" type="search" name="q" value="" class="input-text required-entry" maxlength="128" placeholder="Search entire store here..." autocomplete="off">
        WebElement searchBox = webDriver.findElement(By.id("search"));
        System.out.println("Is SearchBox visible" + searchBox.isDisplayed());
        if(searchBox.isDisplayed()){
            searchBox.sendKeys("Books");
        }
        WebElement searchButton = webDriver.findElement(By.className("search-button"));
        searchButton.click();

        //<button type="submit" title="Search" class="button search-button"><span><span>Search</span></span></button>
        //*[@id="search_mini_form"]/div[1]/button
        assertEquals("Search results for: 'Books'",webDriver.getTitle());

        List<WebElement> searchItems = webDriver.findElements(By.cssSelector("h2.product-name"));

        searchItems.stream().map(elm -> elm.getText())
                .forEach(elm -> System.out.println(elm));

        webDriver.quit();
    }

    @Test
    public void tryHeadLessMode() {
        //None of the options has worked in Chrome

        WebDriver webDriver ;
        System.setProperty("webdriver.chrome.driver","src/test/resources/drivers/chromedriver");
        ChromeOptions chromeOptions = new ChromeOptions();
//       chromeOptions.addArguments("--headless");
//       chromeOptions.addArguments("--disable-gpu");
       //chromeOptions.setBinary("src/test/resources/drivers/chromedriver");
        //chromeOptions.setBinary("/Applications/Google Chrome Canary.app/Contents/MacOS/Google Chrome Canary");
        chromeOptions.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors", "--silent");

        // chromeOptions.addArguments("window-size=1200x600");

        webDriver = new ChromeDriver(chromeOptions);

        webDriver.get("http://demo-store.seleniumacademy.com/");

        WebElement searchBox = webDriver.findElement(By.id("search"));
        System.out.println("Is SearchBox visible" + searchBox.isDisplayed());

        webDriver.quit();
    }


    @Test
    public void takeScreenshot() throws IOException {
        //This will search Books and will take screesnhot

        WebDriver webDriver ;
        System.setProperty("webdriver.chrome.driver","src/test/resources/drivers/chromedriver");
        webDriver = new ChromeDriver();
        webDriver.get("http://demo-store.seleniumacademy.com/");
        WebElement searchBox = webDriver.findElement(By.id("search"));
        System.out.println("Is SearchBox visible" + searchBox.isDisplayed());
        if(searchBox.isDisplayed()){
            searchBox.sendKeys("Books");
        }
        WebElement searchButton = webDriver.findElement(By.className("search-button"));
        searchButton.click();

        assertEquals("Search results for: 'Books'",webDriver.getTitle());

        File screenshot = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File("./target/screenshot.png"));

    }


    @Test
    public void switchingBetweenWindows()  {
        WebDriver webDriver ;
        System.setProperty("webdriver.chrome.driver","src/test/resources/drivers/chromedriver");
        webDriver = new ChromeDriver();
        webDriver.get("http://guidebook.seleniumacademy.com/Window.html");
        String firstWindowHandle = webDriver.getWindowHandle();
        webDriver.findElement(By.linkText("Google Search")).click();
        String secondWindowHandle = webDriver.getWindowHandle();
        Set<String> windowHandles = webDriver.getWindowHandles();

        windowHandles.stream().forEach(System.out::println);

        webDriver.switchTo().window(firstWindowHandle);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        webDriver.quit();


    }

    //handling of popup and alerts https://www.guru99.com/alert-popup-handling-selenium.html
    //http://demo.guru99.com/test/delete_customer.php

    @Test
    public void handlePopup() throws InterruptedException {
        WebDriver webDriver ;
        System.setProperty("webdriver.chrome.driver","src/test/resources/drivers/chromedriver");
        webDriver = new ChromeDriver();
        webDriver.get("http://demo.guru99.com/test/delete_customer.php");

       //<input type="text" name="cusid" maxlength="10">
        //Xpath syntax Xpath=//tagname[@attribute='value']
        webDriver.findElement(By.xpath("//input[@name='cusid']")).sendKeys("110956");
        //<input type="submit" name="submit" value="Submit">
        webDriver.findElement(By.xpath("//input[@name='submit']")).click();
        System.out.println(webDriver.switchTo().alert().getText());

        Thread.sleep(2000);
        webDriver.switchTo().alert().accept();

        Thread.sleep(2000);
        System.out.println(webDriver.switchTo().alert().getText());
        webDriver.switchTo().alert().accept();

        Thread.sleep(2000);

        webDriver.quit();
    }

    @Test
    public void implicitWaitSelenium() throws InterruptedException {

        WebDriver webDriver ;
        System.setProperty("webdriver.chrome.driver","src/test/resources/drivers/chromedriver");
        webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //launch page
        webDriver.get("http://demo.guru99.com/test/guru99home/");
        //maximizes page
        webDriver.manage().window().maximize();

        String title = webDriver.getTitle();
        assertEquals("Demo Guru99 Page",title);
        webDriver.quit();

    }

    //Explicit wait and fluent wait
    // https://www.browserstack.com/guide/wait-commands-in-selenium-webdriver

    //store cookies
    @Test
    public void storeCookiesFordemoStores() throws InterruptedException, IOException {
        //login and then store cookies
        //http://demo-store.seleniumacademy.com/customer/account/login/
        WebDriver webDriver ;
        System.setProperty("webdriver.chrome.driver","src/test/resources/drivers/chromedriver");
        webDriver = new ChromeDriver();
        webDriver.get("http://demo-store.seleniumacademy.com/customer/account/login/");

        //email address fields
        //<input type="email" autocapitalize="off" autocorrect="off" spellcheck="false" name="login[username]" value="" id="email" class="input-text required-entry validate-email" title="Email Address">

        webDriver.findElement(By.xpath("//input[@id='email']")).sendKeys("perftest@mailinator.com");

        ///password fileds
        //<input type="password" name="login[password]" class="input-text required-entry validate-password" id="pass" title="Password">
        webDriver.findElement(By.xpath("//input[@id='pass']")).sendKeys("Test@123");
        //login button
        //<button type="submit" class="button" title="Login" name="send" id="send2"><span><span>Login</span></span></button>
        webDriver.findElement(By.xpath("//button[@id='send2']")).click();

        //check if login is done, we will try to wait for Name to appear with fluent wait here
        //<p class="welcome-msg">Welcome, Perf Test! </p>
        Wait<WebDriver> wait = new FluentWait<WebDriver>(webDriver)
                .withTimeout(30,TimeUnit.SECONDS)
                .pollingEvery(5,TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);

        Function<WebDriver,WebElement> function = new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return webDriver.findElement(By.xpath("//p[@class='welcome-msg']"));
            }
        };

        WebElement name = wait.until(function);
        assertTrue("Welcome, Perf Test!".equalsIgnoreCase(name.getText()));
        Set<Cookie> cookies = webDriver.manage().getCookies();
        System.out.println("number of cookies stored" + cookies.size());
        cookies.stream().forEach(System.out::println);

        //write cookies to a file
        File file = new File("./target/browser.data");
         file.delete();
        BufferedWriter bufferedWriter  = new BufferedWriter(new FileWriter(file));

//        for(Cookie cookie : cookies){
//            bufferedWriter.write(cookie.getDomain() +","+  cookie.getExpiry() + ","+ cookie.getName()  +","+cookie.getValue() +","+cookie.getPath());
//            bufferedWriter.newLine();
//        }

        for (Cookie ck : webDriver.manage().getCookies()) {
            bufferedWriter.write((ck.getName() + ";" + ck.getValue() + ";" + ck.
                    getDomain()
                    + ";" + ck.getPath() + ";" + ck.getExpiry() + ";" + ck.
                    isSecure()));
            bufferedWriter.newLine();
        }

        bufferedWriter.flush();
        bufferedWriter.close();

        webDriver.quit();

    }


    @Test
    public void readCookiesFormFileForDemoStores() throws InterruptedException, IOException, ParseException {

            WebDriver webDriver ;
            System.setProperty("webdriver.chrome.driver","src/test/resources/drivers/chromedriver");
            webDriver = new ChromeDriver();
        webDriver.get("http://demo-store.seleniumacademy.com");

        File dataFile = new File("./target/browser.data");
        FileReader fr = new FileReader(dataFile);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while ((line = br.readLine()) != null) {
            StringTokenizer str = new StringTokenizer(line, ";");
            while (str.hasMoreTokens()) {
                String name = str.nextToken();
                String value = str.nextToken();
                String domain = str.nextToken();
                String path = str.nextToken();
                Date expiry = null;
                String dt;
                if (!(dt = str.nextToken()).equals("null")) {
                    SimpleDateFormat formatter =
                            new SimpleDateFormat("E MMM d HH:mm:ss z yyyy");
                    expiry = formatter.parse(dt);
                }

                boolean isSecure = new Boolean(str.nextToken()).
                        booleanValue();
                Cookie ck = new Cookie(name, value, "demo-store.seleniumacademy.com", path, expiry, isSecure);
                webDriver.manage().addCookie(ck);
            }
        }



        webDriver.get("http://demo-store.seleniumacademy.com/customer/account/index/");
        String val = webDriver.findElement(By.cssSelector("div.page-title")).getText() ;

        assertTrue(val.equals("MY DASHBOARD"));

//            check if login is done, we will try to wait for Name to appear with fluent wait here
//            <p class="welcome-msg">Welcome, Perf Test! </p>
            Wait<WebDriver> wait = new FluentWait<WebDriver>(webDriver)
                    .withTimeout(30,TimeUnit.SECONDS)
                    .pollingEvery(5,TimeUnit.SECONDS)
                    .ignoring(NoSuchElementException.class);

            Function<WebDriver,WebElement> function = new Function<WebDriver, WebElement>() {
                @Override
                public WebElement apply(WebDriver webDriver) {
                    return webDriver.findElement(By.xpath("//p[@class='welcome-msg']"));
                }
            };

            WebElement welcomeElement = wait.until(function);
            assertTrue("Welcome, Perf Test!".equalsIgnoreCase(welcomeElement.getText()));

    }

    @Test
    public void testRadioButtonandCheckBox(){
        //http://demo.guru99.com/test/radio.html

        WebDriver webDriver ;
        System.setProperty("webdriver.chrome.driver","src/test/resources/drivers/chromedriver");
        webDriver = new ChromeDriver();
        webDriver.get("http://demo.guru99.com/test/radio.html");

        //<input type="radio" name="webform" id="vfb-7-2" value="Option 2">
        WebElement radio1 = webDriver.findElement(By.id("vfb-7-1"));
        WebElement radio2 = webDriver.findElement(By.id("vfb-7-2"));
        WebElement radio3 = webDriver.findElement(By.id("vfb-7-3"));

        System.out.println("Radio button 1 selected "+ radio1.isSelected() );
        System.out.println("Radio button 2 selected "+ radio2.isSelected() );
        System.out.println("Radio button 3 selected "+ radio3.isSelected() );

        radio3.click();

        System.out.println("Radio button 1 selected "+ radio1.isSelected() );
        System.out.println("Radio button 2 selected "+ radio2.isSelected() );
        System.out.println("Radio button 3 selected "+ radio3.isSelected() );

        //<input type="checkbox" name="webform" id="vfb-6-0" value="checkbox1">

        WebElement checkbox1 = webDriver.findElement(By.id("vfb-6-0"));
        WebElement checkbox2 = webDriver.findElement(By.id("vfb-6-1"));
        WebElement checkbox3 = webDriver.findElement(By.id("vfb-6-2"));

        System.out.println("Checkbox 1 selected "+ checkbox1.isSelected() );
        System.out.println("Checkbox 2 selected "+ checkbox2.isSelected() );
        System.out.println("Checkbox 3 selected "+ checkbox3.isSelected() );

        checkbox2.click();

        System.out.println("Checkbox 1 selected "+ checkbox1.isSelected() );
        System.out.println("Checkbox 2 selected "+ checkbox2.isSelected() );
        System.out.println("Checkbox 3 selected "+ checkbox3.isSelected() );

        webDriver.quit();

    }


    @Test
    public void dropdownHandle() throws InterruptedException {
        //<select name="country" size="1">

        WebDriver webDriver ;
        System.setProperty("webdriver.chrome.driver","src/test/resources/drivers/chromedriver");
        webDriver = new ChromeDriver();
        webDriver.get("http://demo.guru99.com/test/newtours/register.php");

        Select selectDropDown = new Select(webDriver.findElement(By.name("country")));

        //to show all options
        selectDropDown.getOptions().stream().map(x -> x.getText()).forEach(System.out::println);

        selectDropDown.selectByVisibleText("UNITED STATES");

        Thread.sleep(20000);

        webDriver.quit();

    }
    @AfterAll
    public static void tearDown(){

    }
}











