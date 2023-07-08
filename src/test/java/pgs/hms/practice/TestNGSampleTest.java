package pgs.hms.practice;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * /dependsOnGroups, inheritGroups, onlyForGroups, invocationTimeOut,successPercentage,singleThreaded,timeOut,threadPoolSize,not used
 * 
 * @author jeet
 */
 
public class TestNGSampleTest {
@BeforeSuite
public void beforeSuite() {
	System.out.println("beforeSuite is executed");
}
@BeforeTest
public void beforeTest() {
	System.out.println("beforeTest is executed");
}
@BeforeClass
public void beforeClass() {
	System.out.println("beforeClass is executed");
}
@BeforeMethod
public void beforeMethod1() {
	System.out.println("beforeMethod1 is executed");
}
@BeforeMethod
public void beforeMethod2() {
	System.out.println("beforeMethod2 is executed");
}
@Test(priority = 1,invocationCount = 1)
public void test1() {
	Assert.fail();
	System.out.println("###Mumbai");
}
@Test(priority = -2,invocationCount = 3, enabled = true)
public void test2() {
	System.out.println("###Delhi");
}
@Test(priority = 0, dependsOnMethods = {"test1","test2"},alwaysRun = true)
public void test3() {
	System.out.println("###Bangaluru");
}
@AfterSuite
public void afterSuite() {
	System.out.println("afterSuite is executed");
}
@AfterTest
public void afterTest() {
	System.out.println("afterTest is executed");
}
@AfterClass
public void afterClass1() {
	System.out.println("afterClass1 is executed");
}
@AfterClass
public void afterClass2() {
	System.out.println("afterClass2 is executed");
}
@AfterMethod
public void afterMethod() {
	System.out.println("afterMethod is executed");
	}
@Test(groups = { "test1", "test2" })
public void testMethod1() {
}

@Test(groups = {"test1", "test3"} )
public void testMethod2() {
}

@Test(groups = { "test2" })
public void testMethod3() {
}
}


