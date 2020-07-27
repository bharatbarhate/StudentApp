package com.qa.testcases;


import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.pages.LoginPage;
import com.qa.util.TestUtil;

public class LoginPageTest extends TestBase {

	LoginPage loginpage;
	TestUtil testutil;


	public LoginPageTest() {
		// TODO Auto-generated constructor stub
		super();
	}

	@BeforeMethod
	public void setUp() {
		initilalization();
		loginpage = new LoginPage();
		testutil = new TestUtil();
	}
	@Test (priority =1, enabled=true)
	public void validateLoginPageTitle() {
		String title = loginpage.validateloginPageTitle();
		Assert.assertEquals(title, "Account");
	}
	@AfterClass
	public void tearDown() throws Exception {

		driver.close();
	}
}
