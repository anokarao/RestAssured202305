package api.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;
	String path;
	
	public ExcelUtil(String path) {
		this.path=path;
	}
	
	public int getRowCount(String sheetName) throws IOException {
		fi=new FileInputStream(sheetName);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		int rowCount=sheet.getLastRowNum();
		workbook.close();
		fi.close();
		return rowCount;
		
	}
	public int getCellCount(String sheetName,int rowNum) throws IOException {
		fi=new FileInputStream(sheetName);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(rowNum);
		int cellCount=row.getLastCellNum();
		workbook.close();
		fi.close();
		return cellCount;
	}
	
	public String getCellData(String sheetName,int rowNum,int colNum) throws IOException {
		try {
		fi=new FileInputStream(sheetName);
		
			workbook=new XSSFWorkbook(fi);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(rowNum);
		cell=row.getCell(colNum);
		String data;
		
		DataFormatter formatter=new DataFormatter();
		try {
			data=formatter.formatCellValue(cell);
		}
		catch(Exception e) {
			data="";
		}
		
		workbook.close();
		fi.close();
		return data;
		
		
		
	}
	public void setCellData(String sheetName,int rownum,int colnum,String data) throws IOException {
		File xlfile=new File(path);
		if(!xlfile.exists())    // If file not exists then create new file
		{
		workbook=new XSSFWorkbook();
		fo=new FileOutputStream(path);
		workbook.write(fo);
		}
				
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
			
		if(workbook.getSheetIndex(sheetName)==-1) // If sheet not exists then create new Sheet
			workbook.createSheet(sheetName);
		sheet=workbook.getSheet(sheetName);
					
		if(sheet.getRow(rownum)==null)   // If row not exists then create new Row
				sheet.createRow(rownum);
		row=sheet.getRow(rownum);
		
		cell=row.createCell(colnum);
		cell.setCellValue(data);
		fo=new FileOutputStream(path);
		workbook.write(fo);		
		workbook.close();
		fi.close();
		fo.close();
	}
}
