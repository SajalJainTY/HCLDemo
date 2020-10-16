package com.nykaa.assignment;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.assignment.practice.BaseTest;

import junit.framework.Assert;

public class ValidateNotifyMail extends BaseTest{
	public static void main(String[] args) throws EncryptedDocumentException, IOException, InterruptedException {
	/* change browser backend keys to block browser native popup */
	HashMap p = new HashMap();
	p.put("profile.default_content_setting_values.notifications", 2);
	ChromeOptions o = new ChromeOptions();
	o.setExperimentalOption("prefs", p);
	/* launch the browser */
	driver = new ChromeDriver(o);
	/* wait for elements to load */
	driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	/* navigate to given url */
	driver.get("https://www.nykaa.com/");
	/* maximize the browser */
	driver.manage().window().maximize();
	Actions act = new Actions(driver);
	/* Class for using dynamic wait */
	WebDriverWait wait1 = new WebDriverWait(driver, 30);
	
	driver.findElement(By.className("AccountText")).click();
	
	driver.findElement(By.cssSelector("input[placeholder='Enter Phone Number or Email']")).click();
	By emailID = By.xpath("//input[@placeholder='Enter Email ID or Phone Number']");
	driver.findElement(emailID).sendKeys(getDataFromExcel("NykaaData", 16, 0));

	driver.findElement(By.xpath("//button[@type='submit']")).click();
	waitForElementUsingLocator(By.xpath("//button[@type='submit']"));
	driver.findElement(By.xpath("//button[@type='submit']")).click();
	driver.findElement(By.xpath("//div[text()='Login via Password']")).click();	
	waitForElementUsingLocator(By.id("secure-input"));

	driver.findElement(By.id("secure-input")).sendKeys(getDataFromExcel("NykaaData", 15, 1));

	driver.findElement(By.xpath("//button[text()='login']")).click();
	
	/*driver.findElement(By.xpath("//span[text()='Google']")).click();
	driver.findElement(By.xpath("//div[text()='Use another account']")).click();
	driver.findElement(By.id("identifierId")).sendKeys(getDataFromExcel("NykaaData", 15, 0),Keys.ENTER);
	driver.findElement(By.name("password")).sendKeys(getDataFromExcel("NykaaData", 15, 1),Keys.ENTER);
	
	*/
	
	waitTillPageLoad(20);
	wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='appliances']")));
	act.moveToElement(driver.findElement(By.xpath("//a[text()='appliances']"))).perform();
	driver.findElement(By.xpath("//div[text()='Top Brands']/..//a[text()='Philips']")).click();
	
	switchWindow();
	windowScroll(0, 3500);
	driver.findElement(By.xpath("//span[contains(text(),'Page 1')]/..//span[text()='"+getDataFromExcel("NykaaData", 15, 2)+"']/..")).click();
	
	WebElement item = driver
			.findElement(By.xpath("//span[contains(text(),'"+getDataFromExcel("NykaaData", 15, 3)+"')]"));
	/* fetch item name */
	String expectedItemName = item.getText();
	/* move mouse pointer to required item */
	act.moveToElement(item).perform();
	System.out.println(expectedItemName);
	/* wait for visibility of WishList */
	By wishList =By.xpath("(//span[contains(text(),'"+getDataFromExcel("NykaaData", 15, 3)+"')]/ancestor::a/following-sibling::div/div/div/div/div)[1]");
	waitForElementUsingLocator(wishList);
	/* click on Wish List */
	driver.findElement(wishList).click();
	
	//String expectedSuccessMsg="";

	waitForElementUsingLocator(By.xpath("//span[contains(text(),'Product has been added to Wishlist.')]"));	
	
	WebElement accountName = driver.findElement(By.className("AccountText"));
	act.moveToElement(accountName).perform();
	
	driver.findElement(By.xpath("//a[text()='My Wishlist']")).click();
	
	List<WebElement> allItemsInCart = driver.findElements(By.xpath("//div[@class='product-name']"));
	
	for (WebElement wb : allItemsInCart) {
		String actualItemName = wb.getText();
			/* Validation for same item present in cart */
			Assert.assertTrue(actualItemName.equals(expectedItemName));
			System.out.println("Expected item is present in cart");
			driver.findElement(By.xpath("//div[@class='product-name']/../..//span[text()='Notify Me']")).click();
			
			driver.findElement(By.cssSelector("input[placeholder='Your Email']")).sendKeys("sajjujain1012@gmail.com");
			
			driver.findElement(By.xpath("(//button[@type='submit'])[2]")).click();
			By successMsg=By.xpath("//span[text()='You are subscribed to this notification.']");
			waitForElementUsingLocator(successMsg);
			String actualSuccessNotificationMsg = driver.findElement(successMsg).getText();
			String expectedSuccessNotificationMsg=getDataFromExcel("NykaaData", 15, 4);
			Assert.assertEquals(expectedSuccessNotificationMsg, actualSuccessNotificationMsg);
			System.out.println("Successfully subcribed to Notification ");
		}
	
}

}
