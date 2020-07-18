package com.orangeHrm.testCases;

import java.io.File;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.orangeHrm.utility.ConfigDataProvider;
import com.orangeHrm.utility.ExcelDataProvider;

public class BaseClass {

	public static WebDriver driver = null;
	public ConfigDataProvider configData;
	public ExcelDataProvider excelDataProvider;

	public ExtentHtmlReporter htmlReport;
	public ExtentReports extent;
	public ExtentTest test_Logger;

	@BeforeSuite
	public void init() {
		configData = new ConfigDataProvider();
		excelDataProvider = new ExcelDataProvider("login");
	}

	@BeforeTest
	public void setExtent() {

		String datetime =new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		htmlReport = new ExtentHtmlReporter(System.getProperty("user.dir") + "/Reports/"+datetime+".html");

		htmlReport.config().setDocumentTitle("Automation Report");// title of
																	// the
																	// report
		htmlReport.config().setReportName("Smoke Test Report"); // Name of the
																// report
		htmlReport.config().setTheme(Theme.STANDARD);

		extent = new ExtentReports();

		extent.attachReporter(htmlReport);

		extent.setSystemInfo("HostName", "LocalHost");
		extent.setSystemInfo("OS", "Windows10");
		extent.setSystemInfo("Tester Name", "Vasant");
		extent.setSystemInfo("Browser Name", "Chrome");
	}

	@AfterTest
	public void endReport() {
		extent.flush();
	}

	public static String screenCapture(WebDriver driver, String screenshotName) throws Exception {

		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

		TakesScreenshot ts = (TakesScreenshot) driver;
		File sourceFile = ts.getScreenshotAs(OutputType.FILE);

		String destination = System.getProperty("user.dir") + "/Screenshots/" + screenshotName + dateName + ".png";

		File fileDestination = new File(destination);

		FileUtils.copyFile(sourceFile, fileDestination);

		return destination;

	}

	@AfterMethod
	public void extentStatus(ITestResult result) throws Exception {

		if (result.getStatus() == ITestResult.FAILURE) {
			test_Logger.log(Status.FAIL, "TEST CASE FAILED IS " + result.getName());
			test_Logger.log(Status.FAIL, "TEST CASE FAILED IS " + result.getThrowable());

			String screenshotsPath = screenCapture(driver, result.getName());
			test_Logger.addScreenCaptureFromPath(screenshotsPath);
		}

		else if (result.getStatus() == ITestResult.SKIP) {
			test_Logger.log(Status.SKIP, "TEST CASSE SKIPPED IS " + result.getName());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test_Logger.log(Status.PASS, "TEST CASSE SUCCESS IS " + result.getName());
		}
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
