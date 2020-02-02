package GetDataFromExcel;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

public interface typeFilesDate {
    ArrayList<Calendar> readDataTypeTime(String nameFile, int noOfColumns) throws IOException, InvalidFormatException;
}
