package apache_poi_excel;

import utils.ExcelUtil;

import java.util.Arrays;
import java.util.List;

public class TestingExcelUtil {
    public static void main(String[] args) {

        ExcelUtil.openExcelFile("ExcelSample", "Sheet1");
//        String cellValue = ExcelUtil.getValue(2,2);
//        System.out.println("Testing the getValue method: Cell value is: " + cellValue);
//
        List<List<String>> readingValues = ExcelUtil.getValues();
        System.out.println(readingValues);

//        System.out.println(Arrays.deepToString(ExcelUtil.getExcelData(ExcelUtil.getValues())));


    }
}
