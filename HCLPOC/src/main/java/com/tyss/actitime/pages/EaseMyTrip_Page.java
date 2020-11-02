package com.tyss.actitime.pages;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.tyss.acttime.util.WebActionUtil;

import junit.framework.Assert;

/**
 * Description This class has the implementations of the Ease My Trip flight
 * Booking related methods
 * 
 * @author Sajal jain
 */
public class EaseMyTrip_Page {
	public WebDriver driver;
	public WebActionUtil WebActionUtil;
	public long ETO = 10;

	public EaseMyTrip_Page(WebDriver driver, long ETO, WebActionUtil WebActionUtil) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.WebActionUtil = WebActionUtil;
		this.ETO = ETO;
	}

	/* One Way Tab */
	@FindBy(xpath = "//li[text()='One Way ']")
	private WebElement tabOneWay;
	/* From Option Drop down */
	@FindBy(id = "fromautoFill")
	private WebElement sddFrom;
	/* To Option Drop down */
	@FindBy(id = "toautoFill")
	private WebElement sddTo;
	/* From Drop down */
	@FindBy(id = "FromSector_show")
	private WebElement ddFrom;
	/* To Drop down */
	@FindBy(id = "Editbox13_show")
	private WebElement ddTo;
	/* Departure Drop down */
	@FindBy(id = "ddate")
	private WebElement ddDeparture;
	/* Search Button */
	@FindBy(id = "search")
	private WebElement btnSearch;
	/* One Stop Filter Check Box */
	@FindBy(xpath = "//input[@id='chkOneStop']/following-sibling::span")
	private WebElement cbOneStop;
	/* Airlines Filter Check Box */
	@FindBy(xpath = "//input[@id='GoAir']/following-sibling::span")
	private WebElement cbAirLines;
	/* Book Now Button */
	@FindBy(xpath = "//button[text()='Book Now']/..")
	private WebElement btnBookNow;
	/* No Radio Button */
	@FindBy(xpath = "//input[@id='chkNotZeroInsurance']/following-sibling::span")
	private WebElement rbNo;
	/* Enter Email Address Text Box */
	@FindBy(css = "input[placeholder='Enter Email Address']")
	private WebElement txtEnterEmailAddress;
	/* Continue Booking Button */
	@FindBy(id = "spnVerifyEmail")
	private WebElement btnContinueBooking;
	/* Continue Button */
	@FindBy(id = "spnTransaction")
	private WebElement btnContinue;
	/* First Name Text Box */
	@FindBy(id = "txtFNAdult0")
	private WebElement txtFirstName;
	/* Last Name Text Box */
	@FindBy(id = "txtLNAdult0")
	private WebElement txtLastName;
	/* Title Drop down */
	@FindBy(id = "titleAdult0")
	private WebElement ddTitle;
	/* Mobile Number Text Box */
	@FindBy(id = "txtCPhone")
	private WebElement txtMobileNumber;
	/* Email Message */
	@FindBy(xpath = "//span[contains(text(),'E-mail')]/following-sibling::span")
	private WebElement msgEmail;

	/**
	 * Description Method to validate Email and Mobile Number
	 * 
	 * @author Sajal jain
	 * @param mobileNumber
	 * @param emailAddress
	 */
	public void validateEmailMobileNumber(String emailAddress, String mobileNumber) {
		WebActionUtil.scrollToElement(msgEmail, "Email Message");
		WebActionUtil.isElementVisible(msgEmail, "Email Message");
		WebActionUtil.waitForElement(msgEmail, "Email Message", 20);
		String actualEmail = msgEmail.getText();
		/*
		 * System.out.println("expected :"+emailAddress);
		 * System.out.println("actual : "+actualEmail);
		 */
		Assert.assertEquals(emailAddress, actualEmail);
		System.out.println("Email validated");
		String actualMobileNumber = driver
				.findElement(By.xpath("//span[contains(text(),'Contact No')]/following-sibling::span")).getText();
		/*
		 * System.out.println("expected :"+mobileNumber);
		 * System.out.println("actual : "+actualMobileNumber);
		 */
		Assert.assertEquals(mobileNumber, actualMobileNumber);
		System.out.println("Mobile Number validated");
	}

	/**
	 * Description Method to Enter Mobile Number Text Box
	 * 
	 * @author Sajal jain
	 * @param mobileNumber
	 */
	public void setMobileNumber(String mobileNumber) {
		WebActionUtil.waitForElement(txtMobileNumber, "Mobile Number Text Box", 25);
		WebActionUtil.typeText(txtMobileNumber, mobileNumber, "Mobile Number Text Box");
	}

	/**
	 * Description Method to Select Title Drop down
	 * 
	 * @author Sajal jain
	 * @param title
	 */
	public void selectTitleDD(String title) {
		WebActionUtil.waitForElement(ddTitle, "Title Drop down", 25);
		WebActionUtil.selectByText(ddTitle, title);
	}

	/**
	 * Description Method to Enter Last Name Text Box
	 * 
	 * @author Sajal jain
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		WebActionUtil.waitForElement(txtLastName, "Last Name Text Box", 25);
		WebActionUtil.typeText(txtLastName, lastName, "Last Name Text Box");
	}

	/**
	 * Description Method to Enter First Name Text Box
	 * 
	 * @author Sajal jain
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		WebActionUtil.waitForElement(txtFirstName, "First Name Text Box", 25);
		WebActionUtil.typeText(txtFirstName, firstName, "First Name Text Box");
	}

	/**
	 * Description Method to Continue Button
	 * 
	 * @author Sajal jain
	 */
	public void clkContinueBtn() {
		WebActionUtil.scrollToElement(btnContinue, "Continue Button");
		WebActionUtil.waitForElement(btnContinue, "Continue Button", 45);
		// btnContinue.click();
		WebActionUtil.clickOnElementUsingJS(btnContinue, "Continue Button");
		// WebActionUtil.clickOnWebElement(btnContinue, "Continue Button","Unable to
		// click on Continue Button");

	}

	/**
	 * Description Method to Continue Booking Button
	 * 
	 * @author Sajal jain
	 */
	public void clkContinueBookingBtn() {
		WebActionUtil.scrollToElement(btnContinueBooking, "Continue Booking Button");
		WebActionUtil.waitForElement(btnContinueBooking, "Continue Booking Button", 45);
		WebActionUtil.clickOnElementUsingJS(btnContinueBooking, "Continue Booking Button");
	}

	/**
	 * Description Method to Enter Email Address Text Box
	 * 
	 * @author Sajal jain
	 * @param enterEmailAddress
	 */
	public void setEmailAddress(String enterEmailAddress) {
		WebActionUtil.scrollToElement(txtEnterEmailAddress, "Enter Email Address Text Box");
		WebActionUtil.waitForElement(txtEnterEmailAddress, "Enter Email Address Text Box", 25);
		// WebActionUtil.action.moveToElement(txtEnterEmailAddress).perform();
		// txtEnterEmailAddress.click();
		txtEnterEmailAddress.sendKeys(enterEmailAddress);
		// WebActionUtil.typeText(txtEnterEmailAddress, enterEmailAddress, "Enter Email
		// Address Text Box");
	}

	/**
	 * Description Method to click on No Radio Button
	 * 
	 * @author Sajal jain
	 */
	public void clkNoRb() {
		WebActionUtil.scrollToElement(rbNo, "No Radio Button");
		WebActionUtil.waitForElement(rbNo, "No Radio Button", 45);
		rbNo.click();
		// WebActionUtil.clickOnElementUsingJS(rbNo, "No Radio Button");
	}

	/**
	 * Description Method to click on Book Now Button
	 * 
	 * @author Sajal jain
	 */
	public void clkBookNowBtn() {
		WebActionUtil.scrollToElement(btnBookNow, "Book Now Button");
		WebActionUtil.isElementVisible(btnBookNow, "Book Now Button");
		WebActionUtil.waitForElement(btnBookNow, "Book Now Button", 45);
		btnBookNow.click();
		/*
		 * WebActionUtil.clickOnWebElement(btnBookNow, "Book Now Button",
		 * "Unable to click on Book Now Button");
		 * WebActionUtil.clickOnElementUsingJS(btnBookNow, "Book Now Button");
		 */
	}

	/**
	 * Description Method to click on AirLines Check Box
	 * 
	 * @author Sajal jain
	 */
	public void clkAirLinesCb() {
		WebActionUtil.scrollToElement(cbAirLines, "AirLines Check Box");
		WebActionUtil.waitForElement(cbAirLines, "AirLines Check Box", 45);
		WebActionUtil.clickOnElementUsingJS(cbAirLines, "AirLines Check Box");
	}

	/**
	 * Description Method to click on One Stop Check Box
	 * 
	 * @author Sajal jain
	 */
	public void clkOneStopCb() {
		WebActionUtil.scrollToElement(cbOneStop, "One Stop Check Box");
		WebActionUtil.waitForElement(cbOneStop, "One Stop Check Box", 45);
		WebActionUtil.clickOnElementUsingJS(cbOneStop, "One Stop Check Box");
	}

	/**
	 * Description Method to click on Search Button
	 * 
	 * @author Sajal jain
	 */
	public void clkSearchBtn() {
		WebActionUtil.waitForElement(btnSearch, "Search Button", 45);
		WebActionUtil.poll(2000);
		btnSearch.click();
		/*
		 * WebActionUtil.clickOnElementUsingJS(btnSearch, "Search Button");
		 * WebActionUtil.clickOnWebElement(btnSearch, "Search Button",
		 * "Unable to click on Search Button");
		 */
	}

	/**
	 * Description Method enters To Drop down
	 * 
	 * @author Sajal jain
	 * @param toLocation
	 */
	public synchronized void selectToDD(String toLocation) {
		WebActionUtil.waitForElement(ddTo, "To Drop down", 30);
		WebActionUtil.clearText(ddTo, "To Drop down");
		WebActionUtil.typeText(ddTo, toLocation, "To Drop down");
		WebElement sddTo = driver.findElement(By.xpath(
				"//input[@id='hdnSysDate']/following-sibling::ul[2]//span[contains(text(),'" + toLocation + "')]"));
		WebActionUtil.waitForElement(sddTo, "From List", 30);
		WebActionUtil.poll(4000);
		sddTo.click();
		// ddTo.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
	}

	/**
	 * Description Method enters From Drop down
	 * 
	 * @author Sajal jain
	 * @param fromLocation
	 */
	public synchronized void selectFromDD(String fromLocation) {
		WebActionUtil.waitForElement(ddFrom, "From Drop down", 30);
		WebActionUtil.clearText(ddFrom, "From Drop down");
		WebActionUtil.typeText(ddFrom, fromLocation, "From Drop down");
		WebElement sddFrom = driver.findElement(By.xpath(
				" //input[@id='hdnSysDate']/following-sibling::ul//span[contains(text(),'" + fromLocation + "')]"));
		WebActionUtil.waitForElement(sddFrom, "From List", 30);
		WebActionUtil.poll(4000);
		sddFrom.click();
		// ddFrom.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
	}

	/**
	 * Description Method to click on One Way Tab
	 * 
	 * @author Sajal jain
	 */
	public void clkOneWayTab() {
		WebActionUtil.waitForElement(tabOneWay, "One Way Tab", 45);
		WebActionUtil.clickOnElementUsingJS(tabOneWay, "One Way Tab");
	}

	/**
	 * Description Method Select Departure Date Drop down
	 * 
	 * @author Sajal jain
	 * @param year
	 *            month date
	 * @throws ParseException
	 */
	public synchronized void selectDepartureDateDD(String year, String month, String date) throws ParseException {// String
																													// departureDate)
																													// {
		WebActionUtil.waitForElement(ddDeparture, "Departure Date Drop down", 30);
		/*
		 * WebActionUtil.clickOnWebElement(ddDeparture, "Departure Date Drop down",
		 * "Unable to Click on Departure Date Drop down"); WebElement date =
		 * driver.findElement(By.xpath("//li[contains(@id,'"+departureDate+"')]"));
		 * WebActionUtil.clickOnWebElement(date, "Date Drop down",
		 * "Unable to Click on Date Drop down");
		 * WebActionUtil.clickOnDay(departureDate);
		 */
		selectDate(ddDeparture, year, month, date);

	}

	public void selectDate(WebElement calendar, String year, String monthName, String date) throws ParseException {
		// Clicking on calendar to open calendar widget
		calendar.click();

		// Retrieving current year value
		// String currentYear=
		// driver.findElement(By.xpath("//div[@class='ui-datepicker-title']/span[@class='ui-datepicker-year']")).getText();
		String currentYear = driver.findElement(By.xpath("(//div[@id='dvprevious'])[1]/following-sibling::div[1]"))
				.getText().substring(4);
		// Click on Next arrow till we get desired year
		if (!currentYear.equals(year)) {
			do {
				driver.findElement(By.id("img2Nex")).click();
				// System.out.println("year");
				// driver.findElement(By.xpath("(//span[text()='Next'])[1]")).click();
			} while (!driver.findElement(By.xpath("(//div[@id='dvprevious'])[1]/following-sibling::div[1]")).getText()
					.substring(4).equals(year));
		}

		// Select desired month after selecting desired year
		String currentMonth = driver.findElement(By.xpath("(//div[@id='dvprevious'])[1]/following-sibling::div[1]"))
				.getText().substring(0, 3);
		// String currentMonth=
		// driver.findElement(By.xpath("(//div[@class='ui-datepicker-title']/span[@class='ui-datepicker-month'])[1]")).getText();
		if (!currentMonth.equalsIgnoreCase(monthName)) {
			do {
				driver.findElement(By.id("img2Nex")).click();
				// System.out.println("month");
				// System.out.println(driver.findElement(By.xpath("(//div[@id='dvprevious'])[2]/following-sibling::div[1]")).getText().substring(0,3).trim());
				// driver.findElement(By.xpath("(//span[text()='Next'])[1]")).click();
			} while (!driver.findElement(By.xpath("//div[@class='month2']")).getText().substring(0, 3).trim()
					.equalsIgnoreCase(monthName));
			// while(!driver.findElement(By.xpath("(//div[@id='dvprevious'])[2]/following-sibling::div[1]")).getText().substring(0,2).trim().equalsIgnoreCase(monthName));

		}
		// get java month number for desired month
		int javaMonthInt = getMonthJavaInt(monthName);

		// Find dates of desired month only
		// List<WebElement> allDateOfDesiredMonth=
		// driver.findElements(By.xpath("//div[@class='ui-datepicker-group
		// ui-datepicker-group-first']//table/tbody[1]//td[(@class=' ' or @class='
		// ui-datepicker-week-end ' ) and @data-month='"+javaMonthInt+"']"));
		List<WebElement> allDateOfDesiredMonth = driver
				.findElements(By.xpath("//div[@class='month']/following-sibling::div/ul/li"));

		for (WebElement d : allDateOfDesiredMonth) {
			// System.out.println(d.getText());
			// System.out.println(d.getText().contains("25"));
			if (d.getText().trim().contains(date)) {
				// System.out.println("loop");
				d.click();
				break;
			}
		}
		WebActionUtil.poll(2000);
	}

	// Code to get java month number
	public static int getMonthJavaInt(String monthName) throws ParseException {
		Date date = new SimpleDateFormat("MMMM").parse(monthName);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.MONTH);
	}
}
