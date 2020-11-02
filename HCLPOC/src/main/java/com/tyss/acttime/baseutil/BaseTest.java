package com.tyss.acttime.baseutil;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import com.aventstack.extentreports.ExtentTest;
import com.tyss.acttime.reports.ExtentHCLManager;
import com.tyss.acttime.reports.ExtentHCLTest;
import com.tyss.acttime.util.WebActionUtil;
import com.tyss.acttime.util.commonutils.ExcelUtil;
import com.tyss.acttime.util.commonutils.FileOperation;

import io.github.bonigarcia.wdm.WebDriverManager;
/***********************************************************************
 * Description : Implements Application Precondition and Postcondition.
 * 
 * @author : Shreya U ,Vivek Dogra, Aatish Slathia
 * @BeforeSuite: Creates all the folder structure for Extent Reports
 * @BeforeClass : Launches the browser according to the browser name specified.
 * @AfterClass : Closes the browser after completion of execution
 * @AfterSuite: Kills the driver (example chromedriver.exe) according to browser
 *              specified.
 */

public class BaseTest {
	
	public  WebDriver driver;
	public static Properties prop;
	public static final int ITO = 20;
	public static final int ETO = 30;
	public static String sDirPath = System.getProperty("user.dir");
	public static final String EXCELPATH = sDirPath + "./src/main/resources/data/Data.xlsx";
	public static Logger logger = LogManager.getLogger(BaseTest.class);
	public static WebActionUtil WebActionUtil;
	public String testCaseName;
	public DesiredCapabilities cap;
	public static final String LOCAL_HUB_URL = "http://localhost:4444/wd/hub";
	public static final String CONFIGPATH = sDirPath + "./conf/config.properties";
	public static final String MESSAGESPATH=sDirPath + "./src/main/resources/data/messages.properties";
	public ChromeOptions chromeOpt;
	public FirefoxOptions  firefoxOptions;
	public static String filePath=sDirPath+"./src/main/resources/data/Doc1.docx";
	
	static
	{
		try {
			prop = new Properties();
			FileInputStream fis = new FileInputStream(CONFIGPATH);
			prop.load(fis);
			FileInputStream fis1 = new FileInputStream(MESSAGESPATH);
			prop.load(fis1);
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * Description : Creates folder structure for Extent reports.
	 * 
	 * @author:Shreya U
	 */
	@BeforeSuite(alwaysRun = true,groups= {"GoibiboOneWay","GoibiboRT"})
	public synchronized void createFiles() {
		try {
			logger.info("Folder creation for Extent");
			FileOperation fileOperation = new FileOperation();
			fileOperation.CreateFiles();
		} catch (Exception e) {
			logger.info("Exception while report inititation");
		}

	}

	/**
	 * Description: Launches the browser as specified in the parameter
	 * 
	 * @author:Shreya U,Vivek Dogra
	 * @param :browserName
	 */
	@Parameters({ "browserName" })
	@BeforeClass(groups= {"GoibiboOneWay","GoibiboRT"})
	public synchronized void launchApp(String browserName) {
		ExtentTest parentExtentTest = ExtentHCLTest.createTest(getClass().getSimpleName());

		ExtentHCLManager.setParentReport(parentExtentTest);
		
			 try {
				driver =CreateDriver.getInstance().setWebDriver(browserName, cap,LOCAL_HUB_URL ,chromeOpt,firefoxOptions);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		
		driver.manage().timeouts().implicitlyWait(ITO, TimeUnit.SECONDS);
		WebActionUtil = new WebActionUtil(driver, ETO);
		driver.manage().window().maximize();
		String appurl= prop.getProperty("App_URL");
		driver.get(appurl);
	}


	/**
	 * Description: Closes the browser
	 * 
	 * @author:Shreya U
	 */
	@AfterClass(groups= {"GoibiboOneWay","GoibiboRT"})
	public synchronized void closeBrowser() {

		try {
			if (driver != null) {

				driver.quit();

			} else {
				logger.info("Unable to close the driver");
			}
		} catch (Exception e) {

		}

	}

	/**
	 * Description: Kills the driver in Task Manager as specified in the parameter.
	 * 
	 * @author:Shreya U
	 * @param :browserName
	 */
	@AfterSuite(groups= {"GoibiboOneWay","GoibiboRT"})
	@Parameters({ "browserName" })
	public synchronized void killTask(String browserName) {
		try {
			if (browserName.equalsIgnoreCase("chrome")) {
				Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
			} else if (browserName.equalsIgnoreCase("firefox")) {

				Runtime.getRuntime().exec("taskkill /IM firefox.exe /F");
			} else if (browserName.equalsIgnoreCase("edge")) {

				Runtime.getRuntime().exec("taskkill /F /IM MicrosoftEdgeCP.exe");
			} else {
				logger.info("Browser Name Not specified properly to kil the Task");
			}

		} catch (IOException e) {

		}
	}
	/**
	 * Description: Creates nodes for the test methods in Extent report.
	 * 
	 * @author:Shreya U
	 * @param: methodName
	 */
	@BeforeMethod(groups= {"GoibiboOneWay","GoibiboRT"})
	public synchronized void setExtentReport(Method methodName) {
		this.testCaseName = methodName.getName();
		ExtentTest testReport = ExtentHCLManager.getParentReport().createNode(testCaseName, "Description");
		ExtentHCLManager.setTestReport(testReport);
	}
}
