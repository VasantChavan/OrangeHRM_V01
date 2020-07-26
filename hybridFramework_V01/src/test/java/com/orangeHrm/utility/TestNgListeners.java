package com.orangeHrm.utility;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.orangeHrm.testCases.BaseClass;

public class TestNgListeners implements ITestListener {

	public ExtentHtmlReporter htmlReport;
	public ExtentReports extent;
	public ExtentTest test_Logger;

	@Override
	public void onStart(ITestContext context) {

		String stamTime = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

		htmlReport = new ExtentHtmlReporter(System.getProperty("user.dir") + "/Reports/" + stamTime + ".html");
		htmlReport.config().setDocumentTitle("Automation Report");																	
		htmlReport.config().setReportName("Smoke Test Report"); 
		htmlReport.config().setTheme(Theme.STANDARD);

		extent = new ExtentReports();

		extent.attachReporter(htmlReport);

		extent.setSystemInfo("HostName", "LocalHost");
		extent.setSystemInfo("OS", "Windows10");
		extent.setSystemInfo("Tester Name", "Vasant");
		extent.setSystemInfo("Browser Name", "Chrome");
	}

	@Override
	public void onFinish(ITestContext context) {
		test_Logger =extent.createTest(context.getName());
		test_Logger.info("On Test Finish flush the report  "+ context.getName());
		extent.flush();
	}

	@Override
	public void onTestStart(ITestResult result) {
		test_Logger=extent.createTest(result.getName());
		test_Logger.info("On Test Start flush the test Test Case name is "+ result.getName());
		

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test_Logger=extent.createTest(result.getName());
		test_Logger.info("On Test Success Test Case name is "+ result.getName());		
		
		if (result.getStatus() == ITestResult.SUCCESS) {
			test_Logger.log(Status.PASS, "TEST CASSE SUCCESS IS " + result.getName());
		}
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		test_Logger=extent.createTest(result.getName());
		
		test_Logger.info("On Test Failure capture the screenshots of Test "+ result.getName());	
		
		if (result.getStatus() == ITestResult.FAILURE) {
			test_Logger.log(Status.FAIL, "TEST CASE FAILED IS " + result.getName());
			test_Logger.log(Status.FAIL, "TEST CASE FAILED IS " + result.getThrowable());

			String screenshotsPath = BaseClass.screenCapture(result.getName());
			
			try {
				test_Logger.addScreenCaptureFromPath(screenshotsPath);
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
		test_Logger=extent.createTest(result.getName());
		test_Logger.info("On Test Skipped Test is "+ result.getName());	
		
		if (result.getStatus() == ITestResult.SKIP) {
			test_Logger.log(Status.SKIP, "TEST CASSE SKIPPED IS " + result.getName());
		} 
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

}
