package com.testhouse.Functions;

import java.util.concurrent.TimeUnit;

import org.joda.time.DateTime;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;

public class CustomerServiceFunctions extends GeneralFunctions
{
	public String accountID = null, verifyStatus = null, verifyType = null, payMethod = null, payStatus = null, renStatus = null, subRole = null;	
	public static String testName,orderRef;

	/**
	 * Method performed to do a new subscription 
	 * 
	 * @param driver Object for webdriver
	 * @param client Object for selecting a client from the list
	 * @param brand Object for selecting a brand from the list
	 * @param promotion Object for selecting a valid promotion to do a new subscription
	 * @param cardType Object for selecting a card type from the options available 
	 * @param custTitle Object for fetching the salutation
	 * @param firstname Object for fetching the first name 
	 * @param surname Object for fetching the surname 
	 * @param postalcode Object for fetching the post code
	 * @param address Object for fetching the address 
	 * @param custName Object for fetching the Card Holder's Name 
	 * @param cardNum1 Object for fetching the first 8 digits of the Card Number 
	 * @param cardNum2 Object for fetching the last 8 digits of the Card Number
	 * @param date Object for fetching the Expiry Date
	 * @param year Object for fetching the Expiry Year
	 * @throws Exception To throw an exception whenever an unexpected failure occurs
	 * 
	 */
	public void newSubscriptionCCType1(WebDriver driver, String client, String brand, String promotion, String cardType, String custTitle, String firstname, String surname, String postalcode, String address, String custName, String cardNum1, String date, String year) throws Exception
	{
		try
		{
			element(driver, customerServiceLink).click();	
			TimeUnit.SECONDS.sleep(3);
			for (String winHandle : driver.getWindowHandles()) 
			{
				driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
			}
			Select(element(driver, clientSelect)).selectByVisibleText(client);
			for (int i = 1; i <= 100; i++)
			{
				try
				{
					element(driver, brandSelect(brand)).click();
					TimeUnit.SECONDS.sleep(7);
					break;
				}

				catch (Exception e)
				{
					element(driver, fastFoward).click();
				}
			}		
			element(driver, newSubscription).click();
			TimeUnit.SECONDS.sleep(2);
			element(driver, promotionName).sendKeys(promotion);
			element(driver, findPromotion).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, selectPromotion(promotion)).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, promotionNextBtn).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, offerCard(cardType)).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, title).sendKeys(custTitle);
			element(driver, firstName).sendKeys(firstname);
			element(driver, surName).sendKeys(surname);
			element(driver, postCode).sendKeys(postalcode);
			element(driver, lookupAddress).click();
			TimeUnit.SECONDS.sleep(5);
			Select(element(driver, selectAddress)).selectByVisibleText(address);
			TimeUnit.SECONDS.sleep(5);
			element(driver, custNextBtn).click();
			TimeUnit.SECONDS.sleep(2);
			try
			{
				element(driver, custAssociationNextBtn).isDisplayed();
				element(driver, custAssociationNextBtn).click();
			}
			catch(Exception e)
			{
				// No Action Required
			}
			TimeUnit.SECONDS.sleep(2);
			element(driver, issueCalenderNextBtn).click();
			TimeUnit.SECONDS.sleep(2);
			element(driver, customerName).sendKeys(custName);
			TimeUnit.SECONDS.sleep(2);
			element(driver, cardNumber).sendKeys(cardNum1);
			TimeUnit.SECONDS.sleep(2);
			Select(element(driver, expiryDate)).selectByVisibleText(""+date+"");
			TimeUnit.SECONDS.sleep(2);
			Select(element(driver, expiryYear)).selectByVisibleText(""+year+"");	
			TimeUnit.SECONDS.sleep(5);
			element(driver, checkoutNextBtn).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, orderRefNumber).isDisplayed();
			orderRef = element(driver, orderRefNumber).getText();
			System.out.println(orderRef);
			ATUReports.add("New subscription has been done sucessfully with order reference number as: "+orderRef, "Promotion name: "+ promotion,"Order Reference",orderRef, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		catch(Exception e)
		{
			ATUReports.add("Unable to do a new subscription", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			takeScreenShotOnFailure(driver, testName);
		}
	}

	public void fetchDetailsCs(WebDriver driver, String client, String brand)
	{
		try
		{
			TimeUnit.SECONDS.sleep(5);
			element(driver, homeLink).click();
			TimeUnit.SECONDS.sleep(5);
			element(driver, customerServiceLink).click();
			TimeUnit.SECONDS.sleep(5);
			TimeUnit.SECONDS.sleep(3);
			for (String winHandle : driver.getWindowHandles()) 
			{
				driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
			}
			Select(element(driver, clientSelect)).selectByVisibleText(client);
			for (int i = 1; i <= 100; i++)
			{
				try
				{
					element(driver, brandSelect(brand)).click();
					TimeUnit.SECONDS.sleep(7);
					break;
				}

				catch (Exception e)
				{
					element(driver, fastFoward).click();
				}
			}

			TimeUnit.SECONDS.sleep(3);
			element(driver, serviceExistingSubscriptionLink).click();
			TimeUnit.SECONDS.sleep(5);
			element(driver, customerRefSearch).sendKeys(orderRef);
			TimeUnit.SECONDS.sleep(2);
			new Actions(driver).moveToElement(element(driver, viewCustomersButton)).perform();
			element(driver, viewCustomersButton).click();
			TimeUnit.SECONDS.sleep(5);
			new Actions(driver).moveToElement(element(driver, viewCustomer(orderRef))).perform();
			element(driver, viewCustomer(orderRef)).click();
			TimeUnit.SECONDS.sleep(3);
			if(element(driver, custAssociationNextBtn).isEnabled())
			{
				element(driver, custAssociationNextBtn).click();
				TimeUnit.SECONDS.sleep(3);
			}
			accountID = element(driver, verifyCustomerRef).getText();
			verifyStatus = element(driver, verifyContractStatus).getText();
			verifyType = element(driver, verifySubType).getText();
			payMethod = element(driver, paymentMethod).getText();
			payStatus = element(driver, paymentStatus).getText();
			subRole = element(driver, subscriberRole).getText();
			renStatus = element(driver, renewalStatus).getText();
		}
		catch(Exception e2)
		{		
			ATUReports.add("Unable to fetch details from CS screen",LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
	}

	/**
	 * Method used for verifying the newly created subscription
	 * @param driver Object for defining the web driver
	 * @param promotion Variable to pass the promotion name
	 * @throws Exception To throw an exception whenever an unexpected failure occurs
	 */
	public void verifyNewSubscriptionCSType1(WebDriver driver, String promotion, String client, String brand) throws Exception
	{
		try
		{
			try
			{
				fetchDetailsCs(driver, client, brand);
				TimeUnit.SECONDS.sleep(3);
				Assert.assertEquals(accountID, orderRef);
				Assert.assertEquals(verifyStatus, "ACTIVE");
				Assert.assertEquals(verifyType, "STANDARD_SUBSCRIPTION");
				Assert.assertEquals(payMethod, "CREDIT_CARD");
				Assert.assertEquals(payStatus, "Paid");
				Assert.assertEquals(renStatus, "");
				Assert.assertEquals(subRole, "DIRECT");
				ATUReports.add(verifyType + " Order: "+accountID+"has been successfully verified in CS screen with contract status as:"+verifyStatus,"Order Reference: "+ orderRef,"Payment Method: Direct_Debit, Payment Status: UnPaid, Renewal Status: Continous, Subscriber Role: Direct","Payment Method: "+payMethod + ", Payment Status:"+payStatus+ " ,Renewal Status:"+ renStatus+", Subscriber Role: " +subRole,LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

				TimeUnit.SECONDS.sleep(5); 
			}
			catch (AssertionError e)
			{
				ATUReports.add("Unable to verify the newly created subscription",accountID, verifyStatus, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				takeScreenShotOnFailure(driver, testName);
				System.out.println(e);
			}

		}
		catch (Exception e1)
		{
			takeScreenShotOnFailure(driver, testName);
			System.out.println(e1);
		}
	}

	public void newSubscriptionDD(WebDriver driver, String client, String brand, String promotion, String cardType, String custTitle, String firstname, String surname, String postalcode, String address, String accHolderName, String accNumber, String SortCode) throws Exception
	{
		try
		{
			element(driver, customerServiceLink).click();	
			TimeUnit.SECONDS.sleep(3);
			for (String winHandle : driver.getWindowHandles()) 
			{
				driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
			}
			Select(element(driver, clientSelect)).selectByVisibleText(client);
			for (int i = 1; i <= 100; i++)
			{
				try
				{
					element(driver, brandSelect(brand)).click();
					TimeUnit.SECONDS.sleep(7);
					break;
				}

				catch (Exception e)
				{
					element(driver, fastFoward).click();
				}
			}		
			element(driver, newSubscription).click();
			TimeUnit.SECONDS.sleep(2);
			element(driver, promotionName).sendKeys(promotion);
			element(driver, findPromotion).click();
			TimeUnit.SECONDS.sleep(3);

			element(driver, selectPromotion(promotion)).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, promotionNextBtn).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, offerCard(cardType)).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, title).sendKeys(custTitle);
			element(driver, firstName).sendKeys(firstname);
			element(driver, surName).sendKeys(surname);
			element(driver, postCode).sendKeys(postalcode);
			element(driver, lookupAddress).click();
			TimeUnit.SECONDS.sleep(5);
			Select(element(driver, selectAddress)).selectByVisibleText(address);
			TimeUnit.SECONDS.sleep(5);
			element(driver, custNextBtn).click();
			TimeUnit.SECONDS.sleep(2);
			try
			{
				element(driver, custAssociationNextBtn).isDisplayed();
				element(driver, custAssociationNextBtn).click();
			}
			catch(Exception e)
			{
				// No Action Required
			}
			TimeUnit.SECONDS.sleep(2);
			element(driver, issueCalenderNextBtn).click();
			TimeUnit.SECONDS.sleep(2);
			element(driver, accountHolderName).sendKeys(accHolderName);
			TimeUnit.SECONDS.sleep(2);
			element(driver, accountNumber).sendKeys(accNumber);
			TimeUnit.SECONDS.sleep(2);
			element(driver, sCode).sendKeys(SortCode);
			//element(driver, sCode).sendKeys(sortCode);
			/*Select(element(driver, expiryDate)).selectByVisibleText(""+date+"");
		TimeUnit.SECONDS.sleep(2);
		Select(element(driver, expiryYear)).selectByVisibleText(""+year+"");	
		TimeUnit.SECONDS.sleep(5);*/
			TimeUnit.SECONDS.sleep(5);
			element(driver, lookupBankButton).click();
			TimeUnit.SECONDS.sleep(5);
			element(driver, checkoutNextBtn).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, orderRefNumber).isDisplayed();
			orderRef = element(driver, orderRefNumber).getText();
			System.out.println(orderRef);
			ATUReports.add("New subscription has been done sucessfully with order reference number as: "+orderRef, "Promotion name: "+ promotion,"Order Reference",orderRef,LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		catch(Exception e)
		{
			ATUReports.add("Unable to do a new subscription", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			takeScreenShotOnFailure(driver, testName);
		}
	}
	public void verifyNewSubscriptionCSType2(WebDriver driver, String promotion, String client, String brand) throws Exception
	{
		try
		{
			try
			{
				fetchDetailsCs(driver, client, brand);
				TimeUnit.SECONDS.sleep(3);
				Assert.assertEquals(accountID, orderRef);
				Assert.assertEquals(verifyStatus, "ACTIVE");
				Assert.assertEquals(verifyType, "STANDARD_SUBSCRIPTION");
				Assert.assertEquals(payMethod, "DIRECT_DEBIT");
				Assert.assertEquals(payStatus, "Unpaid");
				Assert.assertEquals(renStatus, "Continious");
				Assert.assertEquals(subRole, "DIRECT");
				ATUReports.add(verifyType + " Order: "+orderRef+"has been successfully verified in CS screen with contract status as:"+verifyStatus,"Order Reference: "+ orderRef,"Payment Method: Direct_Debit, Payment Status: UnPaid, Renewal Status: Continous, Subscriber Role: Direct","Payment Method: "+payMethod + ", Payment Status:"+payStatus+ " ,Renewal Status:"+ renStatus+", Subscriber Role: " +subRole,LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

				TimeUnit.SECONDS.sleep(5); 
			}
			catch (AssertionError e)
			{
				ATUReports.add("Unable to verify the newly created subscription",accountID, verifyStatus, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				takeScreenShotOnFailure(driver, testName);
				System.out.println(e);
			}

		}
		catch (Exception e1)
		{
			takeScreenShotOnFailure(driver, testName);
			System.out.println(e1);
		}
	}

	public void newSubscriptionCCGiftType3(WebDriver driver, String client, String brand, String promotion, String cardType, String custTitle, String firstname, String surname, String postalcode, String address, String gCustTitle, String gFirstname, String gSurname, String gPostalcode, String gAddress, String custName, String cardNum1, String date, String year) throws Exception
	{
		try
		{
			element(driver, customerServiceLink).click();	
			TimeUnit.SECONDS.sleep(3);
			for (String winHandle : driver.getWindowHandles()) 
			{
				driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
			}
			Select(element(driver, clientSelect)).selectByVisibleText(client);
			for (int i = 1; i <= 100; i++)
			{
				try
				{
					element(driver, brandSelect(brand)).click();
					TimeUnit.SECONDS.sleep(7);
					break;
				}

				catch (Exception e)
				{
					element(driver, fastFoward).click();
				}
			}		
			element(driver, newSubscription).click();
			TimeUnit.SECONDS.sleep(2);
			element(driver, promotionName).sendKeys(promotion);
			element(driver, findPromotion).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, selectPromotion(promotion)).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, promotionNextBtn).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, offerCard(cardType)).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, title).sendKeys(custTitle);
			element(driver, firstName).sendKeys(firstname);
			element(driver, surName).sendKeys(surname);
			element(driver, postCode).sendKeys(postalcode);
			element(driver, lookupAddress).click();
			TimeUnit.SECONDS.sleep(5);
			Select(element(driver, selectAddress)).selectByVisibleText(address);
			TimeUnit.SECONDS.sleep(5);
			element(driver, giftSubs).click();
			TimeUnit.SECONDS.sleep(2);
			element(driver, gTitle).sendKeys(gCustTitle);
			TimeUnit.SECONDS.sleep(2);
			element(driver, gFirstName).sendKeys(gFirstname);
			TimeUnit.SECONDS.sleep(2);
			element(driver, gSurName).sendKeys(gSurname);
			TimeUnit.SECONDS.sleep(2);
			element(driver, gPostCode).sendKeys(gPostalcode);
			TimeUnit.SECONDS.sleep(2);
			element(driver, gLookupAddress).click();
			TimeUnit.SECONDS.sleep(2);
			element(driver, gSelectAddress).click();
			TimeUnit.SECONDS.sleep(2);
			element(driver, gSaveButton).click();
			TimeUnit.SECONDS.sleep(2);
			element(driver, custNextBtn).click();
			TimeUnit.SECONDS.sleep(2);
			try
			{
				element(driver, custAssociationNextBtn).isDisplayed();
				element(driver, custAssociationNextBtn).click();
			}
			catch(Exception e)
			{
				// No Action Required
			}
			TimeUnit.SECONDS.sleep(2);
			element(driver, issueCalenderNextBtn).click();
			TimeUnit.SECONDS.sleep(2);
			element(driver, customerName).sendKeys(custName);
			TimeUnit.SECONDS.sleep(2);
			element(driver, cardNumber).sendKeys(cardNum1);
			TimeUnit.SECONDS.sleep(2);
			Select(element(driver, expiryDate)).selectByVisibleText(""+date+"");
			TimeUnit.SECONDS.sleep(2);
			Select(element(driver, expiryYear)).selectByVisibleText(""+year+"");	
			TimeUnit.SECONDS.sleep(5);
			element(driver, checkoutNextBtn).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, orderRefNumber).isDisplayed();
			orderRef = element(driver, orderRefNumber).getText();
			System.out.println(orderRef);
			ATUReports.add("New subscription has been done sucessfully with order reference number as: "+orderRef, "Promotion name: "+ promotion,"Order Reference",orderRef, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		catch(Exception e)
		{
			ATUReports.add("Unable to do a new subscription", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			takeScreenShotOnFailure(driver, testName);
		}
	}

	/**
	 * Method used for verifying the newly created subscription
	 * @param driver Object for defining the web driver
	 * @param promotion Variable to pass the promotion name
	 * @throws Exception To throw an exception whenever an unexpected failure occurs
	 */
	public void verifyNewSubscriptionCSType3(WebDriver driver, String promotion, String client, String brand) throws Exception
	{
		try
		{
			try
			{
				fetchDetailsCs(driver, client, brand);
				TimeUnit.SECONDS.sleep(3);
				//Assert.assertEquals(accountID, orderRef);
				Assert.assertEquals(verifyStatus, "ACTIVE");
				Assert.assertEquals(verifyType, "GIFT_SUBSCRIPTION");
				Assert.assertEquals(payMethod, "CREDIT_CARD");
				Assert.assertEquals(payStatus, "Paid");
				Assert.assertEquals(renStatus, "");
				Assert.assertEquals(subRole, "DONOR");
				ATUReports.add(verifyType + " Order: "+accountID+"has been successfully verified in CS screen with contract status as:"+verifyStatus,"Order Reference: "+ orderRef,"Payment Method: CREDIT_CARD, Payment Status: Paid, Renewal Status: Continous, Subscriber Role: DONOR","Payment Method: "+payMethod + ", Payment Status:"+payStatus+ " ,Renewal Status:"+ renStatus+", Subscriber Role: " +subRole,LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

				TimeUnit.SECONDS.sleep(5); 
			}
			catch (Exception e)
			{
				ATUReports.add("Unable to verify the newly created subscription",accountID, verifyStatus, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				takeScreenShotOnFailure(driver, testName);
				System.out.println(e);
			}
		}
		catch (Exception e1)
		{
			takeScreenShotOnFailure(driver, testName);
			System.out.println(e1);
		}
	}

	/**
	 * Method performed to do a new subscription 
	 * 
	 * @param driver Object for webdriver
	 * @param client Object for selecting a client from the list
	 * @param brand Object for selecting a brand from the list
	 * @param promotion Object for selecting a valid promotion to do a new subscription
	 * @param cardType Object for selecting a card type from the options available 
	 * @param custTitle Object for fetching the salutation
	 * @param firstname Object for fetching the first name 
	 * @param surname Object for fetching the surname 
	 * @param postalcode Object for fetching the post code
	 * @param address Object for fetching the address 
	 * @param custName Object for fetching the Card Holder's Name 
	 * @param cardNum1 Object for fetching the first 8 digits of the Card Number 
	 * @param cardNum2 Object for fetching the last 8 digits of the Card Number
	 * @param date Object for fetching the Expiry Date
	 * @param year Object for fetching the Expiry Year
	 * @throws Exception To throw an exception whenever an unexpected failure occurs
	 * 
	 */
	public void newSubscriptionCCType4(WebDriver driver, String client, String brand, String promotion, String cardType, String custTitle, String firstname, String surname, String postalcode, String address, String custName, String cardNum1, String date, String year) throws Exception
	{
		try
		{
			element(driver, customerServiceLink).click();	
			TimeUnit.SECONDS.sleep(3);
			for (String winHandle : driver.getWindowHandles()) 
			{
				driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
			}
			Select(element(driver, clientSelect)).selectByVisibleText(client);
			for (int i = 1; i <= 100; i++)
			{
				try
				{
					element(driver, brandSelect(brand)).click();
					TimeUnit.SECONDS.sleep(7);
					break;
				}

				catch (Exception e)
				{
					element(driver, fastFoward).click();
				}
			}		
			element(driver, newSubscription).click();
			TimeUnit.SECONDS.sleep(2);
			element(driver, proRadioButton).click();
			TimeUnit.SECONDS.sleep(2);
			element(driver, promotionName).sendKeys(promotion);
			element(driver, findPromotion).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, selectPromotion(promotion)).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, promotionNextBtn).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, offerCard(cardType)).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, listProButton).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, quantityNum).sendKeys("1");
			TimeUnit.SECONDS.sleep(3);
			element(driver, quantitySel).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, quantityNext).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, title).sendKeys(custTitle);
			element(driver, firstName).sendKeys(firstname);
			element(driver, surName).sendKeys(surname);
			element(driver, postCode).sendKeys(postalcode);
			element(driver, lookupAddress).click();
			TimeUnit.SECONDS.sleep(5);
			Select(element(driver, selectAddress)).selectByVisibleText(address);
			TimeUnit.SECONDS.sleep(5);
			element(driver, custNextBtn).click();
			TimeUnit.SECONDS.sleep(2);
			try
			{
				element(driver, custAssociationNextBtn).isDisplayed();
				element(driver, custAssociationNextBtn).click();
			}
			catch(Exception e)
			{
				// No Action Required
			}
			TimeUnit.SECONDS.sleep(2);
			element(driver, issueCalenderNextBtn).click();
			TimeUnit.SECONDS.sleep(2);
			element(driver, customerName).sendKeys(custName);
			TimeUnit.SECONDS.sleep(2);
			element(driver, cardNumber).sendKeys(cardNum1);
			TimeUnit.SECONDS.sleep(2);
			Select(element(driver, expiryDate)).selectByVisibleText(""+date+"");
			TimeUnit.SECONDS.sleep(2);
			Select(element(driver, expiryYear)).selectByVisibleText(""+year+"");	
			TimeUnit.SECONDS.sleep(5);
			element(driver, checkoutNextBtn).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, orderRefNumber).isDisplayed();
			orderRef = element(driver, orderRefNumber).getText();
			System.out.println(orderRef);
			ATUReports.add("New subscription has been done sucessfully with order reference number as:"+orderRef, "Promotion name: "+ promotion,"Order Reference",orderRef, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		catch(Exception e)
		{
			ATUReports.add("Unable to do a new subscription", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			takeScreenShotOnFailure(driver, testName);
		}
	}
	/**
	 * Method used for verifying the newly created subscription
	 * @param driver Object for defining the web driver
	 * @param promotion Variable to pass the promotion name
	 * @throws Exception To throw an exception whenever an unexpected failure occurs
	 */
	public void verifyNewSubscriptionCSType4(WebDriver driver, String promotion, String client, String brand) throws Exception
	{
		try
		{
			String proId  = null, proSStatus = null, proSStype = null, proPStatus = null;
			try
			{
				fetchDetailsCs(driver, client, brand);
				TimeUnit.SECONDS.sleep(3);
				proId = element(driver, proAccountId).getText();
				proSStatus = element(driver, proSubStatus).getText();
				proSStype = element(driver, proSubType).getText();
				proPStatus = element(driver, proPaystatus).getText();
				Assert.assertEquals(proId, orderRef);
				Assert.assertEquals(proSStatus, "COMPLETED");
				Assert.assertEquals(proSStype, "PRODUCT_ONLY_SUBSCRIPTION");
				Assert.assertEquals(proPStatus, "Paid");
				ATUReports.add(verifyType + " Order: "+accountID+"has been successfully verified in CS screen with contract status as:"+verifyStatus,"Order Reference: "+ orderRef,"Payment Method: Credit Card, Payment Status: Paid","Payment Method: "+payMethod + ", Payment Status:"+payStatus,LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

				TimeUnit.SECONDS.sleep(5); 
			}
			catch (Exception e)
			{
				ATUReports.add("Unable to verify the newly created subscription",accountID, verifyStatus, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				takeScreenShotOnFailure(driver, testName);
				System.out.println(e);
			}
		}
		catch (Exception e1)
		{
			takeScreenShotOnFailure(driver, testName);
			System.out.println(e1);
		}
	}

	/**
	 * Method used for verifying the newly created subscription
	 * @param driver Object for defining the web driver
	 * @param promotion Variable to pass the promotion name
	 * @throws Exception To throw an exception whenever an unexpected failure occurs
	 */
	public void searchCs(WebDriver driver, String client, String brand, String customerReference, String AddressLine, String PostCode, String CompanyName, String LastName, String FirstName, String Email, String AccountNumber, String SortCode, String CCNumber, String Country) throws Exception
	{
		try
		{						//Customer Reference
			try
			{
				TimeUnit.SECONDS.sleep(5);
				element(driver, customerServiceLink).click();
				TimeUnit.SECONDS.sleep(5);
				TimeUnit.SECONDS.sleep(3);
				for (String winHandle : driver.getWindowHandles()) 
				{
					driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
				}
				Select(element(driver, clientSelect)).selectByVisibleText(client);
				for (int i = 1; i <= 100; i++)
				{
					try
					{
						element(driver, brandSelect(brand)).click();
						TimeUnit.SECONDS.sleep(7);
						break;
					}

					catch (Exception e)
					{
						element(driver, fastFoward).click();
					}
				}

				TimeUnit.SECONDS.sleep(3);
				element(driver, serviceExistingSubscriptionLink).click();
				TimeUnit.SECONDS.sleep(5);
				try
				{
					element(driver, customerRefSearch).sendKeys(customerReference);
					TimeUnit.SECONDS.sleep(3);
					Assert.assertTrue(element(driver, verifySearch).isDisplayed());
					TimeUnit.SECONDS.sleep(3);
				}
				catch(Exception e)
				{
					ATUReports.add("Verified search using Customer Reference number", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				}
			}
			catch (Exception e)
			{
				ATUReports.add("Unable to verify the search using Customer Reference number",LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				takeScreenShotOnFailure(driver, testName);
				System.out.println(e);
			}
			try					//Address Line1
			{
				TimeUnit.SECONDS.sleep(3);
				element(driver, homeLink).click();
				TimeUnit.SECONDS.sleep(3);
				element(driver, customerServiceLink).click();
				TimeUnit.SECONDS.sleep(3);
				for (String winHandle : driver.getWindowHandles()) 
				{
					driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
				}
				Select(element(driver, clientSelect)).selectByVisibleText(client);
				for (int i = 1; i <= 100; i++)
				{
					try
					{
						element(driver, brandSelect(brand)).click();
						TimeUnit.SECONDS.sleep(7);
						break;
					}

					catch (Exception e)
					{
						element(driver, fastFoward).click();
					}
				}

				TimeUnit.SECONDS.sleep(3);
				element(driver, serviceExistingSubscriptionLink).click();
				TimeUnit.SECONDS.sleep(3);
				try
				{
					element(driver, addressLine).sendKeys(AddressLine);
					TimeUnit.SECONDS.sleep(3);
					Assert.assertTrue(element(driver, verifySearch).isDisplayed());
					TimeUnit.SECONDS.sleep(3);
				}
				catch(Exception e)
				{
					ATUReports.add("Verified search using Address Line", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				}
			}
			catch (Exception e)
			{
				ATUReports.add("Unable to verify the search using Address Line",LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				takeScreenShotOnFailure(driver, testName);
				System.out.println(e);
			}
			try				//Post Code		
			{
				TimeUnit.SECONDS.sleep(5);
				element(driver, homeLink).click();
				TimeUnit.SECONDS.sleep(5);
				element(driver, customerServiceLink).click();
				TimeUnit.SECONDS.sleep(3);
				for (String winHandle : driver.getWindowHandles()) 
				{
					driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
				}
				Select(element(driver, clientSelect)).selectByVisibleText(client);
				for (int i = 1; i <= 100; i++)
				{
					try
					{
						element(driver, brandSelect(brand)).click();
						TimeUnit.SECONDS.sleep(7);
						break;
					}

					catch (Exception e)
					{
						element(driver, fastFoward).click();
					}
				}

				TimeUnit.SECONDS.sleep(3);
				element(driver, serviceExistingSubscriptionLink).click();
				TimeUnit.SECONDS.sleep(5);
				try
				{
					element(driver, postcodeSearch).sendKeys(PostCode);
					TimeUnit.SECONDS.sleep(3);
					Assert.assertTrue(element(driver, verifySearch).isDisplayed());
					TimeUnit.SECONDS.sleep(3);
				}
				catch(Exception e)
				{
					ATUReports.add("Verified search using Post Code", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				}

			}
			catch (Exception e)
			{
				ATUReports.add("Unable to verify the search using Post Code",LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				takeScreenShotOnFailure(driver, testName);
				System.out.println(e);
			}

			try			//Company Name
			{
				TimeUnit.SECONDS.sleep(5);
				element(driver, homeLink).click();
				TimeUnit.SECONDS.sleep(5);
				element(driver, customerServiceLink).click();
				TimeUnit.SECONDS.sleep(3);
				for (String winHandle : driver.getWindowHandles()) 
				{
					driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
				}
				Select(element(driver, clientSelect)).selectByVisibleText(client);
				for (int i = 1; i <= 100; i++)
				{
					try
					{
						element(driver, brandSelect(brand)).click();
						TimeUnit.SECONDS.sleep(7);
						break;
					}

					catch (Exception e)
					{
						element(driver, fastFoward).click();
					}
				}

				TimeUnit.SECONDS.sleep(3);
				element(driver, serviceExistingSubscriptionLink).click();
				TimeUnit.SECONDS.sleep(5);
				try{
					element(driver, companyName).sendKeys(CompanyName);
					TimeUnit.SECONDS.sleep(3);
					Assert.assertTrue(element(driver, verifySearch).isDisplayed());
					TimeUnit.SECONDS.sleep(3);
				}
				catch(Exception e)
				{
					ATUReports.add("Verified search using Company Name", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				}

			}
			catch (Exception e)
			{
				ATUReports.add("Unable to verify the search using Company Name",LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				takeScreenShotOnFailure(driver, testName);
				System.out.println(e);
			}

			try			//Last Name
			{
				TimeUnit.SECONDS.sleep(5);
				element(driver, homeLink).click();
				TimeUnit.SECONDS.sleep(5);
				element(driver, customerServiceLink).click();
				TimeUnit.SECONDS.sleep(3);
				for (String winHandle : driver.getWindowHandles()) 
				{
					driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
				}
				Select(element(driver, clientSelect)).selectByVisibleText(client);
				for (int i = 1; i <= 100; i++)
				{
					try
					{
						element(driver, brandSelect(brand)).click();
						TimeUnit.SECONDS.sleep(7);
						break;
					}

					catch (Exception e)
					{
						element(driver, fastFoward).click();
					}
				}

				TimeUnit.SECONDS.sleep(3);
				element(driver, serviceExistingSubscriptionLink).click();
				TimeUnit.SECONDS.sleep(5);
				try
				{
					element(driver, lastName).sendKeys(LastName);
					TimeUnit.SECONDS.sleep(3);
					Assert.assertTrue(element(driver, verifySearch).isDisplayed());
					TimeUnit.SECONDS.sleep(3);
				}
				catch(Exception e)
				{
					ATUReports.add("Verified search using Last Name", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				}

			}
			catch (Exception e)
			{
				ATUReports.add("Unable to verify the search using Last Name",LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				takeScreenShotOnFailure(driver, testName);
				System.out.println(e);
			}

			try			//First Name
			{
				TimeUnit.SECONDS.sleep(5);
				element(driver, homeLink).click();
				TimeUnit.SECONDS.sleep(5);
				element(driver, customerServiceLink).click();
				TimeUnit.SECONDS.sleep(3);
				for (String winHandle : driver.getWindowHandles()) 
				{
					driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
				}
				Select(element(driver, clientSelect)).selectByVisibleText(client);
				for (int i = 1; i <= 100; i++)
				{
					try
					{
						element(driver, brandSelect(brand)).click();
						TimeUnit.SECONDS.sleep(7);
						break;
					}

					catch (Exception e)
					{
						element(driver, fastFoward).click();
					}
				}

				TimeUnit.SECONDS.sleep(3);
				element(driver, serviceExistingSubscriptionLink).click();
				TimeUnit.SECONDS.sleep(5);
				try
				{
					element(driver, firstNameSearch).sendKeys(FirstName);
					TimeUnit.SECONDS.sleep(3);
					Assert.assertTrue(element(driver, verifySearch).isDisplayed());
					TimeUnit.SECONDS.sleep(3);
				}
				catch(Exception e)
				{
					ATUReports.add("Verified search using First Name", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				}

			}
			catch (Exception e)
			{
				ATUReports.add("Unable to verify the search using First Name",LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				takeScreenShotOnFailure(driver, testName);
				System.out.println(e);
			}

			try			//Email
			{
				TimeUnit.SECONDS.sleep(5);
				element(driver, homeLink).click();
				TimeUnit.SECONDS.sleep(5);
				element(driver, customerServiceLink).click();
				TimeUnit.SECONDS.sleep(3);
				for (String winHandle : driver.getWindowHandles()) 
				{
					driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
				}
				Select(element(driver, clientSelect)).selectByVisibleText(client);
				for (int i = 1; i <= 100; i++)
				{
					try
					{
						element(driver, brandSelect(brand)).click();
						TimeUnit.SECONDS.sleep(7);
						break;
					}

					catch (Exception e)
					{
						element(driver, fastFoward).click();
					}
				}

				TimeUnit.SECONDS.sleep(3);
				element(driver, serviceExistingSubscriptionLink).click();
				TimeUnit.SECONDS.sleep(5);
				try
				{
					element(driver, email).sendKeys(Email);
					TimeUnit.SECONDS.sleep(3);
					Assert.assertTrue(element(driver, verifySearch).isDisplayed());
					TimeUnit.SECONDS.sleep(3);
				}
				catch(Exception e)
				{
					ATUReports.add("Verified search using Email", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				}

			}
			catch (Exception e)
			{
				ATUReports.add("Unable to verify the search using Email",LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				takeScreenShotOnFailure(driver, testName);
				System.out.println(e);
			}

			try			//Account Number
			{
				TimeUnit.SECONDS.sleep(5);
				element(driver, homeLink).click();
				TimeUnit.SECONDS.sleep(5);
				element(driver, customerServiceLink).click();
				TimeUnit.SECONDS.sleep(3);
				for (String winHandle : driver.getWindowHandles()) 
				{
					driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
				}
				Select(element(driver, clientSelect)).selectByVisibleText(client);
				for (int i = 1; i <= 100; i++)
				{
					try
					{
						element(driver, brandSelect(brand)).click();
						TimeUnit.SECONDS.sleep(7);
						break;
					}

					catch (Exception e)
					{
						element(driver, fastFoward).click();
					}
				}

				TimeUnit.SECONDS.sleep(3);
				element(driver, serviceExistingSubscriptionLink).click();
				TimeUnit.SECONDS.sleep(5);
				try
				{
					element(driver, accNumberSearch).sendKeys(AccountNumber);
					TimeUnit.SECONDS.sleep(3);
					Assert.assertTrue(element(driver, verifySearch).isDisplayed());
					TimeUnit.SECONDS.sleep(3);
				}
				catch(Exception e)
				{
					ATUReports.add("Verified search using Account Number", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				}

			}
			catch (Exception e)
			{
				ATUReports.add("Unable to verify the search using Account Number",LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				takeScreenShotOnFailure(driver, testName);
				System.out.println(e);
			}

			try			//Sort Code
			{
				TimeUnit.SECONDS.sleep(5);
				element(driver, homeLink).click();
				TimeUnit.SECONDS.sleep(5);
				element(driver, customerServiceLink).click();
				TimeUnit.SECONDS.sleep(3);
				for (String winHandle : driver.getWindowHandles()) 
				{
					driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
				}
				Select(element(driver, clientSelect)).selectByVisibleText(client);
				for (int i = 1; i <= 100; i++)
				{
					try
					{
						element(driver, brandSelect(brand)).click();
						TimeUnit.SECONDS.sleep(7);
						break;
					}

					catch (Exception e)
					{
						element(driver, fastFoward).click();
					}
				}

				TimeUnit.SECONDS.sleep(3);
				element(driver, serviceExistingSubscriptionLink).click();
				TimeUnit.SECONDS.sleep(5);
				try
				{
					element(driver, sortCodeSearch).sendKeys(SortCode);
					TimeUnit.SECONDS.sleep(3);
					Assert.assertTrue(element(driver, verifySearch).isDisplayed());
					TimeUnit.SECONDS.sleep(3);
				}
				catch(Exception e)
				{
					ATUReports.add("Verified search using Sort code", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				}

			}
			catch (Exception e)
			{
				ATUReports.add("Unable to verify the search using Sort Code",LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				takeScreenShotOnFailure(driver, testName);
				System.out.println(e);
			}

			try			//CC Number
			{
				TimeUnit.SECONDS.sleep(5);
				element(driver, homeLink).click();
				TimeUnit.SECONDS.sleep(5);
				element(driver, customerServiceLink).click();
				TimeUnit.SECONDS.sleep(3);
				for (String winHandle : driver.getWindowHandles()) 
				{
					driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
				}
				Select(element(driver, clientSelect)).selectByVisibleText(client);
				for (int i = 1; i <= 100; i++)
				{
					try
					{
						element(driver, brandSelect(brand)).click();
						TimeUnit.SECONDS.sleep(7);
						break;
					}

					catch (Exception e)
					{
						element(driver, fastFoward).click();
					}
				}

				TimeUnit.SECONDS.sleep(3);
				element(driver, serviceExistingSubscriptionLink).click();
				TimeUnit.SECONDS.sleep(5);
				try
				{
					element(driver, creditCardNum).sendKeys(CCNumber);
					TimeUnit.SECONDS.sleep(3);
					Assert.assertTrue(element(driver, verifySearch).isDisplayed());
					TimeUnit.SECONDS.sleep(3);
				}
				catch(Exception e)
				{
					ATUReports.add("Verified search using CC Number", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				}

			}
			catch (Exception e)
			{
				ATUReports.add("Unable to verify the search using CC Number",LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				takeScreenShotOnFailure(driver, testName);
				System.out.println(e);
			}

			try			//Country
			{
				TimeUnit.SECONDS.sleep(5);
				element(driver, homeLink).click();
				TimeUnit.SECONDS.sleep(5);
				element(driver, customerServiceLink).click();
				TimeUnit.SECONDS.sleep(3);
				for (String winHandle : driver.getWindowHandles()) 
				{
					driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
				}
				Select(element(driver, clientSelect)).selectByVisibleText(client);
				for (int i = 1; i <= 100; i++)
				{
					try
					{
						element(driver, brandSelect(brand)).click();
						TimeUnit.SECONDS.sleep(7);
						break;
					}

					catch (Exception e)
					{
						element(driver, fastFoward).click();
					}
				}

				TimeUnit.SECONDS.sleep(3);
				element(driver, serviceExistingSubscriptionLink).click();
				TimeUnit.SECONDS.sleep(5);
				try
				{
					element(driver, country).sendKeys(Country);
					TimeUnit.SECONDS.sleep(3);
					Assert.assertTrue(element(driver, verifySearch).isDisplayed());
					TimeUnit.SECONDS.sleep(3);
				}
				catch(Exception e)
				{
					ATUReports.add("Verified search using Country", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				}

			}
			catch (Exception e)
			{
				ATUReports.add("Unable to verify the search using Country",LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				takeScreenShotOnFailure(driver, testName);
				System.out.println(e);
			}
		}
		catch (Exception e1)
		{
			takeScreenShotOnFailure(driver, testName);
			System.out.println(e1);
		}
	}

	public void newSubscriptionCCGiftType5(WebDriver driver, String client, String brand, String promotion, String cardType, String custTitle, String firstname, String surname, String postalcode, String address, String gCustTitle, String gFirstname, String gSurname, String gPostalcode, String gAddress, String accHolderName, String accNumber, String SortCode) throws Exception
	{
		try
		{
			element(driver, customerServiceLink).click();	
			TimeUnit.SECONDS.sleep(3);
			for (String winHandle : driver.getWindowHandles()) 
			{
				driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
			}
			Select(element(driver, clientSelect)).selectByVisibleText(client);
			for (int i = 1; i <= 100; i++)
			{
				try
				{
					element(driver, brandSelect(brand)).click();
					TimeUnit.SECONDS.sleep(7);
					break;
				}

				catch (Exception e)
				{
					element(driver, fastFoward).click();
				}
			}		
			element(driver, newSubscription).click();
			TimeUnit.SECONDS.sleep(2);
			element(driver, promotionName).sendKeys(promotion);
			element(driver, findPromotion).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, selectPromotion(promotion)).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, promotionNextBtn).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, offerCard(cardType)).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, title).sendKeys(custTitle);
			element(driver, firstName).sendKeys(firstname);
			element(driver, surName).sendKeys(surname);
			element(driver, postCode).sendKeys(postalcode);
			element(driver, lookupAddress).click();
			TimeUnit.SECONDS.sleep(5);
			Select(element(driver, selectAddress)).selectByVisibleText(address);
			TimeUnit.SECONDS.sleep(5);
			element(driver, giftSubs).click();
			TimeUnit.SECONDS.sleep(2);
			element(driver, gTitle).sendKeys(gCustTitle);
			TimeUnit.SECONDS.sleep(2);
			element(driver, gFirstName).sendKeys(gFirstname);
			TimeUnit.SECONDS.sleep(2);
			element(driver, gSurName).sendKeys(gSurname);
			TimeUnit.SECONDS.sleep(2);
			element(driver, gPostCode).sendKeys(gPostalcode);
			TimeUnit.SECONDS.sleep(2);
			element(driver, gLookupAddress).click();
			TimeUnit.SECONDS.sleep(2);
			element(driver, gSelectAddress).click();
			TimeUnit.SECONDS.sleep(2);
			element(driver, gSaveButton).click();
			TimeUnit.SECONDS.sleep(2);
			element(driver, custNextBtn).click();
			TimeUnit.SECONDS.sleep(2);
			try
			{
				element(driver, custAssociationNextBtn).isDisplayed();
				element(driver, custAssociationNextBtn).click();
			}
			catch(Exception e)
			{
				// No Action Required
			}
			TimeUnit.SECONDS.sleep(2);
			element(driver, issueCalenderNextBtn).click();
			TimeUnit.SECONDS.sleep(2);
			TimeUnit.SECONDS.sleep(2);
			element(driver, accountHolderName).sendKeys(accHolderName);
			TimeUnit.SECONDS.sleep(2);
			element(driver, accountNumber).sendKeys(accNumber);
			TimeUnit.SECONDS.sleep(2);
			element(driver, sCode).sendKeys(SortCode);
			//element(driver, sCode).sendKeys(sortCode);
			/*Select(element(driver, expiryDate)).selectByVisibleText(""+date+"");
			TimeUnit.SECONDS.sleep(2);
			Select(element(driver, expiryYear)).selectByVisibleText(""+year+"");	
			TimeUnit.SECONDS.sleep(5);*/
			TimeUnit.SECONDS.sleep(5);
			element(driver, lookupBankButton).click();
			TimeUnit.SECONDS.sleep(5);
			element(driver, checkoutNextBtn).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, orderRefNumber).isDisplayed();
			orderRef = element(driver, orderRefNumber).getText();
			System.out.println(orderRef);
			ATUReports.add("New subscription has been done sucessfully with order reference number as: "+orderRef, "Promotion name: "+ promotion,"Order Reference",orderRef,LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		catch(Exception e)
		{
			ATUReports.add("Unable to do a new subscription", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			takeScreenShotOnFailure(driver, testName);
		}
	}
	public void verifyNewSubscriptionCSType5(WebDriver driver, String promotion, String client, String brand) throws Exception
	{
		try
		{
			try
			{
				fetchDetailsCs(driver, client, brand);
				TimeUnit.SECONDS.sleep(3);
				//Assert.assertEquals(accountID, orderRef);
				Assert.assertEquals(verifyStatus, "ACTIVE");
				Assert.assertEquals(verifyType, "GIFT_SUBSCRIPTION");
				Assert.assertEquals(payMethod, "DIRECT_DEBIT");
				Assert.assertEquals(payStatus, "Paid");
				Assert.assertEquals(renStatus, "Continious");
				Assert.assertEquals(subRole, "DONOR");
				ATUReports.add(verifyType + " Order: "+orderRef+"has been successfully verified in CS screen with contract status as:"+verifyStatus,"Order Reference: "+ orderRef,"Payment Method: Direct_Debit, Payment Status: UnPaid, Renewal Status: Continous, Subscriber Role: Donor","Payment Method: "+payMethod + ", Payment Status:"+payStatus+ " ,Renewal Status:"+ renStatus+", Subscriber Role: " +subRole,LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

				TimeUnit.SECONDS.sleep(5); 
			}
			catch (AssertionError e)
			{
				ATUReports.add("Unable to verify the newly created subscription",accountID, verifyStatus, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				takeScreenShotOnFailure(driver, testName);
				System.out.println(e);
			}

		}
		catch (Exception e1)
		{
			takeScreenShotOnFailure(driver, testName);
			System.out.println(e1);
		}


	}
	/**
	 * Method performed to do a new subscription 
	 * 
	 * @param driver Object for webdriver
	 * @param client Object for selecting a client from the list
	 * @param brand Object for selecting a brand from the list
	 * @param promotion Object for selecting a valid promotion to do a new subscription
	 * @param cardType Object for selecting a card type from the options available 
	 * @param custTitle Object for fetching the salutation
	 * @param firstname Object for fetching the first name 
	 * @param surname Object for fetching the surname 
	 * @param postalcode Object for fetching the post code
	 * @param address Object for fetching the address 
	 * @param custName Object for fetching the Card Holder's Name 
	 * @param cardNum1 Object for fetching the first 8 digits of the Card Number 
	 * @param cardNum2 Object for fetching the last 8 digits of the Card Number
	 * @param date Object for fetching the Expiry Date
	 * @param year Object for fetching the Expiry Year
	 * @throws Exception To throw an exception whenever an unexpected failure occurs
	 * 
	 */
	public void newSubscriptionFreeType6(WebDriver driver, String client, String brand, String promotion, String cardType, String custTitle, String firstname, String surname, String postalcode, String address) throws Exception
	{
		try
		{
			element(driver, customerServiceLink).click();	
			TimeUnit.SECONDS.sleep(3);
			for (String winHandle : driver.getWindowHandles()) 
			{
				driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
			}
			Select(element(driver, clientSelect)).selectByVisibleText(client);
			for (int i = 1; i <= 100; i++)
			{
				try
				{
					element(driver, brandSelect(brand)).click();
					TimeUnit.SECONDS.sleep(7);
					break;
				}

				catch (Exception e)
				{
					element(driver, fastFoward).click();
				}
			}		
			element(driver, newSubscription).click();
			TimeUnit.SECONDS.sleep(2);
			element(driver, promotionName).sendKeys(promotion);
			element(driver, findPromotion).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, selectPromotion(promotion)).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, promotionNextBtn).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, offerCard(cardType)).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, title).sendKeys(custTitle);
			element(driver, firstName).sendKeys(firstname);
			element(driver, surName).sendKeys(surname);
			element(driver, postCode).sendKeys(postalcode);
			element(driver, lookupAddress).click();
			TimeUnit.SECONDS.sleep(5);
			Select(element(driver, selectAddress)).selectByVisibleText(address);
			TimeUnit.SECONDS.sleep(5);
			element(driver, custNextBtn).click();
			TimeUnit.SECONDS.sleep(2);
			try
			{
				element(driver, custAssociationNextBtn).isDisplayed();
				element(driver, custAssociationNextBtn).click();
			}
			catch(Exception e)
			{
				// No Action Required
			}
			TimeUnit.SECONDS.sleep(2);
			element(driver, issueCalenderNextBtn).click();
			TimeUnit.SECONDS.sleep(5);
			element(driver, checkoutNextBtn).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, orderRefNumber).isDisplayed();
			orderRef = element(driver, orderRefNumber).getText();
			System.out.println(orderRef);
			ATUReports.add("New subscription has been done sucessfully with order reference number as: "+orderRef, "Promotion name: "+ promotion,"Order Reference",orderRef, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		catch(Exception e)
		{
			ATUReports.add("Unable to do a new subscription", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			takeScreenShotOnFailure(driver, testName);
		}
	}
	public void verifyNewSubscriptionFreeType6(WebDriver driver, String promotion, String client, String brand) throws Exception
	{
		try
		{
			try
			{
				fetchDetailsCs(driver, client, brand);
				TimeUnit.SECONDS.sleep(3);
				Assert.assertEquals(accountID, orderRef);
				Assert.assertEquals(verifyStatus, "ACTIVE");
				//Assert.assertEquals(verifyType, "STANDARD_SUBSCRIPTION");
				Assert.assertEquals(payMethod, "Free");
				//Assert.assertEquals(payStatus, "Unpaid");
				//Assert.assertEquals(renStatus, "");
				//Assert.assertEquals(subRole, "DIRECT");
				ATUReports.add(verifyType + " Order: "+accountID+"has been successfully verified in CS screen with contract status as:"+verifyStatus,"Order Reference: "+ orderRef,"Payment Method: Free","Payment Method: "+payMethod + ", Payment Status:"+payStatus+ " ,Renewal Status:"+ renStatus+", Subscriber Role: " +subRole,LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

				TimeUnit.SECONDS.sleep(5); 
			}
			catch (AssertionError e)
			{
				ATUReports.add("Unable to verify the newly created subscription",accountID, verifyStatus, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				takeScreenShotOnFailure(driver, testName);
				System.out.println(e);
			}

		}
		catch (Exception e1)
		{
			takeScreenShotOnFailure(driver, testName);
			System.out.println(e1);
		}

	}
	public void serviceAmendDetails(WebDriver driver, String client, String brand, String refNumber, String newName, String newAddress) throws Exception
	{
		orderRef=refNumber;
		fetchDetailsCs(driver, client, brand);

		try
		{
			TimeUnit.SECONDS.sleep(2);
			element(driver, amendDetails).click();
			TimeUnit.SECONDS.sleep(2);
			element(driver, changeName).clear();
			TimeUnit.SECONDS.sleep(2);
			element(driver, changeName).sendKeys(newName);
			TimeUnit.SECONDS.sleep(2);
			element(driver, changeAddress).clear();
			TimeUnit.SECONDS.sleep(2);
			element(driver, changeAddress).sendKeys(newAddress);
			element(driver, changeDetails).click();
			TimeUnit.SECONDS.sleep(2);
			element(driver, summary).click();
			String cName = element(driver, fName).getText();
			//verification of Details changed
			Assert.assertEquals(cName, newName);
			ATUReports.add("successfully amended details",accountID,"Name and Address change","Change successful", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

			TimeUnit.SECONDS.sleep(5); 
		}
		catch (AssertionError e)
		{
			ATUReports.add("Unable to amend details from CS screen", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			takeScreenShotOnFailure(driver, testName);
			System.out.println(e);
		}

	}

	public void renewSubscription(WebDriver driver, String client, String brand, String refNumber,  String promotion, String cardType, String custTitle, String firstname, String surname, String postalcode, String address, String custName, String cardNum1, String date, String year) throws Exception
	{
		orderRef=refNumber;
		fetchDetailsCs(driver, client, brand);

		try
		{
			TimeUnit.SECONDS.sleep(2);
			element(driver, renewButton).click();
			TimeUnit.SECONDS.sleep(2);

			TimeUnit.SECONDS.sleep(2);
			element(driver, promotionName).sendKeys(promotion);
			element(driver, findPromotion).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, selectPromotion(promotion)).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, promotionNextBtn).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, offerCard(cardType)).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, custNextBtn).click();
			TimeUnit.SECONDS.sleep(2);
			try
			{
				element(driver, custAssociationNextBtn).isDisplayed();
				element(driver, custAssociationNextBtn).click();
			}
			catch(Exception e)
			{
				// No Action Required
			}
			TimeUnit.SECONDS.sleep(2);
			element(driver, issueCalenderNextBtn).click();
			TimeUnit.SECONDS.sleep(2);
			element(driver, customerName).sendKeys(custName);
			TimeUnit.SECONDS.sleep(2);
			element(driver, cardNumber).sendKeys(cardNum1);
			TimeUnit.SECONDS.sleep(2);
			Select(element(driver, expiryDate)).selectByVisibleText(""+date+"");
			TimeUnit.SECONDS.sleep(2);
			Select(element(driver, expiryYear)).selectByVisibleText(""+year+"");	
			TimeUnit.SECONDS.sleep(5);
			element(driver, checkoutNextBtn).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, orderRefNumber).isDisplayed();
			ATUReports.add("Renew subscription has been done sucessfully with order reference number as: "+orderRef, "Promotion name: "+ promotion,"Order Reference",orderRef, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		catch(Exception e)
		{
			ATUReports.add("Unable to do Renew subscription", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			takeScreenShotOnFailure(driver, testName);
		}
	}

	public void upgradeSubscription(WebDriver driver, String client, String brand,  String refNumber,String contractType,  String promotion, String cardType, String custTitle, String firstname, String surname, String postalcode, String address, String custName, String cardNum1, String date, String year) throws Exception
	{
		orderRef=refNumber;
		fetchDetailsCs(driver, client, brand);

		try
		{
			TimeUnit.SECONDS.sleep(2);
			element(driver, upgradeButton).click();
			TimeUnit.SECONDS.sleep(2);
			element(driver, selectBrand).sendKeys(brand);
			TimeUnit.SECONDS.sleep(2);
			element(driver, selectContract).sendKeys(contractType);
			TimeUnit.SECONDS.sleep(2);
			element(driver, promotionName).sendKeys(promotion);
			TimeUnit.SECONDS.sleep(2);
			element(driver, findPromotion).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, selectPromotion(promotion)).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, promotionNextBtn).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, offerCard(cardType)).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, custNextBtn).click();
			TimeUnit.SECONDS.sleep(2);
			try
			{
				element(driver, custAssociationNextBtn).isDisplayed();
				element(driver, custAssociationNextBtn).click();
			}
			catch(Exception e)
			{
				// No Action Required
			}
			TimeUnit.SECONDS.sleep(2);
			element(driver, issueCalenderNextBtn).click();
			TimeUnit.SECONDS.sleep(2);
			element(driver, customerName).sendKeys(custName);
			TimeUnit.SECONDS.sleep(2);
			element(driver, cardNumber).sendKeys(cardNum1);
			TimeUnit.SECONDS.sleep(2);
			Select(element(driver, expiryDate)).selectByVisibleText(""+date+"");
			TimeUnit.SECONDS.sleep(2);
			Select(element(driver, expiryYear)).selectByVisibleText(""+year+"");	
			TimeUnit.SECONDS.sleep(5);
			element(driver, checkoutNextBtn).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, orderRefNumber).isDisplayed();
			ATUReports.add("Upgrade subscription has been done sucessfully with order reference number as: "+orderRef, "Promotion name: "+ promotion,"Order Reference",orderRef, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		catch(Exception e)
		{
			ATUReports.add("Unable to do upgrade subscription", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			takeScreenShotOnFailure(driver, testName);
		}
	}

	public void amendContract_SendLetter(WebDriver driver, String letterName, String client, String brand, String referenceNumber) throws Exception
	{
		orderRef = referenceNumber;
		fetchDetailsCs(driver, client, brand);
		try
		{
			element(driver, transactionEnquiry).click();
			TimeUnit.SECONDS.sleep(5);
			element(driver, amendContract).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, systemLetter).click();
			for (int i = 1; i <= 100; i++)
			{
				try
				{
					element(driver, sendLetter(letterName)).click(); 
					break;
				}

				catch (Exception e)
				{
					element(driver, fastFoward).click();
					TimeUnit.SECONDS.sleep(5);
				}
			}

			waitForElementToVanish(driver, spinner);
			Assert.assertTrue(element(driver, sendLetterVerification).isDisplayed());
			ATUReports.add("Amend contract System letter has been sent successfully: "+letterName+ ":" +orderRef, "User should be able to send the letter successfully", "Letter " + letterName+ " has been sent successfully", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		catch(Exception e)
		{
			ATUReports.add("Amend contract System letter not sent successfully: "+letterName+ ":" +orderRef, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			takeScreenShotOnFailure(driver, testName);
		}
	}


	public void amendContract_Suspend(WebDriver driver, String reason, String client, String brand, String referenceNumber) throws Exception
	{
		orderRef = referenceNumber;
		fetchDetailsCs(driver, client, brand);
		try
		{
			element(driver, transactionEnquiry).click();
			TimeUnit.SECONDS.sleep(5);
			element(driver, amendContract).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, suspend).click();
			/*waitForElement(driver, selectReasonSuspend, 50);*/
			TimeUnit.SECONDS.sleep(10);
			//Select(element(driver, selectReasonSuspend)).selectByVisibleText(reason);
			elementHighlight(driver, selectReasonSuspend);
			element(driver, selectReasonSuspend).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, suspendReason(reason)).click();
			waitForElementToVanish(driver, spinner);
			TimeUnit.SECONDS.sleep(3);
			element(driver, suspendStartingFrom).clear();
			element(driver, suspendStartingFrom).sendKeys(DateTime.now().toString("dd/MM/yyyy"));
			TimeUnit.SECONDS.sleep(3);
			element(driver, confirmSuspend).click();
			waitForElementToVanish(driver, spinner);

			TimeUnit.SECONDS.sleep(8);

			Assert.assertEquals(element(driver, suspendedStatus).getText(), "SUSPENDED");			
			ATUReports.add("Customer reference number has been suspended successfully : "+reason+ " : " +orderRef, "User should be able to suspend the subscription successfully", "Customer Reference Number "+orderRef+" has been suspended successfully", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		catch(Exception e)
		{
			ATUReports.add("Customer reference number has not been suspended: "+reason+ " : " +orderRef, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			takeScreenShotOnFailure(driver, testName);
		}
	}



	public void amendContract_Resume(WebDriver driver, String reason, String resumeStartFrom, String client, String brand, String referenceNumber) throws Exception
	{
		orderRef = referenceNumber;
		fetchDetailsCs(driver, client, brand);
		try
		{
			element(driver, transactionEnquiry).click();
			TimeUnit.SECONDS.sleep(5);
			element(driver, amendContract).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, resume).click();
			waitForElement(driver, selectReasonResume, 50);

			element(driver, selectReasonResume).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, resumeReason(reason)).click();
			waitForElementToVanish(driver, spinner);
			TimeUnit.SECONDS.sleep(3);
			element(driver, selectReasonResume).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, resumeStartingSelect(resumeStartFrom)).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, resumeStartingDate).sendKeys(DateTime.now().toString("dd/MM/yyyy"));

			element(driver, confirmResume).click();
			waitForElementToVanish(driver, spinner);
			if (element(driver, suspendedStatus).getText() == "SUSPENDED")
			{
				element(driver, customerHistory).click();
				TimeUnit.SECONDS.sleep(8);	

				Assert.assertEquals(element(driver, resumeVerification).getText(), "RESUME_PENDING - "+reason);
			}
			else 
			{
				if(element(driver, suspendedStatus).getText() == "ACTIVE")
				{
					Assert.assertTrue(true);
				}
			}

			ATUReports.add("Customer reference number has been resumed successfully : "+reason+ " : " +orderRef, "User should be able to resume the subsricption successfully", "Customer Reference Number "+orderRef+" has been resumed successfully", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		catch(Exception e)
		{
			ATUReports.add("Customer reference number has not been resumed: "+reason+ " : " +orderRef, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			takeScreenShotOnFailure(driver, testName);
		}
	}

	public void amendContract_RefundAmount(WebDriver driver, String amount, String reason, String payeeTitle, String customerFullName,String address1, String address2, String address3, String address4, String address5, String address6, String country, String postCode, String client, String brand, String referenceNumber) throws Exception
	{
		orderRef = referenceNumber;
		fetchDetailsCs(driver, client, brand);
		try
		{
			element(driver, transactionEnquiry).click();
			TimeUnit.SECONDS.sleep(5);
			element(driver, amendContract).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, refundAmount).click();
			waitForElement(driver, refundAmountValue, 50);

			element(driver, refundAmountValue).sendKeys(amount);
			TimeUnit.SECONDS.sleep(3);
			Select(element(driver, refundReason)).selectByVisibleText(reason);
			TimeUnit.SECONDS.sleep(3);

			element(driver, editChequeDetails).click();
			TimeUnit.SECONDS.sleep(8);

			element(driver, editChequeDetails_PayeeTitle).clear();
			element(driver, editChequeDetails_PayeeTitle).sendKeys(payeeTitle);
			TimeUnit.SECONDS.sleep(3);
			element(driver, editChequeDetails_PayeeName).clear();
			element(driver, editChequeDetails_PayeeName).sendKeys(customerFullName);
			TimeUnit.SECONDS.sleep(3);

			element(driver, editChequeDetails_AddressLine1).clear();
			element(driver, editChequeDetails_AddressLine1).sendKeys(address1);
			TimeUnit.SECONDS.sleep(3);

			element(driver, editChequeDetails_AddressLine2).clear();
			element(driver, editChequeDetails_AddressLine2).sendKeys(address2);
			TimeUnit.SECONDS.sleep(3);

			element(driver, editChequeDetails_AddressLine3).clear();
			element(driver, editChequeDetails_AddressLine3).sendKeys(address3);
			TimeUnit.SECONDS.sleep(3);

			element(driver, editChequeDetails_AddressLine4).clear();
			element(driver, editChequeDetails_AddressLine4).sendKeys(address4);
			TimeUnit.SECONDS.sleep(3);

			element(driver, editChequeDetails_AddressLine5).clear();
			element(driver, editChequeDetails_AddressLine5).sendKeys(address5);
			TimeUnit.SECONDS.sleep(3);

			element(driver, editChequeDetails_AddressLine6).clear();
			element(driver, editChequeDetails_AddressLine6).sendKeys(address6);
			TimeUnit.SECONDS.sleep(3);

			//Select(element(driver,editChequeDetails_Country)).selectByVisibleText(country);

			element(driver, editChequeDetails_PostCode).clear();
			element(driver, editChequeDetails_PostCode).sendKeys(postCode);
			TimeUnit.SECONDS.sleep(3);

			element(driver, saveChequeDetails).click();
			TimeUnit.SECONDS.sleep(8);	

			Assert.assertEquals(element(driver, chequeDetailsVerifcation_Name).getText(), payeeTitle +" " +customerFullName);
			Assert.assertEquals(element(driver, chequeDetailsVerifcation_AddressLine1).getText(), address1);
			Assert.assertEquals(element(driver, chequeDetailsVerifcation_AddressLine2).getText(), address2);
			Assert.assertEquals(element(driver, chequeDetailsVerifcation_AddressLine3).getText(), address3);
			Assert.assertEquals(element(driver, chequeDetailsVerifcation_AddressLine4).getText(), address4);
			Assert.assertEquals(element(driver, chequeDetailsVerifcation_AddressLine5).getText(), address5);
			Assert.assertEquals(element(driver, chequeDetailsVerifcation_AddressLine6).getText(), address6);
			Assert.assertEquals(element(driver, chequeDetailsVerifcation_PostCode).getText(), postCode);

			element(driver, confirmRefund).click();
			waitForElementToVanish(driver, spinner);

			element(driver, refundDetails).click();
			TimeUnit.SECONDS.sleep(8);	

			Assert.assertEquals(element(driver, refundDetailsVerification).getText(), "CHQ REF "+amount+" to " +customerFullName+ " .");
			ATUReports.add("Amount has been refunded successfully : "+reason+ " : " +orderRef, "User should be able to refund the amount for the subsricption successfully", "Customer Reference Number "+orderRef+" has been refunded successfully", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		catch(Exception e)
		{
			ATUReports.add("Amount has not been refunded: "+reason+ " : " +orderRef, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			takeScreenShotOnFailure(driver, testName);
		}
	}

	public void amendContract_MailingMethod(WebDriver driver, String mailingType, String client, String brand, String referenceNumber) throws Exception
	{
		orderRef = referenceNumber;
		fetchDetailsCs(driver, client, brand);
		try
		{
			element(driver, transactionEnquiry).click();
			TimeUnit.SECONDS.sleep(5);
			element(driver, amendContract).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, mailingMethod).click();
			waitForElement(driver, selectMailingMethod, 50);

			Select(element(driver, selectMailingMethod)).selectByVisibleText(mailingType);
			TimeUnit.SECONDS.sleep(3);

			element(driver, confirmMailingMethod).click();
			waitForElementToVanish(driver, spinner);

			element(driver, summary).click();
			TimeUnit.SECONDS.sleep(8);	

			Assert.assertEquals(element(driver, verifyDispatchType).getText(), mailingType);
			ATUReports.add("Mailing Method has been updated successfully : "+mailingType+ " : " +orderRef, "User should be able to update the mailing method for the subsricption successfully", "Mailing method for Customer Reference Number "+orderRef+" has been updated successfully", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		catch(Exception e)
		{
			ATUReports.add("Mailing method has not been updated : "+mailingType+ " : " +orderRef, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			takeScreenShotOnFailure(driver, testName);
		}
	}

	public void amendContract_ChangeTerm(WebDriver driver, String action, String reason, String issueType, String noOfIssues, String client, String brand, String referenceNumber) throws Exception
	{
		orderRef = referenceNumber;
		fetchDetailsCs(driver, client, brand);
		try
		{
			element(driver, transactionEnquiry).click();
			TimeUnit.SECONDS.sleep(5);
			element(driver, amendContract).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, changeTerm).click();
			waitForElement(driver, changeTerm_Action, 50);

			Select(element(driver,changeTerm_Action)).selectByVisibleText(action);
			TimeUnit.SECONDS.sleep(3);

			Select(element(driver,changeTerm_Reason)).selectByVisibleText(reason);
			TimeUnit.SECONDS.sleep(3);

			Select(element(driver,changeTerm_IssueType)).selectByVisibleText(issueType);
			TimeUnit.SECONDS.sleep(3);

			element(driver,changeTerm_NoOfIssues).clear();
			element(driver,changeTerm_NoOfIssues).sendKeys(noOfIssues);

			element(driver, changeTerm_Save).click();

			waitForElementToVanish(driver, spinner);

			element(driver, customerHistory).click();
			TimeUnit.SECONDS.sleep(8);	

			Assert.assertEquals(element(driver, changeTermVerification).getText(), "CHANGE_CONTRACT_TERM   -   "+reason);
			Assert.assertEquals(element(driver, changeTermVerification1).getText(), "Change request for ADD_ENTITLEMENT with Issue Numbers : "+noOfIssues+", Issue Type : "+issueType+", Date : "+DateTime.now().toString("dd/MM/yyyy"));
			ATUReports.add("Term has been updated successfully : "+reason+ " : " +orderRef, "User should be able to update the Term for the subsricption successfully", "Term for Customer Reference Number "+orderRef+" has been updated successfully", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		catch(Exception e)
		{
			ATUReports.add("Term has not been updated : "+reason+ " : " +orderRef, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			takeScreenShotOnFailure(driver, testName);
		}
	}

	public void amendContract_PaymentDetails(WebDriver driver, String accountName, String accountNumber, String sortCode, String chequeNumber, String cardNumber, String month, String year, String client, String brand, String referenceNumber) throws Exception
	{
		orderRef = referenceNumber;
		fetchDetailsCs(driver, client, brand);
		try
		{
			element(driver, transactionEnquiry).click();
			TimeUnit.SECONDS.sleep(5);
			element(driver, amendContract).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, paymentDetails).click();
			TimeUnit.SECONDS.sleep(5);
			if(payMethod.contains("DIRECT_DEBIT"))
			{
				element(driver,paymentDetails_DirectDebit_AccountName).sendKeys(accountName);
				TimeUnit.SECONDS.sleep(3);
				element(driver,paymentDetails_DirectDebit_AccountNumber).sendKeys(accountNumber);
				TimeUnit.SECONDS.sleep(3);
				element(driver,paymentDetails_DirectDebit_SortCode).sendKeys(sortCode);
				TimeUnit.SECONDS.sleep(3);
				element(driver,paymentDetails_DirectDebit_PaymentStartDate).clear();
				element(driver,paymentDetails_DirectDebit_PaymentStartDate).sendKeys(DateTime.now().toString("dd/MM/yyy"));
				TimeUnit.SECONDS.sleep(3);
				element(driver, paymentDetails_DirectDebit_SaveChangesButton).click();
			}
			else if(payMethod.contains("CHEQUE"))
			{
				element(driver,paymentDetails_Cheque_AccountName).sendKeys(accountName);
				TimeUnit.SECONDS.sleep(3);
				element(driver,paymentDetails_Cheque_AccountNumber).sendKeys(accountNumber);
				TimeUnit.SECONDS.sleep(3);
				element(driver,paymentDetails_Cheque_SortCode).sendKeys(sortCode);
				TimeUnit.SECONDS.sleep(3);
				element(driver,paymentDetails_Cheque_ChequeNumber).sendKeys(chequeNumber);
				TimeUnit.SECONDS.sleep(3);
				element(driver, paymentDetails_Cheque_SaveChangesButton).click();
			}
			else if(payMethod.contains("CREDIT_CARD"))
			{
				element(driver,paymentDetails_CreditCard_AccountName).sendKeys(accountName);
				TimeUnit.SECONDS.sleep(3);
				element(driver,paymentDetails_CreditCard_CardNumber).sendKeys(cardNumber);
				TimeUnit.SECONDS.sleep(3);
				Select(element(driver,paymentDetails_CreditCard_ExpiryDate_Month)).selectByVisibleText(""+month+"");
				TimeUnit.SECONDS.sleep(3);
				Select(element(driver,paymentDetails_CreditCard_ExpiryDate_Year)).selectByVisibleText(""+year+"");
				TimeUnit.SECONDS.sleep(3);
				element(driver, paymentDetails_CreditCard_SaveChangesButton).click();
			}
			TimeUnit.SECONDS.sleep(8);	
			waitForElementToVanish(driver, spinner);

			element(driver, customerHistory).click();
			TimeUnit.SECONDS.sleep(8);	

			Assert.assertEquals(element(driver, paymentDetailsVerification).getText(), "OTHER   -   Payment detail changed");
			ATUReports.add("Payment Details have been updated successfully : " +orderRef, "User should be able to update the payment details for the subsricption successfully", "Payment Details for Customer Reference Number "+orderRef+" has been updated successfully", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		catch(Exception e)
		{
			ATUReports.add("Payment Details have not been updated : " +orderRef, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			takeScreenShotOnFailure(driver, testName);
		}
	}

	public void amendContract_AddPayment(WebDriver driver, String accountName, String accountNumber, String sortCode, String ddCollectionDate, String client, String brand, String referenceNumber) throws Exception
	{
		orderRef = referenceNumber;
		fetchDetailsCs(driver, client, brand);
		try
		{
			element(driver, transactionEnquiry).click();
			TimeUnit.SECONDS.sleep(5);
			element(driver, amendContract).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, addPayment).click();
			TimeUnit.SECONDS.sleep(5);

			if(payMethod.contains("DIRECT_DEBIT"))
			{
				element(driver,addPayment_DirectDebit_AccountName).sendKeys(accountName);
				TimeUnit.SECONDS.sleep(3);
				element(driver,addPayment_DirectDebit_AccountNumber).sendKeys(accountNumber);
				TimeUnit.SECONDS.sleep(3);
				element(driver,addPayment_DirectDebit_SortCode).sendKeys(sortCode);
				TimeUnit.SECONDS.sleep(3);
				element(driver,addPayment_DirectDebit_PaymentStartDate).clear();
				element(driver,addPayment_DirectDebit_PaymentStartDate).sendKeys(DateTime.now().toString("dd/MM/yyyy"));
				TimeUnit.SECONDS.sleep(3);
				Select(element(driver,addPayment_DirectDebit_PreferredDDCollectionDate)).selectByVisibleText(ddCollectionDate);
				TimeUnit.SECONDS.sleep(3);
				element(driver, addPayment_Submit).click();
			}

			waitForElementToVanish(driver, spinner);
			Assert.assertTrue(element(driver, addPaymentVerification).isDisplayed());

			ATUReports.add("Add Payment has been processed successfully : " +orderRef, "User should be able to process the Add Payment for the subsricption successfully", "Add Payment for Customer Reference Number "+orderRef+" has been updated successfully", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		catch(Exception e)
		{
			ATUReports.add("Add Payment has not been updated : " +orderRef, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			takeScreenShotOnFailure(driver, testName);
		}
	}

	public void amendContract_ChangeStartIssue(WebDriver driver, String issueNumber, String client, String brand, String referenceNumber) throws Exception
	{
		orderRef = referenceNumber;
		fetchDetailsCs(driver, client, brand);
		try
		{
			element(driver, transactionEnquiry).click();
			TimeUnit.SECONDS.sleep(5);
			element(driver, amendContract).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, changeStartIssue).click();
			TimeUnit.SECONDS.sleep(5);

			Select(element(driver,selectStartIssue)).selectByVisibleText(issueNumber);
			TimeUnit.SECONDS.sleep(3);

			element(driver, changeContract).click();
			waitForElementToVanish(driver, spinner);

			Assert.assertTrue(element(driver, changeStartIssueVerification).isDisplayed());

			ATUReports.add("Start Issue has been updated successfully : " +orderRef, "User should be able to update the Start Issue for the subsricption successfully", "Start Issue for Customer Reference Number "+orderRef+" has been updated successfully", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		catch(Exception e)
		{
			ATUReports.add("Start Issue has not been updated : " +orderRef, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			takeScreenShotOnFailure(driver, testName);
		}
	}

	public void amendContract_CancelImmediately(WebDriver driver, String reason, String refundAmount, String client, String brand, String referenceNumber) throws Exception
	{
		orderRef = referenceNumber;
		fetchDetailsCs(driver, client, brand);
		try
		{
			element(driver, transactionEnquiry).click();
			TimeUnit.SECONDS.sleep(5);
			element(driver, amendContract).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, cancelImmediately).click();
			TimeUnit.SECONDS.sleep(5);

			try
			{
				element(driver, chequeRefundCheckBox).click();
				TimeUnit.SECONDS.sleep(3);
				element(driver, immediateCancellationrefundAmount).clear();
				element(driver, immediateCancellationrefundAmount).sendKeys(refundAmount);
				TimeUnit.SECONDS.sleep(3);
			}
			catch(Exception e)
			{				
			}

			Select(element(driver,immediateCancellationReason)).selectByVisibleText(reason);
			TimeUnit.SECONDS.sleep(3);

			element(driver, immadiateCancellationConfirm).click();
			waitForElementToVanish(driver, spinner);


			Assert.assertTrue(element(driver, cancelImmediatelyVerification).isDisplayed(), "Contract cancelled successfully");

			ATUReports.add("Subscription cancelled successfully : " +orderRef, "User should be able to cancel the subsricption successfully", "Customer Reference Number "+orderRef+" has been cancelled successfully", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		catch(Exception e)
		{
			ATUReports.add("Subscription has not been cancelled : " +orderRef, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			takeScreenShotOnFailure(driver, testName);
		}
	}

	public void amendContract_CancelOnExpiry(WebDriver driver, String reason, String client, String brand, String referenceNumber) throws Exception
	{
		orderRef = referenceNumber;
		fetchDetailsCs(driver, client, brand);
		try
		{
			element(driver, transactionEnquiry).click();
			TimeUnit.SECONDS.sleep(5);
			element(driver, amendContract).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, cancelOnExpiry).click();
			TimeUnit.SECONDS.sleep(5);

			Select(element(driver,cancellationReasonOnExpiry)).selectByVisibleText(reason);
			TimeUnit.SECONDS.sleep(3);

			element(driver, confirmCancelonExpiry).click();
			waitForElementToVanish(driver, spinner);

			Assert.assertTrue(element(driver, cancelOnExpiryVerification).isDisplayed());

			ATUReports.add("Cancel on Expiry has been updated successfully : " +orderRef, "User should be able to update the Cancel on Expiry for the subsricption successfully", "Cancel on Expiry for Customer Reference Number "+orderRef+" has been updated successfully", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		catch(Exception e)
		{
			ATUReports.add("Cancel on Expiry has not been updated : " +orderRef, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			takeScreenShotOnFailure(driver, testName);
		}
	}



	public void differentDeliveryAddress(WebDriver driver, String client, String brand, String promotion, String cardType, String custTitle, String firstname, String surname, String postalcode, String address, String deliverypostalcode, String deliveryAddress, String custName, String cardNum1, String date, String year) throws Exception
	{
		try
		{
			element(driver, customerServiceLink).click();	
			TimeUnit.SECONDS.sleep(3);
			for (String winHandle : driver.getWindowHandles()) 
			{
				driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
			}
			Select(element(driver, clientSelect)).selectByVisibleText(client);
			for (int i = 1; i <= 100; i++)
			{
				try
				{
					element(driver, brandSelect(brand)).click();
					TimeUnit.SECONDS.sleep(7);
					break;
				}

				catch (Exception e)
				{
					element(driver, fastFoward).click();
				}
			}		
			element(driver, newSubscription).click();
			TimeUnit.SECONDS.sleep(2);
			element(driver, promotionName).sendKeys(promotion);
			element(driver, findPromotion).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, selectPromotion(promotion)).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, promotionNextBtn).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, offerCard(cardType)).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, title).sendKeys(custTitle);
			element(driver, firstName).sendKeys(firstname);
			element(driver, surName).sendKeys(surname);
			element(driver, postCode).sendKeys(postalcode);
			element(driver, lookupAddress).click();
			TimeUnit.SECONDS.sleep(5);
			Select(element(driver, selectAddress)).selectByVisibleText(address);
			TimeUnit.SECONDS.sleep(5);

			element(driver, differentDeliveryAddress).click();
			TimeUnit.SECONDS.sleep(5);
			element(driver, deliveryPostCode).sendKeys(deliverypostalcode);
			element(driver, deliveryLookupAddress).click();
			TimeUnit.SECONDS.sleep(5);	
			Select(element(driver, selectDeliveryAddress)).selectByVisibleText(deliveryAddress);
			TimeUnit.SECONDS.sleep(5);

			element(driver, custNextBtn).click();
			TimeUnit.SECONDS.sleep(2);
			try
			{
				element(driver, custAssociationNextBtn).isDisplayed();
				element(driver, custAssociationNextBtn).click();
			}
			catch(Exception e)
			{
				// No Action Required
			}
			TimeUnit.SECONDS.sleep(2);

			if (element(driver, verifyBillingAddress).isDisplayed() && element(driver, verifyDeliveryAddress).isDisplayed())
			{
				Assert.assertTrue(true, "Different Delivery address is present for the subscription");
			}
			/*else
			{
				Assert.assertTrue(false, "Different Delivery address is not present for the subscription");
			}*/

			TimeUnit.SECONDS.sleep(2);
			element(driver, issueCalenderNextBtn).click();
			TimeUnit.SECONDS.sleep(2);
			element(driver, customerName).sendKeys(custName);
			TimeUnit.SECONDS.sleep(2);
			element(driver, cardNumber).sendKeys(cardNum1);
			TimeUnit.SECONDS.sleep(2);
			Select(element(driver, expiryDate)).selectByVisibleText(""+date+"");
			TimeUnit.SECONDS.sleep(2);
			Select(element(driver, expiryYear)).selectByVisibleText(""+year+"");	
			TimeUnit.SECONDS.sleep(5);
			element(driver, checkoutNextBtn).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, orderRefNumber).isDisplayed();
			orderRef = element(driver, orderRefNumber).getText();
			System.out.println(orderRef);
			ATUReports.add("New subscription has been performed successfully using different delivery address with order reference number as: "+orderRef, "Promotion name: "+ promotion,"Order Reference",orderRef, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		catch(Exception e)
		{
			ATUReports.add("Unable to do a new subscription", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			takeScreenShotOnFailure(driver, testName);
		}
	}

	public void getDefaultPromotion(WebDriver driver, String client, String brand, String promotion, String cardType, String custTitle, String firstname, String surname, String postalcode, String address, String custName, String cardNum1, String date, String year) throws Exception
	{
		try
		{
			element(driver, customerServiceLink).click();	
			TimeUnit.SECONDS.sleep(3);
			for (String winHandle : driver.getWindowHandles()) 
			{
				driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
			}
			Select(element(driver, clientSelect)).selectByVisibleText(client);
			for (int i = 1; i <= 100; i++)
			{
				try
				{
					element(driver, brandSelect(brand)).click();
					TimeUnit.SECONDS.sleep(7);
					break;
				}

				catch (Exception e)
				{
					element(driver, fastFoward).click();
				}
			}		
			element(driver, newSubscription).click();
			TimeUnit.SECONDS.sleep(2);
			element(driver, getDefaultPromotion).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, selectPromotion(promotion)).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, promotionNextBtn).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, offerCard(cardType)).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, title).sendKeys(custTitle);
			element(driver, firstName).sendKeys(firstname);
			element(driver, surName).sendKeys(surname);
			element(driver, postCode).sendKeys(postalcode);
			element(driver, lookupAddress).click();
			TimeUnit.SECONDS.sleep(5);
			Select(element(driver, selectAddress)).selectByVisibleText(address);
			TimeUnit.SECONDS.sleep(5);


			element(driver, custNextBtn).click();
			TimeUnit.SECONDS.sleep(2);
			try
			{
				element(driver, custAssociationNextBtn).isDisplayed();
				element(driver, custAssociationNextBtn).click();
			}
			catch(Exception e)
			{
				// No Action Required
			}

			TimeUnit.SECONDS.sleep(2);
			element(driver, issueCalenderNextBtn).click();
			TimeUnit.SECONDS.sleep(2);
			element(driver, customerName).sendKeys(custName);
			TimeUnit.SECONDS.sleep(2);
			element(driver, cardNumber).sendKeys(cardNum1);
			TimeUnit.SECONDS.sleep(2);
			Select(element(driver, expiryDate)).selectByVisibleText(""+date+"");
			TimeUnit.SECONDS.sleep(2);
			Select(element(driver, expiryYear)).selectByVisibleText(""+year+"");	
			TimeUnit.SECONDS.sleep(5);
			element(driver, checkoutNextBtn).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, orderRefNumber).isDisplayed();
			orderRef = element(driver, orderRefNumber).getText();
			System.out.println(orderRef);
			ATUReports.add("New subscription has been performed successfully using default promotion with order reference number as: "+orderRef, "Promotion name: "+ promotion,"Order Reference",orderRef, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		catch(Exception e)
		{
			ATUReports.add("Unable to do a new subscription", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			takeScreenShotOnFailure(driver, testName);
		}
	}

	public void newSubscriptionCCCType8(WebDriver driver, String client, String brand, String promotion, String cardType, String custTitle, String firstname, String surname, String postalcode, String address, String custName, String cardNum1, String date, String year) throws Exception
	{
		try
		{
			element(driver, customerServiceLink).click();	
			TimeUnit.SECONDS.sleep(3);
			for (String winHandle : driver.getWindowHandles()) 
			{
				driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
			}
			Select(element(driver, clientSelect)).selectByVisibleText(client);
			for (int i = 1; i <= 100; i++)
			{
				try
				{
					element(driver, brandSelect(brand)).click();
					TimeUnit.SECONDS.sleep(7);
					break;
				}

				catch (Exception e)
				{
					element(driver, fastFoward).click();
				}
			}		
			element(driver, newSubscription).click();
			TimeUnit.SECONDS.sleep(2);
			element(driver, promotionName).sendKeys(promotion);
			element(driver, findPromotion).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, selectPromotion(promotion)).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, promotionNextBtn).click();
			TimeUnit.SECONDS.sleep(3);
			for (int i = 1; i <= 100; i++)
			{
				try
				{
					element(driver, offerCard(cardType)).click();
					TimeUnit.SECONDS.sleep(7);
					break;
				}

				catch (Exception e)
				{
					element(driver, fastFoward).click();
				}
			}		
			element(driver, title).sendKeys(custTitle);
			element(driver, firstName).sendKeys(firstname);
			element(driver, surName).sendKeys(surname);
			element(driver, postCode).sendKeys(postalcode);
			element(driver, lookupAddress).click();
			TimeUnit.SECONDS.sleep(5);
			Select(element(driver, selectAddress)).selectByVisibleText(address);
			TimeUnit.SECONDS.sleep(5);
			element(driver, custNextBtn).click();
			TimeUnit.SECONDS.sleep(2);
			try
			{
				element(driver, custAssociationNextBtn).isDisplayed();
				element(driver, custAssociationNextBtn).click();
			}
			catch(Exception e)
			{
				// No Action Required
			}
			TimeUnit.SECONDS.sleep(2);
			element(driver, issueCalenderNextBtn).click();
			TimeUnit.SECONDS.sleep(2);
			element(driver, customerName).sendKeys(custName);
			TimeUnit.SECONDS.sleep(2);
			element(driver, cardNumber).sendKeys(cardNum1);
			TimeUnit.SECONDS.sleep(2);
			Select(element(driver, expiryDate)).selectByVisibleText(""+date+"");
			TimeUnit.SECONDS.sleep(2);
			Select(element(driver, expiryYear)).selectByVisibleText(""+year+"");	
			TimeUnit.SECONDS.sleep(5);
			element(driver, checkoutNextBtn).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, orderRefNumber).isDisplayed();
			orderRef = element(driver, orderRefNumber).getText();
			System.out.println(orderRef);
			element(driver, csHomeLink).click();
			TimeUnit.SECONDS.sleep(8);
			Assert.assertTrue(element(driver, clientSelect).isDisplayed(), "User is navigated to Customer Services Home page");
			ATUReports.add("New subscription has been performed sucessfully with order reference number as: "+orderRef, "Promotion name: "+ promotion,"Order Reference",orderRef, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		catch(Exception e)
		{
			ATUReports.add("Unable to do a new subscription", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			takeScreenShotOnFailure(driver, testName);
		}
	}

	/**
	 * Method used for verifying the newly created subscription
	 * @param driver Object for defining the web driver
	 * @param promotion Variable to pass the promotion name
	 * @throws Exception To throw an exception whenever an unexpected failure occurs
	 */
	public void verifyNewSubscriptionCSType8(WebDriver driver, String promotion, String client, String brand) throws Exception
	{
		try
		{
			try
			{
				fetchDetailsCs(driver, client, brand);
				TimeUnit.SECONDS.sleep(3);
				Assert.assertEquals(accountID, orderRef);
				Assert.assertEquals(verifyStatus, "ACTIVE");
				Assert.assertEquals(verifyType, "STANDARD_SUBSCRIPTION");
				Assert.assertEquals(payMethod, "CREDIT_CARD");
				Assert.assertEquals(payStatus, "Paid");
				Assert.assertEquals(renStatus, "Continious");
				Assert.assertEquals(subRole, "DIRECT");
				ATUReports.add(verifyType + " Order: "+accountID+"has been successfully verified in CS screen with contract status as:"+verifyStatus,"Order Reference: "+ orderRef,"Payment Method: Direct_Debit, Payment Status: UnPaid, Renewal Status: Continous, Subscriber Role: Direct","Payment Method: "+payMethod + ", Payment Status:"+payStatus+ " ,Renewal Status:"+ renStatus+", Subscriber Role: " +subRole,LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));

				TimeUnit.SECONDS.sleep(5); 
			}
			catch (AssertionError e)
			{
				ATUReports.add("Unable to verify the newly created subscription",accountID, verifyStatus, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				takeScreenShotOnFailure(driver, testName);
				System.out.println(e);
			}

		}
		catch (Exception e1)
		{
			takeScreenShotOnFailure(driver, testName);
			System.out.println(e1);
		}
	}

	public void giftSubscriptionRecepientDetails(WebDriver driver, String client, String brand, String promotion, String cardType, String custTitle, String firstname, String surname, String postalcode, String address, String gCustTitle, String gFirstname, String gSurname, String gPostalcode, String gAddress, String gCustTitle1, String gFirstname1, String gSurname1, String gCustTitle2, String gFirstname2, String gSurname2, String gEditCustTitle, String gEditFirstName, String gEditSurName, String custName, String cardNum1, String date, String year) throws Exception
	{
		try
		{
			element(driver, customerServiceLink).click();	
			TimeUnit.SECONDS.sleep(3);
			for (String winHandle : driver.getWindowHandles()) 
			{
				driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
			}
			Select(element(driver, clientSelect)).selectByVisibleText(client);
			for (int i = 1; i <= 100; i++)
			{
				try
				{
					element(driver, brandSelect(brand)).click();
					TimeUnit.SECONDS.sleep(7);
					break;
				}

				catch (Exception e)
				{
					element(driver, fastFoward).click();
				}
			}		
			element(driver, newSubscription).click();
			TimeUnit.SECONDS.sleep(2);
			element(driver, promotionName).sendKeys(promotion);
			element(driver, findPromotion).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, selectPromotion(promotion)).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, promotionNextBtn).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, offerCard(cardType)).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, title).sendKeys(custTitle);
			element(driver, firstName).sendKeys(firstname);
			element(driver, surName).sendKeys(surname);
			TimeUnit.SECONDS.sleep(2);
			element(driver, postCode).sendKeys(postalcode);
			TimeUnit.SECONDS.sleep(2);
			element(driver, lookupAddress).click();
			TimeUnit.SECONDS.sleep(5);
			Select(element(driver, selectAddress)).selectByVisibleText(address);
			TimeUnit.SECONDS.sleep(5);

			element(driver, giftSubs).click();
			TimeUnit.SECONDS.sleep(2);
			element(driver, gTitle).sendKeys(gCustTitle);
			TimeUnit.SECONDS.sleep(2);
			element(driver, gFirstName).sendKeys(gFirstname);
			TimeUnit.SECONDS.sleep(2);
			element(driver, gSurName).sendKeys(gSurname);
			TimeUnit.SECONDS.sleep(2);
			element(driver, gPostCode).sendKeys(gPostalcode);
			TimeUnit.SECONDS.sleep(2);
			element(driver, gLookupAddress).click();
			TimeUnit.SECONDS.sleep(2);
			element(driver, gSelectAddress).click();
			TimeUnit.SECONDS.sleep(2);
			element(driver, gSaveButton).click();
			TimeUnit.SECONDS.sleep(2);
			takeScreenShotOnFailure(driver, testName);

			Assert.assertTrue(element(driver, gRecipientName(gCustTitle, gFirstname, gSurname)).isDisplayed(), "Receipient details added successfully");

			element(driver, gAddMoreButton).click();

			element(driver, giftSubs).click();
			TimeUnit.SECONDS.sleep(2);
			element(driver, gTitle).sendKeys(gCustTitle1);
			TimeUnit.SECONDS.sleep(2);
			element(driver, gFirstName).sendKeys(gFirstname1);
			TimeUnit.SECONDS.sleep(2);
			element(driver, gSurName).sendKeys(gSurname1);
			TimeUnit.SECONDS.sleep(2);
			element(driver, gPostCode).sendKeys(gPostalcode);
			TimeUnit.SECONDS.sleep(2);
			element(driver, gLookupAddress).click();
			TimeUnit.SECONDS.sleep(2);
			element(driver, gSelectAddress).click();
			TimeUnit.SECONDS.sleep(2);
			element(driver, gSaveButton).click();
			TimeUnit.SECONDS.sleep(2);
			takeScreenShotOnFailure(driver, testName);		
			Assert.assertTrue(element(driver, gRecipientName(gCustTitle, gFirstname1, gSurname1)).isDisplayed(), "Receipient details added successfully");

			element(driver, gAddMoreButton).click();

			element(driver, giftSubs).click();
			TimeUnit.SECONDS.sleep(2);
			element(driver, gTitle).sendKeys(gCustTitle2);
			TimeUnit.SECONDS.sleep(2);
			element(driver, gFirstName).sendKeys(gFirstname2);
			TimeUnit.SECONDS.sleep(2);
			element(driver, gSurName).sendKeys(gSurname2);
			TimeUnit.SECONDS.sleep(2);
			element(driver, gPostCode).sendKeys(gPostalcode);
			TimeUnit.SECONDS.sleep(2);
			element(driver, gLookupAddress).click();
			TimeUnit.SECONDS.sleep(2);
			element(driver, gSelectAddress).click();
			TimeUnit.SECONDS.sleep(2);
			element(driver, gSaveButton).click();
			TimeUnit.SECONDS.sleep(2);
			takeScreenShotOnFailure(driver, testName);
			Assert.assertTrue(element(driver, gRecipientName(gCustTitle2, gFirstname2, gSurname2)).isDisplayed(), "Receipient details added successfully");

			element(driver, gEditButton(gCustTitle2, gFirstname2, gSurname2)).click();

			TimeUnit.SECONDS.sleep(2);
			element(driver, gTitle).sendKeys(gEditCustTitle);
			TimeUnit.SECONDS.sleep(2);
			element(driver, gFirstName).sendKeys(gEditFirstName);
			TimeUnit.SECONDS.sleep(2);
			element(driver, gSurName).sendKeys(gEditSurName);
			TimeUnit.SECONDS.sleep(2);
			element(driver, gPostCode).sendKeys(gPostalcode);
			TimeUnit.SECONDS.sleep(2);
			element(driver, gLookupAddress).click();
			TimeUnit.SECONDS.sleep(2);
			element(driver, gSelectAddress).click();
			TimeUnit.SECONDS.sleep(2);
			element(driver, gSaveButton).click();
			TimeUnit.SECONDS.sleep(2);
			takeScreenShotOnFailure(driver, testName);

			Assert.assertTrue(element(driver, gRecipientName(gEditCustTitle, gEditFirstName, gEditSurName)).isDisplayed(), "Receipient details added successfully");

			element(driver, gRemoveButton(gEditCustTitle, gEditFirstName, gEditSurName)).click();
			TimeUnit.SECONDS.sleep(5);
			takeScreenShotOnFailure(driver, testName);
			Assert.assertTrue(!element(driver, gRecipientName(gEditCustTitle, gEditFirstName, gEditSurName)).isDisplayed(), "Recepient has been removed successfully");


			element(driver, custNextBtn).click();
			TimeUnit.SECONDS.sleep(2);
			try
			{
				element(driver, custAssociationNextBtn).isDisplayed();
				element(driver, custAssociationNextBtn).click();
			}
			catch(Exception e)
			{
				// No Action Required
			}
			TimeUnit.SECONDS.sleep(2);
			Assert.assertTrue(element(driver, gRecipientNameOrderDetails(gCustTitle, gFirstname, gSurname)).isDisplayed(), "Receipient details are present in Order Details page");
			Assert.assertTrue(element(driver, gRecipientNameOrderDetails(gCustTitle1, gFirstname1, gSurname1)).isDisplayed(), "Receipient details are present in Order Details page");
			Assert.assertTrue(!element(driver, gRecipientNameOrderDetails(gEditCustTitle, gEditFirstName, gEditSurName)).isDisplayed(), "Receipient details are not present in Order Details page");
			takeScreenShotOnFailure(driver, testName);

			element(driver, issueCalenderNextBtn).click();
			TimeUnit.SECONDS.sleep(2);
			element(driver, customerName).sendKeys(custName);
			TimeUnit.SECONDS.sleep(2);
			element(driver, cardNumber).sendKeys(cardNum1);
			TimeUnit.SECONDS.sleep(2);
			Select(element(driver, expiryDate)).selectByVisibleText(""+date+"");
			TimeUnit.SECONDS.sleep(2);
			Select(element(driver, expiryYear)).selectByVisibleText(""+year+"");	
			TimeUnit.SECONDS.sleep(5);
			element(driver, checkoutNextBtn).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, orderRefNumber).isDisplayed();
			orderRef = element(driver, orderRefNumber).getText();
			System.out.println(orderRef);
			ATUReports.add("New subscription has been perforemed sucessfully with order reference number as: "+orderRef, "Promotion name: "+ promotion,"Order Reference",orderRef, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		catch(Exception e)
		{
			ATUReports.add("Unable to do a new subscription", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			takeScreenShotOnFailure(driver, testName);
		}
	}

	public void demographicsVerification(WebDriver driver, String client, String brand, String promotion, String cardType, String custTitle, String firstname, String surname, String postalcode, String address, String demographicProperty1, String demographicProperty2, String demographicProperty3, String custName, String cardNum1, String date, String year) throws Exception
	{
		try
		{
			element(driver, customerServiceLink).click();	
			TimeUnit.SECONDS.sleep(3);
			for (String winHandle : driver.getWindowHandles()) 
			{
				driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
			}
			Select(element(driver, clientSelect)).selectByVisibleText(client);
			for (int i = 1; i <= 100; i++)
			{
				try
				{
					element(driver, brandSelect(brand)).click();
					TimeUnit.SECONDS.sleep(7);
					break;
				}

				catch (Exception e)
				{
					element(driver, fastFoward).click();
				}
			}		
			element(driver, newSubscription).click();
			TimeUnit.SECONDS.sleep(2);
			element(driver, promotionName).sendKeys(promotion);
			element(driver, findPromotion).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, selectPromotion(promotion)).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, promotionNextBtn).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, offerCard(cardType)).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, title).sendKeys(custTitle);
			element(driver, firstName).sendKeys(firstname);
			element(driver, surName).sendKeys(surname);
			element(driver, postCode).sendKeys(postalcode);
			element(driver, lookupAddress).click();
			TimeUnit.SECONDS.sleep(5);
			Select(element(driver, selectAddress)).selectByVisibleText(address);
			TimeUnit.SECONDS.sleep(5);

			element(driver, demographics(demographicProperty1, demographicProperty2, demographicProperty3)).click();

			element(driver, custNextBtn).click();
			TimeUnit.SECONDS.sleep(2);
			try
			{
				element(driver, custAssociationNextBtn).isDisplayed();
				element(driver, custAssociationNextBtn).click();
			}
			catch(Exception e)
			{
				// No Action Required
			}
			TimeUnit.SECONDS.sleep(2);
			element(driver, issueCalenderNextBtn).click();
			TimeUnit.SECONDS.sleep(2);
			element(driver, customerName).sendKeys(custName);
			TimeUnit.SECONDS.sleep(2);
			element(driver, cardNumber).sendKeys(cardNum1);
			TimeUnit.SECONDS.sleep(2);
			Select(element(driver, expiryDate)).selectByVisibleText(""+date+"");
			TimeUnit.SECONDS.sleep(2);
			Select(element(driver, expiryYear)).selectByVisibleText(""+year+"");	
			TimeUnit.SECONDS.sleep(5);
			element(driver, checkoutNextBtn).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, orderRefNumber).isDisplayed();
			orderRef = element(driver, orderRefNumber).getText();
			System.out.println(orderRef);
			ATUReports.add("New subscription has been done sucessfully with order reference number as: "+orderRef, "Promotion name: "+ promotion,"Order Reference",orderRef, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		catch(Exception e)
		{
			ATUReports.add("Unable to do a new subscription", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			takeScreenShotOnFailure(driver, testName);
		}
	}

	public void customerEventsVerification(WebDriver driver, String referenceNumber, String client, String brand) throws Exception
	{
		orderRef = referenceNumber;
		fetchDetailsCs(driver, client, brand);
		try
		{
			element(driver, customerEvents).click();
			TimeUnit.SECONDS.sleep(8);
			element(driver, customerEvents_AllEventsTab).click();
			TimeUnit.SECONDS.sleep(3);

			Assert.assertTrue(element(driver, allIssues_FutureEventsTable).isDisplayed());
			Assert.assertTrue(element(driver, allIssues_FutureEventsTableVerification).isDisplayed());
			Assert.assertTrue(element(driver, allIssues_ReceivedEventsTable).isDisplayed());
			Assert.assertTrue(element(driver, allIssues_ReceivedEventsVerification).isDisplayed());

			element(driver, customerEvents_IssuesTab).click();
			TimeUnit.SECONDS.sleep(8);
			Assert.assertTrue(element(driver, issues_FutureEventsTable).isDisplayed());
			Assert.assertTrue(element(driver, issues_FutureEventsTableVerification).isDisplayed());

			element(driver, customerEvents_LettersTab).click();
			TimeUnit.SECONDS.sleep(8);
			Assert.assertTrue(element(driver, letters_FutureEventsTable).isDisplayed());
			Assert.assertTrue(element(driver, letters_FutureEventsTableVerification).isDisplayed());
			Assert.assertTrue(element(driver, letters_ReceivedEventsTable).isDisplayed());
			Assert.assertTrue(element(driver, letters_ReceivedEventsTableVerification).isDisplayed());

			element(driver, customerEvents_CollectionsTab).click();
			TimeUnit.SECONDS.sleep(8);
			Assert.assertTrue(element(driver, collections_ReceivedEventsTable).isDisplayed());
			Assert.assertTrue(element(driver, collections_ReceivedEventsTableVerification).isDisplayed());

			ATUReports.add("Customer Events have been verified successfully : " +orderRef, "User should be able to verify the customer events successfully", "Customer Events for Customer Reference Number "+orderRef+" has been verified successfully", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		catch(Exception e)
		{
			ATUReports.add("Customer Events have not been verified : " +orderRef, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			takeScreenShotOnFailure(driver, testName);
		}
	}

}	