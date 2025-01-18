package com.laldover04;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/*
 * Testing for the XLSXreader class
 */
public class DiffCheckerTest {
    List<String[]> columnValues = new ArrayList<String[]>();
    XLSXreader tarXLSXreader = new XLSXreader("C:\\Users\\lukes\\OneDrive\\Documents\\GitHub\\ShipKeepCoRecordsDiffChecker\\recordsdiffchecker\\ServiceCodes_TAR.xlsx");
    XLSXreader ecbXLSXreader = new XLSXreader("C:\\Users\\lukes\\OneDrive\\Documents\\GitHub\\ShipKeepCoRecordsDiffChecker\\recordsdiffchecker\\ServiceCodes_ECB.xlsx");
    
    /*
     * Breaks down the information from the csv file into a list
     */
    @BeforeEach
    public void init(){
        DiffChecker dc = new DiffChecker("C:\\Users\\lukes\\OneDrive\\Documents\\GitHub\\ShipKeepCoRecordsDiffChecker\\differences.csv");
        dc.close();

    try {
        Scanner scanner = new Scanner(new File("C:\\Users\\lukes\\OneDrive\\Documents\\GitHub\\ShipKeepCoRecordsDiffChecker\\differences.csv"));
        while(scanner.hasNextLine()) {
            String[] values = scanner.nextLine().split(",");
            columnValues.add(values);
        }
        scanner.close();
    }catch(Exception e) {
        e.printStackTrace();
    }

}

    /**
     * Tests that all rows reported in the file have one of 3 possible erros
     */
    @Test
    public void testSPASCmissing() {
        for(int x = 1; x < columnValues.size(); x++) {
            String col1 = columnValues.get(x)[0];
            String col2 = columnValues.get(x)[1];

            //Strange reading where it adds quotes inside the string, must remove.
            boolean isInTar = tarXLSXreader.getHashMap().containsKey(col1.replace("\"", "") + col2.replace("\"", ""));
            boolean isInEcb = ecbXLSXreader.getHashMap().containsKey(col1.replace("\"", "") + col2.replace("\"", ""));

            assertTrue((!isInTar && isInEcb) || (isInTar && !isInEcb) || (isInTar && isInEcb && mismatch(columnValues.get(x))));
        }
    }

    /*
     * Helper method for testing, checks if there is a mismatch of charges by the column array.
     */
    private boolean mismatch(String[] col){
        String tarCharge = col[2];
        String ecbCharge = col[3];

		String tarNewCharge = col[4];
		String ecbNewCharge = col[5];

        return (!(tarCharge.equals(ecbCharge) && tarNewCharge.equals(ecbNewCharge)));
    }
}
