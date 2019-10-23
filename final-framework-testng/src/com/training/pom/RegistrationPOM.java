package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class RegistrationPOM {
	
	@FindBy(id="input-firstname")
	
	WebElement firstname;
	@FindBy(id="input-lastname")
	
	WebElement lastname;
	@FindBy(id="input-email")
	WebElement email;
	@FindBy(id="input-telephone")
	WebElement phone;
	@FindBy(id="input-address-1")
	WebElement address1;
	@FindBy(id="input-address-2")                
	WebElement address2;
	@FindBy(id="input-city")
	WebElement city;
	@FindBy(id="input-postcode")
	WebElement postcode;
	@FindBy(id="input-country")
    WebElement country;
	@FindBy(id="input-zone")
	WebElement region;
	@FindBy(id="input-password")
	WebElement password;
	@FindBy(id="input-confirm")
	WebElement confirm;
	@FindBy(xpath="//label[contains(text(),'No')]")
	WebElement subscribe;
	@FindBy(name="agree")
	WebElement agree;
	@FindBy(xpath="//input[@class='btn btn-primary']")
	WebElement contbutton;
    WebDriver driver;
	public RegistrationPOM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	public void firstName(String fn)
	{
	   firstname.sendKeys(fn);
	}
	public void lastName(String ln)
	{
		lastname.sendKeys(ln);
	}
	public void email(String mail)
	{
		email.sendKeys(mail);
	}
	public void telePhone(String no)
	{
		phone.sendKeys(no);
	}
	public void address1(String add1)
	{
		address1.sendKeys(add1);
	}
	public void address2(String add2)
	{
		address2.sendKeys(add2);
	}
	public void city(String ct)
	{
		city.sendKeys(ct);
	}
    public void postcode(String pc)
    {
    	postcode.sendKeys(pc);
    }
    public void country(String cn)
    {
    	Select sel1=new Select(country);
    	sel1.selectByVisibleText(cn);
    }
    public void region(String rg)
    {
    	Select sel2=new Select(region);
    	sel2.selectByVisibleText(rg);
    }
    public void password(String pw)
    {
    	password.sendKeys(pw);
    }
    public void confirmPassword(String cp)
    {
    	confirm.sendKeys(cp);
    }
    public void subscribe()
    {
    	subscribe.click();
    }
    public void conditions()
    {
    	agree.click();
    }
    public void clickContinue()
    {
    	contbutton.click();
    }
}
