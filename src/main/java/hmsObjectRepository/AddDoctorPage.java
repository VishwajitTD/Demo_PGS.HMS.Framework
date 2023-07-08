package hmsObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pgh.hms.generic.genericUtility.WebActionUtility;
/**
 * This page consist of all the element to create a Doctor
 * @author jeet
 *
 */


public class AddDoctorPage {

	WebActionUtility wLib;
	// Doctors Dropdown
	@FindBy(xpath = "//span[.=' Doctors ']")
	private WebElement doctorsDropdown;

	// Add Doctors Link
	@FindBy(xpath = "//a[@href='add-doctor.php']")
	private WebElement addDoctorsLink;

	// Doctor Specialization Dropdown
	@FindBy(xpath = "//select[@name='Doctorspecialization']")
	private WebElement doctorSpecializationDropdown;

	// Option in Doctor Specialization Dropdown
	@FindBy(xpath = "//option[@value='Neuro Surgeon']")
	private WebElement selectOption;

	// Doctor Name Input
	@FindBy(xpath = "//input[@name='docname']")
	private WebElement docNameInput;

	// Clinic Address Textarea
	@FindBy(xpath = "//textarea[@name='clinicaddress']")
	private WebElement clinicAddressTextarea;

	// Doctor Fees Input
	@FindBy(xpath = "//input[@name='docfees']")
	private WebElement docFeesInput;

	// Doctor Contact Input
	@FindBy(xpath = "//input[@name='doccontact']")
	private WebElement docContactInput;

	// Doctor Email Input
	@FindBy(xpath = "//input[@name='docemail']")
	private WebElement docEmailInput;

	// New Password Input
	@FindBy(xpath = "//input[@name='npass']")
	private WebElement newPasswordInput;

	// Confirm Password Input
	@FindBy(xpath = "//input[@name='cfpass']")
	private WebElement confirmPasswordInput;

	// Add Doctor Submit Button
	@FindBy(xpath = "//button[@name='submit']")
	private WebElement addDocSubmitButton;

	// Initialization
	public AddDoctorPage(WebDriver driver) {
		 wLib=new WebActionUtility();
		wLib.waitForElementInDOM(driver);
		PageFactory.initElements(driver, this);
	}
	
	//Utilization
	
	public WebElement getDoctorsDropdown() {
		return doctorsDropdown;
	}

	public WebElement getAddDoctorsLink() {
		return addDoctorsLink;
	}

	public WebElement getDoctorSpecializationDropdown() {
		return doctorSpecializationDropdown;
	}

	public WebElement getSelectOption() {
		return selectOption;
	}

	public WebElement getDocNameInput() {
		return docNameInput;
	}

	public WebElement getClinicAddressTextarea() {
		return clinicAddressTextarea;
	}

	public WebElement getDocFeesInput() {
		return docFeesInput;
	}

	public WebElement getDocContactInput() {
		return docContactInput;
	}

	public WebElement getDocEmailInput() {
		return docEmailInput;
	}

	public WebElement getNewPasswordInput() {
		return newPasswordInput;
	}

	public WebElement getConfirmPasswordInput() {
		return confirmPasswordInput;
	}

	public WebElement getAddDocSubmitButton() {
		return addDocSubmitButton;
	}

	
	// Business library methods

	public void clickDoctorsDropdown() {
		doctorsDropdown.click();
	}
	
	public void clickAddDoctorsLink() {
		addDoctorsLink.click();
	}

	public void selectDoctorSpecialization() {
		doctorSpecializationDropdown.click();
		selectOption.click();
	}

	public void enterDoctorName(String name) {
		docNameInput.sendKeys(name);
	}

	public void enterClinicAddress(String address) {
		clinicAddressTextarea.sendKeys(address);
	}

	public void enterDoctorFees(String fees) {
		docFeesInput.sendKeys(fees);
	}

	public void enterDoctorContact(String contact) {
		docContactInput.sendKeys(contact);
	}

	public void enterDoctorEmail(String email) {
		docEmailInput.sendKeys(email);
	}

	public void enterNewPassword(String password) {
		newPasswordInput.sendKeys(password);
	}

	public void enterConfirmPassword(String confirmPassword) {
		confirmPasswordInput.sendKeys(confirmPassword);
	}

	public void clickAddDoctorSubmitButton() {
		addDocSubmitButton.click();
	}
	public void addDoctor(String name,String address,String fees,String contact,String email,String password, String confirmPassword) {
		doctorsDropdown.click();
		addDoctorsLink.click();
		doctorSpecializationDropdown.click();
		selectOption.click();
		docNameInput.sendKeys(name);
		clinicAddressTextarea.sendKeys(address);
		docFeesInput.sendKeys(fees);
		docContactInput.sendKeys(contact);
		docEmailInput.sendKeys(email);
		newPasswordInput.sendKeys(password);
		confirmPasswordInput.sendKeys(confirmPassword);
		addDocSubmitButton.click();
		
	}
}
