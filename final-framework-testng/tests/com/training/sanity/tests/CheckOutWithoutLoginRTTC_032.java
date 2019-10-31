package com.training.sanity.tests;

import static org.junit.Assert.assertEquals;
import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class CheckOutWithoutLoginRTTC_032 {

	private WebDriver driver;
	private String baseUrl;
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
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl);
		
	}
	
    @Test(priority=1)
    public void PlaceOrder() throws InterruptedException
    {
      
       WebElement search=driver.findElement(By.xpath("//a[@id='search_button']"));
       Actions act=new Actions(driver);
       act.moveToElement(search).build().perform();
       driver.findElement(By.xpath("//input[@id='filter_keyword']")).sendKeys("Nam sed");
       
      driver.findElement(By.xpath("//a[@id='search_button']")).click();
      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
      driver.findElement(By.xpath("//div[@class='col col-xs-fill']//a[contains(text(),'Nam sed')]")).click();
           
       driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
       assertEquals("Nam sed",driver.getTitle(),"Wrong Page");
    }
    
    @Test(priority=2, dependsOnMethods= {"PlaceOrder"})
    public void checkOut() throws InterruptedException
    {
       driver.findElement(By.id("button-cart")).click();
       driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
       
       WebElement cart=driver.findElement(By.xpath("//i[@class='tb_icon ico-linea-ecommerce-bag']"));
       Actions act1=new Actions(driver);
       act1.moveToElement(cart).build().perform();
       driver.findElement(By.xpath("//a[contains(text(),'View Cart')]")).click();
       
       driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
       assertEquals("Shopping Cart",driver.getTitle(),"Shopping cart page not diaplyed");
       
       driver.findElement(By.xpath("//a[@class='btn btn-primary']")).click();
       driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
       assertEquals(driver.getTitle(),"Checkout", "Login Page not displayed");
       screenShot.captureScreenShot("Login Page displayed");
       System.out.println("Login Page displayed");
     
    }
    
	
	@AfterTest
			
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
}
