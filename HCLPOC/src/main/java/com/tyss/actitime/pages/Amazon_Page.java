package com.tyss.actitime.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.tyss.acttime.baseutil.BaseTest;
import com.tyss.acttime.util.WebActionUtil;

import junit.framework.Assert;
/**
 * Description This class has the implementations of Amazon.com
 * 
 * @author Sajal jain
 */
public class Amazon_Page {
	public WebDriver driver;
	public WebActionUtil WebActionUtil;
	public long ETO = 10;

	public Amazon_Page(WebDriver driver, long ETO, WebActionUtil WebActionUtil) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.WebActionUtil = WebActionUtil;
		this.ETO = ETO;
	}
	/*Mobiles Tab*/
	@FindBy(xpath="//a[text()='Mobiles']")
	private WebElement tabMobiles;
	/*Electronics Menus*/
	@FindBys({@FindBy(xpath="//span[@class='nav-a-content']")})
	private List<WebElement> menuElectronics;
	
	
	/**
	 * Description Method to validate Electronics Menu Options
	 * 
	 * @author Sajal jain
	 */
	public void validateElectronicsMenuOtn(String expectedMenu) {
		for(WebElement wb:menuElectronics) {
			System.out.println(wb.getText().trim());
			String actualMenu = wb.getText().trim();
			Assert.assertTrue(actualMenu.contains(expectedMenu));
		}
		BaseTest.logger.info("All Menus Present");
	}
	
	
	/**
	 * Description Method to click on Mobiles Tab
	 * 
	 * @author Sajal jain
	 */
	public void clkMobilesTab() {
		WebActionUtil.isElementVisible(tabMobiles, "Mobiles Tab");
		WebActionUtil.waitForElement(tabMobiles, "Mobiles Tab", 45);
		WebActionUtil.clickOnElementUsingJS(tabMobiles, "Mobiles Tab");
	}
	
}
