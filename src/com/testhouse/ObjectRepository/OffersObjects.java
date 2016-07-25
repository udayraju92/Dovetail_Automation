package com.testhouse.ObjectRepository;

import org.openqa.selenium.By;

import com.testhouse.Functions.RenewalStrategyFunctions;

public class OffersObjects extends RenewalStrategyFunctions
{
	/* Offers Objects */
	public By accountAdminLink = By.linkText("Account Admin");
	public By manageOffers = By.id("iconid_manageOffers");
	public By clientSelect = By.id("j_id54:client");

	public By brandSelect(String brand)
	{
		By brandSelect = By.linkText(brand);
		return brandSelect;
	}
	public By fastFoward = By.xpath("//td[text()='»']");
	public By fastFoward1 = By.xpath("//*[@id='id_offersListForm:id_offerList:j_id126_table']/tbody/tr/td[16]");
	public By fastFoward2 = By.xpath("//*[contains(@id,'brandScheduleList')]/tbody/tr/td[text()='»']");
	public By addOffer = By.xpath("//input[@value = 'New...' and @type = 'button']");

	/* New Offer */
	public By offerName = By.xpath("//input[@value = '<New Offer>' and @type = 'text']");
	public By offerDesc = By.id("id_offerForm:j_id131:id_offerWebDescriptionDecorator:offerWebDescription");
	public By minCopies = By.id("id_offerForm:j_id131:id_minimumCopiesDecorator:minCopies");
	public By optIssues = By.id("id_offerForm:j_id131:id_optimisticIssuesDecorator:optimisticIssues");
	public By graIssues = By.id("id_offerForm:j_id131:id_optimisticIssuesDecorator:optimisticIssues");

	public By startDate = By.id("id_offerForm:j_id131:id_startDateDecorator:startDateInputDate");
	public By endDate = By.id("id_offerForm:j_id131:id_endDateDecorator:endDateInputDate");
	public By expiryDate = By.id("id_offerForm:j_id131:id_expiryDateDecorator:expiryDateInputDate");
	public By next = By.xpath("//input[@type='button' and @value = 'Next >']");
	public By inWays(String ways)
	{
		By inWays= By.xpath("//input[contains(@id,'invWaysToPay') and @value='"+ways+"']");
		return inWays;
	}


	/* Upgrade subscription*/
	public By upgrade = By.xpath("//div[text()='Upgrade Configuration']");
	public By upgradeType = By.xpath("//div[contains(@class,'picklist-button') and text()='Copy all']");
	public By upBrand = By.xpath("//select[contains(@id,'brandSelection')]");
	public By upRegions = By.xpath("//select[contains(@id,'offerSelection')]");
	public By addUpgrade = By.xpath("//*[@id='id_offerForm:j_id131:id_addUpgradeOption']");
	public By addBoundary = By.xpath("//input[@value = 'Add Boundary' and @type = 'button']");
	public By addBoundaryFrom = By.xpath("//input[@id='addUpgradeBoundaryForm:id_fromDecorator:boundaryFrom' and @type = 'text']");
	public By addBoundaryCharge = By.xpath("//input[@id='addUpgradeBoundaryForm:id_chargeDecorator:charge' and @type = 'text']");
	public By addUp = By.xpath("//input[contains(@id,'addUpgradeBoundaryForm') and @value='Add']");
	public By addOption = By.xpath("//input[contains(@id,'offerForm') and @value = 'Add Option']");


	public By selectSchedule(String schedule)
	{
		By selectSchedule = By.xpath("//td[@class='rich-table-cell' and text() ='"+schedule+"']/following-sibling::td/div/input[@value = 'Select' and @type = 'button']");
		return selectSchedule;
	}

	public By payMethod(String paymentMethod)
	{
		By payMethod = By.xpath("//input[@value = '"+paymentMethod+"' and @type = 'checkbox']");
		return payMethod;
	}
	public By selRegion(String region)
	{
		By selRegion = By.xpath("//td[text()='"+region+"']/input[@type='checkbox']");
		return selRegion;
	}
	public By renewalStrategy(String rS)
	{
		By renewalStrategy = By.xpath("//tr/td[text() = '"+rS+"']/following-sibling::td/div/input[@value = 'Select' and @type = 'button']");
		return renewalStrategy;
	}

	public By finish = By.xpath("//input[@value='Finish' and @type='button']");

	public By price = By.xpath("//input[@type='text' and @value='<New Price>']");
	public By pTotal = By.xpath("//*[@id='id_offerForm:j_id131:id_priceTaxTypeDecorator:id_priceTaxType']");
	public By txCategory = By.xpath("//*[@id='id_offerForm:j_id131:id_priceTaxRateDecorator:id_priceTaxRate']");
	public By addChargeTerm = By.xpath("//input[@value = 'Add Charging Term...' and @type = 'button']");
	public By freqncy = By.xpath("//*[@id='chargingForm:chargingFreq']");
	public By charPrice = By.xpath("//*[@id='chargingForm:id_chargingPrice']");
	public By addPrice = By.xpath("//*[@id='chargingForm:add2']");
	public By billFrequency = By.xpath("//input[contains(@id,'billingFreq')]");
	public By durType = By.xpath("//select[contains(@id, 'durationType') and contains(@name,'durationType')]");
	public By billCollType = By.xpath("	//select[@id = 'id_offerForm:j_id131:id_billingCollectionTypeDecorator:id_billingCollectionType' and @name = 'id_offerForm:j_id131:id_billingCollectionTypeDecorator:id_billingCollectionType']");
	public By prosCheck = By.xpath("//input[@id='id_offerForm:j_id131:prospectonly' and @type = 'checkbox']");

	public By findOffer(String name)
	{
		By findOffer = By.xpath("//a[contains(text(), '"+name+"')]");
		return findOffer;
	}
	//Maintenance Offer
	public By search = By.xpath("//input[contains(@id,'offerNameInputText') and @type = 'text']");
	public By searchOffer = By.xpath("//*[@id='id_offersListForm:j_id80']");
	public By offerList(String offer) 
	{
		By offerList = By.xpath("//a[contains(@id,'offerList') and text()='"+offer+"']");
	return offerList;
	}
	
	public By delete = By.xpath("//input[@value='Delete' and @type = 'button']");
	
	//Add schedule from offer page
	
	public By addSchedule = By.xpath("//*[@id='id_offerForm:j_id131:id_offerWizardAddScheduleButton']");
	public By scheduleName = By.xpath("//input[@value = '<new schedule>' and @type= 'text']");
	public By scheduleDesc = By.xpath("//input[contains(@id, 'descriptionDecorator:desc') and @type = 'text']");
	public By optm = By.xpath("//*[@id='id_offerForm:j_id131:opt']");
	public By calBased = By.xpath("//*[@id='id_offerForm:j_id131:cal']");
	public By active = By.xpath("//*[@id='id_offerForm:j_id131:act']");
	public By typeSubscription = By.xpath("//select[contains(@id, 'typeOfSubscription')]");
	public By schSave = By.xpath("//input[@value = 'Save' and @type = 'button']");
}

