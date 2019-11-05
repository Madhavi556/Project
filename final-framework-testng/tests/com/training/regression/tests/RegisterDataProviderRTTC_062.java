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
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.RegistrationPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class RegisterDataProviderRTTC_062 {
	private WebDriver driver; 
	private String baseUrl; 
	private RegistrationPOM registrationPOM;
	private static Properties properties; 
	private ScreenShot screenShot; 


	@DataProvider(name="inputs")
	public Object[][] getData() {
		return new Object[][] {
			{"sunil", "nagaraj", "sunil5@gmail.com", "9876543210", "yeshwanthapur", "bangalore", "bangalore", "560022", "India", "Karnataka", "manipal", "manipal"},
			{"manzoor", "mehadi", "mehadi5@gmail.com", "8906543210", "electronic city", "bangalore", "bangalore","560100", "India", "Karnataka", "manzoor", "manzoor"},
			{"puli", "keshi", "puli5@gmail.com", "6789043217", "chennai", "chennai", "chennai", "561321","India","Tamil Nadu","pulikeshi","pulikeshi"},
			{"priya","prabhu","priya5@gmail.com","8906754321","hyderabad","hyderabad","hyderabad","9876547","India","Telangana","priya","priya"}
		};
	}

	@BeforeSuite
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}

	@BeforeClass
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		registrationPOM = new RegistrationPOM(driver); 
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl);
	}
	
	@AfterTest
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
	
	@BeforeMethod
	public void registerPage()
	{
		WebElement register=driver.findElement(By.xpath("//i[@class='fa fa-user-o']"));
		Actions act=new Actions(driver);
		act.moveToElement(register).build().perform();
		driver.findElement(By.xpath("//span[contains(text(),'LOGIN / REGISTER')]")).click();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		assertEquals("Account Login", driver.getTitle(),"Wrong Window");
		driver.findElement(By.xpath("//a[@class='btn btn-primary']")).click();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		assertEquals("Register Account", driver.getTitle(),"Wrong Window");
	}
	@Test(dataProvider="inputs")
	public void testMethod1(String FirstName, String Lastname, String Email, String Telephone, String Address1, String Address2, String city, String PostalCode, String country, String region, String password, String confirm) {
		registrationPOM.firstName(FirstName);
		registrationPOM.lastName(Lastname);
		registrationPOM.email(Email);
		registrationPOM.telePhone(Telephone);
		registrationPOM.address1(Address1);
		registrationPOM.address2(Address2);
		registrationPOM.city(city);
		registrationPOM.postcode(PostalCode);
	    registrationPOM.country(country);
		registrationPOM.region(region);
		registrationPOM.password(password);
		registrationPOM.confirmPassword(confirm);
		registrationPOM.subscribe();
		registrationPOM.conditions();
		registrationPOM.clickContinue();
		assertEquals("Your Account Has Been Created!",driver.getTitle(),"Unable to Register");
		screenShot.captureScreenShot(FirstName);
	}
	@AfterMethod
	public void logout()
	{
		WebElement logout=driver.findElement(By.xpath("//i[@class='fa fa-user-o']"));
		Actions act=new Actions(driver);
		act.moveToElement(logout).build().perform();
		driver.findElement(By.xpath("//span[contains(text(),'LOGOUT')]")).click();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		assertEquals("Account Logout", driver.getTitle(),"Wrong Window");
	}
}
