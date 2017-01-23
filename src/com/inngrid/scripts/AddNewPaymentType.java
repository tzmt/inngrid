package com.inngrid.scripts;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import com.inngrid.pagehelper.ResendEmployeeEmailHelper;
import com.inngrid.pagehelper.AddEmployeeHelper;
import com.inngrid.pagehelper.AddExtraHelper;
import com.inngrid.pagehelper.AddNewPaymentTypeHelper;
import com.inngrid.pagehelper.LoginHelper;
import com.inngrid.util.DriverTestCase;
import com.inngrid.util.ExecutionLog;

public class AddNewPaymentType extends DriverTestCase {
	@Test
	public void testLogin() throws Exception {
		// Initialize objects
		loginHelper = new LoginHelper(getWebDriver());
		addEmployeeHelper = new AddEmployeeHelper(getWebDriver());
		resendEmployeeEmailHelper = new ResendEmployeeEmailHelper(getWebDriver());
		addExtraHelper = new AddExtraHelper(getWebDriver());
		addNewPaymentTypeHelper = new AddNewPaymentTypeHelper(getWebDriver());

		// Var
		String firstname = "first" + getRandomInteger(1, 9999);
		String lastname = "last" + getRandomInteger(1, 9999);
		String email = "email" + getRandomInteger(1, 98987) + "@mailinator.com";
		String Employeename = firstname + " " + lastname;
		String extraname = "extra" + getRandomInteger(1, 999);
		
		String paymenttype = "Payment" + getRandomInteger(1, 999);

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
			loginHelper.waitForWorkAroundTime(3000);
			ExecutionLog.Log("Passed");

			// Click on SettingIcon
			ExecutionLog.Log("Click on Setting Icon");
			addEmployeeHelper.click("ClickSetting");
			addEmployeeHelper.waitForWorkAroundTime(3000);
			ExecutionLog.Log("Passed");

			// Click on Accounting tab
			ExecutionLog.Log("Click On Accounting Tab");
			addNewPaymentTypeHelper.click("ClickOnAccountingTab");
			addNewPaymentTypeHelper.waitForWorkAroundTime(3000);
			ExecutionLog.Log("Passed");

			// Click on Patyment type
			ExecutionLog.Log("Click on payment type");
			addNewPaymentTypeHelper.click("ClickOnPaymentType");
			addNewPaymentTypeHelper.waitForWorkAroundTime(3000);
			ExecutionLog.Log("PAssed");
			
			//Click on Add payment type button
			ExecutionLog.Log("Click on Add payment type button");
			addNewPaymentTypeHelper.click("ClickOnAddPaymentBtn");
			addNewPaymentTypeHelper.waitForWorkAroundTime(3000);
			ExecutionLog.Log("Passed");
			
			// Click on 'Click to edit'
			ExecutionLog.Log("Click to edit payment type");
			addNewPaymentTypeHelper.click("ClickToEdit");
			addNewPaymentTypeHelper.waitForWorkAroundTime(3000);
			ExecutionLog.Log("Passed");
			
			//Enter type of payment
		    ExecutionLog.Log("Enter payment type");
		    addNewPaymentTypeHelper.type("typePayment", paymenttype);
		    addNewPaymentTypeHelper.waitForWorkAroundTime(3000);
		    ExecutionLog.Log("Passed");
		    
		    //Click on ok button
		    ExecutionLog.Log("Click on Ok button");
		    addNewPaymentTypeHelper.ClickViaJavaScript("//button[text()='Ok']");
		    addNewPaymentTypeHelper.waitForWorkAroundTime(5000);
		    ExecutionLog.Log("Passed");
		    
		    // Verify payment type
		    ExecutionLog.Log("Verify payment type");
		    addNewPaymentTypeHelper.verifyTextPresent(paymenttype);
		    addNewPaymentTypeHelper.waitForWorkAroundTime(4000);
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
