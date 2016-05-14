package com.catalog.pageobjects;

import com.catalog.common.Common;

public class HomePage{
	public Common CM;
	public String LOGYOURSELFLINK="//*[@id='bodyContent']/div/div[1]/a[1]/u";
	
	public void clickLogYourSelfLink(){
		CM = new Common(null); 
		CM.clickElementByXpath(LOGYOURSELFLINK);
		
	}
	

}
