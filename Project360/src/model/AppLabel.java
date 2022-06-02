package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Label object that includes a collection of AppFiles
 * associated with this AppLabel, and has methods to add
 * and remove AppFiles from that collection.
 * 
 * @authors Christopher, 
 */
public class AppLabel implements Serializable{
	
	/**
	 * serial Version UID.
	 * @see Serializable
	 */
	private static final long serialVersionUID = 4285037443708081840L;

	/** Name of this Label. */
	private String labelName;
	
	/** Collection of Files that have this AppLabels applied to them. */
	private ArrayList<AppFile> filesArray;
	
	public AppLabel(final String theLableName) {
		labelName = theLableName;
		filesArray = new ArrayList<>();
	}

	/** Getter for the name of this AppLabel. */
	public String getMyName() {
		return labelName;
	}

	/** Setter for the name of this AppLabel. */
	public void setMyName(String theLableName) {
		this.labelName = theLableName;
	}
	
	/**
	 * Getter for {@link #filesArray}.
	 * 
	 * @author Christopher
	 * @return Array of {@link AppFile} associated to this Label.
	 */
	public AppFile[] getFilesArray() {
		AppFile[] arr = new AppFile[filesArray.size()];
		int i = 0;
		for (AppFile file : filesArray) {
			arr[i] = file;
			i++;
		}
		
		return arr;
	}
	
	/**
	 * Adds a new Label to this file's Labels collection.
	 * 
	 * @author Christopher
	 * @param theFile to be added
	 * @return true if addition succeeded
	 */
	public boolean addFile(final AppFile theFile) {
		if (filesArray.contains(theFile)) {
			return false;
		}
		else {
			filesArray.add(theFile);
			return true;
		}
		
	}
	
	/**
	 * Removes a Label in this file's Labels collection.
	 * 
	 * @author Christopher
	 * @param theFile to be removed
	 * @return true if removal succeeded
	 */
	public boolean removeFile(final AppFile theFile) {
		if ( filesArray.isEmpty()  ||  !(filesArray.contains(theFile)) ) {
			return false;
		}
		else {
			filesArray.remove(theFile);
			return true;
		}
	}
	
	/**
	 * toString method for AppLabel.
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		//sb.append("Label {\"");
		sb.append(labelName);
		//sb.append("\"}");
		return sb.toString();
	}
}
