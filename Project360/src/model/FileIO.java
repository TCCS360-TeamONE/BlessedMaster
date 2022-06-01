package model;

import java.awt.Component;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Handles all the file saving and loading operations.
 * 
 * @author Alan, Christopher 
 *
 */
public class FileIO {
	
	public static final String PROFILE_EXT = "prf";
	
	public static final FileNameExtensionFilter PROFILE_EXT_FILTER = 
			new FileNameExtensionFilter("Profile File", PROFILE_EXT);

	//////////////////////////////////////////
	
	/**
	 * Imports a Profile object that has been saved as a file @
	 * Enforced file extension {@value FileIO#PROFILE_EXT}
	 * 
	 * @author Alan
	 * @return true if the import was successful 
	 */
	public static boolean importProfile(Component theParent) {
		File file = loadFile(theParent, PROFILE_EXT_FILTER);
		if (file == null) {
			System.err.println("FileIO.importProfile(): file is null");
			return false;
		}

		Object importObject = loadObjectFile(file);
		if (importObject == null) {
			System.err.println("FileIO.importProfile(): ImportObject is null");
			return false;
		}
		else if (importObject.getClass() != model.Profile.class) {
			System.err.println("FileIO.importProfile(): Import Object is not a Profile Object " +
								importObject.getClass());
			return false;
		}
		
		Profile profileImport = (Profile) importObject;
		if (main.Main.mainProfileManger.userAlreadyExists(profileImport.getUserName())) {
			System.err.println("FileIO.importProfile(): This User already exists");
			return false;
		}

		main.Main.mainProfileManger.addProfile(profileImport);
		return true;
	}
	
	/**
	 * Exports a Profile object and saves it to a user selected file
	 * Enforced file extension {@value FileIO#PROFILE_EXT}
	 * 
	 * @author Alan
	 * @param theProfile to be exported and saved to file suggested 
	 * @return true if the export was successful 
	 */
	public static boolean exportProfile(Component theParent, Profile theProfile) {		
		String suggestedName = theProfile.getUserName() + "." + PROFILE_EXT;
		
		File file = saveFile(theParent, suggestedName, PROFILE_EXT_FILTER);
		if (file == null) {
			System.err.println("FileIO.exportProfile(): file is null");
			return false;
		}

		if(!saveObjectFile(theProfile, file)) {
			System.err.println("FileIO.exportProfile(): Object File could not be saved");
		}
		
		return true;
	}
	
///////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Opens a {@link JFileChooser} for the user to select a file and returns that file.
	 * 
	 * @author Christopher
	 * @see JFileChooser
	 * @see Component
	 * @see FileNameExtensionFilter
	 * @param theParent a Java {@link Component} for the Open Dialog to be connected to
	 * @param theFilters array of {@link FileNameExtensionFilter} to limit what files can be loaded
	 * @return A java File object or Null if no file was selected
	 */
	public static File loadFile(Component theParent, FileNameExtensionFilter... theFilters) {
		JFileChooser fileChooser = new JFileChooser();
		File myFile = null;
		
		if (theFilters != null) {
			for (FileNameExtensionFilter filter : theFilters) {
				fileChooser.addChoosableFileFilter(filter);
				fileChooser.setFileFilter(filter);
			}
		}
		
		int choice = fileChooser.showOpenDialog(theParent);
		
		
		if (choice == JFileChooser.APPROVE_OPTION) {
			myFile = new File(fileChooser.getSelectedFile().getAbsolutePath());
		} else {
			System.out.println("canceled");
		}
		
		return myFile;
	}
	
	
	/**
	 * Opens a {@link JFileChooser} and allows the user to select a file to be saved
	 * to disk, and returns the file.
	 * 
	 * @author Christopher
	 * @see JFileChooser
	 * @see Component
	 * @see FileNameExtensionFilter
	 * @param theParent Component of the <b>JFileChooser</b>
	 * @param defaultFileName of the file to be saved
	 * @param theFilters array of {@link FileNameExtensionFilter} to limit what files can be saved
	 * @return A File to be saved
	 */
	public static File saveFile(Component theParent, String defaultFileName, FileNameExtensionFilter... theFilters) {
		JFileChooser fileChooser = new JFileChooser();
		File myFile = null;
		
		if (theFilters != null) {
			for (FileNameExtensionFilter filter : theFilters) {
				fileChooser.addChoosableFileFilter(filter);
				fileChooser.setFileFilter(filter);
			}
		}
		
		if (defaultFileName != null) {
			fileChooser.setSelectedFile(new File(defaultFileName));
		}
		
		int choice = fileChooser.showSaveDialog(theParent);
		
		if (choice == JFileChooser.APPROVE_OPTION) {
			myFile = new File(fileChooser.getSelectedFile().getAbsolutePath());
		}
		
		return myFile;
	}
	
///////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Loads <b>theFile</b> and, assuming it is a serialized object, returns the
	 * <b>theObject</b> contained in that file.
	 * 
	 * @author Christopher
	 * @param theFile that containers an <b>theObject<b>
	 * @return theObject or null if file could not be loaded
	 */
	public static Object loadObjectFile(File theFile) {
		if (!theFile.exists()) return null;
		
		Object theObject = null;
		try {
			FileInputStream dataFile = new FileInputStream(theFile);
			ObjectInputStream dataIn = new ObjectInputStream(dataFile);
			theObject = dataIn.readObject();
			dataIn.close();
			dataFile.close();
		} catch (ClassNotFoundException e) {
			System.err.println("FileIO.openObjectFile(): " + e);
			//e.printStackTrace();
		} catch (IOException e) {
			System.err.println("FileIO.openObjectFile(): " + e);
			//e.printStackTrace();
		}
		
		return theObject;
	}
	
	/**
	 * Saves the Object <b>theData</b> to <b>theFile</b> passed into this method.
	 * 
	 * @author Christopher
	 * @param theObject and Object to be serialized and saved to a <b>theFile</b>
	 * @param theFile where the <b>theData</b> is stored
	 * @return true if the object was saved to file
	 */
	public static boolean saveObjectFile(Object theObject, File theFile) {
		try {
			FileOutputStream dataFile = new FileOutputStream(theFile);
			ObjectOutputStream dataOut = new ObjectOutputStream(dataFile);
			dataOut.writeObject(theObject);
			dataOut.close();
			dataFile.close();
			return true; // return true because we can only get here if nothing got messed up
		} catch (NullPointerException e) {
			System.err.println("FileIO.saveObjectFile(): " + e);
			//e.printStackTrace();
		} catch (FileNotFoundException e) {
			System.err.println("FileIO.saveObjectFile(): " + e);
			//e.printStackTrace();
		} catch (IOException e) {
			System.err.println("FileIO.saveObjectFile(): " + e);
			//e.printStackTrace();
		}
		
		return false; // only gets run if the return true in the try block does not get executed
	}

}
