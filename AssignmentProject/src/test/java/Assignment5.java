import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import junit.framework.Assert;

public class Assignment5 {
	public static void main(String[] args) throws InterruptedException {
		/*Changing browser backend keys to block browser notification*/
		HashMap p=new HashMap();
		p.put("profile.default_content_setting_values.notifications", 2);
		ChromeOptions o=new ChromeOptions();
		o.setExperimentalOption("prefs", p);
		/* launch the browser */
		WebDriver driver = new ChromeDriver(o);
		/* wait for elements to load */
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		/*maximize the browser*/
		driver.manage().window().maximize();
		/* navigate to given url */
		driver.get("https://www.indiatoday.in/");
		/*close the widget*/
		driver.findElement(By.className("crosscloseif")).click();
		/*identify 3 dots*/
		WebElement dots = driver.findElement(By.xpath("//li[@class='all-menu']/a"));
		Actions act=new Actions(driver);
		act.moveToElement(dots).perform();
		/*click on 3 dots*/
		driver.findElement(By.xpath("//li[@class='all-menu']/a")).click();
		/*click on 1 link ie education*/
		driver.findElement(By.xpath("//ul[@id='newlist']/li[1]")).click();
		/*fetch the advertisement text*/		
		String actualAdvertisement=driver.findElement(By.xpath("(//div[text()='advertisement'])[2]")).getText();
		String expectedAdvertisement="ADVERTISEMENT";
		/*validating adversiment present or not*/
		Assert.assertEquals(expectedAdvertisement, actualAdvertisement);
		System.out.println("Advertisement Displayed Successfully");
		driver.quit();
	}
}
