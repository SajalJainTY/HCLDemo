import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Assignment6 {
public static void main(String[] args) throws Exception {
	FileInputStream fis = new FileInputStream("./MobileData/mobileData.xlsx");
	HashMap p=new HashMap();
	p.put("profile.default_content_setting_values.notifications", 2);
	ChromeOptions o=new ChromeOptions();
	o.setExperimentalOption("prefs", p);
	/* launch the browser */
	WebDriver driver = new ChromeDriver(o);
	/* wait for elements to load */
	driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	/*Maximize the browser*/
	driver.manage().window().maximize();
	/* navigate to given url */
	driver.get("https://www.reliancedigital.in/smart-phones/c/S101711?searchQuery=:relevance:availability:Exclude%20out%20of%20Stock&page=0");
	List<WebElement> mobileName = driver.findElements(By.xpath("//p[@class='sp__name']"));
	XSSFWorkbook workbook = new XSSFWorkbook(fis);
	XSSFSheet sheet = workbook.getSheetAt(0);
	sheet.createRow(0);
	sheet.getRow(0).createCell(0).setCellValue("Mobile Name");
	sheet.getRow(0).createCell(4).setCellValue("Actual Price");
	sheet.getRow(0).createCell(5).setCellValue("Discount");
	sheet.getRow(0).createCell(6).setCellValue("Discount Price");
	for(int i=0;i<mobileName.size();i++) {
		sheet.createRow(i+1);
		String discountPrice=driver.findElement(By.xpath("//p[text()='"+mobileName.get(i).getText()+"']/../div/div/div/span[1]")).getText();
		discountPrice=discountPrice.replace("?", "Rs");
		String actualPrice=driver.findElement(By.xpath("//p[text()='"+mobileName.get(i).getText()+"']/../div/div/div/span[2]")).getText();
		actualPrice=actualPrice.replace("?", "Rs");
		String discount=driver.findElement(By.xpath("//p[text()='"+mobileName.get(i).getText()+"']/../div/div/div/span[3]")).getText();
		discount=discount.replace("?", "Rs");
		String mo = mobileName.get(i).getText();
		System.out.println(mo);
		String[] st=mo.split(",");
		for(int j=0;j<st.length-1;j++) {
		sheet.getRow(i+1).createCell(j).setCellValue(st[j]);
		if(j==st.length-2) {
		sheet.getRow(i+1).createCell(4).setCellValue(actualPrice);
		sheet.getRow(i+1).createCell(5).setCellValue(discount);
		sheet.getRow(i+1).createCell(6).setCellValue(discountPrice);
		}
		FileOutputStream fos = new FileOutputStream("./MobileData/mobileData.xlsx");
		workbook.write(fos);
		fos.close();
	}	
	}
	System.out.println("pass");
	driver.close();
}
}
