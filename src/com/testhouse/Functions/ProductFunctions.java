package com.testhouse.Functions;

import java.util.concurrent.TimeUnit;

import org.joda.time.DateTime;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.testhouse.ObjectRepository.ProductObjects;

import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;

public class ProductFunctions extends ProductObjects 
{
	public static String testName, doveRef;
	String merchandiseDescriptionName, clientName;
	
	public void manageMerchandise(WebDriver driver, String client, String brand, String merchandiseName, String brandAssociation, String stockAssociationPercentage, String depletionOption) throws Exception
	{
		try
		{
			element(driver, productLink).click();
			TimeUnit.SECONDS.sleep(3);
			for (String winHandle : driver.getWindowHandles()) 
			{
				driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
			}
			
			element(driver, manageMerchandise).click();
			TimeUnit.SECONDS.sleep(3);
			waitForElement(driver, manageMerchandise_Client);
			Select(element(driver, manageMerchandise_Client)).selectByVisibleText(client);
			waitForElementToVanish(driver, spinner);
			Select(element(driver, manageMerchandise_Brand)).selectByVisibleText(brand);
			waitForElementToVanish(driver, spinner);
			
			element(driver, newMerchandise).click();
			waitForElement(driver, merchandiseDescription);
			element(driver, merchandiseDescription).clear();
			merchandiseDescriptionName = merchandiseName +" "+ DateTime.now().toString("dd-MM-yy hh-mm-ss");
			element(driver, merchandiseDescription).sendKeys(merchandiseDescriptionName);
			TimeUnit.SECONDS.sleep(1);
			
			element(driver, brandAssociationsButton).click();
			waitForElementToVanish(driver, spinner);
			element(driver, stockDeliveryPercentageForBrand(brandAssociation)).clear();
			element(driver, stockDeliveryPercentageForBrand(brandAssociation)).sendKeys(stockAssociationPercentage);
			TimeUnit.SECONDS.sleep(1);
			Select(element(driver, stockDepletionOptionForBrand(brandAssociation))).selectByVisibleText(depletionOption);
			TimeUnit.SECONDS.sleep(1);			
			element(driver, merchandise_SaveButton).click();
			TimeUnit.SECONDS.sleep(8);
			ATUReports.add("New Merchandise has been crated sucessfully", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		catch(Exception e)
		{
			ATUReports.add("Unable to create the merchandise", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			takeScreenShotOnFailure(driver, testName);
		}
	}
	
	public void verifyMerchandise(WebDriver driver, String client, String brand, String brandAssociation, String stockAssociationPercentage) throws Exception
	{
		try
		{
			element(driver, homeLink).click();
			TimeUnit.SECONDS.sleep(3);						
			element(driver, productLink).click();
			TimeUnit.SECONDS.sleep(3);
			for (String winHandle : driver.getWindowHandles()) 
			{
				driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
			}
			
			element(driver, manageMerchandise).click();
			TimeUnit.SECONDS.sleep(6);
			waitForElement(driver, manageMerchandise_Client);
			/*Select(element(driver, manageMerchandise_Client)).selectByVisibleText(client);
			TimeUnit.SECONDS.sleep(10);
			waitForElementToVanish(driver, spinner);
			Select(element(driver, manageMerchandise_Brand)).selectByVisibleText(brand);
			waitForElementToVanish(driver, spinner);*/
			
			element(driver, manageMerchandise_SearchBox).sendKeys(merchandiseDescriptionName);
			TimeUnit.SECONDS.sleep(1);
			element(driver, manageMerchandise_SearchButton).click();
			TimeUnit.SECONDS.sleep(8);
			Assert.assertTrue(element(driver, verifyMerchandiseName(merchandiseDescriptionName)).isDisplayed());
			
			Assert.assertTrue(element(driver, editLink(merchandiseDescriptionName)).isDisplayed());
			Assert.assertTrue(element(driver, viewLink(merchandiseDescriptionName)).isDisplayed());
			
			element(driver, viewLink(merchandiseDescriptionName)).click();
			TimeUnit.SECONDS.sleep(3);
			waitForElementToVanish(driver, spinner);
			
			String doveRef = element(driver, merchandise_VerifySerialNumber).getText();
		
			Assert.assertTrue(element(driver, merchandise_VerifyBrandAssociations_Brand(brandAssociation)).isDisplayed());
			Assert.assertEquals(element(driver, merchandise_VerifyBrandAssociations_StockDeliveryPercentage(brandAssociation)).getText(), stockAssociationPercentage);
			ATUReports.add("New Merchandise has been verified sucessfully with reference number as: "+doveRef, "Order Reference",doveRef, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		catch(Exception e)
		{
			ATUReports.add("Unable to do a verify the merchandise", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			takeScreenShotOnFailure(driver, testName);
		}
	}
	
	public void manageClient_NewClient(WebDriver driver, String name, String sagaReference, String tolerance) throws Exception
	{
		try
		{
			element(driver, productLink).click();
			TimeUnit.SECONDS.sleep(3);
			for (String winHandle : driver.getWindowHandles()) 
			{
				driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
			}
			
			element(driver, manageClients).click();
			TimeUnit.SECONDS.sleep(3);
			waitForElement(driver, manageClients_New);
			element(driver, manageClients_New).click();
			waitForElement(driver, manageClients_New_Name);
			clientName = name +" "+ DateTime.now().toString("dd-MM-yy hh-mm-ss");
			element(driver, manageClients_New_Name).sendKeys(clientName);
			TimeUnit.SECONDS.sleep(2);
			element(driver, manageClients_New_SagaReference).sendKeys(sagaReference);
			TimeUnit.SECONDS.sleep(2);
			element(driver, manageClients_New_Tolerance).sendKeys(tolerance);
			TimeUnit.SECONDS.sleep(2);
			element(driver, manageClients_New_ProductAutoGenPasswordTypeComboxBox).click();
			TimeUnit.SECONDS.sleep(2);
			element(driver, manageClients_New_SelectPRoductAutoGenPasswordOption("AUTO_GENERATED")).click();
			TimeUnit.SECONDS.sleep(2);
			element(driver, manageClients_New_CaptureClientReason).click();
			TimeUnit.SECONDS.sleep(2);
			element(driver, manageClients_SaveButton).click();
			TimeUnit.SECONDS.sleep(10);
			waitForElementToVanish(driver, spinner);
			ATUReports.add("New Client has been crated sucessfully", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		catch(Exception e)
		{
			ATUReports.add("Unable to create the Client", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			takeScreenShotOnFailure(driver, testName);
		}
	}
	
	public void manageClient_VerifyClient(WebDriver driver) throws Exception
	{
		try
		{
			element(driver, homeLink).click();
			TimeUnit.SECONDS.sleep(3);						
			element(driver, productLink).click();
			TimeUnit.SECONDS.sleep(3);
			for (String winHandle : driver.getWindowHandles()) 
			{
				driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
			}
			element(driver, manageClients).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, manageClients_SearchField).sendKeys(clientName);
			element(driver, manageClients_SearchButton).click();
			TimeUnit.SECONDS.sleep(5);
			Assert.assertTrue(element(driver, verifyCreatedClient(clientName)).isDisplayed());
			String referenceNumber = element(driver, manageClient_ReferenceNumber(clientName)).getText();
			
			ATUReports.add("New Client : "+clientName+" has been verified sucessfully with refernece number : "+referenceNumber, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		catch(Exception e)
		{
			ATUReports.add("Unable to verify the created Client", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			takeScreenShotOnFailure(driver, testName);
		}
	}
	
	public void manageClient_ViewClient(WebDriver driver) throws Exception
	{
		try
		{
			element(driver, homeLink).click();
			TimeUnit.SECONDS.sleep(3);						
			element(driver, productLink).click();
			TimeUnit.SECONDS.sleep(3);
			for (String winHandle : driver.getWindowHandles()) 
			{
				driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
			}
			element(driver, manageClients).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, manageClients_SearchField).sendKeys(clientName);
			element(driver, manageClients_SearchButton).click();
			TimeUnit.SECONDS.sleep(6);	
			Assert.assertTrue(element(driver, verifyCreatedClient(clientName)).isDisplayed());
			element(driver, manageClient_ViewLink(clientName)).click();
			TimeUnit.SECONDS.sleep(6);	
			Assert.assertTrue(!element(driver, manageClients_New_Name).isDisplayed());
			Assert.assertTrue(!element(driver, manageClients_New_Tolerance).isDisplayed());
			
			ATUReports.add("New Client : "+clientName+" has been verified sucessfully", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		catch(Exception e)
		{
			ATUReports.add("Unable to verify the created Client", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			takeScreenShotOnFailure(driver, testName);
		}
	}
	
	public void manageClient_EditClient(WebDriver driver, String client, String brand, String group, String contactType, String reference, String questionType) throws Exception
	{
		try
		{
			element(driver, homeLink).click();
			TimeUnit.SECONDS.sleep(3);						
			element(driver, productLink).click();
			TimeUnit.SECONDS.sleep(3);
			for (String winHandle : driver.getWindowHandles()) 
			{
				driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
			}
			element(driver, manageClients).click();
			TimeUnit.SECONDS.sleep(3);
			// clientName = "Test Client 25-07-16 01-06-03";
			element(driver, manageClients_SearchField).sendKeys(clientName);
			element(driver, manageClients_SearchButton).click();
			TimeUnit.SECONDS.sleep(6);	
			Assert.assertTrue(element(driver, verifyCreatedClient(clientName)).isDisplayed());
			element(driver, manageClient_editLink(clientName)).click();
			TimeUnit.SECONDS.sleep(7);
			waitForElementToVanish(driver, spinner);
			element(driver, manageClient_Parameters).click();
			
			waitForElement(driver, manageClient_Parameters_SearchAClientOrBrand);
			element(driver, manageClient_Parameters_SearchAClientOrBrand).click();
			TimeUnit.SECONDS.sleep(5);	
			Select(element(driver, manageClient_Parameters_Client)).selectByVisibleText(client);
			TimeUnit.SECONDS.sleep(5);	
			for (int i = 1; i <= 100; i++)
			{
				try
				{
					element(driver, manageClient_Parameters_BrandSelect(brand)).click();
					TimeUnit.SECONDS.sleep(7);
					break;
				}

				catch (Exception e)
				{
					element(driver, fastFoward).click();
				}
			}
			waitForElementToVanish(driver, spinner);
			waitForElement(driver, manageClient_Parameters_ShowButton);
			if (element(driver, manageClient_Parameters_ShowButton).isEnabled())
			{
				element(driver, manageClient_Parameters_ShowButton).click();
			}
			TimeUnit.SECONDS.sleep(10);
			waitForElementToVanish(driver, manageClient_Parameters_LoadingIcon);

			waitForElement(driver, manageClient_Parameters_SelectGroup);
			Select(element(driver, manageClient_Parameters_SelectGroup)).selectByVisibleText(group);
			TimeUnit.SECONDS.sleep(5);
			waitForElementToVanish(driver, manageClient_Parameters_LoadingIcon);
			element(driver, manageClient_Parameters_EditButton).click();
			TimeUnit.SECONDS.sleep(5);
			waitForElementToVanish(driver, manageClient_Parameters_LoadingIcon);
			if (group.contains("DPP"))
			{
				Select(element(driver, manageClient_Parameters_SelectContactType)).selectByVisibleText(contactType);
				TimeUnit.SECONDS.sleep(3);	
				Select(element(driver, manageClient_Parameters_DPP_QuestionType(reference))).selectByVisibleText(questionType);
				TimeUnit.SECONDS.sleep(3);	
				element(driver, manageClient_Parameters_DPP_Available(reference)).click();
				TimeUnit.SECONDS.sleep(3);	
				element(driver, manageClient_Parameters_SaveButton).click();
				TimeUnit.SECONDS.sleep(5);
				waitForElementToVanish(driver, manageClient_Parameters_LoadingIcon);
			}
			else if(group.contains("Demographics"))
			{
				element(driver, manageClient_Parameters_Demographics_Available(reference)).click();
				TimeUnit.SECONDS.sleep(3);	
				element(driver, manageClient_Parameters_SaveButton).click();
				TimeUnit.SECONDS.sleep(5);
				waitForElementToVanish(driver, manageClient_Parameters_LoadingIcon);
			}
			ATUReports.add("Client : "+clientName+" has been edited sucessfully", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		catch(Exception e)
		{
			ATUReports.add("Unable to edit the created Client", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			takeScreenShotOnFailure(driver, testName);
		}
	}
	
	public void manageBrands_NewClient(WebDriver driver, String name, String sagaReference, String tolerance) throws Exception
	{
		try
		{
			element(driver, productLink).click();
			TimeUnit.SECONDS.sleep(3);
			for (String winHandle : driver.getWindowHandles()) 
			{
				driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
			}
			
			element(driver, manageBrandsLink).click();
			TimeUnit.SECONDS.sleep(3);
			waitForElement(driver, manageClients_New);
			element(driver, manageClients_New).click();
			waitForElement(driver, manageClients_New_Name);
			clientName = name +" "+ DateTime.now().toString("dd-MM-yy hh-mm-ss");
			element(driver, manageClients_New_Name).sendKeys(clientName);
			TimeUnit.SECONDS.sleep(2);
			element(driver, manageClients_New_SagaReference).sendKeys(sagaReference);
			TimeUnit.SECONDS.sleep(2);
			element(driver, manageClients_New_Tolerance).sendKeys(tolerance);
			TimeUnit.SECONDS.sleep(2);
			element(driver, manageClients_New_ProductAutoGenPasswordTypeComboxBox).click();
			TimeUnit.SECONDS.sleep(2);
			element(driver, manageClients_New_SelectPRoductAutoGenPasswordOption("AUTO_GENERATED")).click();
			TimeUnit.SECONDS.sleep(2);
			element(driver, manageClients_New_CaptureClientReason).click();
			TimeUnit.SECONDS.sleep(2);
			element(driver, manageClients_SaveButton).click();
			TimeUnit.SECONDS.sleep(10);
			waitForElementToVanish(driver, spinner);
			ATUReports.add("New Client has been crated sucessfully", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
		}
		catch(Exception e)
		{
			ATUReports.add("Unable to create the Client", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			takeScreenShotOnFailure(driver, testName);
		}
	}
	
}
