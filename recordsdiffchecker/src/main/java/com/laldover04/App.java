package com.laldover04;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        // XLSXreader tarXreader = new
        // XLSXreader("C:\\Users\\lukes\\OneDrive\\Documents\\GitHub\\ShipKeepCoRecordsDiffChecker\\recordsdiffchecker\\ServiceCodes_TAR.xlsx");
        // XLSXreader ecbXreader = new
        // XLSXreader("C:\\Users\\lukes\\OneDrive\\Documents\\GitHub\\ShipKeepCoRecordsDiffChecker\\recordsdiffchecker\\ServiceCodes_ECB.xlsx");
        // String[] expected = new String[]{"8155SYS TOT ", "DF001", "IA V/D MODEM", "
        // $12.00 ", "013120", " $ 12.50 ", "8155", "SYS ", "TOT "};
        // String[] row = ecbXreader.getRow("8155SYS TOT DF001");
        // for(int i = 0; i < 9; i++){
        // System.out.println(row[i]);
        // }

        DiffChecker dc = new DiffChecker(
                "C:\\Users\\lukes\\OneDrive\\Documents\\GitHub\\ShipKeepCoRecordsDiffChecker\\differences.csv");
        dc.close();
    }

    // private static void printArray(String[] arr){
    // for (String arr1 : arr) {
    // System.out.println(arr1);
    // }
    // }
}