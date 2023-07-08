package pgs.hms.practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Integration_between_Doctor_And_PateintTest {

	@Test
	
	public void integration_between_Doctor_And_PateintTest() throws InterruptedException, IOException {
		//get the java representation object of the Physical file
		FileInputStream fis=new FileInputStream("C:\\Users\\vishw\\OneDrive\\Desktop\\TY\\commonData.properties");
		Properties pObj=new Properties();
		pObj.load(fis);
		String url = System.getProperty("url",pObj.getProperty("url"));
		String docUsernameP = System.getProperty("docUsername",pObj.getProperty("docUsername"));
		String docPasswordP = System.getProperty("docPassword",pObj.getProperty("docPassword"));
		String patUsernameP = System.getProperty("patUsername",pObj.getProperty("patUsername"));
		String patPasswordP = System.getProperty("patPassword",pObj.getProperty("patPassword"));
		String browser = System.getProperty("browser",pObj.getProperty("browser"));

		

		//  launch the Browser [CTP]
		WebDriver driver;
		if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();

		} else if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else {
			driver = new ChromeDriver();

		}
		
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// "Open the browser and enter the test URL"

		driver.get(url);

		// chose the patient login
		WebElement patient = driver.findElement(By.xpath("//a[@href='hms/user-login.php']"));
		patient.click();

		// Login as patient with the valid user name and password
		WebElement patUsername = driver.findElement(By.xpath("//input[@name='username']"));
		patUsername.sendKeys(patUsernameP);

		WebElement patPassword = driver.findElement(By.xpath("//input[@name='password']"));
		patPassword.sendKeys(patPasswordP);

		WebElement patSubmit = driver.findElement(By.xpath("//button[@name='submit']"));
		patSubmit.click();

		Thread.sleep(1000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// Book Appointment
		WebElement bookAppointment = driver.findElement(By.xpath("//span[.=' Book Appointment ']"));
		bookAppointment.click();
		Thread.sleep(1000);

		// Fill the form
		WebElement selectDoctorspecialization = driver.findElement(By.xpath("//select[@name='Doctorspecialization']"));
		selectDoctorspecialization.click();
		Thread.sleep(1000);

		WebElement selectDentist = driver.findElement(By.xpath("//option[@value='Dentist']"));
		selectDentist.click();
		Thread.sleep(1000);

		WebElement selectDoctor = driver.findElement(By.xpath("//select[@name='doctor']"));
		selectDoctor.click();
		Thread.sleep(2000);

		WebElement chooseOption = driver.findElement(By.xpath("//option[@value='1']"));
		chooseOption.click();
		Thread.sleep(1000);

		// scroll down
		js.executeScript("window.scrollBy(0,300)");

		WebElement appDate = driver.findElement(By.xpath("//input[@name='appdate']"));
		appDate.click();
		Thread.sleep(1000);

		WebElement appDay = driver.findElement(By.xpath("//td[.='29' and @class='day']"));
		appDay.click();

		WebElement appTime = driver.findElement(By.xpath("//input[@name='apptime']"));
		appTime.click();
		Thread.sleep(1000);

		WebElement appTimeHour = driver
				.findElement(By.xpath("//input[@class='bootstrap-timepicker-hour form-control']"));
		appTimeHour.click();
		appTimeHour.clear();
		appTimeHour.sendKeys("11");
		Thread.sleep(1000);

		WebElement appTimeMiunute = driver
				.findElement(By.xpath("//input[@class='bootstrap-timepicker-minute form-control']"));
		appTimeMiunute.click();
		appTimeMiunute.sendKeys("00");
		Thread.sleep(1000);

		WebElement appTimeMeridian = driver
				.findElement(By.xpath("//input[@class='bootstrap-timepicker-meridian form-control']"));
		appTimeMeridian.click();
		appTimeMeridian.clear();
		appTimeMeridian.sendKeys("AM");
		Thread.sleep(1000);

		WebElement appSubmit = driver.findElement(By.xpath("//button[@name='submit']"));
		appSubmit.click();
		Thread.sleep(1000);

		Alert a = driver.switchTo().alert();
		a.accept();

		// logout
		WebElement profile = driver.findElement(By.xpath("//span[@class='username']"));
		profile.click();

		WebElement logout = driver.findElement(By.xpath("//a[@href='logout.php']"));
		logout.click();


		// Login as doctor with the valid user name and password
		WebElement doctor = driver.findElement(By.xpath("//a[@href='hms/doctor/'and text()='Click Here']"));
		doctor.click();
		
		WebElement docUsername = driver.findElement(By.xpath("//input[@name='username']"));
		docUsername.sendKeys(docUsernameP);

		WebElement docPassword = driver.findElement(By.xpath("//input[@name='password']"));
		docPassword.sendKeys(docPasswordP);

		WebElement docSubmit = driver.findElement(By.xpath("//button[@name='submit']"));
		docSubmit.click();
		
		//check appointment
		WebElement appointment=driver.findElement(By.xpath("//span[.=' Appointment History ']"));
		appointment.click();
		
		WebElement appointmentText = driver.findElement(By.xpath("//table/tbody/tr[last()]"));
		String actual=appointmentText.getText();
		System.out.println("actual: "+actual);
		
		js.executeScript("window.scrollBy(0,400)");
		String expected=driver.findElement(By.xpath("//tbody/tr[last()]//td[@class='center']/following-sibling::td[4]")).getText();
		if (actual.contains(expected)) {
			System.out.println("Appointment is there ---> PASS");
		}
		else {
			System.out.println("Appointment is not there ---> FAIL");
		}
		//logout as patient
//		WebElement profilePat = driver.findElement(By.xpath("//span[@class='username']"));
//		profilePat.click();
//		WebElement logoutPatient = driver.findElement(By.xpath("//a[@href='logout.php']"));
//		logoutPatient.click();
//		
//		driver.close();
//		
		
		

	}

}

/*
 * package pgs.hms.practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Integration_between_Doctor_And_PateintTest {

	@Test
	public void integration_between_Doctor_And_PateintTest() throws InterruptedException, IOException {
		// Read the test data and configuration from the properties file
		FileInputStream fis = new FileInputStream("C:\\Users\\vishw\\OneDrive\\Desktop\\TY\\commonData.properties");
		Properties pObj = new Properties();
		pObj.load(fis);
		String url = System.getProperty("url", pObj.getProperty("url"));
		String docUsernameP = System.getProperty("docUsername", pObj.getProperty("docUsername"));
		String docPasswordP = System.getProperty("docPassword", pObj.getProperty("docPassword"));
		String patUsernameP = System.getProperty("patUsername", pObj.getProperty("patUsername"));
		String patPasswordP = System.getProperty("patPassword", pObj.getProperty("patPassword"));

		String browser = System.getProperty("browser", pObj.getProperty("browser"));

		// Launch the Browser
		WebDriver driver;
		if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else {
			driver = new ChromeDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// Open the browser and enter the test URL
		driver.get(url);

		// Choose the patient login
		WebElement patient = driver.findElement(By.xpath("//a[@href='hms/user-login.php']"));
		patient.click();

		// Login as patient with the valid username and password
		WebElement patUsername = driver.findElement(By.xpath("//input[@name='username']"));
		patUsername.sendKeys(patUsernameP);

		WebElement patPassword = driver.findElement(By.xpath("//input[@name='password']"));
		patPassword.sendKeys(patPasswordP);

		WebElement patSubmit = driver.findElement(By.xpath("//button[@name='submit']"));
		patSubmit.click();

		Thread.sleep(1000);
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Book Appointment
		WebElement bookAppointment = driver.findElement(By.xpath("//span[.=' Book Appointment ']"));
		bookAppointment.click();
		Thread.sleep(1000);

		// Fill the appointment form
		WebElement selectDoctorspecialization = driver.findElement(By.xpath("//select[@name='Doctorspecialization']"));
		selectDoctorspecialization.click();
		Thread.sleep(1000);

		WebElement selectDentist = driver.findElement(By.xpath("//option[@value='Dentist']"));
		selectDentist.click();
		Thread.sleep(1000);

		WebElement selectDoctor = driver.findElement(By.xpath("//select[@name='doctor']"));
		selectDoctor.click();
		Thread.sleep(2000);

		WebElement chooseOption = driver.findElement(By.xpath("//option[@value='1']"));
		chooseOption.click();
		Thread.sleep(1000);

		// Scroll down
		js.executeScript("window.scrollBy(0,300)");

		// Select appointment date
		WebElement appDate = driver.findElement(By.xpath("//input[@name='appdate']"));
		appDate.click();
		Thread.sleep(1000);

		WebElement appDay = driver.findElement(By.xpath
*/