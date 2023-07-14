package pgs.hms.test;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import hmsObjectRepository.AddDoctorPage;
import hmsObjectRepository.PatientSearchPage;
import pgh.hms.generic.genericUtility.BaseClass;

@Listeners(pgh.hms.generic.genericUtility.ListenerImplementation.class)
public class AddingDoctorTest extends BaseClass {

	// Admin Log in
	@Test(retryAnalyzer = pgh.hms.generic.genericUtility.RetryImplementation.class,groups = "regressionTest", dataProvider = "addDoctorData")
	public void addingDoctorsAndSearchPatientsTest(String doctor_Name, String clinic_Address, String doctor_Fees,
			String contact_Number, String doctor_Email, String new_Password, String confirm_Password) throws Throwable {
		// read Excel testData
		SoftAssert sAssert = new SoftAssert();
		
		// Create an instance of AddDoctorPage
		AddDoctorPage addDoctorPage = new AddDoctorPage(driver);
		
		wLib.waitForElementInDOM(driver);
		String expected=doctor_Email+jLib.getRandomNumber();
		// Add Doctor
		addDoctorPage.addDoctor(doctor_Name, clinic_Address, doctor_Fees, contact_Number, expected, new_Password,
				confirm_Password);

		wLib.waitForElementInDOM(driver);
		// Handle the Alert
		wLib.swithToAlertWindowAndOnlyAccpect(driver);

		// scroll down to element
		jsLib.executeJavaScript(driver, "window.scrollBy(0,300)");

		driver.findElement(By.xpath("(//table/tbody/tr[*]/td[5])[last()]//i[@class='fa fa-pencil'] ")).click();
		String actual=driver.findElement(By.xpath("//input[@name='docemail']")).getAttribute("value");


		// Verify the doctor is created
		sAssert.assertTrue(expected.contains(actual), "Doctor has been NOT created");
		Reporter.log(expected + " This doctor has been created",true);
		

		// Search for the patient
		String expected1 = "John";
		PatientSearchPage searchPatient = new PatientSearchPage(driver);
		searchPatient.searchPatient(expected1);


		String actual1 = driver.findElement(By.xpath("//td[2]")).getText();
		System.out.println(actual1 + " : actual1 ");
		
		

		// Check for the patient is present with Patient Contact Number
		Assert.assertTrue(actual1.contains(expected1), "Patient is NOT Present: Failed");
		Reporter.log(expected1 + " Patient is Present: Passed",true);

		sAssert.assertAll();
	}

	@DataProvider
	public Object[][] addDoctorData() {

		Object[][] data = eLib.getDataForDataProvider(excelPath, "Data Provider");

		return data;

	}

}
