package testScreen;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Info {
	String name;
	String EPUS;
	String Type;
	String SN;
	String timeStamp = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
	

	public String getEPUS() {
		return EPUS;
	}

	public void setEPUS(String ePUS) {
		EPUS = ePUS;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public String getSN() {
		return SN;
	}

	public void setSN(String sN) {
		SN = sN;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	void create() throws IOException {
	XSSFWorkbook workbook = new XSSFWorkbook();
	CreationHelper createHelper = workbook.getCreationHelper();
	XSSFSheet sheet = workbook.createSheet("JavaBooks");
	
	Object[][] bookData = {
			{"Head First Java", "Kathy Serria", 79},
			{"Effective Java", "Joshua Bloch", 36},
			{"Clean Code", "Robert martin", 42},
			{"Thinking in Java", "Bruce Eckel", 35},
	};

	int rowCount = 0;
	
	for (Object[] aBook : bookData) {
		Row row = sheet.createRow(++rowCount);
		int columnCount = 0;
		Cell cell = row.createCell(++columnCount);
		cell.setCellValue(name);
				
		/*
		for (Object field : aBook) {
			Cell cell = row.createCell(++columnCount);
			cell = row.createCell(1);
			if (field instanceof String) {
				cell.setCellValue((String) field);
			} else if (field instanceof Integer) {
				cell.setCellValue((Integer) field);
			}
		}*/
		
	}
	
	
	try (FileOutputStream outputStream = new FileOutputStream("JavaBooks.xlsx")) {
		workbook.write(outputStream);
	}
	}
void modify() throws IOException, EncryptedDocumentException, InvalidFormatException {
    Workbook workbook = WorkbookFactory.create(new File("123.xlsx"));
    
	CreationHelper createHelper = workbook.getCreationHelper();
	
	Sheet sheet = workbook.getSheetAt(0);
	doSomething(0, 2, sheet, timeStamp, 1);
	doSomething(1, 2, sheet, SN, 0);
	doSomething(2, 2, sheet, name, 0);
	doSomething(3, 7, sheet, EPUS, 0);
    // Write the output to the file
    FileOutputStream fileOut = new FileOutputStream(SN + ".xlsx");
    workbook.write(fileOut);
    fileOut.close();
	}
void modify2() throws IOException, EncryptedDocumentException, InvalidFormatException {
    Workbook workbook = WorkbookFactory.create(new File("Epus.xlsx"));
    
	CreationHelper createHelper = workbook.getCreationHelper();
	
	Sheet sheet = workbook.getSheetAt(0);
	// Get Row at index 1
    doSomething(1, 2, sheet, "1",0);
    // Write the output to the file
    FileOutputStream fileOut = new FileOutputStream("Relações Epus.xlsx");
    workbook.write(fileOut);
    fileOut.close();
	}
void doSomething(int rownum, int cellnum, Sheet sheet, String content, int type) {
	Row row = sheet.getRow(rownum);
    
    // Get the Cell at index 2 from the above row
    Cell cell = row.getCell(cellnum);

    // Create the cell if it doesn't exist
    if (cell == null) {
    cell = row.createCell(cellnum);
    }
    // Update the cell's value
    if(type == 0) {
    	cell.setCellType(CellType.NUMERIC);
    	cell.setCellValue(Integer.valueOf(content));
    }
    if(type == 1) {
    	cell.setCellType(CellType.STRING);
    	cell.setCellValue(content);
    }
    if(type == 2) {
    	cell.setCellType(CellType.FORMULA);
    	cell.setCellValue(content);
    }
    if(type == 3) {
    	cell.setCellType(CellType.BOOLEAN);
    	cell.setCellValue(content);
    }
    

	
	
}
}
