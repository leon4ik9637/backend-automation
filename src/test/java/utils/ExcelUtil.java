package utils;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtil {

    private static XSSFWorkbook workbook;
    private static XSSFSheet sheet;
    private static XSSFRow row;
    private static XSSFCell cell;
    private static String filePath;

    public static void openExcelFile(String excelFileName, String sheetName) {
        filePath = "test_data/" + excelFileName + ".xlsx";

        try {
            // Finding the file path
            FileInputStream fileInputStream = new FileInputStream(filePath);
            // Opening the file
            workbook = new XSSFWorkbook(fileInputStream);
            System.out.println("File exists!");
            sheet = workbook.getSheet(sheetName);
            System.out.println("Sheet exists!");
        } catch (Exception e) {
            System.out.println("The file or sheet does not exist");
        }

    }

    public static String getValue(int rowNum, int cellNum) {
        row = sheet.getRow(rowNum);
        cell = row.getCell(cellNum);
        cell.setCellType(CellType.STRING);
        return cell.toString();
    }

    public static List<List<String>> getValues() {
        // Creating the list of list to store whole table from the Excel
        List<List<String>> allValues = new ArrayList<>();
        // creating the loop for the rows
        // getLastRow is getting the index number
        for (int r = sheet.getFirstRowNum() + 1; r <= sheet.getLastRowNum(); r++) {
            row = sheet.getRow(r);
            //creating the list for storing the row values
            List<String> eachRow = new ArrayList<>();
            // Looping each cell in that row
            for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
                // getting the value from the that cell
                // adding it to eachRow list
                eachRow.add(getValue(r, c));
            }
            // adding the eachRow to allValues list of list
            allValues.add(eachRow);
        }
        return allValues;
    }

    public static String[][] getExcelData(List<List<String>> mergedList) {
        // Creating multidimensional array
        String[][] result = new String[mergedList.size()][];
        // loop the list until the last one
        for (int i = 0; i < mergedList.size(); i++) {
            // getting each list of the list
            List<String> currentList = mergedList.get(i);
            // converting the list to Array
            result[i] = currentList.toArray(new String[currentList.size()]);
        }
        return result;
    }

    // this method is closing the file
    public static void closeExcelFile(){

        try {
            workbook.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
