package GetDataFromExcel;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.IOException;

public interface typeFiles {
    void readData(String var1) throws IOException, InvalidFormatException, IOException;
}
