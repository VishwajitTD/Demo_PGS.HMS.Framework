package pgh.hms.generic.genericUtility;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * 
 * @author jeet
 *
 */
public class JavaScriptUtility {
     JavascriptExecutor js;
    
    
    /**
     * Constructor to initialize the JavaScript class with a WebDriver instance.
     */
    public JavaScriptUtility( WebDriver driver) {
        js=(JavascriptExecutor)driver;
    }
    
    
	/**
     * Executes the given JavaScript code.
     * @param javaScript The JavaScript code to be executed.
     */
    public void executeJavaScript( WebDriver driver,String javaScript) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(javaScript);
    }
    
    /**
     * Performs a forced click on the specified WebElement using JavaScript.
     * @param webElement The WebElement to be clicked.
     */
    public void executeJavaScriptForcedClick(WebDriver driver,WebElement webElement) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()", webElement);
    }
    
    /**
     * Scrolls the page vertically to the specified pixel offset.
     * @param yOffset The vertical pixel offset to scroll by.
     */
    public void scrollPageBy(WebDriver driver,int yOffset) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, " + yOffset + ")");
    }
    
    /**
     * Scrolls the page to the top.
     */
    public void scrollToTop(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 0)");
    }
    
    /**
     * Scrolls the page to the bottom.
     */
    public void scrollToBottom(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }
    
    /**
     * Highlights the specified WebElement by changing its background color.
     * @param webElement The WebElement to highlight.
     */
    public void highlightElement(WebDriver driver,WebElement webElement) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String originalStyle = webElement.getAttribute("style");
        js.executeScript("arguments[0].setAttribute('style', 'background-color: yellow; border: 2px solid red;');", webElement);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        js.executeScript("arguments[0].setAttribute('style', '" + originalStyle + "');", webElement);
    }
    
    /**
     * Scrolls the page to make the specified WebElement visible in the viewport.
     * @param webElement The WebElement to be brought into view.
     */
    public void scrollToElement(WebDriver driver,WebElement webElement) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", webElement);
    }
    
    /**
     * Returns the current page title using JavaScript.
     * @return The page title.
     */
    public String getPageTitle(WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (String) js.executeScript("return document.title");
    }
}
