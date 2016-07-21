package com.testhouse.ObjectRepository;

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
	public By manageMerchandise = By.id("iconid_merchandiseLink");
	
	public By manageMerchandise_Client = By.id("id_manageMerchandiseForm:client");
	public By manageMerchandise_Brand = By.id("id_manageMerchandiseForm:id_merchandiseBrandMenu");
	public By newMerchandise = By.id("id_manageMerchandiseForm:id_newMerchandiseBtn");
	public By manageMerchandise_SearchBox = By.name("id_manageMerchandiseForm:j_id65");
	public By manageMerchandise_SearchButton = By.id("id_manageMerchandiseForm:id_searchMerchandiseBtn");
	
	public By verifyMerchandiseName = By.xpath("//*[@id='id_manageMerchandiseForm:id_merchandiseList']/tbody/tr[last()]/td[2]");
	public By verifyMerchandiseDate = By.xpath("//*[@id='id_manageMerchandiseForm:id_merchandiseList']/tbody/tr[last()]/td[1]");
	public By editLink = By.xpath("//*[@id='id_manageMerchandiseForm:id_merchandiseList']/tbody/tr[last()]/td[3]/a[text()='Edit']");
	public By viewLink = By.xpath("//*[@id='id_manageMerchandiseForm:id_merchandiseList']/tbody/tr[last()]/td[3]/a[text()='View']");
	
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
	
	public By merchandise_VerifySerialNumber = By.xpath("//*[@id='j_id53:j_id55_body']/table/tbody/tr[1]/td[2]/label");
	public By merchandise_verifyDescription = By.xpath("//*[@id='j_id53:j_id55_body']/table/tbody/tr[2]/td[2]/label");
	public By merchandise_DovetailRef = By.xpath("//*[@id='j_id53:j_id55_body']/table/tbody/tr[3]/td[2]/label");
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
	
	

}
