package com.assignment.practice;

import org.testng.AssertJUnit;
import org.testng.AssertJUnit;
import org.testng.AssertJUnit;
import org.testng.AssertJUnit;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Set;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class BaseTest {
private static final WebDriver JavascriptExecutor = null;
public static WebDriver driver;
public static WebDriverWait wait;
public static String mainID;

public static String getDataFromExcel(String sheet,int row,int cell) throws EncryptedDocumentException, IOException {
	FileInputStream fis=new FileInputStream("./MobileData/testData.xlsx");
	Workbook book = WorkbookFactory.create(fis);
	String value=book.getSheet(sheet).getRow(row).getCell(cell).getStringCellValue();
	return value;
}

public static void waitForElement(WebElement element, String eleName,long seconds) {
	try {
		
	//	wait= new WebDriverWait(driver, seconds);
		wait.until(ExpectedConditions.visibilityOf(element));
		AssertJUnit.assertTrue(wait.until(ExpectedConditions.visibilityOf(element)) != null);
		
	} catch (Exception e) {
		System.out.println( "Element is not visible---------" + eleName);
	}
}

public static void switchWindow() {
	mainID = driver.getWindowHandle();
	Set<String> allID = driver.getWindowHandles();
	for(String id:allID) {
		if(!id.equals(mainID)) {
			driver.switchTo().window(id);
		}
	}
}

public static void switchToMainWindow() {
			driver.switchTo().window(mainID);
}

public static void waitForElementUsingLocator(By locator) {
	WebDriverWait wait1=new WebDriverWait(driver,30);
	wait1.until(ExpectedConditions.visibilityOfElementLocated(locator));
}

public static void windowScroll(int x,int y) {
	JavascriptExecutor jse=(JavascriptExecutor)driver;
	jse.executeScript("window.scrollBy("+x+","+y+")");
}


@SuppressWarnings("deprecation")
public static synchronized void waitTillPageLoad(long seconds) {
	WebDriverWait wait = new WebDriverWait(driver, seconds);
	JavascriptExecutor jsExecutor = (JavascriptExecutor)driver;
	// Wait for Javascript to load
	ExpectedCondition<Boolean> jsLoad = wd -> ((JavascriptExecutor) driver)
			.executeScript("return document.readyState").toString().equals("complete");
	// Get JS is Ready
boolean jsReady = (Boolean) jsExecutor.executeScript("return document.readyState").toString().equals("complete");
	// Wait Javascript until it is Ready!
	if (!jsReady) {
		
		// Wait for Javascript to load
		wait.until(jsLoad);
	}
	else 
	{
		
	}
}
}
