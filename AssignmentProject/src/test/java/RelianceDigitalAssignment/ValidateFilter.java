package RelianceDigitalAssignment;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.assignment.practice.BaseTest;

import junit.framework.Assert;
/**
 * Description This class is used for validate filter applied or not
 * @author Sajal jain
 */
public class ValidateFilter extends BaseTest{
	public static void main(String[] args) throws IOException, Exception {
		/* Changing the backend keys to block browser native popup */
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
		/* identify mobile and tablets drop down */
		WebElement ddMobilesAndTablets = driver.findElement(By.xpath("//div[text()='MOBILES & TABLETS']"));
		/* create objects of actions class for mouse over */
		Actions act = new Actions(driver);
		/* move mouse pointer to mobiles and tablets drop down */
		act.moveToElement(ddMobilesAndTablets).perform();
		/* click on memory card option in drop down */
		driver.findElement(By.xpath("(//a[text()='Memory Cards'])[1]")).click();
		/* using javascriptexecutor for scrolling browser window */
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,2000)");
		/* click on Storage capacity drop down */
		driver.findElement(By.xpath("//h4[text()='Storage Capacity']")).click();
		/* click on required filter */
		driver.findElement(
				By.xpath("//div[text()='" + getDataFromExcel("EcommData", 1, 0) + "']/../following-sibling::span"))
				.click();

		String actualFilterValue = driver
				.findElement(By.xpath("//span[text()='Filters: ']/following-sibling::ul//div[contains(text(),'"
						+ getDataFromExcel("EcommData", 1, 0) + "')]"))
				.getText();
		String expectedFilterValue = getDataFromExcel("EcommData", 1, 0);
		/* validate expected filter value with actual filter value */
		try {
			Assert.assertEquals(expectedFilterValue, actualFilterValue);
			System.out.println("Filter applied successfully");
		} catch (Exception e) {
			System.out.println("Filter not applied");
		}
		/* identify the elements after applying filter */
		List<WebElement> allItems = driver.findElements(By.cssSelector("p[class='sp__name']"));
		int count = 0;
		/* print the name of the element */
		for (WebElement wb : allItems) {
			String text = wb.getText();
			System.out.println("item : " + text);
			if (text.contains(getDataFromExcel("EcommData", 1, 0))) {
				count++;
			}
		}
		/*
		 * After contains validation number of elements is equal to count then filter
		 * applied successfully
		 */
		Assert.assertEquals(allItems.size(), count);
		System.out.println("filter applied");
		/* close the browser */
		driver.close();
	}
}