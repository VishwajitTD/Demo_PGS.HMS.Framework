package practiseProgram;
import pgh.hms.generic.genericUtility.ExcelUtility;

public class SampleCodeToGetFilePath {

    public static void main(String[] args) {
        // Create an instance of ExcelUtility
        ExcelUtility eLib = new ExcelUtility();

        // Call the getDataFromExcelBasedTestId method to retrieve data from the Excel file
        // Provide the file path, sheet name, test ID, and column header as arguments
        String data = eLib.getDataFromExcelBasedTestId("./testData/testData.xlsx", "exampleFOrMyProject1",
                "exampleFOrMyProject2", "exampleFOrMyProject3");

        // Print the retrieved data
        System.out.println(data);
    }

}
