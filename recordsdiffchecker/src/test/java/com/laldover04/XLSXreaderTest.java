package com.laldover04;


import java.util.HashMap;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/*
 * Testing for the XLSXreader class
 */
public class XLSXreaderTest {
    XLSXreader tarXLSXreader = new XLSXreader("C:\\Users\\lukes\\OneDrive\\Documents\\GitHub\\ShipKeepCoRecordsDiffChecker\\recordsdiffchecker\\ServiceCodes_TAR.xlsx");
    HashMap<String, Row> tarLookup = tarXLSXreader.getHashMap();
    Sheet tarSheet = tarXLSXreader.getSheet();

    XLSXreader ecbXLSXreader = new XLSXreader("C:\\Users\\lukes\\OneDrive\\Documents\\GitHub\\ShipKeepCoRecordsDiffChecker\\recordsdiffchecker\\ServiceCodes_ECB.xlsx");
    
    /**
     * Tests that the XSLXreader class is created without exceptions. Including creation of the hashmap.
     * Checks that lookups using the .getRow method (hashMap) returns the correct rows.
     */
    @Test
    public void testGetRowTrue() {
        //TAR file
        // Row 1
        String[] expected = new String[]{"815510000020", "DF001", "12.00", "013120", "12.50"};
        String[] row = tarXLSXreader.getRow("815510000020DF001");
        for(int i = 0; i < 5; i++){
            assertTrue(expected[i].equals(row[i]));
        }

        // Row 4969
        expected = new String[]{"815540000530", "HF059", "12.00", "013120", "12.50"};
        row = tarXLSXreader.getRow("815540000530HF059");
        for(int i = 0; i < 5; i++){
            assertTrue(expected[i].equals(row[i]));
        }

        // Row 26937
        expected = new String[]{"815560000780", "VTL06", "12.00", "013120", "12.50"};
        row = tarXLSXreader.getRow("815560000780VTL06");
        for(int i = 0; i < 5; i++){
            assertTrue(expected[i].equals(row[i]));
        }

        // Row 26938
        expected = new String[]{"81555000TOT ", "VTL06", "12.00", "013120", "12.50"};
        row = tarXLSXreader.getRow("81555000TOT VTL06");
        for(int i = 0; i < 5; i++){
            assertTrue(expected[i].equals(row[i]));
        }

        // final Row 26940
        expected = new String[]{"8155SYS TOT ", "VTL06", "12.00", "013120", "12.50"};
        row = tarXLSXreader.getRow("8155SYS TOT VTL06");
        for(int i = 0; i < 5; i++){
            assertTrue(expected[i].equals(row[i]));
        }

        //ECB file
        // Row 1
        expected = new String[]{"8155SYS TOT ", "DF001", "IA V/D MODEM", " $12.00 ", "013120", " $ 12.50 ", "8155", "SYS ", "TOT "};
        row = ecbXLSXreader.getRow("8155SYS TOT DF001");
        for(int i = 0; i < 5; i++){
            assertTrue(expected[i].equals(row[i]));
        }

    }

    /*
     * Tests that we can use hashMaps to find matching rows.
     */
    @Test
    public void testHashMapsTrue() {

        
    }


    /**
     * Test that an invalid lookup returns expected string array
     */
    @Test
    public void testgetRowUnusedKey() {
        //String[] expected = new String[]{"815510000020", "DF001", "12.00", "013120", "12.50"};
        String[] row = tarXLSXreader.getRow("815551DF001");
        for(int i = 0; i < 5; i++){
            assertTrue(row[0].equals("Invalid SPA or Service Code") && row.length == 1);
        }
    }
}
