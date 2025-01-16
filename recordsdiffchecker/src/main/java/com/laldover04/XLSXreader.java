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
    private Sheet sheet;
    private DataFormatter df;
    private HashMap<String, Row> lookup;

    public XLSXreader(String filePath) {

        //TAR file hard coded
        File file = new File(filePath);
        try (FileInputStream fileStream = new FileInputStream(file)) {

            // workbook
            try (Workbook wb = new XSSFWorkbook(fileStream)) {

                df = new DataFormatter();
                
                // sheet
                sheet = wb.getSheetAt(0);
                lookup = new HashMap<>();
                sheetToHash();

            }


        } catch (IOException e) {
            //System.out.println("exception!");
            e.printStackTrace();
        }

    }

    /*
     * Reads each row into a hashtable "lookup" with the SPA + Service Code as the key.
     */
    private void sheetToHash(){
        Iterator<Row> rowIt = sheet.rowIterator();

        // pass headers
        rowIt.next();
        rowIt.next();
        //System.out.println(df.formatCellValue(sheet.getRow(2).getCell(0)));
        
        Row row;
        //Iterate through all rows
        while (rowIt.hasNext()) {
            row = rowIt.next();
            String key = df.formatCellValue(row.getCell(0)) + df.formatCellValue(row.getCell(1));
            if(lookup.containsKey(key)){
                System.out.println("dupe" );
            }
            lookup.put(key, row);
        }
    }

    /*
     * returns the row at the desired key,
     * if row is out of bounds it returns the header row at index 0.
     * 
     */
    public String[] getRow(String key) {
        // rows 0 and 1 are headers
        if(lookup.containsKey(key)){

            Row currentRow = lookup.get(key);
            //size of ecb rows
            String[] row = new String[currentRow.getPhysicalNumberOfCells()];
            
            for(int i = 0; i < currentRow.getPhysicalNumberOfCells(); i++){
                row[i] = df.formatCellValue(currentRow.getCell(i));
            }
            return row;
            
        } else {
            String[] fail = new String[]{"Invalid SPA or Service Code"};
            return fail;
        }
    }

    /*
     * returns the sheet
     */
    public Sheet getSheet(){
        return sheet;
    }

    /*
     * returns the hashmap
     */
    public HashMap<String, Row> getHashMap(){
        return lookup;
    }


    
}