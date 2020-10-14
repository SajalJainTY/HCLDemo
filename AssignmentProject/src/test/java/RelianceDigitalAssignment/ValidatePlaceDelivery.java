package RelianceDigitalAssignment;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
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
import org.openqa.selenium.support.ui.WebDriverWait;

import com.assignment.practice.BaseTest;

import junit.framework.Assert;

/**
 * Description This class is used for validating item is deliverable to provided
 * pincode or not
 * 
 * @author Sajal jain
 */
public class ValidatePlaceDelivery extends BaseTest {
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
		/* click on item */
		driver.findElement(By.xpath("//p[text()='" + getDataFromExcel("EcommData", 8, 0) + "']")).click();
		/* navigate to child tab */
		switchWindow();
		/* identify pin code text box */
		WebElement pinCode = driver.findElement(By.id("RIL_PDPInputPincode"));
		/* wait for Pin code text box */
		waitForElement(pinCode, "Pin code text box", 20);
		/* clear pin code text box */
		pinCode.clear();
		/* type pin in pin code text box */
		pinCode.sendKeys(getDataFromExcel("EcommData", 8, 1));
		/* fetch text for validation */
		String actualMessage = driver.findElement(By.xpath("//div[contains(text(),'PIN code is unavailable')]"))
				.getText();
		String expectedMessage = "  Delivery to this PIN code is unavailable. Please try another PIN code.";
		System.out.println("actual : " + actualMessage);
		System.out.println("expected : " + expectedMessage);
		Assert.assertEquals(expectedMessage, actualMessage);
		System.out.println("Error message displayed successfully");
	}
}
