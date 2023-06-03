package Framework_SampleTest;

import java.io.IOException;

import org.testng.annotations.Test;

import api.utilities.ExcelUtil;

public class testclass {

	@Test
	public void classforExcel() {
		String path="C:\\Users\\anoka\\git\\RestAssured202305\\testData\\UserData.xlsx";
		ExcelUtil util=new ExcelUtil(path);
		try {
			util.getCellData("Sheet1", 1, 1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
