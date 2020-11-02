package com.tyss.actitime.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.tyss.acttime.util.WebActionUtil;

/**
 * Description This class has the implementations of the Time Track related
 * methods
 * 
 * @author Sajal jain
 */
public class TimeTrack_Page {
	public WebDriver driver;
	public WebActionUtil WebActionUtil;
	public long ETO = 10;

	public TimeTrack_Page(WebDriver driver, long ETO, WebActionUtil WebActionUtil) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.WebActionUtil = WebActionUtil;
		this.ETO = ETO;
	}

	/* Project name text box */
	@FindBy(id = "createTasksPopup_newProject")
	private WebElement txtProjectName;

	/* Customer name text box */
	@FindBy(css = "input[placeholder='Enter Customer Name']")
	private WebElement txtCustomerName;

	/* Task name text box */
	@FindBy(css = "input[placeholder='Enter task name']")
	private WebElement txtTaskName;

	/* create task button */
	@FindBy(id = "createTasksPopup_commitBtn")
	private WebElement btnCreateTasks;

	/* time track tab */
	@FindBy(xpath = "//div[text()='TIME-TRACK']")
	private WebElement tabTimeTrack;

	/* Select customer drop down */
	@FindBy(xpath = "//button[text()='- Select Customer -']")
	private WebElement ddSelectCustomer;

	/* select New Customer in drop down */
	@FindBy(xpath = "//a[text()='- New Customer -']")
	private WebElement sddNewCustomer;

	/* new tab */
	@FindBy(xpath = "//span[text()='New']")
	private WebElement tabNew;

	
	/**
	 * Description Method to select New Customer from drop down
	 * 
	 * @author Sajal jain
	 * 
	 */
	public void selectNewCustomerdd() {
		WebActionUtil.waitForElement(ddSelectCustomer, "Select Customer Dropdown", 45);
		Actions act = new Actions(driver);
		act.moveToElement(ddSelectCustomer).perform();
		WebActionUtil.clickOnElementUsingJS(ddSelectCustomer, "Select Customer Dropdown");
		WebActionUtil.waitForElement(sddNewCustomer, "New Customer drop down", 45);
		WebActionUtil.clickOnElementUsingJS(sddNewCustomer, "New Customer drop down");
	}

	/**
	 * Description Method to click on new tab
	 * 
	 * @author Sajal jain
	 * 
	 */
	public void clkNewTab() {
		WebActionUtil.waitForElement(tabNew, "New  Tab", 45);
		WebActionUtil.clickOnElementUsingJS(tabNew, "New Tab");

	}

	/**
	 * Description Method to click time track tab
	 * 
	 * @author Sajal jain
	 * 
	 */
	public void clkTimeTrackTab() {
		WebActionUtil.waitForElement(tabTimeTrack, "Time Track  Tab", 45);
		WebActionUtil.clickOnElementUsingJS(tabTimeTrack, "Time Track Tab");
	}

	/**
	 * Description Method to click on create tasks button
	 * 
	 * @author Sajal jain
	 * 
	 */
	public void clkCreateTasksBtn() {
		WebActionUtil.waitForElement(btnCreateTasks, "Create Task  Button", 5);
		WebActionUtil.clickOnWebElement(btnCreateTasks, "Create Task Button",
				"Unable to click on create Project Button");
	}

	/**
	 * Description Method to enter Project Name
	 * 
	 * @author Sajal jain
	 * @param projectName
	 */
	public void setProjectName(String projectName) {
		WebActionUtil.waitForElement(txtProjectName, "Project Name Text Box", 25);
		WebActionUtil.typeText(txtProjectName, projectName, "Project Name Text Box");

	}

	/**
	 * Description Method to enter task Name
	 * 
	 * @author Sajal jain
	 * @param taskName
	 */
	public void setTaskName(String taskName) {
		WebActionUtil.waitForElement(txtTaskName, "Task Name Text Box", 25);
		WebActionUtil.typeText(txtTaskName, taskName, "Task Name Text Box");
	}

	/**
	 * Description Method to enter Customer Name
	 * 
	 * @author Sajal jain
	 * @param customerName
	 */
	public void setCustomerName(String customerName) {
		WebActionUtil.waitForElement(txtCustomerName, "Customer Name Text Box", 25);
		WebActionUtil.typeText(txtCustomerName, customerName, "Customer Name Text Box");

	}

}
