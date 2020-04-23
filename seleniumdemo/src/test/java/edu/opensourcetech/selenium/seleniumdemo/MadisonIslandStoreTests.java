package edu.opensourcetech.selenium.seleniumdemo;

import org.junit.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.Assert.*;



@SpringBootTest
class MadisonIslandStoreTests {

	public static WebDriver webDriver ;
	@BeforeAll
	public static void setUp(){
		System.setProperty("webdriver.chrome.driver","./src/test/resources/drivers/chromedriver");
		webDriver = new ChromeDriver();
		webDriver.get("http://demo-store.seleniumacademy.com/");
	}

	@Test
	void pageTitleTest() {
		assertEquals("Madison Island",webDriver.getTitle());

	}

	@AfterAll
	public static void tearDown(){
		webDriver.quit();
	}

}
