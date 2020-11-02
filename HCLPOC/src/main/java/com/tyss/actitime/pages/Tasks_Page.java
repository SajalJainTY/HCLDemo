package com.tyss.actitime.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.tyss.acttime.util.WebActionUtil;
/**
 * Description This class has the implementations of the Tasks related methods 
 * @author Sajal jain
 */
public class Tasks_Page {

	public WebDriver driver;
	public WebActionUtil WebActionUtil;
	public long ETO = 10;

	public Tasks_Page(WebDriver driver, long ETO, WebActionUtil WebActionUtil){
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.WebActionUtil = WebActionUtil;
		this.ETO = ETO;
	}
	
	/*Open tasks tab*/
	@FindBy(partialLinkText="Open Tasks")
	private WebElement tabOpenTasks;
	/*Project name text box*/
	@FindBy(id="createTasksPopup_newProject")
	private WebElement txtProjectName;
	/*Customer name text box*/
	@FindBy(css="input[placeholder='Enter Customer Name']")
	private WebElement txtCustomerName;
	/*Task name text box*/
	@FindBy(css="input[placeholder='Enter task name']")
	private WebElement txtTaskName;
	/* Task Tab */
	@FindBy(xpath = "//div[text()='TASKS']/..")
	private WebElement tabTasks;
	/*Completed tasks tab*/
	@FindBy(xpath="//a[text()='Completed Tasks']")
	private WebElement tabCompletedTasks;
	/*select tasks check box*/
	@FindBy(xpath="(//input[@type='checkbox'])[last()]")
	private WebElement cbSelectTasks;
	/* Archives tab*/
	@FindBy(linkText="Archives")
	private WebElement tabArchives;
	/*Restore Selected From Archives button*/
	@FindBy(css="input[value='Restore Selected From Archives']")
	private WebElement btnRestoreSelectedFromArchives;
	/*select check box*/
	@FindBy(xpath="(//input[@type='checkbox'])[last()]")
	private WebElement cbSelect;
	/*Projects and Customers tab*/
	@FindBy(xpath="//a[text()='Projects & Customers']")
	private WebElement tabProjectsAndCustomers;
	/* New Create Tasks button */
	@FindBy(id = "createTasksDiv")
	private WebElement btnNewCreateTasks;
	/* Create Task button in popup*/
	@FindBy(id = "createTasksPopup_commitBtn")
	private WebElement btnCreateTaskspopup;
	/*Delete Selected tasks button*/
	@FindBy(css="input[value='Delete Selected Tasks']")
	private WebElement btnDeleteSelectedTasks;
	/*Delete Selected Button*/
	@FindBy(css="input[value='Delete Selected']")
	private WebElement btnDeleteSelected;
	/*Select customer drop down*/
	@FindBy(xpath="//div[@id='createTasksPopup_customerSelector']/table")
	private WebElement ddSelectCustomer;
	/*Select New Customer drop down*/
	@FindBy(xpath="//a[text()='- New Customer -']")
	private WebElement sddNewCustomer;
	/*Enter Task Name Text Box*/
	@FindBy(id="editTaskPopup_nameField")
	private WebElement txtEnterTaskName;
	/*Save Changes Button*/
	@FindBy(id="editTaskPopup_commitBtn")
	private WebElement btnSaveChanges;
	/*Status Drop down*/
	@FindBy(id="editTaskPopup_statusSelectorPlaceholder")
	private WebElement ddStatus;
	/*Customer Drop down*/
	@FindBy(xpath="//span[@id='editTaskPopup_customerSelector']/table")
	private WebElement ddCustomer;
	/*Project Drop down*/
	@FindBy(xpath="//span[@id='editTaskPopup_projectSelector']/table")
	private WebElement ddProject;
	/*Type Of Work Drop down*/
	@FindBy(xpath="//span[@id='editTaskPopup_taskBillingType']/table")
	private WebElement ddTypeOfWork;
	/*Delete This Task Tab*/
	@FindBy(xpath="//span[text()='Delete this task permanently']")
	private WebElement tabDeleteThisTask;
	/*Delete Task Permanently Button*/
	@FindBy(id="editTaskPopup_deleteConfirm_submitBtn")
	private WebElement btnDeleteTaskPermanently;
	/*Filter Tasks By Name Text Box*/
	@FindBy(name="visiableFilterString")
	private WebElement txtFilterTasksByName;
	
	/**
	 * Description  Method to enter Filter Tasks By Name Text Box
	 * @author Sajal jain
	 * @param filterName
	 */
	public void setFilterTasksByName(String filterName) {
		WebActionUtil.waitForElement(txtFilterTasksByName, "Filter Tasks By Name Text Box", 25);
		WebActionUtil.typeText(txtFilterTasksByName, filterName, "Filter Tasks By Name Text Box");
	}
	
	/**
	 * Description  Method to click Delete Task Permanently Button
	 * @author Sajal jain
	 * 
	 */
	public void clkDeleteTaskPermanentlyBtn() {
		WebActionUtil.waitForElement(btnDeleteTaskPermanently, "Delete Task Permanently Button", 45);
		WebActionUtil.clickOnElementUsingJS(btnDeleteTaskPermanently, "Delete Task Permanently Button");
	}
	/**
	 * Description  Method to click Delete this task permanently Button
	 * @author Sajal jain
	 * 
	 */
	public void clkDeleteThisTaskTab() {
		WebActionUtil.scrollToElement(tabDeleteThisTask, "Delete this task permanently Button");
		WebActionUtil.waitForElement(tabDeleteThisTask, "Delete this task permanently Button", 45);
		WebActionUtil.clickOnElementUsingJS(tabDeleteThisTask, "Delete this task permanently Button");
	}
	
	/**
	 * Description  Method to select Type of Work Drop down
	 * @author Sajal jain
	 * @param typeOfWork
	 */
	public void selectTypeOfWorkDD(String typeOfWork) {
		WebActionUtil.waitForElement(ddTypeOfWork, "Type of Work Drop down", 45);
	//	WebActionUtil.clickOnElementUsingJS(ddProject, "Type of Work Drop down");
		WebActionUtil.clickOnWebElement(ddTypeOfWork, "Type of Work Drop down", "Unable to click on Project Drop down");
		WebElement otnTypeOfWork = driver.findElement(By.xpath("//a[contains(text(),'"+typeOfWork+"')]"));
		WebActionUtil.isElementClickable(otnTypeOfWork, "Select Type of Work Option Drop down");
		WebActionUtil.waitForElement(otnTypeOfWork, "Select Type of Work Option Drop down", 45);
		WebActionUtil.clickOnElementUsingJS(otnTypeOfWork, "Select Type of Work Option Drop down");
	}
	
	/**
	 * Description  Method to select Project Drop down
	 * @author Sajal jain
	 * @param project
	 */
	public void selectProjectDD(String project) {
		WebActionUtil.waitForElement(ddProject, "Project Drop down", 45);
		WebActionUtil.action.moveToElement(ddProject).perform();
//		WebActionUtil.clickOnElementUsingJS(ddProject, "Project Drop down");
		WebActionUtil.clickOnWebElement(ddProject, "Project Drop down", "Unable to click on Project Drop down");
		WebElement otnProject = driver.findElement(By.xpath("//a[contains(text(),'"+project+"')]"));
		WebActionUtil.isElementClickable(otnProject, "Select Project Option Drop down");
		WebActionUtil.waitForElement(otnProject, "Select Project Option Drop down", 45);
		WebActionUtil.clickOnElementUsingJS(otnProject, "Select Project Option Drop down");
	}
	
	/**
	 * Description  Method to select Customer Drop down
	 * @author Sajal jain
	 * @param customer
	 */
	public void selectCustomerDD(String customer) {
		WebActionUtil.waitForElement(ddCustomer, "Customer Drop down", 45);
		WebActionUtil.action.moveToElement(ddCustomer).perform();
	//	WebActionUtil.clickOnElementUsingJS(ddCustomer, "Customer Drop down");
		WebActionUtil.clickOnWebElement(ddCustomer, "Customer Drop down", "Unable to click on Customer Drop down");
		WebElement otnCustomer = driver.findElement(By.xpath("//a[contains(text(),'"+customer+"')]"));
	/*	WebActionUtil.isElementClickable(otnCustomer, "Select Customer Option Drop down");
		WebActionUtil.isElementVisible(otnCustomer, "Select Customer Option Drop down");	
		WebActionUtil.waitForElement(otnCustomer, "Select Customer Option Drop down", 45);
		*///WebActionUtil.clickOnElementUsingJS(otnCustomer, "Select Customer Option Drop down");
		WebActionUtil.clickOnWebElement(otnCustomer, "Select Customer Option Drop down", "Unable to click on Option Drop down");
	}
	
	/**
	 * Description  Method to select Status Drop down
	 * @author Sajal jain
	 * @param status
	 */
	public void selectStatusDD(String status) {
		WebActionUtil.waitForElement(ddStatus, "Status Drop down", 45);
		WebActionUtil.clickOnElementUsingJS(ddStatus, "Status Drop down");
		WebActionUtil.clickOnWebElement(ddStatus, "Status Drop down", "Unable to click on Status Drop down");
		WebElement otnStatus = driver.findElement(By.xpath("(//a[contains(text(),'"+status+"')])[2]"));
		WebActionUtil.waitForElement(otnStatus, "Select Status Option Drop down", 45);
		WebActionUtil.clickOnElementUsingJS(otnStatus, "Select Status Option Drop down");
	}

	
	/**
	 * Description  Method to click Save Changes Button
	 * @author Sajal jain
	 * 
	 */
	public void clkSaveChangesBtn() {
		WebActionUtil.waitForElement(btnSaveChanges, "Save Changes Button", 45);
		WebActionUtil.clickOnElementUsingJS(btnSaveChanges, "Save Changes Button");
	}
	/**
	 * Description  Method to enter New Task Name
	 * @author Sajal jain
	 * @param newTaskName
	 */
	public void setEnterTaskName(String newTaskName) {
		WebActionUtil.waitForElement(txtEnterTaskName, "Enter Task Name Text Box", 25);
		WebActionUtil.clearText(txtEnterTaskName, "Enter Task Name Text Box");
		WebActionUtil.typeText(txtEnterTaskName, newTaskName, "Enter Task Name Text Box");
	}
	
	/**
	 * Description Method to click on created task link
	 * 
	 * @author Sajal jain
	 * @param taskName
	 */
	public void selectTaskLnk(String taskName) {
		WebElement lnkTask = driver.findElement(By.xpath("//a[contains(text(),'"+taskName+"')]"));
		WebActionUtil.waitForElement(lnkTask, "Task link", 45);
		WebActionUtil.clickOnElementUsingJS(lnkTask, "Task link");
	}
	
	/**
	 * Description  Method to select New Customer from drop down
	 * @author Sajal jain
	 * 
	 */
	public void selectNewCustomerdd() {
		WebActionUtil.waitForElement(ddSelectCustomer, "Select Customer Dropdown", 45);
		Actions act=new Actions(driver);
		act.moveToElement(ddSelectCustomer).perform();
		WebActionUtil.clickOnElementUsingJS(ddSelectCustomer, "Select Customer Dropdown");
		WebActionUtil.waitForElement(sddNewCustomer, "New Customer drop down", 45);
		WebActionUtil.clickOnElementUsingJS(sddNewCustomer, "New Customer drop down");
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
	 * Description  Method to click create task button
	 * @author Sajal jain
	 * 
	 */
	public void clkDeleteSelectedBtn() {
		WebActionUtil.waitForElement(btnDeleteSelected, "Delete Selected Button", 5);
		WebActionUtil.clickOnWebElement(btnDeleteSelected, "Delete Selected Button",
				"Unable to click on Delete Selected Button");
	}
	
	
	/**
	 * Description  Method to click create task button
	 * @author Sajal jain
	 * 
	 */
	public void clkCreateTasksPopupBtn() {
		WebActionUtil.waitForElement(btnCreateTaskspopup, "Create Tasks  Button", 5);
		WebActionUtil.clickOnWebElement(btnCreateTaskspopup, "Create Tasks Button",
				"Unable to click on Create Tasks Button");
	}
	
	/**
	 * Description  Method to click create task button
	 * @author Sajal jain
	 * 
	 */
	public void clkNewCreateTasksBtn() {
		WebActionUtil.waitForElement(btnNewCreateTasks, "New Create Tasks  Button", 5);
		WebActionUtil.clickOnWebElement(btnNewCreateTasks, "New Create Tasks Button",
				"Unable to click on New Create Tasks Button");
	}
	
	/**
	 * Description  Method to click Projects And Customers Tab
	 * @author Sajal jain
	 * 
	 */
	public void clkProjectsAndCustomersTab() {
		WebActionUtil.waitForElement(tabProjectsAndCustomers, "Projects And Customers Tab", 45);
		WebActionUtil.clickOnElementUsingJS(tabProjectsAndCustomers, "Projects And Customers Tab");
	}
	
	
	/**
	 * Description  Method to click select checkbox
	 * @author Sajal jain
	 * 
	 */
	public void clkSelectCb() {
		WebActionUtil.waitForElement(cbSelect, "select checkbox", 45);
		WebActionUtil.clickOnElementUsingJS(cbSelect, "select checkbox");
	}
	
	/**
	 * Description  Method to click Restore Selected From Archives button
	 * @author Sajal jain
	 * 
	 */
	public void clkRestoreSelectedFromArchivesbtn() {
		WebActionUtil.waitForElement(btnRestoreSelectedFromArchives, "Restore Selected From Archives button", 45);
		WebActionUtil.clickOnElementUsingJS(btnRestoreSelectedFromArchives, "Restore Selected From Archives button");
	}
	
	/**
	 * Description  Method to click Archives tab
	 * @author Sajal jain
	 * 
	 */
	public void clkArchivesTab() {
		WebActionUtil.waitForElement(tabArchives, "Archives tab", 45);
		WebActionUtil.clickOnElementUsingJS(tabArchives, "Archives tab");
	}
	
	/**
	 * Description  Method to click completed tasks tab
	 * @author Sajal jain
	 * 
	 */
	public void clkCompletedTasksTab() {
		WebActionUtil.isElementVisible(tabCompletedTasks, "Completed Tasks Tab");
		WebActionUtil.waitForElement(tabCompletedTasks, "Completed Tasks Tab", 45);
		WebActionUtil.clickOnElementUsingJS(tabCompletedTasks, "Completed Tasks Tab");
	}
	/**
	 * Description  Method to click select tasks check box
	 * @author Sajal jain
	 * 
	 */
	public void clkSelectTaskschk() {
		WebActionUtil.waitForElement(cbSelectTasks, "Select Tasks chkbox", 45);
		WebActionUtil.clickOnElementUsingJS(cbSelectTasks, "Select Tasks chkbox");
	}
	
	/**
	 * Description  Method to click task tab
	 * @author Sajal jain
	 * 
	 */
	public void clkTasksTab() {
		WebActionUtil.waitForElement(tabTasks, "Task Tab", 45);
		WebActionUtil.clickOnElementUsingJS(tabTasks, "Task Tab");
	
	}

	/**
	 * Description  Method to click open tasks tab
	 * @author Sajal jain
	 * 
	 */
	public void clkOpenTasksTab() {
		WebActionUtil.waitForElement(tabOpenTasks, "Open Tasks Tab", 45);
		WebActionUtil.clickOnElementUsingJS(tabOpenTasks, "Open Tasks Tab");
	
	}

	/**
	 * Description  Method to enter Project Name
	 * @author Sajal jain
	 * @param projectName
	 */
	public void setProjectName(String projectName) {
		WebActionUtil.waitForElement(txtProjectName, "Project Name Text Box", 25);
		WebActionUtil.typeText(txtProjectName, projectName, "Project Name Text Box");
		
		
	}

	/**
	 * Description  Method to enter task Name
	 * @author Sajal jain
	 * @param taskName
	 */
	public void setTaskName(String taskName) {
		WebActionUtil.waitForElement(txtTaskName, "Task Name Text Box", 25);
		WebActionUtil.typeText(txtTaskName, taskName, "Task Name Text Box");
		
	}

	/**
	 * Description  Method to enter Customer Name
	 * @author Sajal jain
	 * @param customerName
	 */
	public synchronized void setCustomerName(String customerName) {
		WebActionUtil.waitForElement(txtCustomerName, "Customer Name Text Box", 25);
		WebActionUtil.typeText(txtCustomerName, customerName, "Customer Name Text Box");
		
}
	
}
