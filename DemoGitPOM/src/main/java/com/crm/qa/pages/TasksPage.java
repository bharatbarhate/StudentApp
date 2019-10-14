package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.crm.qa.base.TestBase;

public class TasksPage extends TestBase {

	@FindBy(xpath="//div[@id='dashboard-toolbar']//div[contains(@class,'header')]")
	public WebElement headerTasksPage;
	
	public TasksPage linkTasksClick() {
		headerTasksPage.click();
		return new TasksPage();
	}
	
	public String verifyTasksPageTitle() {
		return driver.getTitle();
	}
}
