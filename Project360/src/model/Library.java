package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * Library that holds all the AppLabels and AppFiles used
 * in a profile.
 * Has a map of Name -> AppLabels, and file path -> AppFile.
 * 
 * @authors Christopher, 
 */
public class Library implements Serializable {

	/**
	 * serial Version UID.
	 * @see Serializable
	 */
	private static final long serialVersionUID = 3216087415273229721L;

	/** A map of unique file paths to a AppFile Object. */
	private ArrayList<AppFile> fileLibrary;
	
	/** A map of unique label names to a AppLabel Object. */
	private ArrayList<AppLabel> labelLibrary;
	
	public Library() {
		fileLibrary = new ArrayList<AppFile>();
		labelLibrary = new ArrayList<AppLabel>();	
	}
	
	/**
	 * Getter for fileLibrary.
	 * @return AppFile[] of all the AppFiles in this Library
	 */
	public ArrayList<AppFile> getFileLibraryArray() {
		ArrayList<AppFile> files = new ArrayList<>();
		for (AppFile file : fileLibrary) {
			files.add(file);
		}
        return files;

	}
	
	/**
	 * Getter for labelLibrary.
	 * @return AppLabel[] of all the AppFiles in this Library
	 */
	public ArrayList<AppLabel> getLabelLibraryArray() {
		ArrayList<AppLabel> labels = new ArrayList<>();
		for (AppLabel label : labelLibrary) {
			labels.add(label);
		}
        return labels;
	}

	/**
	 * Associates an AppLabel to an AppFile.
	 * 
	 * @author Christopher
	 * @param theFile
	 * @param theLabel
	 * @return true if successful
	 */
	public boolean applyLabelToFile(AppFile theFile, AppLabel theLabel) {
		if (containsFile(theFile) &&
			containsLabel(theLabel)) {
			
			theFile.addLabel(theLabel);
			theLabel.addFile(theFile);
			return true;
		}
		
		return false;
	}
	
	/**
	 * Removes a AppLabel from an AppFile
	 * 
	 * @author Christopher
	 * @param theFile
	 * @param theLabel
	 * @return true if successful
	 */
	public boolean removeLabelFromFile(AppFile theFile, AppLabel theLabel) {
		if (containsFile(theFile) &&
			containsLabel(theLabel)) {
			
			theFile.removeLabel(theLabel);
			theLabel.removeFile(theFile);

			return true;
		}
		
		return false;
	}
	
	
	//////////////// Files //////////////////////////////////////////////////
	
	/**
	 * Searches for a AppFile in the file library.
	 * 
	 * @author Christopher
	 * @param theFile
	 * @return true if theFile exists
	 */
	public boolean containsFile(AppFile theFile) {
		return fileLibrary.contains(theFile);
	}
	public boolean containsFile(String theFilePath) {
		return !(getFile(theFilePath) == null);
	}
	
	/**
	 * Gets a AppFile from fileLibrary.
	 * 
	 * @author Christopher
	 * @param theFilePath
	 * @return AppFile, or Null if no AppFile exists
	 */
	public AppFile getFile(final String theFilePath) {
		for (AppFile file : fileLibrary) {
			if (file.getFilePath().equals(theFilePath))
				return file;
		}
		return null;
	}
	
	/**
	 * Adds an AppFile to this Library
	 * 
	 * @author Christopher
	 * @param theFilePath
	 * @return true if successful
	 */
	public boolean addFile(final String theFilePath) {
		if (containsFile(theFilePath)) {
			return false;
		}
		else {
			fileLibrary.add(new AppFile(theFilePath));
			return true;
		}
	}

	/**
	 * Removes an AppFile from this library.
	 * 
	 * @author Christopher
	 * @param theFilePath of the file to be removed
	 * @return true if successful
	 */
	public boolean removeFile(final String theFilePath) {
		return removeFile(getFile(theFilePath));
	}
	
	/**
	 * Removes an AppFile from this library.
	 * 
	 * @author Christopher
	 * @param theFile to be removed
	 * @return true if successful
	 */
	public boolean removeFile(final AppFile theFile) {
		if (theFile == null || !(containsFile(theFile))) {
			return false;
		}
		// Removes the reference to theFile in each Label connected with it
		AppLabel[] labelsArr = theFile.getLabelsArray();
		for (AppLabel label : labelsArr)
			label.removeFile(theFile);
		//
		fileLibrary.remove(theFile);
		return true;
	}
	
	///////////// Labels /////////////////////////////////////////////////////
	
	/**
	 * Searches for a AppLabel in the Label library.
	 * 
	 * @author Christopher
	 * @param theLabel
	 * @return true if theLabel exists
	 */
	public boolean containsLabel(AppLabel theLabel) {
		return labelLibrary.contains(theLabel);
	}
	public boolean containsLabel(String theLabelName) {
		return !(getLabel(theLabelName) == null);

	}
	
	/**
	 * Gets a AppLabel from the Label library.
	 * 
	 * @author Christopher
	 * @param theLabelName
	 * @return AppLabel or Null if no AppLabel exists
	 */
	public AppLabel getLabel(final String theLabelName) {
		for (AppLabel label : labelLibrary) {
			if (label.getMyName().equals(theLabelName))
				return label;
		}
		return null;
	}
	
	/**
	 * Adds an AppLabel to this Library.
	 * 
	 * @author Christopher
	 * @param theLabelName
	 * @return true if successful
	 */
	public boolean addLabel(final String theLabelName) {
		if (containsLabel(theLabelName)) {
			System.out.println("yo 221");
			return false;
		}
		else{
			labelLibrary.add(new AppLabel(theLabelName));
			return true;
		}
	}
	
	/**
	 * Removes an AppLabel from this Library.
	 * 
	 * @author Christopher
	 * @param theLabelName
	 * @return true if successful
	 */
	public boolean removeLabel(final String theLabelName) {
		return removeLabel(getLabel(theLabelName));
	}
	
	/**
	 * Removes an AppLabel from this Library.
	 * 
	 * @author Christopher
	 * @param theLabel
	 * @return true if successful
	 */
	public boolean removeLabel(final AppLabel theLabel) {
		System.out.println(theLabel);
		if (containsLabel(theLabel)) {
			//Removes the reference to theLabel in each AppFile connected with it
			AppFile[] filesArr = theLabel.getFilesArray();
			for (AppFile file : filesArr)
				file.removeLabel(theLabel);
			//
			System.out.println(labelLibrary.remove(theLabel));
			return true;
		}
		else return false;
	}
	
	//////////////////////////////////////////////////////////////////
	
	/**
	 * TODO: Make Better
	 * toString method for Library.
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		String Files = "";
		for (AppFile f : fileLibrary) {
			Files += f.getFileName() + ", ";
		}
		
		String Labels = "";
		for (AppLabel l : labelLibrary) {
			Labels += l.getMyName() + ", ";
		}
		
		return (" Library: Files {" + Files + "} Labels: {" + Labels + "}");
	}
	
}

