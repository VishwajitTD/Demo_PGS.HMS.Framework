package pgh.hms.generic.genericUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import hmsObjectRepository.HMS_WelcomPage;
import hmsObjectRepository.LoginModulePage;
import hmsObjectRepository.LogoutPage;

public class BaseClass {
	public static WebDriver listenerDriver;
	public WebDriver driver=null;
	// create object for Utility
	
	public FileUtility fLib = new FileUtility();
	public WebActionUtility wLib = new WebActionUtility();
	public JavaScriptUtility jsLib = new JavaScriptUtility(driver);
	public ExcelUtility eLib = new ExcelUtility();
	public DataBaseUtility dLib = new DataBaseUtility();
	public JavaUtility jLib=new JavaUtility();
	

	// get the filePath
	public String envtFilePath = fLib.getFilePathFromPropertiesFile("projectConfigDataFilePath");
	public String excelPath=fLib.getFilePathFromPropertiesFile("testDataFilePath");
	
	/**
	 * 
	 */
	@BeforeSuite(groups = {"smokeTest","regressionTest"})
	public void connectToDB() {
		dLib.connectDB();
		Reporter.log("Data base is connected",true);
	}

	/**
	 * Open the Browser
	 */
	@BeforeClass(groups = {"smokeTest","regressionTest"})
//	@Parameters("browser")
	public void setUpBrowser(/*String browser*/) {
	String browser = fLib.getDataFromProperties(envtFilePath, "browser");
	//  String browser=System.getProperty("BROWSER");
	  //String url=System.getProperty("URL");
	    
	    switch (browser.toLowerCase()) {
	        case "chrome":
				driver = new ChromeDriver();
				listenerDriver = driver;
				Reporter.log("Chrome is connected", true);
				break;
			case "firefox":
				driver = new FirefoxDriver();
				listenerDriver = driver;
				Reporter.log("Firefox is connected", true);
				break;
			default:
				driver = new ChromeDriver();
				listenerDriver = driver;
				Reporter.log("Default Chrome is connected", true);
			}

			driver.manage().window().maximize();
			wLib.waitForElementInDOM(driver);

			String url = fLib.getDataFromProperties(envtFilePath, "url");
			
			// "Open the browser and enter the test URL"

			driver.get(url);
		}

	
/**
 * Here, login to Applicationas as Patient
 */
	@BeforeMethod(groups = {"smokeTest","regressionTest"},enabled = false)
	public void loginToApplicationasAsPatient() {
		
	
		String patUsername = fLib.getDataFromProperties(envtFilePath, "patUsername");
		String patPassword = fLib.getDataFromProperties(envtFilePath, "patPassword");
		// click for admin module
		HMS_WelcomPage welcom = new HMS_WelcomPage(driver);
		welcom.patientLogin();

		// Login as Admin with the valid user name and password
		
		LoginModulePage choose = new LoginModulePage(driver);
		choose.loginToAdminModule(patUsername, patPassword);
		Reporter.log("Data base is connected",true);

	}

/**
 * Here we are login to Applicationas as Doctor
 */
	@BeforeMethod(groups = {"smokeTest","regressionTest"},enabled = false)
	public void loginToApplicationasAsDoctor() {
		
		excelPath = fLib.getFilePathFromPropertiesFile("testDataFilePath");
		
		String url = fLib.getDataFromProperties(envtFilePath, "url");
		String docUsername = fLib.getDataFromProperties(envtFilePath, "docUsername");
		String docPassword = fLib.getDataFromProperties(envtFilePath, "docPassword");
		
		// "Open the browser and enter the test URL"
		
		driver.get(url);
		// click for admin module
		HMS_WelcomPage welcom = new HMS_WelcomPage(driver);
		welcom.doctorLogin();

		// Login as Admin with the valid user name and password
		
		LoginModulePage choose = new LoginModulePage(driver);
		choose.loginToAdminModule(docUsername, docPassword);
		Reporter.log("Logged in as Doctor.....",true);
	}


	/**
	 * Login From the Application
	 */
	@BeforeMethod(groups = {"smokeTest","regressionTest"},enabled = true)
	public void loginToApplicationAsAdmin() {
		
		excelPath = fLib.getFilePathFromPropertiesFile("testDataFilePath");
		
		String url = fLib.getDataFromProperties(envtFilePath, "url");
		String adminUsername = fLib.getDataFromProperties(envtFilePath, "adminUsername");
		String adminPassword = fLib.getDataFromProperties(envtFilePath, "adminPassword");
		
		// "Open the browser and enter the test URL"
		
		driver.get(url);
		// click for admin module
		HMS_WelcomPage welcom = new HMS_WelcomPage(driver);
		welcom.adminLogin();

		// Login as Admin with the valid user name and password
		
		LoginModulePage choose = new LoginModulePage(driver);
		choose.loginToAdminModule(adminUsername, adminPassword);
		Reporter.log("Logged in as Admin.....",true);
	}

	/**
	 * Logout From the Application
	 */
	@AfterMethod(groups = {"smokeTest","regressionTest"})
	public void logoutToApplication() {
		
		
		
		// Create an instance of LogoutPage
		LogoutPage logoutPage = new LogoutPage(driver);
		
		// Perform logout
				logoutPage.performLogout();
				
				Reporter.log("Loggiing out ....",true);

	}

	/**
	 * Close the browser
	 */
	@AfterClass(groups = {"smokeTest","regressionTest"},alwaysRun = true)
	public void closeBrowser() {
		if(driver!=null) {
			driver.quit();

			Reporter.log("Browser is closing...",true);
		}
	}
/**
 * Close the Data Base
 */
	@AfterSuite(groups = {"smokeTest","regressionTest"},alwaysRun = true)
	public void closeDB() {
		dLib.closeDB();
		Reporter.log("Data base is closing...",true);
	}
}
