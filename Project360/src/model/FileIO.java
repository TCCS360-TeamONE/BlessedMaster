package model;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

/**
 * Handles all the file saving and loading operations.
 * 
 * @author Alan, Christopher 
 *
 */
public class FileIO {
	
	/**
	 * Opens a {@link JFileChooser} for the user to select a file and returns that file.
	 * @return A java File object or Null if no file was selected
	 */
	public static File openFile() {
		return openFile(null, null);
	}
	
	/**
	 * Opens a {@link JFileChooser} for the user to select a file and returns that file.
	 * @param theParent a Java {@link Component} for the Open Dialog to be connected to
	 * @return A java File object or Null if no file was selected
	 */
	public static File openFile(Component theParent) {
		return openFile(theParent, null);
	}
	
	/**
	 * Opens a {@link JFileChooser} for the user to select a file and returns that file.
	 * @param theFilters array of {@link FileNameExtensionFilter} to limit what files can be loaded
	 * @return A java File object or Null if no file was selected
	 */
	public static File openFile(FileNameExtensionFilter[] theFilters) {
		return openFile(null, theFilters);
	}	
	
	/**
	 * Opens a {@link JFileChooser} for the user to select a file and returns that file.
	 * @param theParent a Java {@link Component} for the Open Dialog to be connected to
	 * @param theFilters array of {@link FileNameExtensionFilter} to limit what files can be loaded
	 * @return A java File object or Null if no file was selected
	 */
	public static File openFile(Component theParent, FileNameExtensionFilter[] theFilters) {
		JFileChooser fileChooser = new JFileChooser();
		File myFile = null;
		int choice = fileChooser.showOpenDialog(theParent);
		
		if (theFilters != null) {
			for (FileNameExtensionFilter fnef : theFilters) {
				fileChooser.setFileFilter(fnef);
			}
		}
		
		if (choice == JFileChooser.APPROVE_OPTION) {
			myFile = new File(fileChooser.getSelectedFile().getAbsolutePath());
		}
		else System.out.println("canceled");
		
		return myFile;
	}

	
	public static void importProfile(ProfileManager theProfiles) throws IOException{
		String[] importedProfile = null;
		
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getDefaultDirectory());
		jfc.setDialogTitle("Import profile");
		
		int returnValue = jfc.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) 
			importedProfile = readFile(jfc.getSelectedFile().getAbsolutePath());
		
		if (importedProfile != null && importedProfile.length != 0) {
			String[] profileData = importedProfile[0].split("\"");
			String profileName = profileData[1];
			String profilePass = profileData[3];
			if (!theProfiles.userAlreadyExists(profileName)) {
				theProfiles.createNewUser(profileName, profilePass);
			} else {
				String alertMessage = 	"Profile \""
										+ profileName
										+ "\" already exists. Please try importing a different profile.";
				JOptionPane.showMessageDialog(null, alertMessage);
			}
		}
			
	}
	
	public static void exportProfile(ProfileManager.Profile theProfile) throws IOException{
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getDefaultDirectory());
		jfc.setDialogTitle("Export profile");
		
		int returnValue = jfc.showSaveDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION)
			writeFile(jfc.getSelectedFile().getAbsolutePath(), theProfile.toString());
		
	}
	
	
	
	/**
	 * Saves the Object <Strong>theData</Strong> to <Strong>theFile</Strong>
	 * passed into this method.
	 * 
	 * @param theData and Object to be serialized and saved to a <Strong>theFile</Strong>
	 * @param theFile where the <Strong>theData</Strong> is stored
	 * @return
	 */
	public static boolean saveData(Object theData, File theFile) {
		boolean isSaved = false;
		try {
			FileOutputStream dataFile = new FileOutputStream(theFile);
			ObjectOutputStream dataOut = new ObjectOutputStream(dataFile);
			dataOut.writeObject(theData);
			dataOut.close();
			dataFile.close();
			isSaved = true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return isSaved;
	}
	
	/**
	 * Loads <Strong>theFile</Strong> and, assuming it is a serialized object, returns the
	 * <Strong>theObject</Strong> contained in that file.
	 * 
	 * @param theFile that containers an <Strong>theObject<Strong>
	 * @return theObject
	 */
	public static Object loadData(File theFile) {
		if (!theFile.exists()) return null;
		
		Object data = null;
		try {
			FileInputStream dataFile = new FileInputStream(theFile);
			ObjectInputStream dataIn = new ObjectInputStream(dataFile);
			data = dataIn.readObject();
			dataIn.close();
			dataFile.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return data;
	}
	

	/**
	 * Reads in file located at thePath in to a String[] indexed per line
	 * 
	 * @author Christopher Henderson, Alan Thompson
	 * @param thePath of file to be read
	 * @return String[] indexed per line
	 */
	private static String[] readFile(String thePath) throws IOException {
		String data = "";
		BufferedReader bRead = new BufferedReader(new FileReader(thePath));
		
		String currLine;
		while ( (currLine = bRead.readLine()) != null )
			data += currLine + "\n";
		
		bRead.close();
		return data.split("\n");
	}
	
	/**
	 * Writes theData to file at thePath
	 * 
	 * @author Christopher Henderson, Alan Thompson
	 * @param thePath of the file to be written
	 * @param theData to be written
	 */
	private static void writeFile(String thePath, String theData) throws IOException {
		BufferedWriter bWrite = new BufferedWriter(new FileWriter(thePath));
		bWrite.write(theData);
		bWrite.close();
	}

}
