package model;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class AppFile implements Serializable {
	
	/** TODO JavaDoc */
	private String filePath;
	
	/** TODO JavaDoc */
	private String fileName;
	
	private File jFile;
	
	/** TODO JavaDoc */
	private ArrayList<Label> labelsArray;
	
	
	public AppFile(final String theFilePath) {
		filePath = theFilePath;
		jFile = new File(theFilePath);
		fileName = jFile.getName();
		labelsArray = new ArrayList<>();
	}
	
	/**
	 * Getter for the file path of this File.
	 * 
	 * @author Christopher
	 * @return filePath as a String
	 */
	public String getFilePath() {
		return filePath;
	}
	
	/**
	 * Getter for the name of this file
	 * 
	 * @author Christopher
	 * @return fileName as a String
	 */
	public String getFileName() {
		return fileName;
	}
	
	/**
	 * Getter for the list of labels associated with this file.
	 * 
	 * @author Christopher
	 * @return an array of Labels associated to this File
	 */
	public Label[] getLabelsArray() {
		final int count = labelsArray.size();
		final Label[] currentLabels = new Label[count];
		
		for (int i = 0; i < count; i++) {
			currentLabels[i] = labelsArray.get(i);
		}
		
		return currentLabels;
	}
	
	/**
	 * Adds a new Label to this file's Labels collection.
	 * 
	 * @author Christopher
	 * @param theLabel to be added
	 * @return true if addition succeeded
	 */
	public boolean addLabel(final Label theLabel) {
		if (labelsArray.contains(theLabel)) {
			return false;
		}
		else {
			labelsArray.add(theLabel);
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
	public boolean removeLabel(final Label theLabel) {
		if ( labelsArray.isEmpty()  ||  !(labelsArray.contains(theLabel)) ) {
			return false;
		}
		else {
			labelsArray.remove(theLabel);
			return true;
		}
	}
	
	/** TODO: JavaDoc*/
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("File {\"");
		sb.append(filePath);
		sb.append("\"}");
		return sb.toString();
	}
}
