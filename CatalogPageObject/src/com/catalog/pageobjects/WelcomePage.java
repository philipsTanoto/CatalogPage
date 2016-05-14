package com.catalog.pageobjects;

import com.catalog.common.Common;

public class WelcomePage{
	public Common CM;
	public String LOGOFFBUTTON="//*[@id='tdb4']/span";
	
	public void clickLogOff(){
		CM = new Common();
		CM.clickByXpath(LOGOFFBUTTON);
		
	}

}
