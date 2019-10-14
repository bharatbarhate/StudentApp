package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.crm.qa.base.TestBase;

public class LoginPage extends TestBase {
	// Page Factory or OR
	@FindBy(xpath = "//div[@class='ui stacked segment']//input[@name='email']")
	WebElement username;

	@FindBy(xpath = "//div[@class='ui stacked segment']//input[@name='password']")
	WebElement password;

	@FindBy(xpath = "//div[@class='ui stacked segment']//*[text()='Login' and contains(@class,'submit')]")
	WebElement buttonLogin;

	@FindBy(xpath = "//link[@rel='shortcut icon']")
	WebElement imgCRM;

	// Create constructor of page class and call initElements method from
	// PageFactory class and initiaize page objects
	public LoginPage() {
		// first parameter driver here is initializing all webElemtnts from this class
		PageFactory.initElements(driver, this); // initialize elements (driver instance from base class, current class
												// reference)
	}

	// Actions
	public String validateLoginPageTitle() {
		return driver.getTitle();
	}

	public boolean validateCRMImage() {
		return imgCRM.isEnabled();
	}
	
	/* Page Chaining :: Below login method is redirecting user on Home Page. Hence return type of this method is Home page class object  */
	public HomePage login(String un, String pwd) {
		username.sendKeys(un);
		password.sendKeys(pwd);
		buttonLogin.click();
		
		return new HomePage();
	}
}
