package com.inngrid.pagehelper;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.inngrid.locators.LocatorReader;
import com.inngrid.util.DriverHelper;

public class ResendEmployeeEmailHelper extends DriverHelper 
{
	private LocatorReader readlocator;	

	
	public ResendEmployeeEmailHelper(WebDriver driver) 
	{
		super(driver);
		readlocator = new LocatorReader("ResendEmployeeEmail.xml");
	}


	public void type(String Field, String text) 
	{
		String locator = readlocator.getLocator(Field);
		WaitForElementPresent(locator, 40);
		sendKeys(locator, text);
	}
	public void verifyHelp(String field)
	{
		String locator = readlocator.getLocator(field);
		WaitForElementPresent(locator, 40);
		Assert.assertTrue(isElementPresent(locator));	
	}


	public void click(String button) 
	{
		String locator = readlocator.getLocator(button);
		WaitForElementPresent(locator, 40);
		clickOn(locator);
	}


	public void verifyItem(String Field) 
	{
		String locator = readlocator.getLocator(Field);
		WaitForElementPresent(locator, 40);
		Assert.assertTrue(isElementPresent(locator));
	}
	
	public void clickOnResendEmail(String empName)
	{
		String locator = "//table/tbody/tr[td[contains(text(),'"+empName+"')]]//div[text()='Re-send email']" ;
		WaitForElementPresent(locator, 40);
		clickOn(locator);
	}
     
	
}
