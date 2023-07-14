package pgh.hms.generic.genericUtility;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * contains all web browser related actions which includes mouse move , select ,
 * switchToWindow , wait etc
 * 
 * @author jeet
 *
 */
public class WebActionUtility {
	
	public FileUtility fLib = new FileUtility();
	int timeout;

	public WebActionUtility() {
		try{
			String filePath = fLib.getFilePathFromPropertiesFile("projectConfigDataFilePath");
		
		String sTimeout = fLib.getDataFromProperties(filePath, "timeout");
		timeout = Integer.parseInt(sTimeout);
	}
		catch (Throwable e) {
		}
	}

	/**
	 * it's an implicitly wait Always wait for Element in DOM document & release the
	 * control if element available
	 * 
	 * @param driver
	 * @throws Throwable
	 */
	public void waitForElementInDOM(WebDriver driver) {

		try {driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
			
		} catch (Throwable e) {		} 
	}

	/**
	 * it's an Explicitly wait Always wait for Page to be loaded & available in GUI
	 * 
	 * @param driver
	 * @param partailPageURL
	 * @throws Throwable
	 */
	public void waitForPage(WebDriver driver, String partailPageURL)  {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.urlContains(partailPageURL));
	}

	/**
	 * it's an Explicitly wait Always wait for Page to be loaded & available in GUI
	 * 
	 * @param driver
	 * @param partailPageURL
	 * @throws Throwable
	 */
	public void waitForElement(WebDriver driver, WebElement element) {

		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
			wait.until(ExpectedConditions.visibilityOf(element));
			
		} catch (Throwable e) {
		}
		
	}

	/**
	 * 
	 * @param element
	 * @throws Throwable
	 */
	public void waitAndClick(WebElement element){

		try {int count = 0;
		while (count < timeout) {
			try {
				element.click();
				break;
			} catch (Throwable e) {
				Thread.sleep(1000);
				count++;
			}
		}
			
		} catch (Throwable e) {
		}
		
	}

	/**
	 * wait for the element and type the data
	 * 
	 * @param element
	 * @param data
	 * @throws InterruptedException
	 */
	public void waitAndType(WebElement element, String data){
		try {
			int count = 0;
			while (count < 20) {
				try {
					element.sendKeys(data);
					break;
				} catch (Throwable e) {
					Thread.sleep(1000);
					count++;
				}
			}
		} catch (Throwable e) {
		}
	}

	/**
	 * used to Switch to Any Window based on Window Title
	 * 
	 * @param driver
	 * @param partialWindowTitle
	 */
	public void swithToWindow(WebDriver driver, String partialWindowTitle) {
		Set<String> set = driver.getWindowHandles();
		Iterator<String> it = set.iterator();

		while (it.hasNext()) {
			String wID = it.next();
			driver.switchTo().window(wID);
			String currentWindowTitle = driver.getTitle();
			if (currentWindowTitle.contains(partialWindowTitle)) {
				System.out.println(partialWindowTitle + "Switch to Window is passed--!");
				break;
			}
		}
	}

	/**
	 * used to Switch to Any Window based on Window URL
	 * 
	 * @param driver
	 * @param partialWindowTitle
	 */
	public void swithToWindowBasedOnURL(WebDriver driver, String partialWindowURL) {
		Set<String> set = driver.getWindowHandles();
		Iterator<String> it = set.iterator();

		while (it.hasNext()) {
			String wID = it.next();
			driver.switchTo().window(wID);
			String currentWindowTitle = driver.getCurrentUrl();
			if (currentWindowTitle.contains(partialWindowURL)) {
				System.out.println(partialWindowURL + "Switch to Window is passed--!");
				break;
			}
		}
	}

	/**
	 * used to Switch to Alert Window & click on OK button
	 * 
	 * @param driver
	 */

	public void swithToAlertWindowAndAccpectWithCondition(WebDriver driver, String expectedMsg) {
		Alert alt = driver.switchTo().alert();
		if (alt.getText().trim().equalsIgnoreCase(expectedMsg.trim())) {
			System.out.println("Alert Message is verified");
		} else {
			System.out.println("Alert Message is not verified");
		}
		alt.accept();
	}

	/**
	 * Here we just accept Handle the Alert pop-up by accepting it
	 * 
	 * @param driver
	 */
	public void swithToAlertWindowAndOnlyAccpect(WebDriver driver) {
		Alert alt = driver.switchTo().alert();
		alt.accept();
	}

	/**
	 * used to Switch to Alert Window & click on Cancel button
	 * 
	 * @param driver
	 */
	public void swithToAlertWindowAndCancel(WebDriver driver, String expectedMsg) {
		Alert alt = driver.switchTo().alert();
		if (alt.getText().equals(expectedMsg)) {
			System.out.println("Alert Message is verified");
		} else {
			System.out.println("Alert Message is not verified");
		}
		alt.dismiss();
	}

	/**
	 * used to Switch to Frame Window based on index
	 * 
	 * @param driver
	 * @param index
	 */
	public void swithToFrame(WebDriver driver, int index) {
		driver.switchTo().frame(index);
	}

	/**
	 * used to Switch to Frame Window based on id or name attribute
	 * 
	 * @param driver
	 * @param id_name_attribute
	 */
	public void swithToFrame(WebDriver driver, String id_name_attribute) {
		driver.switchTo().frame(id_name_attribute);
	}

	/**
	 * used to select the value from the dropDwon based on index
	 * 
	 * @param driver
	 * @param index
	 */
	public void select(WebElement element, int index) {
		Select sel = new Select(element);
		sel.selectByIndex(index);
	}

	/**
	 * used to select the value from the dropDwon based on value / option avlaible
	 * in GUI
	 * 
	 * @param element
	 * @param value
	 */
	public void select(WebElement element, String text) {
		Select sel = new Select(element);
		sel.selectByVisibleText(text);
	}

	/**
	 * used to place mouse cursor on specified element
	 * 
	 * @param driver
	 * @param elemnet
	 */
	public void mouseOverOnElement(WebDriver driver, WebElement elemnet) {
		Actions act = new Actions(driver);
		act.moveToElement(elemnet).perform();
	}

	/**
	 * used to right click on specified element
	 * 
	 * @param driver
	 * @param elemnet
	 */

	public void rightClickOnElement(WebDriver driver, WebElement elemnet) {
		Actions act = new Actions(driver);
		act.contextClick(elemnet).perform();
	}

	/**
	 * pass enter Key appertain in to Browser
	 * 
	 * @param driver
	 */
	public void passEnterKey(WebDriver driver) {
		Actions act = new Actions(driver);
		act.sendKeys(Keys.ENTER).perform();
	}

    public void getRobot(int keys) {
        try {
            Robot robot = new Robot();
            robot.keyPress(keys);
            robot.keyRelease(keys);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
    /**
     * This method will take the failed screenshot
     * @param driver
     * @param screenshotname
     * @return
     * @throws IOException
     */

    public String screenshot(WebDriver driver,String screenshotname) throws IOException
    {
    	TakesScreenshot shot=(TakesScreenshot)driver;
    	File scr = shot.getScreenshotAs(OutputType.FILE);
    	File dst=new File(".\\sceenshot\\"+screenshotname+".png");
    	FileUtils.copyFile(scr, dst);
    	return dst.getAbsolutePath();
   
    	
    }
    
    
    
}
