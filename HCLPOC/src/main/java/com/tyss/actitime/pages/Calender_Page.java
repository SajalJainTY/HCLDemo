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

import com.tyss.acttime.util.WebActionUtil;

/**
 * Description This class has the implementations of the select Date from kendo
 * calender
 * 
 * @author Sajal jain
 */
public class Calender_Page {
	public WebDriver driver;
	public WebActionUtil WebActionUtil;
	public long ETO = 10;

	public Calender_Page(WebDriver driver, long ETO, WebActionUtil WebActionUtil) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.WebActionUtil = WebActionUtil;
		this.ETO = ETO;
	}

	/* Next Link */
	@FindBy(xpath = "//div[@id='datetimepicker_dateview']//div[@class='k-header']//a[contains(@class,'k-nav-next')]")
	private WebElement nextLink;
	/* Mid Link */
	@FindBy(xpath = "//div[@id='datetimepicker_dateview']//div[@class='k-header']//a[contains(@class,'k-nav-fast')]")
	private WebElement midLink;
	/* Previous Link */
	@FindBy(xpath = "//div[@id='datetimepicker_dateview']//div[@class='k-header']//a[contains(@class,'k-nav-prev')]")
	private WebElement previousLink;
	/* All month List */
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
	/* All time List */
	@FindBys({ @FindBy(xpath = "//ul[@id='datetimepicker_timeview']/li") })
	private List<WebElement> allTime;
	/* Calender Date Button */
	@FindBy(xpath = "//span[@aria-controls='datetimepicker_dateview']")
	private WebElement btnCalenderDate;

	/**
	 * Description Method clicks on Calender Date button
	 * 
	 * @author Sajal jain
	 * 
	 */
	public synchronized void clkCalenderBtn() {
		WebActionUtil.scrollToElement(btnCalenderDate, "Calender Date button");
		WebActionUtil.waitForElement(btnCalenderDate, "Calender Date button", 45);
		WebActionUtil.clickOnElementUsingJS(btnCalenderDate, "Calender Date button");
	}

	/**
	 * Description Method Select Year
	 * 
	 * @author Sajal jain
	 * @param year
	 */
	public synchronized void selectYearDD(String year) {

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
	}

	/**
	 * Description Method Select Month
	 * 
	 * @author Sajal jain
	 * @param month
	 */
	public synchronized void selectMonthDD(String month) {
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfAllElements(allMonth));
		WebActionUtil.waitForElement(allMonth.get(Integer.parseInt(month) - 1), "Month Drop down", 30);
		WebActionUtil.clickOnElementUsingJS(allMonth.get(Integer.parseInt(month) - 1), "Month Drop down");
	}

	/**
	 * Description Method Select Date
	 * 
	 * @author Sajal jain
	 * @param date
	 */
	public synchronized void selectDateDD(String date) {
		try {
			new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfAllElements(allDate));
		} catch (Exception e) {
			WebActionUtil.waitForElement(allDate.get(Integer.parseInt(date) - 1), "Date Drop down", 30);
			WebActionUtil.clickOnElementUsingJS(allDate.get(Integer.parseInt(date) - 1), "Date Drop down");
		}
	}

	/**
	 * Description Method Select Time
	 * 
	 * @author Sajal jain
	 * @param time
	 */
	public synchronized void selectTimeDD(String time) {

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
	 * Description Method Select Complete date by passing all arguments
	 * 
	 * @author Sajal jain
	 * @param dateTime
	 */
	public synchronized void selectDate(String dateTime) {
		WebActionUtil.selectDate(midLink, nextLink, previousLink, allMonth, allDate, allTime, btnTime, dateTime);

	}
}
