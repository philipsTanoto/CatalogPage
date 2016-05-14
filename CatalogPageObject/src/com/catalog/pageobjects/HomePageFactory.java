package com.catalog.pageobjects;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.catalog.common.Common;

public class HomePageFactory{
	private WebDriver driver;
	public Common CM;
	private Logger logger= Logger.getLogger(HomePageFactory.class);
	@FindBy(how = How.XPATH, using="//*[@id='bodyContent']/div/div[1]/a[1]/u")
	private WebElement LOGYOURSELFLINK;

	public HomePageFactory(WebDriver driver){
		this.driver = driver;
		CM = new Common(driver);
		this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		logger.info("Loading WelcomePage page..."); 
		PageFactory.initElements(this.driver, this);
	}
	
	public void clickLogYourSelfLink(){
		CM.click(LOGYOURSELFLINK);
		
	}
	

}
