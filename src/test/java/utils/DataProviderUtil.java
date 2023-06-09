package utils;

import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;

import static utils.ConfigReader.getProperty;

public class DataProviderUtil {

    @DataProvider (name= "DataFromExcel")
    public static Object[][] getDataFromExcelFileWithDataProvider(){

        ExcelUtil.openExcelFile(getProperty("petStoreExcel"), getProperty("petStoreExcelSheet"));
        // reading all the data from the Excel file and convert it to multidimensional array
        Object[][] dataArray = ExcelUtil.getExcelData(ExcelUtil.getValues());

        ExcelUtil.closeExcelFile();

        return dataArray;
    }
}
