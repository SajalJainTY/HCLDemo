package com.tyss.actitime.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.tyss.acttime.util.WebActionUtil;
/**
 * Description This class has the implementations of the Completed Tasks related methods 
 * @author Sajal jain
 */
public class CompletedTasks_Page {
	public WebDriver driver;
	public WebActionUtil WebActionUtil;
	public long ETO = 10;

	public CompletedTasks_Page(WebDriver driver, long ETO, WebActionUtil WebActionUtil){
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.WebActionUtil = WebActionUtil;
		this.ETO = ETO;
	}
	
	/*Re-open Selected Tasks button*/
	@FindBy(css="input[value='Re-open Selected Tasks']")
	private WebElement btnReopenSelectedTasks;
	
	/*select tasks check box*/
	@FindBy(xpath="(//input[@type='checkbox'])[last()]")
	private WebElement cbSelectTasks;
	
	/*Delete Selected Tasks Button*/
	@FindBy(css="input[value='Delete Selected']")
	private WebElement btnDeleteSelectedTasks;
	
	
	/*Delete With Users Time Entries button*/
	@FindBy(id="deleteTaskPopup_deleteConfirm_cancelBtn")
	private WebElement btnDeleteWithUsersTimeEntries;

	/**
	 * Description  Method to click delete with users time entries button
	 * @author Sajal jain
	 * 
	 */
	public void clkDeleteWithUsersTimeEntriesBtn() {
		WebActionUtil.waitForElement(btnDeleteWithUsersTimeEntries, "Delete With Users Time Entries button", 45);
		WebActionUtil.clickOnElementUsingJS(btnDeleteWithUsersTimeEntries, "Delete With Users Time Entries button");
	}
		
	/**
	 * Description  Method to click delete selected tasks button
	 * @author Sajal jain
	 * 
	 */
	public void clkDeleteSelectedTasksBtn() {
		WebActionUtil.waitForElement(btnDeleteSelectedTasks, "Delete Selected Tasks button", 45);
		WebActionUtil.clickOnElementUsingJS(btnDeleteSelectedTasks, "Delete Selected Tasks button");
	}
	
	
	/**
	 * Description  Method clicks on Reopen Selected Tasks button
	 * @author Sajal jain
	 * 
	 */
	public synchronized void clkReopenSelectedTasksBtn() {
		WebActionUtil.waitForElement(btnReopenSelectedTasks, "Reopen Selected Tasks button", 45);
		WebActionUtil.clickOnElementUsingJS(btnReopenSelectedTasks, "Reopen Selected Tasks button");
	}
	
	/**
	 * Description  Method to click select tasks check box
	 * @author Sajal jain
	 * 
	 */
	public void clkSelectTaskscb() {
		WebActionUtil.waitForElement(cbSelectTasks, "Select Tasks checkbox", 45);
		WebActionUtil.clickOnElementUsingJS(cbSelectTasks, "Select Tasks checkbox");

	}
}
