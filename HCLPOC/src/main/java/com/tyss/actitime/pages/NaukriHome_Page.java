package com.tyss.actitime.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.Set;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.tyss.acttime.util.WebActionUtil;

/**
 * Description This class has the implementations of the Naukri related methods
 * 
 * @author Sajal jain
 */
public class NaukriHome_Page {
	public WebDriver driver;
	public WebActionUtil WebActionUtil;
	public long ETO = 10;

	public NaukriHome_Page(WebDriver driver, long ETO, WebActionUtil WebActionUtil) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.WebActionUtil = WebActionUtil;
		this.ETO = ETO;
	}

	/* Register For Free Button */
	@FindBy(xpath = "(//button[contains(text(),'Register for free')])[1]")
	private WebElement btnRegisterForFree;
	/* I Am Experienced Button */
	@FindBy(xpath = "//button[contains(text(),'I am Experienced')]")
	private WebElement btnIAmExperienced;
	/* Name Text Box */
	@FindBy(id = "fname")
	private WebElement txtName;
	/* Email Id Text Box */
	@FindBy(id = "email")
	private WebElement txtEmailID;
	/* Create Password Text Box */
	@FindBy(name = "password")
	private WebElement txtCreatePassword;
	/* Mobile Number Text Box */
	@FindBy(name = "number")
	private WebElement txtMobileNumber;
	/* Register Now Button */
	@FindBy(name = "basicDetailSubmit")
	private WebElement btnRegisterNow;
	/* Total Work Experience Years Drop down */
	@FindBy(name = "expYear")
	private WebElement ddTotalWorkExperienceYears;
	/* Total Work Experience Months Drop down */
	@FindBy(name = "expMonth")
	private WebElement ddTotalWorkExperienceMonths;
	/* Current City Drop down */
	@FindBy(css = "input[placeholder='Tell us about your current city']")
	private WebElement ddCurrentCity;
	/* Password Text Box */
	@FindBy(id = "typePassword")
	private WebElement txtPassword;
	/* Submit Button */
	@FindBy(name = "formSubmitted")
	private WebElement btnSubmit1;
	/* Annual Salary Lakhs Drop down */
	@FindBy(name = "salaryLac_0")
	private WebElement ddAnnualSalaryLakhs;
	/* Annual Salary Thousand Drop down */
	@FindBy(name = "salaryThou_0")
	private WebElement ddAnnualSalaryThousand;
	/* Working Since Year Drop down */
	@FindBy(name = "startYear_0")
	private WebElement ddWorkingSinceYear;
	/* Working Since Month Drop down */
	@FindBy(name = "startMonth_0")
	private WebElement ddWorkingSinceMonth;
	/* Duration of Notice Period Drop down */
	@FindBy(name = "noticePeriod")
	private WebElement ddDurationofNoticePeriod;
	/* Skills Drop down */
	@FindBy(name = "keyskills")
	private WebElement ddSkills;
	/* Industry Drop down */
	@FindBy(name = "industry")
	private WebElement ddIndustry;
	/* Functional Area Drop down */
	@FindBy(name = "farea")
	private WebElement ddFunctionalArea;
	/* Role Drop down */
	@FindBy(name = "role")
	private WebElement ddRole;
	/* Highest Qualification Drop down */
	@FindBy(name = "qualification_0")
	private WebElement ddHighestQualification;
	/* Course Drop down */
	@FindBy(name = "course_0")
	private WebElement ddCourse;
	/* Specialization Drop down */
	@FindBy(xpath ="//input[@name='spec_0']/preceding-sibling::span ")
	private WebElement ddSpecialization;
	/* University and College Drop down */
	@FindBy(id = "institute_0")
	private WebElement ddUniversityCollege;
	/* Passing Year Drop down */
	@FindBy(xpath = "//input[@name='passingYear_0']/preceding-sibling::span")
	private WebElement ddPassingYear;
	/*Option Passing Year Drop down*/
	@FindBy(xpath="//input[@name='passingYear_0']/ancestor::div[2]/following-sibling::div")
	private WebElement otnPassingYear;
	/* Submit Button */
	@FindBy(name = "submitProfileComplete")
	private WebElement btnSubmit2;
	/* Current Designation Drop down */
	@FindBy(id = "designation_0")
	private WebElement ddCurrentDesignation;
	/* Current Company Drop down */
	@FindBy(id = "company_0")
	private WebElement ddCurrentCompany;
	/* Add More Education Tab */
	@FindBy(xpath = "//a[contains(text(),'Add more education')]")
	private WebElement tabAddMoreEducation;
	/* Continue Employee Button */
	@FindBy(name = "submitEmploymentDetail")
	private WebElement btnContinueEmployee;
	/* Continue Education Button */
	@FindBy(xpath = "//button[@name='submitEducationDetail']")
	private WebElement btnContinueEducation;
	/* Upload CV Button */
	@FindBy(id = "wdgt-file-upload")
	private WebElement btnUploadCV;
	/* Upload Resume Button */
	@FindBy(name = "uploadCV")
	private WebElement btnUploadResume;
	/* Resume Upload Success Message */
	@FindBy(xpath = "//span[text()='File uploaded successfully']")
	private WebElement msgUploadResume;
	/* CV Upload Success Message */
	@FindBy(xpath = "//span[contains(text(),'CV file name')]")
	private WebElement msgUploadCV;

	/**
	 * Description Method to close Child Browser Tab/Window
	 * 
	 * @author Sajal jain
	 * 
	 */
	public synchronized void closeChildTab() {
		WebActionUtil.mainID = WebActionUtil.driver.getWindowHandle();
		Set<String> allID = WebActionUtil.driver.getWindowHandles();
		for (String id : allID) {
			if (!id.equals(WebActionUtil.mainID)) {
				driver.switchTo().window(id);
				driver.close();
			}
		}
		driver.switchTo().window(WebActionUtil.mainID);
	}

	/**
	 * Description Method validate CV Upload Success Message
	 * 
	 * @author Sajal jain
	 * @param expectedMessage
	 */
	public synchronized void validateCvUploadMessage(String expectedMessage) {
		WebActionUtil.isElementVisible(msgUploadCV, "CV Upload Message");
		WebActionUtil.waitForElement(msgUploadCV, "CV Upload Message", 45);
		try {
			WebActionUtil.info("Getting text from CV Upload Message");
			String actualMessage = msgUploadCV.getText();
			Assert.assertTrue(actualMessage.contains(expectedMessage));
		} catch (Exception e) {
			WebActionUtil.fail("Failed to fetch the text from CV Upload Message");
		}
	}

	/**
	 * Description Method validate Resume Upload Success Message
	 * 
	 * @author Sajal jain
	 * @param expectedMessage
	 */
	public synchronized void validateResumeUploadMessage(String expectedMessage) {
		WebActionUtil.isElementVisible(msgUploadResume, "Resume Upload Message");
		WebActionUtil.waitForElement(msgUploadResume, "Resume Upload Message", 80);
		WebActionUtil.verifytext(expectedMessage, msgUploadResume, "Resume Upload Message");
	}

	/**
	 * Description Method Select file from system for Upload Resume Button
	 * 
	 * @author Sajal jain
	 * @param filePath
	 */
	public synchronized void clkUploadResumeBtn(String filePath) {
		WebActionUtil.scrollToElement(btnUploadResume, "Upload Resume Button");
		WebActionUtil.waitForElement(btnUploadResume, "Upload Resume Button", 80);
		btnUploadResume.sendKeys(filePath);
	}

	/**
	 * Description Method Click on Upload CV button and Select file from system
	 * 
	 * @author Sajal jain
	 * @param filePath
	 */
	public synchronized void clkUploadCVBtn(String filePath) {
		WebActionUtil.waitForElement(btnUploadCV, "Upload CV Button", 45);
		WebActionUtil.clickOnElementUsingJS(btnUploadCV, "Upload CV Button");
		WebActionUtil.clickOnWebElement(btnUploadCV, "Upload CV Button", "Unable to click on Upload CV Button");
		StringSelection path = new StringSelection(filePath);
		Toolkit tools = Toolkit.getDefaultToolkit();
		Clipboard cb = tools.getSystemClipboard();
		cb.setContents(path, null);
		try {
			Robot r = new Robot();
			Thread.sleep(5000);
			r.keyPress(KeyEvent.VK_CONTROL);
			r.keyPress(KeyEvent.VK_V);
			r.keyRelease(KeyEvent.VK_CONTROL);
			r.keyRelease(KeyEvent.VK_V);
			Thread.sleep(2000);
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
		} catch (Exception e) {

		}
	}

	/**
	 * Description Method select Duration of Notice Period Drop down
	 * 
	 * @author Sajal jain
	 * @param durationofNoticePeriod
	 */
	public synchronized void selectDurationofNoticePeriodDD(String durationofNoticePeriod) {
		WebActionUtil.waitForElement(ddDurationofNoticePeriod, "Duration of Notice Period Drop down", 45);
		WebActionUtil.clickOnElementUsingJS(ddDurationofNoticePeriod, "Duration of Notice Period Drop down");
		// WebActionUtil.typeText(ddDurationofNoticePeriod, durationofNoticePeriod,
		// "Duration of Notice Period Drop down");
		WebElement ddDurationofNoticePeriod = driver
				.findElement(By.xpath("//input[@name='startYear_0']/ancestor::div[2]/following-sibling::div/ul/li[1]"));
		WebActionUtil.waitForElement(ddDurationofNoticePeriod, "Duration of Notice Period Drop down", 30);
		ddDurationofNoticePeriod.click();
	}

	/**
	 * Description Method clicks on Continue Button Education page
	 * 
	 * @author Sajal jain
	 * 
	 */
	public synchronized void clkContinueEducationBtn() {
		WebActionUtil.scrollToElement(btnContinueEducation, "Continue Education Button");
	//	WebActionUtil.isElementVisible(btnContinueEducation, "Continue Education Button");
		WebActionUtil.waitForElement(btnContinueEducation, "Continue Education Button", 45);
		WebActionUtil.clickOnWebElement(btnContinueEducation, "Continue Education Button",
					"Unable to click on Continue Button");
	   // WebActionUtil.clickOnElementUsingJS(btnContinueEducation, "Continue Education Button");
		
	}

	/**
	 * Description Method clicks on Continue Button Employee page
	 * 
	 * @author Sajal jain
	 * 
	 */
	public synchronized void clkContinueEmployeeBtn() {
		WebActionUtil.scrollToElement(btnContinueEmployee, "Continue Employee Button");
	//	WebActionUtil.isElementVisible(btnContinueEmployee, "Continue Employee Button");
		WebActionUtil.waitForElement(btnContinueEmployee, "Continue Employee Button", 45);
		WebActionUtil.isElementClickable(btnContinueEmployee, "Continue Employee Button");
		WebActionUtil.clickOnWebElement(btnContinueEmployee, "Continue Employee Button",
					"Unable to click on Continue Button");
	//	WebActionUtil.clickOnElementUsingJS(btnContinueEmployee, "Continue Employee Button");
		
	}

	/**
	 * Description Method clicks on Add more education tab
	 * 
	 * @author Sajal jain
	 * 
	 */
	public synchronized void clkAddMoreEducationTab() {
	//	WebActionUtil.isElementVisible(tabAddMoreEducation, "Add more education tab");
		WebActionUtil.waitForElement(tabAddMoreEducation, "Add more education tab", 45);
	//	WebActionUtil.isElementClickable(tabAddMoreEducation, "Add more education tab");
		WebActionUtil.clickOnElementUsingJS(tabAddMoreEducation, "Add more education tab");
	//	WebActionUtil.clickOnWebElement(tabAddMoreEducation, "Add more education tab","Unable to click on Add more education tab");
	}

	/**
	 * Description Method enters Current Designation Drop down
	 * 
	 * @author Sajal jain
	 * @param currentDesignation
	 */
	public synchronized void selectCurrentDesignationDD(String currentDesignation) throws InterruptedException {
		// WebDriverWait wait1=new WebDriverWait(driver,60);
	//	WebActionUtil.isElementVisible(ddCurrentDesignation, "Current Designation Drop down");
		WebActionUtil.waitForElement(ddCurrentDesignation, "Current Designation Drop down", 45);
		WebActionUtil.typeText(ddCurrentDesignation, currentDesignation, "Current Designation Drop down");
		// wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='designation_0']/ancestor::div[2]/following-sibling::ul/li//span[text()='"+currentDesignation+"']")));
		// driver.findElement(By.xpath("//input[@id='designation_0']/ancestor::div[2]/following-sibling::ul/li//span[text()='"+currentDesignation+"']")).click();
		WebActionUtil.waitForTextToAppear(ddCurrentDesignation, currentDesignation,"Current Designation Drop down");
		try {
			WebElement ddCurrentDesignation = driver.findElement(
					By.xpath("//input[@id='designation_0']/ancestor::div[2]/following-sibling::ul/li//span[text()='"
							+ currentDesignation + "']"));
			WebActionUtil.waitForElement(ddCurrentDesignation, "Select Current Designation Drop down", 45);
			WebActionUtil.isElementClickable(ddCurrentDesignation, "Select Current Designation Drop down");
			WebActionUtil.clickOnElementUsingJS(ddCurrentDesignation, "Select Current Designation Drop down");
		} catch (Exception e) {
			WebElement ddCurrentDesignation = driver.findElement(
					By.xpath("//input[@id='designation_0']/ancestor::div[2]/following-sibling::ul/li//span[text()='"
							+ currentDesignation + "']"));
			WebActionUtil.waitForElement(ddCurrentDesignation, "Select Current Designation Drop down", 45);
			WebActionUtil.isElementClickable(ddCurrentDesignation, "Select Current Designation Drop down");
			WebActionUtil.clickOnElementUsingJS(ddCurrentDesignation, "Select Current Designation Drop down");
		}
	}

	/**
	 * Description Method enters Current Company Drop down
	 * 
	 * @author Sajal jain
	 * @param currentCompany
	 */
	public synchronized void selectCurrentCompanyDD(String currentCompany) throws InterruptedException {
		// WebDriverWait wait1=new WebDriverWait(driver,60);
		WebActionUtil.isElementVisible(ddCurrentCompany, "Current Company Drop down");
		WebActionUtil.waitForElement(ddCurrentCompany, "Current Company Drop down", 45);

		WebActionUtil.typeText(ddCurrentCompany, currentCompany, "Current Company Drop down");
		WebActionUtil.waitForTextToAppear(ddCurrentCompany, currentCompany,"Current Company Drop down");

		// wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='company_0']/ancestor::div[2]/following-sibling::ul//span[text()='"+currentCompany+"']")));
		try {
			WebElement ddCurrentCompany = driver.findElement(
					By.xpath("//input[@id='company_0']/ancestor::div[2]/following-sibling::ul//span[text()='"
							+ currentCompany + "']"));
			WebActionUtil.waitForElement(ddCurrentCompany, "Select Current Company Drop down", 45);
			WebActionUtil.isElementVisible(ddCurrentCompany, "Select Current Company Drop down");
			// WebActionUtil.isElementClickable(ddCurrentCompany, "Select Current Company
			// Drop down");
			WebActionUtil.clickOnElementUsingJS(ddCurrentCompany, "Select Current Company Drop down");
		} catch (Exception e) {
			WebElement ddCurrentCompany = driver.findElement(
					By.xpath("//input[@id='company_0']/ancestor::div[2]/following-sibling::ul//span[contains(text(),'"
							+ currentCompany + "')]"));
			WebActionUtil.waitForElement(ddCurrentCompany, "Select Current Company Drop down", 45);
	//		WebActionUtil.isElementVisible(ddCurrentCompany, "Select Current Company Drop down");
			// WebActionUtil.isElementClickable(ddCurrentCompany, "Select Current Company
			// Drop down");
			WebActionUtil.clickOnElementUsingJS(ddCurrentCompany, "Select Current Company Drop down");
		}

	}

	/**
	 * Description Method clicks on Submit Button
	 * 
	 * @author Sajal jain
	 * 
	 */
	public synchronized void clkSubmit2Btn() {
		WebActionUtil.scrollToElement(btnSubmit2, "Submit Button");
		WebActionUtil.waitForElement(btnSubmit2, "Submit Button", 45);
		WebActionUtil.clickOnElementUsingJS(btnSubmit2, "Submit Button");
	}

	/**
	 * Description Method select Passing Year Drop down
	 * 
	 * @author Sajal jain
	 * @param passingYear
	 */
	public synchronized void selectPassingYearDD(String passingYear) {
		WebActionUtil.scrollToElement(ddPassingYear, "Passing Year Drop down");
	//	WebActionUtil.isElementVisible(ddPassingYear, "Passing Year Drop down");
		WebActionUtil.waitForElement(ddPassingYear, "Passing Year Drop down", 45);
		WebActionUtil.isElementClickable(ddPassingYear, "Passing Year Drop down");
		WebActionUtil.clickOnWebElement(ddPassingYear, "Passing Year Drop down", "Unable to click on Passing Year Drop down");
		WebActionUtil.clickOnElementUsingJS(ddPassingYear, "Passing Year Drop down");
		WebActionUtil.poll(2000);
		WebActionUtil.waitForElement(otnPassingYear, "Option Passing Year Drop down", 45);
		WebElement ddPassingYear = driver.findElement(
				By.xpath("//li[@ng-repeat-done-notification='"+passingYear+"']"));
		WebActionUtil.waitForElement(ddPassingYear, "Select Passing Year Drop down", 45);
		WebActionUtil.isElementClickable(ddPassingYear, "Select Passing Year Drop down");
		WebActionUtil.clickOnElementUsingJS(ddPassingYear, "Select Passing Year Drop down");
	}

	/**
	 * Description Method clicks on Course Type Button
	 * 
	 * @author Sajal jain
	 * @param courseType
	 */
	public synchronized void clkCourseTypeBtn(String courseType) {
		WebElement btnCourseType = driver.findElement(By.xpath("//label[@data-content='" + courseType + "']"));
		WebActionUtil.waitForElement(btnCourseType, "Course Type Button", 45);
		WebActionUtil.clickOnElementUsingJS(btnCourseType, "Course Type Button");
		WebActionUtil.poll(2000);
	}

	/**
	 * Description Method select University College Drop down
	 * 
	 * @author Sajal jain
	 * @param universityCollege
	 */
	public synchronized void selectUniversityCollegeDD(String universityCollege) {
	//	WebActionUtil.isElementVisible(ddUniversityCollege, "University College Drop down");
		WebActionUtil.waitForElement(ddUniversityCollege, "University College Drop down", 45);
		WebActionUtil.typeText(ddUniversityCollege, universityCollege, "University College Drop down");
		WebActionUtil.waitForTextToAppear(ddUniversityCollege, universityCollege, "University College Drop down");
		WebActionUtil.waitForElement(driver.findElement(
					By.xpath("//input[@id='institute_0']/ancestor::div/following-sibling::ul/li//span[text()='"
							+ universityCollege + "']")), "Select University College Drop down", 45);
		WebActionUtil.clickOnWebElement(driver.findElement(
				By.xpath("//input[@id='institute_0']/ancestor::div/following-sibling::ul/li//span[text()='"
						+ universityCollege + "']")), "Select University College Drop down",
				"Unable to click on University College Drop down");
		/*try {
			driver.findElement(
					By.xpath("//input[@id='institute_0']/ancestor::div/following-sibling::ul/li//span[text()='"
							+ universityCollege + "']"))
					.click();
		} catch (Exception e) {
			WebElement ddUniversityCollege = driver.findElement(
					By.xpath("//input[@id='institute_0']/ancestor::div/following-sibling::ul/li//span[text()='"
							+ universityCollege + "']"));
			WebActionUtil.waitForElement(ddUniversityCollege, "Select University College Drop down", 45);
			WebActionUtil.isElementClickable(ddUniversityCollege, "Select University College Drop down");
			try {
				WebActionUtil.clickOnWebElement(ddUniversityCollege, "Select University College Drop down",
						"Unable to click on University College Drop down");
			} catch (Exception f) {
				WebActionUtil.clickOnElementUsingJS(ddUniversityCollege, "Select University College Drop down");
			}
		}*/
	}

	/**
	 * Description Method select Specialization Drop down
	 * 
	 * @author Sajal jain
	 * @param specialization
	 */
	public synchronized void selectSpecializationDD(String specialization) {
		WebActionUtil.scrollToElement(ddSpecialization, "Specialization Drop down");
//		WebActionUtil.isElementVisible(ddSpecialization, "Specialization Drop down");
		WebActionUtil.waitForElement(ddSpecialization, "Specialization Drop down", 45);
		WebActionUtil.isElementClickable(ddSpecialization, "Specialization Drop down");
		WebActionUtil.clickOnWebElement(ddSpecialization, "Specialization Drop down",
				"Unable to click on Specialization Drop down");
//		WebActionUtil.clickOnElementUsingJS(ddSpecialization, "Specialization Drop down");
		WebActionUtil.poll(2000);
		//	try {
			driver.findElement(By.xpath("//input[@name='spec_0']/ancestor::div[2]/following-sibling::div//span[text()='"
					+ specialization + "']")).click();
		/*} catch (Exception e) {
			WebElement ddSpecialization = driver.findElement(
					By.xpath("//input[@name='spec_0']/ancestor::div[2]/following-sibling::div//span[text()='"
							+ specialization + "']"));
			WebActionUtil.waitForElement(ddSpecialization, "Select Specialization Drop down", 45);
			WebActionUtil.isElementClickable(ddSpecialization, "Select Specialization Drop down");
			WebActionUtil.clickOnElementUsingJS(ddSpecialization, "Select Specialization Drop down");
		}*/
	}

	/**
	 * Description Method select Highest Qualification Drop down
	 * 
	 * @author Sajal jain
	 * @param highestQualification
	 */
	public synchronized void selectHighestQualificationDD(String highestQualification) {
	//	WebActionUtil.isElementVisible(ddHighestQualification, "Highest Qualification Drop down");
		WebActionUtil.waitForElement(ddHighestQualification, "Highest Qualification Drop down", 45);
		WebActionUtil.isElementClickable(ddHighestQualification, "Highest Qualification Drop down");
		WebActionUtil.clickOnWebElement(ddHighestQualification, "Highest Qualification Drop down",
				"Unable to click on Highest Qualification Drop down");
	//	try {
		driver.findElement(
					By.xpath("//ul[@selected-id='selectedId']/li//span[text()='" + highestQualification + "']"))
					.click();
		/*} catch (Exception e) {
			WebElement ddHighestQualification = driver.findElement(
					By.xpath("//ul[@selected-id='selectedId']/li//span[text()='" + highestQualification + "']"));
			WebActionUtil.waitForElement(ddHighestQualification, "Select Highest Qualification Drop down", 45);
		//	WebActionUtil.isElementVisible(ddHighestQualification, "Select Highest Qualification Drop down");
			WebActionUtil.isElementClickable(ddHighestQualification, "Select Highest Qualification Drop down");
			WebActionUtil.clickOnElementUsingJS(ddHighestQualification, "Select Highest Qualification Drop down");
		}*/
	}
	
	/**
	 * Description Method select Course Drop down
	 * 
	 * @author Sajal jain
	 * @param course
	 */
	public synchronized void selectCourseDD(String course) {
		WebActionUtil.scrollToElement(ddCourse, "Course Drop down");
		WebActionUtil.isElementVisible(ddCourse, "Course Drop down");
		WebActionUtil.waitForElement(ddCourse, "Course Drop down", 45);
		WebActionUtil.clickOnWebElement(ddCourse, "Course Drop down", "Unable to click on Course Drop down");
		try {
			driver.findElement(By.xpath(
					"//input[@name='course_0']/ancestor::div[2]/following-sibling::div//span[text()='" + course + "']"))
					.click();
		} catch (Exception e) {
			WebElement ddCourse = driver.findElement(
					By.xpath("//input[@name='course_0']/ancestor::div[2]/following-sibling::div//span[text()='" + course
							+ "']"));
			WebActionUtil.waitForElement(ddCourse, "Select Course Drop down", 45);
			WebActionUtil.isElementClickable(ddCourse, "Select Course Drop down");
			WebActionUtil.clickOnWebElement(ddCourse, "Course Drop down", "Unable to click on Course Drop down");
		}
		WebActionUtil.poll(2000);
	}



	/**
	 * Description Method select Role Drop down
	 * 
	 * @author Sajal jain
	 * @param role
	 */
	public synchronized void selectRoleDD(String role) {

		/*
		 * driver.findElement(By.xpath(
		 * "//input[@name='role']/ancestor::div[2]/following-sibling::div/ul/li//span[text()='"
		 * +role+"']")).click();
		 * 
		 * driver.findElement(By.xpath(
		 * "(//input[@name='role']/ancestor::div[@class='DDwrap']/following-sibling::div/ul/descendant::div/span)[1]"
		 * )).click(); }catch(Exception e) { new
		 * WebDriverWait(driver,30).until(ExpectedConditions.visibilityOfElementLocated(
		 * By.xpath(
		 * "//input[@name='role']/ancestor::div[2]/following-sibling::div/ul/li//span[text()='"
		 * +role+"']"))); driver.findElement(By.xpath(
		 * "//input[@name='role']/ancestor::div[2]/following-sibling::div/ul/li//span[text()='"
		 * +role+"']")).click(); }WebElement ddRole = driver.findElement(By.xpath(
		 * "//input[@name='role']/ancestor::div[2]/following-sibling::div/ul/li//span[1]"
		 * ));
		 * 
		 * 
		 */
		WebActionUtil.scrollToElement(ddRole, "Role Drop down");
		WebActionUtil.waitForElement(ddRole, "Role Drop down", 20);
		WebActionUtil.isElementVisible(ddRole, "Role Drop down");
		WebActionUtil.clickOnWebElement(ddRole, "Role Drop down", "Unable to click on Role Drop down");
		WebActionUtil.typeText(ddRole, role, "Role Drop down");
		WebActionUtil.waitForTextToAppear(ddRole, role, "Role Drop down");
		try {
			driver.findElement(By.xpath("//input[@name='role']/ancestor::div[2]/following-sibling::div/ul/li//span[1]"))
					.click();
		} catch (Exception e) {
		}

	}

	/**
	 * Description Method select Functional Area Drop down
	 * 
	 * @author Sajal jain
	 * @param functionalArea
	 */
	public synchronized void selectFunctionalAreaDD(String functionalArea) {
		WebActionUtil.isElementVisible(ddFunctionalArea, "Functional Area Drop down");
		WebActionUtil.waitForElement(ddFunctionalArea, "Functional Area Drop down", 45);
		WebActionUtil.typeText(ddFunctionalArea, functionalArea, "Functional Area Drop down");
		WebActionUtil.waitForTextToAppear(ddFunctionalArea, functionalArea, "Functional Area Drop down");
		WebElement ddFunctionalArea = driver.findElement(
				By.xpath("//input[@name='farea']/ancestor::div[2]/following-sibling::div/ul/li//span[text()='"
						+ functionalArea + "']"));
		WebActionUtil.waitForElement(ddFunctionalArea, "Select Functional Area Drop down", 45);
		WebActionUtil.isElementClickable(ddFunctionalArea, "Select Funtional Area Drop down");
		WebActionUtil.clickOnElementUsingJS(ddFunctionalArea, "Select Functional Area Drop down");
	}

	/**
	 * Description Method select Industry Drop down
	 * 
	 * @author Sajal jain
	 * @param industry
	 */
	public synchronized void selectIndustryDD(String industry) {
		WebActionUtil.isElementVisible(ddIndustry, "Industry Drop down");
		WebActionUtil.waitForElement(ddIndustry, "Industry Drop down", 45);
		WebActionUtil.typeText(ddIndustry, industry, "Industry Drop down");
		WebActionUtil.waitForTextToAppear(ddIndustry, industry, "Industry Drop down");
		WebElement ddIndustry = driver.findElement(
				By.xpath("//input[@name='industry']/ancestor::div[2]/following-sibling::div/ul/li//span[text()='"
						+ industry + "']"));
		WebActionUtil.waitForElement(ddIndustry, "Industry Drop down", 45);
		WebActionUtil.isElementClickable(ddIndustry, "Industry Drop down");
		WebActionUtil.clickOnElementUsingJS(ddIndustry, "Industry Drop down");
	}

	/**
	 * Description Method select Skills Drop down
	 * 
	 * @author Sajal jain
	 * @param skills
	 * 
	 */
	public synchronized void selectSkillsDD(String skills) {
		WebActionUtil.isElementVisible(ddSkills, "Skills Drop down");
		WebDriverWait wait1 = new WebDriverWait(driver, 60);
		WebActionUtil.waitForElement(ddSkills, "Skills Drop down", 45);
		WebActionUtil.typeText(ddSkills, skills, "Skills Drop down");
		WebActionUtil.waitForTextToAppear(ddSkills, skills, "Skills Drop down");
		// By ddSkills =
		// By.xpath("//input[@name='keyskills']/ancestor::div/following-sibling::ul/li//span[text()='"+skills+"']");
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
				"//input[@name='keyskills']/ancestor::div/following-sibling::ul/li//span[text()='" + skills + "']")));
		driver.findElement(By.xpath(
				"//input[@name='keyskills']/ancestor::div/following-sibling::ul/li//span[text()='" + skills + "']"))
				.click();
		/*
		 * WebElement ddSkills = driver.findElement(By.xpath(
		 * "//input[@name='keyskills']/ancestor::div/following-sibling::ul/li//span[text()='"
		 * +skills+"']")); WebActionUtil.waitForElement(ddSkills,
		 * "Select Skills Drop down", 45); WebActionUtil.isElementClickable(ddSkills,
		 * "Select Skills Drop down"); WebActionUtil.clickOnElementUsingJS(ddSkills,
		 * "Select Skills Drop down");
		 */
	}

	/**
	 * Description Method select Working Since Year Drop down
	 * 
	 * @author Sajal jain
	 * @param workingSinceYear
	 */
	public synchronized void selectWorkingSinceYearDD(String workingSinceYear) {
		WebActionUtil.scrollToElement(ddWorkingSinceYear, "Working Since Year Drop down");
		WebActionUtil.isElementVisible(ddWorkingSinceYear, "Working Since Year Drop down");
		WebActionUtil.waitForElement(ddWorkingSinceYear, "Working Since Year Drop down", 45);
		WebActionUtil.typeText(ddWorkingSinceYear, workingSinceYear, "Working Since Year Drop down");
		WebActionUtil.waitForTextToAppear(ddWorkingSinceYear, workingSinceYear, "Working Since Year Drop down");

		try {
			driver.findElement(
					By.xpath("//input[@name='startYear_0']/ancestor::div[2]/following-sibling::div/ul/li//span[text()='"
							+ workingSinceYear + "']"))
					.click();
		} catch (Exception e) {
			WebElement ddWorkingSinceYear = driver.findElement(
					By.xpath("//input[@name='startYear_0']/ancestor::div[2]/following-sibling::div/ul/li//span[text()='"
							+ workingSinceYear + "']"));
			// WebActionUtil.waitForElement(ddWorkingSinceYear, "Working Since Year Drop
			// down", 45);
			// WebActionUtil.isElementClickable(ddWorkingSinceYear, "Working Since Year Drop
			// down");
			WebActionUtil.isElementVisible(ddWorkingSinceYear, "Working Since Year Drop down");
			WebActionUtil.clickOnElementUsingJS(ddWorkingSinceYear, "Working Since Year Drop down");
		}
	}

	/**
	 * Description Method select Working Since Month Drop down
	 * 
	 * @author Sajal jain
	 * @param workingSinceMonth
	 */
	public synchronized void selectWorkingSinceMonthDD(String workingSinceMonth) {
		WebActionUtil.isElementVisible(ddWorkingSinceMonth, "Working Since Month Drop down");
		WebActionUtil.waitForElement(ddWorkingSinceMonth, "Working Since Month Drop down", 45);
		WebActionUtil.typeText(ddWorkingSinceMonth, workingSinceMonth, "Working Since Month Drop down");
		WebActionUtil.waitForTextToAppear(ddWorkingSinceMonth, workingSinceMonth, "Working Since Month Drop down");
		try {
			driver.findElement(By
					.xpath("//input[@name='startMonth_0']/ancestor::div[2]/following-sibling::div/ul/li//span[text()='"
							+ workingSinceMonth + "']"))
					.click();
		} catch (Exception e) {
			WebElement ddWorkingSinceMonth = driver.findElement(By
					.xpath("//input[@name='startMonth_0']/ancestor::div[2]/following-sibling::div/ul/li//span[text()='"
							+ workingSinceMonth + "']"));
			WebActionUtil.isElementVisible(ddWorkingSinceMonth, "Working Since Month Drop down");
			WebActionUtil.waitForElement(ddWorkingSinceMonth, "Working Since Month Drop down", 45);
			// WebActionUtil.isElementClickable(ddWorkingSinceMonth, "Working Since Month
			// Drop down");
			WebActionUtil.clickOnElementUsingJS(ddWorkingSinceMonth, "Working Since Month Drop down");
		}
	}

	/**
	 * Description Method select the given Annual salary Thousand drop down
	 * 
	 * @author Sajal jain
	 * @param annualSalaryThousand
	 */
	public synchronized void selectAnnualSalaryThousandDD(String annualSalaryThousand) {
		WebActionUtil.scrollToElement(ddAnnualSalaryThousand, "Annual Salary Thousand Drop down");
		WebActionUtil.isElementVisible(ddAnnualSalaryThousand, "Annual Salary Thousand Drop down");
		WebActionUtil.waitForElement(ddAnnualSalaryThousand, "Annual Salary Thousand Drop down", 45);
		WebActionUtil.typeText(ddAnnualSalaryThousand, annualSalaryThousand, "Annual Salary Thousand Drop down");
		WebActionUtil.waitForTextToAppear(ddAnnualSalaryThousand, annualSalaryThousand, "Annual Salary Thousand Drop down");
		try {
			driver.findElement(
					By.xpath("//input[@name='salaryThou_0']/ancestor::div[2]/following-sibling::div//span[text()='"
							+ annualSalaryThousand + "']"))
					.click();
		} catch (Exception e) {
			WebElement ddAnnualSalaryThousand = driver.findElement(
					By.xpath("//input[@name='salaryThou_0']/ancestor::div[2]/following-sibling::div//span[text()='"
							+ annualSalaryThousand + "']"));
			WebActionUtil.waitForElement(ddAnnualSalaryThousand, "Annual Salary Thousand Drop down", 45);
			WebActionUtil.isElementVisible(ddAnnualSalaryThousand, "Annual Salary Thousand Drop down");
			// WebActionUtil.isElementClickable(ddAnnualSalaryThousand, "Annual Salary
			// Thousand Drop down");
			WebActionUtil.clickOnElementUsingJS(ddAnnualSalaryThousand, "Annual Salary Thousand Drop down");
		}
	}

	/**
	 * Description Method select the given Annual salary lakhs Drop down
	 * 
	 * @author Sajal jain
	 * @param annualSalarylakhs
	 */
	public synchronized void selectAnnualSalaryLakhsDD(String annualSalarylakhs) {
		WebActionUtil.scrollToElement(ddAnnualSalaryLakhs, "Annual Salary Lakhs Drop down");
		WebActionUtil.isElementVisible(ddAnnualSalaryLakhs, "Annual Salary Lakhs Drop down");
		WebActionUtil.waitForElement(ddAnnualSalaryLakhs, "Annual Salary Lakhs Drop down", 45);
		WebActionUtil.typeText(ddAnnualSalaryLakhs, annualSalarylakhs, "Annual Salary Lakhs Drop down");
		WebActionUtil.waitForTextToAppear(ddAnnualSalaryLakhs, annualSalarylakhs, "Annual Salary Lakhs Drop down");
		try {
			driver.findElement(
					By.xpath("//input[@name='salaryLac_0']/ancestor::div[2]/following-sibling::div/ul/li//span[text()='"
							+ annualSalarylakhs + "']"))
					.click();
		} catch (Exception e) {
			WebElement ddAnnualSalaryLakhs = driver.findElement(
					By.xpath("//input[@name='salaryLac_0']/ancestor::div[2]/following-sibling::div/ul/li//span[text()='"
							+ annualSalarylakhs + "']"));
			// WebActionUtil.isElementClickable(ddAnnualSalaryLakhs, "Annual Salary Lakhs
			// Drop down");
			WebActionUtil.isElementVisible(ddAnnualSalaryLakhs, "Annual Salary Lakhs Drop down");
			WebActionUtil.waitForElement(ddAnnualSalaryLakhs, "Annual Salary Lakhs Drop down", 45);
			WebActionUtil.clickOnElementUsingJS(ddAnnualSalaryLakhs, "Annual Salary Lakhs Drop down");
		}
	}

	/**
	 * Description Method clicks on Submit Button
	 * 
	 * @author Sajal jain
	 * 
	 */
	public synchronized void clkSubmit1Btn() {
		WebActionUtil.waitForElement(btnSubmit1, "Submit Button", 45);
		WebActionUtil.clickOnElementUsingJS(btnSubmit1, "Submit Button");
	}

	/**
	 * Description Method enters Password text box
	 * 
	 * @author Sajal jain
	 * @param password
	 */
	public synchronized void setPassword(String password) {
		WebActionUtil.waitForElement(txtPassword, "Password Text Box", 30);
		WebActionUtil.typeText(txtPassword, password, "Password Text Box");
	}

	/**
	 * Description Method select the given city name
	 * 
	 * @author Sajal jain
	 * @param currentCityName
	 */
	public synchronized void selectCurrentCityDD(String currentCityName) throws InterruptedException {
		WebActionUtil.waitForElement(ddCurrentCity, "Current City Drop down", 45);
		WebActionUtil.typeText(ddCurrentCity, currentCityName, "Current City Drop down");
		WebActionUtil.waitForTextToAppear(ddCurrentCity, currentCityName, "Current City Drop down");
		WebDriverWait wait1 = new WebDriverWait(driver, 50);
		By ddCurrentCity = By.xpath(
				"//input[@placeholder='Tell us about your current city']/ancestor::div[2]/following-sibling::ul/li//span[text()='"
						+ currentCityName + "']");
		wait1.until(ExpectedConditions.visibilityOfElementLocated(ddCurrentCity));
		driver.findElement(ddCurrentCity).click();
	}

	/**
	 * Description Method clicks on Total Work Experience Years Drop down
	 * 
	 * @author Sajal jain
	 * @param years
	 */
	public synchronized void selectTotalWorkExperienceYearsDD(String years) throws InterruptedException {
		WebActionUtil.waitForElement(ddTotalWorkExperienceYears, "Total Work Experience Years Drop down", 45);
		// WebActionUtil.isElementClickable(ddTotalWorkExperienceYears, "Total Work
		// Experience Years Drop down");
		WebActionUtil.typeText(ddTotalWorkExperienceYears, years, "Total Work Experience Years Drop down");
		WebActionUtil.waitForTextToAppear(ddTotalWorkExperienceYears, years, "Total Work Experience Years Drop down");
		driver.findElement(By.xpath("//li[@ng-repeat-done-notification='" + years + "']")).click();
		/*
		 * WebElement ddYears =
		 * driver.findElement(By.xpath("//li[@ng-repeat-done-notification='"+years+"']")
		 * ); WebActionUtil.clickOnElementUsingJS(ddYears, "Select Years Drop down");
		 */

	}

	/**
	 * Description Method clicks on Total Work Experience Months Drop down
	 * 
	 * @author Sajal jain
	 * @param months
	 */
	public synchronized void selectTotalWorkExperienceMonthsDD(String months) {
		WebActionUtil.waitForElement(ddTotalWorkExperienceMonths, "Total Work Experience Months Drop down", 45);
		// WebActionUtil.clickOnElementUsingJS(ddTotalWorkExperienceYears, "Total Work
		// Experience Years Drop down");
		WebActionUtil.typeText(ddTotalWorkExperienceMonths, months, "Total Work Experience Months Drop down");
		WebActionUtil.waitForTextToAppear(ddTotalWorkExperienceMonths, months, "Total Work Experience Months Drop down");
		/*
		 * WebElement ddMonths =
		 * driver.findElement(By.xpath("//li[@ng-repeat-done-notification='"+months+"']"
		 * )); WebActionUtil.clickOnElementUsingJS(ddMonths, "Select Months Drop down");
		 */
		driver.findElement(By.xpath("//li[@ng-repeat-done-notification='" + months + "']")).click();
	}

	/**
	 * Description Method clicks on Register Now Button
	 * 
	 * @author Sajal jain
	 * 
	 */
	public synchronized void clkRegisterNowBtn() {
		WebActionUtil.waitForElement(btnRegisterNow, "Register Now Button", 45);
		WebActionUtil.clickOnElementUsingJS(btnRegisterNow, "Register Now Button");
		WebActionUtil.waitForElement(
				driver.findElement(
						By.xpath("//span[contains(text(),'You have successfully registered with Naukri.')]")),
				"success msg", 50);
	}

	/**
	 * Description Method enters Mobile Number
	 * 
	 * @author Sajal jain
	 * @param mobileNumber
	 */
	public synchronized void setMobileNumber(String mobileNumber) {
		WebActionUtil.waitForElement(txtMobileNumber, "Mobile Number Text Box", 10);
		WebActionUtil.typeText(txtMobileNumber, mobileNumber, "Mobile Number Text Box");

	}

	/**
	 * Description Method enters Create Password
	 * 
	 * @author Sajal jain
	 * @param createPassword
	 */
	public synchronized void setCreatePassword(String createPassword) {
		WebActionUtil.waitForElement(txtCreatePassword, "Create Password Text Box", 10);
		WebActionUtil.typeText(txtCreatePassword, createPassword, "Create Password Text Box");

	}

	/**
	 * Description Method enters EmailID
	 * 
	 * @author Sajal jain
	 * @param emailID
	 */
	public synchronized void setEmailID(String emailID) {
		WebActionUtil.waitForElement(txtEmailID, "EmailID Text Box", 10);
		WebActionUtil.typeText(txtEmailID, emailID, "EmailID Text Box");

	}

	/**
	 * Description Method enters Name
	 * 
	 * @author Sajal jain
	 * @param name
	 */
	public synchronized void setName(String name) {
		WebActionUtil.waitForElement(txtName, "Name Text Box", 10);
		WebActionUtil.typeText(txtName, name, "Name Text Box");

	}

	/**
	 * Description Method clicks on I am a Fresher Button
	 * 
	 * @author Sajal jain
	 * 
	 */
	public synchronized void clkIAmExperiencedBtn() {
		WebActionUtil.waitForElement(btnIAmExperienced, "I am Experienced Button", 45);
		WebActionUtil.clickOnElementUsingJS(btnIAmExperienced, "I am Experienced Button");
	}

	/**
	 * Description Method clicks on Register for free Button
	 * 
	 * @author Sajal jain
	 * 
	 */
	public synchronized void clkRegisterForFreeBtn() {
		WebActionUtil.waitForElement(btnRegisterForFree, "Register for free Button", 45);
		WebActionUtil.clickOnElementUsingJS(btnRegisterForFree, "Register for free Button");
	}

}
