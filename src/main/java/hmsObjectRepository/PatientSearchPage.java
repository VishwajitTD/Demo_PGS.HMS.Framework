package hmsObjectRepository;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PatientSearchPage {

    @FindBy(xpath = "//span[.=' Dashboard ']")
    private WebElement dashBoard;

    @FindBy(xpath = "//span[.=' Patient Search ']")
    private WebElement searchByName;

    @FindBy(xpath = "//input[@name='searchdata']")
    private WebElement patSearch;

    @FindBy(xpath = "//button[@name='search']")
    private WebElement searchSubmit;

    public PatientSearchPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
    
    // utilization
    public WebElement getDashBoard() {
		return dashBoard;
	}

	public WebElement getSearchByName() {
		return searchByName;
	}

	public WebElement getPatSearch() {
		return patSearch;
	}
	
	//Business Libraries
   
    public void searchPatient(String patientName) {
        dashBoard.click();
        searchByName.click();
        patSearch.sendKeys(patientName);
        searchSubmit.click();
    }

	

    
}
