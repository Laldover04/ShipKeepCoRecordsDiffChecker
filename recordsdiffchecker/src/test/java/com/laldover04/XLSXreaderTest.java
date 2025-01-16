package com.laldover04;


import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

/*
 * Testing for the XLSXreader class
 */
public class XLSXreaderTest {


    /**
     * Test that object can be created.
     */
    @Test
    public void testRowToArrayTrue() {
        XLSXreader myXreader = new XLSXreader("C:\\Users\\lukes\\OneDrive\\Documents\\GitHub\\ShipKeepCoRecordsDiffChecker\\recordsdiffchecker\\ServiceCodes_TAR.xlsx");
        String[] expected = new String[]{"815510000020", "DF001", "12.00", "013120", "12.50"};
        String[] row = myXreader.rowToArray(2);
        for(int i = 0; i < 5; i++){
            assertTrue(expected[i].equals(row[i]));
        }
    }

    @Test
    public void testGetRowTrue() {
        XLSXreader myXreader = new XLSXreader("C:\\Users\\lukes\\OneDrive\\Documents\\GitHub\\ShipKeepCoRecordsDiffChecker\\recordsdiffchecker\\ServiceCodes_TAR.xlsx");
        String[] expected = new String[]{"815510000020", "DF001", "12.00", "013120", "12.50"};
        String[] row = myXreader.getRow("8155DF001");
        for(int i = 0; i < 5; i++){
            assertTrue(expected[i].equals(row[i]));
        }
    }
}
