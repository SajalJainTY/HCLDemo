import java.io.FileInputStream;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class SelectDiamond {
	public static void main(String[] args) throws IOException, Exception {
		
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
		/*identify the Rings webelemt for mouse over action*/
		WebElement rings = driver.findElement(By.xpath("//a[text()='Rings ']/.."));
		/*move mouse pointer to Rings webelement*/
		act.moveToElement(rings).perform();
		System.out.println("move mouse pointer to rings");
		Thread.sleep(2000);
		/*Perform click on Diamond in drop down*/
		driver.findElement(By.xpath("//div[text()='Popular Ring Types ']/../ul/li["+getDataFromExcel("Sheet2",1,0)+"]")).click();
		/*identify the price drop down*/
		WebElement price = driver.findElement(By.id("Price-form"));
		/*move cursor to price drop down*/
		act.moveToElement(price).perform();
		System.out.println("move mouse pointer to price");
		Thread.sleep(2000);
		/*click on required price*/
		driver.findElement(By.xpath("//span[@data-displayname='"+getDataFromExcel("Sheet2",1,1)+"']")).click();
		/*identify the popular drop down*/
		WebElement popular = driver.findElement(By.xpath("//span[text()=' Popular ']"));
		/*move cursor to popular drop down*/
		act.moveToElement(popular).perform();
		System.out.println("move mouse pointer to popular");
		Thread.sleep(2000);
		/*click on required filter */
		driver.findElement(By.xpath("//a[text()='"+getDataFromExcel("Sheet2",1,2)+"']")).click();
		/*click on required item*/
		driver.findElement(By.xpath("//img[@alt='"+getDataFromExcel("Sheet2",1,3)+"']")).click();
		/*window switching handle code*/
		String mainId = driver.getWindowHandle();
		Set<String> allID = driver.getWindowHandles();
		for(String s:allID) {
			if(!s.equals(mainId)) {
				driver.switchTo().window(s);
			}
		}
		Thread.sleep(2000);
		System.out.println("Product details");
		/*identify and print product details*/
		for(int i=1;i<=4;i++) {
		String data1 = driver.findElement(By.xpath("//h2[text()='Product Details']/following-sibling::div/dl["+i+"]/dt")).getText();
		String data2 = driver.findElement(By.xpath("//h2[text()='Product Details']/following-sibling::div/dl["+i+"]/dd")).getText();
		System.out.println(data1+" : "+data2);
		Thread.sleep(1000);
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