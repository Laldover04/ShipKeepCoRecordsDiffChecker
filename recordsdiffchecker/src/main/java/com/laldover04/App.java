package com.laldover04;

import org.apache.poi.ss.usermodel.Sheet;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        XLSXreader tarXreader = new XLSXreader("C:\\Users\\lukes\\OneDrive\\Documents\\GitHub\\ShipKeepCoRecordsDiffChecker\\recordsdiffchecker\\ServiceCodes_TAR.xlsx");
        XLSXreader ecbXreader = new XLSXreader("C:\\Users\\lukes\\OneDrive\\Documents\\GitHub\\ShipKeepCoRecordsDiffChecker\\recordsdiffchecker\\ServiceCodes_ECB.xlsx");
        // String[] expected = new String[]{"815510000020", "DF001", "12.00", "013120", "12.50"};
        // String[] row = ecbXreader.getRow("8155SYS TOT DF001");
        // System.out.println(row[3].substring(1));
        // System.out.println(row[5].substring(4));

        Sheet sheet = tarXreader.getSheet();

        // DiffChecker handler = new DiffChecker("C:\\Users\\lukes\\OneDrive\\Documents\\GitHub\\ShipKeepCoRecordsDiffChecker\\differences.csv");
        // handler.close();
    }

    // private static void printArray(String[] arr){
    //     for (String arr1 : arr) {
    //         System.out.println(arr1);
    //     }
    // }
}