package com.tyss.actitime.pages;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.tyss.acttime.util.WebActionUtil;

/**
 * Description This class has the implementations of the Goibibo related methods
 * 
 * @author Sajal jain
 */
public class GoibiboHotel_Page {
	public WebDriver driver;
	public WebActionUtil WebActionUtil;
	public long ETO = 10;

	public GoibiboHotel_Page(WebDriver driver, long ETO, WebActionUtil WebActionUtil) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.WebActionUtil = WebActionUtil;
		this.ETO = ETO;
	}

	/* Hotels Tab */
	@FindBy(xpath = "(//span[text()='Hotels'])[1]")
	private WebElement tabHotels;
	/* Where Drop down */
	@FindBy(css = "input[placeholder='e.g. - Area, Landmark or Hotel Name']")
	private WebElement ddWhere;
	/* Check-In Drop down */
	@FindBy(xpath = "//div[text()='Check-in']/following-sibling::h4")
	private WebElement ddCheckIn;
	/* Check-Out Drop down */
	@FindBy(xpath = "//div[text()='Check-out']/following-sibling::h4")
	private WebElement ddCheckOut;
	/* Guests and Rooms Drop down */
	@FindBy(xpath = "//span[text()='Guests & Rooms']/following-sibling::div")
	private WebElement ddGuestsAndRooms;
	/* Done Button */
	@FindBy(xpath = "//button[text()='Done']")
	private WebElement btnDone;
	/* Search Hotels Button */
	@FindBy(xpath = "//button[text()='Search Hotels']")
	private WebElement btnSearchHotels;
	/* Update Search Button */
	@FindBy(xpath = "//button[text()='UPDATE SEARCH']")
	private WebElement btnUpdateSearch;
	/* Price Range Filter Check box */
	@FindBy(xpath = "//span[text()='Upto ₹2000']")
	private WebElement cbPriceRange;
	/* Sort By Drop down */
	@FindBy(xpath = "//span[text()='Sort By:']")
	private WebElement ddSortBy;
	/* Hotel Image */
	@FindBy(xpath = "//div[contains(@class,'HotelCardstyles__HeadingInfoWrapperD')]")
	private WebElement imgHotel;

	/**
	 * Description Method click on Hotel Image
	 * 
	 * @author Sajal jain
	 * 
	 */
	public synchronized void clkHotelImg() {
		WebActionUtil.waitForElement(imgHotel, "Hotel Image", 45);
		WebActionUtil.isElementVisible(imgHotel, "Hotel Image");
		WebActionUtil.clickOnElementUsingJS(imgHotel, "Hotel Image");
	}

	/**
	 * Description Method select sort by drop down
	 * 
	 * @author Sajal jain
	 * @param sortBy
	 */
	public synchronized void selectSortByDD(String sortBy) {
		WebActionUtil.waitForElement(ddSortBy, "Sort By drop down", 45);
		WebActionUtil.action.moveToElement(ddSortBy).perform();
		WebElement otnSortBy = driver
				.findElement(By.xpath("//span[text()='Price (Low to High)']/preceding-sibling::input"));
		WebActionUtil.waitForElement(otnSortBy, "Sort By drop down", 45);
		WebActionUtil.isElementVisible(otnSortBy, "Sort By drop down");
		WebActionUtil.clickOnElementUsingJS(otnSortBy, "Sort By drop down");
	}

	/**
	 * Description Method clicks on Update Search Button
	 * 
	 * @author Sajal jain
	 * 
	 */
	public synchronized void clkUpdateSearchBtn() {
		WebActionUtil.waitForElement(btnUpdateSearch, "Update Search Button", 45);
		WebActionUtil.isElementVisible(btnUpdateSearch, "Update Search Button");
		WebActionUtil.clickOnElementUsingJS(btnUpdateSearch, "Update Search Button");
	}

	/**
	 * Description Method select Where locations information
	 * 
	 * @author Sajal jain
	 * @param where
	 */
	public synchronized void selectWhereDD(String where) {
		WebActionUtil.waitForElement(ddWhere, "Where Drop down", 20);
		WebActionUtil.typeText(ddWhere, where, "Where Drop down");
		WebElement otnWhere = driver.findElement(
				By.xpath("//input[@placeholder='e.g. - Area, Landmark or Hotel Name']/following-sibling::ul"));
		WebActionUtil.waitForElement(otnWhere, "Where Drop down", 45);
		WebActionUtil.isElementVisible(otnWhere, "Where Drop down");
		WebActionUtil.clickOnElementUsingJS(otnWhere, "Where Drop down");
	}

	/**
	 * Description Method clicks on Search Hotels Button
	 * 
	 * @author Sajal jain
	 * 
	 */
	public synchronized void clkSearchHotelsBtn() {
		WebActionUtil.waitForElement(btnSearchHotels, "Search Hotels Button", 45);
		WebActionUtil.isElementVisible(btnSearchHotels, "Search Hotels Button");
		WebActionUtil.clickOnElementUsingJS(btnSearchHotels, "Search Hotels Button");
	}

	/**
	 * Description Method clicks on Done Button
	 * 
	 * @author Sajal jain
	 * 
	 */
	public synchronized void clkDoneBtn() {
		WebActionUtil.waitForElement(btnDone, "Done Button", 45);
		WebActionUtil.isElementVisible(btnDone, "Done Button");
		WebActionUtil.clickOnElementUsingJS(btnDone, "Done Button");
	}

	/**
	 * Description Method Select From Guests & Rooms Drop down
	 * 
	 * @author Sajal jain
	 *
	 */
	public synchronized void selectGuestsAndRoomsDD() {
		WebActionUtil.waitForElement(ddGuestsAndRooms, "Guests & Rooms Drop down", 30);
		WebActionUtil.clickOnElementUsingJS(ddGuestsAndRooms, "Guests & Rooms Drop down");
	}

	/**
	 * Description Method Select Date From Check-Out Drop down
	 * 
	 * @author Sajal jain
	 * @param checkOutDate
	 */
	public synchronized void selectCheckOutRDD(String checkOutDate) {
		WebActionUtil.waitForElement(ddCheckOut, "Check-out Date Drop down", 30);
		WebActionUtil.clickOnElementUsingJS(ddCheckOut, "Check-out Date Drop down");
		WebElement otnCheckOutDate = driver.findElement(By.xpath("//span[text()='" + checkOutDate + "']"));
		/*
		 * while(!otnCheckInDate.isDisplayed()) {
		 * WebActionUtil.waitForElement(btnForwardArrow, "Forward Arrow Button", 30);
		 * WebActionUtil.clickOnElementUsingJS(btnForwardArrow, "Forward Arrow Button");
		 * }
		 */
		otnCheckOutDate.click();
	}

	/**
	 * Description Method Select Date From Check-In Drop down
	 * 
	 * @author Sajal jain
	 * @param checkInDate
	 */
	public synchronized void selectCheckInDD(String checkInDate) {
		WebActionUtil.waitForElement(ddCheckIn, "Check-in Date Drop down", 30);
		WebActionUtil.clickOnElementUsingJS(ddCheckIn, "Check-in Date Drop down");
		WebElement otnCheckInDate = driver.findElement(By.xpath("//span[text()='" + checkInDate + "']"));
		/*
		 * while(!otnCheckInDate.isDisplayed()) {
		 * WebActionUtil.waitForElement(btnForwardArrow, "Forward Arrow Button", 30);
		 * WebActionUtil.clickOnElementUsingJS(btnForwardArrow, "Forward Arrow Button");
		 * }
		 */
		otnCheckInDate.click();
	}

	/**
	 * Description Method clicks on Country Radio Button
	 * 
	 * @author Sajal jain
	 * @param country
	 */
	public synchronized void clkCountryRb(String country) {
		WebElement rbCountry = driver.findElement(
				By.xpath("//span[text()='Hotels'])[1]//h4[text()='" + country + "']/preceding-sibling::label"));
		WebActionUtil.waitForElement(rbCountry, "Country Radio Button", 45);
		WebActionUtil.clickOnElementUsingJS(rbCountry, "Country Radio Button");
	}

	/**
	 * Description Method clicks on Hotels Tab
	 * 
	 * @author Sajal jain
	 * 
	 */
	public synchronized void clkHotelsTab() {
		WebActionUtil.waitForElement(tabHotels, "Hotels Tab", 45);
		WebActionUtil.clickOnElementUsingJS(tabHotels, "Hotels Tab");
	}

}
