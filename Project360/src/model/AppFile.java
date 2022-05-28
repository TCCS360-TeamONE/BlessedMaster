package model;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.filechooser.FileSystemView;

/**
 * File object that wraps a java File Object, includes a
 * collection of AppLabels associated with that File,
 * and has methods to add and remove labels from that collection.
 * 
 * @authors Christopher,  
 */
@SuppressWarnings("serial")
public class AppFile implements Serializable {
	
//	public static void main(String[] args) {
//		String dir = System.getProperty("user.dir") + File.separator + "TestFiles" + File.separator;
//		
//		AppFile test = new AppFile(dir + "test.docx");
//		System.out.println(test.getFileName());
//		System.out.println(test.getFilePath());
//		System.out.println(test.getFileTypeDescription());
//		System.out.println(test.fileSystem.getHomeDirectory());
//		System.out.println(System.getProperty("user.dir"));
//	}
//	
	/** Size of the File Icon */
	private static final int ICON_WIDTH = 64;
	private static final int ICON_HEIGHT = 64;
	
	/**
	 * TODO: Write Javadoc
	 */
	private final FileSystemView fileSystem;
	
	/** File path of this AppFile. */
	private String fileFullPath;
	
	/** Names of this AppFile. */
	private String fileName;
	
	/** File icon used by OS */
	private Icon fileIcon;
	
	/**
	 * Type description for a file, directory, or folder as it would
	 * be displayed in a system file browser.
	 * 
	 * TODO: link to FileSystemView.getSystemTypeDescription()
	 */
	private String fileTypeDescription;
	
	/**
	 * Underlying File object to get name and icons.
	 */
	private File jFile;
	
	/**
	 * Collection of AppLabels that have been applied
	 * to this AppFile.
	 */
	private ArrayList<AppLabel> labelsArray;
	
	public AppFile(final String theFilePath) {
		fileSystem = FileSystemView.getFileSystemView();
		jFile = fileSystem.createFileObject(theFilePath);
		
		fileFullPath = theFilePath;
		fileName = fileSystem.getSystemDisplayName(jFile);
		fileIcon = fileSystem.getSystemIcon(jFile, ICON_WIDTH, ICON_HEIGHT);
		fileTypeDescription = fileSystem.getSystemTypeDescription(jFile);
		
		labelsArray = new ArrayList<>();
	}
	
	/**
	 * Getter for the file path of this File.
	 * 
	 * @author Christopher
	 * @return filePath as a String
	 */
	public String getFilePath() {
		return fileFullPath;
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
	
	public String getFileTypeDescription() {
		return fileTypeDescription;
	}
	
	public Icon getFileIcon() {
		return fileIcon;
	}
	
	/**
	 * Getter for the list of labels associated with this file.
	 * 
	 * @author Christopher
	 * @return an array of Labels associated to this File
	 */
	public AppLabel[] getLabelsArray() {
		final int count = labelsArray.size();
		final AppLabel[] currentLabels = new AppLabel[count];
		
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
	public boolean addLabel(final AppLabel theLabel) {
		if (labelsArray.contains(theLabel)) {
			return false;
		} else {
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
	public boolean removeLabel(final AppLabel theLabel) {
		if ( labelsArray.isEmpty()  ||  !(labelsArray.contains(theLabel)) ) {
			return false;
		}
		else {
			labelsArray.remove(theLabel);
			return true;
		}
	}

	/**
	 * toString method for AppFile.
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("File {\"");
		sb.append(fileFullPath);
		sb.append("\"}");
		return sb.toString();
	}
}
