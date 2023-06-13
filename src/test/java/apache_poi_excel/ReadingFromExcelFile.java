package apache_poi_excel;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ReadingFromExcelFile {

    public static void main(String[] args) throws IOException {

        String excelFilePath = "test_data/ExcelSample.xlsx";

        // I am able to reach the file without any error
        FileInputStream fileInputStream = new FileInputStream(excelFilePath);

        // Opening the Excel file from the path that we specify
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);

        // Going to the specific Sheet on the Excel file
        XSSFSheet sheet = workbook.getSheet("Sheet1");

        // Getting the value from the sheet
        String firstName = sheet.getRow(1).getCell(0).getStringCellValue();
        System.out.println("Getting the first name from the file: " + firstName);

        int secondId = (int) sheet.getRow(2).getCell(1).getNumericCellValue();
        System.out.println("Getting the second Id from the file: " + secondId);

        // finding the last row number to use in the loop
        int lastRow = sheet.getLastRowNum();
        System.out.println("Getting the last row number: " + lastRow);

        // getting the last cell number to use in the second loop
        int lastCell = sheet.getRow(1).getLastCellNum();
        System.out.println("Getting the last cell number: " + lastCell);

        for (int r = 0; r <= lastRow; r++) {
            // Visiting each row
            XSSFRow row = sheet.getRow(r);

            // Looping each cell from corresponding row
            for (int c = 0; c < lastCell; c++){
                // Getting each cell value
                XSSFCell cell = row.getCell(c);

                System.out.print(cell + " | ") ;

            }
            System.out.println();
        }
    }
}
