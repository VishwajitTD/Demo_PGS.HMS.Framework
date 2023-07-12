package pgs.hms.practice;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProviderSample {

	// This test method declares that its data should be supplied by the Data
	// Provider
	// named "test1"
	@Test(dataProvider = "test1")
	public void verifyData1(String n1, Integer n2) {
		System.out.println(n1 + " " + n2);
	}

	
	@DataProvider(name = "test1")
	public Object[][] createData2() {
	    Object[][] dObj2 = new Object[][] {
	        { "Jeet", 69 },
	        { "Rahul", 143 },
	    };

	    return dObj2;
	    //Hi i am conflict
	}

	
	

}
