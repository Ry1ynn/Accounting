package Interfaces;

import org.apache.poi.ss.usermodel.Row;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface Calculating {
   void closeStream() throws IOException;
   void makeMonthTable() throws IOException;
}
