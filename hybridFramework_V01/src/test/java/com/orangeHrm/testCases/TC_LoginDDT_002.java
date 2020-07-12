package com.orangeHrm.testCases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.orangeHrm.PageObjects.LoginPage;

public class TC_LoginDDT_002 extends BaseClass {

	// Retrieve particular test data from excel
	/*
	 * @Test public void loginToOrangeHRMTest() { LoginPage login = new
	 * LoginPage(driver);
	 * login.loginToOrangeHRM(excelDataProvider.getCellData(1, 0),
	 * excelDataProvider.getCellData(1, 1)); WebElement welcomeLink =
	 * driver.findElement(By.id("welcome"));
	 * 
	 * if (welcomeLink.isDisplayed()) { Assert.assertTrue(true);
	 * System.out.println("login sucessful.."); welcomeLink.click();
	 * 
	 * login.logoutFromOrangeHRM(); } else {
	 * System.out.println("login failed.."); Assert.assertTrue(false); } }
	 */

	@Test(dataProvider = "loginTestData")
	public void loginToOrangeHRMTest(String user, String pass) {

		LoginPage login = new LoginPage(driver);
		login.loginToOrangeHRM(user, pass);
		
		if (driver.findElement(By.id("welcome")).isDisplayed()) {
			Assert.assertTrue(true);
			driver.findElement(By.id("welcome")).click();
			login.logoutFromOrangeHRM();
			
		} else {
			//driver.navigate().refresh();
			Assert.assertTrue(false);
		}
		login.logoutFromOrangeHRM();

	}

	@DataProvider(name = "loginTestData")
	public Object[][] getDataa() {
		Object[][] loginTestData = excelDataProvider.getCellData();
		return loginTestData;
	}

}
