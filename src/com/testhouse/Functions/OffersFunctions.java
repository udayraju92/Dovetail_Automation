package com.testhouse.Functions;

import java.util.concurrent.TimeUnit;

import org.joda.time.DateTime;
import org.openqa.selenium.WebDriver;

import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;

import com.testhouse.ObjectRepository.OffersObjects;

public class OffersFunctions extends OffersObjects
{
	public static String testName;

	/**
	 * Method to create a new offer
	 * @param driver Object for webdriver
	 * @param client Object for selecting a client from the list
	 * @param brand Object for selecting a brand from the list
	 * @param opt Object to check / uncheck the optimistic check box
	 * @param name Object for naming the offer
	 * @param description Object defined for the offer description
	 */
	public void createOffer(WebDriver driver, String client, String brand,String name, String description, String type,  String billingCollection, String minimumCopies, String optimisticIssues, String graceIssues, String starDate, String enDate, String expDate, String schedules, String paymentMethod, String ways, String region, String priceOffer, String priceTotal, String taxCategory, String frequency, String chargePrice, String billingFrequency, String durationType, String renStrategy) throws Exception
	{
		try

		{

			element(driver, accountAdminLink).click();	
			TimeUnit.SECONDS.sleep(10);
			for (String winHandle : driver.getWindowHandles()) 
			{
				driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
			}
			element(driver, manageOffers).click();
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
				element(driver, addOffer).isEnabled();
				element(driver, addOffer).click();
			}
			catch (Exception e)
			{
				TimeUnit.SECONDS.sleep(5);
				element(driver, addOffer).click();
			}


			TimeUnit.SECONDS.sleep(3);
			DateTime dt = DateTime.now();
			String dateFolder = dt.toLocalDate().toString();

			element(driver, offerName).click();
			element(driver, offerName).clear();
			element(driver, offerName).sendKeys(""+name+""+" "+dateFolder+"");
			TimeUnit.SECONDS.sleep(3);
			element(driver, offerDesc).clear();
			element(driver, offerDesc).sendKeys(description);
			TimeUnit.SECONDS.sleep(5);
			//element(driver, minCopies).clear();
			//element(driver, minCopies).sendKeys(minimumCopies);
			//TimeUnit.SECONDS.sleep(3);
			//element(driver, optIssues).clear();
			//element(driver, optIssues).sendKeys(optimisticIssues);
			//TimeUnit.SECONDS.sleep(3);
			//element(driver, graIssues).clear();
			//element(driver, graIssues).sendKeys(graceIssues);
			Select(element(driver, billCollType)).selectByVisibleText(billingCollection);


			TimeUnit.SECONDS.sleep(5);
			element(driver, prosCheck).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, startDate).clear();
			TimeUnit.SECONDS.sleep(5);
			element(driver, startDate).sendKeys(starDate);
			element(driver, endDate).clear();
			TimeUnit.SECONDS.sleep(5);
			element(driver, endDate).sendKeys(enDate);
			element(driver, expiryDate).clear();
			TimeUnit.SECONDS.sleep(5);
			element(driver, expiryDate).sendKeys(expDate);
			TimeUnit.SECONDS.sleep(5);
			element(driver, next).click();
			TimeUnit.SECONDS.sleep(8);
			for (int i = 1; i <= 100; i++)
			{
				try
				{
					element(driver, selectSchedule(schedules)).click();
					TimeUnit.SECONDS.sleep(7);
					break;
				}

				catch (Exception e)
				{
					element(driver, fastFoward2).click();
				}
			}
			//			if(!element(driver, selectSchedule(schedules)).isSelected())
			//			{
			//				element(driver, selectSchedule(schedules)).click();
			//			}

			TimeUnit.SECONDS.sleep(3);
			element(driver, next).click();
			TimeUnit.SECONDS.sleep(8);
			element(driver, payMethod(paymentMethod)).click();
			TimeUnit.SECONDS.sleep(8);
			if(billCollType.equals("INVOICE"))
			{
				element(driver, inWays(ways)).click();
			}
			TimeUnit.SECONDS.sleep(8);
			element(driver, next).click();
			element(driver, selRegion(region)).click();
			TimeUnit.SECONDS.sleep(8);
			element(driver, next).click();
			TimeUnit.SECONDS.sleep(8);
			element(driver, price).clear();
			element(driver, price).sendKeys(priceOffer);
			TimeUnit.SECONDS.sleep(3);
			//Select(element(driver, pTotal)).selectByVisibleText(priceTotal);
			//			
			//			element(driver, pTotal).clear();
			//			element(driver, pTotal).sendKeys(priceTotal);
			TimeUnit.SECONDS.sleep(5);
			//element(driver, txCategory).clear();
			//Select(element(driver, txCategory)).selectByVisibleText(taxCategory);
			element(driver, txCategory).sendKeys(taxCategory);
			TimeUnit.SECONDS.sleep(3);
			element(driver, next).click();
			TimeUnit.SECONDS.sleep(16);
			element(driver, addChargeTerm).click();
			TimeUnit.SECONDS.sleep(8);
			element(driver, freqncy).clear();
			element(driver, freqncy).sendKeys(frequency);
			TimeUnit.SECONDS.sleep(3);
			element(driver, charPrice).clear();
			element(driver, charPrice).sendKeys(chargePrice);
			TimeUnit.SECONDS.sleep(3);
			element(driver, addPrice).click();
			TimeUnit.SECONDS.sleep(8);
			element(driver, next).click();
			TimeUnit.SECONDS.sleep(16);
			TimeUnit.SECONDS.sleep(32);
			if(element(driver, durType).isDisplayed())
			{
				Select(element(driver, durType)).selectByVisibleText(durationType);

				TimeUnit.SECONDS.sleep(8);
			}

			try
			{
				if(element(driver, billFrequency).isDisplayed())
				{
					element(driver, billFrequency).sendKeys(billingFrequency);
					TimeUnit.SECONDS.sleep(8);
					element(driver, next).click();
					TimeUnit.SECONDS.sleep(8);
					element(driver, next).click();
				}
	
			}
			catch(Exception e)
			{
				
			}
			//element(driver, durType).sendKeys("");

			TimeUnit.SECONDS.sleep(8);
			element(driver, renewalStrategy(renStrategy)).click();
			TimeUnit.SECONDS.sleep(8);
			element(driver, finish).click();
			TimeUnit.SECONDS.sleep(10);
			element(driver, homeLink).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, accountAdminLink).click();	
			TimeUnit.SECONDS.sleep(10);
			for (String winHandle : driver.getWindowHandles()) 
			{
				driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
			}
			element(driver, manageOffers).click();
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
			element(driver, searchOffer).click();
			TimeUnit.SECONDS.sleep(8);
			try
			{
				for (int i = 1; i <= 100; i++)
				{
					try

					{
						if(element(driver, findRenewal(""+name+""+" "+dateFolder+"")).isDisplayed())
						{
							ATUReports.add(type+" - "+ "Created and verified a new Offer: "+""+name+""+" "+dateFolder+"", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
						}
						break;
					}

					catch (Exception e)
					{
						element(driver, fastFoward1).click();
					}
				}
			}

			catch (Exception e)
			{
				ATUReports.add("Unable to create and verify a new offer- " + type, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				takeScreenShotOnFailure(driver, testName);
				System.out.println(e);
			}


		}
		catch(Exception e)
		{
			ATUReports.add("Unable to create and verify a new offer", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			takeScreenShotOnFailure(driver, testName);
			System.out.println(e);

		}
	}

	//Upgrade and add boundary
	/**
	 * Method to add upgrade to a new offer
	 * @param driver Object for webdriver
	 * @param client Object for selecting a client from the list
	 * @param brand Object for selecting a brand from the list
	 * @param opt Object to check / uncheck the optimistic check box
	 * @param name Object for naming the offer
	 * @param description Object defined for the offer description
	 */
	public void upgradeOffer(WebDriver driver, String client, String brand,String name, String description, String type,  String billingCollection, String minimumCopies, String optimisticIssues, String graceIssues, String starDate, String enDate, String expDate, String schedules, String paymentMethod, String ways, String region, String priceOffer, String priceTotal, String taxCategory, String frequency, String chargePrice, String billingFrequency, String durationType,String upgradeBrand, String upgradeRegion, String addBound, String addCharge, String renStrategy) throws Exception
	{
		try

		{

			element(driver, accountAdminLink).click();	
			TimeUnit.SECONDS.sleep(10);
			for (String winHandle : driver.getWindowHandles()) 
			{
				driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
			}
			element(driver, manageOffers).click();
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
				element(driver, addOffer).isEnabled();
				element(driver, addOffer).click();
			}
			catch (Exception e)
			{
				TimeUnit.SECONDS.sleep(5);
				element(driver, addOffer).click();
			}


			TimeUnit.SECONDS.sleep(3);
			DateTime dt = DateTime.now();
			String dateFolder = dt.toLocalDate().toString();

			element(driver, offerName).click();
			element(driver, offerName).clear();
			element(driver, offerName).sendKeys(""+name+""+" "+dateFolder+"");
			TimeUnit.SECONDS.sleep(3);
			element(driver, offerDesc).clear();
			element(driver, offerDesc).sendKeys(description);
			TimeUnit.SECONDS.sleep(5);
			//element(driver, minCopies).clear();
			//element(driver, minCopies).sendKeys(minimumCopies);
			//TimeUnit.SECONDS.sleep(3);
			//element(driver, optIssues).clear();
			//element(driver, optIssues).sendKeys(optimisticIssues);
			//TimeUnit.SECONDS.sleep(3);
			//element(driver, graIssues).clear();
			//element(driver, graIssues).sendKeys(graceIssues);
			Select(element(driver, billCollType)).selectByVisibleText(billingCollection);


			TimeUnit.SECONDS.sleep(5);
			element(driver, prosCheck).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, startDate).clear();
			TimeUnit.SECONDS.sleep(5);
			element(driver, startDate).sendKeys(starDate);
			element(driver, endDate).clear();
			TimeUnit.SECONDS.sleep(5);
			element(driver, endDate).sendKeys(enDate);
			element(driver, expiryDate).clear();
			TimeUnit.SECONDS.sleep(5);
			element(driver, expiryDate).sendKeys(expDate);
			TimeUnit.SECONDS.sleep(5);
			element(driver, next).click();
			TimeUnit.SECONDS.sleep(8);
			for (int i = 1; i <= 100; i++)
			{
				try
				{
					element(driver, selectSchedule(schedules)).click();
					TimeUnit.SECONDS.sleep(7);
					break;
				}

				catch (Exception e)
				{
					element(driver, fastFoward2).click();
				}
			}
			//			if(!element(driver, selectSchedule(schedules)).isSelected())
			//			{
			//				element(driver, selectSchedule(schedules)).click();
			//			}

			TimeUnit.SECONDS.sleep(3);
			element(driver, next).click();
			TimeUnit.SECONDS.sleep(8);
			element(driver, payMethod(paymentMethod)).click();
			TimeUnit.SECONDS.sleep(8);
			if(billCollType.equals("INVOICE"))
			{
				element(driver, inWays(ways)).click();
			}
			TimeUnit.SECONDS.sleep(8);
			element(driver, next).click();
			element(driver, selRegion(region)).click();
			TimeUnit.SECONDS.sleep(8);
			element(driver, next).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, price).clear();
			element(driver, price).sendKeys(priceOffer);
			TimeUnit.SECONDS.sleep(3);
			//Select(element(driver, pTotal)).selectByVisibleText(priceTotal);
			//			
			//			element(driver, pTotal).clear();
			//			element(driver, pTotal).sendKeys(priceTotal);
			TimeUnit.SECONDS.sleep(5);
			//element(driver, txCategory).clear();
			//Select(element(driver, txCategory)).selectByVisibleText(taxCategory);
			element(driver, txCategory).sendKeys(taxCategory);
			TimeUnit.SECONDS.sleep(3);
			element(driver, next).click();
			TimeUnit.SECONDS.sleep(8);
			element(driver, addChargeTerm).click();
			TimeUnit.SECONDS.sleep(8);
			element(driver, freqncy).clear();
			element(driver, freqncy).sendKeys(frequency);
			TimeUnit.SECONDS.sleep(3);
			element(driver, charPrice).clear();
			element(driver, charPrice).sendKeys(chargePrice);
			TimeUnit.SECONDS.sleep(3);
			element(driver, addPrice).click();
			TimeUnit.SECONDS.sleep(8);
			element(driver, next).click();
			TimeUnit.SECONDS.sleep(8);

			if(element(driver, durType).isDisplayed())
			{
				Select(element(driver, durType)).selectByVisibleText(durationType);

			}
			if(element(driver, billFrequency).isDisplayed())
			{
				element(driver, billFrequency).sendKeys(billingFrequency);

				element(driver, next).click();
				TimeUnit.SECONDS.sleep(16);
				System.out.println("Test1");

			}
			if(element(driver, upgrade).isDisplayed())
			{
				try
				{
					element(driver, upgradeType).click();
					TimeUnit.SECONDS.sleep(8);
					Select(element(driver, upBrand)).selectByVisibleText(upgradeBrand);
					TimeUnit.SECONDS.sleep(8);
					Select(element(driver, upRegions)).selectByVisibleText(upgradeRegion);
					element(driver, addUpgrade).click();
					TimeUnit.SECONDS.sleep(8);
					element(driver, addBoundary).click();
					TimeUnit.SECONDS.sleep(8);
					element(driver, addBoundaryFrom).sendKeys(addBound);
					TimeUnit.SECONDS.sleep(8);
					element(driver, addBoundaryCharge).sendKeys(addCharge);
					TimeUnit.SECONDS.sleep(8);
					element(driver, addUp).click();
					TimeUnit.SECONDS.sleep(8);
					element(driver, addOption).click();
				}
				catch(Exception e)
				{
					System.out.println("Test2");
					TimeUnit.SECONDS.sleep(8);
					System.out.println(element(driver, upBrand));
					System.out.println(upgradeBrand);
					Select(element(driver, upBrand)).selectByVisibleText(upgradeBrand);
					System.out.println(element(driver, upRegions));
					System.out.println(upgradeRegion);
					TimeUnit.SECONDS.sleep(8);
					Select(element(driver, upRegions)).selectByVisibleText(upgradeRegion);
					element(driver, addUpgrade).click();
					TimeUnit.SECONDS.sleep(8);
					element(driver, addBoundary).click();
					TimeUnit.SECONDS.sleep(8);
					element(driver, addBoundaryFrom).sendKeys(addBound);
					TimeUnit.SECONDS.sleep(8);
					element(driver, addBoundaryCharge).sendKeys(addCharge);
					TimeUnit.SECONDS.sleep(8);
					element(driver, addUp).click();
					TimeUnit.SECONDS.sleep(8);
					element(driver, addOption).click();
				}
			}
			//element(driver, durType).sendKeys("");
			TimeUnit.SECONDS.sleep(8);
			element(driver, next).click();
			TimeUnit.SECONDS.sleep(8);
			element(driver, renewalStrategy(renStrategy)).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, finish).click();
			TimeUnit.SECONDS.sleep(10);
			element(driver, homeLink).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, accountAdminLink).click();	
			TimeUnit.SECONDS.sleep(10);
			for (String winHandle : driver.getWindowHandles()) 
			{
				driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
			}
			element(driver, manageOffers).click();
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
			element(driver, searchOffer).click();
			TimeUnit.SECONDS.sleep(8);
			try
			{
				for (int i = 1; i <= 100; i++)
				{
					try

					{
						if(element(driver, findRenewal(""+name+""+" "+dateFolder+"")).isDisplayed())
						{
							ATUReports.add(type+" - "+ "Created and verified a new Offer: "+""+name+""+" "+dateFolder+"", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
						}
						break;
					}

					catch (Exception e)
					{
						element(driver, fastFoward1).click();
					}
				}
			}

			catch (Exception e)
			{
				ATUReports.add("Unable to create and verify a new offer- " + type, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				takeScreenShotOnFailure(driver, testName);
				System.out.println(e);
			}


		}
		catch(Exception e)
		{
			ATUReports.add("Unable to create and verify a new offer", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			takeScreenShotOnFailure(driver, testName);
			System.out.println(e);

		}
	}
	//Maintenance  offer
	/**
	 * Method for maintenance of offer
	 * @param driver Object for webdriver
	 * @param client Object for selecting a client from the list
	 * @param brand Object for selecting a brand from the list
	 */
	public void maintenanceOff(WebDriver driver, String client, String brand,String offer) throws Exception
	{
		try

		{

			element(driver, accountAdminLink).click();	
			TimeUnit.SECONDS.sleep(10);
			for (String winHandle : driver.getWindowHandles()) 
			{
				driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
			}
			element(driver, manageOffers).click();
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
				element(driver, search).sendKeys(offer);
				TimeUnit.SECONDS.sleep(7);

				element(driver, searchOffer).click();

				TimeUnit.SECONDS.sleep(7);
				element(driver, offerList(offer)).click();
				TimeUnit.SECONDS.sleep(7);
				element(driver, offerDesc).clear();
				element(driver, offerDesc).sendKeys("Edited Description");


				ATUReports.add("Search and edit offer successfull", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));


			}

			catch (Exception e)
			{
				ATUReports.add("Unable to search and edit offer", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				takeScreenShotOnFailure(driver, testName);
			}

			element(driver, finish).click();

			try
			{
				TimeUnit.SECONDS.sleep(7);

				element(driver, delete).click();
				ATUReports.add("Delete offer successfull", LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));


			}
			catch(Exception e)

			{
				ATUReports.add("Unable to delete offer", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				takeScreenShotOnFailure(driver, testName);
			}


		}
		catch(Exception e)
		{
			ATUReports.add("Offer maintenance failed", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			takeScreenShotOnFailure(driver, testName);
		}

	}

	/**
	 * Method to add new schedule  to  a new offer
	 * @param driver Object for webdriver
	 * @param client Object for selecting a client from the list
	 * @param brand Object for selecting a brand from the list
	 */
	public void addSchedule(WebDriver driver, String client, String brand,String name, String description, String type,  String billingCollection, String minimumCopies, String optimisticIssues, String graceIssues, String starDate, String enDate, String expDate, String schName, String schDesc, String schOpt, String schCal, String schActv, String schSubType, String paymentMethod, String ways, String region, String priceOffer, String priceTotal, String taxCategory, String frequency, String chargePrice, String billingFrequency, String durationType, String renStrategy) throws Exception
	{
		try

		{

			element(driver, accountAdminLink).click();	
			TimeUnit.SECONDS.sleep(10);
			for (String winHandle : driver.getWindowHandles()) 
			{
				driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
			}
			element(driver, manageOffers).click();
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
				element(driver, addOffer).isEnabled();
				element(driver, addOffer).click();
			}
			catch (Exception e)
			{
				TimeUnit.SECONDS.sleep(5);
				element(driver, addOffer).click();
			}


			TimeUnit.SECONDS.sleep(3);
			DateTime dt = DateTime.now();
			String dateFolder = dt.toLocalDate().toString();

			element(driver, offerName).click();
			element(driver, offerName).clear();
			element(driver, offerName).sendKeys(""+name+""+" "+dateFolder+"");
			TimeUnit.SECONDS.sleep(3);
			element(driver, offerDesc).clear();
			element(driver, offerDesc).sendKeys(description);
			TimeUnit.SECONDS.sleep(5);
			//element(driver, minCopies).clear();
			//element(driver, minCopies).sendKeys(minimumCopies);
			//TimeUnit.SECONDS.sleep(3);
			//element(driver, optIssues).clear();
			//element(driver, optIssues).sendKeys(optimisticIssues);
			//TimeUnit.SECONDS.sleep(3);
			//element(driver, graIssues).clear();
			//element(driver, graIssues).sendKeys(graceIssues);
			Select(element(driver, billCollType)).selectByVisibleText(billingCollection);


			TimeUnit.SECONDS.sleep(5);
			element(driver, prosCheck).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, startDate).clear();
			TimeUnit.SECONDS.sleep(5);
			element(driver, startDate).sendKeys(starDate);
			element(driver, endDate).clear();
			TimeUnit.SECONDS.sleep(5);
			element(driver, endDate).sendKeys(enDate);
			element(driver, expiryDate).clear();
			TimeUnit.SECONDS.sleep(5);
			element(driver, expiryDate).sendKeys(expDate);
			TimeUnit.SECONDS.sleep(5);
			element(driver, next).click();
			TimeUnit.SECONDS.sleep(8);
			element(driver, addSchedule).click();
			TimeUnit.SECONDS.sleep(5);
			element(driver, scheduleName).clear();
			element(driver, scheduleName).sendKeys(schName);
			element(driver, scheduleDesc).sendKeys(schDesc);
			if(schOpt.equalsIgnoreCase("Yes"))
			{
				element(driver, optm).click();
			}
			if(schCal.equalsIgnoreCase("Yes"))
			{
				element(driver, calBased).click();
			}
			if(schActv.equalsIgnoreCase("Yes"))
			{
				element(driver, active).click();
			}

			Select(element(driver, typeSubscription)).selectByVisibleText(schSubType);
			element(driver, schSave).click();
			for (int i = 1; i <= 100; i++)
			{
				try
				{
					element(driver, selectSchedule(schName)).click();
					TimeUnit.SECONDS.sleep(7);
					break;
				}

				catch (Exception e)
				{
					element(driver, fastFoward2).click();
				}
			}
			//			if(!element(driver, selectSchedule(schedules)).isSelected())
			//			{
			//				element(driver, selectSchedule(schedules)).click();
			//			}

			TimeUnit.SECONDS.sleep(3);
			element(driver, next).click();
			TimeUnit.SECONDS.sleep(8);
			element(driver, payMethod(paymentMethod)).click();
			TimeUnit.SECONDS.sleep(8);
			if(billCollType.equals("INVOICE"))
			{
				element(driver, inWays(ways)).click();
			}
			TimeUnit.SECONDS.sleep(8);
			element(driver, next).click();
			element(driver, selRegion(region)).click();
			TimeUnit.SECONDS.sleep(8);
			element(driver, next).click();
			TimeUnit.SECONDS.sleep(8);
			element(driver, price).clear();
			element(driver, price).sendKeys(priceOffer);
			TimeUnit.SECONDS.sleep(3);
			//Select(element(driver, pTotal)).selectByVisibleText(priceTotal);
			//			
			//			element(driver, pTotal).clear();
			//			element(driver, pTotal).sendKeys(priceTotal);
			TimeUnit.SECONDS.sleep(5);
			//element(driver, txCategory).clear();
			//Select(element(driver, txCategory)).selectByVisibleText(taxCategory);
			element(driver, txCategory).sendKeys(taxCategory);
			TimeUnit.SECONDS.sleep(3);
			element(driver, next).click();
			TimeUnit.SECONDS.sleep(16);
			element(driver, addChargeTerm).click();
			TimeUnit.SECONDS.sleep(8);
			element(driver, freqncy).clear();
			element(driver, freqncy).sendKeys(frequency);
			TimeUnit.SECONDS.sleep(3);
			element(driver, charPrice).clear();
			element(driver, charPrice).sendKeys(chargePrice);
			TimeUnit.SECONDS.sleep(3);
			element(driver, addPrice).click();
			TimeUnit.SECONDS.sleep(8);
			element(driver, next).click();
			TimeUnit.SECONDS.sleep(16);
			TimeUnit.SECONDS.sleep(32);
			if(element(driver, durType).isDisplayed())
			{
				Select(element(driver, durType)).selectByVisibleText(durationType);

				TimeUnit.SECONDS.sleep(8);
			}


			if(element(driver, billFrequency).isDisplayed())
			{
				element(driver, billFrequency).sendKeys(billingFrequency);
				TimeUnit.SECONDS.sleep(8);
				element(driver, next).click();
				TimeUnit.SECONDS.sleep(8);
				element(driver, next).click();
			}
			

			TimeUnit.SECONDS.sleep(8);
			element(driver, renewalStrategy(renStrategy)).click();
			TimeUnit.SECONDS.sleep(8);
			element(driver, finish).click();
			TimeUnit.SECONDS.sleep(10);
			element(driver, homeLink).click();
			TimeUnit.SECONDS.sleep(3);
			element(driver, accountAdminLink).click();	
			TimeUnit.SECONDS.sleep(10);
			for (String winHandle : driver.getWindowHandles()) 
			{
				driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
			}
			element(driver, manageOffers).click();
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
			element(driver, searchOffer).click();
			TimeUnit.SECONDS.sleep(8);
			try
			{
				for (int i = 1; i <= 100; i++)
				{
					try

					{
						if(element(driver, findRenewal(""+name+""+" "+dateFolder+"")).isDisplayed())
						{
							ATUReports.add("Created and verified a new Offer by adding a new schedule:"+schName, LogAs.PASSED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
						}
						break;
					}

					catch (Exception e)
					{
						element(driver, fastFoward1).click();
					}
				}
			}

			catch (Exception e)
			{
				ATUReports.add("Unable to create and verify a new offer- " + type, LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
				takeScreenShotOnFailure(driver, testName);
				System.out.println(e);
			}


		}
		catch(Exception e)
		{
			ATUReports.add("Unable to create and add a new schedule to an offer", LogAs.FAILED, new CaptureScreen(ScreenshotOf.BROWSER_PAGE));
			takeScreenShotOnFailure(driver, testName);
			System.out.println(e);

		}
	}
}
