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
	private HashMap<String, AppFile> fileLibrary;
	
	/** A map of unique label names to a AppLabel Object. */
	private HashMap<String, AppLabel> labelLibrary;
	
	public Library() {
		fileLibrary = new HashMap<String, AppFile>();
		labelLibrary = new HashMap<String, AppLabel>();	
	}
	
	/**
	 * Getter for fileLibrary.
	 * @return AppFile[] of all the AppFiles in this Library
	 */
	public ArrayList<AppFile> getFileLibraryArray() {
		ArrayList<AppFile> fileList = new ArrayList<>();
        final Set<String> keys = fileLibrary.keySet();
        Iterator<String> itr = keys.iterator();
        while(itr.hasNext()) {
        	fileList.add(fileLibrary.get(itr.next()));
        }
        
        return fileList;

	}
	
	/**
	 * Getter for labelLibrary.
	 * @return AppLabel[] of all the AppFiles in this Library
	 */
	public ArrayList<AppLabel> getLabelLibraryArray() {
		ArrayList<AppLabel> labelList = new ArrayList<>();
        final Set<String> keys = labelLibrary.keySet();
        Iterator<String> itr = keys.iterator();
        while(itr.hasNext()) {
        	labelList.add(labelLibrary.get(itr.next()));
        }
        
        return labelList;
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
	
	/**
	 * Searches for a AppFile in the file library.
	 * 
	 * @author Christopher
	 * @param theFile
	 * @return true if theFile exists
	 */
	public boolean containsFile(AppFile theFile) {
		return fileLibrary.containsValue(theFile);
	}
	
	/**
	 * Searches for a AppLabel in the Label library.
	 * 
	 * @author Christopher
	 * @param theLabel
	 * @return true if theLabel exists
	 */
	public boolean containsLabel(AppLabel theLabel) {
		return labelLibrary.containsValue(theLabel);
	}
	
	//////////////// Files //////////////////////////////////////////////////
	
	/**
	 * Adds an AppFile to this Library
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
	 * Removes an AppFile from this library.
	 * 
	 * @author Christopher
	 * @param theFilePath of the file to be removed
	 * @return true if successful
	 */
	public boolean removeFile(final String theFilePath) {
		return removeFile(fileLibrary.get(theFilePath));
	}
	
	/**
	 * Removes an AppFile from this library.
	 * 
	 * @author Christopher
	 * @param theFile to be removed
	 * @return true if successful
	 */
	public boolean removeFile(final AppFile theFile) {
		if (containsFile(theFile)) {
			//Removes the reference to theFile in each Label connected with it
			AppLabel[] labelsArr = theFile.getLabelsArray();
			for (AppLabel label : labelsArr)
				label.removeFile(theFile);
			//
			fileLibrary.remove(theFile.getFileName());
			return true;
		}
		else return false;
	}
	
	///////////// Labels /////////////////////////////////////////////////////
	
	/**
	 * Adds an AppLabel to this Library.
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
	 * Removes an AppLabel from this Library.
	 * 
	 * @author Christopher
	 * @param theLabelName
	 * @return true if successful
	 */
	public boolean removeLabel(final String theLabelName) {
		return removeLabel(labelLibrary.get(theLabelName));
	}
	
	/**
	 * Removes an AppLabel from this Library.
	 * 
	 * @author Christopher
	 * @param theLabelName
	 * @return true if successful
	 */
	public boolean removeLabel(final AppLabel theLabel) {
		if (containsLabel(theLabel)) {
			//Removes the reference to theLabel in each AppFile connected with it
			AppFile[] filesArr = theLabel.getFilesArray();
			for (AppFile file : filesArr)
				file.removeLabel(theLabel);
			//
			fileLibrary.remove(theLabel.getMyName());
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

