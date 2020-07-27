package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.TestBase;

public class LoginPage extends TestBase
{

	@FindBy(xpath ="//h3[text()='Sign In']")
	WebElement labelSignIn;
	
	@FindBy(xpath ="//input[@name='loginEmail']")
	WebElement textEmailAddress;

	@FindBy(xpath ="//input[@name='loginPassword']")
	WebElement textPassword;

	@FindBy(xpath ="//input[@value='SIGN IN']")
	WebElement buttonSignIn;

	// Initializing the Page Objects:

	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	public String validateloginPageTitle()
	{
		return driver.getTitle();	
	}
	
	public boolean validateSignInLabel()
	{
		return labelSignIn.isDisplayed();
	}
	public HomePage clickSignIn(String username, String password)
	{
		textEmailAddress.sendKeys(username);
		textPassword.sendKeys(password);
		
		buttonSignIn.click();
		return new HomePage();
	}
}
