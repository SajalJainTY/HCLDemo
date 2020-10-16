package com.assignment.actitime;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.assignment.practice.BaseTest;

public class AccountCreation extends BaseTest {

	public static void main(String[] args) throws EncryptedDocumentException, IOException, InterruptedException {

		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.get("https://demo.actitime.com/");
		driver.findElement(By.id("username")).sendKeys("admin");
		driver.findElement(By.name("pwd")).sendKeys("manager");
		driver.findElement(By.xpath("//div[text()='Login ']")).click();

		driver.findElement(By.id("container_users")).click();
		driver.findElement(By.xpath("//div[contains(text(),'PTO Settings')]")).click();

		List<WebElement> totalControl = driver.findElements(By.xpath("//td[@class='ptoControlCell on']"));
		for (int i = 1; i < totalControl.size(); i++) {
			try {
				driver.findElement(By.xpath("(//td[@class='ptoControlCell on'])[" + i + "]")).click();
				waitForElementUsingLocator(By.xpath("(//td[@class='ptoControlCell on'])[" + (i + 1) + "]"));
				// Thread.sleep(2000);
			} catch (Exception e) {
				break;
			}
		}

		driver.findElement(By.xpath("//div[text()='New User']")).click();
		// waitForElementUsingLocator(By.id("createUserPanel_firstNameField"));
		Thread.sleep(5000);

		WebElement firstName = driver.findElement(By.id("createUserPanel_firstNameField"));
		waitForElement(firstName, "First Name Textbox", 30);
		firstName.sendKeys(getDataFromExcel("AccountCreation", 1, 0));
		driver.findElement(By.id("createUserPanel_lastNameField")).sendKeys(getDataFromExcel("AccountCreation", 1, 1));
		driver.findElement(By.id("createUserPanel_emailField")).sendKeys(getDataFromExcel("AccountCreation", 1, 2));

		driver.findElement(By.xpath("(//div[@class='selectorPlaceholder'])[2]")).click();
		driver.findElement(By.xpath(
				"//div[@class='item selected']/..//div[text()='" + getDataFromExcel("AccountCreation", 1, 3) + "']"))
				.click();

		driver.findElement(By.xpath("//div[text()='Save & Send Invitation']")).click();
		// waitTillPageLoad(10);
		// waitForElementUsingLocator(By.xpath("//div[@class='invitationImg']/following-sibling::div[1]"));
		Thread.sleep(5000);
		String expectedSuccessMessage1 = "Account for " + getDataFromExcel("AccountCreation", 1, 0) + " "
				+ getDataFromExcel("AccountCreation", 1, 1) + " has been created.";

		String expectedSuccessMessage2 = "The invitation has been sent to the user's email address: "
				+ getDataFromExcel("AccountCreation", 1, 2);

		String actualSuccessMessage1 = driver
				.findElement(By.xpath("//div[@class='invitationImg']/following-sibling::div[1]")).getText();
		String actualSuccessMessage2 = driver
				.findElement(By.xpath("//div[@class='invitationImg']/following-sibling::div[2]")).getText();
		System.out.println("expected sm1 " + expectedSuccessMessage1);
		System.out.println("actual sm1 " + actualSuccessMessage1);

		System.out.println("expected sm2 " + expectedSuccessMessage2);
		System.out.println("actual sm2 " + actualSuccessMessage2);

		Assert.assertEquals(actualSuccessMessage1, expectedSuccessMessage1);
		System.out.println("Account created successfully");
		Assert.assertEquals(actualSuccessMessage2, expectedSuccessMessage2);
		System.out.println("Mail sent to " + getDataFromExcel("AccountCreation", 1, 2));

		driver.findElement(By.xpath("//span[text()='Close']")).click();

		driver.close();

	}

}