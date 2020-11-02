package com.tyss.acttime.dataproviders;

import org.testng.annotations.DataProvider;

import com.tyss.acttime.baseutil.BaseTest;
import com.tyss.acttime.util.commonutils.ExcelUtil;

/**
 * Description : Data Provider to fetch the data from Excel workbook.
 * 
 * @author Sajal jain
 */
public class SnapDealDataProvider {
	/**
	 * Description : Data Provider to fetch data from Excel file according to
	 * specified sheet name.
	 * 
	 * @author Sajal jain
	 */
	@DataProvider(name = "TestDataProvider")
	public static Object[][] getTestData() {
		int noofrows = ExcelUtil.getRowCount(BaseTest.EXCELPATH, "SnapDealData");
		int noofcols = ExcelUtil.getColoumCount(BaseTest.EXCELPATH, "SnapDealData");
		Object[][] workflowdata = new Object[noofrows][noofcols];

		for (int i = 1; i <= noofrows; i++) {
			String data[] = ExcelUtil.getRowData(BaseTest.EXCELPATH, "SnapDealData", i);

			for (int j = 0; j < data.length; j++) {

				workflowdata[i - 1][j] = data[j];
			}

		}
		return workflowdata;

	}
}
