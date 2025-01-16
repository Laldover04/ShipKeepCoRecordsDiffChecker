package com.laldover04;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        XLSXreader tarXreader = new XLSXreader("C:\\Users\\lukes\\OneDrive\\Documents\\GitHub\\ShipKeepCoRecordsDiffChecker\\recordsdiffchecker\\ServiceCodes_TAR.xlsx");
        XLSXreader ecbXreader = new XLSXreader("C:\\Users\\lukes\\OneDrive\\Documents\\GitHub\\ShipKeepCoRecordsDiffChecker\\recordsdiffchecker\\ServiceCodes_ECB.xlsx");
        // String[] expected = new String[]{"815510000020", "DF001", "12.00", "013120", "12.50"};
        // String[] row = tarXreader.getRow("815510000020DF001");
        // printArray(row);
        System.out.println(" ");

        tarXreader.reportDiff(ecbXreader);

    }

    private static void printArray(String[] arr){
        for (String arr1 : arr) {
            System.out.println(arr1);
        }
    }
}