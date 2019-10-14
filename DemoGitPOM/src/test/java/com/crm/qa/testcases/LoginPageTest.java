package com.crm.qa.testcases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.*;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;

import junit.framework.Assert;

public class LoginPageTest extends TestBase{
	LoginPage loginPage;
	HomePage homePage;

	public LoginPageTest() {
		super(); // initialize base class constructor and initialize properties. and then we can call initilization method
	}

	@BeforeMethod
	public void setUp() {
		initilization();
		loginPage = new LoginPage();
	}
	
	@Test (priority=1)
	public void loginPageTitleTest()
	{
		String title = loginPage.validateLoginPageTitle();
		Assert.assertEquals(driver.getTitle(), title);
	}

	@Test  (priority=2)
	public void crmLogoImageTest()
	{
		Boolean flag_Logo = loginPage.validateCRMImage();
		Assert.assertTrue("Pass: CRM logo image is available.", flag_Logo);
	}
	@Test (priority=3)
	public void verifyFreeCrmLogin()
	{
		homePage = loginPage.login(prop.getProperty("USERNAME"), prop.getProperty("PASSWORD"));
		System.out.println(driver.getTitle());
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
