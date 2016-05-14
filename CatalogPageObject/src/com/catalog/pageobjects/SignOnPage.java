package com.catalog.pageobjects;

import com.catalog.common.Common;

public class SignOnPage{
	
	public Common CM = new Common();
	public String USERNAME="email_address";
	public String PWD="password";
	public String SIGNONBUTTON="//*[@id='tdb5']/span[2]";
	
	
	
	public void enterUserName(String svalue){
		CM.setValueByName(USERNAME,svalue);
	}
	
	public void enterPWD(String svalue){
		CM.setValueByName(PWD,svalue);
		
	}
	
	public void clickSignOnButton() {
		CM.clickByXpath(SIGNONBUTTON);
			
	}
	
	public void Login(String UserName, String PWD){
		enterUserName(UserName);
		enterPWD(PWD);
		clickSignOnButton();
	}

}
