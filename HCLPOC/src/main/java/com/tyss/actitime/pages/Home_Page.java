package com.tyss.actitime.pages;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.tyss.acttime.util.WebActionUtil;
/**
 * Description This class has the implementations of the Home related methods 
 * @author sajal jain
 */
public class Home_Page {

	public WebDriver driver;
	public WebActionUtil WebActionUtil;
	public long ETO = 10;

	public Home_Page(WebDriver driver, long ETO, WebActionUtil WebActionUtil){
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.WebActionUtil = WebActionUtil;
		this.ETO = ETO;
	}
	
	/* Task Tab */
	@FindBy(xpath = "//div[text()='TASKS']/..")
	private WebElement tabTasks;
	
	/**
	 * Description  Method clicks on Task Tab
	 * @author Sajal jain
	 * 
	 */
	public synchronized void clkTaskTab() {
		WebActionUtil.waitForElement(tabTasks, "Task Tab", 45);
		WebActionUtil.clickOnElementUsingJS(tabTasks, "Task Tab");
	}
	
	/**
	 * Description  Method To read Data From PDF
	 * @author Sajal jain
	 * @throws IOException 
	 * 
	 */
	public synchronized void readPdfData() throws IOException {
	//in argument pass the pdf url or location
	URL url=new URL("");
	
	InputStream is=url.openStream();
	BufferedInputStream fileParse=new BufferedInputStream(is);
	PDDocument document =null;
	document=PDDocument.load(fileParse);
	String pdfContent =new PDFTextStripper().getText(document);
	System.out.println(pdfContent);
	}
	
}
