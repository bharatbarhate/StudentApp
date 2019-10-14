package com.qa.practice;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HDFCBankTest {
	WebDriver driver;
	String baseUrl;

	@BeforeMethod
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", ".\\src\\test\\resources\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		baseUrl = "https://www.hdfcbank.com/";
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(baseUrl);
	}

	@Test
	public void test1() throws InterruptedException {
		System.out.println("@Test1");
		WebDriverWait wait = new WebDriverWait(driver, 10);
		// WebElement popBanner =
		// driver.findElement(By.xpath("//img[contains(@class,'popupbanner
		// at-element-click-tracking')]"));

		try {
			WebElement popBanner = driver.findElement(By.xpath("//img[contains(@class,'popupbanner')]"));
			wait.until(ExpectedConditions.visibilityOf(popBanner)).isDisplayed();
			// driver.findElement(By.xpath("//img[@class='popupbanner
			// at-element-click-tracking']/../following-sibling::img[@class='popupCloseButton']")).click();
			driver.findElement(By.xpath("//img[@class='popupCloseButton']")).click();
			System.out.println("Popup 1 closed");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			// System.out.println(e);
			// e.getCause();
		}

		String firstwindowhandle = driver.getWindowHandle();
		System.out.println("Initial  Window Title :: " + driver.getTitle());
		System.out.println(firstwindowhandle);
		driver.findElement(By.id("loginsubmit")).click();
		Set allwindows = driver.getWindowHandles();

		if (allwindows.size() > 1) {
			Iterator<String> itr = allwindows.iterator();
			while (itr.hasNext()) {
				String firstwindow = itr.next();
				String secondwindow = itr.next();
				System.out.println(firstwindow + ":: 2nd " + secondwindow);
				if (firstwindow != secondwindow) {
					driver.switchTo().window(secondwindow);
					System.out.println("Second Window Title :: " + driver.getTitle());
					WebElement contNetBanking = driver
							.findElement(By.xpath("(//a[text()='Continue to NetBanking'])[2]"));
					wait.until(ExpectedConditions.visibilityOf(contNetBanking));
					contNetBanking.click();
				}
				allwindows.remove(secondwindow);
				// driver.switchTo().window(firstwindow).close();
			}
		}

		for (String allinsidewindowshandles : driver.getWindowHandles()) {

			System.out.println("All New ewindowshandles :: " + allinsidewindowshandles.toString());
			if (driver.switchTo().window(allinsidewindowshandles).getTitle()
					.equals("Welcome to HDFC Bank NetBanking")) {
				driver.switchTo().window(allinsidewindowshandles);
				// Welcome to HDFC Bank NetBanking
				System.out.println("Latest Title :: " + driver.getTitle());
				driver.switchTo().frame("login_page");
				// System.out.println(driver.findElement(By.xpath("//td[contains(text(),'User ID
				// / Customer ID')]")).getText());
				driver.findElement(By.xpath("//input[@name='fldLoginUserId']")).sendKeys("12345678");
				driver.findElement(By.xpath("//img[@alt='continue']")).click();
				Thread.sleep(5000);
				driver.findElement(By.xpath("//input[@name='fldPassword']")).sendKeys("Test@12345");

				if (driver.findElement(By.xpath("//img[@alt='Login']")).isDisplayed()) {
					System.out.println("Test Case Pass");
				} else {
					System.out.println("Test Case Failed");
				}
				driver.switchTo().defaultContent();
				System.out.println("Title post Default content :: " + driver.getTitle());
			}
			driver.switchTo().window(firstwindowhandle);
			System.out.println("Title post swicthing on to First Handle :: " + driver.getTitle());

		}
	}

	public void addName() throws InterruptedException {
		driver.findElement(By.name("firstname")).sendKeys("Bharat");
		driver.findElement(By.name("lastname")).sendKeys("Barhate");

		if (!driver.findElement(By.xpath("//input[@value='Male' and @type='radio']")).isSelected()) {
			driver.findElement(By.xpath("//input[@value='Male' and @type='radio']")).click();
		} else {
			System.out.println("Male is already selected");
		}

		if (!driver.findElement(By.xpath("//input[@value='6' and @type='radio']")).isSelected()) {
			driver.findElement(By.xpath("//input[@value='6' and @type='radio']")).click();
		} else {
			System.out.println("Years of Exp is already selected");
		}

		if (driver.findElement(By.id("datepicker")).isDisplayed()) {

			DateTimeFormatter dtformat = DateTimeFormatter.ofPattern("MM/dd/yyyy");
			LocalDateTime localtDate = LocalDateTime.now();

			// SimpleDateFormat formDate = new SimpleDateFormat("MM/dd/yyyy");
			// String strDate = formDate.format(System.currentTimeMillis()); // option 1
			// String tDate = formDate.format(new Date()); // option 2

			String tDate = dtformat.format(localtDate);
			System.out.println(tDate);
			driver.findElement(By.id("datepicker")).sendKeys(tDate);
		}

		if (!driver.findElement(By.xpath("//input[@type='checkbox' and @value='Selenium Webdriver']")).isSelected()) {
			driver.findElement(By.xpath("//input[@type='checkbox' and @value='Selenium Webdriver']")).click();
		} else {
			System.out.println("Selenium Webdriver is already selected");
		}

		WebElement cmbcontinent = driver.findElement(By.id("continents"));
		Select selectcontinent = new Select(cmbcontinent);
		selectcontinent.selectByVisibleText("North America");

		WebElement cmbselCommands = driver.findElement(By.id("selenium_commands"));
		Select selectselCommands = new Select(cmbselCommands);
		// selectselCommands.selectByVisibleText("WebElement Commands");
		List<WebElement> lstselCommands = selectselCommands.getOptions();
		for (int i = 0; i < lstselCommands.size(); i++) {
			System.out.println(lstselCommands.get(i).getText());
			String sValue = selectselCommands.getOptions().get(i).getText();

			selectselCommands.selectByIndex(i);
		}
		driver.findElement(By.xpath("//button[text()='Button' and @id='submit']")).click();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
