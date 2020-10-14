import java.io.FileInputStream;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class NewScenario {
public static void main(String[] args) throws Exception {
	/* launch the browser */
	WebDriver driver = new ChromeDriver();
	/* wait for elements to load */
	driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	/* navigate to given url */
	driver.get("https://www.bluestone.com/");
	/*maximize the browser*/
	driver.manage().window().maximize();
	/*create object of Actions class for mouse over action*/
	Actions act=new Actions(driver);
	/*identify the drop down for mouse over*/
	WebElement ddAll = driver.findElement(By.xpath("//a[text()='"+getDataFromExcel("Sheet3",1,0)+"']"));
	/*move cursor to required element*/
	act.moveToElement(ddAll).perform();
	/*perform click on braclets*/
	driver.findElement(By.xpath("//a[text()='Gold Jewellery']/../following-sibling::ul//a[text()='"+getDataFromExcel("Sheet3",1,1)+"']")).click();
	/*identify price drop down*/
	WebElement price = driver.findElement(By.id("Price-form"));
	/*move cursor to price*/
	act.moveToElement(price).perform();
	System.out.println("move mouse pointer to price");
	Thread.sleep(2000);
	/*select required price*/
	driver.findElement(By.cssSelector("span[data-displayname='"+getDataFromExcel("Sheet3",1,2)+"']")).click();
	/*identify metal drop down*/
	WebElement metal = driver.findElement(By.id("Metal-form"));
	/*move cursor to metal drop down*/
	act.moveToElement(metal).perform();
	System.out.println("move mouse pointer to metal");
	Thread.sleep(2000);
	/*click on required metal*/
	driver.findElement(By.cssSelector("span[data-displayname='"+getDataFromExcel("Sheet3",1,3)+"']")).click();
	/*identify gender dropdown*/
	WebElement gender = driver.findElement(By.id("Gender-form"));
	/*move cursor tp gender drop down*/
	act.moveToElement(gender).perform();
	System.out.println("move mouse pointer to gender");
	Thread.sleep(2000);
	/*click on desired gender*/
	driver.findElement(By.cssSelector("span[data-displayname='"+getDataFromExcel("Sheet3",1,4)+"']")).click();
	/*identify required item*/
	WebElement itemName = driver.findElement(By.xpath("//img[contains(@alt,'"+getDataFromExcel("Sheet3",1,5)+"')]"));
	/*move cursor to required item*/
	act.moveToElement(itemName).perform();
	Thread.sleep(2000);
	/*click on view details*/
	driver.findElement(By.xpath("(//a[text()='View Details >>'])[1]")).click();
	/*window switching code*/
	String mainId = driver.getWindowHandle();
	Set<String> allID = driver.getWindowHandles();
	for(String s:allID) {
		if(!s.equals(mainId)) {
			driver.switchTo().window(s);
		}
	}
	Thread.sleep(2000);
	/*click on select size drop down*/
	driver.findElement(By.xpath("//span[text()=' Select Size ']")).click();
	/*select required size*/
	driver.findElement(By.xpath("//span[@class='size' and contains(text(),'"+getDataFromExcel("Sheet3",1,6)+"')]")).click();
	/*identify and print the price breakup details*/
	for(int i=1;i<=5;i++) {
	String data1 = driver.findElement(By.xpath("//h2[text()='Price Breakup']/following-sibling::div/table/tbody/tr["+i+"]/td[1]")).getText();
	System.out.println(data1 );
	}
	/*close the browser*/
	driver.quit();
}

public static String getDataFromExcel(String sheet,int row,int cell) throws Exception, IOException {
	FileInputStream fis=new FileInputStream("./MobileData/testData.xlsx");
	Workbook book = WorkbookFactory.create(fis);
	Sheet sh = book.getSheet(sheet);
	String value = sh.getRow(row).getCell(cell).getStringCellValue();
	return value;
}
}
