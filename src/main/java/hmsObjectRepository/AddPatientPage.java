package hmsObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pgh.hms.generic.genericUtility.WebActionUtility;

public class AddPatientPage {
	WebActionUtility wLib;
	// Patient Dropdown
	@FindBy(xpath = "//i[@class='icon-arrow']")
	private WebElement patientDropdown;
	
	// Add Patient Button
	@FindBy(xpath = "//span[.=' Add Patient']")
	private WebElement addPatientButton;
	
	// Patient Name Input
	@FindBy(xpath = "//input[@name='patname']")
	private WebElement patientNameInput;
	
	// Patient Contact Number Input
	@FindBy(xpath = "//input[@name='patcontact']")
	private WebElement patientContactInput;
	
	// Patient Email Input
	@FindBy(xpath = "//input[@name='patemail']")
	private WebElement patientEmailInput;
	
	// Male Gender Option
	@FindBy(xpath = "//label[@for='rg-male']")
	private WebElement maleGenderOption;
	
	// Patient Address Input
	@FindBy(xpath = "//textarea[@name='pataddress']")
	private WebElement patientAddressInput;
	
	// Patient Age Input
	@FindBy(xpath = "//input[@name='patage']")
	private WebElement patientAgeInput;
	
	// Patient Medical History Input
	@FindBy(xpath = "//textarea[@name='medhis']")
	private WebElement patientMedicalHistoryInput;
	
	// Submit Button
	@FindBy(id = "submit")
	private WebElement submitButton;
	
	// Initialization
	public AddPatientPage(WebDriver driver) {
		 wLib=new WebActionUtility();
		wLib.waitForElementInDOM(driver);
		PageFactory.initElements(driver, this);
	}
	//Utilization
	
	public WebElement getPatientDropdown() {
		return patientDropdown;
	}



	public WebElement getAddPatientButton() {
		return addPatientButton;
	}

	public WebElement getPatientNameInput() {
		return patientNameInput;
	}


	public WebElement getPatientContactInput() {
		return patientContactInput;
	}


	public WebElement getPatientEmailInput() {
		return patientEmailInput;
	}


	public WebElement getMaleGenderOption() {
		return maleGenderOption;
	}


	public WebElement getPatientAddressInput() {
		return patientAddressInput;
	}



	public WebElement getPatientAgeInput() {
		return patientAgeInput;
	}



	public WebElement getPatientMedicalHistoryInput() {
		return patientMedicalHistoryInput;
	}

	public WebElement getSubmitButton() {
		return submitButton;
	}

	
	// Click on Patient Dropdown
	public void clickPatientDropdown() {
		patientDropdown.click();
	}

	// Click on Add Patient Button
	public void clickAddPatientButton() {
		addPatientButton.click();
	}
	
	// Enter Patient Name
	public void enterPatientName(String name) {
		patientNameInput.sendKeys(name);
	}
	
	// Enter Patient Contact Number
	public void enterPatientContactNumber(String contactNumber) {
		patientContactInput.sendKeys(contactNumber);
	}
	
	// Enter Patient Email
	public void enterPatientEmail(String email) {
		patientEmailInput.sendKeys(email);
	}
	
	// Select Male Gender Option
	public void selectMaleGender() {
		maleGenderOption.click();
	}
	
	// Enter Patient Address
	public void enterPatientAddress(String address) {
		patientAddressInput.sendKeys(address);
	}
	
	// Enter Patient Age
	public void enterPatientAge(String age) {
		patientAgeInput.sendKeys(age);
	}
	
	// Enter Patient Medical History
	public void enterPatientMedicalHistory(String medicalHistory) {
		patientMedicalHistoryInput.sendKeys(medicalHistory);
	}
	
	// Click on Submit Button
	public void clickSubmitButton() {
		submitButton.click();
	}
	
	// Business library method to add a patient
	public void addPatient(String name, String contactNumber, String email, String address, String age, String medicalHistory) {
		clickPatientDropdown();
		clickAddPatientButton();
		enterPatientName(name);
		enterPatientContactNumber(contactNumber);
		enterPatientEmail(email);
		selectMaleGender();
		enterPatientAddress(address);
		enterPatientAge(age);
		enterPatientMedicalHistory(medicalHistory);
		clickSubmitButton();
		
		//
	}
}
