package com.inngrid.scripts;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.inngrid.pagehelper.AddEmployeeHelper;
import com.inngrid.pagehelper.LoginHelper;
import com.inngrid.util.DriverTestCase;
import com.inngrid.util.ExecutionLog;

public class AddEmployee extends DriverTestCase {
	@Test
	public void testLogin() throws Exception {
		//Initialize objects
		loginHelper = new LoginHelper(getWebDriver());
		addEmployeeHelper = new AddEmployeeHelper(getWebDriver());
		
		//var
		String firstname = "first" +getRandomInteger(1, 9999);
		String lastname = "last" +getRandomInteger(1, 9999);
		String email = "email"+getRandomInteger(1, 98987) + "@mailinator.com";
		String Employeename = firstname + " "+ lastname;
		
		ExecutionLog.LogAddClass(this.getClass().getName() + " and Test method "
				+ Thread.currentThread().getStackTrace()[1].getMethodName());
		try {

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
			
			//Click on SettingIcon
			ExecutionLog.Log("Click on Setting Icon");
		    addEmployeeHelper.click("ClickSetting");
		    addEmployeeHelper.waitForWorkAroundTime(3000);
		    ExecutionLog.Log("Passed");		    
		    
		    //Click on Employee
			ExecutionLog.Log("Click on Employee tab");
			addEmployeeHelper.click("ClickEmployees");
			ExecutionLog.Log("Passed");
			
			//Enter First name
			ExecutionLog.Log("User is able to Enter First Name");
			addEmployeeHelper.type("FirstName",firstname);
			addEmployeeHelper.waitForWorkAroundTime(2000);
			ExecutionLog.Log("Passed");
			
			//Enter last name
			ExecutionLog.Log("User is able to Enter First Name");
			addEmployeeHelper.type("LastName",lastname);
			addEmployeeHelper.waitForWorkAroundTime(2000);
			ExecutionLog.Log("Passed");
			
			//Enter Email
			ExecutionLog.Log("User is able to Enter First Name");
			addEmployeeHelper.type("Email",email);
		    addEmployeeHelper.waitForWorkAroundTime(2000);
			ExecutionLog.Log("Passed");
					
			//Click on Add Employee
			ExecutionLog.Log("Click on Add Employee button");
			addEmployeeHelper.click("AddEmployee");
			addEmployeeHelper.waitForWorkAroundTime(6000);
			ExecutionLog.Log("Passes");
		
			//Verify Added Employee
			System.out.println("emplyname i  "+Employeename);
			ExecutionLog.Log("Verify added Employee");
			addEmployeeHelper.verifyTextPresent(Employeename);
			ExecutionLog.Log("Passed");
			addEmployeeHelper.waitForWorkAroundTime(5000);
			

		}

		catch (Error e) {
			captureScreenshot("testLogin");
			ExecutionLog.LogErrorMessage(e);
			throw e;
		} catch (Exception e) {
			captureScreenshot("testLogin");
			ExecutionLog.LogExceptionMessage(e);
			throw e;
		}
	}

	@AfterMethod
	public void endMethods() throws Exception {
		ExecutionLog.LogEndClass(this.getClass().getName());
	}

}
