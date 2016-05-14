package com.catalog.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.catalog.common.Common;
import com.catalog.common.ScreenCapture;
import com.catalog.pageobjects.HomePage;
import com.catalog.pageobjects.HomePageFactory;
import com.catalog.pageobjects.SignOnPage;
import com.catalog.pageobjects.SignOnPageFactory;
import com.catalog.pageobjects.WelcomePage;
import com.catalog.pageobjects.WelcomePageFactory;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;


public class LoginTests{

	public Common CM;
//	public String sURL="http://107.170.213.234/catalog/";
	public HomePageFactory HomePage;
	public SignOnPageFactory SignOnPage;
	public WelcomePageFactory WelcomePage;
	private String[][] IDPASSWORD;
	private Properties prop;
	private WebDriver driver;
	private Logger logger= Logger.getLogger(LoginTests.class);
	@BeforeMethod
	public void setUp(){
		setProperty();
		setExcel();
		CM = new Common(driver);
		driver = CM.openBrowser(getProperty("BrowserType"));
		CM.openUrl(getProperty("URL"));		
	}
	
	public String getProperty(String key){
		return prop.getProperty(key);
	}
	
	public void setProperty(){
		File file = new File("C:/Users/Philips/workspace/CatalogPageObject/config.properties");
		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		prop = new Properties();
		
		//load properties file
		try {
			prop.load(fileInput);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void setExcel(){
		String FilePath = "C:/Users/Philips/workspace/CatalogPageObject/myData.xls";
		FileInputStream fs;
		Workbook wb = null;
		try{
			fs = new FileInputStream(FilePath);
			wb = Workbook.getWorkbook(fs);
		}catch(Exception e){
			System.err.println("File " + FilePath + " cannot be found!!");
		} 
		
		// TO get the access to the sheet
		Sheet sh = wb.getSheet("Sheet1");

		// To get the number of rows present in sheet
		int totalNoOfRows = sh.getRows();

		// To get the number of columns present in sheet
		int totalNoOfCols = sh.getColumns();
		IDPASSWORD = new String[totalNoOfRows][totalNoOfCols];
		for (int row = 0; row < totalNoOfRows; row++) {

		for (int col = 0; col < totalNoOfCols; col++) {
			IDPASSWORD[row][col] = sh.getCell(col, row).getContents();
		}
		}
	}
	@AfterMethod(alwaysRun=true)
	public void tearDown(ITestResult it) throws Exception{
		ScreenCapture ScreenCapture=new ScreenCapture(driver);
		ScreenCapture.takeScreenShot(it.getMethod());
		System.out.println("screenshot captured for: " +it.getMethod()+ " Failed TestCase");
		CM.closeBrowser();
	}
	
	@Test
	public void login01(){
	     HomePage=new HomePageFactory(driver);
	     CM.verifyText("Welcome to iBusiness");
	     HomePage.clickLogYourSelfLink();
	     SignOnPage=new SignOnPageFactory(driver);
	     SignOnPage.Login(IDPASSWORD[1][0], IDPASSWORD[1][1]);
	     WelcomePage=new WelcomePageFactory(driver);
	     WelcomePage.clickLogOff();
		
	}
	
	@Test
	public void login02(){
		HomePage=new HomePageFactory(driver);
		HomePage.clickLogYourSelfLink();
	    SignOnPage=new SignOnPageFactory(driver);
	    SignOnPage.clickSignOnButton();
	    String ActualText=" Error: No match for E-Mail Address and/or Password.";
	    CM.verifyText(ActualText);
	    WelcomePage=new WelcomePageFactory(driver);
	    WelcomePage.clickLogOff();

		
	}

}
