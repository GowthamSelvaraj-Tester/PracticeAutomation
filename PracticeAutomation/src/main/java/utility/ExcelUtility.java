package utility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	//Imported the Required Dependices to read the excel file 
		private XSSFWorkbook excelworkBook;
		private XSSFSheet excelSheet;
		private XSSFCell cell;
		private XSSFRow row;
			
		//Method to set the Excel file location and sheet name.
		public void setExcelFile(String sheetPath, String sheetName) throws Exception  {
			try {
				FileInputStream inputStream = new FileInputStream(sheetPath);// FileInputStream to read the Excel file
			    excelworkBook = new XSSFWorkbook(inputStream);// Initialize the workbook with the file input stream
			    excelSheet = excelworkBook.getSheet(sheetName.trim());// Get the sheet by name, trimming any extra spaces
			    if (excelSheet == null) {
			    	throw new Exception("Sheet not found: " + sheetName);// Throw an exception if the sheet is not found
			    }
			} catch (Exception e) {
				e.printStackTrace();// Print the stack trace if an exception occurs
			}
		}

				//Method to find out the exact row for the test case 
		private int getTestCaseRow(String testCaseName, int testCaseColumn) throws Exception {
			// Check whether the sheet has been loaded
			if (excelSheet == null) {
				throw new Exception("Excel sheet not loaded. Please call setExcelFile first.");// Throw an exception if the sheet is not loaded
			}
			int row = 0;// Initialize row index to 0
			try {
				int rowCount = excelSheet.getLastRowNum();// Get the last row number
			    for (row = 0; row <= rowCount; row++) {
			    	// Check each row in the specified column for the test case name
			        if (getCellData(row, testCaseColumn).equalsIgnoreCase(testCaseName)) {
			        	break;// Break the loop if the test case name is found
			        }
			    }
			} catch (Exception e) {
				e.printStackTrace();// Print the stack trace if an exception occurs
			}
			return row;// Return the row index where the test case name is found
		}
			    
		// Method to get cell data
		public String getCellData(int rowNumb, int colNumb) throws Exception {
			try {
				//Check the exact cell
				cell = excelSheet.getRow(rowNumb).getCell(colNumb);
				// Check the value of the Cell Type and convert into String if it's numeric
				if(cell.getCellType() == CellType.NUMERIC) {
					cell.setCellType(CellType.STRING);
				}
				String cellData = cell.getStringCellValue();// Get the string value of the cell
				return cellData;// Return the cell data
			} catch(Exception e) {
				e.printStackTrace();// Print the stack trace if an exception occurs
				return " ";// Return empty string in case of an exception
			}
		}
		
		// Method to set cell data
		public void setCellData(String result,int rowNumb, int colNumb, String excelFilePath) throws Exception {
			try {
				//Retrieves the specified row and cell.
				row = excelSheet.getRow(rowNumb);
				cell = row.getCell(colNumb);
								
				//Creates the cell if it doesn't exist or updates it if it does.
				if(cell == null) {
					cell = row.createCell(colNumb);
					cell.setCellValue(result);
				} else {
					cell.setCellValue(result);
				}
				FileOutputStream output = new FileOutputStream(excelFilePath);
				//Writes the updated workbook
				excelworkBook.write(output);
				output.flush();
				output.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
			
		//Method to fetch the data from the excel and store it in the Map<Object, Key> Method
		public Map getdata(String testCaseName, String sheetPath, String sheetName) throws Exception {
			// Create a new HashMap to store the data from the Excel sheet
			Map dataMap= new HashMap<String, String>();
			try {
				// Set the Excel file to read from
				setExcelFile(sheetPath,sheetName);
				// Find the row number of the test case
				int dataRow = getTestCaseRow(testCaseName.trim(),0);
				// Get the number of columns in the row
				int columnCount = excelSheet.getRow(dataRow).getLastCellNum();
				// Iterate over each column in the row
				for(int i=0; i < columnCount ; i++) {
					String cellData = null;
					cell = excelSheet.getRow(dataRow).getCell(i);
					// If the cell contains numeric data, convert it to a string
					if(cell!= null && cell.getCellType() == CellType.NUMERIC) {
						cell.setCellType(CellType.STRING);
					} 
					// If the cell is not null, get the cell's string value
					if(cell!=null) {
						cellData = cell.getStringCellValue();
					}
					// Store the header from the first row as the key and the cell data as the value in the map
					dataMap.put(excelSheet.getRow(0).getCell(i).getStringCellValue(),cellData);	
				}	
			} catch(Exception e) {
				e.printStackTrace();// Print the stack trace if an exception occurs
				}
			// Return the map containing the data
			return dataMap ;
		}

}
