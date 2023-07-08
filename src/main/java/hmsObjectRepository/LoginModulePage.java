package hmsObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pgh.hms.generic.genericUtility.WebActionUtility;
/**
 * This page consist of all LoginModule elements
 * @author jeet
 *
 */
public class LoginModulePage {
	WebActionUtility wLib;
	//Declaration
	// Patient Module
	@FindBy(xpath = "//input[@name='username']")
	private WebElement patientUsername;

	@FindBy(xpath = "//input[@name='password']")
	private WebElement patientPassword;

	@FindBy(xpath = "//button[@name='submit']")
	private WebElement patientSubmit;

	// Doctor Module
	@FindBy(xpath = "//input[@name='username']")
	private WebElement doctorUsername;

	@FindBy(xpath = "//input[@name='password']")
	private WebElement doctorPassword;

	@FindBy(xpath = "//button[@name='submit']")
	private WebElement doctorSubmit;

	// Admin Module
	@FindBy(xpath = "//input[@name='username']")
	private WebElement adminUsername;

	@FindBy(xpath = "//input[@name='password']")
	private WebElement adminPassword;

	@FindBy(xpath = "//button[@name='submit']")
	private WebElement adminSubmit;

	// Initialization
	public LoginModulePage(WebDriver driver) {
		 wLib=new WebActionUtility();
		wLib.waitForElementInDOM(driver);
		PageFactory.initElements(driver, this);
	}

	// Utilization methods
	public WebElement getPatientUsername() {
		return patientUsername;
	}

	public WebElement getPatientPassword() {
		return patientPassword;
	}

	public WebElement getPatientSubmit() {
		return patientSubmit;
	}

	public WebElement getDoctorUsername() {
		return doctorUsername;
	}

	public WebElement getDoctorPassword() {
		return doctorPassword;
	}

	public WebElement getDoctorSubmit() {
		return doctorSubmit;
	}

	public WebElement getAdminUsername() {
		return adminUsername;
	}

	public WebElement getAdminPassword() {
		return adminPassword;
	}

	public WebElement getAdminSubmit() {
		return adminSubmit;
	}

	// Business library methods
	public void loginToAdminModule(String adminUserName, String adminPassWord) {
		adminUsername.sendKeys(adminUserName);
		adminPassword.sendKeys(adminPassWord);
		adminSubmit.click();
	}

	public void loginToPatientModule(String patUserName, String patPassWord) {
		patientUsername.sendKeys(patUserName);
		patientPassword.sendKeys(patPassWord);
		patientSubmit.click();
	}

	public void loginToDoctorModule(String docUserName, String docPassWord) {
		doctorUsername.sendKeys(docUserName);
		doctorPassword.sendKeys(docPassWord);
		doctorSubmit.click();
	}
}
