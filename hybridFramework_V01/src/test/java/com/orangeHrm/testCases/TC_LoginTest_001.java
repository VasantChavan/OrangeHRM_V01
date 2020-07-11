package com.orangeHrm.testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.orangeHrm.PageObjects.LoginPage;

public class TC_LoginTest_001 extends BaseClass {
		
	@Test
	public void loginToOrangeHRMTest(){
	//java.lang.OutOfMemoryError: Java heap space
		LoginPage login =new LoginPage(driver);		
		login.loginToOrangeHRM(configData.getUser(), configData.getPass());
		
		WebElement welcomeLink=driver.findElement(By.id("welcome"));
		
		if(welcomeLink.isDisplayed()){			
			Assert.assertTrue(true);
			System.out.println("login sucessful..");			
			welcomeLink.click();
			
			login.logoutFromOrangeHRM();
		}
		else{
			System.out.println("login failed..");
			Assert.assertTrue(false);
		}
	}

}
