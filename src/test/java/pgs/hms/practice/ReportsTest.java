package pgs.hms.practice;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class ReportsTest{

	@Test
	
	public void reportsTest() throws IOException, AWTException, InterruptedException {
		// get the java representation object of the Physical file
		FileInputStream fis = new FileInputStream("C:\\Users\\vishw\\OneDrive\\Desktop\\TY\\commonData.properties");
		Properties pObj = new Properties();
		pObj.load(fis);
		String url = System.getProperty("url",pObj.getProperty("url"));
//		String docUsernameP = System.getProperty("docUsername",pObj.getProperty("docUsername"));
//		String docPasswordP = System.getProperty("docPassword",pObj.getProperty("docPassword"));
//		String patUsernameP = System.getProperty("patUsername",pObj.getProperty("patUsername"));
//		String patPasswordP = System.getProperty("patPassword",pObj.getProperty("patPassword"));
//	
		String admUsernameP = System.getProperty("adminUsername",pObj.getProperty("adminUsername"));
		String admPasswordP = System.getProperty("adminPassword",pObj.getProperty("adminPassword"));
	
		String browser = System.getProperty("browser",pObj.getProperty("browser"));


		// String patUsernameP=pObj.getProperty("patUsername");
		// String patPasswordP=pObj.getProperty("patPassword");

		// Generate Random Number
		//int random = new Random().nextInt(3000);

		// launch the Browser [CTP]
		WebDriver driver;
		if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();

		} else if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else {
			driver = new ChromeDriver();

		}

		// launch the Browser
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// "Open the browser and enter the test URL"

		driver.get(url);

		WebElement admin = driver.findElement(By.xpath("//a[@href='hms/admin']"));
		admin.click();

		// Login as Admin with the valid user name and password
		WebElement admUsername = driver.findElement(By.xpath("//input[@name='username']"));
		admUsername.sendKeys(admUsernameP);

		WebElement admPassword = driver.findElement(By.xpath("//input[@name='password']"));
		admPassword.sendKeys(admPasswordP);
		
		WebElement admSubmit = driver.findElement(By.xpath("//button[@name='submit']"));
		admSubmit.click();
		
		//Select the report section
		WebElement report = driver.findElement(By.xpath("//span[.=' Reports ']"));
		report.click();
		WebElement bwDatesReports = driver.findElement(By.xpath("//span[.='B/w dates reports ']"));
		bwDatesReports.click();
		WebElement fromDate = driver.findElement(By.id("fromdate"));
		fromDate.click();
		
		//Enter the formDate
		Robot robot=new Robot();
		robot.keyPress(KeyEvent.VK_0);
		robot.keyRelease(KeyEvent.VK_0);
		robot.keyPress(KeyEvent.VK_1);
		robot.keyRelease(KeyEvent.VK_1);
		robot.keyPress(KeyEvent.VK_0);
		robot.keyRelease(KeyEvent.VK_0);
		robot.keyPress(KeyEvent.VK_1);
		robot.keyRelease(KeyEvent.VK_1);
		robot.keyPress(KeyEvent.VK_2);
		robot.keyRelease(KeyEvent.VK_2);
		robot.keyPress(KeyEvent.VK_0);
		robot.keyRelease(KeyEvent.VK_0);
		robot.keyPress(KeyEvent.VK_2);
		robot.keyRelease(KeyEvent.VK_2);
		robot.keyPress(KeyEvent.VK_0);
		robot.keyRelease(KeyEvent.VK_0);
		
		//Enter the toDate
		
		WebElement toDate = driver.findElement(By.id("todate"));
		toDate.click();
		robot.keyPress(KeyEvent.VK_2);
		robot.keyRelease(KeyEvent.VK_2);
		robot.keyPress(KeyEvent.VK_9);
		robot.keyRelease(KeyEvent.VK_9);
		robot.keyPress(KeyEvent.VK_0);
		robot.keyRelease(KeyEvent.VK_0);
		robot.keyPress(KeyEvent.VK_6);
		robot.keyRelease(KeyEvent.VK_6);
		robot.keyPress(KeyEvent.VK_2);
		robot.keyRelease(KeyEvent.VK_2);
		robot.keyPress(KeyEvent.VK_0);
		robot.keyRelease(KeyEvent.VK_0);
		Thread.sleep(2000);
		robot.keyPress(KeyEvent.VK_2);
		Thread.sleep(2000);
		robot.keyRelease(KeyEvent.VK_2);
		Thread.sleep(2000);
		robot.keyPress(KeyEvent.VK_3);
		Thread.sleep(2000);
		robot.keyRelease(KeyEvent.VK_3);
		
		WebElement  bwDatesReportSubmit= driver.findElement(By.id("submit"));
		bwDatesReportSubmit.click();
		
		WebElement  actualResult= driver.findElement(By.xpath("//h5[@align='center']"));
		String actual = actualResult.getText();
		String expected = "Report from 2020-01-01 to 2030-06-29";
		//Verify the report
		if (actual.contains(expected)) {
			System.out.println("Reports are present --->PASSED");
			
		}
		else {
			System.out.println("Reports are NOT present --->Failed");
			
		}
		
		driver.close();
		
		
		
		
		
		
		
		
		
		
		

	}

}

/*
 * package pgs.hms.practice;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class ReportsTest {

	@Test
	public void reportsTest() throws IOException, AWTException {
		// Read the test data and configuration from the properties file
		FileInputStream fis = new FileInputStream("C:\\Users\\vishw\\OneDrive\\Desktop\\TY\\commonData.properties");
		Properties pObj = new Properties();
		pObj.load(fis);
		String url = System.getProperty("url", pObj.getProperty("url"));
		String admUsernameP = System.getProperty("adminUsername", pObj.getProperty("adminUsername"));
		String admPasswordP = System.getProperty("adminPassword", pObj.getProperty("adminPassword"));
		String browser = System.getProperty("browser", pObj.getProperty("browser"));

		// Initialize the WebDriver based on the specified browser
		WebDriver driver;
		if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else {
			driver = new ChromeDriver();
		}

		// Launch the Browser
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// Open the test URL
		driver.get(url);

		// Click on the "Admin" section
		WebElement admin = driver.findElement(By.xpath("//a[@href='hms/admin']"));
		admin.click();

		// Login as Admin with the valid username and password
		WebElement admUsername = driver.findElement(By.xpath("//input[@name='username']"));
		admUsername.sendKeys(admUsernameP);

		WebElement admPassword = driver.findElement(By.xpath("//input[@name='password']"));
		admPassword.sendKeys(admPasswordP);

		WebElement admSubmit = driver.findElement(By.xpath("//button[@name='submit']"));
		admSubmit.click();

		// Select the "Reports" section
		WebElement report = driver.findElement(By.xpath("//span[.=' Reports ']"));
		report.click();

		// Select the "B/w dates reports" option
		WebElement bwDatesReports = driver.findElement(By.xpath("//span[.='B/w dates reports ']"));
		bwDatesReports.click();

		// Enter the fromDate
		WebElement fromDate = driver.findElement(By.id("fromdate"));
		fromDate.click();
		enterDate("2020-01-01");

		// Enter the toDate
		WebElement toDate = driver.findElement(By.id("todate"));
		toDate.click();
		enterDate("2030-06-29");

		// Submit the report form
		WebElement bwDatesReportSubmit = driver.findElement(By.id("submit"));
		bwDatesReportSubmit.click();

		// Verify the generated report
		WebElement actualResult = driver.findElement(By.xpath("//h5[@align='center']"));
		String actual = actualResult.getText();
		String expected = "Report from 2020-01-01 to 2030-06-29";
		if (actual.contains(expected)) {
			System.out.println("Reports are present --->PASSED");
		} else {
			System.out.println("Reports are NOT present --->Failed");
		}

		// Close the browser
		driver.close();
	}

	// Helper method to enter
*/
