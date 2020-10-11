import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

public class Assignment4 {
	public static void main(String[] args) throws InterruptedException {
		HashMap p = new HashMap();
		p.put("profile.default_content_setting_values.notifications", 2);
		ChromeOptions o = new ChromeOptions();
		o.setExperimentalOption("prefs", p);
		/* launch the browser */
		WebDriver driver = new ChromeDriver(o);
		/* wait for elements to load */
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		/* navigate to given url */
		driver.get("https://www.indiatoday.in/");
		/* close the widget */
		driver.findElement(By.className("crosscloseif")).click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		/* perform scroll action */
		js.executeScript("window.scrollBy(0,500)");
		/* fetch the today news latest update */
		String expectedHeadLine = driver
				.findElement(By.xpath("//div[@id='block-itg-widget-top-stories-ordering']/ul/li[1]")).getText();
		// String attribute =
		// driver.findElement(By.xpath("//div[@id='block-itg-widget-top-stories-ordering']/ul/li[1]")).getAttribute("title");
		System.out.println("Expected HeadLine : " + expectedHeadLine);
		/* perform click on latest today newz */
		driver.findElement(By.xpath("//div[@id='block-itg-widget-top-stories-ordering']/ul/li[1]")).click();
		Thread.sleep(2000);
		WebElement highLightHeader = null;
		/* identify Header */
		try {
			highLightHeader = driver.findElement(By.xpath("//h1[@itemprop='headline']"));
		} catch (NoSuchElementException e) {
			try {
				highLightHeader = driver.findElement(By.xpath("//h1[@id='shortdiscription']"));
			} catch (NoSuchElementException c) {
			}
		}
		/* fetching header text */
		String actualHeadLine = highLightHeader.getText();
		System.out.println("Actual HeadLine : " + actualHeadLine);
		/* validation of header text with clicked text */
		Assert.assertEquals(expectedHeadLine, actualHeadLine);
		System.out.println("Title validated successfully");
		/* For highlighting the text */
		/* highlight the header text */
		js.executeScript("arguments[0].setAttribute('style', 'background: green; border: 2px solid red;');",
				highLightHeader);

	}
}