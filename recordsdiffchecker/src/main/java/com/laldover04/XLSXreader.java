package com.laldover04;



import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class XLSXreader {
    public static void main(String[] args) {


        File file = new File("C:\\Users\\lukes\\OneDrive\\Documents\\GitHub\\ShipKeepCoRecordsDiffChecker\\recordsdiffchecker\\ServiceCodes_TAR.xlsx");
        try (FileInputStream fileStream = new FileInputStream(file)) {
            System.out.println("success");
            System.out.println("FileName: " + file.getName());
            System.out.println("Readable: " + file.canRead());


            // workbook
            Workbook wb = new XSSFWorkbook(fileStream);
            DataFormatter df = new DataFormatter();

            // sheet
            Sheet sheet = wb.getSheetAt(0);

            // iterate on rows
            Iterator<Row> rowIt = sheet.iterator();
            int count = 0;
            
            Row currentRow;
            Cell currentCell;
            String temp;
            while(rowIt.hasNext()){
                currentRow = rowIt.next();
                Iterator<Cell> cellIt = currentRow.cellIterator();
                while(cellIt.hasNext()){
                    currentCell = cellIt.next();
                    temp = df.formatCellValue(currentCell);
                    System.out.print(temp + " ");
                    
                }
                System.out.println(" ");
            }



        } catch (IOException e) {
            System.out.println("exception!");
            e.printStackTrace();
        }


    }


    // private static XSSFSheet getSheet(String fileName) {


    // }
}


// while (rowIt.hasNext()) {
// Row row = rowIt.next();
// System.out.println("\n row: " + row.getRowNum());
// if (row.getRowNum() > 10) {
// break;
// }


// // iterate on cells
// Iterator<Cell> cellIt = row.cellIterator();
// while (cellIt.hasNext()) {
// Cell cell = cellIt.next();
// System.out.println(cell.getNumericCellValue());
// }


// }
