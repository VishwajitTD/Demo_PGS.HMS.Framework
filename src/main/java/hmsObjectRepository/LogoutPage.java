package hmsObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pgh.hms.generic.genericUtility.WebActionUtility;

/**
 * This is generic Logout for all the page
 * @author jeet
 *
 */
public class LogoutPage  {
	
	WebActionUtility wLib;
	WebDriver driver;
	// Admin Profile
	@FindBy(xpath = "//span[@class='username']")
	private WebElement profile;
	
	// Logout Link
	@FindAll({@FindBy(xpath="//a[@href='logout.php']"),@FindBy(xpath="//ul[@class='dropdown-menu dropdown-dark']//a[@href='logout.php']")})
	private WebElement logout;
	
	// Initialization
	public LogoutPage(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
	}
	
	//Utilization
	public WebElement getProfile() {
		return profile;
	}

	public WebElement getLogout() {
		return logout;
	}

//Business library method to perform logout
	// Click on Admin Profile
	public void clickProfile() {
		profile.click();
	}
	

	// Click on Logout Link
	public void clickLogout() {
		logout.click();
	}

	
	// Business library method to perform logout
	public void performLogout() {
		clickProfile();
		wLib=new WebActionUtility();
		wLib.waitAndClick(logout);
	
	}
}

