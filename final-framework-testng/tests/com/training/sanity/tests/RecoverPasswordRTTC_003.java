package com.training.sanity.tests;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.LoginPOM;
import com.training.pom.RecoverPasswordPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class RecoverPasswordRTTC_003 {

	private WebDriver driver;
	private String baseUrl;
	private LoginPOM loginPOM;
	private RecoverPasswordPOM recover;
	private static Properties properties;
	private ScreenShot screenShot;

	@BeforeSuite
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}

	@BeforeClass
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		loginPOM = new LoginPOM(driver); 
		recover = new RecoverPasswordPOM(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl);
	}
	
	@Test(priority=1)
	public void launchLoginPage()
	{
		WebElement register=driver.findElement(By.xpath("//i[@class='fa fa-user-o']"));
		Actions act=new Actions(driver);
		act.moveToElement(register).build().perform();
		driver.findElement(By.xpath("//span[contains(text(),'LOGIN / REGISTER')]")).click();
		screenShot.captureScreenShot("Login Page");
	}

	@Test(priority=2,dependsOnMethods= {"launchLoginPage"})
	public void validLoginTest() {
		
			loginPOM.sendUserName("revasharma6@gmail.com");
		    loginPOM.sendPassword("reva124");
		    loginPOM.clickLoginBtn(); 
		    screenShot.captureScreenShot("Login Failure");
		    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		    String expected="Warning: No match for E-Mail Address and/or Password.";
		    String actual=driver.findElement(By.xpath("//div[@class='alert alert-danger']")).getText();
		    assertEquals(actual, expected);
		    driver.findElement(By.xpath("//a[contains(text(),'Forgotten Password')]")).click();
		    recover.sendEmail("revasharma6@gmail.com");
		    recover.clickContinue();
		    screenShot.captureScreenShot("Recover msg");
	  
		    
	}
	
	@AfterTest
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
}
