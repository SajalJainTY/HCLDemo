package RelianceDigitalAssignment;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.assignment.practice.BaseTest;

/**
 * Description This class is used for validate item added to cart or not
 * 
 * @author Sajal jain
 */
public class ValidateAddToCart extends BaseTest {

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
		/* Mobile and Tablets drop down */
		WebElement ddMobilesAndTablets = driver.findElement(By.xpath("//div[text()='MOBILES & TABLETS']"));
		Actions act = new Actions(driver);
		/* move mouse pointer to mobile and tablets drop down */
		act.moveToElement(ddMobilesAndTablets).perform();
		/* click on memory card option in drop down */
		driver.findElement(By.xpath("(//a[text()='Memory Cards'])[1]")).click();
		/* click on 1st item */
		driver.findElement(By.xpath("//p[text()='" + getDataFromExcel("EcommData", 1, 1) + "']")).click();
		/* navigate to child tab */
		switchWindow();
		/* identify pin code text box */
		WebElement pinCode = driver.findElement(By.id("RIL_PDPInputPincode"));
		/* wait for Pin code text box */
		waitForElement(pinCode, "Pin code text box", 20);
		/* clear pin code text box */
		pinCode.clear();
		/* type pin in pin code text box */
		pinCode.sendKeys(getDataFromExcel("EcommData", 1, 3));
		/* identify Add to cart button */
		WebElement btnAddToCart1 = driver.findElement(By.xpath("//button[@id='add_to_cart_main_btn']/.."));
		/* wait for add to card button to be visible */
		waitForElement(btnAddToCart1, "Add To Cart Button", 20);
		/* click on add to cart button */
		btnAddToCart1.click();

		Thread.sleep(4000);
		/* close the child browser tab */
		driver.close();
		/* switch to main window tab */
		switchToMainWindow();
		/* identify item 2 */
		WebElement item2 = driver.findElement(By.xpath("//p[text()='" + getDataFromExcel("EcommData", 1, 2) + "']"));
		/* wait for item 2 to be visible */
		waitForElement(item2, "Select Item", 20);
		/* click on item 2 */
		item2.click();
		/* switch to child browser tab */
		switchWindow();

		/* identify Add to cart button */
		WebElement btnAddToCart2 = driver.findElement(By.xpath("//button[@id='add_to_cart_main_btn']/.."));
		/* wait for add to card button to be visible */
		waitForElement(btnAddToCart2, "Add To Cart Button", 20);
		/* click on add to cart button */
		btnAddToCart2.click();
		/* fetch number of items present in cart */
		String actualNumberOfItems = driver.findElement(By.xpath("//h3[text()='My Cart']/span")).getText();
		String expectedNumberOfItems = getDataFromExcel("EcommData", 1, 4);
		try {
			Assert.assertTrue(actualNumberOfItems.contains(expectedNumberOfItems));
			System.out.println("Number of items added to cart matched");
		} catch (Exception e) {
			System.out.println("Number of items in cart didnt match with number of items added");
		}
		/* close all browser tab */
		driver.quit();
	}
}
