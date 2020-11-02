package com.tyss.actitime.pages;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.tyss.acttime.util.WebActionUtil;
import com.tyss.acttime.util.commonutils.ExcelUtil;

/**
 * Description This class has the implementations of the Task related methods
 * @author shreya.u@testyantra.com
 */
public class Task_Page  {


	public WebDriver driver;
	public WebActionUtil WebActionUtil;
	public long ETO = 10;

	public Task_Page(WebDriver driver, long ETO, WebActionUtil WebActionUtil){
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.WebActionUtil = WebActionUtil;
		this.ETO = ETO;
	}
	
	/* Task Tab */
	@FindBy(xpath = "//div[text()='Reports']/following-sibling::img")
	private WebElement reportsTab;
	
	/* Task Tab */
	@FindBy(xpath = "//div[text()='Tasks']/following-sibling::img")
	private WebElement tabTasks;

	/* Create New Customer Button */
	@FindBy(xpath = "//input[@type='button' and @value='Create New Customer']")
	private WebElement btnCreateNewCustomer;

	/* Create New Customer Button */
	@FindBy(xpath = "//td[contains(text(),' Customer Name:')]/following-sibling::td/child::input[@type='text']")
	private WebElement txtcustomerName;

	/* Add more projects Check box*/
	@FindBy(id = "add_more_customers_action")
	private WebElement cbAddMoreCustomers;
	
	/* Create New Project Button */
	@FindBy(xpath = "//input[@value='Create New Project']")
	private WebElement btnCreateNewProject;
	
	/* Create Customer Button */
	@FindBy(xpath = "//input[@value='Create Customer']")
	private WebElement btnCreateCustomer;
	
	/* Customer Drop Down*/
	@FindBy(xpath = "//select[@name='customerId']")
	private WebElement ddcustomer;
	
	/*Project Name Text Box*/
	@FindBy(xpath = "//td[contains(text(),'Project Name:')]/following-sibling::td/child::input[@type='text']")
	private WebElement txtProjectName;
	
	/* Add more projects Check box*/
	@FindBy(id = "add_more_projects")
	private WebElement cbAddMoreProjects;
	
	/* Add more projects Check box*/
	@FindBy(xpath = "//input[@value='Create Project']")
	private WebElement btnCreateProject;
	
	/* Projects and Customers Tab*/
	@FindBy(xpath = "//a[text()='Projects & Customers']")
	private WebElement tabProjectsandCustomers;
	

	
	/**
	 * Description  Method clicks on Task Tab
	 * @author Shreya Ugavekar
	 * 
	 */
	public synchronized void clkTaskTab() {
		WebActionUtil.waitForElement(tabTasks, "Task  Tab", 45);
		WebActionUtil.clickOnElementUsingJS(tabTasks, "Task Tab");
	

	}
	
	
	/**
	 * Description Method clicks on Reports Tab
	 * @author Shreya Ugavekar
	 * 
	 */
	public synchronized void clkReportsTab() {
		WebActionUtil.waitForElement(reportsTab, "Task Tab", 45);
		WebActionUtil.clickOnWebElement(reportsTab, "Task Tab", "Unable to Click on Task Tab");

	}
	/**
	 * Description Method  clicks  on Projects And Customers Tab
	 * @author Shreya Ugavekar
	 * 
	 */
	public synchronized void clkProjectsAndCustomersTab() {
		WebActionUtil.waitForElement(tabProjectsandCustomers, "Projects And Customers Tab", 20);
		WebActionUtil.clickOnWebElement(tabProjectsandCustomers, "Task Tab", "Unable to Click on Projects and Customers Tab");

	}

	/**
	 * Description Method clicks on Create New Customer
	 * @author Shreya Ugavekar
	 * 
	 */
	public synchronized void clkCreateNewCustomerButton() {
		WebActionUtil.waitForElement(btnCreateNewCustomer, "Create New Customer Button", 5);
		WebActionUtil.clickOnWebElement(btnCreateNewCustomer, "Create New Customer Button",
				"Unable to click on create New Cutomer Button");

	}

	/**
	 * Description  Method to enter Customer Name
	 * @author Shreya Ugavekar
	 * 
	 */
	public synchronized void setCustomerName(String customerName) {
		WebActionUtil.waitForElement(txtcustomerName, "Customer Name Text Box", 25);
		WebActionUtil.typeText(txtcustomerName, customerName, "Customer Name Text Box");
		
	}

	/**
	 * Description  Method to Click Add More Customers Check Box
	 * @author Shreya Ugavekar
	 * 
	 */
	public synchronized void clkAddMoreCustomersCheckBox() {
		WebActionUtil.scrollToElement(cbAddMoreCustomers, "Add More Customers CheckBox");
		WebActionUtil.waitForElement(cbAddMoreCustomers, "Add More Customers CheckBox", 25);
		WebActionUtil.clickCheckBox(cbAddMoreCustomers, "Add More Customers Check Box");

	}
	
	/** Description Method  clicks on Create New Project
	 * @author Shreya Ugavekar
	 * 
	 */
	public synchronized void clkCreateNewProjectButton() {
		WebActionUtil.waitForElement(btnCreateNewProject, "Create New Project Button", 5);
		WebActionUtil.clickOnWebElement(btnCreateNewProject, "Create New Project Button",
				"Unable to click on create New Project Button");

	}
	
	/**
	 * Description Method selects the Customer Name from Drop down 
	 * @author Shreya Ugavekar
	 * 
	 */
	public synchronized void selectCustomerName(String CustomerName) {
		WebActionUtil.waitForElement(ddcustomer, "Customer Drop down", 5);
		WebActionUtil.selectByText(ddcustomer, CustomerName);

	}
	

	/**
	 * Description Method enters Project Name
	 * @author Shreya Ugavekar
	 */
	public synchronized void setProjectName(String projectName) {
		WebActionUtil.waitForElement(txtProjectName, "Project Name Text Box", 5);
		WebActionUtil.typeText(txtProjectName, projectName, "Project Name Text Box");
		
	}
	
	/**
	 * Description Method to Click add more project Check Box
	 * @author Shreya Ugavekar
	 * 
	 */
	public synchronized void clkAddMoreProjectsCheckBox() {
		WebActionUtil.waitForElement(cbAddMoreProjects, "Add More Projects Check Box", 5);
		WebActionUtil.clickCheckBox(cbAddMoreProjects, "Add More Projects Check Box");
			
	}
	
	/**
	 * Description Method  clicks on Create Project Button
	 * @author Shreya Ugavekar
	 * 
	 */
	public synchronized void clkCreateProjectButton() {
		WebActionUtil.waitForElement(btnCreateProject, "Create Project  Button", 5);
		WebActionUtil.clickOnWebElement(btnCreateProject, "Create Project Button",
				"Unable to click on create Project Button");

	}
	/**
	 * Description Method to clicks on Create Customer Button
	 * @author Shreya Ugavekar
	 *  
	 */
	public synchronized void clkCreateCustomerButton() {
		WebActionUtil.waitForElement(btnCreateCustomer, "Create Customer  Button", 5);
		WebActionUtil.clickOnWebElement(btnCreateCustomer, "Create Customer Button",
				"Unable to click on create Project Button");

	}
	/**
	 * Description Method to create multiple customers
	 * @author Shreya Ugavekar
	 * @param path
	 * @param sheet
	 * @param columnName
	 */
	public void createCustomers(String path,String sheet,String columnName)
	{
		int rowcount=ExcelUtil.getRowCount(path, sheet);
		int colindex=ExcelUtil.getColoumIndex(path, sheet, columnName);
		
		for (int i = 1; i <= rowcount; i++) {
			
			String customerName=ExcelUtil.getCellData(path, sheet, i, colindex) +RandomStringUtils.randomAlphanumeric(2);
			WebActionUtil.clearText(txtcustomerName, "Customer Name Text Box");
			setCustomerName(customerName);
			clkAddMoreCustomersCheckBox();
			
		}
		clkCreateCustomerButton();
	}
	/**
	 * Description Method to create Project
	 * @author Shreya Ugavekar
	 * @param path
	 * @param sheet
	 * @param columnName
	 * 
	 */
	public void createProject(String path,String sheet,String columnName)
	{
		int rowcount=ExcelUtil.getRowCount(path, sheet);
		int colindex=ExcelUtil.getColoumIndex(path, sheet, columnName);
		
		for (int i = 1; i <= rowcount; i++) {
			
			String customerName=ExcelUtil.getCellData(path, sheet, i, colindex) +RandomStringUtils.randomAlphanumeric(2);
			WebActionUtil.clearText(txtcustomerName, "Customer Name Text Box");
			setCustomerName(customerName);
			clkAddMoreCustomersCheckBox();
			
		}
		clkCreateCustomerButton();
	}

}
