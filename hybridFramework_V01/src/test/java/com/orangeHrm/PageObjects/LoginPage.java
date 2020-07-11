package com.orangeHrm.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	// page layer : Object Repository 
	// all objects which you are interacting on this page 
	//we will store it in repository
	
	@FindBy(name="txtUsername")
	@CacheLookup
	WebElement userName;
		
	@FindBy(how=How.NAME, using="txtPassword")
	@CacheLookup
	WebElement userPass;
	
	@FindBy(name="Submit")
	@CacheLookup
	WebElement loginBtn;
			
	@FindBy(xpath="//a[text()='Logout']")
	@CacheLookup
	WebElement logoutLink;
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver){		
		this.driver=driver;
		// initializing current class web element
		//PageFactory.initElements(driver, LoginPage.class);		
		PageFactory.initElements(driver, this);		
	}	
	
	public void loginToOrangeHRM(String username, String userpass){
	
		try{				
			userName.sendKeys(username);
			userPass.sendKeys(userpass);
			loginBtn.click();
			
		}catch(Exception e){
			System.out.println("Element not found and exception is :"+e.getMessage());
		}
	}
	
	public void logoutFromOrangeHRM(){
		
		logoutLink.click();
	}
	
}
