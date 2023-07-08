package practiseProgram;

import pgh.hms.generic.genericUtility.ExcelUtilityUpdated;
import pgh.hms.generic.genericUtility.FileUtility;

//import pgh.hms.generic.genericUtility.FileUtility;

public class SampleToWriteDataIntoExcel {
    public static void main(String[] args) {


		FileUtility fLib = new FileUtility();
		ExcelUtilityUpdated exLab = new ExcelUtilityUpdated();
		String excelStorageFilePath = fLib.getFilePathFromPropertiesFile("testDataStorageFilePath");
		
		//Doctor Name	Clinic Address	Doctor Fees	Contact Number	Doctor Email	New Password	Confirm Password
		//setDataToExcel(String filePath, String sheetName, String searchColumnHeader, String searchValue,String targetColumnHeader, String data)
		String data="Abhishek@gmail.com";
		
		exLab.setDataToExcel(excelStorageFilePath, "Doctor Details", "TC_ID", "TC_02", "Doctor Name",data );

    }
}
