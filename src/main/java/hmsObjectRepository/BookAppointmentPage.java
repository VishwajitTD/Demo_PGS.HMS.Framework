package hmsObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pgh.hms.generic.genericUtility.JavaScriptUtility;
import pgh.hms.generic.genericUtility.WebActionUtility;
/**
 *  This page consist of all the element to Book an Appointment
 * @author jeet
 *
 */
public class BookAppointmentPage {
	WebActionUtility wLib;
	WebDriver driver;
	JavaScriptUtility jsLib;

	// Declaration
	// Book Appointment
	@FindBy(xpath = "//span[.=' Book Appointment ']")
	private WebElement bookAppointment;
	
	// Doctor Specialization
	@FindBy(xpath = "//select[@name='Doctorspecialization']")
	private WebElement selectDoctorspecialization;
	
	// Dentist Option
	@FindBy(xpath = "//option[@value='Dentist']")
	private WebElement selectDentist;
	
	// Doctor Option
	@FindBy(xpath = "//select[@name='doctor']")
	private WebElement selectDoctor;
	
	// Choose Option
	@FindBy(xpath = "//option[@value='1']")
	private WebElement chooseOption;
	
	// Appointment Date
	@FindBy(xpath = "//input[@name='appdate']")
	private WebElement appDate;
	
	// Appointment Day
	@FindBy(xpath = "//td[.='29' and @class='day']")
	private WebElement appDay;
	
	// Appointment Time
	@FindBy(xpath = "//input[@name='apptime']")
	private WebElement appTime;
	
	// Appointment Time Hour
	@FindBy(xpath = "//input[@class='bootstrap-timepicker-hour form-control']")
	private WebElement appTimeHour;
	
	// Appointment Time Minute
	@FindBy(xpath = "//input[@class='bootstrap-timepicker-minute form-control']")
	private WebElement appTimeMinute;
	
	// Appointment Time Meridian
	@FindBy(xpath = "//input[@class='bootstrap-timepicker-meridian form-control']")
	private WebElement appTimeMeridian;
	
	// Appointment Submit Button
	@FindBy(xpath = "//button[@name='submit']")
	private WebElement appSubmit;
	
	// Initialization
	public BookAppointmentPage(WebDriver driver) {
		jsLib = new JavaScriptUtility(driver);
		 wLib=new WebActionUtility();
		wLib.waitForElementInDOM(driver);
		PageFactory.initElements(driver, this);
	}
	
	//Utilization
	public WebElement getBookAppointment() {
		return bookAppointment;
	}

	public WebElement getSelectDoctorspecialization() {
		return selectDoctorspecialization;
	}

	public WebElement getSelectDentist() {
		return selectDentist;
	}

	public WebElement getSelectDoctor() {
		return selectDoctor;
	}

	public WebElement getChooseOption() {
		return chooseOption;
	}

	public WebElement getAppDate() {
		return appDate;
	}

	public WebElement getAppDay() {
		return appDay;
	}

	public WebElement getAppTime() {
		return appTime;
	}

	public WebElement getAppTimeHour() {
		return appTimeHour;
	}

	public WebElement getAppTimeMinute() {
		return appTimeMinute;
	}

	public WebElement getAppTimeMeridian() {
		return appTimeMeridian;
	}

	public WebElement getAppSubmit() {
		return appSubmit;
	}

	
	// Click on Book Appointment
	public void clickBookAppointment() {
		bookAppointment.click();
	}
	
	// Select Doctor Specialization
	public void selectDoctorSpecialization() {
		selectDoctorspecialization.click();
	}
	
	// Select Dentist
	public void selectDentist() {
		selectDentist.click();
	}
	
	// Select Doctor
	public void selectDoctor() {
		selectDoctor.click();
	}
	
	// Choose Option
	public void chooseOption() {
		chooseOption.click();
	}
	
	// Click on Appointment Date
	public void clickAppointmentDate() {
		appDate.click();
	}
	
	// Select Appointment Day
	public void selectAppointmentDay() {
		appDay.click();
	}
	
	// Click on Appointment Time
	public void clickAppointmentTime() {
		appTime.click();
	}
	
	// Enter Appointment Time Hour
	public void enterAppointmentTimeHour(String hour) {
		appTimeHour.click();
		appTimeHour.clear();
		appTimeHour.sendKeys(hour);
	}
	
	// Enter Appointment Time Minute
	public void enterAppointmentTimeMinute(String minute) {
		appTimeMinute.click();
		appTimeMinute.sendKeys(minute);
	}
	
	// Enter Appointment Time Meridian
	public void enterAppointmentTimeMeridian(String meridian) {
		appTimeMeridian.click();
		appTimeMeridian.clear();
		appTimeMeridian.sendKeys(meridian);
	}
	
	// Click on Appointment Submit Button
	public void clickAppointmentSubmitButton() {
		appSubmit.click();
	}
	
	// Business library method to perform booking appointment
	public void performBookingAppointment(String TimeHour,String TimeMinute, String TimeMeridian ) {
		clickBookAppointment();
		selectDoctorSpecialization();
		selectDentist();
		selectDoctor();
		chooseOption();
		clickAppointmentDate();
		selectAppointmentDay();
		clickAppointmentTime();
		enterAppointmentTimeHour(TimeHour);
		enterAppointmentTimeMinute(TimeMinute);
		enterAppointmentTimeMeridian(TimeMeridian);
		clickAppointmentSubmitButton();
		
	}
}
