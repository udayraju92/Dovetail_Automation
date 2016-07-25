package com.testhouse.TestScript;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
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

import com.testhouse.Functions.CustomerServiceFunctions;
import com.testhouse.Functions.CustomerBillingFunctions;

/**
 * Class file to write the test scripts for the Customer Service scenarios
 * @version 1.0
 * @author Testhouse
 *
 */
@Listeners({ ATUReportsListener.class, ConfigurationListener.class,	MethodListener.class })
public class CustomerServices extends CustomerServiceFunctions
{
	/* Defining object for driver */
	WebDriver driver;

	public static  List<WebDriver> drivers;

	/* Defining object for excel data read */
	Wrapper wrapper = new ExcelWrapper();	

	/* Constructor to fetch the drivers */
	public CustomerServices()
	{
		CustomerServices.drivers = new ArrayList<WebDriver>();
	}

	/* Setting ATU reports configuration file path */
	Properties props = loadProperties();
	{		
		Properties systemProperties = System.getProperties();
		systemProperties.setProperty("atu.reporter.config", System.getProperty("user.dir").concat(props.getProperty("atuPropertiesFilePath")));	
	}
	CustomerBillingFunctions cbf = new CustomerBillingFunctions();
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
			CustomerServices.drivers.add(driver);
		}

		else if (browser.equalsIgnoreCase("Chrome"))
		{
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir").concat(props.getProperty("chromeDriverPath")));
			driver = new ChromeDriver();
			CustomerServices.drivers.add(driver);
		}

		else if (browser.equalsIgnoreCase("IE"))
		{
			System.setProperty("webdriver.ie.driver",System.getProperty("user.dir").concat(props.getProperty("ieDriverPath")));
			driver = new InternetExplorerDriver();
			CustomerServices.drivers.add(driver);
		}		

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);   
		ATUReports.setWebDriver(driver);
		ATUReports.indexPageDescription = "Dovetail Automation";
		ATUReports.currentRunDescription ="Customer Service";
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
		wrapper.setParameter(ExcelConstants.SHEET_NAME, "Customer Service");
		wrapper.setParameter(ExcelConstants.TESTCASE_NAME, testName);
		wrapper.setParameter(ExcelConstants.TESTCASE_START_ELEMENT, "_START");
		wrapper.setParameter(ExcelConstants.TESTCASE_END_ELEMENT, "_END");
		wrapper.setParameter(ExcelConstants.INCLUDE_TESTDATA_HEADER_NAME, "Execution");
		wrapper.setParameter(ExcelConstants.INCLUDE_TESTDATA_YES, "Run");
		wrapper.setParameter(ExcelConstants.INCLUDE_TESTDATA_NO, "No-Run");
		return wrapper.retrieveTestData();
	}

	/**
	 * Test to perform a new subscription via Customer Service screen and to verify it in CS and Customer Billing screens
	 * @param Type1:Customer service_UK Personal Subscription Issuebased_Optimistic_Single stage_CC;  Renewal Strategy : Single stage;  Schedule:Direct & Optimistic; Offer: Issue based; Payment method: Credit Card
	 * @throws Exception 
	 * @param newSubscription function to create a new subscription
	 * @param verifyNewSubscription function verify newly created subscription in CS screen
	 * @param cbf.verifyNewSubscriptionCB function to verify newly created subscription in Customer Billing screen
	 */
	//@Test(priority=0, dataProvider="databinding")
	public void customerServiceType1(HashMap<String, String> h) throws Exception
	{
		ATUReports.setTestCaseReqCoverage("Creating a new subscription through Customer service screen");
		ATUReports.setAuthorInfo("Automation Tester", Utils.getCurrentTime(),"1.0");	

		/* Login Section */
		driver.get(props.getProperty("baseUrl"));
		driver.manage().window().maximize();
		TimeUnit.SECONDS.sleep(3);
		login(driver, h.get("Username"), h.get("Password"), testName);

		/* Creating a new subscription */		
		newSubscriptionCCType1(driver, h.get("Client"), h.get("Brand"), h.get("PromotionName"), h.get("Card"), h.get("Title"), h.get("Firstname"), h.get("Surname"), h.get("Postcode"), h.get("Address"), h.get("CustomerName"), h.get("Card1"), h.get("ExpiryDate"), h.get("ExpiryYear"));

		/* Verify the newly created subscription in Customer Service Screen*/
		verifyNewSubscriptionCSType1(driver, h.get("PromotionName"), h.get("Client"), h.get("Brand"));

		/* Verify the newly created subscription in Customer Billing Screen*/
		cbf.verifyNewSubscriptionCBType1(driver);
		TimeUnit.SECONDS.sleep(3);

	}

	/**
	 * Test to perform a new subscription via Customer Service screen and to verify it in CS and Customer Billing screens
	 * @param Type2: Customer service_UK Personal Subscription Issuebased_Optimistic_Single stage_DD; Renewal Strategy : Single stage;  Schedule:Direct & Optimistic; Offer: Issue based; Payment method: Direct Debit
	 * @throws Exception 
	 * @param newSubscription function to create a new subscription
	 * @param verifyNewSubscription function verify newly created subscription in CS screen
	 * @param cbf.verifyNewSubscriptionCB function to verify newly created subscription in Customer Billing screen
	 */
	//@Test(priority=1, dataProvider="databinding")
	public void customerServiceType2(HashMap<String, String> h) throws Exception
	{
		ATUReports.setTestCaseReqCoverage("Creating a new subscription through Customer service screen");
		ATUReports.setAuthorInfo("Automation Tester", Utils.getCurrentTime(),"1.0");	

		/* Login Section */
		driver.get(props.getProperty("baseUrl"));
		driver.manage().window().maximize();
		TimeUnit.SECONDS.sleep(3);
		login(driver, h.get("Username"), h.get("Password"), testName);

		/* Creating a new subscription */		
		newSubscriptionDD(driver, h.get("Client"), h.get("Brand"), h.get("PromotionName"), h.get("Card"), h.get("Title"), h.get("Firstname"), h.get("Surname"), h.get("Postcode"), h.get("Address"), h.get("AccHolderName"), h.get("AccNumber"), h.get("SortCode"));

		/* Verify the newly created subscription in Customer Service Screen*/
		verifyNewSubscriptionCSType2(driver, h.get("PromotionName"), h.get("Client"), h.get("Brand"));

		/* Verify the newly created subscription in Customer Billing Screen*/
		cbf.verifyNewSubscriptionCBType2(driver);
		TimeUnit.SECONDS.sleep(3);

	}

	/**
	 * Test to perform a new subscription via Customer Service screen and to verify it in CS and Customer Billing screens
	 * @param Type3: Customer service_UK Gift Subscription Issuebased_Optimistic_Single stage_CC; Renewal Strategy : Single stage;  Schedule:Direct & Optimistic; Offer: Issue based; Payment method: Credit Card
	 * @throws Exception 
	 * @param newSubscription function to create a new subscription
	 * @param verifyNewSubscription function verify newly created subscription in CS screen
	 * @param cbf.verifyNewSubscriptionCB function to verify newly created subscription in Customer Billing screen
	 */
	//@Test(priority=2, dataProvider="databinding")
	public void customerServiceType3(HashMap<String, String> h) throws Exception
	{
		ATUReports.setTestCaseReqCoverage("Creating a new subscription through Customer service screen");
		ATUReports.setAuthorInfo("Automation Tester", Utils.getCurrentTime(),"1.0");	

		/* Login Section */
		driver.get(props.getProperty("baseUrl"));
		driver.manage().window().maximize();
		TimeUnit.SECONDS.sleep(3);
		login(driver, h.get("Username"), h.get("Password"), testName);

		/* Creating a new subscription */		
		newSubscriptionCCGiftType3(driver, h.get("Client"), h.get("Brand"), h.get("PromotionName"), h.get("Card"), h.get("Title"), h.get("Firstname"), h.get("Surname"), h.get("Postcode"), h.get("Address"), h.get("GTitle"), h.get("GFirstname"), h.get("GSurname"), h.get("GPostcode"), h.get("GAddress"),h.get("CustomerName"), h.get("Card1"), h.get("ExpiryDate"), h.get("ExpiryYear"));

		/* Verify the newly created subscription in Customer Service Screen*/
		verifyNewSubscriptionCSType3(driver, h.get("PromotionName"), h.get("Client"), h.get("Brand"));

		/* Verify the newly created subscription in Customer Billing Screen*/
		cbf.verifyNewSubscriptionCBType3(driver);
		TimeUnit.SECONDS.sleep(3);

	}

	/**
	 * Test to perform a new subscription via Customer Service screen and to verify it in CS and Customer Billing screens
	 * @param Type3:Customer Service_UK Personal subscription_Not Optimistic_Singlestage_CC; Renewal strategy: Singlestage; Offer:Product only; Payment method: Credit Card; Schedule: Direct & Not Optimistic
	 * @throws Exception 
	 * @param newSubscription function to create a new subscription
	 * @param verifyNewSubscription function verify newly created subscription in CS screen
	 * @param cbf.verifyNewSubscriptionCB function to verify newly created subscription in Customer Billing screen
	 */

	@Test(priority=3, dataProvider="databinding")
	public void customerServiceType4(HashMap<String, String> h) throws Exception
	{
		ATUReports.setTestCaseReqCoverage("Creating a new subscription through Customer service screen");
		ATUReports.setAuthorInfo("Automation Tester", Utils.getCurrentTime(),"1.0");	

		// Login Section 
		driver.get(props.getProperty("baseUrl"));
		driver.manage().window().maximize();
		TimeUnit.SECONDS.sleep(3);
		login(driver, h.get("Username"), h.get("Password"), testName);

		//Creating a new subscription 		
		newSubscriptionCCType4(driver, h.get("Client"), h.get("Brand"), h.get("PromotionName"), h.get("Product"), h.get("Card"), h.get("Title"), h.get("Firstname"), h.get("Surname"), h.get("Postcode"), h.get("Address"), h.get("CustomerName"), h.get("Card1"), h.get("ExpiryDate"), h.get("ExpiryYear"));

		// Verify the newly created subscription in Customer Service Screen
		verifyNewSubscriptionCSType4(driver, h.get("PromotionName"), h.get("Client"), h.get("Brand"));

		// Verify the newly created subscription in Customer Billing Screen
		cbf.verifyNewSubscriptionCBType4(driver);
		TimeUnit.SECONDS.sleep(3);
	}

	/**
	 * Test to perform a new subscription via Customer Service screen and to verify it in CS and Customer Billing screens
	 * @throws Exception 
	 * @param newSubscription function to create a new subscription
	 * @param verifyNewSubscription function verify newly created subscription in CS screen
	 * @param cbf.verifyNewSubscriptionCB function to verify newly created subscription in Customer Billing screen
	 */
	//@Test(priority=4, dataProvider="databinding")
	public void customerServiceTypeSearch_CustomerReference(HashMap<String, String> h) throws Exception
	{
		ATUReports.setTestCaseReqCoverage("Searching new subscription through Customer service screen");
		ATUReports.setAuthorInfo("Automation Tester", Utils.getCurrentTime(),"1.0");	

		/* Login Section */
		driver.get(props.getProperty("baseUrl"));
		driver.manage().window().maximize();
		TimeUnit.SECONDS.sleep(3);
		login(driver, h.get("Username"), h.get("Password"), testName);

		/* Creating a new subscription */		
		searchCs_CustomerReference(driver, h.get("Client"), h.get("Brand"), h.get("CustomerReference"));
		TimeUnit.SECONDS.sleep(3);
	}

	
	/**
	 * Test to perform a new subscription via Customer Service screen and to verify it in CS and Customer Billing screens
	 * @throws Exception 
	 * @param newSubscription function to create a new subscription
	 * @param verifyNewSubscription function verify newly created subscription in CS screen
	 * @param cbf.verifyNewSubscriptionCB function to verify newly created subscription in Customer Billing screen
	 */
	//@Test(priority=4, dataProvider="databinding")
	public void customerServiceTypeSearch_OrderReference(HashMap<String, String> h) throws Exception
	{
		ATUReports.setTestCaseReqCoverage("Searching new subscription through Customer service screen");
		ATUReports.setAuthorInfo("Automation Tester", Utils.getCurrentTime(),"1.0");	

		/* Login Section */
		driver.get(props.getProperty("baseUrl"));
		driver.manage().window().maximize();
		TimeUnit.SECONDS.sleep(3);
		login(driver, h.get("Username"), h.get("Password"), testName);

		/* Creating a new subscription */		
		searchCs_OrderReference(driver, h.get("Client"), h.get("Brand"), h.get("CustomerReference"), h.get("OrderReferenceNumber"));
		TimeUnit.SECONDS.sleep(3);
	}
	
	
	/**
	 * Test to perform a new subscription via Customer Service screen and to verify it in CS and Customer Billing screens
	 * @throws Exception 
	 * @param newSubscription function to create a new subscription
	 * @param verifyNewSubscription function verify newly created subscription in CS screen
	 * @param cbf.verifyNewSubscriptionCB function to verify newly created subscription in Customer Billing screen
	 */
	//@Test(priority=4, dataProvider="databinding")
	public void customerServiceTypeSearch_PrevSubscriptionreference(HashMap<String, String> h) throws Exception
	{
		ATUReports.setTestCaseReqCoverage("Searching new subscription through Customer service screen");
		ATUReports.setAuthorInfo("Automation Tester", Utils.getCurrentTime(),"1.0");	

		/* Login Section */
		driver.get(props.getProperty("baseUrl"));
		driver.manage().window().maximize();
		TimeUnit.SECONDS.sleep(3);
		login(driver, h.get("Username"), h.get("Password"), testName);

		/* Creating a new subscription */		
		searchCs_PreviousSubscriptionReference(driver, h.get("Client"), h.get("Brand"), h.get("CustomerReference"), h.get("PrevSubscriptionReference"));
		TimeUnit.SECONDS.sleep(3);
	}

	/**
	 * Test to perform a new subscription via Customer Service screen and to verify it in CS and Customer Billing screens
	 * @throws Exception 
	 * @param newSubscription function to create a new subscription
	 * @param verifyNewSubscription function verify newly created subscription in CS screen
	 * @param cbf.verifyNewSubscriptionCB function to verify newly created subscription in Customer Billing screen
	 */
	//@Test(priority=4, dataProvider="databinding")
	public void customerServiceTypeSearch_AddressLine(HashMap<String, String> h) throws Exception
	{
		ATUReports.setTestCaseReqCoverage("Searching new subscription through Customer service screen");
		ATUReports.setAuthorInfo("Automation Tester", Utils.getCurrentTime(),"1.0");	

		/* Login Section */
		driver.get(props.getProperty("baseUrl"));
		driver.manage().window().maximize();
		TimeUnit.SECONDS.sleep(3);
		login(driver, h.get("Username"), h.get("Password"), testName);

		/* Creating a new subscription */		
		searchCs_AddressLine(driver, h.get("Client"), h.get("Brand"), h.get("AddressLine"));
		TimeUnit.SECONDS.sleep(3);
	}
	
	/**
	 * Test to perform a new subscription via Customer Service screen and to verify it in CS and Customer Billing screens
	 * @throws Exception 
	 * @param newSubscription function to create a new subscription
	 * @param verifyNewSubscription function verify newly created subscription in CS screen
	 * @param cbf.verifyNewSubscriptionCB function to verify newly created subscription in Customer Billing screen
	 */
	//@Test(priority=4, dataProvider="databinding")
	public void customerServiceTypeSearch_PostCode(HashMap<String, String> h) throws Exception
	{
		ATUReports.setTestCaseReqCoverage("Searching new subscription through Customer service screen");
		ATUReports.setAuthorInfo("Automation Tester", Utils.getCurrentTime(),"1.0");	

		/* Login Section */
		driver.get(props.getProperty("baseUrl"));
		driver.manage().window().maximize();
		TimeUnit.SECONDS.sleep(3);
		login(driver, h.get("Username"), h.get("Password"), testName);

		/* Creating a new subscription */		
		searchCs_PostCode(driver, h.get("Client"), h.get("Brand"), h.get("CustomerReference"), h.get("PostCodeS"));
		TimeUnit.SECONDS.sleep(3);
	}
	
	/**
	 * Test to perform a new subscription via Customer Service screen and to verify it in CS and Customer Billing screens
	 * @throws Exception 
	 * @param newSubscription function to create a new subscription
	 * @param verifyNewSubscription function verify newly created subscription in CS screen
	 * @param cbf.verifyNewSubscriptionCB function to verify newly created subscription in Customer Billing screen
	 */
	//@Test(priority=4, dataProvider="databinding")
	public void customerServiceTypeSearch_CompanyName(HashMap<String, String> h) throws Exception
	{
		ATUReports.setTestCaseReqCoverage("Searching new subscription through Customer service screen");
		ATUReports.setAuthorInfo("Automation Tester", Utils.getCurrentTime(),"1.0");	

		/* Login Section */
		driver.get(props.getProperty("baseUrl"));
		driver.manage().window().maximize();
		TimeUnit.SECONDS.sleep(3);
		login(driver, h.get("Username"), h.get("Password"), testName);

		/* Creating a new subscription */		
		searchCs_CompanyName(driver, h.get("Client"), h.get("Brand"), h.get("CustomerReference"), h.get("CompanyName"));
		TimeUnit.SECONDS.sleep(3);
	}
	
	/**
	 * Test to perform a new subscription via Customer Service screen and to verify it in CS and Customer Billing screens
	 * @throws Exception 
	 * @param newSubscription function to create a new subscription
	 * @param verifyNewSubscription function verify newly created subscription in CS screen
	 * @param cbf.verifyNewSubscriptionCB function to verify newly created subscription in Customer Billing screen
	 */
	//@Test(priority=4, dataProvider="databinding")
	public void customerServiceTypeSearch_LastName(HashMap<String, String> h) throws Exception
	{
		ATUReports.setTestCaseReqCoverage("Searching new subscription through Customer service screen");
		ATUReports.setAuthorInfo("Automation Tester", Utils.getCurrentTime(),"1.0");	

		/* Login Section */
		driver.get(props.getProperty("baseUrl"));
		driver.manage().window().maximize();
		TimeUnit.SECONDS.sleep(3);
		login(driver, h.get("Username"), h.get("Password"), testName);

		/* Creating a new subscription */		
		searchCs_LastName(driver, h.get("Client"), h.get("Brand"), h.get("CustomerReference"), h.get("LastName"));
		TimeUnit.SECONDS.sleep(3);
	}
	
	/**
	 * Test to perform a new subscription via Customer Service screen and to verify it in CS and Customer Billing screens
	 * @throws Exception 
	 * @param newSubscription function to create a new subscription
	 * @param verifyNewSubscription function verify newly created subscription in CS screen
	 * @param cbf.verifyNewSubscriptionCB function to verify newly created subscription in Customer Billing screen
	 */
	//@Test(priority=4, dataProvider="databinding")
	public void customerServiceTypeSearch_FirstName(HashMap<String, String> h) throws Exception
	{
		ATUReports.setTestCaseReqCoverage("Searching new subscription through Customer service screen");
		ATUReports.setAuthorInfo("Automation Tester", Utils.getCurrentTime(),"1.0");	

		/* Login Section */
		driver.get(props.getProperty("baseUrl"));
		driver.manage().window().maximize();
		TimeUnit.SECONDS.sleep(3);
		login(driver, h.get("Username"), h.get("Password"), testName);

		/* Creating a new subscription */		
		searchCs_FirstName(driver, h.get("Client"), h.get("Brand"), h.get("CustomerReference"), h.get("FirstName"));
		TimeUnit.SECONDS.sleep(3);
	}
	
	/**
	 * Test to perform a new subscription via Customer Service screen and to verify it in CS and Customer Billing screens
	 * @throws Exception 
	 * @param newSubscription function to create a new subscription
	 * @param verifyNewSubscription function verify newly created subscription in CS screen
	 * @param cbf.verifyNewSubscriptionCB function to verify newly created subscription in Customer Billing screen
	 */
	//@Test(priority=4, dataProvider="databinding")
	public void customerServiceTypeSearch_Email(HashMap<String, String> h) throws Exception
	{
		ATUReports.setTestCaseReqCoverage("Searching new subscription through Customer service screen");
		ATUReports.setAuthorInfo("Automation Tester", Utils.getCurrentTime(),"1.0");	

		/* Login Section */
		driver.get(props.getProperty("baseUrl"));
		driver.manage().window().maximize();
		TimeUnit.SECONDS.sleep(3);
		login(driver, h.get("Username"), h.get("Password"), testName);

		/* Creating a new subscription */		
		searchCs_Email(driver, h.get("Client"), h.get("Brand"), h.get("CustomerReference"), h.get("Email"));
		TimeUnit.SECONDS.sleep(3);
	}
	
	
	/**
	 * Test to perform a new subscription via Customer Service screen and to verify it in CS and Customer Billing screens
	 * @throws Exception 
	 * @param newSubscription function to create a new subscription
	 * @param verifyNewSubscription function verify newly created subscription in CS screen
	 * @param cbf.verifyNewSubscriptionCB function to verify newly created subscription in Customer Billing screen
	 */
	//@Test(priority=4, dataProvider="databinding")
	public void customerServiceTypeSearch_WebID(HashMap<String, String> h) throws Exception
	{
		ATUReports.setTestCaseReqCoverage("Searching new subscription through Customer service screen");
		ATUReports.setAuthorInfo("Automation Tester", Utils.getCurrentTime(),"1.0");	

		/* Login Section */
		driver.get(props.getProperty("baseUrl"));
		driver.manage().window().maximize();
		TimeUnit.SECONDS.sleep(3);
		login(driver, h.get("Username"), h.get("Password"), testName);

		/* Creating a new subscription */		
		searchCs_WebID(driver, h.get("Client"), h.get("Brand"), h.get("CustomerReference"), h.get("WebID"));
		TimeUnit.SECONDS.sleep(3);
	}
	
	/**
	 * Test to perform a new subscription via Customer Service screen and to verify it in CS and Customer Billing screens
	 * @throws Exception 
	 * @param newSubscription function to create a new subscription
	 * @param verifyNewSubscription function verify newly created subscription in CS screen
	 * @param cbf.verifyNewSubscriptionCB function to verify newly created subscription in Customer Billing screen
	 */
	//@Test(priority=4, dataProvider="databinding")
	public void customerServiceTypeSearch_AccountNumber(HashMap<String, String> h) throws Exception
	{
		ATUReports.setTestCaseReqCoverage("Searching new subscription through Customer service screen");
		ATUReports.setAuthorInfo("Automation Tester", Utils.getCurrentTime(),"1.0");	

		/* Login Section */
		driver.get(props.getProperty("baseUrl"));
		driver.manage().window().maximize();
		TimeUnit.SECONDS.sleep(3);
		login(driver, h.get("Username"), h.get("Password"), testName);

		/* Creating a new subscription */		
		searchCs_AccountNumber(driver, h.get("Client"), h.get("Brand"), h.get("CustomerReference"), h.get("AccountNumber"));
		TimeUnit.SECONDS.sleep(3);
	}
	
	
	/**
	 * Test to perform a new subscription via Customer Service screen and to verify it in CS and Customer Billing screens
	 * @throws Exception 
	 * @param newSubscription function to create a new subscription
	 * @param verifyNewSubscription function verify newly created subscription in CS screen
	 * @param cbf.verifyNewSubscriptionCB function to verify newly created subscription in Customer Billing screen
	 */
	//@Test(priority=4, dataProvider="databinding")
	public void customerServiceTypeSearch_SortCode(HashMap<String, String> h) throws Exception
	{
		ATUReports.setTestCaseReqCoverage("Searching new subscription through Customer service screen");
		ATUReports.setAuthorInfo("Automation Tester", Utils.getCurrentTime(),"1.0");	

		/* Login Section */
		driver.get(props.getProperty("baseUrl"));
		driver.manage().window().maximize();
		TimeUnit.SECONDS.sleep(3);
		login(driver, h.get("Username"), h.get("Password"), testName);

		/* Creating a new subscription */		
		searchCs_SortCode(driver, h.get("Client"), h.get("Brand"), h.get("CustomerReference"), h.get("SortCode"));
		TimeUnit.SECONDS.sleep(3);
	}
	
	/**
	 * Test to perform a new subscription via Customer Service screen and to verify it in CS and Customer Billing screens
	 * @throws Exception 
	 * @param newSubscription function to create a new subscription
	 * @param verifyNewSubscription function verify newly created subscription in CS screen
	 * @param cbf.verifyNewSubscriptionCB function to verify newly created subscription in Customer Billing screen
	 */
	//@Test(priority=4, dataProvider="databinding")
	public void customerServiceTypeSearch_CreditCardNumber(HashMap<String, String> h) throws Exception
	{
		ATUReports.setTestCaseReqCoverage("Searching new subscription through Customer service screen");
		ATUReports.setAuthorInfo("Automation Tester", Utils.getCurrentTime(),"1.0");	

		/* Login Section */
		driver.get(props.getProperty("baseUrl"));
		driver.manage().window().maximize();
		TimeUnit.SECONDS.sleep(3);
		login(driver, h.get("Username"), h.get("Password"), testName);

		/* Creating a new subscription */		
		searchCs_CreditCardNumber(driver, h.get("Client"), h.get("Brand"), h.get("CustomerReference"), h.get("CreditCardNumber"));
		TimeUnit.SECONDS.sleep(3);
	}
	
	
	/**
	 * Test to perform a new subscription via Customer Service screen and to verify it in CS and Customer Billing screens
	 * @throws Exception 
	 * @param newSubscription function to create a new subscription
	 * @param verifyNewSubscription function verify newly created subscription in CS screen
	 * @param cbf.verifyNewSubscriptionCB function to verify newly created subscription in Customer Billing screen
	 */
	//@Test(priority=4, dataProvider="databinding")
	public void customerServiceTypeSearch_ChequeNumber(HashMap<String, String> h) throws Exception
	{
		ATUReports.setTestCaseReqCoverage("Searching new subscription through Customer service screen");
		ATUReports.setAuthorInfo("Automation Tester", Utils.getCurrentTime(),"1.0");	

		/* Login Section */
		driver.get(props.getProperty("baseUrl"));
		driver.manage().window().maximize();
		TimeUnit.SECONDS.sleep(3);
		login(driver, h.get("Username"), h.get("Password"), testName);

		/* Creating a new subscription */		
		searchCs_ChequeNumber(driver, h.get("Client"), h.get("Brand"), h.get("CustomerReference"), h.get("ChequeNumber"));
		TimeUnit.SECONDS.sleep(3);
	}
	
	/**
	 * Test to perform a new subscription via Customer Service screen and to verify it in CS and Customer Billing screens
	 * @throws Exception 
	 * @param newSubscription function to create a new subscription
	 * @param verifyNewSubscription function verify newly created subscription in CS screen
	 * @param cbf.verifyNewSubscriptionCB function to verify newly created subscription in Customer Billing screen
	 */
	//@Test(priority=4, dataProvider="databinding")
	public void customerServiceTypeSearch_InvoiceNumber(HashMap<String, String> h) throws Exception
	{
		ATUReports.setTestCaseReqCoverage("Searching new subscription through Customer service screen");
		ATUReports.setAuthorInfo("Automation Tester", Utils.getCurrentTime(),"1.0");	

		/* Login Section */
		driver.get(props.getProperty("baseUrl"));
		driver.manage().window().maximize();
		TimeUnit.SECONDS.sleep(3);
		login(driver, h.get("Username"), h.get("Password"), testName);

		/* Creating a new subscription */		
		searchCs_InvoiceNumber(driver, h.get("Client"), h.get("Brand"), h.get("CustomerReference"), h.get("InvoiceNumber"));
		TimeUnit.SECONDS.sleep(3);
	}
	
	
	/**
	 * Test to perform a new subscription via Customer Service screen and to verify it in CS and Customer Billing screens
	 * @throws Exception 
	 * @param newSubscription function to create a new subscription
	 * @param verifyNewSubscription function verify newly created subscription in CS screen
	 * @param cbf.verifyNewSubscriptionCB function to verify newly created subscription in Customer Billing screen
	 */
	//@Test(priority=4, dataProvider="databinding")
	public void customerServiceTypeSearch_Country(HashMap<String, String> h) throws Exception
	{
		ATUReports.setTestCaseReqCoverage("Searching new subscription through Customer service screen");
		ATUReports.setAuthorInfo("Automation Tester", Utils.getCurrentTime(),"1.0");	

		/* Login Section */
		driver.get(props.getProperty("baseUrl"));
		driver.manage().window().maximize();
		TimeUnit.SECONDS.sleep(3);
		login(driver, h.get("Username"), h.get("Password"), testName);

		/* Creating a new subscription */		
		searchCs_Country(driver, h.get("Client"), h.get("Brand"), h.get("Country"));
		TimeUnit.SECONDS.sleep(3);
	}
	
	
	
	/**
	 * Test to perform a new subscription via Customer Service screen and to verify it in CS and Customer Billing screens
	 * @param Type5: Customer service_UK Gift Subscription Issuebased_Optimistic_Single stage_DD; Renewal Strategy : Single stage;  Schedule:Direct & Optimistic; Offer: Issue based; Payment method: Direct Debit
	 * @throws Exception 
	 * @param newSubscription function to create a new subscription
	 * @param verifyNewSubscription function verify newly created subscription in CS screen
	 * @param cbf.verifyNewSubscriptionCB function to verify newly created subscription in Customer Billing screen
	 */
	//@Test(priority=5, dataProvider="databinding")
	public void customerServiceType5(HashMap<String, String> h) throws Exception
	{
		ATUReports.setTestCaseReqCoverage("Creating a new subscription through Customer service screen");
		ATUReports.setAuthorInfo("Automation Tester", Utils.getCurrentTime(),"1.0");	

		/* Login Section */
		driver.get(props.getProperty("baseUrl"));
		driver.manage().window().maximize();
		TimeUnit.SECONDS.sleep(3);
		login(driver, h.get("Username"), h.get("Password"), testName);

		/* Creating a new subscription */		
		newSubscriptionCCGiftType5(driver, h.get("Client"), h.get("Brand"), h.get("PromotionName"), h.get("Card"), h.get("Title"), h.get("Firstname"), h.get("Surname"), h.get("Postcode"), h.get("Address"), h.get("GTitle"), h.get("GFirstname"), h.get("GSurname"), h.get("GPostcode"), h.get("GAddress"),h.get("AccHolderName"), h.get("AccNumber"), h.get("SortCode"));

		/* Verify the newly created subscription in Customer Service Screen*/
		verifyNewSubscriptionCSType5(driver, h.get("PromotionName"), h.get("Client"), h.get("Brand"));

		/* Verify the newly created subscription in Customer Billing Screen*/
		cbf.verifyNewSubscriptionCBType3(driver);
		TimeUnit.SECONDS.sleep(3);
	}

	/**
	 * Test to perform a new subscription via Customer Service screen and to verify it in CS and Customer Billing screens
	 * @param Type6: Customer service_UK Personal Subscription Issuebased_Optimistic_Single stage_Free;  Renewal Strategy : Single stage;  Schedule:Direct & Optimistic; Offer: Issue based; Payment method: Free
	 * @throws Exception 
	 * @param newSubscription function to create a new subscription
	 * @param verifyNewSubscription function verify newly created subscription in CS screen
	 * @param cbf.verifyNewSubscriptionCB function to verify newly created subscription in Customer Billing screen
	 */
	//@Test(priority=6, dataProvider="databinding")
	public void customerServiceType6(HashMap<String, String> h) throws Exception
	{
		ATUReports.setTestCaseReqCoverage("Creating a new subscription through Customer service screen");
		ATUReports.setAuthorInfo("Automation Tester", Utils.getCurrentTime(),"1.0");	

		/* Login Section */
		driver.get(props.getProperty("baseUrl"));
		driver.manage().window().maximize();
		TimeUnit.SECONDS.sleep(3);
		login(driver, h.get("Username"), h.get("Password"), testName);

		/* Creating a new subscription */		
		newSubscriptionFreeType6(driver, h.get("Client"), h.get("Brand"), h.get("PromotionName"), h.get("Product"), h.get("Card"), h.get("Title"), h.get("Firstname"), h.get("Surname"), h.get("Postcode"), h.get("Address"));

		/* Verify the newly created subscription in Customer Service Screen*/
		verifyNewSubscriptionFreeType6(driver, h.get("PromotionName"), h.get("Client"), h.get("Brand"));

		/* Verify the newly created subscription in Customer Billing Screen*/
		cbf.verifyNewSubscriptionCBType6(driver);
		TimeUnit.SECONDS.sleep(3);
	}


	//@Test(priority=7, dataProvider="databinding")
	public void customerServiceType7(HashMap<String, String> h) throws Exception
	{
		ATUReports.setTestCaseReqCoverage("Creating a new subscription through Customer service screen");
		ATUReports.setAuthorInfo("Automation Tester", Utils.getCurrentTime(),"1.0");	

		// Login Section  
		driver.get(props.getProperty("baseUrl"));
		driver.manage().window().maximize();
		TimeUnit.SECONDS.sleep(3);
		login(driver, h.get("Username"), h.get("Password"), testName);

		// Creating a new subscription 		
		newSubscriptionOSType7(driver, h.get("Client"), h.get("Brand"), h.get("Country"), h.get("PromotionName"), h.get("Card"), h.get("Title"), h.get("Firstname"), h.get("Surname"),h.get("OverAddress"), h.get("AccHolderName"), h.get("AccNumber"), h.get("SortCode"));

		// Verify the newly created subscription in Customer Service Screen
		verifyNewSubscriptionCSType7(driver, h.get("PromotionName"), h.get("Client"), h.get("Brand"));

		// Verify the newly created subscription in Customer Billing Screen
		cbf.verifyNewSubscriptionCBType7(driver);
		TimeUnit.SECONDS.sleep(3);

	}


	/**
	 * Test to check whether user can amend details in CS screen
	 * @throws Exception 
	 * @param check whether user can amend details in CS screen
	 */

	//@Test(priority=7, dataProvider="databinding")
	public void cSServiceAmend(HashMap<String, String> h) throws Exception
	{
		ATUReports.setTestCaseReqCoverage("Creating a new subscription through Customer service screen");
		ATUReports.setAuthorInfo("Automation Tester", Utils.getCurrentTime(),"1.0");	

		/* Login Section */
		driver.get(props.getProperty("baseUrl"));
		driver.manage().window().maximize();
		TimeUnit.SECONDS.sleep(3);
		login(driver, h.get("Username"), h.get("Password"), testName);

		/* Amend details in CS Screen */		
		serviceAmendDetails(driver, h.get("Client"), h.get("Brand"), h.get("ReferenceNumber"), h.get("NewName"), h.get("NewAddress"));
	}

	/**
	 * Test to check whether user can renew a subscription from CS screen
	 * @throws Exception 
	 * @param check whether user can renew a subscription from CS screen
	 */
	//@Test(priority=8, dataProvider="databinding")
	public void cSRenewSub(HashMap<String, String> h) throws Exception
	{
		ATUReports.setTestCaseReqCoverage("Creating a new subscription through Customer service screen");
		ATUReports.setAuthorInfo("Automation Tester", Utils.getCurrentTime(),"1.0");	

		/* Login Section */
		driver.get(props.getProperty("baseUrl"));
		driver.manage().window().maximize();
		TimeUnit.SECONDS.sleep(3);
		login(driver, h.get("Username"), h.get("Password"), testName);

		/* Amend details in CS Screen */		
		renewSubscription(driver, h.get("Client"), h.get("Brand"), h.get("ReferenceNumber"),h.get("PromotionName"), h.get("Card"), h.get("Title"), h.get("Firstname"), h.get("Surname"), h.get("Postcode"), h.get("Address"), h.get("CustomerName"), h.get("Card1"), h.get("ExpiryDate"), h.get("ExpiryYear"));
	}


	/**
	 * Test to check whether user can upgrade a subscription from CS screen
	 * @throws Exception 
	 * @param check whether user can upgrade a subscription from CS screen
	 */
	//@Test(priority=9, dataProvider="databinding")
	public void cSUpgradeSub(HashMap<String, String> h) throws Exception
	{
		ATUReports.setTestCaseReqCoverage("Creating a new subscription through Customer service screen");
		ATUReports.setAuthorInfo("Automation Tester", Utils.getCurrentTime(),"1.0");	

		/* Login Section */
		driver.get(props.getProperty("baseUrl"));
		driver.manage().window().maximize();
		TimeUnit.SECONDS.sleep(3);
		login(driver, h.get("Username"), h.get("Password"), testName);

		/* Amend details in CS Screen */		
		upgradeSubscription(driver, h.get("Client"), h.get("Brand"), h.get("ReferenceNumber"),h.get("Contract") , h.get("PromotionName"), h.get("Card"), h.get("Title"), h.get("Firstname"), h.get("Surname"), h.get("Postcode"), h.get("Address"), h.get("CustomerName"), h.get("Card1"), h.get("ExpiryDate"), h.get("ExpiryYear"));
	}


	/**
	 * Test to check whether user can Amend Contract through System letter from CS screen
	 * @throws Exception 
	 * @param amendContract_SendLetter function to Amend Contract in Transaction Enquiry through System Letter
	 */
	//@Test(priority=10, dataProvider="databinding")
	public void cSAmendContract_SystemLetter(HashMap<String, String> h) throws Exception
	{
		ATUReports.setTestCaseReqCoverage("Amending the existing subscription by sending System Letter");
		ATUReports.setAuthorInfo("Automation Tester", Utils.getCurrentTime(),"1.0");	

		/* Login Section */
		driver.get(props.getProperty("baseUrl"));
		driver.manage().window().maximize();
		TimeUnit.SECONDS.sleep(3);
		login(driver, h.get("Username"), h.get("Password"), testName);

		/* Amend contract in CS Screen */
		/* Send System Letter */
		amendContract_SendLetter(driver, h.get("LetterName"), h.get("Client"), h.get("Brand"), h.get("ReferenceNumber"));
	}

	/**
	 * Test to check whether user can Amend Contract through Suspend from CS screen
	 * @throws Exception 
	 * @param amendContract_Suspend function to Amend Contract in Transaction Enquiry through Suspend
	 * @param Reference Number should be of a Credit Card Subscription
	 */
	//@Test(priority=11, dataProvider="databinding")
	public void cSAmendContract_Suspend(HashMap<String, String> h) throws Exception
	{
		ATUReports.setTestCaseReqCoverage("Amending the existing subscription by sending System Letter");
		ATUReports.setAuthorInfo("Automation Tester", Utils.getCurrentTime(),"1.0");	

		/* Login Section */
		driver.get(props.getProperty("baseUrl"));
		driver.manage().window().maximize();
		TimeUnit.SECONDS.sleep(3);
		login(driver, h.get("Username"), h.get("Password"), testName);

		/* Amend contract in CS Screen */
		/* Suspend Subscription */
		amendContract_Suspend(driver, h.get("Reason"), h.get("Client"), h.get("Brand"), h.get("ReferenceNumber"));
	}

	/**
	 * Test to check whether user can Amend Contract through Resume from CS screen
	 * @throws Exception 
	 * @param amendContract_Suspend function to Amend Contract in Transaction Enquiry through Resume
	 * @param Reference Number should be of a Credit Card Subscription 
	 */
	//@Test(priority=12, dataProvider="databinding")
	public void cSAmendContract_Resume(HashMap<String, String> h) throws Exception
	{
		ATUReports.setTestCaseReqCoverage("Amending the existing subscription by resuming the Suspended subscription");
		ATUReports.setAuthorInfo("Automation Tester", Utils.getCurrentTime(),"1.0");	

		/* Login Section */
		driver.get(props.getProperty("baseUrl"));
		driver.manage().window().maximize();
		TimeUnit.SECONDS.sleep(3);
		login(driver, h.get("Username"), h.get("Password"), testName);

		/* Amend contract in CS Screen */
		/* Send System Letter */
		amendContract_Resume(driver, h.get("Reason"), h.get("ResumeStartingFrom"), h.get("Client"), h.get("Brand"), h.get("ReferenceNumber"));
	}

	/**
	 * Test to check whether user can Amend Contract through Resume from CS screen
	 * @throws Exception 
	 * @param amendContract_Suspend function to Amend Contract in Transaction Enquiry through Resume
	 * @param Reference Number should be of a Credit Card Subscription 
	 */
	//@Test(priority=13, dataProvider="databinding")
	public void cSAmendContract_RefundAmount(HashMap<String, String> h) throws Exception
	{
		ATUReports.setTestCaseReqCoverage("Amending the existing subscription by refunding the amount");
		ATUReports.setAuthorInfo("Automation Tester", Utils.getCurrentTime(),"1.0");	

		/* Login Section */
		driver.get(props.getProperty("baseUrl"));
		driver.manage().window().maximize();
		TimeUnit.SECONDS.sleep(3);
		login(driver, h.get("Username"), h.get("Password"), testName);

		/* Amend contract in CS Screen */
		/* Send System Letter */
		amendContract_RefundAmount(driver, h.get("Amount"), h.get("Reason"),h.get("Title"), h.get("CustomerName"),h.get("AddressLine1"),h.get("AddressLine2"),h.get("AddressLine3"),h.get("AddressLine4"),h.get("AddressLine5"),h.get("AddressLine6"),h.get("Country"),h.get("PostCode"), h.get("Client"), h.get("Brand"), h.get("ReferenceNumber"));
	}

	/**
	 * Test to check whether user can Amend Contract through Resume from CS screen
	 * @throws Exception 
	 * @param amendContract_Suspend function to Amend Contract in Transaction Enquiry through Resume
	 * @param Reference Number should be of a Credit Card Subscription 
	 */
	//@Test(priority=14, dataProvider="databinding")
	public void cSAmendContract_MailingMethod(HashMap<String, String> h) throws Exception
	{
		ATUReports.setTestCaseReqCoverage("Amending the existing subscription by changing Mailing Method");
		ATUReports.setAuthorInfo("Automation Tester", Utils.getCurrentTime(),"1.0");	

		/* Login Section */
		driver.get(props.getProperty("baseUrl"));
		driver.manage().window().maximize();
		TimeUnit.SECONDS.sleep(3);
		login(driver, h.get("Username"), h.get("Password"), testName);

		/* Amend contract in CS Screen */
		/* Send System Letter */
		amendContract_MailingMethod(driver, h.get("MailingMethod"), h.get("Client"), h.get("Brand"), h.get("ReferenceNumber"));
	}

	/**
	 * Test to check whether user can Amend Contract through Resume from CS screen - Customer Reference Number provided should be Active and should be Credit Card payment
	 * @throws Exception 
	 * @param amendContract_Suspend function to Amend Contract in Transaction Enquiry through Resume
	 * @param Reference Number should be of a Credit Card Subscription 
	 */
	//@Test(priority=15, dataProvider="databinding")
	public void cSAmendContract_ChangeTerm(HashMap<String, String> h) throws Exception
	{
		ATUReports.setTestCaseReqCoverage("Amending the existing subscription by changing Term");
		ATUReports.setAuthorInfo("Automation Tester", Utils.getCurrentTime(),"1.0");	

		/* Login Section */
		driver.get(props.getProperty("baseUrl"));
		driver.manage().window().maximize();
		TimeUnit.SECONDS.sleep(3);
		login(driver, h.get("Username"), h.get("Password"), testName);

		/* Amend contract in CS Screen */
		/* Send System Letter */
		amendContract_ChangeTerm(driver, h.get("Action"), h.get("Reason"), h.get("IssueType"), h.get("NumberOfIssues"), h.get("Client"), h.get("Brand"), h.get("ReferenceNumber"));
	}

	/**
	 * Test to check whether user can Amend Contract through Resume from CS screen 
	 * @throws Exception 
	 * @param amendContract_Suspend function to Amend Contract in Transaction Enquiry through Resume
	 * @param Reference Number should be of a Credit Card Subscription 
	 */
	//@Test(priority=16, dataProvider="databinding")
	public void cSAmendContract_PaymentDetails(HashMap<String, String> h) throws Exception
	{
		ATUReports.setTestCaseReqCoverage("Amending payment details for the Existing Subscription");
		ATUReports.setAuthorInfo("Automation Tester", Utils.getCurrentTime(),"1.0");	

		/* Login Section */
		driver.get(props.getProperty("baseUrl"));
		driver.manage().window().maximize();
		TimeUnit.SECONDS.sleep(3);
		login(driver, h.get("Username"), h.get("Password"), testName);

		/* Amend contract in CS Screen */
		/* Send System Letter */
		amendContract_PaymentDetails(driver, h.get("AccountName"), h.get("AccountNumber"), h.get("SortCode"), h.get("ChequeNumber"), h.get("CardNumber"), h.get("ExpiryDate"), h.get("ExpiryYear"), h.get("Client"), h.get("Brand"), h.get("ReferenceNumber"));
	}

	/**
	 * Test to check whether user can Amend Contract through Add Payment from CS screen _ Customer Reference number should be in suspended state and is a Direct Debit Payment
	 * @throws Exception 
	 * @param amendContract_Suspend function to Amend Contract in Transaction Enquiry through Resume
	 * @param Reference Number should be of a Credit Card Subscription 
	 */
	//@Test(priority=17, dataProvider="databinding")
	public void cSAmendContract_AddPayment(HashMap<String, String> h) throws Exception
	{
		ATUReports.setTestCaseReqCoverage("Amending the existing subscription by Adding Payment");
		ATUReports.setAuthorInfo("Automation Tester", Utils.getCurrentTime(),"1.0");	

		/* Login Section */
		driver.get(props.getProperty("baseUrl"));
		driver.manage().window().maximize();
		TimeUnit.SECONDS.sleep(3);
		login(driver, h.get("Username"), h.get("Password"), testName);

		/* Amend contract in CS Screen */
		/* Send System Letter */
		amendContract_AddPayment(driver, h.get("AccountName"), h.get("AccountNumber"), h.get("SortCode"), h.get("DDCollectionDate"), h.get("Client"), h.get("Brand"), h.get("ReferenceNumber"));
	}

	/**
	 * Test to check whether user can Amend Contract through Resume from CS screen
	 * @throws Exception 
	 * @param amendContract_Suspend function to Amend Contract in Transaction Enquiry through Resume
	 * @param Reference Number should be of a Credit Card Subscription 
	 */
	//@Test(priority=18, dataProvider="databinding")
	public void cSAmendContract_ChangeStartIssue(HashMap<String, String> h) throws Exception
	{
		ATUReports.setTestCaseReqCoverage("Amending the existing subscription by changing the Start Issue");
		ATUReports.setAuthorInfo("Automation Tester", Utils.getCurrentTime(),"1.0");	

		/* Login Section */
		driver.get(props.getProperty("baseUrl"));
		driver.manage().window().maximize();
		TimeUnit.SECONDS.sleep(3);
		login(driver, h.get("Username"), h.get("Password"), testName);

		/* Amend contract in CS Screen */
		/* Send System Letter */
		amendContract_ChangeStartIssue(driver, h.get("IssueNumber"), h.get("Client"), h.get("Brand"), h.get("ReferenceNumber"));
	}

	/**
	 * Test to check whether user can Amend Contract through Cancel Immediately from CS screen
	 * @throws Exception 
	 * @param amendContract_Suspend function to Amend Contract in Transaction Enquiry through Resume - Customer Reference Number provided should be Active
	 * @param Reference Number should be of a Credit Card Subscription 
	 */
	//@Test(priority=20, dataProvider="databinding")
	public void cSAmendContract_CancelImmediately(HashMap<String, String> h) throws Exception
	{
		ATUReports.setTestCaseReqCoverage("Cancellation of the existing subscription immediately");
		ATUReports.setAuthorInfo("Automation Tester", Utils.getCurrentTime(),"1.0");	

		/* Login Section */
		driver.get(props.getProperty("baseUrl"));
		driver.manage().window().maximize();
		TimeUnit.SECONDS.sleep(3);
		login(driver, h.get("Username"), h.get("Password"), testName);

		/* Amend contract in CS Screen */
		/* Send System Letter */
		amendContract_CancelImmediately(driver, h.get("Reason"), h.get("RefundAmount"), h.get("Client"), h.get("Brand"), h.get("ReferenceNumber"));
	}


	/**
	 * Test to check whether user can Amend Contract through Resume from CS screen - Customer Reference Number provided should be Active
	 * @throws Exception 
	 * @param amendContract_Suspend function to Amend Contract in Transaction Enquiry through Resume
	 * @param Reference Number should be of a Credit Card Subscription 
	 */
	//@Test(priority=19, dataProvider="databinding")
	public void cSAmendContract_CancelOnExpiry(HashMap<String, String> h) throws Exception
	{
		ATUReports.setTestCaseReqCoverage("Cancelling the existing subscription on Expiry");
		ATUReports.setAuthorInfo("Automation Tester", Utils.getCurrentTime(),"1.0");	

		/* Login Section */
		driver.get(props.getProperty("baseUrl"));
		driver.manage().window().maximize();
		TimeUnit.SECONDS.sleep(3);
		login(driver, h.get("Username"), h.get("Password"), testName);

		/* Amend contract in CS Screen */
		/* Send System Letter */
		amendContract_CancelOnExpiry(driver, h.get("Reason"), h.get("Client"), h.get("Brand"), h.get("ReferenceNumber"));
	}

	/**
	 * Test to check whether user can Amend Contract through Resume from CS screen
	 * @throws Exception 
	 * @param amendContract_Suspend function to Amend Contract in Transaction Enquiry through Resume
	 * @param Reference Number should be of a Credit Card Subscription 
	 */
	//@Test(priority=21, dataProvider="databinding")
	public void differentDeliveryAddress(HashMap<String, String> h) throws Exception
	{
		ATUReports.setTestCaseReqCoverage("Creating a new subscription through Customer service screen using Different Delivery Address");
		ATUReports.setAuthorInfo("Automation Tester", Utils.getCurrentTime(),"1.0");	

		/* Login Section */
		driver.get(props.getProperty("baseUrl"));
		driver.manage().window().maximize();
		TimeUnit.SECONDS.sleep(3);
		login(driver, h.get("Username"), h.get("Password"), testName);

		/* Amend contract in CS Screen */
		/* Send System Letter */
		differentDeliveryAddress(driver, h.get("Client"), h.get("Brand"), h.get("PromotionName"), h.get("Card"), h.get("Title"), h.get("Firstname"), h.get("Surname"), h.get("Postcode"), h.get("Address"), h.get("DeliveryPostCode"), h.get("DeliveryAddress"), h.get("CustomerName"), h.get("Card1"), h.get("ExpiryDate"), h.get("ExpiryYear"));
	}

	/**
	 * Test to check whether user can Amend Contract through Resume from CS screen
	 * @throws Exception 
	 * @param amendContract_Suspend function to Amend Contract in Transaction Enquiry through Resume
	 * @param Reference Number should be of a Credit Card Subscription 
	 */
	//@Test(priority=22, dataProvider="databinding")
	public void getDefaultPromotion(HashMap<String, String> h) throws Exception
	{
		ATUReports.setTestCaseReqCoverage("Creating a new subscription through Customer service screen using Default promotion");
		ATUReports.setAuthorInfo("Automation Tester", Utils.getCurrentTime(),"1.0");	

		/* Login Section */
		driver.get(props.getProperty("baseUrl"));
		driver.manage().window().maximize();
		TimeUnit.SECONDS.sleep(3);
		login(driver, h.get("Username"), h.get("Password"), testName);

		/* Amend contract in CS Screen */
		/* Send System Letter */
		getDefaultPromotion(driver, h.get("Client"), h.get("Brand"), h.get("PromotionName"), h.get("PromotionReference"), h.get("Card"), h.get("Title"), h.get("Firstname"), h.get("Surname"), h.get("Postcode"), h.get("Address"), h.get("AccHolderName"), h.get("AccNumber"), h.get("SortCode"));
	}

	/**
	 * Test to perform a new subscription via Customer Service screen and to verify it in CS and Customer Billing screens
	 * @param Type8:Customer service_UK Personal Subscription Issuebased_Optimistic_Single stage_CCC;  Renewal Strategy : Continuous;  Schedule:Direct & Optimistic; Offer: Issue based; Payment method: Credit Card
	 * @throws Exception 
	 * @param newSubscription function to create a new subscription
	 * @param verifyNewSubscription function verify newly created subscription in CS screen
	 * @param cbf.verifyNewSubscriptionCB function to verify newly created subscription in Customer Billing screen
	 */
	//@Test(priority=23, dataProvider="databinding")
	public void customerServiceType8(HashMap<String, String> h) throws Exception
	{
		ATUReports.setTestCaseReqCoverage("Creating a new subscription through Customer service screen");
		ATUReports.setAuthorInfo("Automation Tester", Utils.getCurrentTime(),"1.0");	

		/* Login Section */
		driver.get(props.getProperty("baseUrl"));
		driver.manage().window().maximize();
		TimeUnit.SECONDS.sleep(3);
		login(driver, h.get("Username"), h.get("Password"), testName);

		/* Creating a new subscription */		
		newSubscriptionCCCType8(driver, h.get("Client"), h.get("Brand"), h.get("PromotionName"), h.get("Card"), h.get("Title"), h.get("Firstname"), h.get("Surname"), h.get("Postcode"), h.get("Address"), h.get("CustomerName"), h.get("Card1"), h.get("ExpiryDate"), h.get("ExpiryYear"));

		/* Verify the newly created subscription in Customer Service Screen*/
		verifyNewSubscriptionCSType8(driver, h.get("PromotionName"), h.get("Client"), h.get("Brand"));

		/* Verify the newly created subscription in Customer Billing Screen*/
		cbf.verifyNewSubscriptionCBType8(driver);
		TimeUnit.SECONDS.sleep(3);

	}

	/**
	 * Test to perform a new subscription via Customer Service screen and to verify it in CS and Customer Billing screens
	 * @param Type8:Customer service_UK Personal Subscription Issuebased_Optimistic_Single stage_CCC;  Renewal Strategy : Continuous;  Schedule:Direct & Optimistic; Offer: Issue based; Payment method: Credit Card
	 * @throws Exception 
	 * @param newSubscription function to create a new subscription
	 * @param verifyNewSubscription function verify newly created subscription in CS screen
	 * @param cbf.verifyNewSubscriptionCB function to verify newly created subscription in Customer Billing screen
	 */
	//@Test(priority=24, dataProvider="databinding")
	public void giftSubscriptionRecepientDetails(HashMap<String, String> h) throws Exception
	{
		ATUReports.setTestCaseReqCoverage("Creating a new subscription through Customer service screen and verifying receipient details");
		ATUReports.setAuthorInfo("Automation Tester", Utils.getCurrentTime(),"1.0");	

		/* Login Section */
		driver.get(props.getProperty("baseUrl"));
		driver.manage().window().maximize();
		TimeUnit.SECONDS.sleep(3);
		login(driver, h.get("Username"), h.get("Password"), testName);

		/* Creating a new subscription */		
		giftSubscriptionRecepientDetails(driver, h.get("Client"), h.get("Brand"), h.get("PromotionName"), h.get("Card"), h.get("Title"), h.get("Firstname"), h.get("Surname"), h.get("Postcode"), h.get("Address"), h.get("GCustTitle"), h.get("GFirstname"), h.get("GSurname"), h.get("GPostcode"), h.get("GAddress"), h.get("GCustTitle1"), h.get("GFirstname1"), h.get("GSurname1"), h.get("GCustTitle2"), h.get("GFirstname2"), h.get("GSurname2"), h.get("GEditCustTitle"), h.get("GEditFirstName"), h.get("GEditSurname"), h.get("Name"), h.get("Card1"), h.get("ExpiryDate"), h.get("ExpiryYear"));

		TimeUnit.SECONDS.sleep(3);

	}

	/**
	 * Test to perform a new subscription via Customer Service screen and to verify it in CS and Customer Billing screens
	 * @param Type8:Customer service_UK Personal Subscription Issuebased_Optimistic_Single stage_CCC;  Renewal Strategy : Continuous;  Schedule:Direct & Optimistic; Offer: Issue based; Payment method: Credit Card
	 * @throws Exception 
	 * @param newSubscription function to create a new subscription
	 * @param verifyNewSubscription function verify newly created subscription in CS screen
	 * @param cbf.verifyNewSubscriptionCB function to verify newly created subscription in Customer Billing screen
	 */
	//@Test(priority=25, dataProvider="databinding")
	public void demographicsVerification(HashMap<String, String> h) throws Exception
	{
		ATUReports.setTestCaseReqCoverage("Creating a new subscription through Customer service screen by checking Demoghraphics");
		ATUReports.setAuthorInfo("Automation Tester", Utils.getCurrentTime(),"1.0");	

		/* Login Section */
		driver.get(props.getProperty("baseUrl"));
		driver.manage().window().maximize();
		TimeUnit.SECONDS.sleep(3);
		login(driver, h.get("Username"), h.get("Password"), testName);

		/* Creating a new subscription */		
		demographicsVerification(driver, h.get("Client"), h.get("Brand"), h.get("PromotionName"), h.get("Card"), h.get("Title"), h.get("Firstname"), h.get("Surname"), h.get("Postcode"), h.get("Address"), h.get("DemographicProperty1"), h.get("DemographicProperty2"), h.get("DemographicProperty3"), h.get("CustomerName"), h.get("Card1"), h.get("ExpiryDate"), h.get("ExpiryYear"));

		TimeUnit.SECONDS.sleep(3);
	}

	/**
	 * Test to perform a new subscription via Customer Service screen and to verify it in CS and Customer Billing screens
	 * @param Type8:Customer service_UK Personal Subscription Issuebased_Optimistic_Single stage_CCC;  Renewal Strategy : Continuous;  Schedule:Direct & Optimistic; Offer: Issue based; Payment method: Credit Card
	 * @throws Exception 
	 * @param newSubscription function to create a new subscription
	 * @param verifyNewSubscription function verify newly created subscription in CS screen
	 * @param cbf.verifyNewSubscriptionCB function to verify newly created subscription in Customer Billing screen
	 */
	//@Test(priority=26, dataProvider="databinding")
	public void customerEvents(HashMap<String, String> h) throws Exception
	{
		ATUReports.setTestCaseReqCoverage("Verifying the Customer events for the Customer Refernce Number");
		ATUReports.setAuthorInfo("Automation Tester", Utils.getCurrentTime(),"1.0");	

		/* Login Section */
		driver.get(props.getProperty("baseUrl"));
		driver.manage().window().maximize();
		TimeUnit.SECONDS.sleep(3);
		login(driver, h.get("Username"), h.get("Password"), testName);

		/* Creating a new subscription */		
		customerEventsVerification(driver, h.get("ReferenceNumber"), h.get("Client"), h.get("Brand"));
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
		}
		catch(Exception e)
		{

		}
		for(WebDriver d : drivers)
		{
			d.quit();
		}
	}
}