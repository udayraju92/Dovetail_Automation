package com.testhouse.Functions;

import java.util.concurrent.TimeUnit;

import org.joda.time.DateTime;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.testhouse.ObjectRepository.SubscriptionManagementObjects;

import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;

public class SubscriptionManagementFunctions extends SubscriptionManagementObjects
{	
	public static String testName, orderRef;

	public void newSubscription(WebDriver driver, String client, String brand, String subscriptionType, String country, String promotionName, String offer, String custTitle, String firstName, String surName, String postCode, String address, String customerName, String cardNumber, String expiryMonth, String expiryYear, String accountNumber, String sortCode, String demographicProperty1, String demographicProperty2, String demographicProperty3, String noOfCopies) throws Exception
	{
		try
		{
			element(driver, subscriptionManagementLink).click();
			TimeUnit.SECONDS.sleep(3);
			for (String winHandle : driver.getWindowHandles()) 
			{
				driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
			}

			element(driver, newSubscriptionLink).click();
			waitForElement(driver, SM_client);

			subscriptionDetails(driver, client, brand, subscriptionType);

			promotionDetails(driver, country, promotionName, offer);

			verifyPromotionSelected(driver, promotionName, offer);

			customerDetails(driver, custTitle, firstName, surName);

			addressDetails(driver, postCode, address);

			if(offer.contains("CREDIT_CARD"))
			{
				paymentDetails_CreditCardOffer(driver, customerName, cardNumber, expiryMonth, expiryYear);
			}
			else if(offer.contains("DIRECT_DEBIT"))
			{
				paymentDetails_DirectDebitOffer(driver, customerName, accountNumber, sortCode);
			}

			demographics(driver, demographicProperty1, demographicProperty2, demographicProperty3);

			confirmOrder(driver, noOfCopies);

			element(driver, placeOrderButton).click();
			waitForElementToVanish(driver, spinnerSM);
			TimeUnit.SECONDS.sleep(5);
			orderRef = element(driver, customerReferenceNumber).getText();

			ATUReports.add("New subscription has been done sucessfully with order reference number as:"+orderRef, "Promotion name: "+ promotionName,"Order Reference",orderRef, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		catch(Exception e)
		{
			ATUReports.add("Unable to do a new subscription", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			takeScreenShotOnFailure(driver, testName);
		}
	}

	public void subscriptionDetails(WebDriver driver, String client, String brand, String subscriptionType) throws Exception
	{
		try
		{
			Select(element(driver, SM_client)).selectByVisibleText(client);
			waitForElementToVanish(driver, spinnerSM);
			Select(element(driver, SM_brand)).selectByVisibleText(brand);
			waitForElementToVanish(driver, spinnerSM);
			Select(element(driver, SM_SubscriptionType)).selectByVisibleText(subscriptionType);
			waitForElementToVanish(driver, spinnerSM);
			ATUReports.add("Subscription details have been selected successfully", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		catch(Exception e)
		{
			ATUReports.add("Promotion have not been selected", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			takeScreenShotOnFailure(driver, testName);
		}
	}

	public void promotionDetails(WebDriver driver, String country, String promotionName, String offer) throws Exception
	{
		try
		{
			TimeUnit.SECONDS.sleep(3);
			Select(element(driver, promotion_Country)).selectByVisibleText(country);
			TimeUnit.SECONDS.sleep(1);
			element(driver, promotion_PromotionName).sendKeys(promotionName);
			TimeUnit.SECONDS.sleep(3);
			elementHighlight(driver, promotion_Search);
			element(driver, promotion_Search).click();
			TimeUnit.SECONDS.sleep(10);
			System.out.println("Click happened");
			waitForElement1(driver, promotion_PromotionDialog);
			System.out.println("Dialog dispalyed");
			elementHighlight(driver, promotion_SelectPromotion(promotionName));
			element(driver, promotion_SelectPromotion(promotionName)).click();
			TimeUnit.SECONDS.sleep(3);
			waitForElementToVanish(driver, spinnerSM);
			element(driver, promotion_SelectOffer(offer)).click();
			TimeUnit.SECONDS.sleep(3);
			waitForElementToVanish(driver, spinnerSM);
			ATUReports.add("Promotion has been selected successfully", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		catch(Exception e)
		{
			ATUReports.add("Promotion has not been selected", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			takeScreenShotOnFailure(driver, testName);
		}
	}

	public void promotionDetails_ProductOnly(WebDriver driver, String country, String promotionName, String offer, String productName, String quantity) throws Exception
	{
		try
		{
			TimeUnit.SECONDS.sleep(3);
			Select(element(driver, promotion_Country)).selectByVisibleText(country);
			TimeUnit.SECONDS.sleep(1);
			element(driver, promotion_PromotionName).sendKeys(promotionName);
			TimeUnit.SECONDS.sleep(1);
			element(driver, promotion_Search).click();
			waitForElement1(driver, promotion_PromotionDialog);

			elementHighlight(driver, promotion_SelectPromotion(promotionName));
			element(driver, promotion_SelectPromotion(promotionName)).click();


			waitForElementToVanish(driver, spinnerSM);
			element(driver, promotion_Offer_ActionCheckBox(offer)).click();
			waitForElementToVanish(driver, spinnerSM);

			element(driver, product_Quantity(productName)).clear();
			element(driver, product_Quantity(productName)).sendKeys(quantity);
			TimeUnit.SECONDS.sleep(2);

			element(driver, product_Quantity_ActionCheckBox(productName)).click();
			waitForElementToVanish(driver, spinnerSM);
			TimeUnit.SECONDS.sleep(2);
			if(!element(driver, product_Quantity_ActionCheckBox(productName)).isSelected())
			{
				element(driver, product_Quantity_ActionCheckBox(productName)).click();
				waitForElementToVanish(driver, spinnerSM);
				TimeUnit.SECONDS.sleep(2);
			}

			element(driver, product_SubmitButton).click();
			waitForElementToVanish(driver, spinnerSM);
			ATUReports.add("Promotion has been selected successfully", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		catch(Exception e)
		{
			ATUReports.add("Promotion has not been selected", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			takeScreenShotOnFailure(driver, testName);
		}
	}

	public void verifyPromotionSelected(WebDriver driver, String promotionName, String offerType) throws Exception
	{
		try
		{
			Assert.assertEquals(element(driver, verifySelectedPromotion).getText(), promotionName);
			Assert.assertEquals(element(driver, verifySelectedPayment).getText(), offerType);
			ATUReports.add("Promotion Details have been verified successfully", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		catch(Exception e)
		{
			ATUReports.add("Promotion Details have not been verified", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			takeScreenShotOnFailure(driver, testName);
		}
	}

	public void customerDetails(WebDriver driver, String custTitle, String firstName, String surName) throws Exception
	{		
		try
		{
			TimeUnit.SECONDS.sleep(3);
			element(driver, customerDetail_Title).clear();
			element(driver, customerDetail_Title).sendKeys(custTitle);
			TimeUnit.SECONDS.sleep(1);
			element(driver, customerDetail_FirstName).clear();
			element(driver, customerDetail_FirstName).sendKeys(firstName);
			TimeUnit.SECONDS.sleep(1);
			element(driver, customerDetail_SurName).clear();
			element(driver, customerDetail_SurName).sendKeys(surName);
			TimeUnit.SECONDS.sleep(1);
			ATUReports.add("Customer Details have been entered successfully", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		catch(Exception e)
		{
			ATUReports.add("Customer Details have not been entered", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			takeScreenShotOnFailure(driver, testName);
		}
	}

	public void customerDetails_Renewal(WebDriver driver, String referenceNumber) throws Exception
	{		
		try
		{
			element(driver, customerDetail_ReferenceNumber).sendKeys(referenceNumber);
			element(driver, searchByReferenceNumber).click();
			waitForElementToVanish(driver, spinnerSM);
			element(driver, selectCustomer(referenceNumber)).click();
			waitForElementToVanish(driver, spinnerSM);
			ATUReports.add("Customer Details have been fetched successfully", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		catch(Exception e)
		{
			ATUReports.add("Customer Details have not been fetched", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			takeScreenShotOnFailure(driver, testName);
		}
	}

	public void addressDetails(WebDriver driver, String postCode, String address) throws Exception
	{
		try
		{
			element(driver, addressDetails_Postcode).clear();
			TimeUnit.SECONDS.sleep(2);
			element(driver, addressDetails_Postcode).sendKeys(postCode);
			TimeUnit.SECONDS.sleep(1);
			element(driver, addressDetails_PostCodeSearch).click();
			waitForElement1(driver, addressDetails_SelectAddress_Dialog);
			System.out.println("dialog displayed" + addressDetails_SelectAddress(address));
			element(driver, addressDetails_SelectAddress(address)).click();
			waitForElementToVanish(driver, spinnerSM);
			ATUReports.add("Address Details have been entered successfully", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		catch(Exception e)
		{
			ATUReports.add("Address Details have not been entered", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			takeScreenShotOnFailure(driver, testName);
		}
	}

	public void paymentDetails_CreditCardOffer(WebDriver driver, String customerName, String cardNumber, String expiryMonth, String expiryYear) throws Exception
	{
		try
		{
			TimeUnit.SECONDS.sleep(5);
			element(driver, paymentDetails_CreditCard_CustomerName).sendKeys(customerName);
			TimeUnit.SECONDS.sleep(1);
			element(driver, paymentDetails_CreditCard_CardNumber).sendKeys(cardNumber);
			TimeUnit.SECONDS.sleep(1);
			Select(element(driver, paymentDetails_CreditCard_ExpiryMonth)).selectByVisibleText(expiryMonth);
			TimeUnit.SECONDS.sleep(1);
			Select(element(driver, paymentDetails_CreditCard_ExpiryYear)).selectByVisibleText(expiryYear);
			ATUReports.add("Payment Details have been entered successfully", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		catch(Exception e)
		{
			ATUReports.add("Payment Details have not been entered", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			takeScreenShotOnFailure(driver, testName);
		}
	}

	public void paymentDetails_DirectDebitOffer(WebDriver driver, String customerName, String accountNumber, String sortCode) throws Exception
	{
		try
		{
			TimeUnit.SECONDS.sleep(5);
			element(driver, paymentDetails_DirectDebit_CustomerName).sendKeys(customerName);
			TimeUnit.SECONDS.sleep(1);
			element(driver, paymentDetails_DirectDebit_AccountNumber).sendKeys(accountNumber);
			TimeUnit.SECONDS.sleep(1);
			element(driver, paymentDetails_DirectDebit_SortCode).sendKeys(sortCode);
			TimeUnit.SECONDS.sleep(1);
			element(driver, paymentDetails_DirectDebit_SortCode).click();
			waitForElementToVanish(driver, spinnerSM);
			ATUReports.add("Payment Details have been entered successfully", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		catch(Exception e)
		{
			ATUReports.add("Payment Details have not been entered", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			takeScreenShotOnFailure(driver, testName);
		}
	}

	public void demographics(WebDriver driver, String demographicProperty1, String demographicProperty2, String demographicProperty3) throws Exception
	{
		try
		{
			element(driver, demographics(demographicProperty1, demographicProperty2, demographicProperty3)).click();
			ATUReports.add("Demographics have been selected successfully", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		catch(Exception e)
		{
			ATUReports.add("Demographics have not been selected", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			takeScreenShotOnFailure(driver, testName);
		}
	}

	public void confirmOrder(WebDriver driver, String noOfCopies) throws Exception
	{
		try
		{
			element(driver, orderSummary_NoOfCopies).clear();
			element(driver, orderSummary_NoOfCopies).sendKeys(noOfCopies);
			TimeUnit.SECONDS.sleep(4);
			element(driver, confirmButton_SM).click();
			waitForElementToVanish(driver, spinnerSM);
			TimeUnit.SECONDS.sleep(4);
			if(element(driver, confirmButton_SM).isDisplayed())
			{
				element(driver, confirmButton_SM).click();
				waitForElementToVanish(driver, spinnerSM);
			}
			TimeUnit.SECONDS.sleep(10);
			ATUReports.add("Confirm order has been performed successfully", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		catch(Exception e)
		{
			ATUReports.add("Confirm order has not been performed", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			takeScreenShotOnFailure(driver, testName);
		}
	}

	public void renewalSubscription(WebDriver driver, String client, String brand, String subscriptionType, String country, String promotionName, String offer, String referenceNumber, String custTitle, String firstName, String surName, String postCode, String address, String customerName, String cardNumber, String expiryMonth, String expiryYear, String accountNumber, String sortCode, String noOfCopies) throws Exception
	{
		try
		{
			element(driver, subscriptionManagementLink).click();
			TimeUnit.SECONDS.sleep(3);
			for (String winHandle : driver.getWindowHandles()) 
			{
				driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
			}

			element(driver, newSubscriptionLink).click();
			waitForElement(driver, SM_client);

			subscriptionDetails(driver, client, brand, subscriptionType);

			promotionDetails(driver, country, promotionName, offer);

			verifyPromotionSelected(driver, promotionName, offer);

			customerDetails_Renewal(driver, referenceNumber);

			customerDetails(driver, custTitle, firstName, surName);

			if(offer.contains("CREDIT_CARD"))
			{
				paymentDetails_CreditCardOffer(driver, customerName, cardNumber, expiryMonth, expiryYear);
			}
			else if(offer.contains("DIRECT_DEBIT"))
			{
				paymentDetails_DirectDebitOffer(driver, customerName, accountNumber, sortCode);
			}

			confirmOrder(driver, noOfCopies);

			TimeUnit.SECONDS.sleep(5);
			element(driver, placeOrderButton).click();
			waitForElementToVanish(driver, spinnerSM);
			TimeUnit.SECONDS.sleep(5);
			orderRef = element(driver, customerReferenceNumber).getText();
			ATUReports.add("Renewal subscription has been done sucessfully with order reference number as:"+orderRef, "Promotion name: "+ promotionName,"Order Reference",orderRef, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		catch(Exception e)
		{
			ATUReports.add("Unable to do a Renewal subscription", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			takeScreenShotOnFailure(driver, testName);
		}
	}

	public void productOnlySubscription(WebDriver driver, String client, String brand, String subscriptionType, String country, String promotionName, String offer, String productName, String quantity, String custTitle, String firstName, String surName, String postCode, String address, String customerName, String cardNumber, String expiryMonth, String expiryYear, String accountNumber, String sortCode) throws Exception
	{
		try
		{
			element(driver, subscriptionManagementLink).click();
			TimeUnit.SECONDS.sleep(3);
			for (String winHandle : driver.getWindowHandles()) 
			{
				driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
			}

			element(driver, newSubscriptionLink).click();
			waitForElement(driver, SM_client);

			subscriptionDetails(driver, client, brand, subscriptionType);

			promotionDetails_ProductOnly(driver, country, promotionName, offer, productName, quantity);

			verifyPromotionSelected(driver, promotionName, offer);

			customerDetails(driver, custTitle, firstName, surName);

			addressDetails(driver, postCode, address);

			if(offer.contains("CREDIT_CARD"))
			{
				paymentDetails_CreditCardOffer(driver, customerName, cardNumber, expiryMonth, expiryYear);
			}
			else if(offer.contains("DIRECT_DEBIT"))
			{
				paymentDetails_DirectDebitOffer(driver, customerName, accountNumber, sortCode);
			}

			element(driver, confirmButton_ProductOnly_SM).click();
			try
			{
				if(!element(driver, placeOrderButton).isDisplayed())
				{
					element(driver, confirmButton_ProductOnly_SM).click();
					waitForElementToVanish(driver, spinnerSM);
				}
				element(driver, placeOrderButton).click();
			}
			catch(Exception e)
			{

			}

			waitForElementToVanish(driver, spinnerSM);
			TimeUnit.SECONDS.sleep(5);
			orderRef = element(driver, customerReferenceNumber).getText();
			ATUReports.add("New Product Only subscription has been done sucessfully with order reference number as:"+orderRef, "Promotion name: "+ promotionName,"Order Reference: " + orderRef, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		catch(Exception e)
		{
			System.out.println(e);
			ATUReports.add("Unable to do a new Product only subscription", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			takeScreenShotOnFailure(driver, testName);

		}
	}

	public void verifyNextButton(WebDriver driver, String client, String brand, String subscriptionType) throws Exception
	{
		try
		{
			element(driver, orderConfirmation_Next).click();
			waitForElementToVanish(driver, spinnerSM);
			Assert.assertEquals(Select(element(driver, SM_client)).getFirstSelectedOption().getText(), client);
			takeScreenShotOnFailure(driver, testName);
			ATUReports.add("Next button verification has been performed successfully", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		catch(Exception e)
		{
			ATUReports.add("Next button verification failure", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			takeScreenShotOnFailure(driver, testName);
		}
	}

	public void verifyRestartButton(WebDriver driver) throws Exception
	{
		try
		{
			element(driver, orderConfirmation_Restart).click();
			waitForElementToVanish(driver, spinnerSM);
			Assert.assertEquals(Select(element(driver, SM_client)).getFirstSelectedOption().getText(), "[Select a client]");
			takeScreenShotOnFailure(driver, testName);
			ATUReports.add("Restart button verification has been performed successfully", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		catch(Exception e)
		{
			ATUReports.add("Restart button verification failure", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			takeScreenShotOnFailure(driver, testName);
		}
	}

	public void verifyExitButton(WebDriver driver) throws Exception
	{
		try
		{
			element(driver, orderConfirmation_Exit).click();
			TimeUnit.SECONDS.sleep(10);
			for (String winHandle : driver.getWindowHandles()) 
			{
				driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
			}
			TimeUnit.SECONDS.sleep(3);
			Assert.assertTrue(element(driver, subscriptionManagementLink).isDisplayed());
			takeScreenShotOnFailure(driver, testName);
			ATUReports.add("Exit button verification has been performed successfully", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		catch(Exception e)
		{
			ATUReports.add("Exit button verification failure", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			takeScreenShotOnFailure(driver, testName);
		}
	}

	public void fetchDetails(WebDriver driver, String client, String brand) throws Exception
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
			try
			{
				if(element(driver, custAssociationNextBtn).isEnabled())
				{
					element(driver, custAssociationNextBtn).click();
					TimeUnit.SECONDS.sleep(3);
				}
			}
			catch(Exception e)
			{
			}
			
		}
		catch(Exception e)
		{
			ATUReports.add("Unable to fetch details", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			takeScreenShotOnFailure(driver, testName);
		}
	}

}
