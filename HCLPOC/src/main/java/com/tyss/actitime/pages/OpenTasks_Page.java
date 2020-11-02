package com.tyss.actitime.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.tyss.acttime.util.WebActionUtil;
/**
 * Description This class has the implementations of Open Tasks related methods 
 * @author Sajal jain
 */
public class OpenTasks_Page {
	public WebDriver driver;
	public WebActionUtil WebActionUtil;
	public long ETO = 10;

	public OpenTasks_Page(WebDriver driver, long ETO, WebActionUtil WebActionUtil){
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.WebActionUtil = WebActionUtil;
		this.ETO = ETO;
	}
	
	/*select tasks check box*/
	@FindBy(xpath="(//input[@type='checkbox'])[last()]")
	private WebElement cbSelectTasks;
	
	/*Delete Selected Tasks Button*/
	@FindBy(css="input[value='Delete Selected Tasks']")
	private WebElement btnDeleteSelectedTasks;
	
	
	/*Delete With Users Time Entries button*/
	@FindBy(id="deleteTaskPopup_deleteConfirm_cancelBtn")
	private WebElement btnDeleteWithUsersTimeEntries;

	/*Complete Selected Tasks Button*/
	@FindBy(css="input[value='Complete Selected Tasks'")
	private WebElement btnCompleteSelectedTasks;
	
	/*Customers And Projects Dropdown*/
	@FindBy(xpath="//span[text()='All active projects of all active customers']")
	private WebElement ddCustomersAndProjects;
	
	/*All active projects of all active customers
	@FindBy(xpath="//label[text()='All active projects of all active customers']/preceding-sibling::img")*/
	private WebElement sddAllActiveProjectsOfAllActiveCustomers;
	
	/*All active projects of all active customers*/
	@FindBy(xpath="//label[text()='Selected customers and projects']/preceding-sibling::img")
	private WebElement sddSelectedCustomersAndProjects;
	
	/*All active projects of all active customers
	@FindBy(xpath="//span[text()='Architects Bureau']/..")*/
	private WebElement sddFromSelectedCustomersAndProject;
	
	/*Close button*/
	@FindBy(xpath="//span[text()='Close']")
	private WebElement btnClose;
	
	/**
	 * Description  Method to select All Active Projects Of All Active Customers Drop down
	 * @author Sajal jain
	 * 
	 */
	public synchronized void selectAllActiveProjectsOfAllActiveCustomersRb(String dropdownValue1) {
//		sddAllActiveProjectsOfAllActiveCustomers = driver.findElement(By.xpath("//label[text()='"+dropdownValue1+"']/preceding-sibling::img"));
		driver.findElement(By.xpath("//label[text()='"+dropdownValue1+"']/preceding-sibling::img")).click();
	}
	/**
	 * Description  Method to select From Selected Customers And Project Customers Drop down
	 * @author Sajal jain
	 * 
	 */
	public synchronized void selectFromSelectedCustomersAndProjectCustomers(String cpValue) {
	//	sddFromSelectedCustomersAndProject = driver.findElement(By.xpath("//span[text()='"+cpValue+"']/preceding-sibling::span"));
		driver.findElement(By.xpath("//span[text()='"+cpValue+"']/preceding-sibling::span")).click();
	}
	
	/**
	 * Description  Method to click Selected customers and projects radio button in Dropdown
	 * @author Sajal jain
	 * 
	 */
	public void selectSelectedCustomersAndProjectsDD() {
		WebActionUtil.waitForElement(ddCustomersAndProjects, "CustomersAndProjects Dropdown", 45);
		Actions act=new Actions(driver);
		act.moveToElement(ddCustomersAndProjects).perform();
		WebActionUtil.clickOnElementUsingJS(ddCustomersAndProjects, "Customers And Projects Dropdown");
	/*	WebActionUtil.waitForElement(sddAllActiveProjectsOfAllActiveCustomers, "Selected customers and projects drop down", 45);
		WebActionUtil.clickOnElementUsingJS(sddAllActiveProjectsOfAllActiveCustomers, "Selected customers and projects drop down");*/
	} 
	
	/**
	 * Description  Method to click Option from Selected Customers And Project
	 * @author Sajal jain
	 * 
	 */
	public void selectFromSelectedCustomersAndProjectcb() {
		WebActionUtil.waitForElement(sddFromSelectedCustomersAndProject, "Select from Selected Customers And Project button", 45);
		WebActionUtil.clickOnElementUsingJS(sddFromSelectedCustomersAndProject, "Select from Selected Customers And Project button");
	} 
	
	
	/**
	 * Description  Method to click All active projects of all active customers radio button Dropdown
	 * @author Sajal jain
	 * 
	 */
	public void selectAllActiveProjectsOfAllActiveCustomersDD() {
		WebActionUtil.waitForElement(ddCustomersAndProjects, "CustomersAndProjects Dropdown", 45);
		Actions act=new Actions(driver);
		act.moveToElement(ddCustomersAndProjects).perform();
		WebActionUtil.clickOnElementUsingJS(ddCustomersAndProjects, "CustomersAndProjects Dropdown");
		WebActionUtil.waitForElement(sddAllActiveProjectsOfAllActiveCustomers, "All active projects of all active customers drop down", 45);
		WebActionUtil.clickOnElementUsingJS(sddAllActiveProjectsOfAllActiveCustomers, "All active projects of all active customers drop down");
	} 
	
	
	/**
	 * Description  Method to click Close button
	 * @author Sajal jain
	 * 
	 */
	public void clkCLoseBtn() {
		WebActionUtil.waitForElement(btnClose, "Close button", 45);
		WebActionUtil.clickOnElementUsingJS(btnClose, "CLose button");
	} 
	
	
	/**
	 * Description  Method to click Complete Selected Tasks button
	 * @author Sajal jain
	 * 
	 */
	public void clkCompleteSelectedTasksBtn() {
		WebActionUtil.waitForElement(btnCompleteSelectedTasks, "Complete Selected Tasks button", 45);
		WebActionUtil.clickOnElementUsingJS(btnCompleteSelectedTasks, "Complete Selected Tasks button");
	} 
	
	/**
	 * Description  Method to click select Tasks check box
	 * @author Sajal jain
	 * 
	 */
	public void clkSelectTaskscb() {
		WebActionUtil.waitForElement(cbSelectTasks, "Select Tasks checkbox", 45);
		WebActionUtil.clickOnElementUsingJS(cbSelectTasks, "Select Tasks checkbox");

	}
	
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
	
}
