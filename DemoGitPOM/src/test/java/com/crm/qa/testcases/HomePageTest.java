package com.crm.qa.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.CompaniesPage;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.DealsPage;
import com.crm.qa.pages.FormsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.pages.TasksPage;
import com.crm.qa.util.TestUtil;

public class HomePageTest extends TestBase{
	LoginPage loginPage;
	HomePage homePage;
	CompaniesPage companiesPage;
	ContactsPage contactsPage;
	DealsPage dealsPage;
	FormsPage formPage;
	TasksPage tasksPage;

	TestUtil tesUtil;

	public HomePageTest() {
		super();
	}

	@BeforeMethod
	public void setUP()
	{
		initilization();
		loginPage = new LoginPage();
		homePage =  new HomePage();
		companiesPage = new CompaniesPage();
		contactsPage= new ContactsPage();
		dealsPage = new DealsPage();
		formPage = new FormsPage();
		tasksPage = new TasksPage();
		tesUtil = new TestUtil();
		homePage = loginPage.login(prop.getProperty("USERNAME"), prop.getProperty("PASSWORD"));
	}

	@Test (priority = 1)
	public void VerifyHomePageLogo() {
		Assert.assertTrue(homePage.logoFreeCRM.isDisplayed(), "Free CRM Logo is not available");
	}

	@Test (priority = 1)
	public void VerifyHomePageUserName() {
		Assert.assertEquals(homePage.labeluserName.getText(), "BHARAT B", "Fail : "+homePage.labeluserName.getText()+" This User is not avaiable on Home Page");
	}

	@Test (priority = 3)
	public void VerifyHomePageTitle() {
		Assert.assertEquals(homePage.verifyHomePageTitle(), "Cogmento CRM","FAIL:HomePageTile is not Matching, Actual Homepage title is "+homePage.verifyHomePageTitle());
	}

	@Test (priority = 4)
	public void VerifyHomePageContents() {

		HashMap<String, String> ExepctedHomePageContents = new HashMap<>();
		ExepctedHomePageContents.put("Deals Summary", homePage.dealsSummary.getText());
		ExepctedHomePageContents.put("Contacts activity stream", homePage.headerContactsactivitystream.getText());
		ExepctedHomePageContents.put("Deals", homePage.headerDeals.getText());
		ExepctedHomePageContents.put("Call Queue", homePage.headerCallQueue.getText());		

		//get it from Excel Sheet
		Assert.assertEquals(ExepctedHomePageContents.get("Deals Summary"), "Deals Summary","FAIL : Deals Summary is not available on HomePage");
		Assert.assertEquals(ExepctedHomePageContents.get("Contacts activity stream"), "Contacts activity stream","FAIL : Contacts activity stream is not available on HomePage");
		Assert.assertEquals(ExepctedHomePageContents.get("Deals"), "Deals","FAIL : Deals is not available on HomePage");
		Assert.assertEquals(ExepctedHomePageContents.get("Call Queue"), "Call Queue","FAIL : Call Queue is not available on HomePage");
	}

	@Test (priority = 5)
	public void VerifyLinkContactsClick() {
		contactsPage = homePage.clickonContactsLink();
		Assert.assertEquals(contactsPage.verifyContactsPageTitle(), "Cogmento CRM","FAIL : ContactsPage Title is not Matching, Actual Homepage title is "+contactsPage.verifyContactsPageTitle());
	}

	@Test (priority = 6)
	public void VerifyLinkFormsClick() {
		formPage = homePage.clickonFormsLink();
		Assert.assertEquals(formPage.verifyFormsPageTitle(), "Cogmento CRM","FAIL : FormsPage Title is not Matching, Actual Homepage title is "+contactsPage.verifyContactsPageTitle());
	}

	@Test (priority = 7)
	public void VerifyLinkDealsClick() {
		dealsPage = homePage.clickonDealsLink();
		Assert.assertEquals(contactsPage.verifyContactsPageTitle(), "Cogmento CRM","FAIL : DealsPage Title is not Matching, Actual Homepage title is "+contactsPage.verifyContactsPageTitle());
	}
	@Test (priority = 8)
	public void VerifyLinkTasksClick() {
		tasksPage = homePage.clickonTasksLink();
		Assert.assertEquals(contactsPage.verifyContactsPageTitle(), "Cogmento CRM","FAIL : TasksPage Title is not Matching, Actual Homepage title is "+contactsPage.verifyContactsPageTitle());
	}

	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}

}
