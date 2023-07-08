package hmsObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pgh.hms.generic.genericUtility.WebActionUtility;


public class ManagePatientHistoryPage  {
	WebActionUtility wLib;
	
	// Declaration
	@FindBy(xpath = "//a[@href='manage-patient.php']")
	private WebElement managePatientLink;

	@FindBy(xpath = "(//i[@class='fa fa-eye'])[last()]")
	private WebElement viewLastUpdatedPatient;

	@FindBy(xpath = "//button[@class='btn btn-primary waves-effect waves-light w-lg']")
	private WebElement addMedicalHistoryButton;

	@FindBy(xpath = "//input[@name='bp']")
	private WebElement bloodPressureField;

	@FindBy(xpath = "//input[@name='bs']")
	private WebElement bloodSugarField;

	@FindBy(xpath = "//input[@name='weight']")
	private WebElement weightField;

	@FindBy(xpath = "//input[@name='temp']")
	private WebElement temperatureField;

	@FindBy(xpath = "//textarea[@name='pres']")
	private WebElement prescriptionField;

	@FindBy(xpath = "//button[@name='submit']")
	private WebElement medicalHistorySubmitButton;

	// Initialization
	public ManagePatientHistoryPage(WebDriver driver) {
		wLib=new WebActionUtility();
		PageFactory.initElements(driver, this);
	}

	//Utilization
	public void clickManagePatientLink() {
		managePatientLink.click();
	}

	public void clickViewLastUpdatedPatient() {
		viewLastUpdatedPatient.click();
	}

	public void clickAddMedicalHistoryButton() {
		addMedicalHistoryButton.click();
	}

	public void enterBloodPressure(String bloodPressure) {
		bloodPressureField.sendKeys(bloodPressure);
	}

	public void enterBloodSugar(String bloodSugar) {
		bloodSugarField.sendKeys(bloodSugar);
	}

	public void enterWeight(String weight) {
		weightField.sendKeys(weight);
	}

	public void enterTemperature(String temperature) {
		temperatureField.sendKeys(temperature);
	}

	public void enterPrescription(String prescription) {
		prescriptionField.sendKeys(prescription);
	}

	public void clickMedicalHistorySubmitButton() {
		medicalHistorySubmitButton.click();
	}

	// Business library method to add medical history
	public void addMedicalHistory(String bloodPressure, String bloodSugar, String weight, String temperature,
		String prescription) {
		clickAddMedicalHistoryButton();
		enterBloodPressure(bloodPressure);
		enterBloodSugar(bloodSugar);
		enterWeight(weight);
		enterTemperature(temperature);
		enterPrescription(prescription);
		clickMedicalHistorySubmitButton();
	}
}
