package model;

import java.util.ArrayList;

public class File {
	
	/** TODO */
	private String myFilePath;
	
	/** TODO */
	private ArrayList<Label> myLabels;
	
	
	public File(final String theFilePath) {
		myFilePath = theFilePath;
		myLabels = new ArrayList<>();
	}
	
	/**
	 * Getter for the list of labels associated with this file.
	 * 
	 * @author Chris H.
	 * @return an array of Labels associated to this File
	 */
	public Label[] getLabels() {
		final int count = myLabels.size();
		final Label[] currentLabels = new Label[count];
		
		for (int i = 0; i < count; i++) {
			currentLabels[i] = myLabels.get(i);
		}
		
		return currentLabels;
	}
	
	/**
	 * Adds a new Label to this file's Labels collection.
	 * 
	 * @author Chris H.
	 * @param theLabel to be added
	 * @return true if addition succeeded
	 */
	public boolean addLabel(final Label theLabel) {
		if (myLabels.contains(theLabel)) {
			return false;
		}
		else {
			myLabels.add(theLabel);
			return true;
		}
		
	}
	
	/**
	 * Removes a Label in this file's Labels collection.
	 * 
	 * @author Chris H.
	 * @param theLabel to be removed
	 * @return true if removal succeeded
	 */
	public boolean removeLabel(final Label theLabel) {
		if ( myLabels.isEmpty()  ||  !(myLabels.contains(theLabel)) ) {
			return false;
		}
		else {
			myLabels.remove(theLabel);
			return true;
		}
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("File {\"");
		sb.append(myFilePath);
		sb.append("\"}");
		return sb.toString();
	}
}
