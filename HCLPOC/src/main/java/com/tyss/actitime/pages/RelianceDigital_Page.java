package com.tyss.actitime.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.tyss.acttime.util.WebActionUtil;

/**
 * Description This class has the implementations of Reliance Digital methods
 * 
 * @author Sajal jain
 */
public class RelianceDigital_Page {
	public WebDriver driver;
	public WebActionUtil WebActionUtil;
	public long ETO = 10;
	public String expectedProductName;

	public RelianceDigital_Page(WebDriver driver, long ETO, WebActionUtil WebActionUtil) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.WebActionUtil = WebActionUtil;
		this.ETO = ETO;
	}

	/* Mobiles and Tablets Tab */
	@FindBy(id = "RIL_MobilesTabletsNavigationNode")
	private WebElement tabMobilesAndTablets;
	/* Television And Audio Tab */
	@FindBy(xpath = "//div[text()='TELEVISION & AUDIO']")
	private WebElement tabTelevisionAndAudio;
	/* Exclude Out of Stock Check Box */
	@FindBy(xpath = "//input[@id='Exclude out of Stock']/following-sibling::span[2]")
	private WebElement cbExcludeOutOfStock;
	/* Min Price Text Box */
	@FindBy(xpath = "//div[text()='Min.']/preceding-sibling::input")
	private WebElement txtMinPrice;
	/* Max Price Text Box */
	@FindBy(xpath = "//div[text()='Max.']/preceding-sibling::input")
	private WebElement txtMaxPrice;
	/* Go Button */
	@FindBy(xpath = "//span[text()='Go']")
	private WebElement btnGo;
	/* Sort By Drop down */
	@FindBy(xpath = "//select")
	private WebElement ddSortBy;
	/* Title Product Name */
	@FindBy(xpath = "//div[@class='pdp__title']")
	private WebElement titleProductName;
	/* Offer Price */
	@FindBy(xpath = "//span[@class='pdp__offerPrice']")
	private WebElement offerPrice;
	/* MRP Price */
	@FindBy(xpath = "//span[@class='pdp__mrpPrice']")
	private WebElement mrpPrice;
	/* Pin Code Text Box */
	@FindBy(id = "RIL_PDPInputPincode")
	private WebElement txtPinCode;
	/* Delivery Available Message */
	@FindBy(xpath = "//div[text()='Standard Delivery By:']")
	private WebElement deliveryAvailable;
	/* Delivery Not Available Message */
	@FindBy(xpath = "//div[contains(text(),'Delivery to this PIN code is unavailable')]")
	private WebElement deliveryUnAvailable;
	/* Specifications Header */
	@FindBy(xpath = "//h3[text()='Specifications']")
	private WebElement hdrSpecifications;
	/* General Information Header */
	@FindBy(xpath = "//div[text()='General Information']")
	private WebElement hdrGeneralInformation;

	/**
	 * Description Method to Print General Information
	 * 
	 * @author Sajal jain
	 * 
	 */
	public void printGeneralInformation() {
		WebActionUtil.scrollToElement(hdrGeneralInformation, "General Information Header");
		WebActionUtil.waitForElement(hdrGeneralInformation, "General Information Header", 60);

		List<WebElement> general = driver
				.findElements(By.xpath("//div[text()='General Information']/following-sibling::ul/div/div[1]"));
		List<WebElement> information = driver
				.findElements(By.xpath("//div[text()='General Information']/following-sibling::ul/div/div[2]"));

		for (int i = 0; i < general.size(); i++) {
			System.out.println(general.get(i).getText() + " : " + information.get(i).getText());
		}
	}

	/**
	 * Description Method to Print Specification
	 * 
	 * @author Sajal jain
	 * 
	 */
	public void printSpecifications() {
		WebActionUtil.scrollToElement(hdrSpecifications, "Specifications Header");
		WebActionUtil.waitForElement(hdrSpecifications, "Specifications Header", 60);

		List<WebElement> manufacturing = driver.findElements(
				By.xpath("//div[text()='Manufacturing & Packing Information']/following-sibling::ul/div/div[1]"));
		List<WebElement> information = driver.findElements(
				By.xpath("//div[text()='Manufacturing & Packing Information']/following-sibling::ul/div/div[2]"));

		for (int i = 0; i < manufacturing.size(); i++) {
			System.out.println(manufacturing.get(i).getText() + " : " + information.get(i).getText());
		}
	}

	/**
	 * Description Method to Verify Delivery available at given pincode
	 * 
	 * @author Sajal jain
	 * @param pinCode
	 */
	public void verifyDeliveryAvailable(String pinCode) {
		WebActionUtil.waitForElement(deliveryAvailable, "Delivery available at :" + pinCode, 60);
		if (WebActionUtil.isElementVisible(deliveryAvailable, "Delivery available at :" + pinCode)) {
			System.out.println("Delivery available at : " + pinCode);
		} else {
			WebActionUtil.waitForElement(deliveryUnAvailable, "Delivery not available at :" + pinCode, 60);
			if (WebActionUtil.isElementVisible(deliveryUnAvailable, "Delivery not available at :" + pinCode)) {
				System.out.println("Delivery not available at : " + pinCode);
			}
		}
	}

	/**
	 * Description Method to Enter Pin Code Text Box
	 * 
	 * @author Sajal jain
	 * @param pinCode
	 */
	public void setPinCode(String pinCode) {
		WebActionUtil.waitForElement(txtPinCode, "Pin Code Text Box", 25);
		WebActionUtil.clearText(txtPinCode, "Pin Code Text Box");
		WebActionUtil.typeText(txtPinCode, pinCode, "Pin Code Text Box");
	}

	/**
	 * Description Method to Print Mrp Price
	 * 
	 * @author Sajal jain
	 * 
	 */
	public void printMrpPrice() {
		WebActionUtil.waitForElement(mrpPrice, "Mrp Price", 45);
		String price = mrpPrice.getText().substring(1);
		price = price.concat("Rs");
		System.out.println("MRP Price : " + price);
	}

	/**
	 * Description Method to Print Offer Price
	 * 
	 * @author Sajal jain
	 * 
	 */
	public void printOfferPrice() {
		WebActionUtil.waitForElement(offerPrice, "Offer Price", 45);
		String price = offerPrice.getText().substring(1);
		price = price.concat("Rs");
		System.out.println("Offer Price : " + price);
	}

	/**
	 * Description Method to Verify Title Product Name
	 * 
	 * @author Sajal jain
	 * 
	 */
	public void verifyTitle() {
		WebActionUtil.waitForElement(titleProductName, "Title Product Name", 45);
		WebActionUtil.verifytext(expectedProductName, titleProductName, "Title Product Name");
	}

	/**
	 * Description Method to Click on Compare Check Box
	 * 
	 * @author Sajal jain
	 * @param productName
	 */
	public void clkCompareCb(String productName) {
		WebElement cbCompare = driver
				.findElement(By.xpath("//p[text()='" + productName + "']/../../following-sibling::div//label/span[2]"));
		WebActionUtil.waitForElement(cbCompare, "Compare Check Box", 45);
	}

	/**
	 * Description Method to Click on Product
	 * 
	 * @author Sajal jain
	 * @param productName
	 */
	public void clkProduct(String productName) {
		WebElement product = driver.findElement(By.xpath("//p[text()='" + productName + "']"));
		WebActionUtil.waitForElement(product, "Product Name", 45);
		expectedProductName = product.getText();
		WebActionUtil.clickOnWebElement(product, "Product Name", "Unable to click on Product Name");
		WebActionUtil.switchWindow();
	}

	/**
	 * Description Method to select Sort By
	 * 
	 * @author Sajal jain
	 * @param sortBy
	 */
	public void selectSortByDD(String sortBy) {
		WebActionUtil.waitForElement(ddSortBy, "Sort By Drop down", 45);
		Select s = new Select(ddSortBy);
		s.selectByVisibleText(sortBy);
	}

	/**
	 * Description Method to click on Brand Check Box
	 * 
	 * @author Sajal jain
	 * @param brand
	 */
	public void clkBrandCb(String brand) {
		WebElement cbBrand = driver.findElement(By.xpath("//input[@id='" + brand + "']/following-sibling::span[2]"));
		WebActionUtil.scrollToElement(cbBrand, "Brand Check Box");
		WebActionUtil.isElementVisible(cbBrand, "Brand Check Box");
		WebActionUtil.waitForElement(cbBrand, "Brand Check Box", 45);
		WebActionUtil.clickOnElementUsingJS(
				driver.findElement(By.xpath("//input[@id='" + brand + "']/following-sibling::span[2]")),
				"Brand Check Box");
	}

	/**
	 * Description Method to click on Go Button
	 * 
	 * @author Sajal jain
	 */
	public void clkGoBtn() {
		WebActionUtil.isElementVisible(btnGo, "Go Button");
		WebActionUtil.waitForElement(btnGo, "Go Button", 45);
		WebActionUtil.clickOnElementUsingJS(btnGo, "Go Button");
	}

	/**
	 * Description Method to Enter Maximum Price Text Box
	 * 
	 * @author Sajal jain
	 * @param maxPrice
	 */
	public void setMaxPrice(String maxPrice) {
		WebActionUtil.waitForElement(txtMaxPrice, "Maximum Price Text Box", 25);
		WebActionUtil.clearText(txtMaxPrice, "Maximum Price Text Box");
		WebActionUtil.typeText(txtMaxPrice, maxPrice, "Maximum Price Text Box");
	}

	/**
	 * Description Method to Enter Minimum Price Text Box
	 * 
	 * @author Sajal jain
	 * @param minPrice
	 */
	public void setMinPrice(String minPrice) {
		WebActionUtil.waitForElement(txtMinPrice, "Minimum Price Text Box", 25);
		WebActionUtil.clearText(txtMinPrice, "Minimum Price Text Box");
		WebActionUtil.typeText(txtMinPrice, minPrice, "Minimum Price Text Box");
	}

	/**
	 * Description Method to click on Exclude out of Stock Check Box
	 * 
	 * @author Sajal jain
	 */
	public void clkExcludeOutOfStockCb() {
		WebActionUtil.isElementVisible(cbExcludeOutOfStock, "Exclude out of Stock Check Box");
		WebActionUtil.waitForElement(cbExcludeOutOfStock, "Exclude out of Stock Check Box", 45);
		WebActionUtil.clickCheckBox(cbExcludeOutOfStock, "Exclude out of Stock Check Box");
	}

	/**
	 * Description Method to select Mobiles And Tablets Drop down
	 * 
	 * @author Sajal jain
	 * @param mobileAndTablets
	 */
	public void selectTelevisionAndAudioDD(String mobileAndTablets) {
		WebActionUtil.waitForElement(tabMobilesAndTablets, "Mobiles And Tablets Drop down", 45);
		WebActionUtil.action.moveToElement(tabMobilesAndTablets).perform();
		WebActionUtil.waitForAngularPageLoad();
		WebElement otnMobiles = driver.findElement(By.xpath("//a[text()='" + mobileAndTablets + "']"));
		WebActionUtil.isElementVisible(otnMobiles, "Mobiles And Tablets Drop down");
		WebActionUtil.isElementClickable(otnMobiles, "Mobiles And Tablets Drop down");
		WebActionUtil.waitForElement(otnMobiles, "Mobiles And Tablets Drop down", 45);
		WebActionUtil.clickOnElementUsingJS(otnMobiles, "Mobiles And Tablets Drop down");
	}

}
