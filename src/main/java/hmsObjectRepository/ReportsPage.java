package hmsObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pgh.hms.generic.genericUtility.BaseClass;
import pgh.hms.generic.genericUtility.WebActionUtility;
/**
 * By using these Webelement's of  repository you can generate Reports
 * @author jeet
 *
 */
public class ReportsPage extends BaseClass{
	WebActionUtility wLib;
	// Report Section
	@FindBy(xpath = "//span[.=' Reports ']")
	private WebElement reportSection;
	
	// B/w Dates Reports
	@FindBy(xpath = "//span[.='B/w dates reports ']")
	private WebElement bwDatesReports;
	
	// From Date Input
	@FindBy(id = "fromdate")
	private WebElement fromDateInput;
	
	// To Date Input
	@FindBy(id = "todate")
	private WebElement toDateInput;
	
	@FindBy(id="submit")
	private WebElement submit ;
	
	// Initialization
	public ReportsPage(WebDriver driver) {
		wLib=new WebActionUtility();
		PageFactory.initElements(driver, this);
	}
	
	//Utilization
	public WebElement getReportSection() {
	 wLib=new WebActionUtility();
		wLib.waitForElementInDOM(driver);
		return reportSection;
	}

	public WebElement getBwDatesReports() {
		return bwDatesReports;
	}

	public WebElement getFromDateInput() {
		return fromDateInput;
	}

	public WebElement getToDateInput() {
		return toDateInput;
	}
	
	public WebElement getSubmit() {
		return submit;
	}

	// Click on Report Section
	public void clickReportSection() {
		reportSection.click();
	}

	// Click on B/w Dates Reports
	public void clickBwDatesReports() {
		bwDatesReports.click();
	}
	
	// Click on From Date Input
	public void clickFromDateInput() {
		fromDateInput.click();
	}
	
	// Click on To Date Input and Submit
	public void clickToDateInput() {
		toDateInput.click();
		
	}
	
	// Click on To Date Input and Submit
	public void submit() {
		submit.click();
		
	}
	
	// Select B/w Dates Reports and Click on From Date Input
	public void selectBwDatesReportsAndClickFromDate() {
		wLib.waitForElementInDOM(driver);
		clickReportSection();
		clickBwDatesReports();
		clickFromDateInput();
	}
	
	// Business library method to perform actions on From Date and To Date inputs
	public void performDateSelection(String fromDate, String toDate) {
		clickFromDateInput();
		// Additional actions for selecting the From Date
		
		clickToDateInput();
		// Additional actions for selecting the To Date
	}
}
