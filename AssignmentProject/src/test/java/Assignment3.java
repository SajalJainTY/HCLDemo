import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Assignment3 {
	public static void main(String[] args) throws InterruptedException {
		/* launch the browser */
		WebDriver driver = new ChromeDriver();
		/* wait for elements to load */
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		/* navigate to given url */
		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
		/* fetch main browser session id */
		String mainId = driver.getWindowHandle();
		/* click on Top Deals tab */
		driver.findElement(By.xpath("//a[text()='Top Deals']")).click();
		/* wait for number windows to be 2 */
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.numberOfWindowsToBe(2));
		/* fetch all session id */
		Set<String> allId = driver.getWindowHandles();
		for (String id : allId) {
			if (!id.equals(mainId)) {
				/* switch driver focus to new tab */
				driver.switchTo().window(id);
			}
		}
		/* Select 10 from drop down */
		WebElement dd = driver.findElement(By.id("page-menu"));
		Select s = new Select(dd);
		s.selectByVisibleText("10");
		/* click on arrange ascending order arrow */
		driver.findElement(By.xpath("//span[text()='Veg/fruit name']/following-sibling::span")).click();
		/* fetch table header */
		String text = driver.findElement(By.xpath("//span[text()='Veg/fruit name']/..")).getText();
		System.out.println(text);
		/* fetch table body and print it */
		List<WebElement> allRows = driver.findElements(By.xpath("//tbody"));
		for (WebElement wr : allRows) {
			System.out.println(wr.getText());
		}
		/*close all window*/
		driver.quit();
	}
}
