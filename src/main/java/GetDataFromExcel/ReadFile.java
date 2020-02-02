package GetDataFromExcel;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;
import java.util.ArrayList;

public class ReadFile {

    private ReadExcel readExcel;

    public ReadFile(String nameFile) throws IOException, InvalidFormatException {
        this.readExcel = new ReadExcel(nameFile);
    }

    public ArrayList<String> ReadColumn(int numberColumn) {
        return this.readExcel.outColumn(numberColumn);
    }

}
