package com.orangeHrm.testCases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.orangeHrm.PageObjects.LoginPage;

public class TC_LoginDDT_002 extends BaseClass {

	// Retrieve particular test data from excel

//	@Test
//	public void loginToOrangeHRMTest() {
//		LoginPage login = new LoginPage(driver);
//		login.loginToOrangeHRM(excelDataProvider.getCellData(1, 0), excelDataProvider.getCellData(1, 1));
//		
//		WebElement welcomeLink = driver.findElement(By.id("welcome"));
//
//		if (welcomeLink.isDisplayed()) {
//			Assert.assertTrue(true);
//			System.out.println("login sucessful..");
//			welcomeLink.click();
//
//			login.logoutFromOrangeHRM();
//		} else {
//			System.out.println("login failed..");
//			Assert.assertTrue(false);
//		}
//	}

	@Test(dataProvider = "loginTestData")
	public void loginToOrangeHRMTest(String user, String pass) {

		LoginPage login = new LoginPage(driver);
		login.loginToOrangeHRM(user, pass);

		if (driver.getPageSource().contains("Welcome Admin")) {

			System.out.println("login success");
			
			try{
				Thread.sleep(3000);
				driver.findElement(By.id("welcome")).click();	
			}catch(Exception e){
				
			}
				Assert.assertTrue(true);	
			login.logoutFromOrangeHRM();
		} else if (driver.getPageSource().contains("LOGIN Panel")) {
			System.out.println("login failed");
			Assert.assertTrue(false);
		}

	}

	@DataProvider(name = "loginTestData")
	public Object[][] getDataa() {
		Object[][] loginTestData = excelDataProvider.getCellData();
		return loginTestData;
	}

}
