package Controllers;

import GetDataFromExcel.ReadExcelDate;
import GetDataFromExcel.ReadFile;

import java.util.ArrayList;
import java.util.Calendar;

public class AnalyzeExcelData {


    private ReadFile readFile;

    public static final String PLACES_EXCEL_FILE = "נספח 3 העמקים 10.8.19.xlsx";
    public static final String TIMES_EXCEL_FILE = "נספח 4 העמקים 11.8.19.xlsx";

    private String FileName;

    public AnalyzeExcelData(String fileName) {

        FileName = fileName;

        GetData();
    }

    private void GetData(){
        try {
            readFile = new ReadFile(FileName);
        }catch (Exception ex) {}
    }

    public ArrayList<String> GetColumnData(int col){
        return readFile == null ? null : readFile.ReadColumn(col);
    }

    public ArrayList<Calendar> GetColumnCalendar(int col){

        ReadExcelDate readExcelDate = new ReadExcelDate();

        try
        {
            ArrayList<Calendar> calendarArrayList = readExcelDate.readDataTypeTime(FileName, col);
            return calendarArrayList;
        }
        catch (Exception ex){
            return null;
        }
    }

}
