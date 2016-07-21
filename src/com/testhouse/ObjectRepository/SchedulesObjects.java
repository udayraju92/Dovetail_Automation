package com.testhouse.ObjectRepository;

import org.openqa.selenium.By;

import com.testhouse.Functions.GeneralFunctions;

public class SchedulesObjects extends GeneralFunctions
{
	/* Schedule Objects */
	public By accountAdminLink = By.linkText("Account Admin");
	public By manageSchedule = By.id("iconid_manageSchedules");
	public By clientSelect = By.id("id_scheduleListform:client");
	
	public By brandSelect(String brand)
	{
		By brandSelect = By.linkText(brand);
		return brandSelect;
	}
	public By fastFoward = By.xpath("//td[text()='»']");
	public By addSchedule = By.id("id_scheduleListform:id_addScheduleButton");
	
	/* New Schedule */
	public By scheduleName = By.id("id_scheduleForm:id_nameDecorator:name");
	public By scheduleDesc = By.id("id_scheduleForm:id_descriptionDecorator:desc");
	public By optimisticCheck = By.id("id_scheduleForm:opt");
	public By calenderBasedCheck = By.id("id_scheduleForm:cal");
	public By activeCheck = By.id("id_scheduleForm:act");	
	public By pActiveCheck = By.id("j_id99:id_productOnly");
	public By subscriptionType = By.id("id_scheduleForm:id_typeOfSubscriptionDecorator:charge");
	public By saveScheduleBtn = By.id("id_scheduleForm:id_saveButton");
	public By schedulesList = By.id("id_scheduleForm:id_schedulesButton");
	public By eventDelivery = By.xpath("//*[@id='j_id99:j_id130_body']/table/tbody/tr[2]/td[2]/select");
	public By backDelivery = By.id("j_id99:id_IncludeOption:0");
	public By schButton = By.id("id_scheduleForm:id_schedulesButton");
	public By test1 = By.xpath("//table[@id='j_id99:id_allbackIssues']/tbody/tr/td[1][span[text()='JUN 05']]/following-sibling::td/input");
	
	//(//table[@id='j_id99:id_allbackIssues']/tbody/tr/td[1])[1]
	public By eventDelivery(String bIssues)
	{
		By eventDelivery = By.xpath("//table[@id='j_id99:id_allbackIssues']/tbody/tr/td[1][span[text()='"+bIssues+"']]/following-sibling::td/input");
		// By eventDelivery = By.xpath("//*[contains(text(),'JUL 05')]/parent::td/following::input");
        return eventDelivery;
	}
	
	public By test = By.id("j_id99:id_allbackIssues:1:id_selectedBackIssue");
	
	public By findSchedule(String name)
	 {
	  By brandSelect = By.xpath("//*[contains(text(),'"+name+"')]");
	  return brandSelect;
	 }

	/* Edit Schedule*/
	public By editSchedule(String schedule) 
	{
	By editSchedule	= By.xpath("//*[contains(text(),'"+schedule+"')]//following::td[3]");
	return editSchedule;
	}
	public By schOffer = By.id("id_scheduleForm:id_offersButton");
	public By offerManager = By.xpath("//*[contains(text(),'Offers')]");
	
	
	/* New Event */
	public By scheduleEventsBtn = By.id("id_scheduleForm:id_eventsButton");
	public By newEventBtn = By.id("form:j_id93");
	public By eventWhen = By.xpath("//select[@id='j_id99:eventWhen' and @name='j_id99:eventWhen']");
	public By eventDeliveryType = By.xpath("//select[@id ='j_id99:deliverableType' and @name = 'j_id99:deliverableType']");
	public By eventNextBtn = By.xpath("//input[@value = 'Next>' and @type = 'button']");
	public By eventDest = By.xpath("//select[@id = 'j_id99:destinationType' and @name = 'j_id99:destinationType']");
	public By eventFinishBtn = By.id("j_id99:j_id150");
	public By eventSaveBtn = By.id("form:j_id94");
	public By eventBackBtn = By.id("form:j_id95");
}
