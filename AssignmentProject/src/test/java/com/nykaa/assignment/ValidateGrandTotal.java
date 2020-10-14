package com.nykaa.assignment;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.assignment.practice.BaseTest;

import junit.framework.Assert;

/**
 * Description This class is used for validate item added to cart and validate
 * the grand total is correct
 * 
 * @author Sajal jain
 */
public class ValidateGrandTotal extends BaseTest {
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
		WebDriverWait wait1 = new WebDriverWait(driver, 30);
		/* Identify Brands tab */
		WebElement tbBrands = driver.findElement(By.xpath("//a[text()='brands']"));
		/* move mouse pointer to Brands tab */
		act.moveToElement(tbBrands).perform();
		/* click on required item in given tab */
		driver.findElement(By.xpath("//a[text()='" + getDataFromExcel("NykaaData", 1, 0) + "']")).click();
		/* click on first product link */
		driver.findElement(By.xpath("(//div[@class='nw_grid_img'])[1]")).click();
		/* navigate to child tab */
		switchWindow();
		/*wait for price filter to be visible*/
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Price']")));
		/* click on Price filter tab */
		driver.findElement(By.xpath("//div[text()='Price']")).click();
		/* click on required value in Price filter */
		driver.findElement(By
				.xpath("//span[contains(text(),'" + getDataFromExcel("NykaaData", 1, 1) + "')]/following-sibling::div"))
				.click();
		/* wait for color filter to be visible */
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Color']")));
		/* click on Color filter tab */
		driver.findElement(By.xpath("//div[text()='Color']")).click();
		/* click on required value from color filter */
		driver.findElement(By
				.xpath("//span[contains(text(),'" + getDataFromExcel("NykaaData", 1, 2) + "')]/following-sibling::div"))
				.click();

		
		
		
		
		/* wait for category filter to be visible */
		/*
		 * wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
		 * "//div[text()='Category']"))); WebElement categoryFilter =
		 * driver.findElement(By.xpath("//div[text()='Category']"));
		 * waitForElement(categoryFilter, "Category Filter", 40);
		 * wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
		 * "//div[text()='Category']")));
		 * wait1.until(ExpectedConditions.elementToBeClickable(categoryFilter)); click
		 * on Category filter tab categoryFilter.click();
		 */
		/* click on category filter */
		Thread.sleep(5000);
		// wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Category']")));
		driver.findElement(By.xpath("//div[text()='Category']")).click();

		
		
		
		/* identify required value in category filter */
		WebElement lipstick = driver.findElement(By.xpath(
				"//span[contains(text(),'" + getDataFromExcel("NykaaData", 1, 3) + "')]/following-sibling::div"));
		/* wait for required value in category filter */
		waitForElement(lipstick, "lipstick check box", 40);
		/* click on required value in Category filter */
		lipstick.click();
		/* identify the required item */
		WebElement item = driver.findElement(By.xpath("//span[text()='" + getDataFromExcel("NykaaData", 1, 4) + "']"));
		/* fetch the name of the item */
		String expectedItemName = item.getText();
		/* move mouse pointer to required item */
		act.moveToElement(item).perform();
		/* wait for visibility of Add to Bag button */
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'"
				+ getDataFromExcel("NykaaData", 1, 4) + "')]/ancestor::a/following-sibling::div/div/button[1]")));
		/* click on Add to Bag button */
		driver.findElement(By.xpath("//span[contains(text(),'" + getDataFromExcel("NykaaData", 1, 4)
				+ "')]/ancestor::a/following-sibling::div/div/button[1]")).click();
		/* click on Add Bag icon */
		driver.findElement(By.className("AddBagIcon")).click();
		/* fetch the item present in cart */
		WebElement itemInCart = driver.findElement(
				By.xpath("//div[@class='product-name' and text()='" + getDataFromExcel("NykaaData", 1, 4) + "']"));
		/* fetch the item name present in cart */
		String actualItemName = itemInCart.getText();
		System.out.println("Expected Item Name :" + expectedItemName);
		System.out.println("Actual Item Name : " + actualItemName);
		/* validate item add to cart is matched with item present in cart */
		Assert.assertEquals(expectedItemName, actualItemName);
		System.out.println("Item present in cart is matched with Item added");
		/* Fetch Bag Total value */
		String bagTotal = driver.findElement(By.xpath("//div[text()='Bag Total']/following-sibling::div")).getText();
		bagTotal = bagTotal.substring(1);
		/* fetch Sub Total value */
		String subTotal = driver.findElement(By.xpath("//div[text()='Sub Total']/following-sibling::div")).getText();
		subTotal = subTotal.substring(1);
		/* fetch Shipping charge */
		String shippingCharge = driver.findElement(By.xpath("//div[text()='Shipping Charge']/following-sibling::div"))
				.getText();
		int shippingAmount = 0;
		if (shippingCharge.equals("Free")) {
			System.out.println("No shipping included");
		} else {
			shippingCharge = shippingCharge.substring(1);
			shippingAmount = Integer.parseInt(shippingCharge);
		}
		/* fetch Grand Total value */
		String grandTotal = driver.findElement(By.xpath("//div[text()='Grand Total']/following-sibling::div"))
				.getText();
		grandTotal = grandTotal.substring(1);
		System.out.println("bag Total price : " + bagTotal);
		System.out.println("Sub Total price : " + subTotal);
		System.out.println("Shipping charge : " + shippingCharge);
		System.out.println("Grand Total : " + grandTotal);
		/* convert string to int for arthmetic operation */
		int bagAmount = Integer.parseInt(bagTotal);
		int subAmount = Integer.parseInt(subTotal);
		int grandAmount = Integer.parseInt(grandTotal);
		/* validation of Grand Total amount */
		if (bagAmount + shippingAmount == grandAmount || subAmount + shippingAmount == grandAmount) {
			System.out.println("Grand total amount is correct");
		}

	}
}
