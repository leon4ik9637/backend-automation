package apache_poi_excel;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class WritingToExcelFile {
    public static void main(String[] args) throws IOException {

        String excelFilePath = "test_data/WritingData.xlsx";

        // Creating an object
        XSSFWorkbook workbook = new XSSFWorkbook();

        // Creating the new sheet
        XSSFSheet sheet = workbook.createSheet("Sheet2");

        // Creating a row in the sheet
        XSSFRow row = sheet.createRow(1);

        // Create a cell in the row
        XSSFCell cell = row.createCell(1);

        // Writing the value to the cell
        cell.setCellValue("Tech Global");

        FileOutputStream fileOutputStream = new FileOutputStream(excelFilePath);
        workbook.write(fileOutputStream);

        fileOutputStream.close();

    }
}
