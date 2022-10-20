import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelEditor {
	private Workbook workbook;
	private String fileLocation;
	public ExcelEditor(String fileLocation) throws IOException {
		this.fileLocation = fileLocation;
		FileInputStream file = new FileInputStream(new File(fileLocation));
		workbook = new XSSFWorkbook(file);
	}
	
	//retrieve values from a single column
	//WF's Note: i actually don't use this anymore, but i put it here in case yall need it
	public ArrayList<String> getColumn(int sheetNumber, int columnNumber) throws IOException{
		
		Sheet sheet = workbook.getSheetAt(sheetNumber);
		
		
		ArrayList<String> list = new ArrayList<String>();
		
		for(Row row: sheet) { //for list
			Cell c = row.getCell(columnNumber);
			
			switch(c.getCellType()) {
				case STRING:
					list.add(c.getRichStringCellValue().getString());
					break;
				default:
					list.add(" ");
			}
			
		}
		
		return list;
	}

	//Get the excel content in HashMap, row by row by sheetnumber
	public Map<Integer, ArrayList<String>> getData(int sheetNumber) throws IOException{ 
		Sheet sheet = workbook.getSheetAt(sheetNumber);
		Map<Integer, ArrayList<String>> data = new HashMap<>();
		int i = 0;
		for(Row row: sheet) {
			data.put(i,  new ArrayList<String>());
			for (Cell cell : row) {
				switch(cell.getCellType()) {
				
					case STRING:
						data.get(i).add(cell.getRichStringCellValue().getString());
						break;
					case NUMERIC:
						if(DateUtil.isCellDateFormatted(cell)) {
							data.get(i).add(cell.getDateCellValue() +"");
						} else {
							data.get(i).add(cell.getNumericCellValue() + "");
						}
						break;
					case BOOLEAN:
						data.get(i).add(cell.getBooleanCellValue() + "");
						break;
					default:
						data.get(i).add(" ");
				}
			}	
			i++;
		}
		
		return data;
	}
	
	public void createRow(ArrayList<String> input, int sheetNumber) throws IOException {
		Sheet sheet = workbook.getSheetAt(sheetNumber);
		
		int rowCount = sheet.getLastRowNum();
		Row row = sheet.createRow(++rowCount);
		
		int columnCount = 0;
		Cell cell;
		for(int i = 0; i < input.size(); i++) {
			cell = row.createCell(columnCount++);
			cell.setCellValue(input.get(i));
			
		}
		
		FileOutputStream outputStream = new FileOutputStream(fileLocation);
		workbook.write(outputStream);
		outputStream.close();
	}
	
}
