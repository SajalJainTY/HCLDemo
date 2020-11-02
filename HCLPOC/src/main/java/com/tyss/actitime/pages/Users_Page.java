package com.tyss.actitime.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.tyss.acttime.util.WebActionUtil;

/**
 * Description This class has the implementations of the Users related methods
 * 
 * @author Sajal jain
 */
public class Users_Page {
	public WebDriver driver;
	public WebActionUtil WebActionUtil;
	public long ETO = 10;

	public Users_Page(WebDriver driver, long ETO, WebActionUtil WebActionUtil) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.WebActionUtil = WebActionUtil;
		this.ETO = ETO;
	}

	/* Users Tab */
	@FindBy(xpath = "//div[text()='USERS']")
	private WebElement tabUsers;
	/* PTO Setting Tab */
	@FindBy(xpath = "//div[contains(text(),'PTO Settings')]")
	private WebElement tabPTOSettings;
	/* New User Button */
	@FindBy(xpath = "//div[text()='Add User']")
	private WebElement btnAddUser;
	/* First Name Text Box */
	@FindBy(id = "userDataLightBox_firstNameField")
	private WebElement txtFirstName;
	/* Last Name Text Box */
	@FindBy(id = "userDataLightBox_lastNameField")
	private WebElement txtLastName;
	/* Email Text Box */
	@FindBy(id = "userDataLightBox_emailField")
	private WebElement txtEmail;
	/* Middle Name Text Box */
	@FindBy(id = "userDataLightBox_middleNameField")
	private WebElement txtMI;
	/* Department Drop Down */
	@FindBy(xpath = "(//div[@class='selectorPlaceholder'])[2]")
	private WebElement ddDepartment;
	/* Success Message 1 */
	@FindBy(xpath = "//div[@class='invitationImg']/following-sibling::div[1]")
	private WebElement txtSuccessMessage1;
	/* Success Message 2 */
	@FindBy(xpath = "//div[@class='invitationImg']/following-sibling::div[2]")
	private WebElement txtSuccessMessage2;
	/* Close Tab */
	@FindBy(xpath = "//span[text()='Close']")
	private WebElement tabClose;
	/* Save & Send Invitation Button */
	@FindBy(xpath = "//div[text()='Save & Send Invitation']")
	private WebElement btnSaveAndSendInvitation;
	/* Username Text Box */
	@FindBy(id = "userDataLightBox_usernameField")
	private WebElement txtUsername;
	/* Password Text Box */
	@FindBy(id = "userDataLightBox_passwordField")
	private WebElement txtPassword;
	/* Retype Password Text Box */
	@FindBy(id = "userDataLightBox_passwordCopyField")
	private WebElement txtRetypePassword;
	/* Time zone group Drop down */
	@FindBy(id = "userDataLightBox_timeZoneGroupSelectorPlaceholder")
	private WebElement ddTimeZoneGroup;
	/* Apply Template Drop down */
	@FindBy(id = "userDataLightBox_permissionsTemplatesMenuButton")
	private WebElement ddApplyTemplate;
	/* Create User Button */
	@FindBy(id = "userDataLightBox_commitBtn")
	private WebElement btnCreateUser;
	/* Department Filter Drop down */
	@FindBy(xpath = "//div[@id='filterByDepartmentButton']/table")
	private WebElement ddDepartmentFilter;
	/* Show selected users Radio Button */
	@FindBy(xpath = "//b[text()='Show Selected Users']/../../img")
	private WebElement rbShowSelectedUsers;
	/* Apply Button */
	@FindBy(xpath = "(//span[text()='Apply'])[2]/..")
	private WebElement btnApply;
	/* Show Disabled Accounts Check box */
	@FindBy(id = "showDisabledUsersChBox")
	private WebElement cbShowDisabledAccounts;
	/* Start Typing Name Text Box */
	@FindBy(xpath = "//input[@class='filterFieldInput inputFieldWithPlaceholder']")
	private WebElement txtStartTypingName;
	/* Delete User Button */
	@FindBy(id = "userDataLightBox_deleteBtn")
	private WebElement btnDeleteUser;

	/**
	 * Description Method clicks on Delete User Button
	 * 
	 * @author Sajal jain
	 * 
	 */
	public synchronized void clkDeleteUserBtn() {
		WebActionUtil.waitForElement(btnDeleteUser, "Delete User Button", 45);
		WebActionUtil.clickOnElementUsingJS(btnDeleteUser, "Delete User Button");

	}

	/**
	 * Description Method enters Start Typing Name TextBox
	 * 
	 * @author Sajal jain
	 * @param typingName
	 */
	public synchronized void setStartTypingName(String typingName) {
		WebActionUtil.waitForElement(txtStartTypingName, "Start Typing Name TextBox", 45);
		WebActionUtil.typeText(txtStartTypingName, typingName, "Start Typing Name TextBox");
	}

	/**
	 * Description Method clicks on User link
	 * 
	 * @author Sajal jain
	 * @param user
	 */
	public synchronized void clkUserLnk(String user) {
		try {
			WebElement lnkUser = driver.findElement(By.xpath("//span[text()='" + user + "']"));
			WebActionUtil.scrollToElement(lnkUser, "User Link");
			WebActionUtil.waitForElement(lnkUser, "User Link", 45);
			WebActionUtil.clickOnElementUsingJS(lnkUser, "User Link");
		} catch (Exception e) {

		}
	}

	/**
	 * Description Method clicks on Show Disabled Accounts Checkbox
	 * 
	 * @author Sajal jain
	 * 
	 */
	public synchronized void clkShowDisabledAccountsCb() {
		WebActionUtil.scrollToElement(cbShowDisabledAccounts, "Show Disabled Accounts Checkbox");
		WebActionUtil.waitForElement(cbShowDisabledAccounts, "Show Disabled Accounts Checkbox", 45);
		WebActionUtil.clickOnElementUsingJS(cbShowDisabledAccounts, "Show Disabled Accounts Checkbox");
	}

	/**
	 * Description Method clicks on Apply Button
	 * 
	 * @author Sajal jain
	 * 
	 */
	public synchronized void clkApplyBtn() {
		WebActionUtil.scrollToElement(btnApply, "Apply Button");
		WebActionUtil.waitForElement(btnApply, "Apply Button", 45);
		WebActionUtil.clickOnElementUsingJS(btnApply, "Apply Button");
	}

	/**
	 * Description Method to Click Department Filter Drop down
	 * 
	 * @author Sajal jain
	 * @param departmentFilter
	 */
	public void selectDepartmentFilterDD(String departmentFilter) {
		WebActionUtil.isElementVisible(ddDepartmentFilter, "Department Filter Drop down");
		WebActionUtil.isElementClickable(ddDepartmentFilter, "Department Filter Drop down");
		WebActionUtil.waitForElement(ddDepartmentFilter, "Department Filter Drop down", 45);
		WebActionUtil.scrollToElement(ddDepartmentFilter, "Department Filter Drop down");
		WebActionUtil.clickOnElementUsingJS(ddDepartmentFilter, "Department Filter Drop down");
		WebActionUtil.clickOnWebElement(rbShowSelectedUsers, "Show Selected Users Radio Button",
				"Unable to click on Show Selected Users Radio Button");

	}

	/**
	 * Description Method to Click Time Zone Group Check box in drop down
	 * 
	 * @author Sajal jain
	 * @param timeZoneGroups
	 */
	public void clkTimeZoneGroupsCb(String timeZoneGroups) {
		WebElement cbTimeZoneGroup = driver
				.findElement(By.xpath("//label[text()='" + timeZoneGroups + "']/preceding-sibling::img"));
		System.out.println(cbTimeZoneGroup.isSelected());
		if (!cbTimeZoneGroup.isSelected()) {
			WebActionUtil.isElementClickable(cbTimeZoneGroup, "Time Zone Group Check box");
			WebActionUtil.waitForElement(cbTimeZoneGroup, "Time Zone Group Check box", 45);
			WebActionUtil.clickOnElementUsingJS(cbTimeZoneGroup, "Time Zone Group Check box");
		}
	}

	/**
	 * Description Method clicks on Create User Button
	 * 
	 * @author Sajal jain
	 * 
	 */
	public synchronized void clkCreateUserBtn() {
		WebActionUtil.scrollToElement(btnCreateUser, "Create User Button");
		WebActionUtil.waitForElement(btnCreateUser, "Create User Button", 45);
		WebActionUtil.clickOnElementUsingJS(btnCreateUser, "Create User Button");
	}

	/**
	 * Description Method to select Template Drop down
	 * 
	 * @author Sajal jain
	 * @param applyTemplate
	 */
	public void selectApplyTemplateDD(String applyTemplate) {
		WebActionUtil.scrollToElement(ddApplyTemplate, "Apply Template Drop down");
		WebActionUtil.waitForElement(ddApplyTemplate, "Apply Template Drop down", 45);
		WebActionUtil.clickOnWebElement(ddApplyTemplate, "Apply Template Drop down",
				"Unable to click on Apply Template Drop down");
		WebElement otnApplyTemplate = driver.findElement(By.xpath("//div[text()='" + applyTemplate + "']"));
		WebActionUtil.isElementClickable(otnApplyTemplate, "Select Apply Template Drop down");
		WebActionUtil.waitForElement(otnApplyTemplate, "Select Apply Template Drop down", 45);
		WebActionUtil.clickOnElementUsingJS(otnApplyTemplate, "Select Apply Template Drop down");
	}

	/**
	 * Description Method to select Time Zone Group Drop down
	 * 
	 * @author Sajal jain
	 * @param timeZoneGroup
	 */
	public void selectTimeZoneGroupDD(String timeZoneGroup) {
		WebActionUtil.waitForElement(ddTimeZoneGroup, "Time Zone Group Drop down", 45);
		WebActionUtil.clickOnWebElement(ddTimeZoneGroup, "Time Zone Group Drop down",
				"Unable to click on Time Zone Group Drop down");
		WebElement otnTimeZoneGroup = driver.findElement(By.xpath("//div[text()='" + timeZoneGroup + "']/.."));
		WebActionUtil.action.moveToElement(otnTimeZoneGroup).perform();
		WebActionUtil.isElementClickable(otnTimeZoneGroup, "Select Time Zone Group Drop down");
		WebActionUtil.waitForElement(otnTimeZoneGroup, "Select Time Zone Group Drop down", 45);
		WebActionUtil.clickOnElementUsingJS(otnTimeZoneGroup, "Select Time Zone Group Drop down");
	}

	/**
	 * Description Method enters Retype Password TextBox
	 * 
	 * @author Sajal jain
	 * @param password
	 */
	public synchronized void setRetypePassword(String password) {
		WebActionUtil.waitForElement(txtRetypePassword, "Retype Password Text Box", 45);
		WebActionUtil.typeText(txtRetypePassword, password, "Retype Password Text Box");
	}

	/**
	 * Description Method enters Password TextBox
	 * 
	 * @author Sajal jain
	 * @param password
	 */
	public synchronized void setPassword(String password) {
		WebActionUtil.waitForElement(txtPassword, "Password Text Box", 45);
		WebActionUtil.typeText(txtPassword, password, "Password Text Box");
	}

	/**
	 * Description Method enters Username TextBox
	 * 
	 * @author Sajal jain
	 * @param username
	 */
	public synchronized void setUsername(String username) {
		WebActionUtil.waitForElement(txtUsername, "Username Text Box", 45);
		WebActionUtil.typeText(txtUsername, username, "Username Text Box");
	}

	/**
	 * Description Method clicks on Close Tab
	 * 
	 * @author Sajal jain
	 * 
	 */
	public synchronized void clkCloseTab() {
		WebActionUtil.waitForElement(tabClose, "Close Tab", 45);
		WebActionUtil.clickOnElementUsingJS(tabClose, "Close Tab");
	}

	/**
	 * Description Method Validate the Success Message 2
	 * 
	 * @author Sajal jain
	 * @param expectedTextMailID
	 */
	public synchronized void validateSuccessMessage2(String expectedTextMailID) {
		expectedTextMailID = "The invitation has been sent to the user's email address: " + expectedTextMailID;
		WebActionUtil.waitForElement(txtSuccessMessage2, "Success Message2", 45);
		WebActionUtil.verifytext(expectedTextMailID, txtSuccessMessage2, "Success Message2");
	}

	/**
	 * Description Method Validate the Success Message 1
	 * 
	 * @author Sajal jain
	 * @param firstName
	 */
	public synchronized void validateSuccessMessage1(String firstName, String lastName) {
		String expectedText = "Account for " + firstName + " " + lastName + " has been created.";
		WebActionUtil.waitForElement(txtSuccessMessage1, "Success Message1", 45);
		WebActionUtil.verifytext(expectedText, txtSuccessMessage1, "Success Message1");
	}

	/**
	 * Description Method selects the Department Name from Drop down
	 * 
	 * @author Sajal jain
	 * @param departmentName
	 */
	public synchronized void selectDepartmentName(String departmentName) {
		WebActionUtil.waitForElement(ddDepartment, "Department Drop down", 45);
		// WebActionUtil.clickOnElementUsingJS(ddDepartment, "Department Drop down");
		WebActionUtil.clickOnWebElement(ddDepartment, "Department Drop down",
				"Unable to Click on Department Drop down");
		driver.findElement(By.xpath("//div[@class='item selected']/..//div[text()='" + departmentName + "']")).click();
	}

	/**
	 * Description Method clicks on Save And Send Invitation Button
	 * 
	 * @author Sajal jain
	 * 
	 */
	public synchronized void clkSaveAndSendInvitationBtn() {
		WebActionUtil.waitForElement(btnSaveAndSendInvitation, "Save And Send Invitation Button", 45);
		WebActionUtil.clickOnElementUsingJS(btnSaveAndSendInvitation, "Save And Send Invitation Button");
	}

	/**
	 * Description Method enters Middle Name
	 * 
	 * @author Sajal jain
	 * @param middleName
	 */
	public synchronized void setMI(String middleName) {
		WebActionUtil.waitForElement(txtMI, "Middle Name Text Box", 45);
		WebActionUtil.typeText(txtMI, middleName, "Middle Name Text Box");
	}

	/**
	 * Description Method enters First Name
	 * 
	 * @author Sajal jain
	 * @param firstName
	 */
	public synchronized void setFirstName(String firstName) {
		WebActionUtil.waitForElement(txtFirstName, "First Name Text Box", 45);
		WebActionUtil.isElementVisible(txtFirstName, "First Name text box");
		WebActionUtil.isElementClickable(txtFirstName, "First Name Text Box");
		WebActionUtil.typeText(txtFirstName, firstName, "First Name Text Box");
	}

	/**
	 * Description Method enters Last Name
	 * 
	 * @author Sajal jain
	 * @param lastName
	 */
	public synchronized void setLastName(String lastName) {
		WebActionUtil.waitForElement(txtLastName, "Last Name Text Box", 45);
		WebActionUtil.typeText(txtLastName, lastName, "Last Name Text Box");
	}

	/**
	 * Description Method enters Email
	 * 
	 * @author Sajal jain
	 * @param email
	 */
	public synchronized void setEmail(String email) {
		WebActionUtil.waitForElement(txtEmail, "Email Text Box", 45);
		WebActionUtil.typeText(txtEmail, email, "Email Text Box");
	}

	/**
	 * Description Method clicks on PTO Settings Tab
	 * 
	 * @author Sajal jain
	 * 
	 */
	public synchronized void clkAddUserBtn() {
		WebActionUtil.waitForElement(btnAddUser, "New User Button", 45);
		WebActionUtil.clickOnElementUsingJS(btnAddUser, "New User Button");
	}

	/**
	 * Description Method clicks on Control On/Off Button
	 * 
	 * @author Sajal jain
	 * 
	 */
	public synchronized void clkToggleControlOnOffBtn() {
		/*
		 * WebActionUtil.waitForElement(toggleBtnControlOnOff, "PTO Settings Tab", 45);
		 * WebActionUtil.clickOnElementUsingJS(toggleBtnControlOnOff,
		 * "PTO Settings Tab");
		 */
		/*
		 * List<WebElement> totalControl =
		 * driver.findElements(By.xpath("//td[@class='ptoControlCell on']")); for(int
		 * i=0;i<totalControl.size();i++) {
		 * driver.findElement(By.xpath("(//td[@class='ptoControlCell on'])["+i+"]")).
		 * click(); }
		 */

		List<WebElement> totalControl = driver.findElements(By.xpath("//td[@class='ptoControlCell on']"));
		for (int i = 1; i < totalControl.size(); i++) {
			try {
				driver.findElement(By.xpath("(//td[@class='ptoControlCell on'])[" + i + "]")).click();
				WebActionUtil.waitForElement(
						driver.findElement(By.xpath("(//td[@class='ptoControlCell on'])[" + (i + 1) + "]")),
						"OnOffToggle", 30);
				// Thread.sleep(2000);
			} catch (Exception e) {
				break;
			}
		}
	}

	/**
	 * Description Method clicks on PTO Settings Tab
	 * 
	 * @author Sajal jain
	 */
	public synchronized void clkPTOSettingsTab() {
		WebActionUtil.waitForElement(tabPTOSettings, "PTO Settings Tab", 45);
		WebActionUtil.clickOnElementUsingJS(tabPTOSettings, "PTO Settings Tab");
	}

	/**
	 * Description Method clicks on Users Tab
	 * 
	 * @author Sajal jain
	 * 
	 */
	public synchronized void clkUsersTab() {
		WebActionUtil.waitForElement(tabUsers, "Users Tab", 45);
		WebActionUtil.clickOnElementUsingJS(tabUsers, "Users Tab");
	}

}
