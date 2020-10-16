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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.assignment.practice.BaseTest;

import junit.framework.Assert;

public class ValidateShade extends BaseTest{
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
		driver.findElement(By.xpath("//a[text()='"+getDataFromExcel("NykaaData", 10, 0)+"']")).click();
		
		windowScroll(0, 1000);
		
		/*wait for price filter to be visible*/
		By tabPrice = By.xpath("//div[text()='Price']");
		waitForElementUsingLocator(tabPrice);
		/* click on Price filter tab */
		driver.findElement(tabPrice).click();
		/* click on required value in Price filter */
		driver.findElement(By
				.xpath("//span[contains(text(),'"+getDataFromExcel("NykaaData", 10, 1)+"')]/following-sibling::div"))
				.click();

		/*wait for Preference filter to be visible*/
		By tabPreference = By.xpath("//div[text()='Preference']");
		waitForElementUsingLocator(tabPreference);
		wait1.until(ExpectedConditions.elementToBeClickable(tabPreference));
		waitTillPageLoad(30);
		/* click on Preference filter tab */
		Thread.sleep(3000);
		driver.findElement(tabPreference).click();
		/* click on required value in Preference filter */
		driver.findElement(By
				.xpath("//span[contains(text(),'"+getDataFromExcel("NykaaData", 10, 2)+"')]/following-sibling::div"))
				.click();
		waitTillPageLoad(20);
		/*wait for Concern filter to be visible*/
		By tabConcern = By.xpath("//div[text()='Concern']");
		waitForElementUsingLocator(tabConcern);
		wait1.until(ExpectedConditions.elementToBeClickable(tabConcern));
		Thread.sleep(3000);
		/* click on Concern filter tab */
		driver.findElement(tabConcern).click();
		/* click on required value in Concern filter */
		driver.findElement(By
				.xpath("//span[contains(text(),'" + getDataFromExcel("NykaaData", 10, 3) + "')]/following-sibling::div"))
				.click();
		
		WebElement item = driver
				.findElement(By.xpath("//span[contains(text(),'" + getDataFromExcel("NykaaData", 10, 4) + "')]"));
		/* fetch item name */
		String expectedItemName = item.getText();
		/* move mouse pointer to required item */
		act.moveToElement(item).perform();
		System.out.println(expectedItemName);
		/* wait for visibility of Preview Shades button */
		By btnPreviewShades =By.xpath("//span[contains(text(),'"+getDataFromExcel("NykaaData", 10, 4)+"')]/ancestor::a/following-sibling::div/div/div[2]/div/button/span");
		waitForElementUsingLocator(btnPreviewShades);
		/* click on Preview Shades button */
		driver.findElement(btnPreviewShades).click();
		
		/*driver.findElement(By.xpath("//img[@alt='Anti-Acne']")).click();
		driver.findElement(By.className("color-name")).getText();*/
		
		By btnViewDetails =By.xpath("//button[text()='View Detail']");
		waitForElementUsingLocator(btnViewDetails);
		driver.findElement(btnViewDetails).click();
		
		switchWindow();
		
		String expectedShadeName=getDataFromExcel("NykaaData", 10, 5);
		WebElement selectShade = driver.findElement(By.xpath("//select"));
		Select s=new Select(selectShade);
		s.selectByVisibleText(expectedShadeName);
		
		driver.findElement(By.name("pincode")).sendKeys(getDataFromExcel("NykaaData", 10, 6));
		driver.findElement(By.name("button")).click();
		boolean flag = false;
		try {
		waitForElementUsingLocator(By.xpath("//div[text()='Shipping To: ']"));
		driver.findElement(By.xpath("(//button[text()='ADD TO BAG'])[1]")).click();
		waitForElementUsingLocator(By.xpath("//span[text()='Product has been added to bag.']"));
		/* click on Add Bag icon */
		driver.findElement(By.className("AddBagIcon")).click();
		
		List<WebElement> allItemsInCart = driver.findElements(By.xpath("//div[@class='product-name']"));
		
		for (WebElement wb : allItemsInCart) {
			String actualItemName = wb.getText();
				/* Validation for checking same Shade item is present in cart */
				Assert.assertTrue(actualItemName.contains(expectedShadeName));
				System.out.println("Expected shade item is present in cart");
				flag=true;
			}
		}catch(Exception e) {
			System.out.println("Shipping not available");
		}
		if(!flag) {
			System.out.println("Selected shade item not present in cart");
		}
		
		
	}
}
