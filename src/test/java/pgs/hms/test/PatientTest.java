package pgs.hms.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import hmsObjectRepository.BookAppointmentPage;
import hmsObjectRepository.HMS_WelcomPage;
import hmsObjectRepository.LoginModulePage;
import hmsObjectRepository.LogoutPage;
import pgh.hms.generic.genericUtility.BaseClass;
@Listeners(pgh.hms.generic.genericUtility.ListenerImplementation.class)

public class PatientTest extends BaseClass {

	@Test(retryAnalyzer = pgh.hms.generic.genericUtility.RetryImplementation.class,groups = "regressionTest")

	public void integrationBetweenDoctorAndPateintTest() throws Throwable {

		// read the common data
		String url = fLib.getDataFromProperties(envtFilePath, "url");
		String patUsername = fLib.getDataFromProperties(envtFilePath, "patUsername");
		String patPassword = fLib.getDataFromProperties(envtFilePath, "patPassword");
		System.out.println(patUsername);
		System.out.println(patPassword);
		
		String docUsername = fLib.getDataFromProperties(envtFilePath, "docUsername");
		String docPassword = fLib.getDataFromProperties(envtFilePath, "docPassword");

		// launch the Browser
		driver.manage().window().maximize();
		wLib.waitForElementInDOM(driver);

		// "Open the browser and enter the test URL"

		driver.get(url);

		// chose the patient login
		HMS_WelcomPage welcome = new HMS_WelcomPage(driver);
		welcome.patientLogin();

		// Login as patient with the valid user name and password
		LoginModulePage choose = new LoginModulePage(driver);
		choose.loginToPatientModule(patUsername, patPassword);

		// Create an instance of BookAppointmentPage
		BookAppointmentPage bookAppointmentPage = new BookAppointmentPage(driver);

		bookAppointmentPage.performBookingAppointment("11", "00", "AM");

		wLib.waitForElementInDOM(driver);
		wLib.swithToAlertWindowAndOnlyAccpect(driver);

		// Create an instance of LogoutPage
		LogoutPage logoutPage = new LogoutPage(driver);

		// Perform logout
		logoutPage.performLogout();
		Reporter.log("Logging Out as Patient....", true);
		// Login as doctor with the valid user name and password
		welcome.doctorLogin();
		choose.loginToDoctorModule(docUsername, docPassword);
		Reporter.log("Log in Doctors....", true);
		// check appointment
		WebElement appointment = driver.findElement(By.xpath("//span[.=' Appointment History ']"));
		appointment.click();

		WebElement appointmentText = driver.findElement(By.xpath("//table/tbody/tr[last()]"));
		String actual = appointmentText.getText();
		System.out.println("actual: " + actual);

		jsLib.executeJavaScript(driver, "window.scrollBy(0,400)");
		String expected = driver
				.findElement(By.xpath("//tbody/tr[last()]//td[@class='center']/following-sibling::td[4]")).getText();

		Assert.assertTrue(actual.contains(expected), "Appointment is NOT there --->Failed");

		Reporter.log("Patient is create  ---> PASS", true);

		Reporter.log("Logging Out as Doctor....");

	}

}