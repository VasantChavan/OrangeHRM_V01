package com.orangeHrm.utility;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataProvider {

	public FileInputStream fins = null;
	public XSSFWorkbook workbook = null;
	public XSSFSheet sheet =null;

	public ExcelDataProvider(String sheetname) {

		try {
			File fs = new File(".//TestData//orangeHRMTestData.xlsx");
			fins = new FileInputStream(fs);
			workbook = new XSSFWorkbook(fins);
			sheet = workbook.getSheet(sheetname);

		} catch (Exception e) {
			System.out.println("excelk sheet not found :"+e.getMessage());
		}

	}
	
	public int rowCount(){		
		return sheet.getLastRowNum();		
	}
	
	public int colsCount(int row){		
		return sheet.getRow(row).getLastCellNum();		
	}
	
	public String getCellData(int row, int col){		
		return sheet.getRow(row).getCell(col).toString();
	}
	
	public Object[][] getCellData(){
		
		int rows=rowCount();
		int cols= colsCount(0);
		
		Object data[][]= new Object[rows][cols];
		
		for(int i=1; i<=rows;i++ ){			
			for(int j=0; j<cols; j++){				
				data[i-1][j]=sheet.getRow(i).getCell(j).toString();
			}
		}
		
		return data;
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
