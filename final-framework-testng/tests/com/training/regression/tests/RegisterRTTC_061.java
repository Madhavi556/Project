//As we don't have access to DB to check the details, I wrote the test cases to register an account.
package com.training.regression.tests;

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
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.RegistrationPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class RegisterRTTC_061 {
	private WebDriver driver;
	private String baseUrl;
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
		registrationPOM.firstName("manzoor");
		registrationPOM.lastName("mehadi");
		registrationPOM.email("mehadi98@gmail.com");
		registrationPOM.telePhone("9876543210");
		registrationPOM.address1("Yeshwantnagar");
		registrationPOM.address2("bangalore");
		registrationPOM.city("bangalore");
		registrationPOM.postcode("560022");
	    registrationPOM.country("India");
		registrationPOM.region("Karnataka");
		registrationPOM.password("manzoor1");
		registrationPOM.confirmPassword("manzoor1");
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
