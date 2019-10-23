package com.training.sanity.tests;

import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.LoginPOM;
import com.training.pom.RegistrationPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class LoginTestRTTC_002 {

	private WebDriver driver;
	private String baseUrl;
	private LoginPOM loginPOM;
	private RegistrationPOM registrationPOM;
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
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl);
	}
	
    @Test(priority=1)
    public void launchLoginPage() throws InterruptedException
    {
    	WebElement register=driver.findElement(By.xpath("//i[@class='fa fa-user-o']"));
		Actions act=new Actions(driver);
		act.moveToElement(register).build().perform();
		driver.findElement(By.xpath("//span[contains(text(),'LOGIN / REGISTER')]")).click();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		screenShot.captureScreenShot("Login Page");
		Assert.assertEquals("Account Login",driver.getTitle(),"Login page Not opened");
		System.out.println("Login Page opened");
    }
    
	@Test(priority=2, dependsOnMethods= {"launchLoginPage"})
	public void Login() throws InterruptedException
	{
			loginPOM.sendUserName("revasharma6@gmail.com");
		    loginPOM.sendPassword("reva123");
		    loginPOM.clickLoginBtn(); 
		    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		    screenShot.captureScreenShot("Successful Login");
		    Assert.assertEquals("My Account",driver.getTitle(),"Login Unsuccessful");
		    System.out.println("Login Successful");
		    
	}
	
	@AfterTest
			
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
}
