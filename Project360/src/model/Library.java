package model;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Library
 * @author Christopher
 *
 */
@SuppressWarnings("serial")
public class Library implements Serializable {

	/** TODO: JavaDoc*/
	private HashMap<String, AppFile> fileLibrary;
	
	/** TODO: JavaDoc*/
	private HashMap<String, AppLabel> labelLibrary;
	
	public Library() {
		fileLibrary = new HashMap<String, AppFile>();
		labelLibrary = new HashMap<String, AppLabel>();	
	}
	
	/** TODO: JavaDoc*/
	public boolean applyLabelToFile(AppFile theFile, AppLabel theLabel) {
		if (containsFile(theFile) &&
			containsLabel(theLabel)) {
			
			theFile.addLabel(theLabel);
			theLabel.addFile(theFile);
			return true;
		}
		
		return false;
	}
	
	/** TODO: JavaDoc*/
	public boolean removeLabelFromFile(AppFile theFile, AppLabel theLabel) {
		if (containsFile(theFile) &&
			containsLabel(theLabel)) {

			return true;
		}
		
		return false;
	}
	
	/** TODO: JavaDoc*/
	public boolean containsFile(AppFile theFile) {
		return fileLibrary.containsValue(theFile);
	}
	
	/** TODO: JavaDoc*/
	public boolean containsLabel(AppLabel theLabel) {
		return labelLibrary.containsValue(theLabel);
	}
	
	/** TODO: JavaDoc*/
	public boolean addFile(final String theFilePath) {
		if (fileLibrary.containsKey(theFilePath))
			return false;
		else
			fileLibrary.put(theFilePath, new AppFile(theFilePath));
		return true;
	}
	
	/** TODO: JavaDoc*/
	public boolean removeFile(final String theFilePath) {
		if (fileLibrary.containsKey(theFilePath)) {
			fileLibrary.remove(theFilePath);
			return true;
		}
		return false;
	}
	
	/** TODO: JavaDoc*/
	public boolean addLabel(final String theLabelName) {
		if (labelLibrary.containsKey(theLabelName))
			return false;
		else
			labelLibrary.put(theLabelName, new AppLabel(theLabelName));
		return true;
	}
	
	/** TODO: JavaDoc*/
	public boolean removeLabel(final String theLabelName) {
		if (labelLibrary.containsKey(theLabelName)) {
			labelLibrary.remove(theLabelName);
			return true;
		}
		return false;
	}
	
	@Override // TODO: Make better
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

