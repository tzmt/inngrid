package com.inngrid.pagehelper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.inngrid.locators.LocatorReader;
import com.inngrid.util.DriverHelper;

public class AddNewPaymentTypeHelper extends DriverHelper 
{
	private LocatorReader readlocator;	

	
	public AddNewPaymentTypeHelper(WebDriver driver) 
	{
		super(driver);
		readlocator = new LocatorReader("AddNewPaymentType.xml");
	}
	
	public void selectDropdownText(String locator, String text) {
		 String xmlNode = readlocator.getLocator(locator);
		 selectDropDown(xmlNode, text);;

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

	public void verifyText(String locator, String text)
	{
		String xml = readlocator.getLocator(locator);
		isTextPresent(xml,text);
		
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

