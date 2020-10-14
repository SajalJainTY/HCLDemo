package OrangeHRMAssignment;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import junit.framework.Assert;

public class CreateCustomer {
public static void main(String[] args) throws IOException, Exception {
	/* launch the browser */
	WebDriver driver = new ChromeDriver();
	/* wait for elements to load */
	driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
	/* navigate to given url */
	driver.get("https://s2.demo.opensourcecms.com/orangehrm/symfony/web/index.php/auth/login");
	/*maximize the browser*/
	driver.manage().window().maximize();
	driver.findElement(By.id("txtUsername")).sendKeys("opensourcecms");
	driver.findElement(By.id("txtPassword")).sendKeys("opensourcecms");
	driver.findElement(By.id("btnLogin")).click();
	/*create object of Actions class for mouse over action*/
	Actions act=new Actions(driver);
	WebElement admin = driver.findElement(By.xpath("//span[text()='Admin']"));
	act.moveToElement(admin).perform();
	WebElement projectInfo = driver.findElement(By.xpath("//span[text()='Project Info']"));
	act.moveToElement(projectInfo).perform();
	driver.findElement(By.xpath("//span[text()='Customers']")).click();
	driver.switchTo().frame("rightMenu");
	driver.findElement(By.id("btnAdd")).click();
	driver.findElement(By.id("addCustomer_customerName")).sendKeys(getDataFromExcel("Sheet4", 1, 0));
	driver.findElement(By.id("addCustomer_description")).sendKeys(getDataFromExcel("Sheet4", 1, 1));
	driver.findElement(By.id("btnSave")).click();
	String actualSuccessMSg = driver.findElement(By.id("messagebar")).getText();
	String expectedSuccesMsg=getDataFromExcel("Sheet4", 1, 2);
	System.out.println("actual"+actualSuccessMSg);
	System.out.println("expected"+expectedSuccesMsg);
	try {
	Assert.assertEquals(expectedSuccesMsg, actualSuccessMSg);
	System.out.println("Success msg displayed");
	}catch(Exception e) {
		System.out.println("Success msg not displayed");
	}
	
	String actualCustomerName = driver.findElement(By.xpath("//a[text()='"+getDataFromExcel("Sheet4", 1, 0)+"']")).getText();
	String expectedCustomerName=getDataFromExcel("Sheet4", 1, 0);
	System.out.println("actual"+actualCustomerName);
	System.out.println("expected"+expectedCustomerName);
	try {
		Assert.assertEquals(expectedCustomerName, actualCustomerName);
		System.out.println("Customer present in customer page");
		}catch(Exception e) {
			System.out.println("Customer is not present in customer page");
		}
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
