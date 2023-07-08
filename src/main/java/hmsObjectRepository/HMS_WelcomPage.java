package hmsObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import pgh.hms.generic.genericUtility.WebActionUtility;
/**
 * This consist of all the element of Home Page 
 * @author jeet
 *
 */
public class HMS_WelcomPage {
	WebActionUtility wLib;
	// Declaration
		@FindBy(xpath = "//a[@href='hms/admin']")
		private WebElement adminLogin;
		
		@FindBy(xpath = "//a[@href='hms/user-login.php']")
		private WebElement patientLogin;
		
		@FindBy(xpath = "//a[@href='hms/doctor/'and text()='Click Here']")
		private WebElement doctorLogin;

		
	//Initialization
		public HMS_WelcomPage(WebDriver driver) {
			 wLib=new WebActionUtility();
			wLib.waitForElementInDOM(driver);
			PageFactory.initElements(driver,this);
		}
		
		
	//Utilization 
		public WebElement getAdminLogin() {
			return adminLogin;
		}

		public WebElement getPatientLogin() {
			return patientLogin;
		}

		public WebElement getDoctorLogin() {
			return doctorLogin;
		}
		
		
	// business library
		
		public void adminLogin() {
		    adminLogin.click();		    
		}

		public void patientLogin() {
		    patientLogin.click();
		}

		public void doctorLogin() {
		    doctorLogin.click();
		}
		
		

}
