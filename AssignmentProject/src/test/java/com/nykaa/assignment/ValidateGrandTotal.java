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

public class ValidateGrandTotal extends BaseTest{
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
	
	WebDriverWait wait1=new WebDriverWait(driver, 30);
	Actions act=new Actions(driver);
	
	/*Identify Brands tab*/
	WebElement tbBrands = driver.findElement(By.xpath("//a[text()='brands']"));
	
	/*move mouse pointer to Brands tab*/
	act.moveToElement(tbBrands).perform();
	
	driver.findElement(By.xpath("//a[text()='"+getDataFromExcel("NykaaData", 1, 0)+"']")).click();
	
	driver.findElement(By.xpath("(//div[@class='nw_grid_img'])[1]")).click();
	
	switchWindow();
	
	driver.findElement(By.xpath("//div[text()='Price']")).click();
	
	driver.findElement(By.xpath("//span[contains(text(),'"+getDataFromExcel("NykaaData", 1, 1)+"')]/following-sibling::div")).click();

	wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Color']")));
driver.findElement(By.xpath("//div[text()='Color']")).click();
//waitForAngularPageLoad();
	WebElement color = driver.findElement(By.xpath("//span[contains(text(),'"+getDataFromExcel("NykaaData", 1, 2)+"')]/following-sibling::div"));
	wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Brown')]/following-sibling::div")));
	color.click();
	Thread.sleep(5000);
WebElement categoryFilter = driver.findElement(By.xpath("//div[text()='Category']"));
waitForElement(categoryFilter, "Category Filter", 40);
wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Category']")));
wait1.until(ExpectedConditions.elementToBeClickable(categoryFilter));
categoryFilter.click();
	
	WebElement lipstick = driver.findElement(By.xpath("//span[contains(text(),'"+getDataFromExcel("NykaaData", 1, 3)+"')]/following-sibling::div"));
	wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'"+getDataFromExcel("NykaaData", 1, 3)+"')]/following-sibling::div")));
	waitForElement(lipstick, "lipstick check box", 40);
	lipstick.click();
	
	WebElement item = driver.findElement(By.xpath("//span[text()='"+getDataFromExcel("NykaaData", 1, 4)+"']"));
	String expectedItemName = item.getText();
	
	act.moveToElement(item).perform();
	
	WebElement addToBag = driver.findElement(By.xpath("//button[text()='ADD TO BAG']"));
	waitForElement(addToBag, "Add To Bag", 30);
	addToBag.click();
	
	driver.findElement(By.className("AddBagIcon")).click();
	
	WebElement itemInCart = driver.findElement(By.xpath("//div[@class='product-name' and text()='"+getDataFromExcel("NykaaData", 1, 4)+"']"));
	String actualItemName = itemInCart.getText();
	Assert.assertEquals(expectedItemName, actualItemName);
	System.out.println("Item present in cart is matched with Item added");
	String bagTotal = driver.findElement(By.xpath("//div[text()='Bag Total']/following-sibling::div")).getText();
	bagTotal=bagTotal.substring(1);
	String subTotal=driver.findElement(By.xpath("//div[text()='Sub Total']/following-sibling::div")).getText();
	subTotal=subTotal.substring(1);
	String shippingCharge=	driver.findElement(By.xpath("//div[text()='Shipping Charge']/following-sibling::div")).getText();
	int shippingAmount=0;
	if(shippingCharge.equals("Free")) {
		System.out.println("No shipping included");
	}else {
		shippingCharge=shippingCharge.substring(1);
		shippingAmount=Integer.parseInt(shippingCharge);
	}	
	String grandTotal=driver.findElement(By.xpath("//div[text()='Grand Total']/following-sibling::div")).getText();
	grandTotal=grandTotal.substring(1);
	System.out.println("bag Total price : "+bagTotal);
	System.out.println("Sub Total price : "+subTotal);
	System.out.println("Shipping charge : "+shippingCharge);
	System.out.println("Grand Total : "+grandTotal);	
	int bagAmount = Integer.parseInt(bagTotal);
	int subAmount = Integer.parseInt(subTotal);
	int grandAmount = Integer.parseInt(grandTotal);

	if(bagAmount+shippingAmount==grandAmount || subAmount+shippingAmount==grandAmount) {
		System.out.println("Grand total amount is correct");
	}

}
}
