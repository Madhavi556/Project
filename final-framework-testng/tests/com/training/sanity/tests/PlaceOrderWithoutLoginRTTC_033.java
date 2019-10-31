package com.training.sanity.tests;

import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.LoginPOM;
import com.training.pom.RegistrationPOM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;

public class PlaceOrderWithoutLoginRTTC_033 {
	private WebDriver driver;
	private LoginPOM login;
	RegistrationPOM register=new RegistrationPOM(driver);
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
	public void Login()
	{
		login = new LoginPOM(driver);
		WebElement register=driver.findElement(By.xpath("//i[@class='fa fa-user-o']"));
		Actions act=new Actions(driver);
		act.moveToElement(register).build().perform();
		driver.findElement(By.xpath("//span[contains(text(),'LOGIN / REGISTER')]")).click();
		login.sendUserName("madhaviboppana556@gmail.com");
		login.sendPassword("madhavi");
		login.clickLoginBtn();
	}
	
    @Test(priority=2, dependsOnMethods= {"Login"})
    public void selectProduct() throws InterruptedException
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
    @Test(priority=3, dependsOnMethods= {"selectProduct"})
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
       assertEquals(driver.getTitle(),"Checkout", "Check out not displayed");
       screenShot.captureScreenShot(" Checkout displayed");
       
     
    }
    
    @Test(priority=4, dependsOnMethods= {"checkOut"})
    public void placeOrder() throws InterruptedException
    {
    	driver.findElement(By.xpath("//input[@id='button-payment-address']")).click();
    	driver.findElement(By.xpath("//input[@id='button-shipping-address']")).click();
    	driver.findElement(By.xpath("//textarea[@name='comment']")).sendKeys("This Product is Nice");
    	 screenShot.captureScreenShot(" Comment");
    	 driver.findElement(By.xpath("//input[@id='button-shipping-method']")).click(); 
    	 driver.findElement(By.xpath("//input[@name='agree']")).click();
    	 driver.findElement(By.xpath("//input[@id='button-payment-method']")).click();
    	 driver.findElement(By.xpath("//input[@id='button-confirm']")).click();
    	 assertEquals("Your order has been placed!",driver.getTitle(),"Order cant be placed");
    	 screenShot.captureScreenShot("Order Placed");
    	 
    }
	
	@AfterTest
			
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}

}
