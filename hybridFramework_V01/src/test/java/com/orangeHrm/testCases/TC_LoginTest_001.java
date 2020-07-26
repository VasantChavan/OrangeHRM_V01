package com.orangeHrm.testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.orangeHrm.PageObjects.LoginPage;

public class TC_LoginTest_001 extends BaseClass {
		
	@Test
	public void loginToOrangeHRMTest(){
			
		//test_Logger.info("This is my login test");
		logger.info("Test Case started and login page is ready..");
		
		LoginPage login =new LoginPage(driver);		
		
		logger.info("Enter username and password and click on login button");
		login.loginToOrangeHRM(configData.getUser(), configData.getPass());
		
		WebElement welcomeLink=driver.findElement(By.id("welcome"));
		
		if(welcomeLink.isDisplayed()){			
			Assert.assertTrue(true);
			System.out.println("login sucessful..");			
			welcomeLink.click();
			logger.info("Test Case success and logout from an app.");
			
			login.logoutFromOrangeHRM();
		}
		else{
			//System.out.println("login failed..");
			
			logger.info("Test Case failed .");
			Assert.assertTrue(false);
		}
	}

}
