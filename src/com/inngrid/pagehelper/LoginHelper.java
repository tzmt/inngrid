package com.inngrid.pagehelper;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.inngrid.locators.LocatorReader;
import com.inngrid.util.DriverHelper;

public class LoginHelper extends DriverHelper 
{
	private LocatorReader loginLocator;	

	
	public LoginHelper(WebDriver driver) 
	{
		super(driver);
		loginLocator = new LocatorReader("Login.xml");
	}


	public void type(String Field, String text) 
	{
		String locator = loginLocator.getLocator(Field);
		WaitForElementPresent(locator, 40);
		sendKeys(locator, text);
	}
	public void verifyHelp(String field)
	{
		String locator = loginLocator.getLocator(field);
		WaitForElementPresent(locator, 40);
		Assert.assertTrue(isElementPresent(locator));	
	}


	public void click(String button) 
	{
		String locator = loginLocator.getLocator(button);
		WaitForElementPresent(locator, 40);
		clickOn(locator);
	}


	public void verifyItem(String Field) 
	{
		String locator = loginLocator.getLocator(Field);
		WaitForElementPresent(locator, 40);
		Assert.assertTrue(isElementPresent(locator));
	}
     
 
}
