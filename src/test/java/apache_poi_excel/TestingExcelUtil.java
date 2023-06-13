package apache_poi_excel;

import utils.ExcelUtil;

public class TestingExcelUtil {
    public static void main(String[] args) {

        ExcelUtil.openExcelFile("ExcelSample", "Sheet1");
        String cellValue = ExcelUtil.getValue(2,2);
        System.out.println("Testing the getValue method: Cell value is: " + cellValue);
    }
}
