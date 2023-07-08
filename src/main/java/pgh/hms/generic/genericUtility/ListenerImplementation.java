package pgh.hms.generic.genericUtility;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListenerImplementation implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {

	}

	@Override
	public void onTestSuccess(ITestResult result) {

	}

	@Override
	public void onTestFailure(ITestResult result) {
		JavaUtility jLib=new JavaUtility();
		String methodName = result.getMethod().getMethodName();
		String path = "./sceenshot/";
		String currentDate = jLib.getDateWithTime()+toString().replace(":", "_").replace(" ", "_");
		TakesScreenshot takesScreenshot = (TakesScreenshot) BaseClass.listenerDriver;
		File src = takesScreenshot.getScreenshotAs(OutputType.FILE);
		File dst = new File(path + methodName + currentDate + ".png");
		try {
			FileUtils.copyFile(src,dst);
		} catch (WebDriverException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
	}

	@Override
	public void onStart(ITestContext context) {
	}

	@Override
	public void onFinish(ITestContext context) {

	}

}
