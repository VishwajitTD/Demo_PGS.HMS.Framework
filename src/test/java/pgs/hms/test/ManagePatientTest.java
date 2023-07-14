package pgs.hms.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import hmsObjectRepository.AddPatientPage;
import hmsObjectRepository.HMS_WelcomPage;
import hmsObjectRepository.LoginModulePage;
import hmsObjectRepository.ManagePatientHistoryPage;
import pgh.hms.generic.genericUtility.BaseClass;
@Listeners(pgh.hms.generic.genericUtility.ListenerImplementation.class)

public class ManagePatientTest extends BaseClass {

	@Test(retryAnalyzer = pgh.hms.generic.genericUtility.RetryImplementation.class,dataProvider = "Medical History",groups = "smokeTest")

	public void ManagPatientHistoryTest(String bp,String sugar,String weight,String temp,String prescription) throws Throwable {
		// Login as Doctor

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
		Reporter.log("Logged in as Doctor.....", true);

		String patientName = eLib.getDataFromExcelBasedTestId(excelPath, "Patient Details", "TC_01", "patientName");
		String patientEmail = eLib.getDataFromExcelBasedTestId(excelPath, "Patient Details", "TC_01", "patientEmail");
		String patientAddress = eLib.getDataFromExcelBasedTestId(excelPath, "Patient Details", "TC_01",
				"patientAddress");
		String patientAge = eLib.getDataFromExcelBasedTestId(excelPath, "Patient Details", "TC_01", "patientAge");
		String patientMedicalHistory = eLib.getDataFromExcelBasedTestId(excelPath, "Patient Details", "TC_01",
				"patientMedicalHistory");

		// get random number and Mobile Number
		String random = jLib.getRandomMobileNumber();
		String expectedMobile = jLib.getRandomMobileNumber();

		// Create an instance of AddPatientPage
		AddPatientPage addPatientPage = new AddPatientPage(driver);

		// Click on Patient Dropdown and then click on Add Patient
		addPatientPage.clickPatientDropdown();
		addPatientPage.clickAddPatientButton();

		// Fill the form Patient History
		addPatientPage.enterPatientName(patientName + random);
		addPatientPage.enterPatientContactNumber(expectedMobile);
		addPatientPage.enterPatientEmail(patientEmail + random);
		addPatientPage.selectMaleGender();
		addPatientPage.enterPatientAddress(patientAddress + random);
		addPatientPage.enterPatientAge(patientAge + random);
		addPatientPage.enterPatientMedicalHistory(patientMedicalHistory + random);

		// public void addPatient(String name, String contactNumber, String email,
		//String address, String age, String medicalHistory) {

		// Submit the form
		addPatientPage.clickSubmitButton();

		addPatientPage.clickPatientDropdown();

		// Create an instance of the ManagePatientHistoryPage
		ManagePatientHistoryPage managePatientHistoryPage = new ManagePatientHistoryPage(driver);

		// Click on the Manage Patient link
		managePatientHistoryPage.clickManagePatientLink();

		// Click on the View Last Updated Patient button
		managePatientHistoryPage.clickViewLastUpdatedPatient();

		// Click on the Add Medical History button and fill in the details
		managePatientHistoryPage.addMedicalHistory(bp, sugar, weight, temp, prescription);

		wLib.swithToAlertWindowAndOnlyAccpect(driver);
		// profile
		WebElement profile = driver.findElement(By.xpath("//span[@class='username']"));
		profile.click();
		String actualMobile = driver.findElement(By.xpath("//tbody/tr[last()]/td[3]")).getText();
		System.out.println(actualMobile + ": actualMobile");
		System.out.println(expectedMobile + " : expectedMobile");

		// Verify the appointment is there
		Assert.assertTrue(expectedMobile.contains(actualMobile), "Patient is NOT create  ---> Fail");

		Reporter.log("Patient is create  ---> PASS", true);

		Reporter.log("Loggin out as Doctor", true);

	}
	
	/**
	 * 
	 */
	@DataProvider(name = "Medical History")
	public Object [][] medicalHistoryData(){
		Object[][] data = eLib.getDataForDataProvider(excelPath, "Medical History");
		return data;
		
	}

}



