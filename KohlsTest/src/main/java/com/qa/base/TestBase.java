package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.qa.util.TestUtil;

public class TestBase {
	public static Properties prop;
	public static WebDriver driver;

//	public TestBase() {
//		prop = new Properties();
//		FileInputStream ip = null;
//		try {
//			ip = new FileInputStream("user.dir")+"/src/main/java/com/qa/config/config.properties");
//		} catch (FileNotFoundException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		try {
//			prop.load(ip);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	
	public TestBase(){
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("D:\\Workspace\\KohlsTest\\src\\main\\java\\com\\qa\\config\\config.properties"));
			System.out.println(ip);
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

		public static void initilalization()
		{
			String browsername = prop.getProperty("browser");
			if (browsername.equals("chrome"))
			{
				//	System.setProperty("webdriver.chrome.driver", "/KohlsTest/src/main/java/com/qa/resources/Drivers/chromedriver.exe");
				System.setProperty("webdriver.chrome.driver", "D:\\Softwares\\chromedriver_win32\\chromedriver.exe");
				driver = new ChromeDriver();
			}
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);

			driver.get(prop.getProperty("url"));
		}
	}
