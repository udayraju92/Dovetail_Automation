package com.testhouse.ObjectRepository;

import org.openqa.selenium.By;

import com.testhouse.Functions.GeneralFunctions;

/**
 * Class file to store all the objects which are relavent for the application
 * @version 1.0
 * @author Testhouse
 *
 */
public class SubscriptionManagementObjects extends GeneralFunctions
{

	public By subscriptionManagementLink = By.linkText("Subscription Management");
	public By newSubscriptionLink = By.id("newSubscriptionLink");
	
	public By spinnerSM = By.id("id_SpinnerGif_SM");
	
	public By brandPanel = By.id("newsubscription_form:brandpanelMain");
	public By promotion = By.id("newsubscription_form:id_promotion_panel");
	public By offer = By.id("newsubscription_form:id_offer_panel");
	public By customerDetail = By.id("newsubscription_form:detailpanel");
	public By address = By.id("newsubscription_form:customerAddressPanel");
	public By payment = By.id("newsubscription_form:paymentscellPanel");
	public By contact = By.id("newsubscription_form:dataprotectionpanel");
	public By orderSummary = By.id("newsubscription_form:summaryPanel");
	
	// Client and Brand Details
	public By SM_client = By.id("newsubscription_form:client_meh");
	public By SM_brand = By.id("newsubscription_form:brandList");
	public By SM_SubscriptionType = By.id("newsubscription_form:deFunction");
	
	// Promotion Details
	public By promotion_Country = By.id("newsubscription_form:promotionPanelCountryList");
	public By promotion_PromotionName = By.id("newsubscription_form:id_promotionName");
	public By promotion_Search = By.xpath("//*[@id='newsubscription_form:id_promotionName']/parent::td/following-sibling::td/a");
	public By promotion_PromotionDialog = By.id("promotionListPanelHeader");
	public By promotion_SelectPromotion(String promotionName)
	{
		By promotion_SelectPromotion = By.xpath("//td[span[text()='"+promotionName+"']]/preceding-sibling::td/a[@title='promolist']");
		return promotion_SelectPromotion;
	}
	
	public By promotion_SelectOffer(String offer)
	{
		By promotion_SelectOffer = By.xpath("//td[span[text()='"+offer+"']]/parent::tr/td/a[@title='offerlist']");
		return promotion_SelectOffer;
	}
	
	public By promotion_Offer_ActionCheckBox(String offer)
	{
		By product_ActionCheckBox = By.xpath("//td[span[text()='"+offer+"']]/parent::tr/td/input[@type='checkbox']");
		return product_ActionCheckBox;
	}
	
	public By product_Quantity(String productName)
	{
		By product_Quantity = By.xpath("//tr[td[span[text()='"+productName+"']]]/td/input[contains(@id,'PurchaseQuantity')]");
		return product_Quantity;
	}
	
	public By product_Quantity_ActionCheckBox(String productName)
	{
		By product_Quantity_ActionCheckBox = By.xpath("//tr[td[span[text()='"+productName+"']]]/td/input[contains(@id,'id_selectedOffer')]");
		return product_Quantity_ActionCheckBox;
	}
	
	public By product_SubmitButton = By.id("offerListForm:id_selectOfferNextBtn");
	
	public By verifySelectedPromotion = By.id("newsubscription_form:id_selectedPromoName1");
	public By verifySelectedPayment = By.id("newsubscription_form:id_selectedPaymentType1");
	
	// Customer Details
	public By customerDetail_Title = By.id("newsubscription_form:title");
	public By customerDetail_FirstName = By.id("newsubscription_form:forname");
	public By customerDetail_SurName = By.id("newsubscription_form:surname");
	public By customerDetail_ReferenceNumber = By.id("newsubscription_form:inRef");
	
	public By customerReferenceNumber = By.id("j_id89:confirmationForm:finalOrderReference");
	public By searchByReferenceNumber = By.id("newsubscription_form:search");
	
	public By selectCustomer(String customerReferenceNumber)
	{
		By selectCustomer = By.xpath("//td[text()='"+customerReferenceNumber+"']/preceding-sibling::td/a[@title='custlist']");
		return selectCustomer;
	}
	
	// Address Details
	public By addressDetails_SelectAddress_Dialog = By.id("addressListPanelHeader");
	public By addressDetails_Postcode = By.id("newsubscription_form:customerAddressPostcode");
	public By addressDetails_PostCodeSearch = By.xpath("//td[input[@id='newsubscription_form:customerAddressPostcode']]/following-sibling::td/a[@title='Search']");
	public By addressDetails_SelectAddress(String address)
	{
		By addressDetails_SelectAddress = By.xpath("//td[span[contains(text(),'"+address+"')]]/preceding-sibling::td/a/img");
		return addressDetails_SelectAddress;
	}
	
	// Card Details
	public By paymentDetails_CreditCard_CustomerName = By.id("newsubscription_form:paymentcustomername");
	public By paymentDetails_CreditCard_CardNumber = By.id("newsubscription_form:cardNumber");
	public By paymentDetails_CreditCard_ExpiryMonth = By.id("newsubscription_form:edsm_som");
	public By paymentDetails_CreditCard_ExpiryYear = By.id("newsubscription_form:edsy_som");
	
	public By paymentDetails_DirectDebit_CustomerName= By.id("newsubscription_form:directdebitaccountholder");
	public By paymentDetails_DirectDebit_AccountNumber= By.id("newsubscription_form:directdebitaccountnumber");
	public By paymentDetails_DirectDebit_SortCode = By.id("newsubscription_form:ddSortcode");
	public By paymentDetails_DirectDebit_SearchSortCode = By.xpath("//td[input[@id='newsubscription_form:ddSortcode']]/following-sibling::td/a[@title='Search']");
	
	// Select Demographics
	public By demographics(String demographicProperty1, String demographicProperty2, String demographicProperty3)
	{
		By demographics = By.xpath("//tr[td[contains(@id,'j_id653') and ./span[contains(text(),'"+demographicProperty1+"')]] and td[contains(@id,'j_id655') and ./span[contains(text(),'"+demographicProperty2+"')]] and  td[contains(@id,'j_id657') and ./span[ contains(text(),'"+demographicProperty3+"')]]]/td/input");
		return demographics;
	}
	
	// Order Summary
	public By orderSummary_NoOfCopies = By.id("newsubscription_form:summaryNoOfCopies");
	
	public By confirmButton_SM = By.id("newsubscription_form:confirmOrderButton");
	
	public By confirmButton_ProductOnly_SM = By.id("newsubscription_form:poConfirmOrderButton");
	
	public By placeOrderButton = By.id("customerassociationsOutputPanel:j_id134:confirmOrderButton");
	
	public By orderConfirmation_Next = By.id("j_id89:confirmationForm:id_nextbutton");
	public By orderConfirmation_Restart = By.id("j_id89:confirmationForm:id_restartbutton");
	public By orderConfirmation_Exit = By.id("j_id89:confirmationForm:id_exitButton");
	
}
