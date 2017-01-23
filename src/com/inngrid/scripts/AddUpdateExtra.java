package com.inngrid.scripts;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.inngrid.pagehelper.ResendEmployeeEmailHelper;
import com.inngrid.pagehelper.AddEmployeeHelper;
import com.inngrid.pagehelper.AddExtraHelper;
import com.inngrid.pagehelper.AddUpdateExtraHelper;
import com.inngrid.pagehelper.LoginHelper;
import com.inngrid.util.DriverTestCase;
import com.inngrid.util.ExecutionLog;

public class AddUpdateExtra extends DriverTestCase {
	@Test
	public void testLogin() throws Exception {
		// Initialize objects
		loginHelper = new LoginHelper(getWebDriver());
		addEmployeeHelper = new AddEmployeeHelper(getWebDriver());
		resendEmployeeEmailHelper = new ResendEmployeeEmailHelper(getWebDriver());
		addExtraHelper = new AddExtraHelper(getWebDriver());
		addUpdateExtraHelper = new AddUpdateExtraHelper(getWebDriver());

		// Var
		String firstname = "first" + getRandomInteger(1, 9999);
		String lastname = "last" + getRandomInteger(1, 9999);
		String email = "email" + getRandomInteger(1, 98987) + "@mailinator.com";
		String Employeename = firstname + " " + lastname;
		String extraname = "extra" + getRandomInteger(1, 999);

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

			// Click on Extras
			ExecutionLog.Log("Click on Extras");
			addExtraHelper.click("Extras");
			addExtraHelper.waitForWorkAroundTime(2000);
			ExecutionLog.Log("Passed");

			// Click on Create new Extra button
			ExecutionLog.Log("Click on Create new extra  button");
			addExtraHelper.click("CreateNewExtraBtn");
			addExtraHelper.waitForWorkAroundTime(2000);
			ExecutionLog.Log("Passed");

			// Enter Extra name
			ExecutionLog.Log("Enter Extra name");
			addExtraHelper.type("Extraname", extraname);
			addExtraHelper.waitForWorkAroundTime(2000);
			ExecutionLog.Log("Passed");

			// Select from Extra type dropdown
			ExecutionLog.Log("Select Item from extra type dropdown");
			addExtraHelper.selectDropdownText("selectExtraTypeDropdown", "Rental");
			addExtraHelper.waitForWorkAroundTime(3000);
			ExecutionLog.Log("Passed");

			// Select from Charging Scheme
			ExecutionLog.Log("Select from charging scheme dropdown");
			addExtraHelper.selectDropdownText("selectChargingschemeDropdown", "Once a day");
			addExtraHelper.waitForWorkAroundTime(3000);
			ExecutionLog.Log("Passed");

			// Select from charge type
			ExecutionLog.Log("Select from charging scheme dropdown");
			addExtraHelper.selectDropdownText("selectChargeTypeDropdown", "Room Charge(No TAX)");
			addExtraHelper.waitForWorkAroundTime(3000);
			ExecutionLog.Log("Passed");

			// Enter default rate
			ExecutionLog.Log("Enter default rate");
			addExtraHelper.type("DefaultRate", "999999");
			addExtraHelper.waitForWorkAroundTime(3000);
			ExecutionLog.Log("Passed");

			// Click on Update button
			ExecutionLog.Log("Click on update button");
			addExtraHelper.click("UpdateBtn");
			addExtraHelper.waitForWorkAroundTime(2000);
			ExecutionLog.Log("Passed");

			// Verify New Extra
			ExecutionLog.Log("Verify confirmation");
			addExtraHelper.verifyText("ExtraConfirmation", "Updated!");
			addExtraHelper.waitForWorkAroundTime(3000);
			ExecutionLog.Log("Passed");
			
			// Upadte Extra name
			ExecutionLog.Log("Enter updated name");
			addUpdateExtraHelper.type("UpdatedExtraname", "UpdatedName");
			addUpdateExtraHelper.waitForWorkAroundTime(3000);
			ExecutionLog.Log("Passed");
			
			// Update Extra type
			ExecutionLog.Log("Update Extra type");
			addUpdateExtraHelper.selectDropdownText("UpdatraExtraType", "Item");
			addUpdateExtraHelper.waitForWorkAroundTime(3000);
			ExecutionLog.Log("Passed");
			
			// Update Charging Scheme
			ExecutionLog.Log("Update Charging Scheme");
			addUpdateExtraHelper.selectDropdownText("UpdateChargingScheme", "On start date");
			addUpdateExtraHelper.waitForWorkAroundTime(3000);
			ExecutionLog.Log("Passed");
			
			// Update Charge type
			ExecutionLog.Log("Update Charge type");
			addUpdateExtraHelper.selectDropdownText("UpdateChargeType", "Services Charge");
			addEmployeeHelper.waitForWorkAroundTime(3000);
			ExecutionLog.Log("Passed");
			
			// Update default rate
			ExecutionLog.Log("Update default rate");
			addUpdateExtraHelper.type("UpdateDefaultRate", "11111");
			addEmployeeHelper.waitForWorkAroundTime(3000);
			ExecutionLog.Log("Passed");
			
			// Click on Upadte Extra button
			addUpdateExtraHelper.click("UpadteExtraBtn");
			addUpdateExtraHelper.waitForWorkAroundTime(3000);
			ExecutionLog.Log("Passed");
			
			// Verify Updated extra
			ExecutionLog.Log("Verify updated extra");
			addUpdateExtraHelper.verifyText("VerifyUpdatedExtra", "Updated!");
			addUpdateExtraHelper.waitForWorkAroundTime(3000);
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
