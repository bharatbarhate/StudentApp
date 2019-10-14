package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class HomePage extends TestBase {

	@FindBy(xpath = "//*[@id='top-header-menu']//div[contains(@class,'header')]")
	public WebElement logoFreeCRM;

	@FindBy(xpath = "//*[@id='top-header-menu']//span[@class='user-display']")
	public WebElement labeluserName;

	@FindBy(xpath = "//div[@class='ui fluid card']//div[contains(text(),'Deals Summary')]")
	public WebElement dealsSummary;

	@FindBy(xpath = "//div[@class='ui fluid card']//div[contains(@class,'ui custom-card-container')]//div[@class='value']")
	public WebElement totalNoOfDeals;

	@FindBy(xpath = "//div[@class='content card-header']")
	public WebElement headerContactsactivitystream;

	@FindBy(xpath = "//div[@class='content']//*[contains(text(),'Deals') and not (contains(text(),'Summary'))]")
	public WebElement headerDeals;

	@FindBy(xpath = "//div[@class='ui fluid card']//span[contains(text(),'Call Queue') and not (contains(text(),'Empty'))]")
	public WebElement headerCallQueue;

	@FindBy(xpath = "//div[@id='main-nav']//*[contains(text(),'Contacts')]")
	public WebElement menuContacts;
	
	@FindBy(xpath = "//div[@id='main-nav']//*[contains(text(),'Deals')]")
	public WebElement menuDeals;
	
	@FindBy(xpath = "//div[@id='main-nav']//*[contains(text(),'Tasks')]")
	public WebElement menuTasks;

	@FindBy(xpath = "//div[@id='main-nav']//*[contains(text(),'Forms')]")
	public WebElement menuForms;
	
	/*********** This Section has all Links on Home Page	 **************/
	
	@FindBy(xpath="//div[@id='main-nav']//span[contains(text(),'Forms')]")
	public WebElement linkForm;

	@FindBy(xpath="//div[@id='main-nav']//span[contains(text(),'Forms')]")
	public WebElement linkCompanies;

	@FindBy(xpath="//div[@id='main-nav']//span[contains(text(),'Forms')]")
	public WebElement linkDeals;

	@FindBy(xpath="//div[@id='main-nav']//span[contains(text(),'Forms')]")
	public WebElement linkContacts;

	
	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	public String verifyHomePageTitle() {
		return driver.getTitle();
	}

	public ContactsPage clickonContactsLink() {
		menuContacts.click();
		return new ContactsPage();
	}
	public DealsPage clickonDealsLink() {
		menuDeals.click();
		return new DealsPage();
	}
	public TasksPage clickonTasksLink() {
		menuTasks.click();
		return new TasksPage();
	}
	public FormsPage clickonFormsLink() {
		menuForms.click();
		return new FormsPage();
	}


}
