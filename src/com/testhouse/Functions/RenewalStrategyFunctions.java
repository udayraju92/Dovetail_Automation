package com.testhouse.Functions;

import java.util.concurrent.TimeUnit;

import org.joda.time.DateTime;
import org.openqa.selenium.WebDriver;

import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;

import com.testhouse.ObjectRepository.SchedulesObjects;
import com.testhouse.ObjectRepository.RenewalStrategyObjects;

public class RenewalStrategyFunctions extends RenewalStrategyObjects
{
	public static String testName;

	/**
	 * Method to create a new renewal startegy
	 * @param driver Object for webdriver
	 * @param client Object for selecting a client from the list
	 * @param brand Object for selecting a brand from the list
	 * @param opt Object to check / uncheck the optimistic check box
	 * @param cal Object to check / uncheck the Calendar check box
	 * @param act Object to check / uncheck the Active check box
	 * @param name Object for naming the schedule
	 * @param description Object defined for the schedule description
	 * @param type Object to select whether the schedule is an Issue / Product
	 * @param when Object for choosing when the event should be scheduled
	 * @param delType Object for selecting the event delivery type
	 * @param dest Object for selecting the destination option from the list
	 * @throws Exception To throw an exception whenever an unexpected failure occurs
	 */
	public void renewalStrategy(WebDriver driver, String client, String brand,String name, String description, String starDate, String enDate, String expDate, String act, String typeRenewal, String promotion, String offer, String whe, String delTyp, String dlv, String destin) throws Exception
	{
		try
		{
			element(driver, accountAdminLink).click();	
			TimeUnit.SECONDS.sleep(10);
			for (String winHandle : driver.getWindowHandles()) 
			{
				driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
			}
			element(driver, manageRenewal).click();
			TimeUnit.SECONDS.sleep(10);
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
			TimeUnit.SECONDS.sleep(8);

			try
			{
				element(driver, addRenewal).isEnabled();
				element(driver, addRenewal).click();
			}
			catch (Exception e)
			{
				TimeUnit.SECONDS.sleep(5);
				element(driver, addRenewal).click();
			}


			TimeUnit.SECONDS.sleep(3);
			DateTime dt = DateTime.now();
			String dateFolder = dt.toLocalDate().toString();

			element(driver, renewalName).click();
			element(driver, renewalName).clear();
			element(driver, renewalName).sendKeys(""+name+""+" "+dateFolder+"");
			TimeUnit.SECONDS.sleep(3);
			element(driver, renewalDesc).sendKeys(description);
			TimeUnit.SECONDS.sleep(3);
			element(driver, startDate).clear();
			TimeUnit.SECONDS.sleep(3);
			element(driver, startDate).sendKeys(starDate);
			element(driver, endDate).clear();
			TimeUnit.SECONDS.sleep(3);
			element(driver, endDate).sendKeys(enDate);
			element(driver, expiryDate).clear();
			TimeUnit.SECONDS.sleep(3);
			element(driver, expiryDate).sendKeys(expDate);
			if(act.equals("Yes")||act.equals("yes"))
			{
				TimeUnit.SECONDS.sleep(3);
				element(driver, activeCheck).click();
			}

			element(driver, selectTypBx).click();

			element(driver, selectType(typeRenewal)).click();
			if(typeRenewal.equals("CONTINIOUS_AUTHORITY")||typeRenewal.equals("STEPUP")||type.equals("MULTI_STAGE"))

			{
				TimeUnit.SECONDS.sleep(8);

				element(driver, findDefaultPro).click();
				TimeUnit.SECONDS.sleep(8);
				element(driver, findPro).click();
				TimeUnit.SECONDS.sleep(8);
				element(driver, selectRPromotion(promotion)).click();
				TimeUnit.SECONDS.sleep(8);
				element(driver, selectPro).click();
				TimeUnit.SECONDS.sleep(3);
				element(driver, findDefaultOff).click();
				TimeUnit.SECONDS.sleep(3);
				element(driver, searchOffer).click();
				element(driver, selectROffer(offer)).click();
				TimeUnit.SECONDS.sleep(3);
				element(driver, selOffer).click();
				TimeUnit.SECONDS.sleep(3);
			}

			if(typeRenewal.equals("NON_CONTINIOUS_AUTHORITY")||typeRenewal.equals("STEPUP"))
			{
				TimeUnit.SECONDS.sleep(3);
				element(driver, efforts).click();
				element(driver, newEffort).click();
				TimeUnit.SECONDS.sleep(3);
				System.out.println(whe);
				element(driver, when).sendKeys(whe);
				//Select(element(driver, when)).selectByVisibleText(whe);
				TimeUnit.SECONDS.sleep(3);
				element(driver, delivType).click();
				TimeUnit.SECONDS.sleep(3);
				if(delTyp.equals("LETTER")||delTyp.equals("LETTER"))
				{
					TimeUnit.SECONDS.sleep(10);
					element(driver, next).click();
				}
				else
				{
					Select(element(driver, delivType)).selectByVisibleText(delTyp);
					TimeUnit.SECONDS.sleep(10);
					element(driver, next).click();
				}
				
				
				element(driver, delive).sendKeys(dlv);
				TimeUnit.SECONDS.sleep(3);
				Select(element(driver, destn)).selectByVisibleText(destin);
				element(driver, next).click();
				TimeUnit.SECONDS.sleep(3);
				element(driver, finPromotion).click();
				TimeUnit.SECONDS.sleep(8);
				element(driver, effortPro(promotion)).click();
				TimeUnit.SECONDS.sleep(8);
				element(driver, next).click();
				TimeUnit.SECONDS.sleep(8);
				element(driver, finOffer).click();
				TimeUnit.SECONDS.sleep(8);
				element(driver, effortOff(offer)).click();
				TimeUnit.SECONDS.sleep(3);
				element(driver, finish).click();
				TimeUnit.SECONDS.sleep(5);
			}

			
			TimeUnit.SECONDS.sleep(5);
			element(driver, saveButton).click();
		
			TimeUnit.SECONDS.sleep(5);
			element(driver, backButton).click();
			
			TimeUnit.SECONDS.sleep(15);
		element(driver, homeLink).click();
			TimeUnit.SECONDS.sleep(5);
			element(driver, accountAdminLink).click();	
			TimeUnit.SECONDS.sleep(10);
			for (String winHandle : driver.getWindowHandles()) 
			{
				driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
			}
			element(driver, manageRenewal).click();
			TimeUnit.SECONDS.sleep(10);
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
			TimeUnit.SECONDS.sleep(8);
			
			if(element(driver, findRenewal(""+name+""+" "+dateFolder+"")).isDisplayed())
			{
				ATUReports.add("Created and verified a new Renewal Strategy: "+""+name+""+" "+dateFolder+"", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}

			else
			{
				ATUReports.add("Unable to verify the newly created Renewal Startegy", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				takeScreenShotOnFailure(driver, testName);
			}

		}

		catch (Exception e)
		{
			ATUReports.add("Unable to create a new schedule", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			takeScreenShotOnFailure(driver, testName);
			System.out.println(e);
		}
	}

}

