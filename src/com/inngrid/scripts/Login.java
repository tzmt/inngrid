package com.inngrid.scripts;


import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.inngrid.pagehelper.LoginHelper;
import com.inngrid.util.DriverTestCase;
import com.inngrid.util.ExecutionLog;

public class Login extends DriverTestCase
{	
	@Test
	public void testLogin() throws Exception
	{			
		//Initialize objects
		loginHelper = new LoginHelper(getWebDriver());		
	    ExecutionLog.LogAddClass(this.getClass().getName() + " and Test method " +Thread.currentThread().getStackTrace()[1].getMethodName());
		try
		{
		         //Open application
		        System.out.println("Testing Application URL:");
				getWebDriver().navigate().to(application_url);
				ExecutionLog.Log("Open application url");
			
				//Enter UserName
				ExecutionLog.Log("User is able to Enter UserName");
				loginHelper.type("Email",username);
				ExecutionLog.Log("Passed");
				
				//Enter Password
				ExecutionLog.Log("User is able to Enter Password");
				loginHelper.type("Password",password);
				ExecutionLog.Log("Passed");
				
				//Click on Login button
				ExecutionLog.Log("user is able to click on login button");
				loginHelper.click("ClickLogin");
				ExecutionLog.Log("Passed");
		
				//wait
				loginHelper.waitForWorkAroundTime(3000);
				
				//Verify home page.
				ExecutionLog.Log("user is able to navigate to Dashboard page");
				loginHelper.verifyItem("VerifyTitle");
				ExecutionLog.Log("Passed");
				
				//wait
				loginHelper.waitForWorkAroundTime(3000);
				
		 }
		
		catch (Error e) 
		{
			captureScreenshot("testLogin");	
			ExecutionLog.LogErrorMessage(e);			
			throw e;
		} 
		catch(Exception e) {
			captureScreenshot("testLogin");
			ExecutionLog.LogExceptionMessage(e);			
			throw e;
		 }		
	}
	
	@AfterMethod
	public void endMethods() throws Exception
	{		
		ExecutionLog.LogEndClass(this.getClass().getName());
	}

}
