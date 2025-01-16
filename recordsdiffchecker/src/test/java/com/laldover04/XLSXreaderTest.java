package com.laldover04;


import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/*
 * Testing for the XLSXreader class
 */
public class XLSXreaderTest {
    XLSXreader myXreader = new XLSXreader("C:\\Users\\lukes\\OneDrive\\Documents\\GitHub\\ShipKeepCoRecordsDiffChecker\\recordsdiffchecker\\ServiceCodes_TAR.xlsx");
    
    

    /**
     * Test that object can be created.
     */
    @Test
    public void testRowToArrayTrue() {
        String[] expected = new String[]{"815510000020", "DF001", "12.00", "013120", "12.50"};
        String[] row = myXreader.getRow("815510000020DF001");
        for(int i = 0; i < 5; i++){
            assertTrue(expected[i].equals(row[i]));
        }


        // expected = new String[]{"8155SYS TOT ", "VTL06", "12.00", "013120", "12.50"};
        // row = myXreader.getRow("8155SYS TOTVTL06");
        // for(int i = 0; i < 5; i++){
        //     assertTrue(expected[i].equals(row[i]));
        // }
    }

    /**
     * Test that object can be created.
     */
    @Test
    public void testRowToArrayUnusedKey() {
        String[] expected = new String[]{"815510000020", "DF001", "12.00", "013120", "12.50"};
        String[] row = myXreader.getRow("815551DF001");
        for(int i = 0; i < 5; i++){
            assertTrue(row[0].equals("Invalid SPA or Service Code") && row.length == 1);
        }
    }
}
