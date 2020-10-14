package com.nykaa.assignment;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.assignment.practice.BaseTest;

import junit.framework.Assert;

/**
 * Description This class is used for validating item added to cart or not and
 * remove it
 * 
 * @author Sajal jain
 */
public class RemoveItemFromCart extends BaseTest {
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
		/* Identify Brands tab */
		WebElement tbBrands = driver.findElement(By.xpath("//a[text()='brands']"));
		/* move mouse pointer to Brands tab */
		act.moveToElement(tbBrands).perform();
		/* click on required item in dropdown */
		driver.findElement(By.xpath("//a[text()='" + getDataFromExcel("NykaaData", 1, 0) + "']")).click();
		/* click on first product link */
		driver.findElement(By.xpath("(//div[@class='nw_grid_img'])[1]")).click();
		/* navigate to child tab */
		switchWindow();
		/* wait for required item to be visible */
		wait1.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//span[contains(text(),'" + getDataFromExcel("NykaaData", 2, 4) + "')]")));
		/* identify the required item */
		WebElement lipstick = driver
				.findElement(By.xpath("//span[contains(text(),'" + getDataFromExcel("NykaaData", 2, 4) + "')]"));
		/* move mouse pointer to required item */
		act.moveToElement(lipstick).perform();
		/* fetch item name */
		String expectedItemName = lipstick.getText();
		/* wait for visibility of Add to Bag button */
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'"
				+ getDataFromExcel("NykaaData", 2, 4) + "')]/ancestor::a/following-sibling::div/div/button[1]")));
		/* click on Add to Bag button */
		driver.findElement(By.xpath("//span[contains(text(),'" + getDataFromExcel("NykaaData", 2, 4)
				+ "')]/ancestor::a/following-sibling::div/div/button[1]")).click();
		/* wait for success msg of adding product to cart */
		wait1.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//span[text()='Product has been added to bag.']")));
		System.out.println("Expected item Name : " + expectedItemName);
		/* click on Add Bag icon */
		driver.findElement(By.className("AddBagIcon")).click();
		/* fetch all the element in cart */
		List<WebElement> allItemsInCart = driver.findElements(By.xpath("//div[@class='product-name']"));
		boolean flag = false;
		for (WebElement wb : allItemsInCart) {
			String actualItemName = wb.getText();
			if (actualItemName.equals(expectedItemName)) {
				/* Validation for checking added item is present in cart */
				Assert.assertEquals(expectedItemName, actualItemName);
				System.out.println("Expected item is present in cart");
				/* click on remove tab */
				driver.findElement(By.xpath("//div[text()='" + actualItemName + "']/../../following-sibling::i"))
						.click();
				/* wait for visibility of Yes */
				wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Yes']")));
				/* click on Yes button */
				driver.findElement(By.xpath("//button[text()='Yes']")).click();
				/* wait for visibility of success msg */
				wait1.until(ExpectedConditions
						.visibilityOfElementLocated(By.xpath("//div[text()='Your Shopping Bag is Empty']")));
				String actualMsg = driver.findElement(By.xpath("//div[text()='Your Shopping Bag is Empty']")).getText();
				String expectedMsg = getDataFromExcel("NykaaData", 1, 5);
				System.out.println("Expected Msg : " + expectedMsg);
				System.out.println("Actual Msg : " + actualMsg);
				/* validation for message */
				Assert.assertEquals(expectedMsg, actualMsg);
				System.out.println(actualItemName + " product removed from the cart");
				flag = true;
			}
		}
		/* if item not added in cart then below statement will execute */
		if (!flag) {
			System.out.println("Item not added in cart");
		}

	}
}
