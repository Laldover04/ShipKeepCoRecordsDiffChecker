package com.laldover04;

import org.apache.poi.ss.usermodel.DataFormatter;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        XLSXreader myXreader = new XLSXreader("C:\\Users\\lukes\\OneDrive\\Documents\\GitHub\\ShipKeepCoRecordsDiffChecker\\recordsdiffchecker\\ServiceCodes_TAR.xlsx");
        DataFormatter df = new DataFormatter();
        String[] temp = myXreader.rowToArray(2);

        
    }
}
