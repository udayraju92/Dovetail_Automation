package com.testhouse.Functions;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

import org.joda.time.DateTime;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.testhouse.ObjectRepository.ProductObjects;

import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;

public class ProductFunctions extends ProductObjects 
{
	public static String testName, doveRef;
	String merchandiseDescriptionName;
	
	public void manageMerchandise(WebDriver driver, String client, String brand, String merchandiseName, String serialNumber, String taxCode, String outputType, String externalRefNumber, String availableTo, String brandAssociation, String stockAssociationPercentage, String depletionOption) throws Exception
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
			waitForElement1(driver, manageMerchandise_Client);
			Select(element(driver, manageMerchandise_Client)).selectByVisibleText(client);
			waitForElementToVanish(driver, spinner);
			Select(element(driver, manageMerchandise_Brand)).selectByVisibleText(brand);
			waitForElementToVanish(driver, spinner);
			
			element(driver, newMerchandise).click();
			waitForElement1(driver, merchandiseDescription);
			element(driver, merchandiseDescription).clear();
			merchandiseDescriptionName = merchandiseName +" "+ DateTime.now().toString("dd-MM-yy hh-mm-ss");
			element(driver, merchandiseDescription).sendKeys(merchandiseDescriptionName);
			TimeUnit.SECONDS.sleep(1);
			element(driver, merchandiseSerialNumber).sendKeys(serialNumber);
			TimeUnit.SECONDS.sleep(1);
			element(driver, merchandiseTaxCode).sendKeys(taxCode);
			TimeUnit.SECONDS.sleep(1);
			Select(element(driver, merchandiseLabelOutputType)).selectByVisibleText(outputType);
			TimeUnit.SECONDS.sleep(1);
			element(driver, merchandiseAvailableFrom).sendKeys(DateTime.now().toString("dd/MM/yyyy"));
			TimeUnit.SECONDS.sleep(1);
			element(driver, merchandiseAvailableTo).sendKeys(DateTime.now().plusDays(Integer.parseInt(availableTo)).toString("dd/MM/yyyy"));
			TimeUnit.SECONDS.sleep(1);
			
			element(driver, brandAssociationsButton).click();
			waitForElementToVanish(driver, spinner);
			element(driver, stockDeliveryPercentageForBrand(brandAssociation)).sendKeys(stockAssociationPercentage);
			TimeUnit.SECONDS.sleep(1);
			Select(element(driver, stockDepletionOptionForBrand(brandAssociation))).selectByVisibleText(depletionOption);
			TimeUnit.SECONDS.sleep(1);			
			element(driver, merchandise_SaveButton).click();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	public void verifyMerchandise(WebDriver driver, String client, String brand)
	{
		try
		{
			
		}
		catch(Exception e)
		{
			
		}
	}
	
}
