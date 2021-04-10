package com.test.utils.dataprovider;

import org.testng.annotations.DataProvider;

public class TestDataProviders {

	@DataProvider(name = "sampleTest")

	public static Object[][] sampleTest() throws Exception {

		ExcelUtils excelData = new ExcelUtils();
		return excelData.getTableArray("Sheet1");

	}
}
