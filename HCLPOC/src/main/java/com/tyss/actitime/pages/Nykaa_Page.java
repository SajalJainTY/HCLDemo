package com.tyss.actitime.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.tyss.acttime.util.WebActionUtil;

import ch.qos.logback.core.net.SyslogOutputStream;
import junit.framework.Assert;

/**
 * Description This class has the method implementations of the Nykaa Site
 * 
 * @author Sajal jain
 */
public class Nykaa_Page {
	public WebDriver driver;
	public WebActionUtil WebActionUtil;
	public long ETO = 10;

	public Nykaa_Page(WebDriver driver, long ETO, WebActionUtil WebActionUtil) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.WebActionUtil = WebActionUtil;
		this.ETO = ETO;
	}

	/* Account Tab */
	@FindBy(className = "AccountText")
	private WebElement tabAccount;
	/* Enter PhoneNumber or Email Button */
	@FindBy(css = "input[placeholder='Enter Phone Number or Email']")
	private WebElement btnEnterPhoneNumberorEmailButton;
	/* Email Id Text Box */
	@FindBy(name = "emailMobile")
	private WebElement txtEmailID;
	/* Proceed Button */
	@FindBy(xpath = "//button[@type='submit']")
	private WebElement btnProceed;
	/* Google Button */
	@FindBy(xpath = "//span[@class='new-login-button new-login-button--google']")
	private WebElement btnGoogle;
	/* Login Via Password Link */
	@FindBy(xpath = "//div[text()='Login via Password']")
	private WebElement lnkLoginViaPassword;
	/* Login Button */
	@FindBy(xpath = "//button[text()='login']")
	private WebElement btnLogin;
	/* Password Text Box */
	@FindBy(id = "secure-input")
	private WebElement txtPassword;
	/* Make up Tab */
	@FindBy(xpath = "//a[text()='makeup']")
	private WebElement tabMakeup;

	/**
	 * Description Method for mouse over to Item
	 * 
	 * @author Sajal jain
	 * @param itemName
	 */
	public synchronized void clkItem(String itemName) {
		WebElement item = driver.findElement(By.xpath("//span[contains(text(),'" + itemName + "')]"));
		WebActionUtil.waitForElement(item, "Item Image", 45);
		WebActionUtil.action.moveToElement(item).perform();
	}

	/**
	 * Description Method for mouse over to Makeup Tab and click on Product
	 * 
	 * @author Sajal jain
	 * @param productName
	 */
	public synchronized void clkMakeupTab(String productName) {
		WebActionUtil.waitForElement(tabMakeup, "Makeup Tab", 60);
		WebActionUtil.action.moveToElement(tabMakeup).perform();
		WebElement lnkProduct = driver.findElement(By.xpath("//a[text()='" + productName + "']"));
		WebActionUtil.waitForElement(lnkProduct, "Product Link", 45);
		WebActionUtil.clickOnElementUsingJS(lnkProduct, "Product Link");
		WebActionUtil.switchWindow();
	}

	/**
	 * Description Method clicks on Enter Phone Number or Email Button
	 * 
	 * @author Sajal jain
	 * 
	 */
	public synchronized void clkEnterPhoneNumberorEmailBtn() {
		WebActionUtil.waitForElement(btnEnterPhoneNumberorEmailButton, "Enter Phone Number or Email Button", 45);
		WebActionUtil.clickOnElementUsingJS(btnEnterPhoneNumberorEmailButton, "Enter Phone Number or Email Button");
	}

	/**
	 * Description Method Fetch color of wishlist Tab
	 * 
	 * @author Sajal jain
	 * @param itemName
	 */
	public synchronized void fetchColorWishListTab(String itemName) {
		WebElement tabWishList = driver.findElement(By.xpath(
				"(//span[contains(text(),'" + itemName + "')]/ancestor::a/following-sibling::div/div/div/div/div)[1]"));
		String actualColor = tabWishList.getCssValue("Color");
		System.out.println("Actual color " + actualColor);
		String expectedColor = "rgb(252, 39, 121)";
		Assert.assertEquals(expectedColor, actualColor);
		System.out.println("Color Validation pass");
	}

	/**
	 * Description Method clicks on WishList Tab
	 * 
	 * @author Sajal jain
	 * @param itemName
	 */
	public synchronized void clkWishListTab(String itemName) {
		WebElement tabWishList = driver.findElement(By.xpath(
				"(//span[contains(text(),'" + itemName + "')]/ancestor::a/following-sibling::div/div/div/div/div)[1]"));
		WebActionUtil.scrollToElement(tabWishList, "WishList Tab");
		WebActionUtil.waitForElement(tabWishList, "WishList Tab", 45);
		WebActionUtil.clickOnElementUsingJS(tabWishList, "WishList Tab");
	}

	/**
	 * Description Method enters Password
	 * 
	 * @author Sajal jain
	 * @param password
	 */
	public synchronized void setPassword(String password) {
		WebActionUtil.waitForElement(txtPassword, "Password Text Box", 30);
		WebActionUtil.typeText(txtPassword, password, "Password Text Box");
	}

	/**
	 * Description Method clicks on Login Button
	 * 
	 * @author Sajal jain
	 * 
	 */
	public synchronized void clkLoginBtn() {
		WebActionUtil.waitForElement(btnLogin, "Login Button", 45);
		WebActionUtil.clickOnElementUsingJS(btnLogin, "Login Button");
	}

	/**
	 * Description Method clicks on Login Via Password Link
	 * 
	 * @author Sajal jain
	 * 
	 */
	public synchronized void clkLoginViaPasswordLnk() {
		WebActionUtil.waitForElement(lnkLoginViaPassword, "Login Via Password Link", 45);
		WebActionUtil.clickOnElementUsingJS(lnkLoginViaPassword, "Login Via Password Link");
	}

	/**
	 * Description Method clicks on Google Button
	 * 
	 * @author Sajal jain
	 * 
	 */
	public synchronized void clkGoogleBtn() {
		WebActionUtil.waitForElement(btnGoogle, "Google Button", 45);
		WebActionUtil.clickOnElementUsingJS(btnGoogle, "Google Button");
	}

	/**
	 * Description Method clicks on Proceed Button
	 * 
	 * @author Sajal jain
	 * 
	 */
	public synchronized void clkProceedBtn() {
		WebActionUtil.waitForElement(btnProceed, "Proceed Button", 45);
		WebActionUtil.clickOnElementUsingJS(btnProceed, "Proceed Button");
	}

	/**
	 * Description Method enters Email ID
	 * 
	 * @author Sajal jain
	 * @throws InterruptedException
	 */
	public synchronized void setEmailID() throws InterruptedException {
		/*
		 * WebActionUtil.waitForElement(txtEmailID, "Email ID Text Box", 30);
		 * WebActionUtil.typeText(txtEmailID, emailID, "Email ID Text Box");
		 * driver.findElement(By.id("userName")).sendKeys(username);
		 * driver.findElement(By.id("secure-input")).sendKeys(password);
		 * Thread.sleep(2000);
		 * driver.findElement(By.xpath("//button[text()='register']")).click();
		 */

		driver.findElement(By.xpath("//span[@class='new-login-button new-login-button--google']")).click();
		driver.findElement(By.id("identifierId")).sendKeys("sajjujain1012@gmail.com");
		driver.findElement(By.xpath("//span[text()='Next']/following-sibling::div")).click();
		Thread.sleep(2000);
		driver.findElement(By.name("password")).sendKeys("Sajju@1234");
		driver.findElement(By.xpath("//span[text()='Next']/following-sibling::div")).click();
	}

	/**
	 * Description Method clicks on Account Tab
	 * 
	 * @author Sajal jain
	 * 
	 */
	public synchronized void clkAccountTab() {
		WebActionUtil.waitForElement(tabAccount, "Account Tab", 45);
		WebActionUtil.clickOnElementUsingJS(tabAccount, "Account Tab");
	}

}
