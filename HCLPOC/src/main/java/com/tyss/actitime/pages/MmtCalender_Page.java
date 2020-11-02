package com.tyss.actitime.pages;

import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.tyss.acttime.baseutil.BaseTest;
import com.tyss.acttime.util.WebActionUtil;

/**
 * Description This class has the implementations of date in Calender in Make my
 * trip
 * 
 * @author Sajal jain
 * 
 */
public class MmtCalender_Page extends BaseTest {
	public WebDriver driver;
	public WebActionUtil WebActionUtil;
	public long ETO = 10;

	public MmtCalender_Page(WebDriver driver, long ETO, WebActionUtil WebActionUtil) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.WebActionUtil = WebActionUtil;
		this.ETO = ETO;
	}

	/* Departure Button */
	@FindBy(xpath = "(//span[@class='lbl_input latoBold appendBottom10'])[1]")
	private WebElement btnDeparture;
	/* Next Link */
	@FindBy(xpath = "//div[@id='datetimepicker_dateview']//div[@class='k-header']//a[contains(@class,'k-nav-next')]")
	private WebElement nextLink;
	/* Mid Link */
	@FindBy(xpath = "//div[@id='datetimepicker_dateview']//div[@class='k-header']//a[contains(@class,'k-nav-fast')]")
	private WebElement midLink;
	/* Previous Link */
	@FindBy(xpath = "//div[@id='datetimepicker_dateview']//div[@class='k-header']//a[contains(@class,'k-nav-prev')]")
	private WebElement previousLink;
	/* All month list */
	@FindBys({
			@FindBy(xpath = "//div[@id='datetimepicker_dateview']//table//tbody//td[not(contains(@class,'k-other-month'))]") })
	private List<WebElement> allMonth;
	/* All Date List */
	@FindBys({
			@FindBy(xpath = "//div[@id='datetimepicker_dateview']//table//tbody//td[not(contains(@class,'k-other-month'))]") })
	private List<WebElement> allDate;
	/* Time Button */
	@FindBy(xpath = "//span[@aria-controls='datetimepicker_timeview']")
	private WebElement btnTime;
	/* All Time list */
	@FindBys({ @FindBy(xpath = "//ul[@id='datetimepicker_timeview']/li") })
	private List<WebElement> allTime;
	/* Calender Date Button */
	@FindBy(xpath = "//span[@aria-controls='datetimepicker_dateview']")
	private WebElement btnCalenderDate;

	/**
	 * Description Method select a date
	 * 
	 * @author Sajal jain
	 * @param date
	 *            month year
	 */
	public void selectDate(String date, String month, String year) {
		WebActionUtil.waitForElement(btnDeparture, "Departure Button", 45);
		WebActionUtil.clickOnWebElement(btnDeparture, "Departure Button", "Click on Departure Button");
		WebActionUtil.datePicker(btnDeparture, date, month, year);
	}

	/**
	 * Description Method Select Date when date given iin separate cells
	 * 
	 * @author Sajal jain
	 * @param year month date time
	 */
	public synchronized void clkCalenderBtn(String year, String month, String date, String time) {
		WebActionUtil.scrollToElement(btnCalenderDate, "Calender Date button");
		WebActionUtil.waitForElement(btnCalenderDate, "Calender Date button", 45);
		WebActionUtil.clickOnElementUsingJS(btnCalenderDate, "Calender Date button");

		// get the year difference between current year and year to set in calander
		int yearDiff = Integer.parseInt(year) - Calendar.getInstance().get(Calendar.YEAR);

		WebActionUtil.waitForElement(midLink, "Mid Link", 45);
		WebActionUtil.clickOnElementUsingJS(midLink, "Mid Link");

		if (yearDiff != 0) {
			// if you have to move next year
			if (yearDiff > 0) {
				for (int i = 0; i < yearDiff; i++) {
					// System.out.println("Year Diff->" + i);
					WebActionUtil.waitForElement(nextLink, "Next Link", 45);
					WebActionUtil.clickOnElementUsingJS(nextLink, "Next Link");
				}
			}
			// if you have to move previous year
			else if (yearDiff < 0) {
				for (int i = 0; i < (yearDiff * (-1)); i++) {
					// System.out.println("Year Diff->" + i);
					WebActionUtil.waitForElement(previousLink, "Previous Link", 45);
					WebActionUtil.clickOnElementUsingJS(previousLink, "Previous Link");
				}
			}
		}

		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfAllElements(allMonth));
		WebActionUtil.waitForElement(allMonth.get(Integer.parseInt(month) - 1), "Month Drop down", 30);
		WebActionUtil.clickOnElementUsingJS(allMonth.get(Integer.parseInt(month) - 1), "Month Drop down");

		try {
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfAllElements(allDate));
		} catch (Exception e) {
			WebActionUtil.waitForElement(allDate.get(Integer.parseInt(date) - 1), "Date Drop down", 30);
			WebActionUtil.clickOnElementUsingJS(allDate.get(Integer.parseInt(date) - 1), "Date Drop down");
		}

		WebActionUtil.waitForElement(btnTime, "Time Button", 45);
		WebActionUtil.clickOnElementUsingJS(btnTime, "Time Button");

		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfAllElements(allTime));
		// select correct time
		for (WebElement fetchtime : allTime) {
			if (fetchtime.getText().equalsIgnoreCase(time)) {
				WebActionUtil.clickOnElementUsingJS(fetchtime, "Time Drop down");
			}

		}
	}

	/**
	 * Description Method Select Date when date given in single cells
	 * 
	 * @author Sajal jain
	 * @param dateTime
	 */
	public synchronized void selectDate(String dateTime) {

		WebActionUtil.scrollToElement(btnCalenderDate, "Calender Date button");
		WebActionUtil.waitForElement(btnCalenderDate, "Calender Date button", 45);
		WebActionUtil.clickOnElementUsingJS(btnCalenderDate, "Calender Date button");
		/* Split the date time to get only the date part */
		String date_dd_MM_yyyy[] = (dateTime.split(" ")[0]).split("/");

		// get the year difference between current year and year to set in calander
		int yearDiff = Integer.parseInt(date_dd_MM_yyyy[2]) - Calendar.getInstance().get(Calendar.YEAR);

		WebActionUtil.waitForElement(midLink, "Mid Link", 45);
		WebActionUtil.clickOnElementUsingJS(midLink, "Mid Link");

		if (yearDiff != 0) {
			// if you have to move next year
			if (yearDiff > 0) {
				for (int i = 0; i < yearDiff; i++) {
					// System.out.println("Year Diff->" + i);
					WebActionUtil.waitForElement(nextLink, "Next Link", 45);
					WebActionUtil.clickOnElementUsingJS(nextLink, "Next Link");
				}
			}
			// if you have to move previous year
			else if (yearDiff < 0) {
				for (int i = 0; i < (yearDiff * (-1)); i++) {
					// System.out.println("Year Diff->" + i);
					WebActionUtil.waitForElement(previousLink, "Previous Link", 45);
					WebActionUtil.clickOnElementUsingJS(previousLink, "Previous Link");
				}
			}
		}

		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfAllElements(allMonth));
		WebActionUtil.waitForElement(allMonth.get(Integer.parseInt(date_dd_MM_yyyy[1]) - 1), "Month Drop down", 30);
		WebActionUtil.clickOnElementUsingJS(allMonth.get(Integer.parseInt(date_dd_MM_yyyy[1]) - 1), "Month Drop down");

		try {
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfAllElements(allDate));
		} catch (Exception e) {
			WebActionUtil.waitForElement(allDate.get(Integer.parseInt(date_dd_MM_yyyy[0]) - 1), "Date Drop down", 30);
			WebActionUtil.clickOnElementUsingJS(allDate.get(Integer.parseInt(date_dd_MM_yyyy[0]) - 1),
					"Date Drop down");
		}

		WebActionUtil.waitForElement(btnTime, "Time Button", 45);
		WebActionUtil.clickOnElementUsingJS(btnTime, "Time Button");
		dateTime = dateTime.split(" ")[1] + " " + dateTime.split(" ")[2];
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfAllElements(allTime));
		// select correct time
		for (WebElement fetchtime : allTime) {
			if (fetchtime.getText().equalsIgnoreCase(dateTime)) {
				WebActionUtil.clickOnElementUsingJS(fetchtime, "Time Drop down");
			}

		}
	}

}
