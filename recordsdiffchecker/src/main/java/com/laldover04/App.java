package com.laldover04;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        XLSXreader myXreader = new XLSXreader("C:\\Users\\lukes\\OneDrive\\Documents\\GitHub\\ShipKeepCoRecordsDiffChecker\\recordsdiffchecker\\ServiceCodes_ECB.xlsx");
        String[] expected = new String[]{"815510000020", "DF001", "12.00", "013120", "12.50"};
        String[] row = myXreader.getRow("815510000020DF001");
        //System.out.println(row[0]);
        for(int i = 0; i < 5; i++){

            System.out.println(row[i]);
        }
    }
}