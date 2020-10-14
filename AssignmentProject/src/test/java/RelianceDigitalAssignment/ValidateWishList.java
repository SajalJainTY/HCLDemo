package RelianceDigitalAssignment;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.assignment.practice.BaseTest;

import junit.framework.Assert;

/**
 * Description This class is used for validating item added to wishlist or not
 * 
 * @author Sajal jain
 */
public class ValidateWishList extends BaseTest {
	public static void main(String[] args) throws IOException, Exception {
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
		driver.get("https://www.reliancedigital.in/");
		/* maximize the browser */
		driver.manage().window().maximize();
	/*	 perform click on login tab 
		driver.findElement(By.xpath("//span[text()='Login']")).click();
		 type username in username text box 
		driver.findElement(By.id("email")).sendKeys(getDataFromExcel("EcommData", 5, 0));
		 type password in password text box 
		driver.findElement(By.id("pass")).sendKeys(getDataFromExcel("EcommData", 5, 1));
		 click on login button 
		driver.findElement(By.xpath("//span[text()='Continue']")).click();*/
		/* Mobile and Tablets drop down */
		WebElement ddMobilesAndTablets = driver.findElement(By.xpath("//div[text()='MOBILES & TABLETS']/.."));
		waitForElement(ddMobilesAndTablets, "Mobile and tablets drop down", 40);
		Actions act = new Actions(driver);
		/* move mouse pointer to mobile and tablets drop down */
		act.moveToElement(ddMobilesAndTablets).perform();
		/* identify memory cards option in drop down */
		WebElement memoryCards = driver.findElement(By.xpath("(//a[text()='Memory Cards'])[1]"));
		/* wait for memory cards option to be visible */
		waitForElement(memoryCards, "Memory Cards option in drop down", 50);
		/* click on memory card option in drop down */
		memoryCards.click();

		/* identify wishlist tab for given item */
/*		WebElement tabWishList = driver.findElement(By.xpath("//p[text()='" + getDataFromExcel("EcommData", 5, 3)
				+ "']/../../following-sibling::div/button[2]/span"));*/
		WebElement tabWishList = driver.findElement(By.xpath("(//span[text()='Wishlist'])[1]"));
		/* wait for wish list tab to be visible */
		waitForElement(tabWishList, "Wishlist tab", 50);
		/* click on wish list tab */
		tabWishList.click();
		driver.findElement(By.xpath("//span[text()='Login']")).click();
		/* type username in username text box */
		driver.findElement(By.id("email")).sendKeys(getDataFromExcel("EcommData", 5, 0));
		/* type password in password text box */
		driver.findElement(By.id("pass")).sendKeys(getDataFromExcel("EcommData", 5, 1));
		/* click on login button */
		driver.findElement(By.xpath("//span[text()='Continue']")).click();
		/* identify wishlist text box */
		WebElement tbWishList = driver.findElement(By.id("wishlist-input"));
		/* wait for wishlist text box to be visible */
		waitForElement(tbWishList, "WishList text box", 40);
		/* type wishlist name */
		tbWishList.sendKeys(getDataFromExcel("EcommData", 5, 2));
		/* identify add to this wish list button */
		WebElement btnAddToThisWishlist = driver
				.findElement(By.xpath("//span[text()='Create and Add to this Wishlist']"));
		/* wait for add to this wish list button to be visible */
		waitForElement(btnAddToThisWishlist, "Add To This Wishlist button", 40);
		/* click on add to this wish list button */
		btnAddToThisWishlist.click();
		/* identidy loginInfo drop down */
		WebElement loginInfo = driver.findElement(By.id("RIL_HeaderLoginAndMyAccount"));
		/* move mouse pointer to login info drop down */
		act.moveToElement(loginInfo).perform();
		/* click on My wish list option in drop down */
		driver.findElement(By.id("wishlist")).click();
		try {
			/* identify given wishlist name */
			WebElement wishlist = driver
					.findElement(By.xpath("//div[text()='" + getDataFromExcel("EcommData", 5, 2) + "']"));
			if (wishlist.isDisplayed()) {
				wishlist.click();
				System.out.println("wishlist selected");
			} else {
				System.out.println("Wishlist not displayed");
			}
		} catch (Exception e) {
			System.out.println("wishlist not created");
		}
		/* fetch the present item name */
		String actualItem = driver.findElement(By.xpath("//div[@class='wishlist-page__item-container__name']"))
				.getText();
		String expectedItem = getDataFromExcel("EcommData", 5, 3);
		System.out.println("actual" + actualItem);
		System.out.println("expected" + expectedItem);
		try {
			Assert.assertEquals(expectedItem, actualItem);
			System.out.println("Item present in wishlist");
		} catch (Exception e) {
			System.out.println("Item not present in wishlist");
		}

	}
}