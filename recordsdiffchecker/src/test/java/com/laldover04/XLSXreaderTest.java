package com.laldover04;


import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/*
 * Testing for the XLSXreader class
 */
public class XLSXreaderTest {
    XLSXreader myXreader = new XLSXreader("C:\\Users\\lukes\\OneDrive\\Documents\\GitHub\\ShipKeepCoRecordsDiffChecker\\recordsdiffchecker\\ServiceCodes_TAR.xlsx");
    
    

    /**
     * Tests that the XSLXreader class is created without exceptions. Including creation of the hashmap.
     * Checks that lookups using the .getRow method (hashMap) returns the correct rows.
     * The issue with this is that most rows share the same values... So theres a chance the strings are 
     * correct but the actual rows are not the same.
     */
    @Test
    public void testGetRowTrue() {
        // Row 1
        String[] expected = new String[]{"815510000020", "DF001", "12.00", "013120", "12.50"};
        String[] row = myXreader.getRow("815510000020DF001");
        for(int i = 0; i < 5; i++){
            assertTrue(expected[i].equals(row[i]));
        }

        // Row 4969
        expected = new String[]{"815540000530", "HF059", "12.00", "013120", "12.50"};
        row = myXreader.getRow("815540000530HF059");
        for(int i = 0; i < 5; i++){
            assertTrue(expected[i].equals(row[i]));
        }

        // Row 26937
        expected = new String[]{"815560000780", "VTL06", "12.00", "013120", "12.50"};
        row = myXreader.getRow("815560000780VTL06");
        for(int i = 0; i < 5; i++){
            assertTrue(expected[i].equals(row[i]));
        }

        // Row 26938
        expected = new String[]{"81555000TOT ", "VTL06", "12.00", "013120", "12.50"};
        row = myXreader.getRow("81555000TOT VTL06");
        for(int i = 0; i < 5; i++){
            assertTrue(expected[i].equals(row[i]));
        }

        // final Row 26940
        expected = new String[]{"8155SYS TOT ", "VTL06", "12.00", "013120", "12.50"};
        row = myXreader.getRow("8155SYS TOT VTL06");
        for(int i = 0; i < 5; i++){
            assertTrue(expected[i].equals(row[i]));
        }
    }

    /*
     * Combats the above testing issue by testing that the actual row object found is equal to the row object we expect.
     */
    // @Test
    // public void testGetRowObjectTrue() {
    //     // Row 1
    //     String[] expected = new String[]{"815510000020", "DF001", "12.00", "013120", "12.50"};
    //     String[] row = myXreader.getRow("815510000020DF001");
    //     for(int i = 0; i < 5; i++){
    //         assertTrue(expected[i].equals(row[i]));
    //     }

    //     // Row 4969
    //     expected = new String[]{"815540000530", "HF059", "12.00", "013120", "12.50"};
    //     row = myXreader.getRow("815540000530HF059");
    //     for(int i = 0; i < 5; i++){
    //         assertTrue(expected[i].equals(row[i]));
    //     }

    //     // Row 26937
    //     expected = new String[]{"815560000780", "VTL06", "12.00", "013120", "12.50"};
    //     row = myXreader.getRow("815560000780VTL06");
    //     for(int i = 0; i < 5; i++){
    //         assertTrue(expected[i].equals(row[i]));
    //     }

    //     // Row 26938
    //     expected = new String[]{"81555000TOT ", "VTL06", "12.00", "013120", "12.50"};
    //     row = myXreader.getRow("81555000TOT VTL06");
    //     for(int i = 0; i < 5; i++){
    //         assertTrue(expected[i].equals(row[i]));
    //     }

    //     // final Row 26940
    //     expected = new String[]{"8155SYS TOT ", "VTL06", "12.00", "013120", "12.50"};
    //     row = myXreader.getRow("8155SYS TOT VTL06");
    //     for(int i = 0; i < 5; i++){
    //         assertTrue(expected[i].equals(row[i]));
    //     }
    // }


    /**
     * Test that an invalid lookup returns expected string array
     */
    @Test
    public void testgetRowUnusedKey() {
        String[] expected = new String[]{"815510000020", "DF001", "12.00", "013120", "12.50"};
        String[] row = myXreader.getRow("815551DF001");
        for(int i = 0; i < 5; i++){
            assertTrue(row[0].equals("Invalid SPA or Service Code") && row.length == 1);
        }
    }
}
