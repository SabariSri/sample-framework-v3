package com.test.utils.dataprovider;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;
	private static XSSFCell Cell;

	public static boolean emptyCell = true;

	public Object[][] getTableArray(String SheetName) throws Exception {

		try {

			FileInputStream ExcelFile = new FileInputStream(testDataExcelBookPath);
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(SheetName);

			int totalRows = ExcelWSheet.getLastRowNum();

			Object[][] dataObject = new Object[totalRows][1];

			int colNumber = 0;
			ArrayList<String> dataKeys = new ArrayList<String>();
			while (emptyCell) {
				String returnValue = getCellData(0, colNumber);
				if (!(returnValue == null || returnValue == "")) {
					dataKeys.add(returnValue);
				}
				colNumber++;
			}

			int startRow = 1;
			while (!isRowEmpty(startRow)) {
				HashMap<String, String> data = new HashMap<String, String>();
				for (String key : dataKeys) {
					int col = getKeyColNumber(dataKeys, key);
					String value = getCellData(startRow, col);
					data.put(key, value);
					System.out.println("Row : " + startRow + ", Key : " + key + ", Value : " + value);
				}
				dataObject[startRow - 1][0] = data;

				startRow++;
			}
			return dataObject;
		} catch (FileNotFoundException e) {
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
			return null;
		}

	}

	@SuppressWarnings({ "static-access", "deprecation" })
	public String getCellData(int RowNum, int ColNum) throws Exception {

		try {

			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);

			XSSFRow row = ExcelWSheet.getRow(RowNum);

			XSSFCell c = row.getCell(RowNum);
			if (c == null || c.getCellType() == Cell.CELL_TYPE_BLANK || c.toString().equals("")) {
				emptyCell = false;
				return "";
			} else {
				String CellData = Cell.getStringCellValue();
				return CellData;
			}
		} catch (Exception e) {
			emptyCell = false;
			return "";
		}
	}

	public int getKeyColNumber(ArrayList<String> header, String headerValue) throws Exception {

		try {
			for (int col = 0; col < header.size(); col++) {
				Cell = ExcelWSheet.getRow(0).getCell(col);
				String CellData = Cell.getStringCellValue();
				if (CellData.equals(headerValue)) {
					return col;
				}
			}
			return 0;
		} catch (Exception e) {
			return 0;
		}
	}

	public boolean isRowEmpty(int row) throws Exception {

		try {
			Cell = ExcelWSheet.getRow(row).getCell(0);
			if (Cell.getStringCellValue() == "") {
				return true;
			}
			return false;

		} catch (Exception e) {
			return true;
		}
	}

	private static String testDataExcelBookPath = System.getProperty("user.dir") + "/DataResource/TestData.xlsx";

	public static void main(String[] a) throws Exception {

		ExcelUtils excelReader = new ExcelUtils();
		excelReader.getTableArray("Sheet1");
	}
}