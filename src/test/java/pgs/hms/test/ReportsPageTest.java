package pgs.hms.test;

import java.awt.event.KeyEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import hmsObjectRepository.ReportsPage;
import pgh.hms.generic.genericUtility.BaseClass;
@Listeners(pgh.hms.generic.genericUtility.ListenerImplementation.class)

public class ReportsPageTest extends BaseClass {

	@Test(retryAnalyzer = pgh.hms.generic.genericUtility.RetryImplementation.class,groups = { "regressionTest", "smokeTest" })
	// Admin Log in
	public void reportsTest() throws Throwable {

		// Create an instance of ReportsPage
		ReportsPage reportsPage = new ReportsPage(driver);

		// Enter the formDate
		reportsPage.selectBwDatesReportsAndClickFromDate();
		wLib.getRobot(KeyEvent.VK_0);
		wLib.getRobot(KeyEvent.VK_1);
		wLib.getRobot(KeyEvent.VK_0);
		wLib.getRobot(KeyEvent.VK_1);
		wLib.getRobot(KeyEvent.VK_2);
		wLib.getRobot(KeyEvent.VK_0);
		wLib.getRobot(KeyEvent.VK_2);
		wLib.getRobot(KeyEvent.VK_0);

		// Click on B/w Dates Report Submit Button
		reportsPage.clickToDateInput();

		// Enter the toDate
		wLib.getRobot(KeyEvent.VK_0);
		wLib.getRobot(KeyEvent.VK_3);
		wLib.getRobot(KeyEvent.VK_0);
		wLib.getRobot(KeyEvent.VK_7);
		wLib.getRobot(KeyEvent.VK_2);
		wLib.getRobot(KeyEvent.VK_0);
		wLib.getRobot(KeyEvent.VK_2);
		wLib.getRobot(KeyEvent.VK_3);

		// submit
		reportsPage.submit();
		
	
		// Perform additional actions on To Date input if needed

		WebElement actualResult = driver.findElement(By.xpath("//h5[@align='center']"));
		String actual = actualResult.getText();
		System.out.println(actual + ":--actual");
		String expected = "2020-01-01 to 2023-07-03";
		// Verify the report

		Assert.assertTrue(actual.contains(expected), "Reports are present --->PASSED");

		Reporter.log("Patient is create  ---> PASS", true);

	}

}