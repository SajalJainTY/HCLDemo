import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Prac {
	public static void main(String[] args) throws InterruptedException {

		/* launch the browser */
		WebDriver driver = new ChromeDriver();
		/* wait for elements to load */
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		/* navigate to given url */
		driver.get("https://stackoverflow.com/questions/31517561/highlight-text-using-selenium");
		 
	}
}
