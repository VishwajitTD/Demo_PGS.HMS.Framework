package pgh.hms.generic.genericUtility;


import java.io.IOException;


import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListenerImplementation implements ITestListener {

	ExtentReports reports;
	ExtentTest test;
	String methodName;

	@Override
	public void onTestStart(ITestResult result) {
		methodName = result.getMethod().getMethodName();
		test = reports.createTest(methodName);
		test.log(Status.INFO, "===test Script execution started === ");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		methodName = result.getMethod().getMethodName();
		test.log(Status.PASS, methodName + "===test case is === passed===");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		JavaUtility jLib=new JavaUtility();
		methodName = result.getMethod().getMethodName()+jLib.getDateWithTime();
		WebActionUtility wutil=new WebActionUtility();
		try {
			String path=wutil.screenshot(BaseClass.listenerDriver, methodName);
			test.addScreenCaptureFromPath(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
//		String path = ".\\ExtentReport\\screenshot";
//		String currentDate = jLib.getDateWithTime()+toString().replace(":", "_").replace(" ", "_");
//		TakesScreenshot takesScreenshot = (TakesScreenshot) BaseClass.listenerDriver;
//	
//		try {
//			FileUtils.copyFile(takesScreenshot.getScreenshotAs(OutputType.FILE), new File(path + methodName + currentDate + ".png"));
//		} catch (WebDriverException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		methodName = result.getMethod().getMethodName();
		test.log(Status.SKIP, methodName+"== skip ==");
		test.log(Status.INFO, methodName+result.getThrowable());
	}


	@Override
	public void onStart(ITestContext context) {
		JavaUtility jLib=new JavaUtility();
		String path = ".\\ExtentReport\\reports";
		String currentDate = jLib.getDateWithTime()+toString().replace(":", "_").replace(" ", "_");
		System.out.println("==Execution Started===");
		//configuration report
		
		ExtentSparkReporter htmlReporter= new ExtentSparkReporter(path + currentDate + ".html");
		htmlReporter.config().setDocumentTitle("base URL: http://rmgtestingserver/domain/Hospital_Management_System/");
//		htmlReporter.config().setDocumentTitle("Base Browser: Chrome, firefox ");
//		htmlReporter.config().setDocumentTitle("Base Report: extend, testNG ");
//		htmlReporter.config().setDocumentTitle("Reporter NAme: Vishwajit T D");
		htmlReporter.config().setTheme(Theme.DARK);
		reports=new ExtentReports();
		reports.attachReporter(htmlReporter);
		
		
	}

	@Override
	public void onFinish(ITestContext context) {
		
		
		System.out.println("Execution Finished");
		reports.flush();

	}



}
