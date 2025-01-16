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

    public DiffChecker(String filePath){
		
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
		
		checkDifferencesTARHash();
		checkDifferencesEBCBHash();
	}

	/*
	 * Method to check the differences between the two files and add them to the csv with tar as the hash, this version also checks for mismatched charges
	 */
	private void checkDifferencesTARHash(){

		Sheet ecbSheet = ecbReader.getSheet();
		HashMap<String, Row> tarHashMap = tarReader.getHashMap();

        Iterator<Row> ecbRowIt = ecbSheet.rowIterator();
        
        //Skip two header lines
        ecbRowIt.next();
        ecbRowIt.next();

        Row tarRow;
        Row ecbRow;

        //Iterate through all rows
        while (ecbRowIt.hasNext()) {
            ecbRow = ecbRowIt.next();

			String spa = df.formatCellValue(ecbRow.getCell(0));
            String serviceCode =  df.formatCellValue(ecbRow.getCell(1));

			
            // check that the transaction exists in tar
			if(!tarHashMap.containsKey(spa + serviceCode)){
				// if it doesn't exist report it
				String ecbCharge = df.formatCellValue(ecbRow.getCell(3)).substring(1);
				String ecbNewCharge = df.formatCellValue(ecbRow.getCell(5)).substring(4);
				String[] nextLine = new String[]{spa, serviceCode, "Does Not Exist,", ecbCharge, "Does not Exist,", ecbNewCharge};
					writer.writeNext(nextLine);

				//We will get all contained errors on the first call, so only run the below portion if it is the first call of this method.
			} else {

            	tarRow = tarHashMap.get(spa + serviceCode);

            	// charge columns
            	String tarCharge = df.formatCellValue(tarRow.getCell(2));
            	String ecbCharge = df.formatCellValue(ecbRow.getCell(3)).substring(1);
				String tarNewCharge = df.formatCellValue(tarRow.getCell(4));
				String ecbNewCharge = df.formatCellValue(ecbRow.getCell(5)).substring(4);

            	if(!(tarCharge.equals(ecbCharge) && tarNewCharge.equals(ecbNewCharge))){
               		String[] nextLine = new String[]{spa, serviceCode, tarCharge, ecbCharge, tarNewCharge, ecbNewCharge};
					writer.writeNext(nextLine);
            	}
			}
        }
	}

	/*
	 * Method to check the differences between the two files and add them to the csv with ESB as the hash
	 */
	private void checkDifferencesEBCBHash(){

		Sheet tarSheet = tarReader.getSheet();
		HashMap<String, Row> ecbHashMap = ecbReader.getHashMap();

        Iterator<Row> tarRowIt = tarSheet.rowIterator();
        
        //Skip two header lines
        tarRowIt.next();
        tarRowIt.next();

        Row tarRow;

        //Iterate through all rows
        while (tarRowIt.hasNext()) {
            tarRow = tarRowIt.next();

			String spa = df.formatCellValue(tarRow.getCell(0));
            String serviceCode =  df.formatCellValue(tarRow.getCell(1));

			
            // check that the transaction exists in tar
			if(!ecbHashMap.containsKey(spa + serviceCode)){
				// if it doesn't exist report it
				String tarCharge = df.formatCellValue(tarRow.getCell(2));
				String tarNewCharge = df.formatCellValue(tarRow.getCell(4));
				String[] nextLine = new String[]{spa, serviceCode, tarCharge, "Does Not Exist,", tarNewCharge, "Does not Exist,"};
					writer.writeNext(nextLine);
			}
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
