package com.tyss.actitime.pages;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.tyss.acttime.util.WebActionUtil;

/**
 * Description This class has the implementations of the Just Dial related
 * methods
 * 
 * @author Sajal jain
 */
public class JustDial_Page {
	public WebDriver driver;
	public WebActionUtil WebActionUtil;
	public long ETO = 10;

	public JustDial_Page(WebDriver driver, long ETO, WebActionUtil WebActionUtil) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.WebActionUtil = WebActionUtil;
		this.ETO = ETO;
	}

	/* Location Drop down */
	@FindBy(id = "city")
	private WebElement ddLocation;
	/* All India Drop down */
	@FindBy(id = "ctypebtn")
	private WebElement ddAllIndia;
	/* Enter City Name Text Box */
	@FindBy(id = "ctypeinput")
	private WebElement txtEnterCityName;
	/* Business Types Drop down */
	@FindBy(id = "btypebtn")
	private WebElement ddBusinessTypes;
	/* Price Drop down */
	@FindBy(id = "ptypebtn")
	private WebElement ddPrice;
	/* With Price Check Box */
	@FindBy(xpath = "//span[text()='With Price']/preceding-sibling::span")
	private WebElement cbWithPrice;
	/* Next Link */
	@FindBy(xpath = "//div[@class='swiper-button-next']")
	private WebElement lnkNext;
	/* Search Text Box */
	@FindBy(id = "srchbx")
	private WebElement txtSearchBox;
	/* Price Low to High Tab */
	@FindBy(xpath = "//span[@value='price_asc']")
	private WebElement tabPriceLowToHigh;
	/* Rating Tab */
	@FindBy(xpath = "//span[@class='total-rate']")
	private WebElement tabRating;
	/* Your Name Text Box */
	@FindBy(id = "bd_name")
	private WebElement txtYourName;
	/* Your Mobile Number Text Box */
	@FindBy(id = "bd_mobile")
	private WebElement txtYourMobileNumber;
	/* Close Tab */
	@FindBy(xpath = "(//span[contains(@onclick,'closePopUp')])[4]")
	private WebElement tabClose;
	/* Distance Drop down */
	@FindBy(xpath = "//span[text()='Distance ']")
	private WebElement ddDistance;
	/* Clear Filter Link */
	@FindBy(xpath = "//span[text()='Clear All']/../..")
	private WebElement lnkCLearFilter;
	/* Add To compare Tab */
	@FindBy(xpath = "//span[text()='Add to Compare']")
	private WebElement tabAddToCompare;
	/* Down Arrow Link */
	@FindBy(xpath = "//a[@class='minimise']")
	private WebElement lnkDownArrow;
	/* View All Categories Link */
	@FindBy(xpath = "//a[contains(text(),'View All Categories')]")
	private WebElement lnkViewAllCategories;
	/* Cuisines Tab */
	@FindBy(xpath = "//span[text()=' Cuisines ']")
	private WebElement tabCuisines;

	/**
	 * Description Method to Print Cuisines Services
	 * 
	 * @author Sajal jain
	 */
	public void printCuisinesServices() {
		List<WebElement> cuisinesServices = driver
				.findElements(By.xpath("//p[text()='Cuisines']/following-sibling::li"));
		System.out.println("Cuisines Services : ");
		for (WebElement wb : cuisinesServices) {
			System.out.println(wb.getText());
		}
	}

	/**
	 * Description Method to Print Services and Amenities
	 * 
	 * @author Sajal jain
	 */
	public void printServicesAndAmenities() {
		List<WebElement> servicesAndAmenities = driver
				.findElements(By.xpath("//p[text()='Services & Amenities']/following-sibling::li"));
		System.out.println("Services and Amenities : ");
		for (WebElement wb : servicesAndAmenities) {
			System.out.println(wb.getText());
		}
	}

	/**
	 * Description Method to Print Cuisines
	 * 
	 * @author Sajal jain
	 */
	public void printCuisines() {
		System.out.println("Cuisines : ");
		WebActionUtil.scrollToElement(tabCuisines, "Cuisines");
		WebActionUtil.isElementVisible(tabCuisines, "Cuisines");
		System.out.println(tabCuisines.getText());
	}

	/**
	 * Description Method to click on Restaurant Name
	 * 
	 * @author Sajal jain
	 * @param restaurantName
	 * 
	 */
	public void clkRestaurantName(String restaurantName) throws InterruptedException {
		WebActionUtil.scrollToElement(driver.findElement(By.xpath("//span[text()='" + restaurantName + "']")),
				"Restaurant Name");
		WebElement tabRestaurant = driver.findElement(By.xpath("//span[text()='" + restaurantName + "']"));
		WebActionUtil.waitForAngularPageLoad();
		WebActionUtil.isElementVisible(tabRestaurant, "Restaurant Name");
		WebActionUtil.waitForElement(tabRestaurant, "Restaurant Name", 90);
		driver.findElement(By.xpath("//span[text()='" + restaurantName + "']")).click();
		/*
		 * WebActionUtil.clickOnElementUsingJS(tabRestaurant, "Restaurant Name");
		 */
	}

	/**
	 * Description Method to Print Restaurant Name
	 * 
	 * @author Sajal jain
	 * @param count
	 */
	public void printRestaurantName(String count) {
		List<WebElement> allRestaurant = driver.findElements(By.xpath("//span[@class='lng_cont_name']"));
		int count1 = Integer.parseInt(count);
		for (int i = 0; i < count1; i++) {
			try {
				System.out.println(allRestaurant.get(i).getText());
			} catch (Exception e) {
				System.out.println("No. of Restaurants present are less than the count");
			}
		}
	}

	/**
	 * Description Method to click on Restaurant Tab
	 * 
	 * @author Sajal jain
	 * @param restaurantType
	 */
	public void clkRestaurantTab(String restaurantType) {
		WebActionUtil.scrollToElement(driver.findElement(By.xpath("//span[text()='" + restaurantType + "']")),
				"Restaurant Tab");
		WebActionUtil.waitForAngularPageLoad();
		WebElement tabRestaurant = driver.findElement(By.xpath("//span[text()='" + restaurantType + "']"));
		driver.findElement(By.xpath("//span[text()='" + restaurantType + "']")).click();
		/*
		 * WebActionUtil.isElementVisible(tabRestaurant, "Restaurant Tab");
		 * WebActionUtil.waitForElement(tabRestaurant, "Restaurant Tab", 60);
		 * WebActionUtil.clickOnElementUsingJS(tabRestaurant, "Restaurant Tab");
		 */
	}

	/**
	 * Description Method to click on View All Categories Link
	 * 
	 * @author Sajal jain
	 * 
	 */
	public void clkViewAllCategoriesLnk() {
		WebActionUtil.isElementVisible(lnkViewAllCategories, "View All Categories Link");
		WebActionUtil.waitForElement(lnkViewAllCategories, "View All Categories Link", 45);
		WebActionUtil.clickOnElementUsingJS(lnkViewAllCategories, "View All Categories Link");
	}

	/**
	 * Description Method to click on Down Arrow Link
	 * 
	 * @author Sajal jain
	 * 
	 */
	public void clkDownArrowLnk() {
		WebActionUtil.isElementVisible(lnkDownArrow, "Down Arrow Link");
		WebActionUtil.waitForElement(lnkDownArrow, "Down Arrow Link", 45);
		WebActionUtil.clickOnElementUsingJS(lnkDownArrow, "Down Arrow Link");
	}

	/**
	 * Description Method to click on Add To Compare Tab
	 * 
	 * @author Sajal jain
	 * 
	 */
	public void clkAddToCompareTab() {
		WebActionUtil.isElementVisible(tabAddToCompare, "Add To Compare Tab");
		WebActionUtil.waitForElement(tabAddToCompare, "Add To Compare Tab", 45);
		WebActionUtil.clickOnElementUsingJS(tabAddToCompare, "Add To Compare Tab");
	}

	/**
	 * Description Method to click on Clear Filter Link
	 * 
	 * @author Sajal jain
	 * 
	 */
	public void clkClearFilterLnk() {
		WebActionUtil.scrollToElement(lnkCLearFilter, "Clear Filter Link");
		WebActionUtil.isElementVisible(lnkCLearFilter, "Clear Filter Link");
		WebActionUtil.waitForElement(lnkCLearFilter, "Clear Filter Link", 45);
		WebActionUtil.clickOnElementUsingJS(lnkCLearFilter, "Clear Filter Link");
	}

	/**
	 * Description Method to click on Type Filter Check Box
	 * 
	 * @author Sajal jain
	 * @param type
	 */
	public void clkTypeFilterCb(String type) {
		WebActionUtil.scrollToElement(driver.findElement(By.xpath("//input[@value='" + type + "']")),
				"Color Filter Check Box");
		WebElement cbTypeFilter = driver.findElement(By.xpath("//input[@value='" + type + "']"));
		WebActionUtil.isElementVisible(cbTypeFilter, "Type Filter Check Box");
		WebActionUtil.waitForElement(cbTypeFilter, "Type Filter Check Box", 45);
		WebActionUtil.clickOnElementUsingJS(cbTypeFilter, "Type Filter Check Box");
	}

	/**
	 * Description Method to click on Brand Name Filter Check Box
	 * 
	 * @author Sajal jain
	 * @param brandName
	 */
	public void clkBrandNameFilterCb(String brandName) {
		WebActionUtil.scrollToElement(driver.findElement(By.xpath("//input[@value='" + brandName + "']")),
				"Brand Name Filter Check Box");
		WebElement cbBrandNameFilter = driver.findElement(By.xpath("//input[@value='" + brandName + "']"));
		WebActionUtil.isElementVisible(cbBrandNameFilter, "Brand Name Filter Check Box");
		WebActionUtil.waitForElement(cbBrandNameFilter, "Brand Name Filter Check Box", 45);
		WebActionUtil.clickOnElementUsingJS(cbBrandNameFilter, "Brand Name Filter Check Box");
	}

	/**
	 * Description Method to click on Fuel Type Filter Check Box
	 * 
	 * @author Sajal jain
	 * @param fuelType
	 */
	public void clkFuelTypeFilterCb(String fuelType) {
		WebActionUtil.scrollToElement(driver.findElement(By.xpath("//input[@value='" + fuelType + "']")),
				"Color Filter Check Box");
		WebElement cbFuelTypeFilter = driver.findElement(By.xpath("//input[@value='" + fuelType + "']"));
		WebActionUtil.isElementVisible(cbFuelTypeFilter, "Fuel Type Filter Check Box");
		WebActionUtil.waitForElement(cbFuelTypeFilter, "Fuel Type Filter Check Box", 45);
		WebActionUtil.clickOnElementUsingJS(cbFuelTypeFilter, "Fuel Type Filter Check Box");
	}

	/**
	 * Description Method to click on Vehicle Variant Filter Check Box
	 * 
	 * @author Sajal jain
	 * @param vehicleVariant
	 */
	public void clkVehicleVariantFilterCb(String vehicleVariant) {
		WebActionUtil.scrollToElement(driver.findElement(By.xpath("//input[@value='" + vehicleVariant + "']")),
				"Color Filter Check Box");
		WebElement cbVehicleVariantFilter = driver.findElement(By.xpath("//input[@value='" + vehicleVariant + "']"));
		WebActionUtil.isElementVisible(cbVehicleVariantFilter, "Vehicle Variant Filter Check Box");
		WebActionUtil.waitForElement(cbVehicleVariantFilter, "Vehicle Variant Filter Check Box", 45);
		WebActionUtil.clickOnElementUsingJS(cbVehicleVariantFilter, "Vehicle Variant Filter Check Box");
	}

	/**
	 * Description Method to fetch Price
	 * 
	 * @author Sajal jain
	 * @param company
	 */
	public void fetchPrice(String company) {
		WebElement tabPrice = driver.findElement(By.xpath("//span[@seller='" + company + "']"));
		WebActionUtil.isElementVisible(tabPrice, "Price Tab");
		WebActionUtil.waitForElement(tabPrice, "Price Tab", 45);
		String price = tabPrice.getText();
		System.out.println("Price : " + price);
	}

	/**
	 * Description Method to click on Popular Areas Link
	 * 
	 * @author Sajal jain
	 * @param popularAreas
	 */
	public void clkPopularAreasLnk(String popularAreas) {
		WebElement lnkPopularAreas = driver.findElement(By.xpath("//a[contains(text(),'" + popularAreas + "')]"));
		WebActionUtil.scrollToElement(lnkPopularAreas, "Popular Areas Link");
		WebActionUtil.isElementVisible(lnkPopularAreas, "Popular Areas Link");
		WebActionUtil.waitForElement(lnkPopularAreas, "Popular Areas Link", 45);
		WebActionUtil.clickOnElementUsingJS(lnkPopularAreas, "Popular Areas Link");
	}

	/**
	 * Description Method to select Distance Drop down
	 * 
	 * @author Sajal jain
	 * @param distance
	 */
	public void selectDistanceDD(String distance) {
		WebActionUtil.waitForElement(ddPrice, "Distance Drop down", 45);
		WebActionUtil.clickOnWebElement(ddPrice, "Distance Drop down", "Unable to click on Distance Drop down");
		WebElement otnDistance = driver.findElement(By.xpath("//a[text()='" + distance + "']"));
		WebActionUtil.isElementVisible(otnDistance, "Select Distance Drop down");
		WebActionUtil.isElementClickable(otnDistance, "Select Distance Drop down");
		WebActionUtil.waitForElement(otnDistance, "Select Distance Drop down", 45);
		WebActionUtil.clickOnElementUsingJS(otnDistance, "Select Distance Drop down");
	}

	/**
	 * Description Method to click on Close Tab
	 * 
	 * @author Sajal jain
	 */
	public void clkCloseTab() {
		WebActionUtil.isElementVisible(tabClose, "Close Tab");
		WebActionUtil.waitForElement(tabClose, "Close Tab", 45);
		WebActionUtil.clickOnElementUsingJS(tabClose, "Close Tab");
	}

	/**
	 * Description Method to Enter in Your Mobile Number Text Box
	 * 
	 * @author Sajal jain
	 * @param yourMobileNumber
	 */
	public void setYourMobileNumber(String yourMobileNumber) {
		WebActionUtil.waitForElement(txtYourMobileNumber, "Your Mobile Number Text Box", 25);
		WebActionUtil.typeText(txtYourMobileNumber, yourMobileNumber, "Your Mobile Number Text Box");
	}

	/**
	 * Description Method to Enter in Your Name Text Box
	 * 
	 * @author Sajal jain
	 * @param yourName
	 */
	public void setYourName(String yourName) {
		WebActionUtil.waitForElement(txtYourName, "Your Name Text Box", 25);
		WebActionUtil.typeText(txtYourName, yourName, "Your Name Text Box");
	}

	/**
	 * Description Method to fetch Rating
	 * 
	 * @author Sajal jain
	 * 
	 */
	public void fetchRating() {
		WebActionUtil.isElementVisible(tabRating, "Rating Tab");
		WebActionUtil.waitForElement(tabRating, "Rating Tab", 45);
		String rating = tabRating.getText();
		System.out.println("Rating : " + rating);
	}

	/**
	 * Description Method to click on Product Link
	 * 
	 * @author Sajal jain
	 * @param productName
	 */
	public void clkProductName(String productName) {
		WebActionUtil.isElementVisible(driver.findElement(By.xpath("//span[contains(text(),'" + productName + "')]")),
				"Product Link");
		WebElement lnkProductName = driver.findElement(By.xpath("//span[contains(text(),'" + productName + "')]"));
		WebActionUtil.waitForElement(lnkProductName, "Product Link", 45);
		WebActionUtil.clickOnElementUsingJS(lnkProductName, "Product Link");
	}

	/**
	 * Description Method to click on Price Low To High Tab
	 * 
	 * @author Sajal jain
	 * 
	 */
	public void clkPriceLowToHighTab() {
		WebActionUtil.scrollToElement(tabPriceLowToHigh, "Price Low To High Tab");
		WebActionUtil.isElementVisible(tabPriceLowToHigh, "Price Low To High Tab");
		WebActionUtil.waitForElement(tabPriceLowToHigh, "Price Low To High Tab", 45);
		WebActionUtil.clickOnElementUsingJS(tabPriceLowToHigh, "Price Low To High Tab");
	}

	/**
	 * Description Method to click on Color Filter Check Box
	 * 
	 * @author Sajal jain
	 * @param color
	 */
	public void clkColorFilterCb(String color) {
		WebActionUtil.scrollToElement(driver.findElement(By.xpath("//input[@value='" + color + "']")),
				"Color Filter Check Box");
		WebElement cbColorFilter = driver.findElement(By.xpath("//input[@value='" + color + "']"));
		WebActionUtil.isElementVisible(cbColorFilter, "Color Filter Check Box");
		WebActionUtil.waitForElement(cbColorFilter, "Color Filter Check Box", 45);
		WebActionUtil.clickOnElementUsingJS(cbColorFilter, "Color Filter Check Box");
	}

	/**
	 * Description Method to click on Vehicle Model
	 * 
	 * @author Sajal jain
	 * @param vehicleModel
	 */
	public void clkVehicleModelCb(String vehicleModel) {
		WebElement cbVehicleModel = driver.findElement(By.xpath("//input[@value='" + vehicleModel + "']"));
		WebActionUtil.isElementVisible(cbVehicleModel, "Vehicle Model CheckBox");
		WebActionUtil.waitForElement(cbVehicleModel, "Vehicle Model CheckBox", 45);
		WebActionUtil.clickOnElementUsingJS(cbVehicleModel, "Vehicle Model CheckBox");
	}

	/**
	 * Description Method to click on Price Filter CheckBox
	 * 
	 * @author Sajal jain
	 * @param priceFilter
	 */
	public void clkPriceFilterCb(String priceFilter) {
		WebElement cbPriceFilter = driver.findElement(By.xpath("//input[@value='" + priceFilter + "']"));
		WebActionUtil.isElementVisible(cbPriceFilter, "Price Filter CheckBox");
		WebActionUtil.waitForElement(cbPriceFilter, "Price Filter CheckBox", 45);
		WebActionUtil.clickOnElementUsingJS(cbPriceFilter, "Price Filter CheckBox");
	}

	/**
	 * Description Method to click on Category Link
	 * 
	 * @author Sajal jain
	 * @param category
	 */
	public void clkCategorylnk(String category) {
		WebElement lnkCategory = driver.findElement(By.xpath("//span[text()='" + category + "']"));
		WebActionUtil.isElementVisible(lnkCategory, "Category Link");
		WebActionUtil.waitForElement(lnkCategory, "Category Link", 45);
		WebActionUtil.clickOnElementUsingJS(lnkCategory, "Category Link");
	}

	/**
	 * Description Method to Enter in Search Text Box
	 * 
	 * @author Sajal jain
	 * @param search
	 */
	public void setProjectName(String search) {
		WebActionUtil.waitForElement(txtSearchBox, "Search Text Box", 25);
		WebActionUtil.typeText(txtSearchBox, search, "Search Text Box");
	}

	/**
	 * Description Method to validate Location
	 * 
	 * @author Sajal jain
	 * @param expectedLocation
	 */
	public void validateLocation(String expectedLocation) {
		WebElement itemName = driver.findElement(
				By.xpath("//div[text()='Signellent Technologies INDIA Pvt Ltd']/following-sibling::div[1]"));
		String actualLocation = itemName.getText();
		try {
			Assert.assertTrue(actualLocation.contains(expectedLocation));
			System.out.println("Location is same");
			WebActionUtil.pass("Location is same");
		} catch (Exception e) {
			WebActionUtil.fail("Location is not same");
			System.out.println("Location is not same");
		}
	}

	/**
	 * Description Method to click Product Type Link
	 * 
	 * @author Sajal jain
	 * @param productType
	 */
	public void clkProductTypeLnk(String productType) {
		WebElement lnkProductType = driver.findElement(By.xpath("//span[text()='" + productType + "']"));
		WebActionUtil.isElementVisible(lnkProductType, "Product Type Link");
		WebActionUtil.waitForElement(lnkProductType, "Product Type Link", 45);
		WebActionUtil.clickOnElementUsingJS(lnkProductType, "Product Type Link");
	}

	/**
	 * Description Method to click on Popular Categories Link
	 * 
	 * @author Sajal jain
	 * @param popularCategories
	 */
	public void clkPopularCategorieslnk(String popularCategories) {
		WebElement lnkProductType = driver.findElement(By.xpath("//span[text()='" + popularCategories + "']"));
		WebActionUtil.isElementVisible(lnkProductType, "Product Type Link");
		WebActionUtil.waitForElement(lnkProductType, "Product Type Link", 45);
		WebActionUtil.clickOnElementUsingJS(lnkProductType, "Product Type Link");
	}

	/**
	 * Description Method to click Product Link
	 * 
	 * @author Sajal jain
	 * @param product
	 */
	public void clkProductLnk(String product) {
		WebElement lnkProduct = driver.findElement(By.xpath("//div[text()='" + product + "']"));
		if (!WebActionUtil.isElementVisible(lnkProduct, "Product Link")) {
			driver.navigate().refresh();
		}
		WebActionUtil.waitForElement(lnkProduct, "Product Link", 45);
		WebActionUtil.clickOnElementUsingJS(lnkProduct, "Product Link");
	}

	/**
	 * Description Method to click Next Link
	 * 
	 * @author Sajal jain
	 *
	 */
	public void clkNextLnk() {
		WebActionUtil.waitForElement(lnkNext, "Next Link", 45);
		WebActionUtil.clickOnElementUsingJS(lnkNext, "Next Link");
	}

	/**
	 * Description Method to select Price Drop down
	 * 
	 * @author Sajal jain
	 * 
	 */
	public void selectPriceDD() {
		WebActionUtil.waitForElement(ddPrice, "Price Drop down", 45);
		WebActionUtil.clickOnWebElement(ddPrice, "Price Drop down", "Unable to click on Price Drop down");
		WebActionUtil.isElementVisible(cbWithPrice, "With Price Check Box");
		WebActionUtil.isElementClickable(cbWithPrice, "With Price Check Box");
		WebActionUtil.waitForElement(cbWithPrice, "With Price Check Box", 45);
		WebActionUtil.clickOnElementUsingJS(cbWithPrice, "With Price Check Box");
	}

	/**
	 * Description Method to select Business Types Drop down
	 * 
	 * @author Sajal jain
	 * @param businessType
	 */
	public void selectBusinessTypeDD(String businessType) {
		WebActionUtil.waitForElement(ddBusinessTypes, "Business Types Drop down", 45);
		WebActionUtil.clickOnWebElement(ddBusinessTypes, "Business Types Drop down",
				"Unable to click on Business Types Drop down");
		WebElement otnBusinessType = driver
				.findElement(By.xpath("//span[text()='" + businessType + "']/preceding-sibling::span"));
		WebActionUtil.isElementVisible(otnBusinessType, "Select Business Types Drop down");
		WebActionUtil.isElementClickable(otnBusinessType, "Select Business Types Drop down");
		WebActionUtil.waitForElement(otnBusinessType, "Select Business Types Drop down", 45);
		WebActionUtil.clickOnElementUsingJS(otnBusinessType, "Select Business Types Drop down");
	}

	/**
	 * Description Method to select Location AllIndia Drop down
	 * 
	 * @author Sajal jain
	 * @param location
	 */
	public void selectAllIndiaDD(String location) {
		WebActionUtil.waitForElement(ddAllIndia, "AllIndia Drop down", 45);
		WebActionUtil.clickOnWebElement(ddAllIndia, "AllIndia Drop down", "Unable to click on AllIndia Drop down");
		WebActionUtil.clearText(txtEnterCityName, "Enter City Name Text Box");
		WebActionUtil.typeText(txtEnterCityName, location, "Enter City Name Text Box");
		// WebActionUtil.clickOnWebElement(ddTypeOfWork, "Location Drop down", "Unable
		// to click on Location Drop down");
		WebElement otnLocation = driver.findElement(By.xpath("//b[text()='" + location + "']"));
		WebActionUtil.isElementVisible(otnLocation, "Select AllIndia Drop down");
		/*
		 * WebActionUtil.isElementClickable(otnLocation, "Select AllIndia Drop down");
		 * WebActionUtil.waitForElement(otnLocation, "Select AllIndia Drop down", 45);
		 */ // WebActionUtil.clickOnElementUsingJS(otnLocation, "Select AllIndia Drop
			// down");
		WebActionUtil.clickOnWebElement(otnLocation, "Select AllIndia Drop down",
				"Unable to click on Location Drop down");
	}

	/**
	 * Description Method to click AP Antenna Tab
	 * 
	 * @author Sajal jain
	 * @param product1
	 */
	public void clkAPAntennaTab(String product1) {
		WebElement tabApAntenna = driver.findElement(By.xpath("//span[text()='" + product1 + "']"));
		WebActionUtil.waitForElement(tabApAntenna, "AP Antenna Tab", 45);
		WebActionUtil.clickOnElementUsingJS(tabApAntenna, "AP Antenna Tab");
	}

	/**
	 * Description Method to click Electronics & Electrical Tab
	 * 
	 * @author Sajal jain
	 * @param category
	 */
	public void clkElectronicsAndElectricalTab(String category) {
		WebActionUtil.isElementVisible(driver.findElement(By.xpath("//div[text()='" + category + "']")),
				"Electronics & Electrical Tab");
		WebElement tabCategory = driver.findElement(By.xpath("//div[text()='" + category + "']"));
		WebActionUtil.waitForElement(tabCategory, "Electronics & Electrical Tab", 45);
		WebActionUtil.clickOnElementUsingJS(tabCategory, "Electronics & Electrical Tab");
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

	/**
	 * Description Method to click Module Tab
	 * 
	 * @author Sajal jain
	 * @param module
	 */
	public void clkModuleTab(String module) {
		WebActionUtil.scrollToElement(driver.findElement(By.xpath("//span[text()='" + module + "']/..")), "Module Tab");
		WebElement tabModule = driver.findElement(By.xpath("//span[text()='" + module + "']/.."));
		WebActionUtil.waitForElement(tabModule, "Module Tab", 45);
		WebActionUtil.clickOnElementUsingJS(tabModule, "Module Tab");
	}

}
