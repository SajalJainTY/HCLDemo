package com.tyss.actitime.pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.tyss.acttime.util.WebActionUtil;
import com.tyss.acttime.util.commonutils.ExcelUtil;

/**
 * Description This class has the implementations of the Projects And Customers
 * related methods
 * 
 * @author Sajal jain
 */
public class ProjectsAndCustomers_Page {

	public WebDriver driver;
	public WebActionUtil WebActionUtil;
	public long ETO = 10;

	public ProjectsAndCustomers_Page(WebDriver driver, long ETO, WebActionUtil WebActionUtil) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.WebActionUtil = WebActionUtil;
		this.ETO = ETO;
	}

	/* Create New Customer Button */
	@FindBy(id = "projectPopup_newCustomerNameField")
	private WebElement txtCustomerName;
	/* Project Name Text Box */
	@FindBy(id = "projectPopup_projectNameField")
	private WebElement txtProjectName;
	/* Projects and Customers Tab */
	@FindBy(xpath = "//a[text()='Projects & Customers']")
	private WebElement tabProjectsandCustomers;
	/* Create Project Button */
	@FindBy(id = "projectPopup_commitBtn")
	private WebElement btnCreateProject;
	/* New Create Project Button */
	@FindBy(id = "createProjectDiv")
	private WebElement btnNewCreateProject;
	/* New Create Customer Button */
	@FindBy(id = "createCustomerDiv")
	private WebElement btnNewCreateCustomer;
	/* Create Customer Button */
	@FindBy(xpath = "(//span[text()='Create Customer'])[1]")
	private WebElement btnCreateCustomer;
	/* Select Customer Drop Down */
	@FindBy(xpath = "//button[text()='-- Please Select Customer to Add Project for  --']")
	private WebElement ddSelectCustomer;
	/* New Customer Drop Down */
	@FindBy(xpath = "//a[text()='-- New Customer --']")
	private WebElement sddNewCustomer;
	/* All Active Customers Drop Down */
	@FindBy(name = "selectedCustomer")
	private WebElement ddAllActiveCustomers;
	/* Show Button */
	@FindBy(className = "showCustomersButton")
	private WebElement btnShow;
	/* Enter Customer Name TextBox */
	@FindBy(id = "customerLightBox_nameField")
	private WebElement txtEnterCustomerName;
	/* Save Changes Button */
	@FindBy(id = "customerLightBox_commitBtn")
	private WebElement btnSaveChanges;
	/* Archive Selected Button */
	@FindBy(xpath = "//input[@value='Archive Selected']")
	private WebElement btnArchiveSelected;
	/* Customer Project CheckBox */
	@FindBy(xpath = "//input[contains(@name,'customerById')]")
	private WebElement cbCustomerProject;
	/* Delete Button */
	@FindBy(id = "customerLightBox_deleteBtn")
	private WebElement btnDelete;

	/**
	 * Description Method clicks on Delete Button
	 * 
	 * @author Sajal jain
	 * 
	 */
	public synchronized void clkDeleteBtn() {
		WebActionUtil.waitForElement(btnDelete, "Delete Button", 20);
		WebActionUtil.clickOnWebElement(btnDelete, "Delete Button", "Unable to Click on Delete Button");
	}

	/**
	 * Description Method clicks on Customer Project Checkbox
	 * 
	 * @author Sajal jain
	 * 
	 */
	public synchronized void clkCustomerProjectCb() {
		WebActionUtil.waitForElement(cbCustomerProject, "Customer Project Checkbox", 20);
		WebActionUtil.clickOnWebElement(cbCustomerProject, "Customer Project Checkbox",
				"Unable to Click on Customer Project Checkbox");
	}

	/**
	 * Description Method clicks on Archive Selected Button
	 * 
	 * @author Sajal jain
	 * @throws InterruptedException
	 * 
	 */
	public synchronized void clkArchiveSelectedBtn() throws InterruptedException {
		WebActionUtil.waitForElement(btnArchiveSelected, "Archive Selected Button", 20);
		WebActionUtil.clickOnWebElement(btnArchiveSelected, "Archive Selected Button",
				"Unable to Click on Archive Selected Button");
		Thread.sleep(5000);
		WebActionUtil.driver.switchTo().alert().accept();
	}

	/**
	 * Description Method clicks on Customer/Project link
	 * 
	 * @author Sajal jain
	 * @param customerProject
	 */
	public synchronized void clkCustomerProjectLnk(String customerProject) {
		WebActionUtil.waitForElement(driver.findElement(By.xpath("//a[contains(text(),'" + customerProject + "')]")),
				"Customer Project link", 20);
		WebElement lnkCustomerProject = driver.findElement(By.xpath("//a[contains(text(),'" + customerProject + "')]"));
		WebActionUtil.waitForElement(lnkCustomerProject, "Customer Project link", 20);
		WebActionUtil.clickOnWebElement(lnkCustomerProject, "Customer Project link",
				"Unable to Click on Customer Project link");
	}

	/**
	 * Description Method clicks on Save Changes Button
	 * 
	 * @author Sajal jain
	 * 
	 */
	public synchronized void clkSaveChangesBtn() {
		WebActionUtil.waitForElement(btnSaveChanges, "Save Changes Button", 20);
		WebActionUtil.clickOnWebElement(btnSaveChanges, "Save Changes Button",
				"Unable to Click on Save Changes Button");
	}

	/**
	 * Description Method to enter New Customer Name
	 * 
	 * @author Sajal jain
	 * @param newCustomerName
	 * 
	 */
	public synchronized void setNewCustomerName(String newCustomerName) {
		WebActionUtil.waitForElement(txtEnterCustomerName, "New Customer Name Text Box", 25);
		WebActionUtil.typeText(txtEnterCustomerName, newCustomerName, "New Customer Name Text Box");

	}

	/**
	 * Description Method clicks on Show Button
	 * 
	 * @author Sajal jain
	 * 
	 */
	public synchronized void clkShowBtn() {
		WebActionUtil.waitForElement(btnShow, "Show Button", 20);
		WebActionUtil.clickOnWebElement(btnShow, "Show Button", "Unable to Click on Show Button");
	}

	/**
	 * Description Method to select existing Customer from drop down
	 * 
	 * @author Sajal jain
	 * @param customerName
	 */
	public void selectCustomerdd(String customerName) {
		WebActionUtil.waitForElement(ddAllActiveCustomers, "Select Customer Dropdown", 45);
		WebActionUtil.selectByText(ddAllActiveCustomers, customerName);
	}

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
	 * Description Method clicks on Create Customer Button
	 * 
	 * @author Sajal jain
	 * 
	 */
	public synchronized void clkCreateCustomerBtn() {
		WebActionUtil.waitForElement(btnCreateCustomer, "Create Customer Button", 20);
		WebActionUtil.clickOnWebElement(btnCreateCustomer, "Create Customer Button",
				"Unable to Click on Create Customer Button");
	}

	/**
	 * Description Method clicks on New Create Customer Button
	 * 
	 * @author Sajal jain
	 * 
	 */
	public synchronized void clkNewCreateCustomerBtn() {
		WebActionUtil.waitForElement(btnNewCreateCustomer, "New Create Customer Button", 20);
		WebActionUtil.clickOnWebElement(btnNewCreateCustomer, "New Create Customer Button",
				"Unable to Click on New Create Customer Button");
	}

	/**
	 * Description Method clicks on New Create Project Button
	 * 
	 * @author Sajal jain
	 * 
	 */
	public synchronized void clkNewCreateProjectBtn() {
		WebActionUtil.waitForElement(btnNewCreateProject, "New Create Project Button", 20);
		WebActionUtil.clickOnWebElement(btnNewCreateProject, "New Create Project Button",
				"Unable to Click on New Create Project Button");
	}

	/**
	 * Description Method clicks on Create Project Button
	 * 
	 * @author Sajal jain
	 * 
	 */
	public synchronized void clkCreateProjectBtn() {
		WebActionUtil.waitForElement(btnCreateProject, "Create Project Button", 20);
		WebActionUtil.clickOnWebElement(btnCreateProject, "Create Project Button",
				"Unable to Click on Create Project Button");
	}

	/**
	 * Description Method to enter Project Name
	 * 
	 * @author Sajal jain
	 * @param projectName
	 * 
	 */
	public synchronized void setProjectName(String projectName) {
		WebActionUtil.waitForElement(txtProjectName, "Project Name Text Box", 25);
		WebActionUtil.typeText(txtProjectName, projectName, "Project Name Text Box");

	}

	/**
	 * Description Method to enter Customer Name
	 * 
	 * @author Sajal jain
	 * @param customerName
	 */
	public synchronized void setCustomerName(String customerName) {
		WebActionUtil.waitForElement(txtCustomerName, "Customer Name Text Box", 25);
		WebActionUtil.typeText(txtCustomerName, customerName, "Customer Name Text Box");

	}

}
