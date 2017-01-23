package com.inngrid.scripts;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.inngrid.pagehelper.ResendEmployeeEmailHelper;
import com.inngrid.pagehelper.AddEmployeeHelper;
import com.inngrid.pagehelper.AddExtraHelper;
import com.inngrid.pagehelper.AddRatesHelper;
import com.inngrid.pagehelper.AddRoomTypeHelper;
import com.inngrid.pagehelper.LoginHelper;
import com.inngrid.util.DriverTestCase;
import com.inngrid.util.ExecutionLog;

public class AddRoomType extends DriverTestCase {
	@Test
	public void testLogin() throws Exception {
		// Initialize objects
		loginHelper = new LoginHelper(getWebDriver());
		addEmployeeHelper = new AddEmployeeHelper(getWebDriver());
		resendEmployeeEmailHelper = new ResendEmployeeEmailHelper(getWebDriver());
		addExtraHelper = new AddExtraHelper(getWebDriver());
		addRatesHelper = new AddRatesHelper(getWebDriver());
		addRoomTypeHelper = new AddRoomTypeHelper(getWebDriver());

		// Var
		String firstname = "first" + getRandomInteger(1, 9999);
		String lastname = "last" + getRandomInteger(1, 9999);
		String email = "email" + getRandomInteger(1, 98987) + "@mailinator.com";
		String Employeename = firstname + " " + lastname;
		String extraname = "extra" + getRandomInteger(1, 999);
		String rateplan = "ratepaln" + getRandomInteger(1, 999);
		String roomtypename = "roomtype" + getRandomInteger(1, 999);

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

			// Click on Rooms tab
			ExecutionLog.Log("Click on Rooms tab");
			addRoomTypeHelper.click("RoomTab");
			addRoomTypeHelper.waitForWorkAroundTime(3000);
			ExecutionLog.Log("Passed");
			
			// Click on Room type
			ExecutionLog.Log("Click on room type tab");
			addRoomTypeHelper.click("RoomTypes");
			addRoomTypeHelper.waitForWorkAroundTime(3000);
			ExecutionLog.Log("Passed");
			
			// Click on Add room type button
			ExecutionLog.Log("Click on room type button");
			addRoomTypeHelper.click("AddRoomTypeBtn");
			addRoomTypeHelper.waitForWorkAroundTime(3000);
			ExecutionLog.Log("Passed");
			
			// Enter Room type name
			ExecutionLog.Log("Enter room type name");
			addRoomTypeHelper.type("RoomTypeName", roomtypename);
			addRoomTypeHelper.waitForWorkAroundTime(3000);
			ExecutionLog.Log("Passed");
			
			// Enter Acronym name
			ExecutionLog.Log("Enter acronym");
			addRoomTypeHelper.type("AcronymName" , "RT");
			addRoomTypeHelper.waitForWorkAroundTime(3000);
			ExecutionLog.Log("Passed");
			
			
			// Choose adults capacity
			ExecutionLog.Log("Choose adults capacity from dropdown");
			addRoomTypeHelper.selectDropdownText("ChooseAdults", "2 adults");
			addRoomTypeHelper.waitForWorkAroundTime(3000);
			ExecutionLog.Log("Passed");
			
			// Choose Children capacity
			ExecutionLog.Log("Choose Children capacity");
			addRoomTypeHelper.selectDropdownText("ChooseChildren", "2 children");
			addRoomTypeHelper.waitForWorkAroundTime(3000);
			ExecutionLog.Log("Passed");
			
			// Choose show on website
			ExecutionLog.Log("Choose show on website");
			addRoomTypeHelper.selectDropdownText("ShowOnWebSite", "Yes");
			addRoomTypeHelper.waitForWorkAroundTime(3000);
			ExecutionLog.Log("Passed");
			
			// Enter description
			ExecutionLog.Log("Enter description");
			addRoomTypeHelper.type("EnterDescription" , "Test Description");
			addRoomTypeHelper.waitForWorkAroundTime(3000);
			ExecutionLog.Log("Passed");
			
			// Click on Update roomtype button
			ExecutionLog.Log("User is able to click on Update room type button");
			addRoomTypeHelper.click("UpdateRoomTypeBtn");
			addRoomTypeHelper.waitForWorkAroundTime(3000);
			ExecutionLog.Log("Passed");
			
			// Verify Added room type
			ExecutionLog.Log("Verify added room type");
			addRoomTypeHelper.verifyText("VerifyRoomType", "Updated!");
			addRoomTypeHelper.waitForWorkAroundTime(4000);
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
