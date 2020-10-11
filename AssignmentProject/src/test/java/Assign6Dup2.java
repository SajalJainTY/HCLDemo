import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Assign6Dup2 {
	public static void main(String[] args) throws Exception {
		FileInputStream fis = new FileInputStream("./MobileData/mobileData.xlsx");
		/*Changing browser backend keys to block browser notification*/
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
		/*fetch all the mobile name elements*/
		List<WebElement> mobileName = driver.findElements(By.xpath("//p[@class='sp__name']"));
		/*code for writing in excel sheet*/
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(0);
		sheet.createRow(0);
		sheet.getRow(0).createCell(0).setCellValue("Mobile Name");
		sheet.getRow(0).createCell(1).setCellValue("Memory");
		sheet.getRow(0).createCell(2).setCellValue("Actual Price");
		sheet.getRow(0).createCell(3).setCellValue("Discount");
		sheet.getRow(0).createCell(4).setCellValue("Discount Price");
		for(int i=0;i<mobileName.size();i++) {
			/*create the row*/
			sheet.createRow(i+1);
			/*fetch discount price for phone*/
			String discountPrice=driver.findElement(By.xpath("//p[text()='"+mobileName.get(i).getText()+"']/../div/div/div/span[1]")).getText();
			discountPrice=discountPrice.replace("?", "Rs");
			/*fetch actual price for phone*/
			String actualPrice=driver.findElement(By.xpath("//p[text()='"+mobileName.get(i).getText()+"']/../div/div/div/span[2]")).getText();
			actualPrice=actualPrice.replace("?", "Rs");
			/*fetch discount for phone*/
			String discount=driver.findElement(By.xpath("//p[text()='"+mobileName.get(i).getText()+"']/../div/div/div/span[3]")).getText();
			discount=discount.replace("?", "Rs");
			/*fetch mobile name from list*/
			String mo = mobileName.get(i).getText();
			//System.out.println(mo);
			String[] st=mo.split(",");
	        int j=0;
			String s=st[j];	
			sheet.getRow(i+1).createCell(j).setCellValue(s.substring(0,s.length()-5));
			sheet.getRow(i+1).createCell(j+1).setCellValue(s.substring(s.length()-5));
			sheet.getRow(i+1).createCell(j+2).setCellValue(actualPrice);
			sheet.getRow(i+1).createCell(j+3).setCellValue(discount);
			sheet.getRow(i+1).createCell(j+4).setCellValue(discountPrice);
			FileOutputStream fos = new FileOutputStream("./MobileData/mobileData.xlsx");
			workbook.write(fos);
			fos.close();
	}
		System.out.println("pass");
		driver.close();
	}
}
