package com.laldover04;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.opencsv.CSVWriter;

public class CSVWriterHandler {
	CSVWriter writer;

    public CSVWriterHandler(String filePath){
		// first create file object for file placed at location 
		// specified by filepath 
		File csvfile = new File(filePath); 

		try { 
			// create csvWriter from filewriter
			FileWriter outputFile = new FileWriter(csvfile); 
			writer = new CSVWriter(outputFile); 
	
			String header[] = {"SPA", "Service Code", "Tar Charge", "ECB Charge", "Tar New Charge", "ECB New Charge"};
			writer.writeNext(header); 
	
			// String[] row1 = {"2342", "df03", "12", "13", "12.5", "13.5"};
			// String[] row2 = {"234002", "df003", "120", "130", "12.50", "13.50"};
			//writer.writeNext(row2); 
	
		} 
		catch (IOException e) { 
			e.printStackTrace(); 
		} 
	}

	public void addLine(String[] row){
		writer.writeNext(row);
	}

    public  void close() 
	{ 
		try {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	} 

}
