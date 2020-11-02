package com.tyss.actitime.pages;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.tyss.acttime.baseutil.BaseTest;
import com.tyss.acttime.util.WebActionUtil;

/**
 * Description This class has the implementations of Blue Stone
 * 
 * @author Sajal jain
 */
public class BlueStone_Page {
	public WebDriver driver;
	public WebActionUtil WebActionUtil;
	public long ETO = 10;

	public BlueStone_Page(WebDriver driver, long ETO, WebActionUtil WebActionUtil) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.WebActionUtil = WebActionUtil;
		this.ETO = ETO;
	}

	/* Rings Drop down */
	@FindBy(xpath = "//a[text()='Rings ']/..")
	private WebElement ddRings;
	/* Price Filter Drop down */
	@FindBy(id = "Price-form")
	private WebElement ddPriceFilter;
	/* Sort By Drop down */
	@FindBy(xpath = "//div[text()='Sort by']/../section/span/span")
	private WebElement ddSortBy;
	/* Metal Filter Drop down */
	@FindBy(id = "Metal-form")
	private WebElement ddMetalFilter;
	/* Gold Purity Filter Drop down */
	@FindBy(id = "Gold Purity-form")
	private WebElement ddGoldPurityFilter;
	/* Gender Filter Drop down */
	@FindBy(id = "Gender-form")
	private WebElement ddGenderFilter;
	/* Offers Filter Drop down */
	@FindBy(id = "Offers-form")
	private WebElement ddOffersFilter;
	/* Product Details */
	@FindBy(xpath = "//h2[text()='Product Details']")
	private WebElement productDetails;
	/* Mrp Price */
	@FindBy(id = "our_price_display")
	private WebElement mrpPrice;
	/* Discount Price */
	@FindBy(id = "discountedPriceSpan")
	private WebElement discountPrice;
	/* Pincode Text Box */
	@FindBy(id = "getNearestStore")
	private WebElement txtPincode;
	/* Update Button */
	@FindBy(xpath = "//div[text()='Update']")
	private WebElement btnUpdate;
	/* Delivery Text Message */
	@FindBy(xpath = "//div[@class='store-delivery']")
	private WebElement msgDelivery;
	/* Non Delivery Text Message */
	@FindBy(xpath = "//div[contains(text(),'Please try again with any other Pincode')]")
	private WebElement msgNonDelivery;
	/* Search Text Box */
	@FindBy(id = "search_query_top_elastic_search")
	private WebElement txtSearch;
	/* Search Button */
	@FindBy(name = "submit_search")
	private WebElement btnSearch;
	/* Sign up Tab */
	@FindBy(id = "signup")
	private WebElement tabSignup;
	/* Name Text Box */
	@FindBy(id = "search_query_top_elastic_search")
	private WebElement txtName;
	/* Email Id Text Box */
	@FindBy(id = "search_query_top_elastic_search")
	private WebElement txtEmailId;
	/* Mobile Number Text Box */
	@FindBy(id = "search_query_top_elastic_search")
	private WebElement txtMobileNumber;
	/* Create Account Button */
	@FindBy(id = "search_query_top_elastic_search")
	private WebElement btnCreateAccount;
	/* I Accept Blue Stone Check Box */
	@FindBy(id = "terms_condition")
	private WebElement cbIAcceptBlueStone;
	/* Buy Now Button */
	@FindBy(id = "buy-now")
	private WebElement btnBuyNow;
	/* Product Name Header */
	@FindBy(xpath = "//div[@class='product-header row']/div/a")
	private WebElement hdrProductName;
	/* Remove Link */
	@FindBy(xpath = "//a[contains(text(),'Remove')]")
	private WebElement lnkRemove;
	/*Next Day Delivery Drop down*/
	@FindBy(xpath="//a[text()='Next Day Delivery ']")
	private WebElement ddNextDayDelivery;
	/*Next Day Delivery Option*/
	@FindBy(xpath="//a[text()='Next Day Delivery ']/following-sibling::div/ul/li/span")
	private WebElement otnNextDayDelivery;
	

	/**
	 * Description Method to Calculate Discount Percentage
	 * 
	 * @author Sajal jain
	 */
	public void calculateDiscount() {
		WebActionUtil.waitForElement(driver.findElement(By.xpath("(//div[@class='p-image']/a/img)[1]")), "Item Name", 40);
		WebElement item = driver.findElement(By.xpath("(//div[@class='p-image']/a/img)[1]"));
		String itemName = item.getAttribute("alt");
		BaseTest.logger.info(itemName);
		WebElement mrpPrice = driver.findElement(By.xpath("//img[@alt='"+itemName+"']/../../following-sibling::span//span[@id='bst-actual-price']"));
		BaseTest.logger.info("MRP Price : "+mrpPrice.getText());
		String mrp = mrpPrice.getText().substring(3).replace(",","");
		int mrpP = Integer.parseInt(mrp);
		WebElement actualPrice = driver.findElement(By.xpath("//img[@alt='"+itemName+"']/../../following-sibling::span//span[@id='bst-discounted-price']"));
		BaseTest.logger.info("Actual Price : "+actualPrice.getText());
		String act = actualPrice.getText().substring(3).replace(",","");
		int actP = Integer.parseInt(act);
		double discount=mrpP-actP;
		double discountPrecentage=discount*100/mrpP;
		DecimalFormat numberFormat = new DecimalFormat("#.00");
		BaseTest.logger.info("Discount Percentage : "+numberFormat.format(discountPrecentage)+" %");
	}
	
	
	
	/**
	 * Description Method to Fetch All Products Name given making charge
	 * 
	 * @author Sajal jain
	 * @param makingCharge
	 * @throws IOException 
	 */
	public void fetchAllProducts(String makingCharge) throws IOException {
		List<WebElement> allItem = driver.findElements(By.xpath("//span[text()='"+makingCharge+"']/../../preceding-sibling::div/a/img"));
		BaseTest.logger.info("Number of Items "+allItem.size());
		BaseTest.logger.info("Items With "+makingCharge);
		
		FileInputStream fis = new FileInputStream("./src/main/resources/data/mobileData.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(2);
		int i=0;
		for(WebElement wb:allItem) {
			BaseTest.logger.info(wb.getAttribute("alt"));
			sheet.createRow(i).createCell(0).setCellValue(wb.getAttribute("alt"));
			i++;
		}
		//sheet.getRow(0).createCell(1).setCellValue("Actual Price");
		FileOutputStream fos = new FileOutputStream("./src/main/resources/data/mobileData.xlsx");
		workbook.write(fos);
		fos.close();
		System.out.println("Pass");
	}
	
	/**
	 * Description Method to Select Next Day Delivery Drop down
	 * 
	 * @author Sajal jain
	 */
	public void selectNextDayDeliveryDD() {
		WebActionUtil.waitForElement(ddNextDayDelivery, "Next Day Delivery Drop down", 60);
		WebActionUtil.action.moveToElement(ddNextDayDelivery).perform();
		WebActionUtil.isElementVisible(otnNextDayDelivery, "Select Next Day Delivery Drop down");
		WebActionUtil.waitForElement(otnNextDayDelivery, "Select Next Day Delivery Drop down", 90);
		WebActionUtil.clickOnElementUsingJS(otnNextDayDelivery, "Select Next Day Delivery Drop down");
	}
	
	/**
	 * Description Method to click on remove link
	 * 
	 * @author Sajal jain
	 */
	public void clkRemoveLnk() {
		WebActionUtil.isElementVisible(lnkRemove, "Remove Link");
		WebActionUtil.waitForElement(lnkRemove, "Remove Link", 45);
		WebActionUtil.clickOnElementUsingJS(lnkRemove, "Remove Link");
	}

	/**
	 * Description Method to Validate Product in Cart
	 * 
	 * @author Sajal jain
	 * @param expectedProductName
	 */
	public void validateProductInCart(String expectedProductName) {
		WebActionUtil.scrollToElement(hdrProductName, "Product Title Name");
		WebActionUtil.isElementVisible(hdrProductName, "Product Title Name");
		String actualProductName = hdrProductName.getText();
		try {
			Assert.assertEquals(expectedProductName, actualProductName);
			System.out.println("Product is present in cart");
		} catch (Exception e) {
			System.out.println("Product is not present in cart");
		}
	}

	/**
	 * Description Method to click on Buy Now Button
	 * 
	 * @author Sajal jain
	 */
	public void clkBuyNowBtn() {
		WebActionUtil.isElementVisible(btnBuyNow, "Buy Now Button");
		WebActionUtil.waitForElement(btnBuyNow, "Buy Now Button", 45);
		WebActionUtil.clickOnElementUsingJS(btnBuyNow, "Buy Now Button");
	}

	/**
	 * Description Method to Click on View Details Tab
	 * 
	 * @author Sajal jain
	 * @param productName
	 */
	public void clkViewDetailsTab(String productName) {
		WebActionUtil.scrollToElement(driver.findElement(By.xpath("//img[@alt='" + productName + "']")),
				"Product Name Link");
		WebElement lnkProductName = driver.findElement(By.xpath("//img[@alt='" + productName + "']"));
		WebActionUtil.isElementVisible(lnkProductName, "Product Name Link");
		// WebActionUtil.waitForElement(lnkProductName, "Product Name Link", 45);
		WebActionUtil.action.moveToElement(lnkProductName).perform();
		WebElement tabViewDetails = driver.findElement(
				By.xpath("//img[@alt='" + productName + "']/../../following-sibling::span/div/div/div[2]/a"));
		WebActionUtil.isElementVisible(tabViewDetails, "View Details Link");
		WebActionUtil.clickOnElementUsingJS(tabViewDetails, "View Details Link");
		// WebActionUtil.clickOnWebElement(tabViewDetails, "View Details Link","Unable
		// to click on View Details Link");
		WebActionUtil.switchWindow();
	}

	/**
	 * Description Method to click on I Accept Blue Stone Check Box
	 * 
	 * @author Sajal jain
	 */
	public void clkIAcceptBlueStoneCb() {
		WebActionUtil.isElementVisible(cbIAcceptBlueStone, "I Accept Blue Stone Check Box");
		WebActionUtil.waitForElement(cbIAcceptBlueStone, "I Accept Blue Stone Check Box", 45);
		WebActionUtil.clickOnElementUsingJS(cbIAcceptBlueStone, "I Accept Blue Stone Check Box");
	}

	/**
	 * Description Method to Enter in Mobile Number Text Box
	 * 
	 * @author Sajal jain
	 * @param mobileNumber
	 */
	public void setMobileNumber(String mobileNumber) {
		WebActionUtil.waitForElement(txtMobileNumber, "Mobile Number Text Box", 25);
		WebActionUtil.typeText(txtMobileNumber, mobileNumber, "Mobile Number Text Box");
	}

	/**
	 * Description Method to Enter in EmailId Text Box
	 * 
	 * @author Sajal jain
	 * @param emailId
	 */
	public void setEmailId(String emailId) {
		WebActionUtil.waitForElement(txtEmailId, "EmailId Text Box", 25);
		WebActionUtil.typeText(txtEmailId, emailId, "EmailId Text Box");
	}

	/**
	 * Description Method to Enter in Name Text Box
	 * 
	 * @author Sajal jain
	 * @param name
	 */
	public void setName(String name) {
		WebActionUtil.isElementVisible(txtName, "Name Text Box");
		WebActionUtil.waitForElement(txtName, "Name Text Box", 25);
		WebActionUtil.typeText(txtName, name, "Name Text Box");
	}

	/**
	 * Description Method to click on Create Account Button
	 * 
	 * @author Sajal jain
	 */
	public void clkCreateAccountBtn() {
		WebActionUtil.isElementVisible(btnCreateAccount, "Create Account Button");
		WebActionUtil.waitForElement(btnCreateAccount, "Create Account Button", 45);
		WebActionUtil.clickOnElementUsingJS(btnCreateAccount, "Create Account Button");
		WebActionUtil.poll(2000);
	}

	/**
	 * Description Method to click on Signup Tab
	 * 
	 * @author Sajal jain
	 */
	public void clkSignupTab() {
		WebActionUtil.isElementVisible(tabSignup, "Signup Tab");
		WebActionUtil.waitForElement(tabSignup, "Signup Tab", 45);
		WebActionUtil.clickOnElementUsingJS(tabSignup, "Signup Tab");
		WebActionUtil.poll(2000);
	}

	/**
	 * Description Method to Enter in Search Text Box
	 * 
	 * @author Sajal jain
	 * @param search
	 */
	public void setSearch(String search) {
		WebActionUtil.waitForElement(txtSearch, "Search Text Box", 25);
		WebActionUtil.clearText(txtSearch, "Search Text Box");
		WebActionUtil.typeText(txtSearch, search, "Search Text Box");
	}

	/**
	 * Description Method to click on Search Button
	 * 
	 * @author Sajal jain
	 */
	public void clkSearchBtn() {
		WebActionUtil.isElementVisible(btnSearch, "Search Button");
		WebActionUtil.waitForElement(btnSearch, "Search Button", 45);
		WebActionUtil.clickOnElementUsingJS(btnSearch, "Search Button");
	}

	/**
	 * Description Method to Validate Delivery
	 * 
	 * @author Sajal jain
	 */
	public void validateDelivery() {
		try {
			WebActionUtil.isElementVisible(msgDelivery, "Delivery Message");
			WebActionUtil.waitForElement(msgDelivery, "Delivery Message", 45);
			System.out.println(msgDelivery.getText());
		} catch (Exception e) {
			WebActionUtil.isElementVisible(msgNonDelivery, "Non Delivery Message");
			WebActionUtil.waitForElement(msgNonDelivery, "Non Delivery Message", 45);
			System.out.println(msgNonDelivery.getText());
		}
	}

	/**
	 * Description Method to click on Update Button
	 * 
	 * @author Sajal jain
	 */
	public void clkUpdateBtn() {
		WebActionUtil.isElementVisible(btnUpdate, "Update Button");
		WebActionUtil.waitForElement(btnUpdate, "Update Button", 45);
		WebActionUtil.clickOnElementUsingJS(btnUpdate, "Update Button");
	}

	/**
	 * Description Method to Enter in Pincode Text Box
	 * 
	 * @author Sajal jain
	 * @param pincode
	 */
	public void setPincode(String pincode) {
		WebActionUtil.waitForElement(txtPincode, "Pincode Text Box", 25);
		WebActionUtil.clearText(txtPincode, "Pincode Text Box");
		WebActionUtil.typeText(txtPincode, pincode, "Pincode Text Box");
	}

	/**
	 * Description Method to fetch Discount Price
	 * 
	 * @author Sajal jain
	 */
	public void fetchDiscountPrice() {
		WebActionUtil.isElementVisible(discountPrice, "Discount Price Tab");
		WebActionUtil.waitForElement(discountPrice, "Discount Price Tab", 45);
		String price = discountPrice.getText();
		System.out.println("DiscountPrice : " + price);
	}

	/**
	 * Description Method to fetch Mrp Price
	 * 
	 * @author Sajal jain
	 */
	public void fetchMrpPrice() {
		WebActionUtil.isElementVisible(mrpPrice, "Mrp Price Tab");
		WebActionUtil.waitForElement(mrpPrice, "Mrp Price Tab", 45);
		String price = mrpPrice.getText();
		System.out.println("MrpPrice : " + price);
	}

	/**
	 * Description Method to validate Product Name
	 * 
	 * @author Sajal jain
	 * @param expectedProductName
	 */
	public void validateProductName(String expectedProductName) {
		WebElement product = driver.findElement(By.xpath("//input[@id='product_type_name']/following-sibling::h1"));
		String actualProductName = product.getText();
		try {
			Assert.assertEquals(expectedProductName, actualProductName);
			System.out.println("Product Title Matched");
		} catch (Exception e) {
			System.out.println("Product Title not Matched");
		}
	}

	/**
	 * Description Method to select Offers Filter Drop down
	 * 
	 * @author Sajal jain
	 * @param offers
	 */
	public void selectOffersDD(String offers) {
		WebActionUtil.waitForElement(ddOffersFilter, "Offers Filter Drop down", 60);
		WebActionUtil.action.moveToElement(ddOffersFilter).perform();
		WebElement otnOffersFilter = driver.findElement(By.xpath("//span[@data-displayname='" + offers + "']"));
		WebActionUtil.isElementVisible(otnOffersFilter, "Select Offers Filter Drop down");
		WebActionUtil.waitForElement(otnOffersFilter, "Select Offers Filter Drop down", 90);
		WebActionUtil.clickOnElementUsingJS(otnOffersFilter, "Select Offers Filter Drop down");
	}

	/**
	 * Description Method to select Gender Filter Drop down
	 * 
	 * @author Sajal jain
	 * @param gender
	 */
	public void selectGenderDD(String gender) {
		WebActionUtil.waitForElement(ddGenderFilter, "Gender Filter Drop down", 60);
		WebActionUtil.action.moveToElement(ddGenderFilter).perform();
		WebElement otnGenderFilter = driver.findElement(By.xpath("//span[@data-displayname='" + gender + "']"));
		WebActionUtil.isElementVisible(otnGenderFilter, "Select Gender Filter Drop down");
		WebActionUtil.waitForElement(otnGenderFilter, "Select Gender Filter Drop down", 90);
		WebActionUtil.clickOnElementUsingJS(otnGenderFilter, "Select Gender Filter Drop down");
	}

	/**
	 * Description Method to select Gold Purity Filter Drop down
	 * 
	 * @author Sajal jain
	 * @param goldPurity
	 */
	public void selectGoldPurityDD(String goldPurity) {
		WebActionUtil.waitForElement(ddGoldPurityFilter, "Gold Purity Filter Drop down", 60);
		WebActionUtil.action.moveToElement(ddGoldPurityFilter).perform();
		WebElement otnGoldPurityFilter = driver.findElement(By.xpath("//span[@data-displayname='" + goldPurity + "']"));
		WebActionUtil.isElementVisible(otnGoldPurityFilter, "Select Gold Purity Filter Drop down");
		WebActionUtil.waitForElement(otnGoldPurityFilter, "Select Gold Purity Filter Drop down", 90);
		WebActionUtil.clickOnElementUsingJS(otnGoldPurityFilter, "Select Gold Purity Filter Drop down");
	}

	/**
	 * Description Method to select Metal Filter Drop down
	 * 
	 * @author Sajal jain
	 * @param metal
	 */
	public void selectMetalFilterDD(String metal) {
		WebActionUtil.waitForElement(ddMetalFilter, "Metal Filter Drop down", 60);
		WebActionUtil.action.moveToElement(ddMetalFilter).perform();
		WebElement otnMetalFilter = driver.findElement(By.xpath("//span[@data-displayname='" + metal + "']"));
		WebActionUtil.isElementVisible(otnMetalFilter, "Select Metal Filter Drop down");
		WebActionUtil.waitForElement(otnMetalFilter, "Select Metal Filter Drop down", 90);
		WebActionUtil.clickOnElementUsingJS(otnMetalFilter, "Select Metal Filter Drop down");
	}

	/**
	 * Description Method to Print Product Details
	 * 
	 * @author Sajal jain
	 */
	public void printProductDetails() {
		WebActionUtil.scrollToElement(productDetails, "Product Details");
		WebActionUtil.waitForElement(productDetails, "Product Details", 30);
		System.out.println("Product Details : ");
		List<WebElement> product = driver
				.findElements(By.xpath("//h2[text()='Product Details']/following-sibling::div/dl/dt"));
		List<WebElement> details = driver
				.findElements(By.xpath("//h2[text()='Product Details']/following-sibling::div/dl/dd"));
		for (int i = 0; i < product.size(); i++) {
			System.out.println(product.get(i).getText() + " : " + details.get(i).getText());
		}
	}

	/**
	 * Description Method to click on Product Name Link
	 * 
	 * @author Sajal jain
	 * @param productName
	 */
	public void clkProductNameLnk(String productName) {
		WebActionUtil.scrollToElement(driver.findElement(By.xpath("//img[@alt='" + productName + "']")),
				"Product Name Link");
		WebElement lnkProductName = driver.findElement(By.xpath("//img[@alt='" + productName + "']"));
		WebActionUtil.isElementVisible(lnkProductName, "Product Name Link");
		WebActionUtil.waitForElement(lnkProductName, "Product Name Link", 45);
		WebActionUtil.action.moveToElement(lnkProductName).perform();
		WebActionUtil.clickOnElementUsingJS(lnkProductName, "Product Name Link");
	//	WebActionUtil.clickOnWebElement(lnkProductName, "Product Name Link", "Unable to click on Product Name Link");
		WebActionUtil.switchWindow();
	}

	/**
	 * Description Method to select Sort By Drop down
	 * 
	 * @author Sajal jain
	 * @param sortBy
	 */
	public void selectSortByDD(String sortBy) {
		WebActionUtil.waitForElement(ddSortBy, "Sort By Drop down", 60);
		WebActionUtil.action.moveToElement(ddSortBy).perform();
		WebElement otnSortBy = driver.findElement(By.xpath("//a[contains(text(),'" + sortBy + "')]"));
		WebActionUtil.isElementVisible(otnSortBy, "Select Sort By Drop down");
		// WebActionUtil.waitForElement(otnSortBy, "Select Sort By Drop down", 90);
		WebActionUtil.clickOnElementUsingJS(otnSortBy, "Select Sort By Drop down");
	}

	/**
	 * Description Method to select Price Filter Drop down
	 * 
	 * @author Sajal jain
	 * @param price
	 */
	public void selectPriceFilterDD(String price) {
		WebActionUtil.waitForElement(ddPriceFilter, "Price Filter Drop down", 60);
		WebActionUtil.action.moveToElement(ddPriceFilter).perform();
		WebElement otnPriceFilter = driver.findElement(By.xpath("//span[@data-displayname='" + price + "']"));
		WebActionUtil.isElementVisible(otnPriceFilter, "Select Price Filter Drop down");
		WebActionUtil.waitForElement(otnPriceFilter, "Select Price Filter Drop down", 90);
		WebActionUtil.clickOnElementUsingJS(otnPriceFilter, "Select Price Filter Drop down");
	}

	/**
	 * Description Method to Select Popular Ring Types from Rings Drop down
	 * 
	 * @author Sajal jain
	 * @param popularRingTypes
	 */
	public void selectPopularRingTypesOtn(String popularRingTypes) {
		WebActionUtil.waitForElement(ddRings, "Rings Module Drop down", 60);
		WebActionUtil.action.moveToElement(ddRings).perform();
		WebElement otnPopularRingTypes = driver.findElement(
				By.xpath("//div[text()='Popular Ring Types ']/../ul//a[text()='" + popularRingTypes + "']"));
		WebActionUtil.isElementVisible(otnPopularRingTypes, "Select Popular Ring Types Drop down");
		WebActionUtil.waitForElement(otnPopularRingTypes, "Select Popular Ring Types Drop down", 90);
		WebActionUtil.clickOnElementUsingJS(otnPopularRingTypes, "Select Popular Ring Types Drop down");
	}

}
