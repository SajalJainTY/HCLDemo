package com.tyss.acttime.util;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.joda.time.DateTime;
import org.joda.time.Months;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.tyss.acttime.baseutil.BaseTest;
import com.tyss.acttime.reports.ExtentHCLManager;

/**
 * Description: All the action utilities added in this class e.g
 * clickonelement,WaitforElement etc
 * 
 * @author : Shreya U ,Vivek Dogra,Aatish Slatia
 */


public class WebActionUtil {
	public WebDriver driver;
	WebDriverWait wait;
	public long ETO;
	public JavascriptExecutor jsExecutor;
	public Actions action;
	public static TakesScreenshot screenshot;
	public static String mainID;
	
	public WebActionUtil(WebDriver driver, long ETO) {
		this.driver = driver;
		this.ETO = ETO;
		wait = new WebDriverWait(driver, ETO);
		jsExecutor = (JavascriptExecutor) driver;
		action = new Actions(driver);
		screenshot=(TakesScreenshot)driver;
	}

	
	/**
	 * 
	 * Description Method for the pass updation in extent Report,Log file,TestNG
	 *              Report
	 * @author Shreya Ugavekar
	 */

	public static void pass(String message) {
		ExtentHCLManager.getTestReport().pass(MarkupHelper.createLabel(message, ExtentColor.GREEN));
	}
	/**Description Method to provide info in the log,extent reports,testNg reports
	 * @author Shreya Ugavekar
	 * 
	 */
	public static void info(String message) {
		Reporter.log(message, true);
		BaseTest.logger.info(message);
		ExtentHCLManager.getTestReport().info(message);
	
	}

	/**
	 * 
	 * Description Method for the Warning updation in extent Report,Log file,TestNG
	 *              Report
	 *  @author Shreya Ugavekar
	 */
	
	public void warn(String message) {

		BaseTest.logger.warn(message);
		Reporter.log(message, true);
	}

	/**
	 * 
	 * Description Method for the error/Failure updation in extent Report,Log
	 *              file,TestNG Report
	 *  @author Shreya Ugavekar
	 *              
	 */

	public static void fail(String message) {
		Reporter.log(message, true);
		ExtentHCLManager.getTestReport().fail(MarkupHelper.createLabel(message, ExtentColor.RED));

	}
	public void error(String message) {

		BaseTest.logger.error(message);
		Reporter.log(message, true);
		ExtentHCLManager.getTestReport().error(message);
	}/**
	 * 
	 * Description Method for fetching of get Current Date Time
	 *  @author Shreya Ugavekar
	 *              
	 */

	public static String getCurrentDateTime() {
		DateFormat customFormat = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");
		Date currentDate = new Date();
		return customFormat.format(currentDate);
	}
	/**
	 * 
	 * Description Method clicks on web element
	 *  @author Shreya Ugavekar
	 *              
	 */

	public synchronized void clickOnWebElement(WebElement element, String elementName,String message) {
	     		if (isElementClickable(element, elementName)) {
				element.click();
				pass("Click on " + elementName);
			}
			else {
				fail(message);
				Assert.assertTrue(wait.until(ExpectedConditions.elementToBeClickable(element))==null);
		} 	
	}

	/**
	 * 
	 * Description Check whether the Element is displayed with expected conditions
	 * @author Shreya Ugavekar
	 * @param element elementName
	 */
	public synchronized boolean isElementClickable(WebElement element, String elementName) {

		info("Verify "+elementName+" is Clickable or Not");
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			wait.until(ExpectedConditions.elementToBeClickable(element));
			return true;
		} catch (Exception e) {
			fail(elementName + " is not Clickable ");
			return false;
		}
	}
	
	public void waitForElement(WebElement element, String eleName,long seconds) {
		try {
			
			wait= new WebDriverWait(driver, seconds);
			wait.until(ExpectedConditions.visibilityOf(element));
			Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(element)) != null);
			
		} catch (Exception e) {
			fail( "Element is not visible---------" + eleName);
			
		}
	}
	public static void deleteDir(String pathToDeleteFolder) {
		File extefolder = new File(pathToDeleteFolder);
		if ((extefolder.exists())) {
			deleteFolderDir(extefolder);
		}
	}
	public static void deleteFolderDir(File folderToDelete) {
			File[] folderContents = folderToDelete.listFiles();
			if (folderContents != null) {
				for (File folderfile : folderContents) {
					if (!Files.isSymbolicLink(folderfile.toPath())) {
						deleteFolderDir(folderfile);
					}
				}

			}
			folderToDelete.delete();
		}
	/**
	 * Description Capture the screenshot of the complete screen 
	 * @author Shreya Ugavekar
	 * @param path driver
	 */
	public static String getScreenShot(String path) {

		File src = (screenshot.getScreenshotAs(OutputType.FILE));
		String destinationPath = path + getCurrentDateTime() + ".png";
		File destination = new File(destinationPath);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return destinationPath;
	}
	
	/**
	 * Description To Enter the Text to the Text filed 
	 * @author Shreya Ugavekar 
	 * @param element value elementName
	 */
	public synchronized void typeText(WebElement element, String value, String elementName) {
		try {
			info("Enter the value into " + elementName);
			element.sendKeys(value);
			pass("User is able to type " + value + " into " + elementName);
		} catch (AssertionError error) {
			fail(" User is not able to type " + value + " into " + elementName);
			Assert.fail("Unable to type on " + elementName);
		} catch (Exception e) {
			fail(" User is not able to type " + value + "into " + elementName);
			Assert.fail("Unable to type in " + elementName);
		}
	}
	
	/**
	 * Description To Select the value by the Visible Text
	 * @author Shreya Ugavekar
	 * @param element value elementName
	 */
	public synchronized void selectByText(WebElement element, String value) {
		try {
			info("Select the value " + value);
			Select selecvalue=new Select(element);
			selecvalue.selectByVisibleText(value);
			pass("User is able to select the value"+value);
		} catch (Exception e) {
			fail(" User is not able to Select" + value );
			Assert.fail("Unable to select  " + value);
		}
	}
	
	/**
	 * Description Click on the check box
	 * @author Shreya Ugavekar
	 * @param element  elementName
	 */
	public void clickCheckBox(WebElement element, String elementname) {
		if (element.isSelected()) {
			pass("Already Selected " + elementname);
		} else {
			element.click();
			pass("Selected the " + elementname);
		}
	}
	/**
	 * @author Shreya Ugavekar
	 * @description Scroll to the Element
	 * @param elementName 
	 */
	public void scrollToElement(WebElement element, String elementName)  {
		info("-------------Scrolling till the Element------------");
		try {
			jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
			pass("-------------Scrolling till the Element Completed------------");
		} catch (Exception e) {
		fail("-------------Scroll Till the Element Has Failed ------------");
		}

	}
	/**
	 * Description Click on the Element using JavaSCript 
	 * @author Shreya Ugavekar
	 * @param element  elementName
	 */
	public void clickOnElementUsingJS(WebElement element, String elementName) {
		try {
			if (isElementClickable(element, elementName)) {
				pass("User is able to click " + " into " + elementName);
				jsExecutor.executeScript("arguments[0].click();", element);
			}
		} catch (NoSuchElementException e) {
			pass("User is not able to click " + " into " + elementName);
			Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(element)) == null);
		}
	}
	/**
	 * Description Double Click On Element
	 * @author Shreya Ugavekar
	 * @param element  elementName
	 */
	public void doubleClickOnElement(WebElement element, String elementName) {
		try {
			pass("User is able to click " + " into " + elementName);
			action.doubleClick(element).perform();
		} catch (Exception e) {
			fail(" User is not able to double click on  " + elementName);
			Assert.fail("Unable to Double click on  " + elementName);
		}
	}
	

	/**
	 * Description Clear the Text
	 * @author Shreya Ugavekar
	 * @param element elementName
	 */
	public void clearText(WebElement element, String elementName) {
		try {
		info("Clear the Text Present in" + elementName);
		element.clear();
		pass("Cleared the Text Present in" + elementName);
		}catch (Exception e) {
		fail("Unable to clear the text in "+elementName);
		}
	}
	/**
	 * Description To create the duration of the Test Run
	 * @author Aatish Slathia 
	 * @param element elementName
	 */
	public static String formatDuration(final long millis) {
	  	   long seconds = (millis / 1000) % 60;
	  	   long minutes = (millis / (1000 * 60)) % 60;
	  	   long hours = millis / (1000 * 60 * 60);

	  	   StringBuilder b = new StringBuilder();
	  	   b.append(hours == 0 ? "00" : hours < 10 ? String.valueOf("0" + hours) :
	  	   String.valueOf(hours));
	  	   b.append(":");
	  	   b.append(minutes == 0 ? "00" : minutes < 10 ? String.valueOf("0" + minutes) :    
	  	   String.valueOf(minutes));
	  	 b.append(":");
	  	   b.append(seconds == 0 ? "00" : seconds < 10 ? String.valueOf("0" + seconds) :
	  	   String.valueOf(seconds));
	  	   return b.toString();
	}
	
	/**
	 * Description Wait for the angular page to load
	 * @author Aatish Slathia
	 * @param element elementName
	 */
	 public void waitForAngularPageLoad() {
	        try {
	            ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
	                @Override
	                public Boolean apply(WebDriver driver) {
	                    return Boolean.valueOf(((JavascriptExecutor) driver).executeScript(
	                            "return (window.angular !== undefined) "
	                                    + "&& (angular.element(document).injector() !== undefined) "
	                                    + "&& (angular.element(document).injector().get('$http').pendingRequests.length === 0)").toString());
	                }
	            };
	            WebDriverWait wait = new WebDriverWait(driver, 30);
	            wait.until(pageLoadCondition);
	        } catch (Exception e) {
	            fail("Unable to load the page correctly");
	        }
	    }
	 
	 /**
		 * Description Verifies  the Text
		 * 
		 * @author Aatish Slathia
		 * @param expected
		 * @param element
		 * @param elementname
		 */
		public synchronized void verifytext(String expected, WebElement element, String elementname) {
			try {
				info("Getting text from " + elementname);
				String actual = element.getText();
				Assert.assertEquals(actual, expected);
			} catch (Exception e) {
				fail("Failed to fetch the text from " + elementname);
			}

		}
		

		/**
		 * Description :Checks whether an element is visible
		 * 
		 * @author Aatish Slathia
		 * @param element
		 * @param elementName
		 * 
		 */
		public synchronized boolean isElementVisible(WebElement element, String elemantName) {

			try {
				wait.until(ExpectedConditions.visibilityOf(element));
			pass(elemantName + " is Visible ");
				return true;
			} catch (Exception e) {
				return false;
			}
		}

	/**
	 * Description :Method to select date from calender
	 * 
	 * @author Sajal jain
	 * @param element date month year
	 * 
	 */
	public void datePicker(WebElement element,String date,String month,String year)
	{
		wait.until(ExpectedConditions.elementToBeClickable(element));
		WebElement selecteddate = driver.findElement(By.xpath("//div[@class='DayPicker-Day DayPicker-Day--selected']/following::div/div/p[text()='"+date+"']"));
		String selectedmonth = driver.findElement(By.xpath("//div[text()='"+month+"']")).getText();
		String selectedyear = driver.findElement(By.xpath("//span[text()='"+year+"']")).getText();
		WebElement dateinput = wait.until(ExpectedConditions.elementToBeClickable(By.id("datepicker")));
		dateinput.sendKeys(date+"/"+month+"/"+year);
		if((selectedmonth==month)&&(selectedyear==year))
		{
			wait.until(ExpectedConditions.elementToBeClickable(selecteddate)).click();
			
		}
		else{
			List<WebElement> next = driver.findElements(By.xpath("//span[@class='DayPicker-NavButton DayPicker-NavButton--next']"));
			for(WebElement nextbutton:next)
			{
				String actualdate = selecteddate.getText();
				nextbutton.click();
				while(actualdate==date)
				{
					wait.until(ExpectedConditions.elementToBeClickable(selecteddate));
					break;
				}
			}
			}
	}
	
	
	public synchronized void waitForTextToAppear(WebElement element,String textToAppear,String elementName) {
	   /*try {
		wait.until(ExpectedConditions.textToBePresentInElement(element, textToAppear));
		pass(textToAppear+" is present in "+elementName);
	   }catch(Exception e) {
		   fail(textToAppear+" is not present in "+elementName);
	   }*/
	   poll(2000);
	   }
	
	
	
	/**
	 * Description :Method to select date from calender
	 * 
	 * @author Sajal jain
	 * @param element date month year
	 * 
	 */
	public synchronized void selectDate(WebElement midLink,WebElement nextLink,WebElement previousLink,List<WebElement> allMonth,List<WebElement> allDate,List<WebElement> allTime,WebElement btnTime,String dateTime)
	{
		/* Split the date time to get only the date part*/
		String date_dd_MM_yyyy[] = (dateTime.split(" ")[0]).split("/");
		
		// get the year difference between current year and year to set in calander
		int yearDiff = Integer.parseInt(date_dd_MM_yyyy[2]) - Calendar.getInstance().get(Calendar.YEAR);

		waitForElement(midLink, "Mid Link", 45);
		clickOnElementUsingJS(midLink, "Mid Link");

		if (yearDiff != 0) {
			// if you have to move next year
			if (yearDiff > 0) {
				for (int i = 0; i < yearDiff; i++) {
					// System.out.println("Year Diff->" + i);
					waitForElement(nextLink, "Next Link", 45);
					clickOnElementUsingJS(nextLink, "Next Link");
				}
			}
			// if you have to move previous year
			else if (yearDiff < 0) {
				for (int i = 0; i < (yearDiff * (-1)); i++) {
					// System.out.println("Year Diff->" + i);
					waitForElement(previousLink, "Previous Link", 45);
					clickOnElementUsingJS(previousLink, "Previous Link");
				}
			}
		}

		wait.until(ExpectedConditions.visibilityOfAllElements(allMonth));
		waitForElement(allMonth.get(Integer.parseInt(date_dd_MM_yyyy[1]) - 1), "Month Drop down", 30);
		clickOnElementUsingJS(allMonth.get(Integer.parseInt(date_dd_MM_yyyy[1]) - 1), "Month Drop down");

		try {
		wait.until(ExpectedConditions.visibilityOfAllElements(allDate));
		} catch (Exception e) {
			waitForElement(allDate.get(Integer.parseInt(date_dd_MM_yyyy[0]) - 1), "Date Drop down", 30);
			clickOnElementUsingJS(allDate.get(Integer.parseInt(date_dd_MM_yyyy[0]) - 1), "Date Drop down");
		}

		waitForElement(btnTime, "Time Button", 45);
		clickOnElementUsingJS(btnTime, "Time Button");
		dateTime = dateTime.split(" ")[1] + " " + dateTime.split(" ")[2];
		wait.until(ExpectedConditions.visibilityOfAllElements(allTime));
		// select correct time
		for (WebElement fetchtime : allTime) {
			if (fetchtime.getText().equalsIgnoreCase(dateTime)) {
				clickOnElementUsingJS(fetchtime, "Time Drop down");
		}
		}
	}
	
	
	
	
	/**
	 * Description :Method to switch to Child Window/Tab
	 * 
	 * @author Sajal jain
	 */
	public synchronized void switchWindow() {
		mainID = driver.getWindowHandle();
		Set<String> allID = driver.getWindowHandles();
		for(String id:allID) {
			if(!id.equals(mainID)) {
				driver.switchTo().window(id);
			}
		}
	}
	
	/**
	 * Description :Method to switch to Main Window/Tab
	 * 
	 * @author Sajal jain
	 */
	public synchronized void switchToMainWindow() {
		driver.switchTo().window(mainID);
	}
	
	/**
	 * Description :Method to Accept Alert Popup
	 * 
	 * @author Sajal jain
	 */
	public synchronized void acceptAlert() {
		Alert alt = driver.switchTo().alert();
		alt.accept();
	}
	
	/**
	 * Description :Method to Dismiss Alert Popup
	 * 
	 * @author Sajal jain
	 */
	public synchronized void dismissAlert() {
		Alert alt = driver.switchTo().alert();
		alt.dismiss();
	}
	
	/**
	 * Description :Method to Validate Text In Alert
	 * 
	 * @author Sajal jain
	 */
	public synchronized void validateAlertMessage(String expectedMessage) {
		try {
		Alert alt = driver.switchTo().alert();
		info("Getting text from Alert Popup");
		String actualMessage = alt.getText();
		Assert.assertEquals(actualMessage, expectedMessage);
		}
		 catch (Exception e) {
			fail("Failed to fetch the text from Alert Popup");
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * Description :Method to handle ajax wait
	 * 
	 * @author Sajal jain
	 */
	public synchronized void ajaxComplete() {
        jsExecutor.executeScript("var callback = arguments[arguments.length - 1];"
            + "var xhr = new XMLHttpRequest();" + "xhr.open('GET', '/Ajax_call', true);"
            + "xhr.onreadystatechange = function() {" + "  if (xhr.readyState == 4) {"
            + "    callback(xhr.responseText);" + "  }" + "};" + "xhr.send();");
    }
 
	/**
	 * Description :Method to wait till Jquery Load
	 * 
	 * @author Sajal jain
	 */
	public synchronized void waitForJQueryLoad() {
        try {
            ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
				@Override
				public Boolean apply(WebDriver driver) {
					return ((Long) ((JavascriptExecutor) WebActionUtil.this.driver)
					    .executeScript("return jQuery.active") == 0);
				}
			};
 
            boolean jqueryReady = (Boolean) jsExecutor.executeScript("return jQuery.active==0");
 
            if (!jqueryReady) {
                wait.until(jQueryLoad);
            }
        } catch (WebDriverException ignored) {
        }
    }
 
 
	/**
	 * Description :Method to wait till JS Ready
	 * 
	 * @author Sajal jain
	 */
	public synchronized void waitUntilJSReady() {
        try {
            ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
				@Override
				public Boolean apply(WebDriver driver) {
					return ((JavascriptExecutor) WebActionUtil.this.driver)
					    .executeScript("return document.readyState").toString().equals("complete");
				}
			};
 
            boolean jsReady = jsExecutor.executeScript("return document.readyState").toString().equals("complete");
 
            if (!jsReady) {
                wait.until(jsLoad);
            }
        } catch (WebDriverException ignored) {
        }
    }
 
	/**
	 * Description :Method to wait till JQuery Ready
	 * 
	 * @author Sajal jain
	 */
	public synchronized void waitUntilJQueryReady() {
        Boolean jQueryDefined = (Boolean) jsExecutor.executeScript("return typeof jQuery != 'undefined'");
        if (jQueryDefined) {
            poll(20);
 
            waitForJQueryLoad();
 
            poll(20);
        }
    }
 
 

	/**
	 * Description :Method to wait till js ready, ajax complete, Jquery ready, angular ready, angular 5  ready
	 * 
	 * @author Sajal jain
	 */
    public synchronized void waitAllRequest() {
        waitUntilJSReady();
        ajaxComplete();
        waitUntilJQueryReady();
    }
 
    /**
     * Method to make sure a specific element has loaded on the page
     *
     *@author Sajal jain
     * @param by
     * @param expected
     */
    public synchronized void waitForElementAreComplete(final By by, final int expected) {
        ExpectedCondition<Boolean> angularLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
			    int loadingElements = WebActionUtil.this.driver.findElements(by).size();
			    return loadingElements >= expected;
			}
		};
        wait.until(angularLoad);
    }
 
    /**
     * Waits for the elements animation to be completed
     * 
     * 
     * @author Sajal jain
     * @param css
     */
    public synchronized void waitForAnimationToComplete(final String css) {
        ExpectedCondition<Boolean> angularLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
			    int loadingElements = WebActionUtil.this.driver.findElements(By.cssSelector(css)).size();
			    return loadingElements == 0;
			}
		};
        wait.until(angularLoad);
    }
    
    /**
     * Wait for the fixed Interval
     * 
     * 
     * @author Sajal jain
     * @param milis
     */
    public synchronized void poll(long milis) {
        try {
            Thread.sleep(milis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    
    
    
    public synchronized Date setDate(String setDateStr) throws ParseException
	{
		Date setDate = new SimpleDateFormat("dd/MM/yyyy").parse(setDateStr);
		System.out.println("set date "+setDate);
		return setDate;
	}
    
    public synchronized Date currentDate(String currentDatesMonthYear) throws ParseException
	{
		Date currentDate = new SimpleDateFormat("MMMM yyyy").parse(currentDatesMonthYear);
		System.out.println("current date "+currentDate);
		return currentDate;
	}
    
    public synchronized int getMonthDiffrence(String currentDatesMonthYear,String setDateStr) throws ParseException
   	{
   		int monthDiff = Months.monthsBetween(new DateTime(currentDate(currentDatesMonthYear)).withDayOfMonth(1),new DateTime(setDate(setDateStr)).withDayOfMonth(1)).getMonths();
   		System.out.println("month diff "+monthDiff);
   		
   		return monthDiff;
   	}

	public synchronized String clickOnDay(String setDeparturedate)
	{
		String day = "";
		try {
			day = new SimpleDateFormat("dd").format(setDate(setDeparturedate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return day;
	}
	public synchronized String getCurrentMonthYear()
	{
		String currentMonthYear = driver.findElement(By.xpath("//p[contains(@class,'dcalendarstyles__MonthNamePara')]")).getText();
		System.out.println("currentMonthYear "+currentMonthYear);
		return currentMonthYear;
	}
	
	
	
	
	
}

