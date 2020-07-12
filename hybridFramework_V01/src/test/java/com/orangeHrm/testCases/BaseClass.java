package com.orangeHrm.testCases;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.orangeHrm.utility.ConfigDataProvider;
import com.orangeHrm.utility.ExcelDataProvider;

public class BaseClass {

	public static WebDriver driver = null;
	public ConfigDataProvider configData;
	public ExcelDataProvider excelDataProvider;
	
	@BeforeSuite
	public void init(){		
		configData = new ConfigDataProvider();
		excelDataProvider=new ExcelDataProvider("login");
	}

	
	@BeforeClass
	public void setup() {
		
		if(configData.getBrowser().equals("chrome")){
			System.setProperty("webdriver.chrome.driver", configData.getChromPath());
			driver = new ChromeDriver();
		}
		else if(configData.getBrowser().equals("ie")){
			System.setProperty("webdriver.ie.driver", configData.getIEPath());
			driver = new InternetExplorerDriver();
		}
		else if(configData.getBrowser().equals("firefox")){
			System.setProperty("webdriver.gecko.driver", configData.getFirefoxPath());
			driver = new FirefoxDriver();
		}
		else{
			
			System.out.println("browser driver is not matching please check once again");
		}
		driver.get(configData.getAppUrl());		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@AfterClass
	public void teardown() {
		driver.quit();
	}

}
