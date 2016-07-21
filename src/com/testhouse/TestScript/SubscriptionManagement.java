package com.testhouse.TestScript;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.omg.CORBA.TIMEOUT;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import atu.testng.reports.ATUReports;
import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;
import atu.testng.reports.utils.Utils;
import automation_home.ddf.constants.ExcelConstants;
import automation_home.ddf.wrapper.Wrapper;
import automation_home.ddf.wrapperimpl.ExcelWrapper;

import com.testhouse.Functions.CustomerBillingFunctions;
import com.testhouse.Functions.CustomerServiceFunctions;
import com.testhouse.Functions.SubscriptionManagementFunctions;

/**
 * Class file to write the test scripts for the Subscription Management scenarios
 * @version 1.0
 * @author Testhouse
 *
 */
@Listeners({ ATUReportsListener.class, ConfigurationListener.class,	MethodListener.class })
public class SubscriptionManagement extends SubscriptionManagementFunctions 
{
	/* Defining object for driver */
	WebDriver driver;

	public static  List<WebDriver> drivers;

	/* Defining object for excel data read */
	Wrapper wrapper = new ExcelWrapper();	

	/* Constructor to fetch the drivers */
	public SubscriptionManagement()
	{
		SubscriptionManagement.drivers = new ArrayList<WebDriver>();
	}

	/* Setting ATU reports configuration file path */
	Properties props = loadProperties();
	{		
		Properties systemProperties = System.getProperties();
		systemProperties.setProperty("atu.reporter.config", System.getProperty("user.dir").concat(props.getProperty("atuPropertiesFilePath")));	
	}
	
	CustomerServiceFunctions csf = new CustomerServiceFunctions();
	
	/**
	 * Method to fetch the browser which needs to be launched during parallel execution 
	 * @param browser Name of the browser which needs to be executed
	 * @throws Exception
	 * 
	 */	
	@BeforeMethod(alwaysRun=true)
	@Parameters("browser")	
	public void setUp(String browser) throws Exception 
	{ 
		if(browser.equalsIgnoreCase("Firefox"))
		{
			driver = new FirefoxDriver();
			SubscriptionManagement.drivers.add(driver);
		}

		else if (browser.equalsIgnoreCase("Chrome"))
		{
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir").concat(props.getProperty("chromeDriverPath")));
			driver = new ChromeDriver();
			SubscriptionManagement.drivers.add(driver);
		}

		else if (browser.equalsIgnoreCase("IE"))
		{
			System.setProperty("webdriver.ie.driver",System.getProperty("user.dir").concat(props.getProperty("ieDriverPath")));
			driver = new InternetExplorerDriver();
			SubscriptionManagement.drivers.add(driver);
		}		

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);   
		ATUReports.setWebDriver(driver);
		ATUReports.indexPageDescription = "Dovetail Automation";
		ATUReports.currentRunDescription ="Subscription Management";
		ATUReports.setAuthorInfo("Automation Tester", Utils.getCurrentTime(),"1.0");
	}

	/**
	 * Method which is used to fetch the data from Excel
	 * @param method Object to identify the method which is currently using the Data provider
	 * @param context Object which is used to fetch the sheet name from Excel
	 * @return Will return the data values which has been identified from Excel
	 * @throws Exception
	 * 
	 */	
	@DataProvider
	public Object[][] databinding(Method method, ITestContext context) throws Exception 
	{ 				
		testName = method.getName();
		wrapper.setParameter(ExcelConstants.FILE_PATH, System.getProperty("user.dir").concat(props.getProperty("testDataFilePath")));
		wrapper.setParameter(ExcelConstants.SHEET_NAME, "Subscription Management");
		wrapper.setParameter(ExcelConstants.TESTCASE_NAME, testName);
		wrapper.setParameter(ExcelConstants.TESTCASE_START_ELEMENT, "_START");
		wrapper.setParameter(ExcelConstants.TESTCASE_END_ELEMENT, "_END");
		wrapper.setParameter(ExcelConstants.INCLUDE_TESTDATA_HEADER_NAME, "Execution");
		wrapper.setParameter(ExcelConstants.INCLUDE_TESTDATA_YES, "Run");
		wrapper.setParameter(ExcelConstants.INCLUDE_TESTDATA_NO, "No-Run");
		return wrapper.retrieveTestData();
	}
	
	
	
	/**
	 * Test to perform a new subscription via Subscription Management screen and to verify it in Customer Service screens
	 * @throws Exception 
	 * @param newSubscription function to create a new subscription
	 */
	@Test(priority=0, dataProvider="databinding")
	public void newSubscription(HashMap<String, String> h) throws Exception
	{
		ATUReports.setTestCaseReqCoverage("Creating a new subscription through Subscription Management");
		ATUReports.setAuthorInfo("Automation Tester", Utils.getCurrentTime(),"1.0");	

		/* Login Section */
		driver.get(props.getProperty("baseUrl"));
		driver.manage().window().maximize();
		TimeUnit.SECONDS.sleep(3);
		login(driver, h.get("Username"), h.get("Password"), testName);
		
		/*  Create a new subscription*/
		newSubscription(driver, h.get("Client"), h.get("Brand"), h.get("SubscriptionType"), h.get("Country"), h.get("PromotionName"), h.get("CardType"), h.get("Title"), h.get("FirstName"), h.get("SurName"), h.get("PostCode"), h.get("Address"), h.get("CustomerName"), h.get("CardNumber"), h.get("ExpiryMonth"), h.get("ExpiryYear"), h.get("AccountNumber"), h.get("SortCode"), h.get("DemographicProperty1"),h.get("DemographicProperty2"), h.get("DemographicProperty3"), h.get("NoOfCopies"));
		
		/*  Verify Restart Button*/
		verifyNextButton(driver, h.get("Client"), h.get("Brand"), h.get("SubscriptionType"));
		
		String orderRefnew = orderRef.toString();
		
		csf.orderRef = orderRefnew;
		
		/* Verify new subscription */
		csf.fetchDetailsCs(driver, h.get("Client"), h.get("Brand"));
		
		Assert.assertEquals(csf.accountID, orderRef);
		Assert.assertEquals(csf.verifyStatus, "ACTIVE");
		Assert.assertEquals(csf.verifyType, "STANDARD_SUBSCRIPTION");
		Assert.assertEquals(csf.payMethod, "CREDIT_CARD");
		Assert.assertEquals(csf.payStatus, "Paid");
		Assert.assertEquals(csf.renStatus, "");
		Assert.assertEquals(csf.subRole, "DIRECT");
		
	}
	
	/**
	 * Test to perform a new subscription via Subscription Management screen and to verify it in Customer Service screens
	 * @throws Exception 
	 * @param renewalSubscription function to create a renewal subscription
	 */
	@Test(priority=1, dataProvider="databinding")
	public void renewalSubscription(HashMap<String, String> h) throws Exception
	{
		ATUReports.setTestCaseReqCoverage("Creating a Renewal subscription through Subscription Management");
		ATUReports.setAuthorInfo("Automation Tester", Utils.getCurrentTime(),"1.0");	

		/* Login Section */
		driver.get(props.getProperty("baseUrl"));
		driver.manage().window().maximize();
		TimeUnit.SECONDS.sleep(3);
		login(driver, h.get("Username"), h.get("Password"), testName);
		
		/*  Create a new subscription*/
		renewalSubscription(driver, h.get("Client"), h.get("Brand"), h.get("SubscriptionType"), h.get("Country"), h.get("PromotionName"), h.get("CardType"), h.get("ReferenceNumber"), h.get("Title"), h.get("FirstName"), h.get("SurName"), h.get("PostCode"), h.get("Address"), h.get("CustomerName"), h.get("CardNumber"), h.get("ExpiryMonth"), h.get("ExpiryYear"), h.get("AccountNumber"), h.get("SortCode"), h.get("NoOfCopies"));
		
		/*  Verify Exit Button*/
		verifyExitButton(driver);
		
		String orderRefnew = orderRef.toString();
		csf.orderRef = orderRefnew;
		
		/* Verify new subscription */
		csf.fetchDetailsCs(driver, h.get("Client"), h.get("Brand"));
		
		Assert.assertEquals(csf.accountID, orderRef);
		Assert.assertEquals(csf.verifyStatus, "ACTIVE");
		Assert.assertEquals(csf.verifyType, "STANDARD_SUBSCRIPTION");
		Assert.assertEquals(csf.payMethod, "CREDIT_CARD");
		Assert.assertEquals(csf.payStatus, "Paid");
		Assert.assertEquals(csf.renStatus, "Renewed");
		Assert.assertEquals(csf.subRole, "DIRECT");
		
	}
	
	/**
	 * Test to perform a new subscription via Subscription Management screen and to verify it in Customer Service screens
	 * @throws Exception 
	 * @param productOnlySubscription function to create a new product only subscription
	 */
	@Test(priority=2, dataProvider="databinding")
	public void productOnlySubscription(HashMap<String, String> h) throws Exception
	{
		ATUReports.setTestCaseReqCoverage("Creating a new Product only subscription through Subscription Management");
		ATUReports.setAuthorInfo("Automation Tester", Utils.getCurrentTime(),"1.0");	

		/* Login Section */
		driver.get(props.getProperty("baseUrl"));
		driver.manage().window().maximize();
		TimeUnit.SECONDS.sleep(3);
		login(driver, h.get("Username"), h.get("Password"), testName);
		
		/*  Create a new subscription*/
		productOnlySubscription(driver, h.get("Client"), h.get("Brand"), h.get("SubscriptionType"), h.get("Country"), h.get("PromotionName"), h.get("CardType"), h.get("ProductName"), h.get("Quantity"), h.get("Title"), h.get("FirstName"), h.get("SurName"), h.get("PostCode"), h.get("Address"), h.get("CustomerName"), h.get("CardNumber"), h.get("ExpiryMonth"), h.get("ExpiryYear"), h.get("AccountNumber"), h.get("SortCode"));
		/*  Verify Restart Button*/
		verifyRestartButton(driver);

		String orderRefnew = orderRef.toString();
		
		csf.orderRef = orderRefnew;
		
		/* Verify new subscription */
				
		fetchDetails(driver, h.get("Client"), h.get("Brand"));

		String proId = element(driver, proAccountId).getText();
		String proSStatus = element(driver, proSubStatus).getText();
		String proSStype = element(driver, proSubType).getText();
		String proPStatus = element(driver, proPaystatus).getText();
		Assert.assertEquals(proId, orderRef);
		Assert.assertEquals(proSStatus, "COMPLETED");
		Assert.assertEquals(proSStype, "PRODUCT_ONLY_SUBSCRIPTION");
		Assert.assertEquals(proPStatus, "Paid");

	}
	
	/**
	 * Method which is used to quit all the browser instances after execution
	 * @throws Exception
	 * 
	 */
	@AfterMethod(alwaysRun=true)
	public void tearDown() throws Exception 
	{
		try
		{
		element(driver, logOut).click();	
		TimeUnit.SECONDS.sleep(2);
		}catch(Exception e)
		{
			
		}
		
		//driver.manage().deleteAllCookies();
		

		for(WebDriver d : drivers)
		{
			d.quit();
		}
	}
}
