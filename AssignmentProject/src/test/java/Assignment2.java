import junit.framework.Assert;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Assignment2 {
	private static String String = null;

	public static void main(String[] args) throws InterruptedException {
		/*launch browser*/
		WebDriver driver = new ChromeDriver();
		/*wait for elements to load*/
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		/*navigate to url*/
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
		/*enter text in search box*/
		driver.findElement(By.className("search-keyword")).sendKeys("ca", Keys.ENTER);
		/*fetching all elements contains Ca present in page*/
		List<WebElement> ca = driver.findElements(By.xpath("//h4[contains(text(),'Ca')]"));
		int size = ca.size();
		/*create new arraylist for storing search items result*/
		List list = new ArrayList();
		/*add items in array list*/
		for (WebElement c : ca) {
			list.add(c.getText());
		}
		/*clear and change the quantity of item*/
		int k = 1;
		while (k < size) {
			driver.findElement(By.xpath("(//input[@class='quantity'])[" + k + "]")).clear();
			driver.findElement(By.xpath("(//input[@class='quantity'])[" + k + "]")).sendKeys("2");
			k++;
		}

		/*add items to cart*/
		for (int i = 1; i < size; i++) {
			driver.findElement(By.xpath("(//h4[contains(text(),'Ca')]/following-sibling::div[2]/button)[" + i + "]")).click();
		}
		/*click on go to cart image */
		driver.findElement(By.cssSelector("img[alt='Cart']")).click();
		/*click on proceed to checkout button*/
		driver.findElement(By.xpath("//button[text()='PROCEED TO CHECKOUT']")).click();
		double sum = 0;
		/*addition of total amount of each item */
		for (int i = 0; i < 3; i++) {
			String t1 = driver.findElement(By.xpath("//p[text()='" + list.get(i) + "']/../../td[5]")).getText();
			int t = Integer.parseInt(t1);
			sum += t;
		}
		/*fetch all the items quantity*/
		List<WebElement> quantity = driver.findElements(By.xpath("//p[@class='quantity']"));
		/*fetch all the items price*/
		List<WebElement> price = driver.findElements(By.xpath("//p[@class='product-name']/../../td[4]"));
		/*fetch all the items total amount*/
		List<WebElement> total = driver.findElements(By.xpath("//p[@class='product-name']/../../td[5]"));
		boolean flag = true;
		/*create arraylist to store total amount of items*/
		ArrayList l = new ArrayList();
		/*logic to verify price*quantity is equal total amount*/
		for (int i = 0; i < size - 1; i++) {
			int actualQuantity = Integer.parseInt(quantity.get(i).getText());
			int actualPrice = Integer.parseInt(price.get(i).getText());
			int totalPrice = Integer.parseInt(total.get(i).getText());
			l.add(actualQuantity * actualPrice);
			System.out.print("aq " + actualQuantity);
			System.out.print(" ap " + actualPrice);
			System.out.println(" totalPrice " + totalPrice);
			if (!l.get(i).equals(totalPrice)) {
				flag = false;
				break;
			}
		}
		if (flag)
			System.out.println("Total amount validation ie (price * quantity) is pass");
		else
			System.out.println("Total amount validation ie (price * quantity) is fail");

		/*enter promo code*/
		driver.findElement(By.className("promoCode")).sendKeys("rahulshettyacademy");
		/*click on apply button*/
		driver.findElement(By.className("promoBtn")).click();
		Thread.sleep(5000);
		/*fetch the discount amount and total amount and validate total amount*/
		String text = driver.findElement(By.className("discountPerc")).getText();
		text = text.replace("%", "");
		int discount = Integer.parseInt(text);
		double expected = sum - sum * discount / 100;
		String discountAmount = driver.findElement(By.className("discountAmt")).getText();
		double actual = Double.parseDouble(discountAmount);
		Assert.assertEquals(actual, expected);
		System.out.println("validation pass");
		/*click on place order*/
		driver.findElement(By.xpath("//button[.='Place Order']")).click();
		/*select India from drop down*/
		WebElement select = driver.findElement(By.xpath("//option[text()='Select']/.."));
		Select se = new Select(select);
		se.selectByVisibleText("India");
		/*click on check box*/
		driver.findElement(By.className("chkAgree")).click();
		/*click on proceed*/
		driver.findElement(By.xpath("//button[text()='Proceed']")).click();
		System.out.println("order placed successfully");
		/*close all windows*/
		driver.quit();
	}
}
