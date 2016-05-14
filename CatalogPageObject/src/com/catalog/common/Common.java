package com.catalog.common;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;



public class Common {
	
	private WebDriver driver;
	private Logger logger= Logger.getLogger(Common.class);
	public Common(WebDriver driver){
		this.driver = driver;
	}
	
	public void setDriver(WebDriver driver){
		this.driver = driver;
	}
	
	public WebDriver openBrowser(String wd){
		if(wd.equalsIgnoreCase("Chrome")){
			System.setProperty("webdriver.chrome.driver", "C:/Users/Philips/workspace/CatalogPageObject/drivers/chromedriver.exe");
			driver=new ChromeDriver();
			logger.info("Creating ChromeDriver");
		}else if(wd.equalsIgnoreCase("Firefox")){
			driver = new FirefoxDriver();
			logger.info("Creating FirefoxDriver");
		}else if(wd.equalsIgnoreCase("InternetExplorer")){
			System.setProperty("webdriver.ie.driver", "C:/Users/Philips/workspace/CatalogPageObject/drivers/IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			logger.info("Creating InternetExplorerDriver");
		}
		driver.manage().window().maximize();
		logger.info(" Browser is mazimized");
		driver.manage().deleteAllCookies();
		logger.info("deleted all cookies");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		logger.info("Opening the browser");
		return driver;
	}
	
	public void openUrl(String sURL){
		driver.get(sURL);
		logger.info("Opening the URL");
		
	}
	
	public void closeBrowser(){
		driver.quit();
		logger.info("close browser");
	}
	
	 public void verifyText(String expected){
		 try{
			 driver.findElement(By.xpath("//*[contains(text(),'"+ expected.trim() +"')]"));
			 logger.info("On page " + driver.getTitle() + ". Expected Text \""+ expected +"\" verified");

		 }
		 catch(NoSuchElementException e){
			 logger.info("On page " + driver.getTitle() + ". Expected Text \""+ expected +"\" not found");
			 Assert.fail("On page " + driver.getTitle() + ". Expected Text \""+ expected +"\" not found");
		 }
		 
	 }
    
	 public WebDriver getDriver(){
			return driver;
		}
	
	public void setValue(WebElement slocator, String sValue){
		try{
			slocator.clear();
			slocator.sendKeys(sValue);
			logger.info(sValue+" entered");
		}catch(Exception e){
			logger.info(slocator+" not found");
		}
		
	}
	
	
	public void click(WebElement slocator){
		try {
			String Element=slocator.getText();
			if ((Element.isEmpty()) || (Element==null)){
				Element=slocator.getAttribute("value");
			}
			 logger.info(Element + " trying to click");

			slocator.click();
			 logger.info(Element + " clicked ");
					
		} catch (Exception ex) {
			ex.printStackTrace();
			 logger.info(slocator + " not clicked ");
		}
		}
	
	 

}
