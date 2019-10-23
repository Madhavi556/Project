package com.training.sanity.tests;

import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.LoginPOM;

import com.training.pom.RegistrationPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.rules.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;

public class RetailRegistrationRTTC_001 {
    
	private WebDriver driver;
	private String baseUrl;
	private LoginPOM loginPOM;
	private RegistrationPOM registrationPOM;
	private static Properties properties;
	private ScreenShot screenShot;
	
	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}

	@BeforeMethod
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		registrationPOM = new RegistrationPOM(driver); 
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl);
	}
	
	@Test
	public void Register() {
		WebElement register=driver.findElement(By.xpath("//i[@class='fa fa-user-o']"));
		Actions act=new Actions(driver);
		act.moveToElement(register).build().perform();
		driver.findElement(By.xpath("//span[contains(text(),'LOGIN / REGISTER')]")).click();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		assertEquals("Account Login", driver.getTitle(),"Wrong Window");
		driver.findElement(By.xpath("//a[@class='btn btn-primary']")).click();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		assertEquals("Register Account", driver.getTitle(),"Wrong Window");
		registrationPOM.firstName("reva");
		registrationPOM.lastName("sharma");
		registrationPOM.email("revasharma7@gmail.com");
		registrationPOM.telePhone("9345677833");
		registrationPOM.address1("Jayanagar");
		registrationPOM.address2("bangalore");
		registrationPOM.city("bangalore");
		registrationPOM.postcode("560018");
	    registrationPOM.country("India");
		registrationPOM.region("Karnataka");
		registrationPOM.password("reva123");
		registrationPOM.confirmPassword("reva123");
		registrationPOM.subscribe();
		registrationPOM.conditions();
		registrationPOM.clickContinue();
		assertEquals("Your Account Has Been Created!",driver.getTitle(),"Unable to Register");
		screenShot.captureScreenShot("Registered");
   }

	@AfterMethod
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
		
	}
}
