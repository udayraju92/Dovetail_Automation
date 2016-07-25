package com.testhouse.ObjectRepository;

import org.joda.time.DateTime;
import org.openqa.selenium.By;

/**
 * Class file to store all the objects which are relavent for the application
 * @version 1.0
 * @author Testhouse
 *
 */
public class CustomerServiceObjects
{
	/* Login & Logout Objects */
	public By username = By.id("id_loginForm:id_username");
	public By password = By.id("id_loginForm:id_password");
	public By logIn = By.id("id_loginForm:id_loginButton");
	public By logOut = By.linkText("Logout");


	public By spinner = By.xpath("//img[contains(@id,'SpinnerGif')]");


	/* Customer Service Objects */
	public By customerServiceLink = By.linkText("Customer Services");
	public By clientSelect = By.id("brandSearchForm:client");

	public By brandSelect(String brand)
	{
		By brandSelect = By.linkText(brand);
		return brandSelect;
	}


	public By fastFoward = By.xpath("//td[text()='»']");
	public By newSubscription = By.id("newSubscriptionLink");	
	public By promotionName = By.id("form:id_promoNameInputText");
	public By findPromotion = By.xpath("//*[contains(@value,'Find Promotion')]");
	public By country_Promotion = By.id("");
	public By getDefaultPromotion = By.id("form:id_getDefaultPromotionButton");
	public By selectedPromotion = By.xpath("//*[@id='form:id_promoMessagePanel']/tbody/tr[1]/td[2]/span");
	public By selectedPromotionReferenceNumber = By.xpath("//*[@id='form:id_promoMessagePanel']/tbody/tr[2]/td[2]/span");
	
	public By selectPromotion(String promotion)
	{
		By selectPromotion = By.xpath("//tr/td[@class='rich-table-cell center' and text()='"+promotion+"']/following-sibling::td/input");
		return selectPromotion;
	}
	public By promotionNextBtn = By.id("form:id_selectPromotionNext");


	public By offerCard(String card)
	{
		By offerCard = By.xpath("//*[contains(text(),'"+card+"')]/parent::td/following::td/input");
		return offerCard;
	}

	public By title = By.id("id_newCustomerDetailsForm:id_customerTitleDecorator:id_customerTitle");
	public By firstName = By.id("id_newCustomerDetailsForm:id_customerForenameDecorator:id_customerForename");
	public By surName = By.id("id_newCustomerDetailsForm:id_customerSurnameDecorator:id_customerSurname");
	public By postCode = By.id("id_newCustomerDetailsForm:id_custInputDetailsPostCodeDecorator:id_custInputDetailsPostCode");
	public By lookupAddress = By.id("id_newCustomerDetailsForm:id_customerBillingAddressLookupButton");
	public By selectAddress = By.name("id_newCustomerDetailsForm:j_id147");
	public By custNextBtn = By.id("id_newCustomerDetailsForm:id_nextButton");
	public By custAssociationNextBtn = By.id("id_btnNext");
	public By issueCalenderNextBtn = By.id("issueCalendarForm:id_btnOrderNext");
	public By customerName = By.id("id_checkoutForm:id_decorateCreditCardCustomerName:id_creditCardCustomerName");
	public By cardNumber = By.id("id_checkoutForm:id_decorateCreditCardNumber:id_creditCardNumber");
	public By expiryDate = By.name("id_checkoutForm:j_id180:j_id188");
	public By expiryYear = By.xpath("//div[@id='id_checkoutForm:id_decorateCreditCardExpiryYear:j_id202']/select");
	public By checkoutNextBtn = By.id("id_checkoutForm:id_nextButton");
	public By orderRefNumber = By.id("id_orderRef");
	public By differentDeliveryAddress = By.id("id_newCustomerDetailsForm:id_deliveryAddressTogglePanel_header");
	public By deliveryPostCode = By.id("id_newCustomerDetailsForm:id_customerDeliveryPostCodeDecorator:id_customerDeliveryPostCode");
	public By deliveryLookupAddress = By.id("id_newCustomerDetailsForm:id_customerDeliveryAddressLookupButton");
	public By selectDeliveryAddress = By.name("id_newCustomerDetailsForm:j_id186");
	public By verifyDeliveryAddress(String postCode) 
	{
		return By.xpath("//span[text()='"+postCode+"' and @id='issueCalendarForm:id_textCustPostCode']");
	}
	public By verifyBillingAddress(String postCode) 
	{
		return By.xpath("//span[text()='"+postCode+"' and @id='issueCalendarForm:id_textBillingPO']");
	}
	
	public By demographics(String demographicProperty1, String demographicProperty2, String demographicProperty3)
	{
		By demographics = By.xpath(".//tr[td[contains(@id,'j_id214') and contains(text(),'"+demographicProperty1+"')] and td[contains(@id,'j_id216') and contains(text(),'"+demographicProperty2+"')] and  td[contains(@id,'j_id218') and contains(text(),'"+demographicProperty3+"')]]/td[4]/input");
		return demographics;
	}
	
	public By contactReason = By.xpath("//*[contains(@id,'id_contactReasonCombocomboboxButton')]");
	public By selectReason = By.xpath("//span[text()='Billing and Payment - Refund Request' and @class='rich-combobox-item']");
	public By saveReason = By.id("j_id9:id_saveReason");
	
	// Verify the newly created order
	public By homeLink = By.linkText("Main Menu");
	public By csHomeLink = By.linkText("CS_HOME");
	public By serviceExistingSubscriptionLink = By.id("existingSubscriptionLink");
	public By customerRefSearch = By.id("searchCriteria:id_custrefinputtext");
	public By viewCustomersButton = By.id("searchCriteria:id_advancedCustomerSearchButton");

	/*public By viewCustomer(String orderNum)
	{
		By viewCustomer = By.id("searchCriteria:customerTable:"+orderNum+":selectCustomerButton");
		return viewCustomer;
	}*/
	public By viewCustomer(String orderNum)
	{
		By viewCustomer = By.xpath("//*[contains(text(),'"+orderNum+"')]/following::td/input");
		return viewCustomer;
	}
	
	public By viewCustomerButton = By.xpath("//table[@id='searchCriteria:customerTable']/tbody/tr[1]/td/input");

	public By verifyCustomerRef = By.id("summaryform:id_textCustOptionRef");
	public By verifyContractStatus = By.id("summaryform:id_currContractStatustext");
	public By verifySubType = By.id("summaryform:id_textCustOptionSubcriptionType");
	public By paymentMethod = By.xpath("//*[contains(text(),'Payment Method')]/parent::td/following-sibling::td/span");
	public By paymentStatus = By.id("summaryform:id_currcontractpaymentStatustext");
	public By renewalStatus = By.id("summaryform:id_currcontractrenewalStatustext");
	public By subscriberRole = By.id("summaryform:id_textCustOptionSubcriberType");

	//For DD subscription from CS screen
	public By accountHolderName = By.id("id_checkoutForm:id_accountHolderDecorator:id_accountHolder");
	public By accountNumber = By.id("id_checkoutForm:id_accountNumberDecorator:id_accountNumber");
	public By sCode = By.id("id_checkoutForm:id_sortCodeDecorator:id_sortCode");
	public By lookupBankButton = By.id("id_checkoutForm:id_lookupBankAddressButton");

	//For Gift subscription from CS screen
	public By giftSubs = By.id("id_newCustomerDetailsForm:id_custDetailsGiftSubscriptionButton");
	public By gTitle = By.id("id_newCustomerDetailsForm:id_recipientTitleDecorator:id_recipientTitle");
	public By gFirstName = By.id("id_newCustomerDetailsForm:id_recipientFirstNameDecorator:id_recipientFirstName");
	public By gSurName = By.id("id_newCustomerDetailsForm:id_recipientSurnameDecorator:id_recipientSurName");
	public By gPostCode = By.id("id_newCustomerDetailsForm:id_recipientPostCodeDecorator:id_recipientPostCode");
	public By gLookupAddress = By.id("id_newCustomerDetailsForm:id_custRecipientAddressLookupButton");
	public By gSelectAddress = By.name("id_newCustomerDetailsForm:j_id285");
	public By gSaveButton = By.name("id_newCustomerDetailsForm:id_saveRecipientCustomerButton");
	public By gAddMoreButton = By.id("id_newCustomerDetailsForm:id_addNewRecipientCustomerButton");
	public By gRecipientName(String gTitle, String gFirstName, String gSurName)
	{
		By gRecipientName = By.xpath("//div[text()='"+gTitle+" "+gFirstName+" "+gSurName+"' and contains(@id,'header')]");
		return gRecipientName;
	}
	
	public By gEditButton(String gTitle, String gFirstName, String gSurName)
	{
		By gEditButton = By.xpath("//div[text()='"+gTitle+" "+gFirstName+" "+gSurName+"']/following-sibling::*[contains(@id,'body')]/table/tbody//tr/td/input[@value='Edit']");
		return gEditButton;
	}
	
	public By gRemoveButton(String gTitle, String gFirstName, String gSurName)
	{
		By gRemoveButton = By.xpath("//div[text()='"+gTitle+" "+gFirstName+" "+gSurName+"']/following-sibling::*[contains(@id,'body')]/table/tbody//tr/td/input[@value='Remove']");
		return gRemoveButton;
	}
	
	public By gRecipientNameOrderDetails(String gTitle, String gFirstName, String gSurName)
	{
		By gRecipientName = By.xpath("//div[text()='"+gTitle+" "+gFirstName+" "+gSurName+"' and contains(@id,'header') and contains(@id,'issueCalendarForm')]");
		return gRecipientName;
	}
	
	//For product only subscription
	public By proRadioButton = By.id("form:id_subscriptionOption:1");
	public By listProButton = By.id("j_id53:id_textListProducts");
	public By quantityNum(String productName) 
	{
		By quantityNum = By.xpath("//td[span[text()='"+productName+"']]/following-sibling::td[2]/input");
		return quantityNum;
	}
	public By quantitySel(String productName)
	{
		By quantitySel = By.xpath("//td[span[text()='"+productName+"']]/following-sibling::td[3]/input");
		return quantitySel;
	}
	public By quantityNext = By.id("j_id53:id_selectOfferNext");
	public By proSubStatus = By.id("summaryform:id_productCurrContractStatustext");
	public By proAccountId = By.id("summaryform:id_textProductCustOptionRef");
	public By proSubType = By.id("summaryform:id_textProductCustOptionSubcriptionType");
	public By proPaystatus = By.id("summaryform:id_pProductCurrcontractpaymentStatustext");

	//Search CS screen
	public By verifySearch = By.xpath("//input[contains(@id, 'selectCustomerButton')]");
	public By addressLineSearch = By.id("searchCriteria:id_line1inputtext");
	public By postcodeSearch = By.id("searchCriteria:id_postcodeinputtext");
	public By companyNameSearch = By.id("searchCriteria:id_companynameinputtext");
	public By lastNameSearch = By.id("searchCriteria:id_line1inputtext");
	public By firstNameSearch = By.id("searchCriteria:id_forenameinputtext");
	public By emailSearch = By.id("searchCriteria:id_Emailinputtext");
	public By accNumberSearch = By.id("searchCriteria:id_accountnumberinputtext");
	public By sortCodeSearch = By.id("searchCriteria:id_sortcodeinputtext");
	public By creditCardNumSearch = By.id("searchCriteria:id_cccardnumberinputtext");
	public By countrySearch = By.id("searchCriteria:id_countrylistmenu");
	public By orderReferenceSearch = By.id("searchCriteria:id_orderreferencetext");
	public By PrevSubReferneceSearch = By.id("searchCriteria:id_prevsubrefinputtext");
	public By webIDSearch = By.id("searchCriteria:id_webidinputtext");
	public By chequeNumberSearch = By.id("searchCriteria:id_chequenumberinputtext");
	public By invoiceNumberSearch = By.id("searchCriteria:id_invoicenumberinputtext");

	//Overseas
	public By selectCountry = By.id("form:id_countryMenu");
	public By overAddress = By.id("id_newCustomerDetailsForm:id_customerBillingAddressLine1Decorator:id_customerBillingAddressLine1");

	//Service existing subs
	public By amendDetails = By.linkText("Amend Details");
	public By changeName = By.id("amendDetailsform:id_inputTextAmendDetailsNameDecorator:id_inputTextAmendDetailsName");
	public By changeAddress = By.id("amendDetailsform:id_inputTextAmendDetailsLine1Decorator:id_inputTextAmendDetailsLine1");
	public By changeDetails = By.id("amendDetailsform:id_inputTextAmendDetailsAlter");
	public By fName = By.id("summaryform:id_textCustOptionForeName");
	public By summary = By.linkText("Summary");	

	//Renew Subscription /Upgrade Subscription
	public By renewButton = By.xpath("//input[@value='Renew Subscription']");
	public By upgradeButton = By.xpath("//input[@value='Upgrade Subscription']");
	public By selectBrand = By.xpath("//*[@id='form:j_id60_body']/table/tbody/tr[1]/td[2]/select");
	public By selectContract = By.xpath("//*[@id='form:j_id60_body']/table/tbody/tr[2]/td[2]/select");

	//Transaction History
	public By transactionEnquiry = By.linkText("Transaction Enquiry");
	public By amendContract = By.id("transEnquiryform:id_transactionEnquiry_amend_contract_tab_lbl");

	//Customer History
	public By customerHistory = By.linkText("Customer History");

	//Amend Contract - System Letter
	public By systemLetter = By.id("transEnquiryform:id_amendContractButtonSystemLetter");
	public By sendLetter(String letterName)
	{
		By sendLetter = By.xpath("//td[text()='"+letterName+"']/following-sibling::td[2]/div/input[@value='Send']");
		return sendLetter;
	}
	public By sendLetterVerification = By.xpath("//div[text()='The letter has been sent for processing']");

	//Amend Contract - Change Start Issue
	public By changeStartIssue = By.id("transEnquiryform:id_amendContractButtonChangePreferredStartIssue");
	public By selectStartIssue = By.name("transEnquiryform:j_id421");
	public By changeContract = By.id("transEnquiryform:id_amendStartIssueButton");
	public By changeStartIssueVerification = By.xpath("//div[contains(text(),'The contract has been changed successfully')]");

	//Amend Contract - Suspend
	public By suspend = By.id("transEnquiryform:id_amendContractButtonShowSuspend");
	public By selectReasonSuspend = By.id("transEnquiryform:id_amendContractSuspendReasoncomboboxButton");
	public By suspendReason(String reason)
	{
		By selectReason = By.xpath("//span[contains(text(),'"+reason+"') and contains(@class,'rich-combobox-item')]");
		return selectReason;
	}

	public By confirmSuspend = By.id("transEnquiryform:id_amendContractButtonConfirmSuspend");
	public By suspendStartingFrom = By.xpath("//input[contains(@id,'InputDate')]");
	public By suspendedStatus = By.xpath("//td[./*[./*[contains(@id,'arrowImg') and contains(@style,'inline')]]]/following-sibling::td[4]/div[1]/span");

	//Amend Contract - Resume
	public By resume = By.id("transEnquiryform:id_amendContractButtonShowResume");
	public By selectReasonResume = By.id("transEnquiryform:id_amendContractResumeReasoncomboboxButton");
	public By resumeReason(String reason)
	{
		By selectReason = By.xpath("//span[contains(text(),'"+reason+"') and contains(@class,'rich-combobox-item')]");
		return selectReason;
	}
	public By resumeStartingFrom = By.id("transEnquiryform:j_id1035comboboxButton");
	public By resumeStartingSelect(String month)
	{
		By selectResumeStartingFrom = By.xpath("//span[contains(text(),'"+month+"') and contains(@class,'rich-combobox-item')]");
		return selectResumeStartingFrom;
	}
	public By resumeStartingDate = By.id("transEnquiryform:j_id1038InputDate");
	public By confirmResume = By.id("transEnquiryform:id_amendContractButtonConfirmResume");
	public By resumeVerification = By.xpath("//table[@id='historyform:j_id69']/tbody/tr[last()]/td[3]");

	//Amend Contract - Refund Details
	public By refundDetails = By.id("transEnquiryform:id_amendContractButtonRefundDetails");

	//Amend Contract - Mailing Method
	public By mailingMethod = By.id("transEnquiryform:id_amendContractButtonMailingChange");
	public By selectMailingMethod = By.name("transEnquiryform:j_id1018");
	public By confirmMailingMethod = By.id("transEnquiryform:id_amendMailingMethod");
	public By verifyDispatchType = By.id("summaryform:id_textCustOptionDispatchType");

	//Amend Contract - Change Term
	public By changeTerm = By.id("transEnquiryform:id_amendContractButtonTermChange");
	public By changeTerm_Action = By.id("transEnquiryform:id_amendTermActionMenu");
	public By changeTerm_Reason = By.id("transEnquiryform:id_amendTermReasonMenu");
	public By changeTerm_IssueType = By.id("transEnquiryform:id_amendTermIssueTypeMenu");
	public By changeTerm_NoOfIssues = By.id("transEnquiryform:id_inputTextAmendContractTermNoOfIssues");
	public By changeTerm_Save = By.xpath("//input[@type='submit' and @value='Save']");
	public By changeTermVerification(String reason) 
	{
		return By.xpath("//table[@id='historyform:j_id69']/tbody/tr[last()]/td[3][contains(text(),'"+reason+"')]");
	}
	public By changeTermVerification1(String action, String issueType, String noOfIssues)
	{
		return By.xpath("//table[@id='historyform:j_id69']/tbody/tr[last()]/td[4][contains(text(),'Change request for "+action+" with Issue Numbers : "+noOfIssues+", Issue Type : "+issueType+", Date : "+DateTime.now().toString("dd/MM/yyyy")+"')]");	
	}

	//Amend Contract - Payment Details
	public By paymentDetails = By.id("transEnquiryform:id_amendContractButtonPaymentDetails");
	// Direct Debit Reference Number
	public By paymentDetails_DirectDebit_AccountName = By.id("transEnquiryform:setClientName");
	public By paymentDetails_DirectDebit_AccountNumber = By.id("transEnquiryform:setClientSageReference");
	public By paymentDetails_DirectDebit_SortCode = By.id("transEnquiryform:id_inputTextSortCode");
	public By paymentDetails_DirectDebit_PaymentStartDate = By.id("transEnquiryform:startDateInputDate");
	public By paymentDetails_DirectDebit_SaveChangesButton = By.id("transEnquiryform:id_DDSubmitAccountChanges");

	//Cheque Payment Reference Number
	public By paymentDetails_Cheque_AccountName = By.id("transEnquiryform:id_chqAccName");
	public By paymentDetails_Cheque_AccountNumber = By.id("transEnquiryform:id_chqAccNo");
	public By paymentDetails_Cheque_SortCode = By.id("transEnquiryform:id_chqSortCode");
	public By paymentDetails_Cheque_ChequeNumber = By.id("transEnquiryform:id_inputTextChqNo");
	public By paymentDetails_Cheque_SaveChangesButton = By.id("transEnquiryform:id_ChqSubmitDetailsChanges");

	// Credit Card Reference Number
	public By paymentDetails_CreditCard_AccountName = By.id("transEnquiryform:id_ccCardName");
	public By paymentDetails_CreditCard_CardNumber = By.id("transEnquiryform:id_cardNumber");
	public By paymentDetails_CreditCard_ExpiryDate_Month = By.id("transEnquiryform:id_dateExpirySelectMonth");
	public By paymentDetails_CreditCard_ExpiryDate_Year = By.id("transEnquiryform:id_dateExpirySelectYear");
	public By paymentDetails_CreditCard_StartDate_Month = By.id("transEnquiryform:id_dateExpirySelectMonth");
	public By paymentDetails_CreditCard_StartDate_Year = By.id("transEnquiryform:id_dateExpirySelectYear");
	public By paymentDetails_CreditCard_SaveChangesButton = By.id("transEnquiryform:id_CCSubmitChanges");

	public By paymentDetailsVerification = By.xpath("//table[@id='historyform:j_id69']/tbody/tr[last()]/td[3][contains(text(),'Payment detail changed')]");

	//Amend Contract - Add Payment
	public By addPayment= By.id("transEnquiryform:id_amendContractButtonAddPayment");
	public By paymentType = By.id("transEnquiryform:id_pymtMethodTypes");
	public By addPayment_DirectDebit_AccountName = By.id("transEnquiryform:id_ddAccNameAddPymt");
	public By addPayment_DirectDebit_AccountNumber = By.id("transEnquiryform:id_ddAccNoAddPymt");
	public By addPayment_DirectDebit_SortCode = By.id("transEnquiryform:id_inputTextSortCodeAddPymt");
	public By addPayment_DirectDebit_PaymentStartDate = By.id("transEnquiryform:id_startDateAddPymtInputDate");
	public By addPayment_DirectDebit_PreferredDDCollectionDate = By.name("transEnquiryform:id_pDDCollectionDateDecoratorAddPymt:j_id327");
	public By addPayment_Submit = By.id("transEnquiryform:id_submitPayment");
	public By addPaymentVerification = By.xpath("//div[text()='Payment has been sent for processing']");


	//Amend Contract - Cancel Immediately
	public By cancelImmediately = By.id("transEnquiryform:id_amendContractButtonShowCancel");
	public By chequeRefundCheckBox = By.id("transEnquiryform:_id_chequeRefundCBox");
	public By immediateCancellationReason = By.id("transEnquiryform:_id_amendImmediateCancelReason");
	public By immediateCancellationrefundAmount = By.id("transEnquiryform:j_id448:0:_cancelRefund");
	public By immadiateCancellationConfirm = By.id("transEnquiryform:_id_amendContractButtonConfirmContractCancelleation");

	public By cancelImmediatelyVerification = By.xpath("//div[contains(text(),'CONTRACT CANCELLED SUCCESSFULLY.  Total Refunded')]");
		
	//Amend Contract - Cancel on Expiry
	public By cancelOnExpiry = By.id("transEnquiryform:id_amendContractButtonCancelOnExpiry");
	public By cancellationReasonOnExpiry = By.id("transEnquiryform:_id_amendImmediateExpiryCancelReason");
	public By confirmCancelonExpiry = By.id("transEnquiryform:_id_amendContractButtonConfirmExpiry");
	public By cancelOnExpiryVerification = By.xpath("//div[text()='CONTRACT SET TO CANCEL ON EXPIRE SUCCESSFULLY']");


	//	Amend Contract - Refund Amount
	public By refundAmount = By.id("transEnquiryform:id_amendContractButtonRefundAmount");
	public By refundAmountValue = By.id("transEnquiryform:overiderefund");
	public By refundReason = By.id("transEnquiryform:id_refundReason");
	public By confirmRefund = By.id("transEnquiryform:id_amendContractButtonConfirmContractRefund");
	public By refundDetailsVerification = By.xpath("//table[@id='transEnquiryform:clientTable']/tbody/tr[last()]/td[2]");

	public By editChequeDetails = By.xpath("//input[@type='submit' and contains(@value,'Edit Cheque Details')]");
	public By saveChequeDetails = By.xpath("//input[@type='submit' and contains(@value,'Save Cheque Details')]");
	public By editChequeDetails_PayeeTitle = By.id("transEnquiryform:amtrefundform:addInputPayeeTitle_acr");
	public By editChequeDetails_PayeeName = By.id("transEnquiryform:amtrefundform:addInputPayeeSurName_acr");
	public By editChequeDetails_AddressLine1 = By.xpath("//*[@id='transEnquiryform:id_rfcheque_address_body']/table[2]/tbody/tr[1]/td[2]/input");
	public By editChequeDetails_AddressLine2 = By.xpath("//*[@id='transEnquiryform:id_rfcheque_address_body']/table[2]/tbody/tr[2]/td[2]/input");
	public By editChequeDetails_AddressLine3 = By.xpath("//*[@id='transEnquiryform:id_rfcheque_address_body']/table[2]/tbody/tr[3]/td[2]/input");
	public By editChequeDetails_AddressLine4 = By.xpath("//*[@id='transEnquiryform:id_rfcheque_address_body']/table[2]/tbody/tr[4]/td[2]/input");
	public By editChequeDetails_AddressLine5 = By.xpath("//*[@id='transEnquiryform:id_rfcheque_address_body']/table[2]/tbody/tr[5]/td[2]/input");
	public By editChequeDetails_AddressLine6 = By.xpath("//*[@id='transEnquiryform:id_rfcheque_address_body']/table[2]/tbody/tr[6]/td[2]/input");	
	public By editChequeDetails_Country = By.name("//*[@id='transEnquiryform:id_rfcheque_address_body']/table[2]/tbody/tr[7]/td[2]/select");			
	public By editChequeDetails_PostCode = By.xpath("//*[@id='transEnquiryform:id_rfcheque_address_body']/table[2]/tbody/tr[8]/td[2]/input");

	public By chequeDetailsVerifcation_Name = By.xpath("//*[@id='transEnquiryform:id_rfcheque_address_body']/table[1]/tbody/tr/td[2]");
	public By chequeDetailsVerifcation_AddressLine1 = By.xpath("//*[@id='transEnquiryform:id_rfcheque_address_body']/table[2]/tbody/tr[1]/td[2]");
	public By chequeDetailsVerifcation_AddressLine2 = By.xpath("//*[@id='transEnquiryform:id_rfcheque_address_body']/table[2]/tbody/tr[2]/td[2]");
	public By chequeDetailsVerifcation_AddressLine3 = By.xpath("//*[@id='transEnquiryform:id_rfcheque_address_body']/table[2]/tbody/tr[3]/td[2]");
	public By chequeDetailsVerifcation_AddressLine4 = By.xpath("//*[@id='transEnquiryform:id_rfcheque_address_body']/table[2]/tbody/tr[4]/td[2]");
	public By chequeDetailsVerifcation_AddressLine5 = By.xpath("//*[@id='transEnquiryform:id_rfcheque_address_body']/table[2]/tbody/tr[5]/td[2]");
	public By chequeDetailsVerifcation_AddressLine6 = By.xpath("//*[@id='transEnquiryform:id_rfcheque_address_body']/table[2]/tbody/tr[6]/td[2]");
	public By chequeDetailsVerifcation_PostCode = By.xpath("//*[@id='transEnquiryform:id_rfcheque_address_body']/table[2]/tbody/tr[8]/td[2]");
	
	// Customer Events
	public By customerEvents = By.linkText("Customer Events");
	public By customerEvents_AllEventsTab = By.xpath("//*[@id='customerEventsform:id_allEventsTab_lbl' and text()='All Events']");
	public By customerEvents_IssuesTab = By.xpath("//*[@id='customerEventsform:j_id181_lbl' and text()='Issues']");
	public By customerEvents_LettersTab = By.xpath("//*[@id='customerEventsform:id_letterEventsTab_lbl' and text()='Letters']");
	public By customerEvents_CollectionsTab = By.xpath("//*[@id='customerEventsform:j_id254_lbl' and text()='Collections']");
	public By customerEvents_ProductsTab = By.xpath("//*[@id='customerEventsform:j_id281_lbl' and text()='Products']");
	
	// Customer Events verification
	public By allIssues_FutureEventsTable = By.xpath("//*[@id='customerEventsform:futureEventsTable']/thead/tr[1]/th[text()='Future Events']");
	public By allIssues_FutureEventsTableVerification = By.xpath("//table[@id='customerEventsform:futureEventsTable']/thead/tr[th[1]/div/span[text()='Date'] and th[2]/div[text()='Brand'] and th[3]/div[text()='Amount'] and th[4]/div[text()='Deliverable'] and th[5]/div[text()='Status']  and th[6]/div/span[text()='Event Type']  and th[7]/div/span[text()='Destination']]");
	public By allIssues_ReceivedEventsVerification = By.xpath("//table[@id='customerEventsform:receivedEventsTable']/thead/tr[th[1]/div/span[text()='Date'] and th[2]/div[text()='Brand'] and th[3]/div[text()='Amount'] and th[4]/div[text()='Deliverable'] and th[5]/div[text()='Status']  and th[6]/div/span[text()='Event Type']  and th[7]/div/span[text()='Destination']]");
	public By allIssues_ReceivedEventsTable = By.xpath("//*[@id='customerEventsform:receivedEventsTable']/thead/tr[1]/th[text()='Received Events']");
	
	public By issues_FutureEventsTable = By.xpath("//*[@id='customerEventsform:futureIssueEventsTable']/thead/tr[1]/th[text()='Future Events']");
	public By issues_FutureEventsTableVerification = By.xpath("//table[contains(@id,'customerEventsform:futureIssueEventsTable')]/thead/tr[th[1]/div/span[text()='Date'] and th[2]/div[text()='Brand'] and th[3]/div[text()='Issue'] and th[4]/div[text()='Type'] and th[5]/div[text()='Contract']]");
	public By letters_FutureEventsTable = By.xpath("//*[@id='customerEventsform:futureLetterEventsTable']/thead/tr[1]/th[text()='Future Events']");
	public By letters_FutureEventsTableVerification = By.xpath("//table[contains(@id,'customerEventsform:futureLetterEventsTable')]/thead/tr[th[1]/div[text()='Description'] and th[2]/div[text()='Date'] and th[3]/div[text()='Contract'] and th[4]/div[text()='Destination']]");
	public By letters_ReceivedEventsTable = By.xpath("//*[@id='customerEventsform:receivedLetterEventsTable']/thead/tr[1]/th[text()='Received Events']");
	public By letters_ReceivedEventsTableVerification = By.xpath("//*[contains(@id,'customerEventsform:receivedLetterEventsTable')]/thead/tr[th[1]/div[text()='Description'] and th[2]/div[text()='Date'] and th[3]/div[text()='Contract'] and th[4]/div[text()='Destination']]");
	
	public By collections_ReceivedEventsTable = By.xpath("//*[@id='customerEventsform:receivedCollectionsEventsTable']/thead/tr[1]/th[text()='Received Events']");
	public By collections_ReceivedEventsTableVerification = By.xpath("//*[@id='customerEventsform:receivedCollectionsEventsTable']/thead/tr[th[1]/div/span[text()='Date'] and th[2]/div[text()='Amount'] and th[3]/div[text()='Contract'] and th[4]/div[text()='Status']]");
}
