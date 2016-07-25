package com.testhouse.Functions;

import java.util.concurrent.TimeUnit;

import org.joda.time.DateTime;
import org.openqa.selenium.WebDriver;

import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;

import com.testhouse.ObjectRepository.SchedulesObjects;

public class SchedulesFunctions extends SchedulesObjects
{
	public static String testName;
	String maintenSchedule = null;

	/**
	 * Method to create a new standard schedule
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
	public void standardSchedule(WebDriver driver, String client, String brand, String opt, String cal, String act,String name, String description, String type, String when, String delType, String prod, String dest, String delv, String bDelivery, String bissues) throws Exception
	{
		try
		{
			element(driver, accountAdminLink).click();	
			TimeUnit.SECONDS.sleep(10);
			for (String winHandle : driver.getWindowHandles()) 
			{
				driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
			}
			element(driver, manageSchedule).click();
			TimeUnit.SECONDS.sleep(5);
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
				element(driver, addSchedule).isEnabled();
				element(driver, addSchedule).click();
			}
			catch (Exception e)
			{
				TimeUnit.SECONDS.sleep(5);
				element(driver, addSchedule).click();
			}


			TimeUnit.SECONDS.sleep(3);
			DateTime dt = DateTime.now();
			String dateFolder = dt.toLocalDate().toString();

			element(driver, scheduleName).click();
			element(driver, scheduleName).clear();
			element(driver, scheduleName).sendKeys(""+name+""+" "+dateFolder+"");
			maintenSchedule = ""+name+""+" "+dateFolder+"";
			TimeUnit.SECONDS.sleep(3);
			element(driver, scheduleDesc).sendKeys(description);
			if(opt.equals("Yes")||opt.equals("yes"))
			{
				element(driver, optimisticCheck).click();
			}
			if(cal.equals("Yes")||cal.equals("yes"))
			{
				element(driver, calenderBasedCheck).click();
			}
			if(act.equals("Yes")||act.equals("yes"))
			{
				element(driver, activeCheck).click();
			}
			TimeUnit.SECONDS.sleep(2);
			Select(element(driver, subscriptionType)).selectByVisibleText(type);
			TimeUnit.SECONDS.sleep(3);
			element(driver, scheduleEventsBtn).click();
			TimeUnit.SECONDS.sleep(5);
			element(driver, newEventBtn).click();
			TimeUnit.SECONDS.sleep(8);
		
			Select(element(driver, eventWhen)).selectByVisibleText(when);
			
			TimeUnit.SECONDS.sleep(8);
		
			Select(element(driver, eventDeliveryType)).selectByVisibleText(delType);
			TimeUnit.SECONDS.sleep(8);
			//element(driver, eventNextBtn).click();
			TimeUnit.SECONDS.sleep(8);
			if(prod.equals("Yes")||prod.equals("yes"))
			{
				element(driver, pActiveCheck).click();
			}
			element(driver, eventNextBtn).click();
			TimeUnit.SECONDS.sleep(15);
			if(delType.equals("ISSUE")||delType.equals("MERCHANDISE"))
			{
				Select(element(driver, eventDest)).selectByVisibleText(dest);
				if(prod.equals("Yes")||prod.equals("yes"))
				{
					element(driver, eventDelivery).sendKeys(delv);
				}

			}
			TimeUnit.SECONDS.sleep(5);

			if(bDelivery.equals("Include Only")||bDelivery.equals("Include Only"))
			{
				element(driver, backDelivery).click();


				for (int i = 1; i <= 100; i++)
				{
					try
					{
						TimeUnit.SECONDS.sleep(8);
						//String te = element(driver, test1).getText();
						System.out.println(eventDelivery(bissues));
						element(driver, eventDelivery(bissues)).click();
						if(!element(driver, eventDelivery(bissues)).isSelected())
						{
							element(driver, eventDelivery(bissues)).click();

						}
						break;
					}

					catch (Exception e)
					{
						element(driver, fastFoward).click();
					}

				}
			}
			if(prod.equals("Exclude Only")||prod.equals("Exclude Only"))
			{
				element(driver, eventDelivery).sendKeys(delv);
			}

			if(bDelivery.equals("Yes")||bDelivery.equals("yes"))
			{element(driver, backDelivery).click();
			TimeUnit.SECONDS.sleep(10);
			//element(driver, eventDelivery(bissues)).click();
			elementHighlight(driver, test);
			//element(driver, test).click();
			TimeUnit.SECONDS.sleep(7);
			//			for (int i = 1; i <= 100; i++)
			//			{
			//				try
			//				{
			//					TimeUnit.SECONDS.sleep(10);
			//					element(driver, eventDelivery(bissues)).click();
			//					TimeUnit.SECONDS.sleep(7);
			//				}
			//
			//				catch (Exception e)
			//				{
			//					element(driver, fastFoward).click();
			//				}
			//				break;
			//			}
			}
			element(driver, eventFinishBtn).click();
			TimeUnit.SECONDS.sleep(5);
			element(driver, eventSaveBtn).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, eventBackBtn).click();
			TimeUnit.SECONDS.sleep(5);
			//		element(driver, saveScheduleBtn).click();
			TimeUnit.SECONDS.sleep(2);
			element(driver, schedulesList).click();
			TimeUnit.SECONDS.sleep(3);

			if(element(driver, findSchedule(""+name+""+" "+dateFolder+"")).isDisplayed())
			{
				ATUReports.add("Created and verified a new standard schedule: "+""+name+""+" "+dateFolder+"", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}

			else
			{
				ATUReports.add("Unable to verify the newly created schedule", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
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


	/**
	 * Method to edit a schedule; Precondition: Run any create schedule process to get "maintenSchedule"
	 * @param driver Object for webDriver
	 * @param maintenance of schedule - Edit and save, move to offer page from schedule
	 * @param maintenSchedule is the schedule to be edited
	 */
	public void maintSchedule(WebDriver driver, String client, String brand, String schedule, String newName, String newDesc) throws Exception
	{
		try
		{TimeUnit.SECONDS.sleep(3);
		DateTime dt = DateTime.now();
		String dateFolder = dt.toLocalDate().toString();
			element(driver, accountAdminLink).click();	
			TimeUnit.SECONDS.sleep(10);
			for (String winHandle : driver.getWindowHandles()) 
			{
				driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
			}
			element(driver, manageSchedule).click();
			TimeUnit.SECONDS.sleep(5);
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

			for (int i = 1; i <= 100; i++)
			{
				try
				{
					element(driver, editSchedule(maintenSchedule)).click();
					TimeUnit.SECONDS.sleep(7);
					break;
				}

				catch (Exception e)
				{
					element(driver, fastForward1).click();
				}
			}
			
			
		
			TimeUnit.SECONDS.sleep(8);
			element(driver, scheduleName).clear();
			element(driver, scheduleName).sendKeys(""+newName+""+" "+dateFolder+"");
			TimeUnit.SECONDS.sleep(3);
			element(driver, scheduleDesc).sendKeys(newDesc);
			TimeUnit.SECONDS.sleep(3);
			element(driver, saveScheduleBtn).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, schButton).click();
			TimeUnit.SECONDS.sleep(5);
			TimeUnit.SECONDS.sleep(2);
			TimeUnit.SECONDS.sleep(3);

			if(element(driver, findSchedule(""+newName+""+" "+dateFolder+"")).isDisplayed())
			{
				ATUReports.add("Edit schedule Successfull: "+ ""+newName+""+" "+dateFolder+"", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}

			else
			{
				ATUReports.add("Unable to edit schedule", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				takeScreenShotOnFailure(driver, testName);
			}
			//To check whether user can navigate to offer page from schedules
			for (int i = 1; i <= 100; i++)
			{
				try
				{
					TimeUnit.SECONDS.sleep(8);
					element(driver, editSchedule(""+newName+""+" "+dateFolder+"")).click();
					TimeUnit.SECONDS.sleep(7);
					break;
				}

				catch (Exception e)
				{
					element(driver, fastForward1).click();
				}
			}
			
		
			TimeUnit.SECONDS.sleep(3);
			element(driver, schOffer).click();
			TimeUnit.SECONDS.sleep(3);
			if(element(driver, offerManager).isDisplayed())
			{
				ATUReports.add("User can navigate to offer page from schedule page", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			}

			else
			{
				ATUReports.add("User can't navigate to offer page from schedule page", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				takeScreenShotOnFailure(driver, testName);
			}

		}

		catch (Exception e)
		{
			ATUReports.add("Maintenance schedule page failed", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			takeScreenShotOnFailure(driver, testName);
			System.out.println(e);
		}
	}
}

