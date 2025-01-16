package com.laldover04;



import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class XLSXreader {
    Sheet sheet;
    DataFormatter df;
    HashMap<String, Row> lookup;

    public XLSXreader(String filePath) {

        //TAR file hard coded
        File file = new File(filePath);
        try (FileInputStream fileStream = new FileInputStream(file)) {
            // System.out.println("success");
            // System.out.println("FileName: " + file.getName());
            // System.out.println("Readable: " + file.canRead());

            // workbook
            try (Workbook wb = new XSSFWorkbook(fileStream)) {

                df = new DataFormatter();
                
                // sheet
                sheet = wb.getSheetAt(0);
                sheetToHash();
            }


        } catch (IOException e) {
            //System.out.println("exception!");
            e.printStackTrace();
        }


    }

    /*
     * returns the row at the desired index, 
     * if row is out of bounds it returns the header row at index 0.
     * NO LONGER USED, REPLACE BY GETROW()
     * 
     */
    public String[] rowToArray(int index) {
        // rows 0 and 1 are headers
        if(index < sheet.getPhysicalNumberOfRows() && index > 1){

            Row currentRow = sheet.getRow(index);
            //size of ECB rows
            String[] row = new String[currentRow.getPhysicalNumberOfCells()];
            
            for(int i = 0; i < currentRow.getPhysicalNumberOfCells(); i++){
                row[i] = df.formatCellValue(currentRow.getCell(i));
            }
            return row;
            
        } else {
            String[] fail = new String[]{"Index Out of Bounds"};
            return fail;
        }
    }

    /*
     * returns the row at the desired index,
     * if row is out of bounds it returns the header row at index 0.
     * 
     */
    public String[] getRow(String key) {
        // rows 0 and 1 are headers
        if(lookup.containsKey(key)){

            Row currentRow = lookup.get(key);
            //size of ECB rows
            String[] row = new String[currentRow.getPhysicalNumberOfCells()];
            
            for(int i = 0; i < currentRow.getPhysicalNumberOfCells(); i++){
                row[i] = df.formatCellValue(currentRow.getCell(i));
            }
            return row;
            
        } else {
            String[] fail = new String[]{"Index Out of Bounds"};
            return fail;
        }
    }
    private void sheetToHash(){
        Iterator<Row> rowIt = sheet.rowIterator();
        int count = 0;

        // pass headers
        rowIt.next();
        rowIt.next();

        //Iterate through all rows
        while (rowIt.hasNext()) {
            Row row = rowIt.next();
            String key = df.formatCellValue(row.getCell(0)).substring(0, 4) + df.formatCellValue(row.getCell(1));

            lookup.put(key, row);
            count++;
            }
    }
}





// // iterate on cells
// Iterator<Cell> cellIt = row.cellIterator();
// while (cellIt.hasNext()) {
// Cell cell = cellIt.next();
// System.out.println(cell.getNumericCellValue());
// }


// }
