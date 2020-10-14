package com.assignment.practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Set;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class BaseTest {
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
		
		wait= new WebDriverWait(driver, seconds);
		wait.until(ExpectedConditions.visibilityOf(element));
		Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(element)) != null);
		
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


}
