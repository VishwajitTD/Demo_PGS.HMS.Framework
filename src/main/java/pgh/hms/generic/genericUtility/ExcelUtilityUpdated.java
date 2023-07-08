package pgh.hms.generic.genericUtility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * By using this Utility, you can read and write Excel data.
 * 
 * Author: jeet
 */
public class ExcelUtilityUpdated {

	/**
	 * Retrieves data from Excel based on the provided file path, sheet name, row
	 * number, and cell number.
	 *
	 * @param filePath  The path of the Excel file.
	 * @param sheetName The name of the sheet in the Excel file.
	 * @param rowNum    The row number in the sheet.
	 * @param cellNum   The cell number in the row.
	 * @return The retrieved data as a String.
	 */
	public String getDataFromExcel(String filePath, String sheetName, int rowNum, int cellNum) {
		FileInputStream fis = null;
		Workbook workbook = null;
		try {
			// Open the Excel file
			fis = new FileInputStream(filePath);
			// Create a workbook instance
			workbook = WorkbookFactory.create(fis);
			// Get the specified sheet from the workbook
			Sheet sheet = workbook.getSheet(sheetName);
			// Get the row at the specified index
			Row row = sheet.getRow(rowNum);
			// Get the cell value at the specified index and convert it to a string
			String data = row.getCell(cellNum).toString();
			// Return the extracted data
			return data;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// Close the workbook and input stream in the finally block to ensure they are
				// always closed
				if (workbook != null) {
					workbook.close();
				}
				if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null; // or any appropriate value to indicate an error occurred
	}

	/**
	 * Retrieves data from Excel based on the provided file path, sheet name, test
	 * ID, and column header.
	 *
	 * @param filePath     The path of the Excel file.
	 * @param sheetName    The name of the sheet in the Excel file.
	 * @param testId       The test ID to search for.
	 * @param columnHeader The column header to search for.
	 * @return The retrieved data as a String.
	 */
	public String getDataFromExcelBasedTestId(String filePath, String sheetName, String testId, String columnHeader) {
		FileInputStream fis = null;
		Workbook workbook = null;
		try {
			// Open the Excel file
			fis = new FileInputStream(filePath);
			// Create a workbook instance
			workbook = WorkbookFactory.create(fis);
			// Get the specified sheet from the workbook
			Sheet sheet = workbook.getSheet(sheetName);
			// Get the last row index in the sheet
			int rowCount = sheet.getLastRowNum();
			int testRowNum = 0;
			String actTestID = "";
			String data = "";

			// Iterate over the rows to find the matching test ID
			for (int i = 0; i <= rowCount; i++) {
				try {
					// Get the row at the current index
					Row row = sheet.getRow(i);
					// Get the value in the first cell of the row
					Cell cell = row.getCell(0);
					// Convert the cell value to a string
					actTestID = cell.toString();
				} catch (NullPointerException e) {
					// Skip the row if it is empty or null
					continue;
				}
				// If the test ID matches the provided test ID, store the row number and exit
				// the loop
				if (actTestID.equalsIgnoreCase(testId)) {
					testRowNum = i;
					break;
				}
			}

			// If a matching test ID is found, retrieve the data from the specified column
			if (testRowNum > 0) {
				// Get the row at the matching row number
				Row row = sheet.getRow(testRowNum);
				// Get the last cell index in the row
				int lastCellNum = row.getLastCellNum();
				int columnNum = -1;

				// Iterate over the cells to find the matching column header
				for (int j = 0; j < lastCellNum; j++) {
					// Get the cell value at the current index and convert it to a string
					String header = row.getCell(j).toString();
					// If the column header matches the provided column header, store the column
					// number and exit the loop
					if (header.equalsIgnoreCase(columnHeader)) {
						columnNum = j;
						break;
					}
				}

				// If a matching column header is found, retrieve the data from the specified
				// cell
				if (columnNum >= 0) {
					// Get the cell at the matching row and column
					Cell cell = row.getCell(columnNum);
					// Get the cell value as a string
					data = cell.toString();
				}
			}

			// Return the extracted data
			return data;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// Close the workbook and input stream in the finally block to ensure they are
				// always closed
				if (workbook != null) {
					workbook.close();
				}
				if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null; // or any appropriate value to indicate an error occurred
	}

	/**
	 * Reads doctor data from Excel based on the provided Excel path and test ID.
	 *
	 * @param excelPath The path of the Excel file.
	 * @param testId    The test ID to search for.
	 */
	/**
	 * Reads doctor data from Excel based on the provided Excel path and test ID.
	 *
	 * @param excelPath The path of the Excel file.
	 * @param testId    The test ID to search for.
	 * @return An array of strings containing the retrieved data.
	 */
	public String[] readDoctorDataFromExcel(String excelPath, String testId) {
		String[] dataFields = { "Doctor Name", "Clinic Address", "Doctor Fees", "Contact Number", "Doctor Email",
				"New Password", "Confirm Password" };
		String[] dataValues = new String[dataFields.length];

		for (int i = 0; i < dataFields.length; i++) {
			dataValues[i] = getDataFromExcelBasedTestId(excelPath, "Doctor Details", testId, dataFields[i]);
		}

		return dataValues;
	}

	/**
	 * Reads doctor data from Excel based on the provided Excel path and test ID.
	 *
	 * @param excelPath The path of the Excel file.
	 * @param testId    The test ID to search for.
	 */
	public void setDataToExcel(String filePath, String sheetName, String searchColumnHeader, String searchValue,
			String targetColumnHeader, String data) {
		try {
			FileInputStream fis = new FileInputStream(filePath);
			Workbook workbook = WorkbookFactory.create(fis);
			Sheet sheet = workbook.getSheet(sheetName);

			// Step 1: Find the column index of the search column header and target column
			// header
			Row headerRow = sheet.getRow(0);
			int searchColumnIndex = getColumnIndex(headerRow, searchColumnHeader);
			int targetColumnIndex = getColumnIndex(headerRow, targetColumnHeader);

			if (searchColumnIndex != -1 && targetColumnIndex != -1) {
				// Step 2: Find the row index of the specified search value
				int targetRowIndex = getRowIndex(sheet, searchColumnIndex, searchValue);

				if (targetRowIndex != -1) {
					Row row = sheet.getRow(targetRowIndex);
					Cell cell = row.getCell(targetColumnIndex);
					if (cell == null) {
						cell = row.createCell(targetColumnIndex);
					}
					cell.setCellValue(data);
				} else {
					System.out.println("Search value not found in the sheet.");
				}
			} else {
				System.out.println("Search column header or target column header not found in the sheet.");
			}

			FileOutputStream fos = new FileOutputStream(filePath);
			workbook.write(fos);
			workbook.close();
			fis.close();
			fos.close();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	private int getColumnIndex(Row headerRow, String columnHeader) {
		for (int i = 0; i < headerRow.getLastCellNum(); i++) {
			Cell headerCell = headerRow.getCell(i);
			if (headerCell != null) {
				String headerValue = headerCell.toString().trim();
				if (headerValue.equalsIgnoreCase(columnHeader)) {
					return i;
				}
			}
		}
		return -1; // Column header not found
	}

	private int getRowIndex(Sheet sheet, int searchColumnIndex, String searchValue) {
		int rowCount = sheet.getLastRowNum();
		for (int i = 1; i <= rowCount; i++) { // Start from 1 to skip the header row
			Row currentRow = sheet.getRow(i);
			if (currentRow != null) {
				Cell searchCell = currentRow.getCell(searchColumnIndex);
				if (searchCell != null && searchCell.toString().trim().equalsIgnoreCase(searchValue)) {
					return i;
				}
			}
		}
		return -1; // Search value not found
	}

}
