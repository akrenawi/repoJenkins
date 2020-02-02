package GetDataFromExcel;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ReadExcel implements typeFiles {
    private ArrayList<ArrayList<String>> tableSheet = new ArrayList();
    private int noOfColumns;

    public ReadExcel(String nameFile) throws IOException, InvalidFormatException {
        this.readData(nameFile);
    }

    public void readData(String nameFile) throws IOException, InvalidFormatException {
        try {
            String filePath = System.getProperty("user.dir") + "/src/main/java/GetDataFromExcel/";
            InputStream inp = new FileInputStream(filePath + nameFile);
            int ctr = 0;
            Workbook wb = WorkbookFactory.create(inp);
            Sheet sheet = wb.getSheetAt(0);
            Row row = null;
            Cell cell = null;
            this.noOfColumns = sheet.getRow(0).getPhysicalNumberOfCells();
            boolean isNull = false;

            int i;
            for(i = 0; i <= this.noOfColumns; ++i) {
                this.tableSheet.add(new ArrayList());
            }

            for(i = 0; i < this.noOfColumns; ++i) {
                do {
                    try {
                        row = sheet.getRow(ctr);
                        cell = row.getCell(i);
                        ((ArrayList)this.tableSheet.get(i)).add(cell.toString());
                        ++ctr;
                    } catch (Exception var11) {
                        isNull = true;
                    }
                } while(!isNull);

                ctr = 0;
                isNull = false;
                row = null;
                cell = null;
            }

            for(i = 0; i < this.noOfColumns; ++i) {
                ((ArrayList)this.tableSheet.get(i)).remove(0);
            }

            inp.close();
        } catch (Exception var12) {
            System.out.print(var12);
        }

    }

    public ArrayList<String> outColumn(int NumberColumn) {
        ArrayList dataColumn = new ArrayList();

        try {
            for(int i = 0; i < ((ArrayList)this.tableSheet.get(NumberColumn - 1)).size(); ++i) {
                dataColumn.add(((ArrayList)this.tableSheet.get(NumberColumn - 1)).get(i));
            }

            return dataColumn;
        } catch (Exception var4) {
            System.out.print(var4);
            return dataColumn;
        }
    }
}
