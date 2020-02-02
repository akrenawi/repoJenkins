package GetDataFromExcel;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class ReadExcelDate implements typeFilesDate {

    private ArrayList<Calendar> tableSheet = new ArrayList<Calendar>();


    @Override
    public ArrayList<Calendar> readDataTypeTime(String nameFile,int noOfColumns) throws IOException, InvalidFormatException {
        try {

            String filePath = System.getProperty("user.dir") + "/src/main/java/GetDataFromExcel/";
            InputStream inp = new FileInputStream(filePath + nameFile);

            int ctr = 1;
            Workbook wb = WorkbookFactory.create(inp);
            Sheet sheet = wb.getSheetAt(0);
            Row row = null;
            boolean isNull = false;

            do {
                try {

                    if(HSSFDateUtil.isCellDateFormatted(sheet.getRow(ctr).getCell(noOfColumns - 1))){
                        Date d = sheet.getRow(ctr).getCell(noOfColumns - 1).getDateCellValue();

                        Calendar date = Calendar.getInstance();
                        date.setTime(d);
                        Calendar time = Calendar.getInstance();
                        time.setTime(d);
                        Calendar cal = date;
                        cal.set(Calendar.HOUR, time.get(Calendar.HOUR));
                        cal.set(Calendar.MINUTE, time.get(Calendar.MINUTE));
                        cal.set(Calendar.SECOND, time.get(Calendar.SECOND));
                        cal.set(Calendar.MILLISECOND, time.get(Calendar.MILLISECOND));
                        tableSheet.add(cal);
                        ctr++;
                    }


                } catch (Exception e) {
                    isNull = true;
                }
            } while (isNull != true);

            inp.close();

        } catch (Exception e) {
            System.out.print(e);

        }

        return this.tableSheet;

    }

}





