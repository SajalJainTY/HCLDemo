package com.tyss.actitime.pages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.tyss.acttime.util.WebActionUtil;

/**
 * Description This class has the implementations of the Just Dial Chemist page
 * related methods
 * 
 * @author Sajal jain
 */
public class JustDialChemist_Page {
	public WebDriver driver;
	public WebActionUtil WebActionUtil;
	public long ETO = 10;
	public String expectedName;

	public JustDialChemist_Page(WebDriver driver, long ETO, WebActionUtil WebActionUtil) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.WebActionUtil = WebActionUtil;
		this.ETO = ETO;
	}

	/* Location Drop down */
	@FindBy(id = "city")
	private WebElement ddLocation;
	/* Chemist Shop Link */
	@FindBy(xpath = "//span[@class='lng_cont_name']")
	private WebElement lnkChemistShop;
	/* Address Text Box */
	@FindBy(id = "fulladdress")
	private WebElement txtAddress;
	/* Number Text Box */
	@FindBy(xpath = "//span[@class='telnowpr']")
	private WebElement txtNumber;
	/* Chemist Tab */
	@FindBy(xpath = "//span[text()='Chemists']/..")
	private WebElement tabChemist;
	/* Mobile Link */
	@FindBy(xpath = "//a[text()='Mobile']")
	private WebElement lnkMobile;
	/* Popular Brands Tab */
	@FindBy(xpath = "//span[text()='All Options']")
	private WebElement tabPopularBrands;
	/* All Mobiles List */
	@FindBys({ @FindBy(xpath = "//a[@class='frseot']") })
	private List<WebElement> allMobiles;
	/* Caterers Tab */
	@FindBy(xpath = "//span[text()='Caterers']/..")
	private WebElement tabCaterers;
	/* All Caterers Tab */
	@FindBy(xpath = "//span[text()='All Caterers']")
	private WebElement tabAllCaterers;

	/**
	 * Description Method to click All Caters Tab
	 * 
	 * @author Sajal jain
	 * 
	 */
	public void clkAllCaterersTab() {
		WebActionUtil.scrollToElement(tabAllCaterers, "All Caters Tab");
		WebActionUtil.waitForElement(tabAllCaterers, "All Caters Tab", 45);
		tabAllCaterers.click();
		/*
		 * try { WebActionUtil.clickOnWebElement(tabChemist, "Chemist Tab",
		 * "Unable to click on Chemist Tab"); }catch(Exception e) {
		 * WebActionUtil.clickOnElementUsingJS(tabChemist, "Chemist Tab"); }
		 */

	}

	/**
	 * Description Method to click Caters Tab
	 * 
	 * @author Sajal jain
	 * 
	 */
	public void clkCaterersTab() {
		WebActionUtil.scrollToElement(tabCaterers, "Caters Tab");
		WebActionUtil.waitForElement(tabCaterers, "Caters Tab", 45);
		tabCaterers.click();
		/*
		 * try { WebActionUtil.clickOnWebElement(tabChemist, "Chemist Tab",
		 * "Unable to click on Chemist Tab"); }catch(Exception e) {
		 * WebActionUtil.clickOnElementUsingJS(tabChemist, "Chemist Tab"); }
		 */

	}

	/**
	 * Description Method to Print Mobile Details in Excel
	 * 
	 * @author Sajal jain
	 * @throws IOException
	 */
	public void printMobileDetails() throws IOException {
		FileInputStream fis = new FileInputStream("./src/main/resources/data/mobileData.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(0);
		sheet.createRow(0);
		sheet.getRow(0).createCell(0).setCellValue("Brand Name");
		sheet.getRow(0).createCell(1).setCellValue("Actual Price");
		sheet.getRow(0).createCell(2).setCellValue("Discount Price");
		sheet.getRow(0).createCell(3).setCellValue("RAM");
		sheet.getRow(0).createCell(4).setCellValue("Memory");
		sheet.getRow(0).createCell(5).setCellValue("Color");

		int i = 1;
		for (WebElement wb : allMobiles) {
			String mobileInfo = wb.getAttribute("alt");

			String[] mobileBrand = mobileInfo.split("\\(");
			String actPrice = driver.findElement(By.xpath("(//a[@alt='" + mobileInfo + "']/div/div[4]//span/span)[1]"))
					.getText();
			String[] actualPrice = actPrice.split(":");
			String discountPrice = driver
					.findElement(By.xpath("(//a[@alt='" + mobileInfo + "']/div/div[4]//span/span)[2]")).getText();

			sheet.createRow(i);
			sheet.getRow(i).createCell(0).setCellValue(mobileBrand[0]);
			sheet.getRow(i).createCell(1).setCellValue(actualPrice[1]);
			sheet.getRow(i).createCell(2).setCellValue(discountPrice);

			String[] memory = mobileBrand[1].split(",");

			if (mobileInfo.contains("RAM")) {
				sheet.getRow(i).createCell(3).setCellValue(memory[0].replace("RAM ", ""));
				sheet.getRow(i).createCell(4).setCellValue(memory[1]);
				sheet.getRow(i).createCell(5).setCellValue(memory[2].replace(")", ""));
			} else {
				sheet.getRow(i).createCell(4).setCellValue(memory[0]);
				sheet.getRow(i).createCell(5).setCellValue(memory[1].replace(")", ""));
			}
			i++;
			FileOutputStream fos = new FileOutputStream("./src/main/resources/data/mobileData.xlsx");
			workbook.write(fos);
			fos.close();
		}
		System.out.println("Pass");

	}

	/**
	 * Description Method click on Popular Brands Tab
	 * 
	 * @author Sajal jain
	 */
	public void clkPopularBrands() {
		WebActionUtil.waitForElement(tabPopularBrands, "Popular Brands Tab", 45);
		tabPopularBrands.click();
		/*
		 * WebActionUtil.clickOnElementUsingJS(tabPopularBrands, "Popular Brands Tab");
		 * WebActionUtil.clickOnWebElement(tabPopularBrands, "Popular Brands Tab",
		 * "Unable to Click on Popular Brands Tab");
		 */}

	/**
	 * Description Method click on Mobile Link
	 * 
	 * @author Sajal jain
	 */
	public void clkMobileLnk() {
		WebActionUtil.waitForElement(lnkMobile, "Mobile Link", 45);
		// WebActionUtil.clickOnElementUsingJS(lnkMobile, "Mobile Link");
		WebActionUtil.clickOnWebElement(lnkMobile, "Mobile Link", "Unable to Click on Mobile Link");
	}

	/**
	 * Description Method to Fetch Number
	 * 
	 * @author Sajal jain
	 */
	public void fetchNumber() {
		WebActionUtil.waitForElement(txtNumber, "Number Text", 45);
		String shopNumber = txtNumber.getText();
		WebActionUtil.pass(shopNumber);
		System.out.println("Chemist Number " + shopNumber);
	}

	/**
	 * Description Method to Fetch Address
	 * 
	 * @author Sajal jain
	 */
	public void fetchAddress() {
		WebActionUtil.waitForElement(txtAddress, "Address Text", 45);
		String shopAddress = txtAddress.getText();
		WebActionUtil.pass(shopAddress);
		System.out.println("Chemist address " + shopAddress);
	}

	/**
	 * Description Method click on Chemist Shop Link
	 * 
	 * @author Sajal jain
	 */
	public void clkChemistShop() {
		WebElement lnkChemistShop = driver.findElement(By.xpath("(//span[@class='lng_cont_name'])[1]"));
		expectedName = lnkChemistShop.getText();
		WebActionUtil.waitForElement(lnkChemistShop, "Chemist Shop Link", 45);
		WebActionUtil.clickOnElementUsingJS(lnkChemistShop, "Chemist Shop Link");
	}

	/**
	 * Description Method to validate Chemist Shop Name
	 * 
	 * @author Sajal jain
	 */
	public void validateChemistShop() {
		WebElement chemistShopName = driver.findElement(By.xpath("//span[@class='fn']"));
		String actualName = chemistShopName.getText();
		try {
			Assert.assertEquals(expectedName, actualName);
			System.out.println("Shop name matched");
		} catch (Exception e) {
			WebActionUtil.fail("Shop name is not matched");
		}
	}

	/**
	 * Description Method to click Chemist Tab
	 * 
	 * @author Sajal jain
	 * 
	 */
	public void clkChemistTab() {
		WebActionUtil.scrollToElement(tabChemist, "Chemist Tab");
		WebActionUtil.waitForElement(tabChemist, "Chemist Tab", 45);
		tabChemist.click();
		/*
		 * try { WebActionUtil.clickOnWebElement(tabChemist, "Chemist Tab",
		 * "Unable to click on Chemist Tab"); }catch(Exception e) {
		 * WebActionUtil.clickOnElementUsingJS(tabChemist, "Chemist Tab"); }
		 */

	}

	/**
	 * Description Method to select Location Drop down
	 * 
	 * @author Sajal jain
	 * @param location
	 */
	public void selectLocationDD(String location) {
		WebActionUtil.waitForElement(ddLocation, "Location Drop down", 45);
		WebActionUtil.clickOnElementUsingJS(ddLocation, "Location Drop down");
		WebActionUtil.clearText(ddLocation, "Location Text Box");
		WebActionUtil.typeText(ddLocation, location, "Location Text Box");
		// WebActionUtil.clickOnWebElement(ddTypeOfWork, "Location Drop down", "Unable
		// to click on Location Drop down");
		new WebDriverWait(driver, 30).until(ExpectedConditions.visibilityOfElementLocated(By.id("" + location + "")));
		// WebElement sddLocation = driver.findElement(By.id(""+location+""));
		driver.findElement(By.id("" + location + "")).click();
		/*
		 * WebActionUtil.isElementVisible(sddLocation, "Select Location Drop down");
		 * WebActionUtil.isElementClickable(sddLocation, "Select Location Drop down");
		 * WebActionUtil.waitForElement(sddLocation, "Select Location Drop down", 45);
		 * WebActionUtil.clickOnElementUsingJS(sddLocation,
		 * "Select Location Drop down");
		 */
	}

}
