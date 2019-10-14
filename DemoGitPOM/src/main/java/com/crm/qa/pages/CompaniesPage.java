package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.crm.qa.base.TestBase;

public class CompaniesPage extends TestBase{

	@FindBy(xpath="//div[@id='dashboard-toolbar']//div[contains(@class,'header')]")
	public WebElement headerComapniesPage;
	
	public CompaniesPage linkComapniesClick() {
		headerComapniesPage.click();
		return new CompaniesPage();
	}
	
	public String verifyCompaniesPageTitle() {
		return driver.getTitle();
	}
}	
