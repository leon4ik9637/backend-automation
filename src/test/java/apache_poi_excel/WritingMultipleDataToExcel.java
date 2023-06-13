package apache_poi_excel;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class WritingMultipleDataToExcel {

    public static void main(String[] args) throws IOException {

        String excelFile = "test_data/WritingData.xlsx";

        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFSheet sheet = workbook.createSheet("Sheet1");

        Object[][] employeeData = {
                {"EmpID", "Name", "Title"},
                {101, "TechGlobal", "School"},
                {102, "Akin", "Sr SDET"},
                {103, "Ulan", "Sr Dev"},
        };

        // finding the length of the rows
        int rowLength = employeeData.length;
        // finding the length of the cells in the row index 1
        // we could pick any row index the length of the cells
        int cellLength = employeeData[1].length;

        // Creating the row
        for (int r = 0; r < rowLength; r ++){
            XSSFRow row = sheet.createRow(r);

            // Creating the cells
            for (int c = 0; c < cellLength; c++){
                XSSFCell cell = row.createCell(c);

                // Getting the value from the employeeData multidimensional Array
                Object cellValue = employeeData[r][c];
                // checking the value type from the employeeData multidimensional Array
                if(cellValue instanceof String)
                    // writing data based on the data type
                    cell.setCellValue((String) cellValue);

                if(cellValue instanceof Integer)
                    cell.setCellValue((Integer) cellValue);

                if(cellValue instanceof Boolean)
                    cell.setCellValue((Boolean) cellValue);
            }
        }
        FileOutputStream fileOutputStream = new FileOutputStream(excelFile);
        workbook.write(fileOutputStream);
        workbook.close();
    }
}
