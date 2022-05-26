package model;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Library that holds all the AppLabels and AppFiles used
 * in a profile.
 * Has a map of Name -> AppLabels, and file path -> AppFile.
 * 
 * @authors Christopher, 
 */
@SuppressWarnings("serial")
public class Library implements Serializable {

	/** A map of unique file paths to a AppFile Object. */
	private HashMap<String, AppFile> fileLibrary;
	
	/** A map of unique label names to a AppLabel Object. */
	private HashMap<String, AppLabel> labelLibrary;
	
	public Library() {
		fileLibrary = new HashMap<String, AppFile>();
		labelLibrary = new HashMap<String, AppLabel>();	
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

			return true;
		}
		
		return false;
	}
	
	/**
	 * 
	 * @author Christopher
	 * @param theFile
	 * @return true if theFile exists
	 */
	public boolean containsFile(AppFile theFile) {
		return fileLibrary.containsValue(theFile);
	}
	
	/**
	 * 
	 * @author Christopher
	 * @param theLabel
	 * @return true if theLabel exists
	 */
	public boolean containsLabel(AppLabel theLabel) {
		return labelLibrary.containsValue(theLabel);
	}
	
	/**
	 * 
	 * @author Christopher
	 * @param theFilePath
	 * @return true if successful
	 */
	public boolean addFile(final String theFilePath) {
		if (fileLibrary.containsKey(theFilePath))
			return false;
		else
			fileLibrary.put(theFilePath, new AppFile(theFilePath));
		return true;
	}
	
	/**
	 * 
	 * @author Christopher
	 * @param theFilePath
	 * @return true if successful
	 */
	public boolean removeFile(final String theFilePath) {
		if (fileLibrary.containsKey(theFilePath)) {
			fileLibrary.remove(theFilePath);
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @author Christopher
	 * @param theLabelName
	 * @return true if successful
	 */
	public boolean addLabel(final String theLabelName) {
		if (labelLibrary.containsKey(theLabelName))
			return false;
		else
			labelLibrary.put(theLabelName, new AppLabel(theLabelName));
		return true;
	}
	
	/**
	 * 
	 * @author Christopher
	 * @param theLabelName
	 * @return true if successful
	 */
	public boolean removeLabel(final String theLabelName) {
		if (labelLibrary.containsKey(theLabelName)) {
			labelLibrary.remove(theLabelName);
			return true;
		}
		return false;
	}
	
	/**
	 * TODO Make Better
	 * toString method for Library.
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		String Files = "";
		for (AppFile f : fileLibrary.values()) {
			Files += f.getFileName() + ", ";
		}
		
		String Labels = "";
		for (AppLabel l : labelLibrary.values()) {
			Labels += l.getMyName() + ", ";
		}
		
		return (" Library: Files {" + Files + "} Labels {" + Labels + "}");
	}
	
}

