package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RecoverPasswordPOM {
	
	WebDriver driver;
	@FindBy(id="input-email")
	WebElement email;
	@FindBy(xpath="//input[@class='btn btn-primary']")
	WebElement contbutton;
	
	public RecoverPasswordPOM(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver,  this);
	}
	public void sendEmail(String mail)
	{
		email.sendKeys(mail);
	}
    public void clickContinue()
    {
    	contbutton.click();
    }
}
