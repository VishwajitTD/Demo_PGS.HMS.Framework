package pgs.hms.practice;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Manage_Patient_HistoryTest {

	@Test
	public void manage_Patient_HistoryTest() throws IOException, ParseException {

		// get the java representation object of the Physical file
		FileInputStream fis = new FileInputStream("C:\\Users\\vishw\\OneDrive\\Desktop\\TY\\commonData.properties");
		Properties pObj = new Properties();
		pObj.load(fis);
		// String url = System.getProperty("url",pObj.getProperty("url"));
		String docUsernameP = System.getProperty("docUsername", pObj.getProperty("docUsername"));
		String docPasswordP = System.getProperty("docPassword", pObj.getProperty("docPassword"));
		String browser = System.getProperty("browser", pObj.getProperty("browser"));

		// String patUsernameP=pObj.getProperty("patUsername");
		// String patPasswordP=pObj.getProperty("patPassword");

		// JSON FIle reader

		JSONParser jsonP = new JSONParser();
		Object obj = jsonP.parse(new FileReader("C:\\Users\\vishw\\OneDrive\\Desktop\\TY\\JSONcommonData.json"));
		JSONObject map = (JSONObject) obj;
		String url = (String) map.get("url");
		System.out.println(url);

		// Generate Random Number
		int random = new Random().nextInt(3000);

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
		// chose the doctor login
		WebElement doctor = driver.findElement(By.xpath("//a[@href='hms/doctor/'and text()='Click Here']"));
		doctor.click();

		// Login as doctor with the valid user name and password
		WebElement docUsername = driver.findElement(By.xpath("//input[@name='username']"));
		docUsername.sendKeys(docUsernameP);

		WebElement docPassword = driver.findElement(By.xpath("//input[@name='password']"));
		docPassword.sendKeys(docPasswordP);

		WebElement docSubmit = driver.findElement(By.xpath("//button[@name='submit']"));
		docSubmit.click();

		// Click on patient drop down and then click on the add patient
		WebElement docPatients = driver.findElement(By.xpath("//i[@class='icon-arrow']"));
		docPatients.click();

		driver.findElement(By.xpath("//span[.=' Add Patient']")).click();

		// entering the patient name
		String expected = "Sidhant"+ " " + random;

		WebElement patName = driver.findElement(By.xpath("//input[@name='patname']"));
		patName.sendKeys(expected);

		// entering Patient Contact no
		WebElement patcontact = driver.findElement(By.xpath("//input[@name='patcontact']"));
		patcontact.sendKeys("9999955555");

		// entering Patient Email
		WebElement patemail = driver.findElement(By.xpath("//input[@name='patemail']"));
		patemail.sendKeys("Siddhant345@gmail.com" + random);

		// selecting the Gender as a male
		WebElement male = driver.findElement(By.xpath("//label[@for='rg-male']"));
		male.click();

		// entering Patient address
		WebElement pataddress = driver.findElement(By.xpath("//textarea[@name='pataddress']"));
		pataddress.sendKeys("BTM, Bangalore" + random);

		// entering Patient email
		WebElement patage = driver.findElement(By.xpath("//input[@name='patage']"));
		patage.sendKeys("69" + random);

		//// entering Patient medical History
		WebElement medhis = driver.findElement(By.xpath("//textarea[@name='medhis']"));
		medhis.sendKeys("sugar, BP, heart problem" + random);

		// submitting form
		WebElement submit = driver.findElement(By.id("submit"));
		submit.click();

//		driver.navigate().refresh();
//		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
//		wait.until(ExpectedConditions.elementToBeClickable(patients));

		WebElement patientsStale = driver.findElement(By.xpath("//div[@class='item-inner']/i[@class='icon-arrow']"));

		patientsStale.click();

		WebElement managePat = driver.findElement(By.xpath("//a[@href='manage-patient.php']"));
		managePat.click();

		// view new added patient
		WebElement viewUpadatedPat = driver.findElement(By.xpath("(//i[@class='fa fa-eye'])[last()]"));
		viewUpadatedPat.click();

		// add Medical History
		WebElement addMedHistory = driver
				.findElement(By.xpath("//button[@class='btn btn-primary waves-effect waves-light w-lg']"));
		addMedHistory.click();

		// fill all the medical history

		WebElement bloodPressure = driver.findElement(By.xpath("//input[@name='bp']"));
		bloodPressure.sendKeys("BP is 120" + random);

		WebElement bloodSugar = driver.findElement(By.xpath("//input[@name='bs']"));
		bloodSugar.sendKeys("sugar, BP, heart problem" + random);

		WebElement weight = driver.findElement(By.xpath("//input[@name='weight']"));
		weight.sendKeys("1" + random);

		WebElement temp = driver.findElement(By.xpath("//input[@name='temp']"));
		temp.sendKeys("98 degree celsius" + random);

		WebElement prescription = driver.findElement(By.xpath("//textarea[@name='pres']"));
		prescription.sendKeys("dolo 65, sleep, drink more water" + random);

		WebElement medHisSubmit = driver.findElement(By.xpath("//button[@name='submit']"));
		medHisSubmit.click();

		Alert a = driver.switchTo().alert();
		a.accept();

		// profile
		WebElement profile = driver.findElement(By.xpath("//span[@class='username']"));
		profile.click();
		String actual = driver.findElement(By.xpath("//tbody/tr[last()]/td[2]")).getText();
		System.out.println(actual);

//		Date date = new Date();
//		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
//		String expected=sdf.format(date);
//		System.out.println(expected);
		
		
		
		
//  String time=data[3];
//  System.out.println(date);
//	      String year=data[0];
//	      String month=data[2];
//	   
//	      String format=year+","+month;
//	      System.out.println(format);

//		Calendar cal=Calendar.getInstance();
//		Date currentdate=cal.getTime();
//		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
//		String expected=sdf.format(currentdate);
//		 System.out.println(expected);

		// Verify the appointment is there
		if (actual.contains(expected)) {
			System.out.println("Patient is create  ---> PASS");
		} else {
			System.out.println("Patient is not created ---> FAIL");
		}

		// logout
		WebElement logout = driver.findElement(By.xpath("//a[@href='logout.php']"));
		logout.click();

		driver.close();

	}

}

/*
 * package pgs.hms.practice;
 * 
 * import java.awt.AWTException; import java.awt.Robot; import
 * java.awt.event.KeyEvent; import java.io.FileInputStream; import
 * java.io.IOException; import java.time.Duration; import java.util.Properties;
 * 
 * import org.openqa.selenium.By; import org.openqa.selenium.WebDriver; import
 * org.openqa.selenium.WebElement; import
 * org.openqa.selenium.chrome.ChromeDriver; import
 * org.openqa.selenium.firefox.FirefoxDriver; import
 * org.testng.annotations.Test;
 * 
 * public class ReportsTest {
 * 
 * @Test public void reportsTest() throws IOException, AWTException { // Read
 * the test data and configuration from the properties file FileInputStream fis
 * = new FileInputStream(
 * "C:\\Users\\vishw\\OneDrive\\Desktop\\TY\\commonData.properties"); Properties
 * pObj = new Properties(); pObj.load(fis); String url =
 * System.getProperty("url", pObj.getProperty("url")); String admUsernameP =
 * System.getProperty("adminUsername", pObj.getProperty("adminUsername"));
 * String admPasswordP = System.getProperty("adminPassword",
 * pObj.getProperty("adminPassword")); String browser =
 * System.getProperty("browser", pObj.getProperty("browser"));
 * 
 * // Initialize the WebDriver based on the specified browser WebDriver driver;
 * if (browser.equalsIgnoreCase("chrome")) { driver = new ChromeDriver(); } else
 * if (browser.equalsIgnoreCase("firefox")) { driver = new FirefoxDriver(); }
 * else { driver = new ChromeDriver(); }
 * 
 * // Launch the Browser driver.manage().window().maximize();
 * driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
 * 
 * // Open the test URL driver.get(url);
 * 
 * // Click on the "Admin" section WebElement admin =
 * driver.findElement(By.xpath("//a[@href='hms/admin']")); admin.click();
 * 
 * // Login as Admin with the valid username and password WebElement admUsername
 * = driver.findElement(By.xpath("//input[@name='username']"));
 * admUsername.sendKeys(admUsernameP);
 * 
 * WebElement admPassword =
 * driver.findElement(By.xpath("//input[@name='password']"));
 * admPassword.sendKeys(admPasswordP);
 * 
 * WebElement admSubmit =
 * driver.findElement(By.xpath("//button[@name='submit']")); admSubmit.click();
 * 
 * // Select the "Reports" section WebElement report =
 * driver.findElement(By.xpath("//span[.=' Reports ']")); report.click();
 * 
 * // Select the "B/w dates reports" option WebElement bwDatesReports =
 * driver.findElement(By.xpath("//span[.='B/w dates reports ']"));
 * bwDatesReports.click();
 * 
 * // Enter the fromDate WebElement fromDate =
 * driver.findElement(By.id("fromdate")); fromDate.click();
 * enterDate("2020-01-01");
 * 
 * // Enter the toDate WebElement toDate = driver.findElement(By.id("todate"));
 * toDate.click(); enterDate("2030-06-29");
 * 
 * // Submit the report form WebElement bwDatesReportSubmit =
 * driver.findElement(By.id("submit")); bwDatesReportSubmit.click();
 * 
 * // Verify the generated report WebElement actualResult =
 * driver.findElement(By.xpath("//h5[@align='center']")); String actual =
 * actualResult.getText(); String expected =
 * "Report from 2020-01-01 to 2030-06-29"; if (actual.contains(expected)) {
 * System.out.println("Reports are present --->PASSED"); } else {
 * System.out.println("Reports are NOT present --->Failed"); }
 * 
 * // Close the browser driver.close(); }
 * 
 * // Helper method to enter
 */
