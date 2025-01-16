package com.laldover04;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.util.HashMap;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/*
 * Testing for the XLSXreader class
 */
public class DiffCheckerTest {
    List<String[]> columnValues = new ArrayList<String[]>();
    XLSXreader tarXLSXreader = new XLSXreader("C:\\Users\\lukes\\OneDrive\\Documents\\GitHub\\ShipKeepCoRecordsDiffChecker\\recordsdiffchecker\\ServiceCodes_TAR.xlsx");
    HashMap<String, Row> tarLookup = tarXLSXreader.getHashMap();
    Sheet tarSheet = tarXLSXreader.getSheet();

    XLSXreader ecbXLSXreader = new XLSXreader("C:\\Users\\lukes\\OneDrive\\Documents\\GitHub\\ShipKeepCoRecordsDiffChecker\\recordsdiffchecker\\ServiceCodes_ECB.xlsx");
    


    /*
     * Breaks down the information from the csv file into a list
     */
    @BeforeEach
    public void init(){
        DiffChecker dc = new DiffChecker("C:\\Users\\lukes\\OneDrive\\Documents\\GitHub\\ShipKeepCoRecordsDiffChecker\\differences.csv");
        dc.close();

    try {
        Scanner scanner = new Scanner(new File("myFile.csv"));
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
     * Tests that the XSLXreader class is created without exceptions. Including creation of the hashmap.
     * Checks that lookups using the .getRow method (hashMap) returns the correct rows.
     */
    @Test
    public void testSPASCmissing() {
        for(int x = 1; x < columnValues.size(); x++) {
            String col1 = columnValues.get(x)[0];
            String col2 = columnValues.get(x)[0];
            boolean isInTar = tarXLSXreader.getHashMap().containsKey(col1 + col2);
            boolean isInEcb = ecbXLSXreader.getHashMap().containsKey(col1 + col2);
            assertTrue(!isInTar || !isInEcb);
        }
    }
}
