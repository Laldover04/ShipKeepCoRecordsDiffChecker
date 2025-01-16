package com.laldover04;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.opencsv.CSVWriter;

public class DiffChecker {
	private CSVWriter writer;
	private final XLSXreader tarReader;
	private final XLSXreader ecbReader;
    private final DataFormatter df;
	private int count;

    public DiffChecker(String filePath){
		count = 0;

		// Creating csv writer
		File csvfile = new File(filePath);
		try { 
			FileWriter outputFile = new FileWriter(csvfile); 
			writer = new CSVWriter(outputFile); 
	
			String header[] = {"SPA", "Service Code", "Tar Charge", "ECB Charge", "Tar New Charge", "ECB New Charge"};
			writer.writeNext(header);
	
		} 
		catch (IOException e) { 
			e.printStackTrace(); 
		}

		// Creating XLSX readers and dataformatter
		tarReader = new XLSXreader("C:\\Users\\lukes\\OneDrive\\Documents\\GitHub\\ShipKeepCoRecordsDiffChecker\\recordsdiffchecker\\ServiceCodes_TAR.xlsx");
		ecbReader = new XLSXreader("C:\\Users\\lukes\\OneDrive\\Documents\\GitHub\\ShipKeepCoRecordsDiffChecker\\recordsdiffchecker\\ServiceCodes_ECB.xlsx");
		df = new DataFormatter();
		checkDifferences();
	}

	/*
	 * Method to check the differences between the two files and add them to the csv
	 */
	private void checkDifferences(){

		Sheet tarSheet = tarReader.getSheet();
		HashMap<String, Row> ecbHashMap = ecbReader.getHashMap();

        Iterator<Row> rowIt = tarSheet.rowIterator();
        
        //Skip two header lines
        rowIt.next();
        rowIt.next();

        Row tarRow;
        Row ecbRow;

        //Iterate through all rows
        while (rowIt.hasNext()) {
            tarRow = rowIt.next();

			String spa = df.formatCellValue(tarRow.getCell(0));
            String serviceCode =  df.formatCellValue(tarRow.getCell(1));

			
            //check that the charges match
            ecbRow = ecbHashMap.get(spa + serviceCode);
			if(!ecbHashMap.containsKey(spa + serviceCode)){
				count++;
				System.out.println(count + ": miss");
				
			}

            // charge columns
            String tarCharge = df.formatCellValue(tarRow.getCell(2));
            String ecbCharge = df.formatCellValue(ecbRow.getCell(3)).substring(1);
			String newTarCharge = df.formatCellValue(tarRow.getCell(4));
			String newEcbCharge = df.formatCellValue(ecbRow.getCell(5)).substring(4);

            if(!(tarCharge.equals(ecbCharge) && newTarCharge.equals(newEcbCharge))){
                String[] nextLine = new String[]{spa, serviceCode, tarCharge, ecbCharge, newTarCharge, newEcbCharge};
				writer.writeNext(nextLine);
            }	//26938 12.00,   126934 12.50
				//28222 12.00,   28222 12.50
        }
	}

	/*
	 * wrapper method to close the writer to avoid leaks and ensure the writing finishes
	 */
    public void close() 
	{ 
		try {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	} 

}
