package pgs.hms.practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Adding_Doctors_and_SearchPatientsTest {

	@Test
	public void adding_Doctors_and_SearchPatientsTest() throws IOException, InterruptedException {
		// get the java representation object of the Physical file
		FileInputStream fis = new FileInputStream("C:\\Users\\vishw\\OneDrive\\Desktop\\TY\\commonData.properties");
		Properties pObj = new Properties();
		pObj.load(fis);
		String url = System.getProperty("url", pObj.getProperty("url"));
//				String docUsernameP = System.getProperty("docUsername",pObj.getProperty("docUsername"));
//				String docPasswordP = System.getProperty("docPassword",pObj.getProperty("docPassword"));
//				String patUsernameP = System.getProperty("patUsername",pObj.getProperty("patUsername"));
//				String patPasswordP = System.getProperty("patPassword",pObj.getProperty("patPassword"));
		//
		String admUsernameP = System.getProperty("adminUsername", pObj.getProperty("adminUsername"));
		String admPasswordP = System.getProperty("adminPassword", pObj.getProperty("adminPassword"));

		String browser = System.getProperty("browser", pObj.getProperty("browser"));

		// String patUsernameP=pObj.getProperty("patUsername");
		// String patPasswordP=pObj.getProperty("patPassword");

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

		WebElement admin = driver.findElement(By.xpath("//a[@href='hms/admin']"));
		admin.click();

		// Login as Admin with the valid user name and password
		WebElement admUsername = driver.findElement(By.xpath("//input[@name='username']"));
		admUsername.sendKeys(admUsernameP);

		WebElement admPassword = driver.findElement(By.xpath("//input[@name='password']"));
		admPassword.sendKeys(admPasswordP);

		WebElement admSubmit = driver.findElement(By.xpath("//button[@name='submit']"));
		admSubmit.click();

		WebElement doctorsDropdown = driver.findElement(By.xpath("//span[.=' Doctors ']"));
		doctorsDropdown.click();

		WebElement addDoctors = driver.findElement(By.xpath("//a[@href='add-doctor.php']"));
		addDoctors.click();

		WebElement doctorSpecialization = driver.findElement(By.xpath("//select[@name='Doctorspecialization']"));
		doctorSpecialization.click();

		WebElement selectOption = driver.findElement(By.xpath("//option[@value='Neuro Surgeon']"));
		selectOption.click();

		WebElement docName = driver.findElement(By.xpath("//input[@name='docname']"));
		docName.sendKeys("Abhishek TY" + random);

		WebElement clinicAdd = driver.findElement(By.xpath("//textarea[@name='clinicaddress']"));
		clinicAdd.sendKeys("Delhi" + random);

		WebElement docFees = driver.findElement(By.xpath("//input[@name='docfees']"));
		docFees.sendKeys("100" + random);

		WebElement docContact = driver.findElement(By.xpath("//input[@name='doccontact']"));
		docContact.sendKeys("9999988888");

		WebElement docMail = driver.findElement(By.xpath("//input[@name='docemail']"));
		docMail.sendKeys("Abhisheck" + random + "@gmail.com");

		WebElement newPassword = driver.findElement(By.xpath("//input[@name='npass']"));
		newPassword.sendKeys("Abhisheck@123");

		WebElement cnfPassword = driver.findElement(By.xpath("//input[@name='cfpass']"));
		cnfPassword.sendKeys("Abhisheck@123");

		WebElement addDocSubmit = driver.findElement(By.xpath("//button[@name='submit']"));
		addDocSubmit.click();

		Alert a = driver.switchTo().alert();
		System.out.println(a.getText());

		a.accept();

		// scroll down to element
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1200)");
		String actual = driver.findElement(By.xpath("//tbody[last()]")).getText();
		System.out.println(actual + "check1");
		String expected = "Abhishek TY";

		// Verify the doctor is created
		if (actual.contains(expected)) {
			System.out.println("Doctor has been created");
		} else {
			System.out.println("Doctor has been NOT created");
		}

		// Search for the patient
		WebElement dashBoard = driver.findElement(By.xpath("//span[.=' Dashboard ']"));
		dashBoard.click();

		WebElement searchByName = driver.findElement(By.xpath("//span[.=' Patient Search ']"));
		searchByName.click();

		WebElement patSearch = driver.findElement(By.xpath("//input[@name='searchdata']"));
		patSearch.sendKeys("John");

		WebElement searchSubmite = driver.findElement(By.xpath("//button[@name='search']"));
		searchSubmite.click();

		String expected2 = "1234567890";

		String actual2 = driver.findElement(By.xpath("//tbody//td[3]")).getText();

		// Check for the patient is present with Patient Contact Number
		if (actual2.contains(expected2)) {
			System.out.println("Patient is Present: Passed");
		} else {
			System.out.println("Patient is NOT Present: Failed");
		}

		// logout as Admin
		WebElement profileAdm = driver.findElement(By.xpath("//span[@class='username']"));
		profileAdm.click();
		WebElement logoutAdm = driver.findElement(By.xpath("//a[@href='logout.php']"));
		logoutAdm.click();

		driver.close();
	}

}

/*
 * 
 * package pgs.hms.practice;
 * 
 * import java.io.FileInputStream; import java.io.IOException; import
 * java.time.Duration; import java.util.Properties; import java.util.Random;
 * 
 * import org.openqa.selenium.Alert; import org.openqa.selenium.By; import
 * org.openqa.selenium.JavascriptExecutor; import org.openqa.selenium.WebDriver;
 * import org.openqa.selenium.WebElement; import
 * org.openqa.selenium.chrome.ChromeDriver; import
 * org.openqa.selenium.firefox.FirefoxDriver; import
 * org.testng.annotations.Test;
 * 
 * public class Adding_Doctors_and_SearchPatientsTest {
 * 
 * @Test public void adding_Doctors_and_SearchPatientsTest() throws IOException,
 * InterruptedException { // Load test data from properties file FileInputStream
 * fis = new FileInputStream(
 * "C:\\Users\\vishw\\OneDrive\\Desktop\\TY\\commonData.properties"); Properties
 * pObj = new Properties(); pObj.load(fis); String url =
 * System.getProperty("url", pObj.getProperty("url")); String admUsernameP =
 * System.getProperty("adminUsername", pObj.getProperty("adminUsername"));
 * String admPasswordP = System.getProperty("adminPassword",
 * pObj.getProperty("adminPassword")); String browser =
 * System.getProperty("browser", pObj.getProperty("browser"));
 * 
 * // Generate random number int random = new Random().nextInt(3000);
 * 
 * // Launch the browser WebDriver driver; if
 * (browser.equalsIgnoreCase("chrome")) { driver = new ChromeDriver(); } else if
 * (browser.equalsIgnoreCase("firefox")) { driver = new FirefoxDriver(); } else
 * { driver = new ChromeDriver(); }
 * 
 * // Configure browser settings driver.manage().window().maximize();
 * driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
 * 
 * // Open the browser and enter the test URL driver.get(url);
 * 
 * // Login as Admin WebElement admin =
 * driver.findElement(By.xpath("//a[@href='hms/admin']")); admin.click();
 * 
 * WebElement admUsername =
 * driver.findElement(By.xpath("//input[@name='username']"));
 * admUsername.sendKeys(admUsernameP); Thread.sleep(1000);
 * 
 * WebElement admPassword =
 * driver.findElement(By.xpath("//input[@name='password']"));
 * admPassword.sendKeys(admPasswordP); Thread.sleep(1000);
 * 
 * WebElement admSubmit =
 * driver.findElement(By.xpath("//button[@name='submit']")); admSubmit.click();
 * Thread.sleep(1000);
 * 
 * // Add a new doctor WebElement doctorsDropdown =
 * driver.findElement(By.xpath("//span[.=' Doctors ']"));
 * doctorsDropdown.click(); Thread.sleep(1000);
 * 
 * WebElement addDoctors =
 * driver.findElement(By.xpath("//a[@href='add-doctor.php']"));
 * addDoctors.click(); Thread.sleep(1000);
 * 
 * WebElement doctorSpecialization =
 * driver.findElement(By.xpath("//select[@name='Doctorspecialization']"));
 * doctorSpecialization.click(); Thread.sleep(1000);
 * 
 * WebElement selectOption =
 * driver.findElement(By.xpath("//option[@value='Neuro Surgeon']"));
 * selectOption.click(); Thread.sleep(1000);
 * 
 * WebElement docName =
 * driver.findElement(By.xpath("//input[@name='docname']"));
 * docName.sendKeys("Abhishek TY" + random); Thread.sleep(1000);
 * 
 * WebElement clinicAdd =
 * driver.findElement(By.xpath("//textarea[@name='clinicaddress']"));
 * clinicAdd.sendKeys("Delhi" + random); Thread.sleep(1000);
 * 
 * WebElement docFees =
 * driver.findElement(By.xpath("//input[@name='docfees']"));
 * docFees.sendKeys("100" + random); Thread.sleep(1000);
 * 
 * WebElement docContact =
 * driver.findElement(By.xpath("//input[@name='doccontact']"));
 * docContact.sendKeys("9999988888"); Thread.sleep(1000); WebElement docMail =
 * driver.findElement(By.xpath("//input[@name='docemail']"));
 * docMail.sendKeys("Abhishek" + random + "@gmail.com"); Thread.sleep(1000);
 * 
 * WebElement newPassword =
 * driver.findElement(By.xpath("//input[@name='npass']"));
 * newPassword.sendKeys("Abhishek@123"); Thread.sleep(1000);
 * 
 * WebElement cnfPassword =
 * driver.findElement(By.xpath("//input[@name='cfpass']"));
 * cnfPassword.sendKeys("Abhishek@123"); Thread.sleep(1000);
 * 
 * WebElement addDocSubmit =
 * driver.findElement(By.xpath("//button[@name='submit']"));
 * addDocSubmit.click();
 * 
 * // Handle the alert Alert alert = driver.switchTo().alert();
 * System.out.println(alert.getText()); alert.accept();
 * 
 * Thread.sleep(1000);
 * 
 * // Scroll down to element JavascriptExecutor js = (JavascriptExecutor)
 * driver; js.executeScript("window.scrollBy(0,1200)");
 * 
 * String actual = driver.findElement(By.xpath("//tbody[last()]")).getText();
 * System.out.println(actual + "check1"); String expected = "Abhishek TY";
 * 
 * // Verify if the doctor is created if (actual.contains(expected)) {
 * System.out.println("Doctor has been created"); } else {
 * System.out.println("Doctor has NOT been created"); }
 * 
 * // Search for a patient WebElement dashBoard =
 * driver.findElement(By.xpath("//span[.=' Dashboard ']")); dashBoard.click();
 * 
 * WebElement searchByName =
 * driver.findElement(By.xpath("//span[.=' Patient Search ']"));
 * searchByName.click();
 * 
 * WebElement patSearch =
 * driver.findElement(By.xpath("//input[@name='searchdata']"));
 * patSearch.sendKeys("John");
 * 
 * WebElement searchSubmit =
 * driver.findElement(By.xpath("//button[@name='search']"));
 * searchSubmit.click();
 * 
 * String expected2 = "1234567890"; String actual2 =
 * driver.findElement(By.xpath("//tbody//td[3]")).getText();
 * 
 * // Check if the patient is present with the given contact number if
 * (actual2.contains(expected2)) {
 * System.out.println("Patient is Present: Passed"); } else {
 * System.out.println("Patient is NOT Present: Failed"); }
 * 
 * Thread.sleep(1000);
 * 
 * // Close the browser driver.close(); } }
 * 
 */
