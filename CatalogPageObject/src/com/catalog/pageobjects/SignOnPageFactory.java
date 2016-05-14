package com.catalog.pageobjects;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.catalog.common.Common;


public class SignOnPageFactory{
	private Logger logger= Logger.getLogger(SignOnPageFactory.class);
	
	@FindBy(how = How.CSS, using ="input[name='email_address']")
	private WebElement USERNAME;
		
	
	@FindBy(how = How.XPATH, using ="//*[@id='bodyContent']/div[2]/div/form/table/tbody/tr[2]/td[2]/input")
	private WebElement PWD;
	

	@FindBy(how = How.XPATH, using ="//*[@id='tdb5']/span[2]")
	private WebElement SIGNONBUTTON;
	
	private WebDriver driver;
	public Common CM;
	
	public SignOnPageFactory(WebDriver driver){
		this.driver = driver;
		CM = new Common(driver);
		
		this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		logger.info("Loading Account Login page..."); 
		PageFactory.initElements(this.driver, this);
		logger.info("Account Login page found, header verified.");
	}
	
	
	public void enterUserName(String svalue){
		CM.setValue(USERNAME,svalue);
	}
	
	public void enterPWD(String svalue){
		CM.setValue(PWD,svalue);
		
	}
	
	public void clickSignOnButton() {
		CM.click(SIGNONBUTTON);
			
	}
	
	public void Login(String UserName, String PWD){
		enterUserName(UserName);
		enterPWD(PWD);
		clickSignOnButton();
	}

}
