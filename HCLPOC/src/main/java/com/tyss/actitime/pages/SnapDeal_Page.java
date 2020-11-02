package com.tyss.actitime.pages;

import org.testng.AssertJUnit;

import java.io.FileInputStream;
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

import com.tyss.acttime.baseutil.BaseTest;
import com.tyss.acttime.util.WebActionUtil;

/**
 * Description This class has the implementations of Snap Deal methods
 * 
 * @author Sajal jain
 */
public class SnapDeal_Page {
	public WebDriver driver;
	public WebActionUtil WebActionUtil;
	public long ETO = 10;
	public String expectedProductName;
	public String expectedPrice;

	public SnapDeal_Page(WebDriver driver, long ETO, WebActionUtil WebActionUtil) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.WebActionUtil = WebActionUtil;
		this.ETO = ETO;
	}

	/* All Offers Drop down */
	@FindBy(className = "navlink")
	private WebElement ddAllOffers;
	/* Product Link */
	@FindBy(xpath = "(//p[contains(@class,'product-title')])[1]")
	private WebElement lnkProduct;
	/* Product Name Text */
	@FindBy(xpath = "//h1[@itemprop='name']")
	private WebElement productName;
	/* Pincode Text Box */
	@FindBy(id = "pincode-check")
	private WebElement txtPincode;
	/* Check Button */
	@FindBy(id = "pincode-check-bttn")
	private WebElement btnCheck;
	/* Delivery Text Message */
	@FindBy(xpath = "//p[@class='itm-avail']")
	private WebElement msgDelivery;
	/* Delivery Code Text Message */
	@FindBy(id = "pincode-val")
	private WebElement msgDeliveryCode;
	/* Delivery Days Text Message */
	@FindBy(xpath = "//div[@class='ddrPinCheck otoRangeCheck']")
	private WebElement msgDeliveryDays;
	/* Cash On Delivery Text Message */
	@FindBy(id = "pincode-cod")
	private WebElement msgCashDelivery;
	/* Delivery Not available Text Message */
	@FindBy(xpath = "//div[contains(text(),'Item not available at this location')]")
	private WebElement msgNotDelivery;
	/* Add to Cart Button */
	@FindBy(id = "add-cart-button-id")
	private WebElement btnAddToCart;
	/* Add to cart success message */
	@FindBy(xpath = "//span[@class='mess-text']")
	private WebElement txtSuccessMsg;
	/* View Cart Button */
	@FindBy(xpath = "//div[text()='View Cart']")
	private WebElement btnViewCart;
	/* Actual Product Name Text*/
	@FindBy(xpath = "//div[@class='item-name-wrapper']/a")
	private WebElement txtActualProductName;
	/* Actual Size Text*/
	@FindBy(xpath = "//p[@class='item-extra-feature']")
	private WebElement txtActualSize;
	/* Actual Price Text*/
	@FindBy(xpath = "//span[@class='item-price']")
	private WebElement txtActualPrice;
	/* Mobile And Tablets Drop down*/
	@FindBy(xpath = "//span[text()='Mobile & Tablets']")
	private WebElement ddMobileAndTablets;
	/*All Screen Guards Phone Text*/
	@FindBys({@FindBy(xpath="//p[@class='product-title']")})
	private List<WebElement> allScreenGuardsName;
	/*All Screen Guards Price Text*/
	@FindBys({@FindBy(xpath="//p[@class='product-title']/following-sibling::div/div/span[2]")})
	private List<WebElement> allScreenGuardsPrice;
	
	
	
	/**
	 * Description Method to Print Screen Guards Details Text
	 * 
	 * @author Sajal jain
	 */
	public void printScreenGuardsDetails(String minValue,String maxValue) {
	new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOfAllElements(allScreenGuardsName));
		for(WebElement wb:allScreenGuardsName) {
	WebElement txtPrice = driver.findElement(By.xpath("//p[text()='"+wb.getText()+"']/following-sibling::div/div/span[2]"));
	String StringPrice = txtPrice.getAttribute("data-price");
	int intPrice = Integer.parseInt(StringPrice);
	int intMin = Integer.parseInt(minValue);
	int intMax = Integer.parseInt(maxValue);
	if(intPrice>intMin&&intPrice<intMax) {
		BaseTest.logger.info(wb.getText()+" : "+txtPrice.getText());
	}
	}
	}
	
	
	/**
	 * Description Method to Select Mobile And Tablets Drop down
	 * 
	 * @author Sajal jain
	 * @param mobileAndTablets
	 */
	public void selectMobileAndTabletsDD(String mobileAndTablets) {
		WebActionUtil.waitForElement(ddMobileAndTablets, "Mobile And Tablets Drop down", 60);
		WebActionUtil.action.moveToElement(ddMobileAndTablets).perform();
		WebElement otnMobileAndTablets = driver.findElement(By.xpath("//span[text()='" + mobileAndTablets + "']"));
		WebActionUtil.isElementVisible(otnMobileAndTablets, "Select Mobile And Tablets Drop down");
		WebActionUtil.waitForElement(otnMobileAndTablets, "Select Mobile And Tablets Drop down", 60);
		WebActionUtil.clickOnElementUsingJS(otnMobileAndTablets, "Select Mobile And Tablets Drop down");
	}
	
	
	/**
	 * Description Method to validate Product Name ,Size, Price in Cart
	 * 
	 * @author Sajal jain
	 * @param expectedSize
	 */
	public void validateNameSizePrice(String expectedSize) {
		WebActionUtil.isElementVisible(txtActualProductName, "Product in Cart");
		WebActionUtil.waitForElement(txtActualProductName, "Product in Cart", 60);
		String actPN = txtActualProductName.getText();
		String actSize = txtActualSize.getText();
		String actPrice = txtActualPrice.getText();

		try {
			System.out.println("Expected : " + expectedProductName);
			System.out.println("Actual : " + actPN);
			Assert.assertTrue(actPN.contains(expectedProductName));
			System.out.println("Product matched in cart");
		} catch (Exception e) {
			System.out.println("Product Mismatched in cart");
		}
		try {
			System.out.println("Expected : " + expectedSize);
			System.out.println("Actual : " + actSize);
			Assert.assertTrue(actSize.contains(expectedSize));
			System.out.println("Size matched in cart");
		} catch (Exception e) {
			System.out.println("Size Mismatched in cart");
		}
		try {
			System.out.println("Expected : " + expectedPrice);
			System.out.println("Actual : " + actPrice);
			Assert.assertEquals(expectedPrice, actPrice);
			System.out.println("Price matched in cart");
		} catch (Exception e) {
			System.out.println("Price Mismatched in cart");
		}
	}

	/**
	 * Description Method to click on View Cart Button
	 * 
	 * @author Sajal jain
	 */
	public void clkViewCartBtn() {
		WebActionUtil.isElementVisible(btnViewCart, "View Cart Button");
		WebActionUtil.waitForElement(btnViewCart, "View Cart Button", 45);
		WebActionUtil.clickOnElementUsingJS(btnViewCart, "View Cart Button");
	}

	/**
	 * Description Method to validate Added to Cart success message
	 * 
	 * @author Sajal jain
	 * @param expectedMessage
	 */
	public void validateSuccessMsg(String expectedMessage) {
		WebActionUtil.isElementVisible(txtSuccessMsg, "Added to Cart Success Msg");
		String actualSuccessMsg = txtSuccessMsg.getText();
		System.out.println("Actual :" + actualSuccessMsg);
		if (txtSuccessMsg.isDisplayed()) {
			String actualMsg = txtSuccessMsg.getText();
			try {
				Assert.assertTrue(actualMsg.contains(expectedMessage));
				System.out.println("Product Successfully added to cart");
			} catch (Exception e) {
				System.out.println("Product is not added to cart");
			}
		} else {
			System.out.println("Product add to cart success msg is not displayed");
		}
	}

	/**
	 * Description Method to click on Add To Cart Button
	 * 
	 * @author Sajal jain
	 */
	public void clkAddToCartBtn() {
		WebActionUtil.isElementVisible(btnAddToCart, "Add To Cart Button");
		WebActionUtil.waitForElement(btnAddToCart, "Add To Cart Button", 45);
		WebActionUtil.clickOnElementUsingJS(btnAddToCart, "Add To Cart Button");
	}

	/**
	 * Description Method to print Delivery Details In Excel
	 * 
	 * @author Sajal jain
	 * @throws IOException
	 */
	public void printDeliveryDetailsInExcel() throws IOException {
		WebActionUtil.isElementVisible(msgDelivery, "Delivery Message");
		if (msgDelivery.isDisplayed()) {
			String msgDeliver = msgDelivery.getText();
			// String code = msgDeliveryCode.getText();
			String days = msgDeliveryDays.getText();
			System.out.println(days);
			String cash = msgCashDelivery.getText();
			FileInputStream fis = new FileInputStream("./src/main/resources/data/mobileData.xlsx");
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheetAt(1);
			sheet.createRow(0);
			sheet.getRow(0).createCell(0).setCellValue(msgDeliver);
			// sheet.getRow(0).createCell(1).setCellValue(code);
			sheet.getRow(0).createCell(1).setCellValue(days);
			sheet.getRow(0).createCell(2).setCellValue(cash);
			FileOutputStream fos = new FileOutputStream("./src/main/resources/data/mobileData.xlsx");
			workbook.write(fos);
			fos.close();
			System.out.println("Pass");
		} else {
			String notDelivered = msgNotDelivery.getText();
			System.out.println("Delivery not available at given pin code :" + notDelivered);
		}
	}

	/**
	 * Description Method to click on Check Button
	 * 
	 * @author Sajal jain
	 */
	public void clkCheckBtn() {
		WebActionUtil.isElementVisible(btnCheck, "Check Button");
		WebActionUtil.waitForElement(btnCheck, "Check Button", 45);
		WebActionUtil.clickOnElementUsingJS(btnCheck, "Check Button");
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
	 * Description Method to validate Size
	 * 
	 * @author Sajal jain
	 * @param expectedSize
	 */
	public void validateSize(String expectedSize) {
		WebElement size = driver.findElement(By.xpath("//div[text()='" + expectedSize + "']/.."));
		WebActionUtil.waitForElement(size, "Size", 60);
		WebActionUtil.clickOnElementUsingJS(size, "Size");
		String value = size.getAttribute("data-sold");
		System.out.println("data sold " + value);
		if (value.equals("false")) {
			System.out.println(expectedSize + " is available");
		} else {
			System.out.println(expectedSize + " is not available");
		}
	}

	/**
	 * Description Method to validate Product Name
	 * 
	 * @author Sajal jain
	 */
	public void validateProductName() {
		WebActionUtil.waitForElement(productName, "Product Name", 60);
		String actualProductName = productName.getText();
		try {
			AssertJUnit.assertEquals(expectedProductName, actualProductName);
			System.out.println("Product Title Matched");
		} catch (Exception e) {
			System.out.println("Product Title not Matched");
		}
	}

	/**
	 * Description Method to click on Product Link
	 * 
	 * @author Sajal jain
	 */
	public void clkProductLnk() {
		WebActionUtil.isElementVisible(lnkProduct, "Product Link");
		WebActionUtil.waitForElement(lnkProduct, "Product Link", 45);
		WebActionUtil.clickOnElementUsingJS(lnkProduct, "Product Link");
		WebActionUtil.switchWindow();
	}

	/**
	 * Description Method to Fetch Expected Product
	 * 
	 * @author Sajal jain
	 * 
	 */
	public void fetchExpectedProductName() {
		WebActionUtil.waitForElement(lnkProduct, "Product Name", 90);
		expectedProductName = lnkProduct.getText();
		System.out.println("Expected Product Name : " + expectedProductName);
	}

	/**
	 * Description Method to Fetch Expected Price
	 * 
	 * @author Sajal jain
	 * 
	 */
	public void fetchExpectedPrice() {
		WebActionUtil.waitForElement(
				driver.findElement(
						By.xpath("//p[text()='" + expectedProductName + "']/following-sibling::div/div/span[2]")),
				"Price", 90);
		WebElement price = driver
				.findElement(By.xpath("//p[text()='" + expectedProductName + "']/following-sibling::div/div/span[2]"));
		expectedPrice = price.getText();
		System.out.println("Expected Price : " + expectedPrice);
	}

	/**
	 * Description Method to select Color Filter Check Box
	 * 
	 * @author Sajal jain
	 * @param color
	 */
	public void clkColorFilterCb(String color) {
		WebActionUtil.scrollToElement(
				driver.findElement(By.xpath("//input[@value='" + color + "']/following-sibling::label")),
				"Color Filter Check Box");
		WebElement cbColor = driver.findElement(By.xpath("//input[@value='" + color + "']/following-sibling::label"));
		WebActionUtil.isElementVisible(cbColor, "Color Filter Check Box");
		WebActionUtil.waitForElement(cbColor, "Color Filter Check Box", 90);
		WebActionUtil.clickOnElementUsingJS(cbColor, "Color Filter Check Box");
		WebActionUtil.poll(2000);
	}

	/**
	 * Description Method to select Size Filter Check Box
	 * 
	 * @author Sajal jain
	 * @param size
	 * 
	 */
	public void clkSizeFilterCb(String size) {
		WebActionUtil.scrollToElement(
				driver.findElement(By.xpath("//input[@value='" + size + "']/following-sibling::label")),
				"Size Filter Check Box");
		WebElement cbSize = driver.findElement(By.xpath("//input[@value='" + size + "']/following-sibling::label"));
		WebActionUtil.isElementVisible(cbSize, "Size Filter Check Box");
		WebActionUtil.waitForElement(cbSize, "Size Filter Check Box", 90);
		WebActionUtil.clickOnElementUsingJS(cbSize, "Size Filter Check Box");
	}

	/**
	 * Description Method to Select Men's Fashion from All Offers Drop down
	 * 
	 * @author Sajal jain
	 * @param mensFashion
	 */
	public void selectMensFashionOtn(String mensFashion) {
		WebActionUtil.waitForElement(ddAllOffers, "All Offers Drop down", 60);
		WebActionUtil.action.moveToElement(ddAllOffers).perform();
		WebElement otnMensFashion = driver.findElement(By.xpath("//span[text()='" + mensFashion + "']"));
		WebActionUtil.isElementVisible(otnMensFashion, "Select Mens Fashion Drop down");
		WebActionUtil.waitForElement(otnMensFashion, "Select Mens Fashion Drop down", 60);
		WebActionUtil.clickOnElementUsingJS(otnMensFashion, "Select Mens Fashion Drop down");
	}
}
