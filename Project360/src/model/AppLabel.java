package model;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class AppLabel implements Serializable{
	
	/** TODO: JavaDoc*/
	private String labelName;
	
	/** TODO: JavaDoc*/
	private ArrayList<AppFile> filesArray;
	
	public AppLabel(final String theLableName) {
		labelName = theLableName;
		filesArray = new ArrayList<>();
	}

	/** TODO: JavaDoc*/
	public String getMyName() {
		return labelName;
	}

	/** TODO: JavaDoc*/
	public void setMyName(String theLableName) {
		this.labelName = theLableName;
	}
	
	/**
	 * Getter for the list of Files associated with this Label.
	 * 
	 * @author Christopher
	 * @return an array of Files associated to this Label
	 */
	public AppFile[] getLabelsArray() {
		final int count = filesArray.size();
		final AppFile[] currentFiles = new AppFile[count];
		
		for (int i = 0; i < count; i++) {
			currentFiles[i] = filesArray.get(i);
		}
		
		return currentFiles;
	}
	
	/**
	 * Adds a new Label to this file's Labels collection.
	 * 
	 * @author Christopher
	 * @param theLabel to be added
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
	 * @param theLabel to be removed
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
	
	/** TODO: JavaDoc*/
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Label {\"");
		sb.append(labelName);
		sb.append("\"}");
		return sb.toString();
	}
}
