package com.orangeHrm.testCases;

import java.io.File;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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
	
	public static Logger logger= LogManager.getLogger(BaseClass.class);	

	@BeforeSuite
	public void init() {
		
		//logger.info("Config and excel data Provider Object is getting initialise");
		configData = new ConfigDataProvider();
		excelDataProvider = new ExcelDataProvider("login");
		//logger.info("Config and excel data Provider Object is get Initialised");
	}

	public static String screenCapture(String screenshotName) {

		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

		String destination =null;
		
		try {
			
			TakesScreenshot ts = (TakesScreenshot) driver;
			
			File sourceFile = ts.getScreenshotAs(OutputType.FILE);

			destination = System.getProperty("user.dir") + "/Screenshots/" + screenshotName + dateName + ".png";

			File fileDestination = new File(destination);

			FileUtils.copyFile(sourceFile, fileDestination);
			
			//logger.info("screenshots is captured..");
				
			
		} catch (Exception e) {
			System.out.println(e.getMessage());	
			//logger.info("screenshots is not able to captured..");
			
		} 
		return destination;
		

	}
	
	@BeforeClass
	public void setup() {

		if (configData.getBrowser().equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", configData.getChromPath());
			driver = new ChromeDriver();
		} else if (configData.getBrowser().equals("ie")) {
			System.setProperty("webdriver.ie.driver", configData.getIEPath());
			driver = new InternetExplorerDriver();
		} else if (configData.getBrowser().equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", configData.getFirefoxPath());
			driver = new FirefoxDriver();
		} else {

			System.out.println("browser driver is not matching please check once again");
		}
		driver.get(configData.getAppUrl());
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@AfterClass
	public void teardown() {
		driver.quit();
	}

}
