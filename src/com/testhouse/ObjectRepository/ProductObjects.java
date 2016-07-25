package com.testhouse.ObjectRepository;

import org.joda.time.DateTime;
import org.openqa.selenium.By;

import com.testhouse.Functions.GeneralFunctions;

/**
 * Class file to store all the objects which are relavent for the application
 * @version 1.0
 * @author Testhouse
 *
 */
public class ProductObjects extends GeneralFunctions 
{
	public By productLink = By.linkText("Product");
	
	// Manage Merchandise
	public By manageMerchandise = By.id("iconid_merchandiseLink");
	
	public By manageMerchandise_Client = By.id("id_manageMerchandiseForm:client");
	public By manageMerchandise_Brand = By.id("id_manageMerchandiseForm:id_merchandiseBrandMenu");
	public By newMerchandise = By.id("id_manageMerchandiseForm:id_newMerchandiseBtn");
	public By manageMerchandise_SearchBox = By.name("id_manageMerchandiseForm:j_id65");
	public By manageMerchandise_SearchButton = By.id("id_manageMerchandiseForm:id_searchMerchandiseBtn");
	
	public By verifyMerchandiseName(String name) 
	{
		return By.xpath("//*[@id='id_manageMerchandiseForm:id_merchandiseList']/tbody/tr[td[1][text()='"+DateTime.now().toString("dd/MM/yyyy")+"'] and td[2][text()='"+name+"']]");
	}
	public By verifyMerchandiseDate = By.xpath("//*[@id='id_manageMerchandiseForm:id_merchandiseList']/tbody/tr[last()]/td[1]");
	public By editLink(String name)
	{
		return By.xpath("//*[@id='id_manageMerchandiseForm:id_merchandiseList']/tbody/tr/td[2][text()='"+name+"']/following-sibling::td/a[text()='Edit']");
	
	}
	public By viewLink(String name)
	{
		return By.xpath("//*[@id='id_manageMerchandiseForm:id_merchandiseList']/tbody/tr/td[2][text()='"+name+"']/following-sibling::td/a[text()='View']");
	}
	
	public By merchandiseDescription = By.id("j_id53:j_id57:id_merchandiseName");
	public By merchandiseSerialNumber = By.id("j_id53:id_merchandiseSerialNo");
	public By merchandiseTaxCode = By.id("j_id53:id_merchandiseTaxCode");
	public By merchandiseLabelOutputType = By.id("j_id53:id_outputType");
	public By merchandiseAvailableFrom = By.id("j_id53:id_merchandiseFromDateInputDate");
	public By merchandiseAvailableTo = By.id("j_id53:id_merchandiseToDateInputDate");
	public By merchandiseExternalRef = By.id("j_id53:id_externalReference");
	
	public By stockDeliveryPercentageForBrand(String brand)
	{
		By stockDeliveryPercentageForBrand = By.xpath("//td[text()='"+brand+"']/following-sibling::td/input[@type='text']");
		return stockDeliveryPercentageForBrand;
	}
	
	public By stockDepletionOptionForBrand(String brand)
	{
		By stockDepletionOptionForBrand = By.xpath("//td[text()='"+brand+"']/following-sibling::td/select");
		return stockDepletionOptionForBrand;
	}
	
	public By brandAssociationsButton = By.id("j_id53:id_brandBtn");
	public By brandAssociationsTable = By.xpath("//*[@id='j_id53:j_id79_header' and text()='Brand Associations']");
	
	public By merchandise_SaveButton = By.id("j_id53:id_saveBtn");
	
	public By merchandise_VerifySerialNumber = By.xpath("//*[@id='j_id53:j_id55_body']/table/tbody/tr[3]/td[2]/label");
	public By merchandise_verifyDescription = By.xpath("//*[@id='j_id53:j_id55_body']/table/tbody/tr[2]/td[2]/label");
	public By merchandise_DovetailRef = By.xpath("//*[@id='j_id53:j_id55_body']/table/tbody/tr[1]/td[2]/label");
	public By merchandise_VerifyTaxCode = By.xpath("//*[@id='j_id53:j_id55_body']/table/tbody/tr[4]/td[2]/label");
	public By merchandise_VerifyExternalReference = By.xpath("//*[@id='j_id53:j_id55_body']/table/tbody/tr[5]/td[2]/label");
	public By merchandise_VerifyAvailableFrom = By.xpath("//*[@id='j_id53:j_id55_body']/table/tbody/tr[6]/td[2]/span");
	public By merchandise_VerifyAvailableTo = By.xpath("//*[@id='j_id53:j_id55_body']/table/tbody/tr[7]/td[2]/span");
	
	public By merchandise_VerifyBrandAssociations_Brand(String brand)
	{
		By merchandise_VerifyBrandAssociations_Brand = By.xpath("//*[@id='j_id53:id_brandAssociationsTable:0:j_id72' and text()='"+brand+"']");
		return merchandise_VerifyBrandAssociations_Brand;
	}

	public By merchandise_VerifyBrandAssociations_StockDeliveryPercentage(String brand) 
	{
		By merchandise_VerifyBrandAssociations_StockDeliveryPercentage = By.xpath("//*[@id='j_id53:id_brandAssociationsTable:0:j_id72' and text()='"+brand+"']/following-sibling::td");
		return merchandise_VerifyBrandAssociations_StockDeliveryPercentage;
	}
	
	// Manage Client
	public By manageClients = By.id("iconid_clientsLink");
	
	public By manageClients_New = By.id("id_clientForm:id_newClientButton");
	public By manageClients_New_Name = By.id("id_clientForm:setClientNameDecorator:setClientName");
	public By manageClients_New_SagaReference = By.id("id_clientForm:setClientSageReference");
	public By manageClients_New_Tolerance = By.id("id_clientForm:toleranceDecorator:tolerance");
	public By manageClients_New_ProductAutoGenPasswordTypeComboxBox = By.id("id_clientForm:setAutoGenPasswordTypescomboboxButton");
	public By manageClients_New_SelectPRoductAutoGenPasswordOption(String option)
	{
		return By.xpath("//*[@id='id_clientForm:setAutoGenPasswordTypeslist']/span[text()='"+option+"']");
	}
	public By manageClients_New_CaptureClientReason = By.id("id_clientForm:id_captureContactReason");
	public By manageClients_SaveButton = By.id("id_clientForm:id_saveClientButton");
	
	public By manageClients_SearchField = By.id("id_clientForm:clientName");
	public By manageClients_SearchButton = By.id("id_clientForm:j_id57");
	
	public By verifyCreatedClient(String clientName)
	{
		return By.xpath("//td[text()='"+clientName+"']");
	}
	
	public By manageClient_ReferenceNumber(String clientName)
	{
		return By.xpath("//td[text()='"+clientName+"']/following-sibling::td[1]");
	}
	
	public By manageClient_editLink(String clientName)
	{
		return By.xpath("//td[text()='"+clientName+"']/following-sibling::td[3]/input[@value='Edit']");
	}
	
	public By manageClient_ViewLink(String clientName)
	{
		return By.xpath("//td[text()='"+clientName+"']/following-sibling::td[3]/input[@value='View']");
	}
	
	public By manageClient_Parameters = By.id("id_clientForm:id_overrideParamsForClient");
	
	public By manageClient_Parameters_SearchAClientOrBrand = By.xpath("//label[contains(text(),'Search a Client or Brand')]/preceding-sibling::input");
	public By manageClient_Parameters_SearchAPromotion = By.xpath("//label[contains(text(),'Search a Promotion')]/preceding-sibling::input");
	
	public By manageClient_Parameters_Client = By.id("j_id55:client");
	public By manageClient_Parameters_BrandSelect(String brand)
	{
		By brandSelect = By.linkText(brand);
		return brandSelect;
	}
	
	public By manageClient_Parameters_ShowButton = By.id("id_showButtonForm:id_showBut");
	public By manageClient_Parameters_SelectGroup = By.id("id_selectedGroupForm:selectedGroup");
	public By manageClient_Parameters_EditButton = By.id("//td/input[@value='Edit']");
	
	public By manageClient_Parameters_SelectContactType = By.id("id_existingPropertyForm:contactType");
	
	public By manageClient_Parameters_DPPReference(String reference)
	{
		return By.xpath("//table[contains(@id,'id_existingPropertyForm:id_dppByContactTypeTable')]/tbody/tr/td[2][text()='"+reference+"']");
	}
	
	public By manageClient_Parameters_DPP_QuestionType(String reference)
	{
		return By.xpath("//table[contains(@id,'id_existingPropertyForm:id_dppByContactTypeTable')]/tbody/tr/td[2][text()='"+reference+"']/following-sibling::td/select");
	}
	
	public By manageClient_Parameters_DPP_Available(String reference)
	{
		return By.xpath("//table[contains(@id,'id_existingPropertyForm:id_dppByContactTypeTable')]/tbody/tr/td[2][text()='"+reference+"']/preceding-sibling::td/input");
	}
	
	public By manageClient_Parameters_SaveButton = By.xpath("//input[@value='Save']");
	
	public By manageClient_Parameters_Demographics_ExternalReference(String extReference)
	{
		return By.xpath("//*[contains(@id,'j_id164:id_demogrphicsTble')]/tbody/tr/td[3][text()='"+extReference+"']");
	}
	
	public By manageClient_Parameters_Demographics_Available(String extReference)
	{
		return By.xpath("//*[contains(@id,'j_id164:id_demogrphicsTble')]/tbody/tr/td[3][text()='"+extReference+"']/preceding-sibling::td/input");
	}
	
	public By manageClient_Parameters_LoadingIcon = By.xpath("//*[contains(@id,'status.start')]/img");
	
	// Manage Brands
	public By manageBrandsLink = By.id("iconid_brandsLink");
	public By manageBrands_EditLink = By.id("id_manageBrandForm:brandList:0:id_editBrandButton");
	public By manageBrands_ViewLink = By.id("id_manageBrandForm:brandList:0:id_editBrandButton");
	public By manageBrands_ManageBarPricesLink = By.id("id_manageBrandForm:brandList:0:id_editBrandButton");
	public By manageBrands_NewTitle = By.id("id_manageBrandForm:id_newBrandButton");
	
	public By manageBrands_NewBundleBrand = By.id("id_manageBrandForm:id_newTitleBundleButton");
			
	public By manageBrands_NewTitle_BrandName = By.id("id_manageBrandForm:id_nameDecorator:name");
	public By manageBrands_NewTitile_BrandType = By.id("id_manageBrandForm:id_brandTypeMenu");
	public By manageBrands_NewTitle_BankAccount = By.id("id_manageBrandForm:id_bankAccountDecorator:id_brandBankAccount");
	public By manageBrands_NewTitle_Status = By.id("id_manageBrandForm:brandStatus");
	
	public By manageBrands_NewTiltle_NewContact = By.id("id_manageBrandForm:id_newClientContactDetailsButton");
	
	public By manageBrands_NewTitle_NewContact_Title = By.id("id_manageBrandForm:newTile");
	public By manageBrands_NewTitle_NewContact_FirstName = By.id("id_manageBrandForm:newContactName");
	public By manageBrands_NewTitle_NewContact_SurName = By.id("id_manageBrandForm:newContactSurname");
	public By manageBrands_NewTitle_NewContact_SaveButton = By.id("id_manageBrandForm:id_saveClientContactsButton"); 
	
	public By manageBrands_NewTitle_VerifyCreatedContact(String firstName, String lastName)
	{
		return By.xpath("//table[@id='id_manageBrandForm:newClientContactDetails']/tbody/tr[td[1][text()='"+firstName+"'] and td[2][text()='"+lastName+"']]");
	}
	
	public By manageBrands_NewTitle_AddDovetailContact = By.id("id_manageBrandForm:id_newDoveContactDetailsButton");
	
	public By manageBrands_NewTitle_AddDovetailContact_Title = By.id("id_manageBrandForm:id_newDoveTile");
	public By manageBrands_NewTitle_AddDovetailContact_FirstName = By.id("id_manageBrandForm:id_newDoveContactName");
	public By manageBrands_NewTitle_AddDovetailContact_SurName = By.id("id_manageBrandForm:id_newDoveContactSurname");
	public By manageBrands_NewTitle_AddDovetailContact_SaveContact = By.id("id_manageBrandForm:id_saveDoveContactsButton");
	
	public By manageBrands_NewTitle_VerifyCreatedDoveContact(String firstName, String lastName)
	{
		return By.xpath("//table[@id='id_manageBrandForm:id_newDoveContactDetails']/tbody/tr[td[1][text()='"+firstName+"'] and td[2][text()='"+lastName+"']]");
	}
	
}
	
	
