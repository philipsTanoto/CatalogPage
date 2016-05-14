package com.catalog.pageobjects;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.catalog.common.Common;


public class WelcomePageFactory{
	private WebDriver driver;
	public Common CM;
	@FindBy(how = How.XPATH, using ="//*[@id='tdb4']/span")
	private WebElement LOGOFFBUTTON;

	private Logger logger= Logger.getLogger(WelcomePageFactory.class);
	public WelcomePageFactory(WebDriver driver){
		this.driver = driver;
		CM = new Common(driver);
		PageFactory.initElements(this.driver, this);
		logger.info("logging out");
		clickLogOff();
	}
	public void clickLogOff(){
		
		CM.click(LOGOFFBUTTON);
		
	}

}
