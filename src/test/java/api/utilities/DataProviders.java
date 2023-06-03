package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name="data")
	public String[][] getAllData() throws IOException {
		String path=System.getProperty("user.dir")+"\\testData\\UserData.xlsx";
		System.out.println(path);
		ExcelUtil xl=new ExcelUtil(path);
		int rowCount=xl.getRowCount("Sheet1");
		int colCount=xl.getCellCount("Sheet1", 1);
		
		String[][] apiData=new String[rowCount][colCount];
		
		for(int i=1;i<=rowCount;i++) {
			for(int j=0;j<colCount;j++) {
				apiData[i-1][j]=xl.getCellData("Sheet1", i, j);
			}
		}
		return apiData;	
	}
	
	@DataProvider(name="userNames")
	public String[] getUserNames() throws IOException {
		String path=System.getProperty("user.dir")+"\\testData\\UserData.xlsx";
		System.out.println(path);
		ExcelUtil xl=new ExcelUtil(path);
		int rowCount=xl.getRowCount("Sheet1");
		
		String[] apiData=new String[rowCount];
		for(int i=1;i<=rowCount;i++) {
			apiData[i-1]=xl.getCellData("Sheet1", i, 1);
		}
		
		return apiData;
	}

}
