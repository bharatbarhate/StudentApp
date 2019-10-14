package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.crm.qa.base.TestBase;

public class ContactsPage extends TestBase{

	@FindBy(xpath="//div[@id='dashboard-toolbar']//div[contains(@class,'header')]")
	public WebElement headerContactsPage;
	
	public String verifyContactsPageTitle() {
		return driver.getTitle();
	}
}
