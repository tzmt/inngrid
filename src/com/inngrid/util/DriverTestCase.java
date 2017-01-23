package com.inngrid.util;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.inngrid.pagehelper.ResendEmployeeEmailHelper;
import com.inngrid.pagehelper.AddEmployeeHelper;
import com.inngrid.pagehelper.AddExtraHelper;
import com.inngrid.pagehelper.AddNewPaymentTypeHelper;
import com.inngrid.pagehelper.AddRatesHelper;
import com.inngrid.pagehelper.AddRoomTypeHelper;
import com.inngrid.pagehelper.AddUpdateExtraHelper;
import com.inngrid.pagehelper.EditRoomTypeHelper;
import com.inngrid.pagehelper.LoginHelper;

public abstract class DriverTestCase 
{

	enum DriverType 
	{ Firefox, IE, Chrome }

	//Define objects
	private WebDriver driver;
	protected LoginHelper loginHelper;  
	protected AddEmployeeHelper addEmployeeHelper;
	protected ResendEmployeeEmailHelper resendEmployeeEmailHelper;
	protected AddExtraHelper addExtraHelper;
	protected AddNewPaymentTypeHelper addNewPaymentTypeHelper;
	protected AddRatesHelper addRatesHelper;
	protected AddRoomTypeHelper addRoomTypeHelper;
	protected AddUpdateExtraHelper addUpdateExtraHelper;
	protected EditRoomTypeHelper editRoomTypeHelper;
	

	//Initialize objects
	protected PropertyReader propertyReader = new PropertyReader();

	//Define variables
	protected String application_url = propertyReader.readApplicationFile("URL");
	protected String create_company_url = propertyReader.readApplicationFile("create_url");
	protected String delete_company_url = propertyReader.readApplicationFile("delete_url");
	protected String enable_company_url = propertyReader.readApplicationFile("enable_url");
	protected String username = propertyReader.readApplicationFile("username");
	protected String password = propertyReader.readApplicationFile("pswd");
	
	@BeforeSuite
	public void setUp() 
	{  
		String driverType = propertyReader.readApplicationFile("BROWSER");  

		if (DriverType.Firefox.toString().equals(driverType)) 
		{ 
			driver = new FirefoxDriver();   
		}   

		else if (DriverType.IE.toString().equals(driverType)) 
		{ 
			String path1 = getPath();
			File file = new File(path1+"//IEDriverServer.exe");
			System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);   
			driver = new InternetExplorerDriver(capabilities); 
			//driver = new InternetExplorerDriver();
		}
		else if (DriverType.Chrome.toString().equals(driverType)) 
		{ 
			String path1 = getPath();
			String chromeDriverPath= path1+"\\chromedriver.exe";

			//Set the required properties to instantiate Chrome driver. Place any latest Chromedriver.exe files under Drivers folder
			System.setProperty("webdriver.chrome.driver", chromeDriverPath);
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized");

			driver = new ChromeDriver(options); } 
		else 
		{ driver = new FirefoxDriver(); }   

		//Maximize window
		driver.manage().window().maximize();

		//Delete cookies
		driver.manage().deleteAllCookies();   
		
	} 

	@AfterSuite
	public void afterMainMethod() 
	{  
		driver.quit();
	}

	public WebDriver getWebDriver()
	{
		return driver;
	}

	//Handle child windows
	public String switchPreviewWindow()
	{
		Set<String> windows = getWebDriver().getWindowHandles();
		Iterator<String> iter = windows.iterator();		
		String parent = iter.next();
		getWebDriver().switchTo().window(iter.next());
		return parent;
	}

	//Get random integer
	public int getRandomInteger(int aStart, int aEnd){
		Random aRandom = new  Random();
		if ( aStart > aEnd ) {
			throw new IllegalArgumentException("Start cannot exceed End.");
		}
		//get the range, casting to long to avoid overflow problems
		long range = (long)aEnd - (long)aStart + 1;
		// compute a fraction of the range, 0 <= frac < range
		long fraction = (long)(range * aRandom.nextDouble());
		int randomNumber =  (int)(fraction + aStart);    
		return randomNumber;
	}

	//Get random string
	public String randomString( int len ) 
	{
		String AB = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder( len );
		for( int i = 0; i < len; i++ ) 
			sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
		return sb.toString();
	}

	//Get absolute path
	public String getPath()
	{
		String path ="";		
		File file = new File("");
		String absolutePathOfFirstFile = file.getAbsolutePath();
		path = absolutePathOfFirstFile.replaceAll("\\\\+", "/");		
		return path;
	}

	//Get absolute path
	public String getPathUpload()
	{
		String path ="";		
		File file = new File("");
		String absolutePathOfFirstFile = file.getAbsolutePath();
		path = absolutePathOfFirstFile.replaceAll("/", "//");		
		return path;
	}

	//Switch frame
	public void switchFrame(String[] arr){
		for (int i = 0; i < arr.length; i++)
		{			
			getWebDriver().switchTo().frame(arr[i]);
		}
	}


	//capturing screenshot 
	public void captureScreenshot(String fileName) {
		try {
			String screenshotName = this.getFileName(fileName);
			FileOutputStream out = new FileOutputStream("screenshots//" + screenshotName + ".jpg");
			out.write(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
			out.close();
			String path = getPath();
			String  screen = "file://"+path+"/screenshots/"+screenshotName + ".jpg";
			Reporter.log("<a href= '"+screen+  "'target='_blank' >" + screenshotName + "</a>");
		} catch (Exception e) {

		}
	}

	//Creating file name
	public  String getFileName(String file){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();		 
		String fileName = file+dateFormat.format(cal.getTime());
		return fileName;
	}
	
	//Switch to reset password window
	
	public void moveOnNewWindow()
	{
		loginHelper.waitForWorkAroundTime(3000);
		getWebDriver().close();
		for (String winHandle : driver.getWindowHandles()) {
		    driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
		}
	}
	
    public void Login()
    {
    	loginHelper = new LoginHelper(getWebDriver());
    	ExecutionLog.Log("User is able to Enter UserName");
		loginHelper.type("Email",username);
		ExecutionLog.Log("Passed");
		
		//Enter Password
		ExecutionLog.Log("User is able to Enter Password");
		loginHelper.type("Password",password);
		ExecutionLog.Log("Passed");
		
		//Click on Login button
		loginHelper.click("ClickLogin");
		ExecutionLog.Log("user is able to click on login button");
		
		//Verify Title.
		loginHelper.verifyItem("VerifyTitle");
		ExecutionLog.Log("user is able to navigate to Dashboard page");
    }
	
	
	//Send Email through email
	public void SendGmail()
	{
		  final String username = "qa.aman.khan@gmail.com";
		    final String password = "eivor@eivor";
		   
		    PropertyReader property = new PropertyReader();
		    String path = property.getFilePath();
		    ExecutionLog executionLog = new ExecutionLog();
		    String Name = executionLog.getFileName();
		    Properties props = new Properties();
		    props.put("mail.smtp.auth", true);
		    props.put("mail.smtp.starttls.enable", true);
		    props.put("mail.smtp.host", "smtp.gmail.com");
		    props.put("mail.smtp.port", "587");

		    Session session = Session.getInstance(props,
		            new javax.mail.Authenticator() {
		                protected PasswordAuthentication getPasswordAuthentication() {
		                    return new PasswordAuthentication(username, password);
		                }
		            });

		    try {

		        Message message = new MimeMessage(session);
		        
		        message.setFrom(new InternetAddress("jaeyun@gmail.com"));

		        message.setFrom(new InternetAddress("jaeyun@gmail.com"));
		        message.setRecipients(Message.RecipientType.TO,
		                InternetAddress.parse("jaeyun@gmail.com"));
		        message.setSubject("Testing Subject");
		        message.setText("PFA");

		        MimeBodyPart messageBodyPart = new MimeBodyPart();

		        Multipart multipart = new MimeMultipart();

		        messageBodyPart = new MimeBodyPart();
		        String file = path + "//ExecutionLog//" + Name + ".txt";
		        String fileName = "attachmentName";
		        DataSource source = new FileDataSource(file);
		        messageBodyPart.setDataHandler(new DataHandler(source));
		        messageBodyPart.setFileName(fileName);
		        multipart.addBodyPart(messageBodyPart);

		        message.setContent(multipart);

		        System.out.println("Sending");

		        Transport.send(message);

		        System.out.println("Done");

		    } catch (MessagingException e) {
		        e.printStackTrace();
		    }
	}
	
	public static int getRandNumber()
	 {
	  Date d = new Date();
	  int Rand = Integer.parseInt(d.toString().replaceAll(" ", "").replaceAll(":","").substring(6, 14));
	  return Rand;
	 }
	
	}

	

