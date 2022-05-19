package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileIO {
	
	public void chooseFile() {
		
	}
	
	public void importSettings() {
		
	}
	
	public void exportSettings() {
		
	}
	
	public void saveDatabase() {
		
	}
	
	public void loadDatabase() {
		
	}
	
	
//////////////////////////////////////////////////////////////////////////
///// I copied what's below this from my assignment 3 in TCCS 342,  /////
////  its just a jump off point for the File read/write.           /////

	/**
	 * Reads text from input.txt and return each line of that
	 * text as a String array.
	 * 
	 * @author Christopher Henderson
	 * @return String array of each line of text
	 */
	private static String[] readData() {
		String path = "./files/input.txt";
		String allLines = "";
		
		try (BufferedReader reader = new BufferedReader(new FileReader(path))){
            String line;

            while ((line = reader.readLine()) != null) {
            	allLines += line + "\n";
            }
            
            reader.close();
		}
		catch (final IOException e) {
			e.printStackTrace();
		}

		return allLines.split("\n");
	}
	
	
	/**
	 * Writes theText to output.txt
	 * 
	 * @author Christopher Henderson
	 * @param theText to be written
	 */
	private static void writeData(String theText) {
		String path = "./files/output.txt";
		
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))){
			writer.write(theText);
			writer.close();
		}
		catch (final IOException e) {
			e.printStackTrace();
		}
	}

}
