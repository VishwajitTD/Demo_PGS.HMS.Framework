package practiseProgram;


import org.testng.annotations.Test;

import pgh.hms.generic.genericUtility.ExcelUtility;
import pgh.hms.generic.genericUtility.FileUtility;

public class ReadExcelData {

	@Test
	public void readTestData() throws Throwable {
		FileUtility fLib = new FileUtility();
		ExcelUtility eLab = new ExcelUtility();
		String E_path = fLib.getFilePathFromPropertiesFile("testDataFilePath");
		String doctor_Name = eLab.getDataFromExcelBasedTestId(E_path, "Doctor Details", "TC_01",
				"Doctor Name");
		String clinic_Address = eLab.getDataFromExcelBasedTestId(E_path, "Doctor Details", "TC_01",
				"Clinic Address");
		String doctor_Fees = eLab.getDataFromExcelBasedTestId(E_path, "Doctor Details", "TC_01",
				"Doctor Fees");
		String contact_Number = eLab.getDataFromExcelBasedTestId(E_path, "Doctor Details", "TC_01",
				"Contact Number");
		String doctor_Email = eLab.getDataFromExcelBasedTestId(E_path, "Doctor Details", "TC_01",
				"Doctor Email");
		String new_Password = eLab.getDataFromExcelBasedTestId(E_path, "Doctor Details", "TC_01",
				"New Password");		
		String confirm_Password = eLab.getDataFromExcelBasedTestId(E_path, "Doctor Details", "TC_01",
				"Confirm Password");

		System.out.println(doctor_Name);
		System.out.println(contact_Number);
		System.out.println(doctor_Email);
		System.out.println(clinic_Address);
		System.out.println(doctor_Fees);
		System.out.println(new_Password);
		System.out.println(confirm_Password);
		
	}
//
//		FileInputStream fis = null;
//		Properties pObj = null;
//		try {
//			// Open the properties file
//			fis = new FileInputStream(".\\config\\filePath.properties");
//			pObj = new Properties();
//
//			// Load the properties from the input stream
//			pObj.load(fis);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		// Get the value associated with the given key
//		String value = pObj.getProperty("testDataFilePath");
//		System.out.println(value);
//
//	}
//		
//		ExcelUtility eLab = new ExcelUtility();
//
//		// get the filePath
//		
//		
//		String E_path=IConstantUtility.excelpath;
//		System.out.println(E_path);
//		
		
//	}
}
