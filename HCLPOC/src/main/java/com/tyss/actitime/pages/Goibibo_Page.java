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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.tyss.acttime.util.WebActionUtil;

/**
 * Description This class has the implementations of the Goibibo related methods
 * 
 * @author Sajal jain
 */
public class Goibibo_Page {
	public WebDriver driver;
	public WebActionUtil WebActionUtil;
	public long ETO = 10;

	public Goibibo_Page(WebDriver driver, long ETO, WebActionUtil WebActionUtil) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.WebActionUtil = WebActionUtil;
		this.ETO = ETO;
	}

	/* One Way Button */
	@FindBy(id = "oneway")
	private WebElement btnOneWay;
	/* Forward Arrow Button */
	@FindBy(xpath = "//span[@class='DayPicker-NavButton DayPicker-NavButton--next']")
	private WebElement btnForwardArrow;
	/* Student Fare Tab */
	@FindBy(id = "student_fare_check")
	private WebElement tabStudentFare;
	/* Close Button */
	@FindBy(id = "pax_close")
	private WebElement btnClose;
	/* Search Button */
	@FindBy(id = "gi_search_btn")
	private WebElement btnSearch;
	/* Book Button */
	@FindBy(css = "input[value='BOOK']")
	private WebElement btnBook;
	/* I Am Willing To Risk My Trip Radio Button */
	@FindBy(id = "risk-trip")
	private WebElement rbIAmWillingToRiskMyTrip;
	/* Adult Name Title Text Box */
	@FindBy(id = "Adulttitle1")
	private WebElement ddAdultNameTitle;
	/* First Name Text Box */
	@FindBy(id = "AdultfirstName1")
	private WebElement txtFirstName;
	/* Middle Name Text Box */
	@FindBy(id = "AdultmiddleName1")
	private WebElement txtMiddleName;
	/* Last Name Text Box */
	@FindBy(id = "AdultlastName1")
	private WebElement txtLastName;
	/* Email Address Text Box */
	@FindBy(id = "email")
	private WebElement txtEmailAddress;
	/* Mobile Number Text Box */
	@FindBy(name = "mobile")
	private WebElement txtMobileNumber;
	/* Mobile Code Text Box */
	@FindBy(id = "mobilecode")
	private WebElement ddMobileNumberCode;
	/* Proceed Button */
	@FindBy(xpath = "//div[text()='Proceed ']/..")
	private WebElement btnProceed;
	/* Ok Button */
	@FindBy(xpath = "//button[text()='OK']")
	private WebElement btnOk;
	/* Proceed To Payment Button */
	@FindBy(xpath = "//span[text()='Proceed To Payment']")
	private WebElement btnProceedToPayment;
	/* List of options */
	@FindBy(id = "react-autosuggest-1")
	private WebElement sddList;
	/* Round Trip Button */
	@FindBy(id = "roundTrip")
	private WebElement btnRoundTrip;
	/* Return Date Drop down */
	@FindBy(id = "returnCalendar")
	private WebElement ddReturnDate;
	/* Travel Class Drop down */
	@FindBy(id = "gi_class")
	private WebElement ddTravelClass;
	/* Traveller Drop down */
	@FindBy(id = "pax_label")
	private WebElement ddTraveller;
	/* Adult Plus Image */
	@FindBy(id = "adultPaxPlus")
	private WebElement imgAdultPlus;
	/* Children Plus Image */
	@FindBy(id = "childPaxPlus")
	private WebElement imgChildrenPlus;
	/* Infant Plus Image */
	@FindBy(id = "infantPaxPlus")
	private WebElement imgInfantPlus;
	/* From1 Drop down */
	@FindBy(xpath = "(//input[@id='gosuggest_inputSrc'])[1]")
	private WebElement ddFrom1;
	/* From2 Drop down */
	@FindBy(xpath = "(//input[@id='gosuggest_inputSrc'])[2]")
	private WebElement ddFrom2;
	/* From3 Drop down */
	@FindBy(xpath = "(//input[@id='gosuggest_inputSrc'])[3]")
	private WebElement ddFrom3;
	/* From4 Drop down */
	@FindBy(xpath = "(//input[@id='gosuggest_inputSrc'])[4]")
	private WebElement ddFrom4;
	/* Destination1 Drop down */
	@FindBy(xpath = "(//input[@id='gosuggest_inputDest'])[1]")
	private WebElement ddDestination1;
	/* Destination2 Drop down */
	@FindBy(xpath = "(//input[@id='gosuggest_inputDest'])[2]")
	private WebElement ddDestination2;
	/* Destination3 Drop down */
	@FindBy(xpath = "(//input[@id='gosuggest_inputDest'])[3]")
	private WebElement ddDestination3;
	/* Destination4 Drop down */
	@FindBy(xpath = "(//input[@id='gosuggest_inputDest'])[4]")
	private WebElement ddDestination4;
	/* MultiCity Button */
	@FindBy(id = "multiCity")
	private WebElement btnMultiCity;
	/* Departure Date1 Drop down */
	@FindBy(xpath = "(//input[@id='departureCalendar'])[1]")
	private WebElement ddDepartureDate1;
	/* Departure Date2 Drop down */
	@FindBy(xpath = "(//input[@id='departureCalendar'])[2]")
	private WebElement ddDepartureDate2;
	/* Departure Date3 Drop down */
	@FindBy(xpath = "(//input[@id='departureCalendar'])[3]")
	private WebElement ddDepartureDate3;
	/* Departure Date4 Drop down */
	@FindBy(xpath = "(//input[@id='departureCalendar'])[4]")
	private WebElement ddDepartureDate4;
	/* Reset Button */
	@FindBy(xpath = "//span[text()='Reset']")
	private WebElement btnReset;
	/* Add Upto 4 Cities Button */
	@FindBy(xpath = "//span[text()='Add upto 4 cities']")
	private WebElement btnAddUpto4Cities;
	/* Multi Book Button */
	@FindBy(xpath = "//input[@class='button orange fr']")
	private WebElement btnMultiBook;
	/* Hide Multi Check In Flights Check Box */
	@FindBy(xpath = "//span[text()='Hide multi check-in flights']/../preceding-sibling::span")
	private WebElement cbHideMultiCheckInFlights;
	/* Close Tab */
	@FindBy(xpath = "//input[@id='end-date_srp']/following-sibling::i")
	private WebElement tabClose;
	/* Business Button */
	@FindBy(xpath = "//span[text()='Business']")
	private WebElement btnBusiness;
	/* Gst Number Text Box */
	@FindBy(id = "gstn")
	private WebElement txtGstNumber;
	/* Company Name Text Box */
	@FindBy(id = "cname")
	private WebElement txtCompanyName;
	/* Business Email ID Text Box */
	@FindBy(name = "pay_gst_business_email")
	private WebElement txtBusinessEmailID;
	/* Company Address Text Box */
	@FindBy(id = "caddress")
	private WebElement txtCompanyAddress;
	/* Phone Number Text Box */
	@FindBy(id = "phone")
	private WebElement txtPhoneNumber;
	/* State Text Box */
	@FindBy(id = "cstate")
	private WebElement txtState;
	/* Country Text Box */
	@FindBy(id = "country")
	private WebElement txtCountry;

	/**
	 * Description Method enters State Text Box
	 * 
	 * @author Sajal jain
	 * @param state
	 */
	public synchronized void setState(String state) {
		WebActionUtil.waitForElement(txtState, "State Text Box", 20);
		WebActionUtil.typeText(txtState, state, "State Text Box");
	}

	/**
	 * Description Method enters Country Text Box
	 * 
	 * @author Sajal jain
	 * @param country
	 */
	public synchronized void setCountry(String country) {
		WebActionUtil.waitForElement(txtCountry, "Country Text Box", 20);
		WebActionUtil.typeText(txtCountry, country, "Country Text Box");
	}

	/**
	 * Description Method enters Phone Number Text Box
	 * 
	 * @author Sajal jain
	 * @param phoneNumber
	 */
	public synchronized void setPhoneNumber(String phoneNumber) {
		WebActionUtil.waitForElement(txtPhoneNumber, "Phone Number Text Box", 20);
		WebActionUtil.typeText(txtPhoneNumber, phoneNumber, "Phone Number Text Box");
	}

	/**
	 * Description Method enters Company Address Text Box
	 * 
	 * @author Sajal jain
	 * @param companyAddress
	 */
	public synchronized void setCompanyAddress(String companyAddress) {
		WebActionUtil.waitForElement(txtCompanyAddress, "Company Address Text Box", 20);
		WebActionUtil.typeText(txtCompanyAddress, companyAddress, "Company Address Text Box");
	}

	/**
	 * Description Method enters Business Email ID Text Box
	 * 
	 * @author Sajal jain
	 * @param businessEmailID
	 */
	public synchronized void setBusinessEmailID(String businessEmailID) {
		WebActionUtil.waitForElement(txtBusinessEmailID, "Business Email ID Text Box", 20);
		WebActionUtil.typeText(txtBusinessEmailID, businessEmailID, "Business Email ID Text Box");
	}

	/**
	 * Description Method enters Gst Number
	 * 
	 * @author Sajal jain
	 * @param companyName
	 */
	public synchronized void setCompanyName(String companyName) {
		WebActionUtil.waitForElement(txtCompanyName, "Company Name Text Box", 20);
		WebActionUtil.typeText(txtCompanyName, companyName, "Company Name Text Box");
	}

	/**
	 * Description Method enters Gst Number
	 * 
	 * @author Sajal jain
	 * @param gstNumber
	 */
	public synchronized void setGstNumber(String gstNumber) {
		WebActionUtil.waitForElement(txtGstNumber, "Gst Number Text Box", 20);
		WebActionUtil.typeText(txtGstNumber, gstNumber, "Gst Number Text Box");
	}

	/**
	 * Description Method clicks on Business button
	 * 
	 * @author Sajal jain
	 * 
	 */
	public synchronized void clkBusinessBtn() {
		WebActionUtil.scrollToElement(btnBusiness, "Business button");
		WebActionUtil.waitForElement(btnBusiness, "Business button", 45);
		WebActionUtil.clickOnElementUsingJS(btnBusiness, "Business button");
	}

	/**
	 * Description Method clicks on Preferred Airlines filter
	 * 
	 * @author Sajal jain
	 * @param preferredAirlines
	 * 
	 */
	public synchronized void clkPreferredAirlinesFilterCb(String preferredAirlines) {
		WebActionUtil.jsExecutor.executeScript("window.scrollBy(0,4000)");
		WebElement preferredAirlinesFilter = driver
				.findElement(By.xpath("//div[@id='" + preferredAirlines + "']/label/input"));
		WebActionUtil.scrollToElement(preferredAirlinesFilter, "Preferred Airlines filter");
		WebActionUtil.scrollToElement(preferredAirlinesFilter, "Preferred Airlines filter");
		WebActionUtil.waitForElement(preferredAirlinesFilter, "Preferred Airlines filter", 45);
		WebActionUtil.clickOnElementUsingJS(preferredAirlinesFilter, "Preferred Airlines filter");
	}

	/**
	 * Description Method clicks on Return Time filter
	 * 
	 * @author Sajal jain
	 * @param returnTimeFilter
	 */
	public synchronized void clkReturnTimeFilterBtn(String returnTimeFilter) {
		WebElement returnFilter = driver.findElement(By.xpath("(//span[text()='" + returnTimeFilter + "'])[2]"));
		WebActionUtil.scrollToElement(returnFilter, "Return Time filter");
		WebActionUtil.scrollToElement(returnFilter, "Return Time filter");
		WebActionUtil.waitForElement(returnFilter, "Return Time filter", 45);
		WebActionUtil.clickOnElementUsingJS(returnFilter, "Return Time filter");
	}

	/**
	 * Description Method clicks on Departure Time filter
	 * 
	 * @author Sajal jain
	 * @param departureTimeFilter
	 */
	public synchronized void clkDepartureTimeFilterBtn(String departureTimeFilter) {
		WebElement departureFilter = driver.findElement(By.xpath("(//span[text()='" + departureTimeFilter + "'])[1]"));
		WebActionUtil.scrollToElement(departureFilter, "Departure Time filter");
		WebActionUtil.waitForElement(departureFilter, "Departure Time filter", 45);
		WebActionUtil.clickOnElementUsingJS(departureFilter, "Departure Time filter");
	}

	/**
	 * Description Method clicks on Close tab
	 * 
	 * @author Sajal jain
	 * 
	 */
	public synchronized void clkCloseTab() {
		WebActionUtil.waitForElement(tabClose, "Close tab", 45);
		WebActionUtil.clickOnElementUsingJS(tabClose, "Close tab");
	}

	/**
	 * Description Method clicks on Hide multi check-in flights check box
	 * 
	 * @author Sajal jain
	 * 
	 */
	public synchronized void clkHideMultiCheckInFlightsCb() {
		WebActionUtil.scrollToElement(cbHideMultiCheckInFlights, "Hide multi check-in flights check box");
		WebActionUtil.waitForElement(cbHideMultiCheckInFlights, "Hide multi check-in flights check box", 45);
		WebActionUtil.clickOnElementUsingJS(cbHideMultiCheckInFlights, "Hide multi check-in flights check box");
	}

	/**
	 * Description Method clicks on BOOK Button
	 * 
	 * @author Sajal jain
	 * 
	 */
	public synchronized void clkMultiBookBtn() {
		WebActionUtil.waitForElement(btnMultiBook, "BOOK Button", 45);
		WebActionUtil.clickOnElementUsingJS(btnMultiBook, "BOOK Button");
	}

	/**
	 * Description Method clicks on Add upto 4 cities Button
	 * 
	 * @author Sajal jain
	 * 
	 */
	public synchronized void clkAddUpto4CitiesBtn() {
		WebActionUtil.waitForElement(btnAddUpto4Cities, "Add upto 4 cities Button", 45);
		WebActionUtil.clickOnElementUsingJS(btnAddUpto4Cities, "Add upto 4 cities Button");
	}

	/**
	 * Description Method clicks on Reset Button
	 * 
	 * @author Sajal jain
	 * 
	 */
	public synchronized void clkResetBtn() {
		WebActionUtil.waitForElement(btnReset, "Reset Button", 45);
		WebActionUtil.clickOnElementUsingJS(btnReset, "Reset Button");
	}

	/**
	 * Description Method Select Date From Departure1 Drop down
	 * 
	 * @author Sajal jain
	 * @param departureDate1
	 * @throws ParseException 
	 */
	public synchronized void selectDeparture1DD(String year,String month,String date) throws ParseException {
		WebActionUtil.waitForElement(ddDepartureDate1, "Departure1 Drop down", 30);
		selectDate(ddDepartureDate1, year, month, date);
		/*	WebActionUtil.waitForElement(ddDepartureDate1, "Departure1 Drop down", 30);
		WebActionUtil.clickOnElementUsingJS(ddDepartureDate1, "Departure1 Drop down");
		WebElement sddDate = driver.findElement(By.id("" + departureDate1 + ""));
		while (!sddDate.isDisplayed()) {
			WebActionUtil.waitForElement(btnForwardArrow, "Forward Arrow Button", 30);
			WebActionUtil.clickOnElementUsingJS(btnForwardArrow, "Forward Arrow Button");
		}
		sddDate.click();*/
	}

	/**
	 * Description Method Select Date From Departure2 Drop down
	 * 
	 * @author Sajal jain
	 * @param departureDate2
	 */
	public synchronized void selectDeparture2DD(String departureDate2) {
		WebActionUtil.waitForElement(ddDepartureDate2, "Departure2 Drop down", 30);
		WebActionUtil.clickOnElementUsingJS(ddDepartureDate2, "Departure2 Drop down");
		WebElement sddDate = driver.findElement(By.id("" + departureDate2 + ""));
		while (!sddDate.isDisplayed()) {
			WebActionUtil.waitForElement(btnForwardArrow, "Forward Arrow Button", 30);
			WebActionUtil.clickOnElementUsingJS(btnForwardArrow, "Forward Arrow Button");
		}
		sddDate.click();
	}

	/**
	 * Description Method Select Date From Departure3 Drop down
	 * 
	 * @author Sajal jain
	 * @param departureDate3
	 */
	public synchronized void selectDeparture3DD(String departureDate3) {
		WebActionUtil.waitForElement(ddDepartureDate3, "Departure3 Drop down", 30);
		WebActionUtil.clickOnElementUsingJS(ddDepartureDate3, "Departure3 Drop down");
		WebElement sddDate = driver.findElement(By.id("" + departureDate3 + ""));
		while (!sddDate.isDisplayed()) {
			WebActionUtil.waitForElement(btnForwardArrow, "Forward Arrow Button", 30);
			WebActionUtil.clickOnElementUsingJS(btnForwardArrow, "Forward Arrow Button");
		}
		sddDate.click();
	}

	/**
	 * Description Method Select Date From Departure4 Drop down
	 * 
	 * @author Sajal jain
	 * @param departureDate4
	 */
	public synchronized void selectDeparture4DD(String departureDate4) {
		WebActionUtil.waitForElement(ddDepartureDate4, "Departure4 Drop down", 30);
		WebActionUtil.clickOnElementUsingJS(ddDepartureDate4, "Departure4 Drop down");
		WebElement sddDate = driver.findElement(By.id("" + departureDate4 + ""));
		while (!sddDate.isDisplayed()) {
			WebActionUtil.waitForElement(btnForwardArrow, "Forward Arrow Button", 30);
			WebActionUtil.clickOnElementUsingJS(btnForwardArrow, "Forward Arrow Button");
		}
		sddDate.click();
	}

	/**
	 * Description Method enters Destination1 Drop down
	 * 
	 * @author Sajal jain
	 * @param destination1
	 * 
	 */
	public synchronized void selectDestination1DD(String destination1) throws InterruptedException {
		WebActionUtil.waitForElement(ddDestination1, "Destination1 Drop down", 30);
		WebActionUtil.typeText(ddDestination1, destination1, "Destination1 Drop down");
		WebActionUtil.waitForElement(sddList, "Destination1 List", 30);
		ddDestination1.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
	}

	/**
	 * Description Method enters Destination2 Drop down
	 * 
	 * @author Sajal jain
	 * @param destination2
	 */
	public synchronized void selectDestination2DD(String destination2) throws InterruptedException {
		WebActionUtil.waitForElement(ddDestination2, "Destination2 Drop down", 30);
		WebActionUtil.typeText(ddDestination2, destination2, "Destination2 Drop down");
		WebActionUtil.waitForElement(sddList, "Destination2 List", 30);
		ddDestination2.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
	}

	/**
	 * Description Method enters Destination3 Drop down
	 * 
	 * @author Sajal jain
	 * @param destination3
	 */
	public synchronized void selectDestination3DD(String destination3) throws InterruptedException {
		WebActionUtil.waitForElement(ddDestination3, "Destination3 Drop down", 30);
		WebActionUtil.typeText(ddDestination3, destination3, "Destination3 Drop down");
		new WebDriverWait(driver, 30).until(ExpectedConditions.textToBePresentInElement(ddDestination3, destination3));
		WebActionUtil.waitForElement(sddList, "Destination3 List", 30);
		ddDestination3.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
	}

	/**
	 * Description Method enters Destination4 Drop down
	 * 
	 * @author Sajal jain
	 * @param destination4
	 */
	public synchronized void selectDestination4DD(String destination4) throws InterruptedException {
		WebActionUtil.waitForElement(ddDestination4, "Destination4 Drop down", 30);
		WebActionUtil.typeText(ddDestination4, destination4, "Destination4 Drop down");
		WebActionUtil.waitForElement(sddList, "Destination4 List", 30);
		ddDestination4.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
	}

	/**
	 * Description Method enters From1 Drop down
	 * 
	 * @author Sajal jain
	 * @param from1
	 */
	public synchronized void selectFrom1DD(String from1) throws InterruptedException {
		WebActionUtil.waitForElement(ddFrom1, "From1 Drop down", 30);
		WebActionUtil.typeText(ddFrom1, from1, "From1 Drop down");
		WebActionUtil.waitForElement(sddList, "From1 List", 30);
		ddFrom1.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
	}

	/**
	 * Description Method enters From2 Drop down
	 * 
	 * @author Sajal jain
	 * @param from2
	 */
	public synchronized void selectFrom2DD(String from2) throws InterruptedException {
		WebActionUtil.waitForElement(ddFrom2, "From2 Drop down", 30);
		WebActionUtil.typeText(ddFrom2, from2, "From2 Drop down");
		WebActionUtil.waitForElement(sddList, "From2 List", 30);
		ddFrom2.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
	}

	/**
	 * Description Method enters From3 Drop down
	 * 
	 * @author Sajal jain
	 * @param from3
	 */
	public synchronized void selectFrom3DD(String from3) throws InterruptedException {
		WebActionUtil.waitForElement(ddFrom3, "From3 Drop down", 30);
		WebActionUtil.typeText(ddFrom3, from3, "From3 Drop down");
		WebActionUtil.waitForElement(sddList, "From3 List", 30);
		ddFrom3.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
	}

	/**
	 * Description Method enters From4 Drop down
	 * 
	 * @author Sajal jain
	 * @param from4
	 */
	public synchronized void selectFrom4DD(String from4) throws InterruptedException {
		WebActionUtil.waitForElement(ddFrom4, "From4 Drop down", 30);
		WebActionUtil.typeText(ddFrom4, from4, "From4 Drop down");
		WebActionUtil.waitForElement(sddList, "From4 List", 30);
		ddFrom4.sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
	}

	/**
	 * Description Method clicks on Multi City Button
	 * 
	 * @author Sajal jain
	 * 
	 */
	public synchronized void clkMultiCityBtn() {
		WebActionUtil.waitForElement(btnMultiCity, "Multi City Button", 45);
		WebActionUtil.clickOnElementUsingJS(btnMultiCity, "Multi City Button");
	}

	/**
	 * Description Method clicks on Traveller Drop down
	 * 
	 * @author Sajal jain
	 * @param numberOfAdults,numberOfChildren,numberOfInfants,travelClass
	 */
	public synchronized void selectTravellerDD(int numberOfAdults, int numberOfChildren, int numberOfInfants,
			String travelClass) {
		WebActionUtil.waitForElement(ddTraveller, "Traveller Drop down", 45);
		WebActionUtil.clickOnElementUsingJS(ddTraveller, "Traveller Drop down");
		int i = 1, j = 0, k = 0;
		while (i < numberOfAdults) {
			WebActionUtil.waitForElement(imgAdultPlus, "Adult Plus Image", 45);
			WebActionUtil.clickOnElementUsingJS(imgAdultPlus, "Adult Plus Image");
			i++;
		}
		while (j < numberOfChildren) {
			WebActionUtil.waitForElement(imgChildrenPlus, "Children Plus Image", 45);
			WebActionUtil.clickOnElementUsingJS(imgChildrenPlus, "Children Plus Image");
			j++;
		}
		while (k < numberOfInfants) {
			WebActionUtil.waitForElement(imgInfantPlus, "Infant Plus Image", 45);
			WebActionUtil.clickOnElementUsingJS(imgInfantPlus, "Infant Plus Image");
			k++;
		}
		selectTravelClassDD(travelClass);
	}

	/**
	 * Description Method select Travel Class from Drop down
	 * 
	 * @author Sajal jain
	 * @param travelClass
	 */
	public synchronized void selectTravelClassDD(String travelClass) {
		WebActionUtil.waitForElement(ddTravelClass, " Travel Class Drop down", 45);
		WebActionUtil.selectByText(ddTravelClass, travelClass);
	}

	/**
	 * Description Method Select Date From Return Drop down
	 * 
	 * @author Sajal jain
	 * @param year month date
	 * @throws ParseException 
	 */
	public synchronized void selectReturnDD(String year,String month,String date) throws ParseException {
		WebActionUtil.waitForElement(ddReturnDate, "Return Date Drop down", 30);
		selectDate(ddReturnDate, year, month, date);
	}

	/**
	 * Description Method clicks on Round Trip Button
	 * 
	 * @author Sajal jain
	 * 
	 */
	public synchronized void clkRoundTripBtn() {
		WebActionUtil.waitForElement(btnRoundTrip, "Round Trip Button", 45);
		WebActionUtil.clickOnElementUsingJS(btnRoundTrip, "Round Trip Button");
	}

	/**
	 * Description Method clicks on Proceed To Payment Button
	 * 
	 * @author Sajal jain
	 * 
	 */
	public synchronized void clkProceedToPaymentBtn() {
		WebActionUtil.waitForElement(btnProceedToPayment, "Proceed To Payment Button", 45);
		WebActionUtil.clickOnElementUsingJS(btnProceedToPayment, "Proceed To Payment Button");
	}

	/**
	 * Description Method clicks on Ok Button
	 * 
	 * @author Sajal jain
	 * 
	 */
	public synchronized void clkOkBtn() {
		WebActionUtil.waitForElement(btnOk, "Ok Button", 45);
		WebActionUtil.clickOnElementUsingJS(btnOk, "Ok Button");
	}

	/**
	 * Description Method clicks on Proceed Button
	 * 
	 * @author Sajal jain
	 * 
	 */
	public synchronized void clkProceedBtn() {
		WebActionUtil.waitForElement(btnProceed, "Proceed Button", 45);
		WebActionUtil.clickOnElementUsingJS(btnProceed, "Proceed Button");
	}

	/**
	 * Description Method select Mobile Number Code from Drop down
	 * 
	 * @author Sajal jain
	 * @param mobileCode
	 */
	public synchronized void selectMobileNumberCode(String mobileCode) {
		WebActionUtil.waitForElement(ddMobileNumberCode, " Mobile Number Code Drop down", 45);
		WebActionUtil.selectByText(ddMobileNumberCode, mobileCode);
	}

	/**
	 * Description Method enters Mobile Number
	 * 
	 * @author Sajal jain
	 * @param mobileNumber
	 */
	public synchronized void setMobileNumber(String mobileNumber) {
		WebActionUtil.waitForElement(txtMobileNumber, "Mobile Number Text Box", 20);
		WebActionUtil.typeText(txtMobileNumber, mobileNumber, "Mobile Number Text Box");
	}

	/**
	 * Description Method enters Email Address
	 * 
	 * @author Sajal jain
	 * @param emailAddress
	 */
	public synchronized void setEmailAddress(String emailAddress) {
		WebActionUtil.waitForElement(txtEmailAddress, "Email Address Text Box", 20);
		WebActionUtil.typeText(txtEmailAddress, emailAddress, "Email Address Text Box");
	}

	/**
	 * Description Method enters Last Name
	 * 
	 * @author Sajal jain
	 * @param lastName
	 */
	public synchronized void setLastName(String lastName) {
		WebActionUtil.waitForElement(txtLastName, "Last Name Text Box", 20);
		WebActionUtil.typeText(txtLastName, lastName, "Last Name Text Box");
	}

	/**
	 * Description Method enters Middle Name
	 * 
	 * @author Sajal jain
	 * @param middleName
	 */
	public synchronized void setMiddleName(String middleName) {
		WebActionUtil.waitForElement(txtMiddleName, "Middle Name Text Box", 20);
		WebActionUtil.typeText(txtMiddleName, middleName, "Middle Name Text Box");
	}

	/**
	 * Description Method enters First Name
	 * 
	 * @author Sajal jain
	 * @param firstName
	 */
	public synchronized void setFirstName(String firstName) {
		WebActionUtil.waitForElement(txtFirstName, "First Name Text Box", 20);
		WebActionUtil.typeText(txtFirstName, firstName, "First Name Text Box");
	}

	/**
	 * Description Method select Adult Name Title Drop down
	 * 
	 * @author Sajal jain
	 * @param value
	 */
	public synchronized void selectTitleDD(String titleValue) {
		WebActionUtil.waitForElement(ddAdultNameTitle, "Adult Name Title Drop down", 45);
		WebActionUtil.selectByText(ddAdultNameTitle, titleValue);
	}

	/**
	 * Description Method clicks on I am willing to risk my trip Radio Button
	 * 
	 * @author Sajal jain
	 * 
	 */
	public synchronized void clkIAmWillingToRiskMyTripRb() {
		WebActionUtil.scrollToElement(rbIAmWillingToRiskMyTrip, "I am willing to risk my trip Radio Button");
		WebActionUtil.waitForElement(rbIAmWillingToRiskMyTrip, "I am willing to risk my trip Radio Button", 45);
		WebActionUtil.isElementVisible(rbIAmWillingToRiskMyTrip, "I am willing to risk my trip Radio Button");

		WebActionUtil.clickOnElementUsingJS(rbIAmWillingToRiskMyTrip, "I am willing to risk my trip Radio Button");
	}

	/**
	 * Description Method clicks on BOOK Button
	 * 
	 * @author Sajal jain
	 * 
	 */
	public synchronized void clkBOOKBtn() {
		WebActionUtil.waitForElement(btnBook, "BOOK Button", 45);
		WebActionUtil.clickOnElementUsingJS(btnBook, "BOOK Button");
	}

	/**
	 * Description Method clicks on Search Button
	 * 
	 * @author Sajal jain
	 * 
	 */
	public synchronized void clkSEARCHBtn() {
		WebActionUtil.waitForElement(btnSearch, "Search Button", 45);
		WebActionUtil.clickOnElementUsingJS(btnSearch, "Search Button");
	}

	/**
	 * Description Method clicks on Close Image
	 * 
	 * @author Sajal jain
	 * 
	 */
	public synchronized void clkCloseImg() {
		WebActionUtil.waitForElement(btnClose, "Close Image", 45);
		WebActionUtil.clickOnElementUsingJS(btnClose, "Close Image");
	}

	/**
	 * Description Method clicks on Student Fare Tab
	 * 
	 * @author Sajal jain
	 * 
	 */
	public synchronized void clkStudentFareTab() {
		WebActionUtil.waitForElement(tabStudentFare, "Student Fare Tab", 45);
		WebActionUtil.clickOnElementUsingJS(tabStudentFare, "Student Fare Tab");
	}

	/**
	 * Description Method clicks on One Way Button
	 * 
	 * @author Sajal jain
	 * 
	 */
	public synchronized void clkOneWayBtn() {
		WebActionUtil.waitForElement(btnOneWay, "One Way Button", 45);
		WebActionUtil.clickOnElementUsingJS(btnOneWay, "One Way Button");
	}

	public void selectDate(WebElement calendar, String year, String monthName, String date) throws ParseException {
		// Clicking on calendar to open calendar widget
		calendar.click();

		// Retrieving current year value
		// String currentYear=
		String currentYear = driver.findElement(By.xpath("//div[@class='DayPicker-Caption']"))
				.getText();
		currentYear=currentYear.substring(currentYear.length()-4);
		System.out.println(currentYear);
		// Click on Next arrow till we get desired year
		if (!currentYear.equals(year)) {
			do {
				driver.findElement(By.xpath("//span[@class='DayPicker-NavButton DayPicker-NavButton--next']")).click();
				
			} while (!driver.findElement(By.xpath("//div[@class='DayPicker-Caption']"))
					.getText().substring(driver.findElement(By.xpath("//div[@class='DayPicker-Caption']"))
							.getText().length()-4).equals(year));
		}

		// Select desired month after selecting desired year
		String currentMonth = driver.findElement(By.xpath("//div[@class='DayPicker-Caption']"))
				.getText();
		currentMonth=currentMonth.substring(0, currentMonth.length()-5);
		System.out.println(currentMonth);
		if (!currentMonth.equalsIgnoreCase(monthName)) {
			do {
				driver.findElement(By.xpath("//span[@class='DayPicker-NavButton DayPicker-NavButton--next']")).click();
			} while (!driver.findElement(By.xpath("//div[@class='DayPicker-Caption']"))
					.getText().substring(0, driver.findElement(By.xpath("//div[@class='DayPicker-Caption']"))
							.getText().length()-4).trim().equalsIgnoreCase(monthName));
		}
		// get java month number for desired month
		int javaMonthInt = getMonthJavaInt(monthName);
		List<WebElement> allDateOfDesiredMonth = driver
				.findElements(By.xpath("//div[@class='calDate']"));

		for (WebElement d : allDateOfDesiredMonth) {
			 System.out.println(d.getText());
			if (d.getText().trim().contains(date)) {
				System.out.println("loop");
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
