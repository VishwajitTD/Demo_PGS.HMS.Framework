package pgh.hms.generic.genericUtility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * By using this Utility, you can read and write Excel data.
 * 
 * Author: jeet
 */

public class ExcelUtility {
	/**
	 * 
	 * @param filePath
	 * @param sheetName
	 * @param rowNum
	 * @param cellNum
	 * @return String
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
	 * This Method will provide data from escel based on its test case ID
	 * 
	 * @param filePath
	 * @param sheetName
	 * @param testId
	 * @param columnHeader
	 * @return String
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
					// Get the test ID value from the first cell in the row
					actTestID = sheet.getRow(i).getCell(0).toString();
				} catch (Exception e) {
				}

				if (actTestID.equalsIgnoreCase(testId)) {
					break;
				}
				testRowNum++;
			}

			int testColNum = 0;
			String actualColumnHeader = "";
			int celCountforTest = sheet.getRow(testRowNum - 1).getLastCellNum();

			// Iterate over the cells in the test row to find the matching column header
			for (int j = 0; j < celCountforTest; j++) {
				try {
					// Get the column header value from the cell
					actualColumnHeader = sheet.getRow(testRowNum - 1).getCell(j).toString();
				} catch (Exception e) {
				}
				if (actualColumnHeader.equalsIgnoreCase(columnHeader)) {
					break;
				}
				testColNum++;
			}

			try {
				// Get the data value from the cell at the matching row and column
				data = sheet.getRow(testRowNum).getCell(testColNum).toString();
			} catch (Exception e) {
			}

			// Return the extracted data
			return data;
		} catch (EncryptedDocumentException | IOException e) {
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
	 * 
	 * @param filePath
	 * @param sheetName
	 * @return int
	 */
	public int getRowCount(String filePath, String sheetName) {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(filePath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Workbook workbook = null;
		try {
			workbook = WorkbookFactory.create(fis);
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Sheet sheet = workbook.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum();
		return rowCount;
	}

	/**
	 * 
	 * @param filePath
	 * @param sheetName
	 * @param searchColumnHeader
	 * @param searchValue
	 * @param targetColumnHeader
	 * @param data
	 */
	public void setDataToExcel(String filePath, String sheetName, String searchColumnHeader, String searchValue,
			String targetColumnHeader, String data) {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream(filePath);
			Workbook workbook = WorkbookFactory.create(fis);
			Sheet sheet = workbook.getSheet(sheetName);

			// Step 1: Find the column index of the search column header
			Row headerRow = sheet.getRow(0);
			int searchColumnIndex = -1;
			int targetColumnIndex = -1;

			for (int i = 0; i < headerRow.getLastCellNum(); i++) {
				Cell headerCell = headerRow.getCell(i);
				if (headerCell != null) {
					String columnHeader = headerCell.toString().trim();
					if (columnHeader.equalsIgnoreCase(searchColumnHeader)) {
						searchColumnIndex = i;
					} else if (columnHeader.equalsIgnoreCase(targetColumnHeader)) {
						targetColumnIndex = i;
					}
				}
			}

			if (searchColumnIndex != -1 && targetColumnIndex != -1) {
				// Step 2: Find the row index of the specified search value
				int rowCount = sheet.getLastRowNum();
				int targetRowIndex = -1;
				for (int i = 1; i <= rowCount; i++) { // Start from 1 to skip the header row
					Row currentRow = sheet.getRow(i);
					if (currentRow != null) {
						Cell searchCell = currentRow.getCell(searchColumnIndex);
						if (searchCell != null && searchCell.toString().trim().equalsIgnoreCase(searchValue)) {
							targetRowIndex = i;
							break;
						}
					}
				}

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

			fos = new FileOutputStream(filePath);
			workbook.write(fos);
			workbook.close();
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			// Close the resources in the finally block
			try {
				if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if (fos != null) {
					fos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * @param excelPath
	 * @param sheet
	 * @return
	 */

	/**
	 * Reads doctor data from the specified Excel file and sheet. Returns a 2D array
	 * of objects containing the data. Each row in the Excel sheet represents a
	 * doctor's data, starting from the second row. Each column in the Excel sheet
	 * represents a data field for the doctor. The method handles different cell
	 * types (String, Numeric, Boolean) and empty cells. Returns null if the file or
	 * sheet is not found or an error occurs during reading.
	 *
	 * @param excelPath the path to the Excel file
	 * @param sheetName the name of the sheet in the Excel file
	 * @return a 2D array of objects containing the doctor data, or null if an error
	 *         occurs
	 */
	public Object[][] getDataForDataProvider(String excelPath, String sheetName) {
		// Initialize variables
		Object[][] data = null; // Initialize the 2D array to store the data
		FileInputStream fis = null; // Input stream to read the Excel file

		try {
			// Open the Excel file
			fis = new FileInputStream(excelPath);
			Workbook workbook = WorkbookFactory.create(fis);
			Sheet sheet = workbook.getSheet(sheetName);

			// Get the last row number and last cell number in the sheet
			int lastRow = sheet.getLastRowNum();
			int lastCell = sheet.getRow(0).getLastCellNum();

			// Create the data array with appropriate dimensions
			data = new Object[lastRow][lastCell];

			// Iterate over each row in the sheet, starting from the second row (index 1)
			for (int i = 1; i <= lastRow; i++) {
				Row row = sheet.getRow(i);
				// Iterate over each cell in the row
				for (int j = 0; j < lastCell; j++) {
					Cell cell = row.getCell(j);
					if (cell != null) {
						CellType cellType = cell.getCellType();
						// Check the cell type and retrieve the value accordingly
						if (cellType == CellType.STRING) {
							data[i - 1][j] = cell.getStringCellValue();
						} else if (cellType == CellType.NUMERIC) {
							data[i - 1][j] = cell.getNumericCellValue();
						} else if (cellType == CellType.BOOLEAN) {
							data[i - 1][j] = cell.getBooleanCellValue();
						} else if (cellType == CellType.BLANK) {
							data[i - 1][j] = "";
						}
					} else {
						data[i - 1][j] = "";
					}
				}
			}

			workbook.close(); // Close the workbook
		} catch (FileNotFoundException e) {
			e.printStackTrace(); // Handle file not found exception
		} catch (IOException e) {
			e.printStackTrace(); // Handle IO exception
		} catch (EncryptedDocumentException e) {
			e.printStackTrace(); // Handle encrypted document exception
		} finally {
			try {
				if (fis != null) {
					fis.close(); // Close the input stream
				}
			} catch (IOException e) {
				e.printStackTrace(); // Handle IO exception during stream closing
			}
		}

		return data; // Return the extracted data or null if an error occurred
	}

}
