package com.inngrid.scripts;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.inngrid.pagehelper.ResendEmployeeEmailHelper;
import com.inngrid.pagehelper.AddEmployeeHelper;
import com.inngrid.pagehelper.AddExtraHelper;
import com.inngrid.pagehelper.AddRatesHelper;
import com.inngrid.pagehelper.LoginHelper;
import com.inngrid.util.DriverTestCase;
import com.inngrid.util.ExecutionLog;

public class AddRates extends DriverTestCase {
	@Test
	public void testLogin() throws Exception {
		// Initialize objects
		loginHelper = new LoginHelper(getWebDriver());
		addEmployeeHelper = new AddEmployeeHelper(getWebDriver());
		resendEmployeeEmailHelper = new ResendEmployeeEmailHelper(getWebDriver());
		addExtraHelper = new AddExtraHelper(getWebDriver());
		addRatesHelper = new AddRatesHelper(getWebDriver());

		// Var
		String firstname = "first" + getRandomInteger(1, 9999);
		String lastname = "last" + getRandomInteger(1, 9999);
		String email = "email" + getRandomInteger(1, 98987) + "@mailinator.com";
		String Employeename = firstname + " " + lastname;
		String extraname = "extra" + getRandomInteger(1, 999);
		String rateplan = "ratepaln" + getRandomInteger(1, 999);
		
		
		ExecutionLog.LogAddClass(this.getClass().getName() + " and Test method "
				+ Thread.currentThread().getStackTrace()[1].getMethodName());
		try {

			// Open application
			System.out.println("Testing Application URL:");
			getWebDriver().navigate().to(application_url);
			ExecutionLog.Log("Open application url");

			// Enter UserName
			ExecutionLog.Log("User is able to Enter UserName");
			loginHelper.type("Email", username);
			ExecutionLog.Log("Passed");

			// Enter Password
			ExecutionLog.Log("User is able to Enter Password");
			loginHelper.type("Password", password);
			ExecutionLog.Log("Passed");

			// Click on Login button
			ExecutionLog.Log("user is able to click on login button");
			loginHelper.click("ClickLogin");
			ExecutionLog.Log("Passed");

			// Click on SettingIcon
			ExecutionLog.Log("Click on Setting Icon");
			addEmployeeHelper.click("ClickSetting");
			addEmployeeHelper.waitForWorkAroundTime(3000);
			ExecutionLog.Log("Passed");

			// Click on Rates tab
			ExecutionLog.Log("Click on Rates tab");
			addExtraHelper.click("ClickOnRatesTab");
			addExtraHelper.waitForWorkAroundTime(3000);
			ExecutionLog.Log("Passed");

			// Click on Add Rate Plan
			ExecutionLog.Log("Click on Rate Paln");
			addRatesHelper.click("AddRatePlanBtn");
			addRatesHelper.waitForWorkAroundTime(5000);
			ExecutionLog.Log("Passed");
			
			// Enter Rate Plan
			ExecutionLog.Log("Enter rate plan name");
			addRatesHelper.type("RatePlanName", rateplan);
			addRatesHelper.waitForWorkAroundTime(3000);
			ExecutionLog.Log("Passed");
			
			// Select Room type from dropdown
			ExecutionLog.Log("Select room type from dropdown");
			addRatesHelper.selectDropdownText("SelectRoomType", "Double" );
			addRatesHelper.waitForWorkAroundTime(3000);
			ExecutionLog.Log("Passed");
			
			// Select Room charge from dropdown 
			ExecutionLog.Log("Select room charge from dropdown");
			addRatesHelper.selectDropdownText("SelectRoomCharge", "Services Charge");
			addRatesHelper.waitForWorkAroundTime(3000);
			ExecutionLog.Log("Passed");
			
			// Select USD from dropdown
			ExecutionLog.Log("Select USD from dropdown");
			addRatesHelper.selectDropdownText("SelectUSD", "INR (Indian Rupee)");
			addRatesHelper.waitForWorkAroundTime(3000);
			ExecutionLog.Log("Passed");
			
			// Select Display On Off
			ExecutionLog.Log("Select Online booking engine");
			addRatesHelper.selectDropdownText("OnlineBookingEngine", "Do not display on Online Booking Engine");
			addRatesHelper.waitForWorkAroundTime(3000);
			ExecutionLog.Log("Passed");
			
			// Enter description
			ExecutionLog.Log("Enter Description");
			addRatesHelper.type("EnterDescription", "Test Descrition");
			addRatesHelper.waitForWorkAroundTime(3000);
			ExecutionLog.Log("Passed");
			
			//Click on Update plan button
			ExecutionLog.Log("Click on Update Paln button");
			addRatesHelper.click("UpdateRatePlanBtn");
			addRatesHelper.waitForWorkAroundTime(2000);
			ExecutionLog.Log("Passed");
			
			// Verify Rate plan
			ExecutionLog.Log("Verify rate plan");
			addRatesHelper.verifyText("RatePlanConfirmation", "Updated!");
			addRatesHelper.waitForWorkAroundTime(3000);
			ExecutionLog.Log("Passed");

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
