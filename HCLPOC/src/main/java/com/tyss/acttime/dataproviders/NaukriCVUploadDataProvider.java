package com.tyss.acttime.dataproviders;

import org.testng.annotations.DataProvider;

import com.tyss.acttime.baseutil.BaseTest;
import com.tyss.acttime.util.commonutils.ExcelUtil;
/**
 * Description : Data Provider to fetch the data from Excel workbook.
 * 
 * @author Sajal jain
 */
public class NaukriCVUploadDataProvider {
		@DataProvider(name = "TestDataProvider")
		public static Object[][] getTestData() {
			int noofrows = ExcelUtil.getRowCount(BaseTest.EXCELPATH, "NaukriCVUpload");
			int noofcols = ExcelUtil.getColoumCount(BaseTest.EXCELPATH, "NaukriCVUpload");
			Object[][] workflowdata = new Object[noofrows][noofcols];

			for (int i = 1; i <= noofrows; i++) {
				String data[] = ExcelUtil.getRowData(BaseTest.EXCELPATH, "NaukriCVUpload", i);

				for (int j = 0; j < data.length; j++) {

					workflowdata[i - 1][j] = data[j];
				}

			}
			return workflowdata;

		}
}
