package model;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileSystemView;


/**
 * File object that wraps a java File Object.<p>
 * <b>Includes:</b><br>
 * 1) A collection of AppLabels associated with that File<br>
 * 2) Methods to add and remove labels from that collection<br>
 * 3) Several properties of the file<br>
 * 4) A method to open the file in the users OS<br>
 * 
 * @authors Christopher
 */
public class AppFile implements Serializable {
	
	/**
	 * serial Version UID.
	 * @see Serializable
	 */
	private static final long serialVersionUID = -7429108953443696381L;
	
	/** Size of the File Icon */
	private static final int ICON_WIDTH = 64;
	private static final int ICON_HEIGHT = 64;
	
	/**
	 * File System View utility object.
	 * @see FileSystemView
	 */
	private transient FileSystemView fileSystem;
	
	/** File icon used by OS */
	private transient Icon fileIcon;
	
	/**
	 * Type description for a file, directory, or folder as it would
	 * be displayed in a system file browser.<p>Check out
	 * {@link FileSystemView#getSystemTypeDescription() SystemTypeDescription}
	 * for more information.<br>
	 * 
	 */
	private transient String fileTypeDescription;
	
	/** File path of this AppFile. */
	private String fileFullPath;
	
	/** Name of this file. Does not include the file extension.*/
	private String fileName;

	/** Extension of this file. <b>Example:</b> "txt" | "jpg"*/
	private String fileExtension;
	
	/**
	 * Name with Extension of this file.
	 * <b>Example:</b> "words.txt"
	 */
	private String fileNameAndExtension;
	
	/**
	 * Underlying File object to get name and icons.
	 */
	private File jFile;
	
	/**
	 * Collection of AppLabels that have been applied
	 * to this AppFile.
	 */
	private ArrayList<AppLabel> labelsArray;
	
	
	public AppFile(final AppFile theFile) {
		this(theFile.getFilePath());
	}
	
	public AppFile(final String theFilePath) {
		jFile = new File(theFilePath);
		fileSystem = FileSystemView.getFileSystemView();
		fileFullPath = theFilePath;
		
		fileNameAndExtension = fileSystem.getSystemDisplayName(jFile);
		splitFileNameAndExt();
		
		labelsArray = new ArrayList<>();
		
		fileIcon = fileSystem.getSystemIcon(jFile, ICON_WIDTH, ICON_HEIGHT);		
		fileTypeDescription = fileSystem.getSystemTypeDescription(jFile);

	}
	
	public void updateFileInfo() {
		fileSystem = FileSystemView.getFileSystemView();
		fileIcon = fileSystem.getSystemIcon(jFile, ICON_WIDTH, ICON_HEIGHT);
		fileTypeDescription = fileSystem.getSystemTypeDescription(jFile);
	}
	
	/**
	 * Getter for the file path of this File.
	 * 
	 * @author Christopher
	 * @return {@link String} the full file path
	 */
	public String getFilePath() {
		return fileFullPath;
	}

	/**
	 * Getter for the name of this file. Does not include the file extension.<p> 
	 * <b>Example</b> for a file Notes.txt this method returns "Notes"
	 * @author Christopher
	 * @return {@link String} the file name, no extension
	 */
	public String getFileName() {
		return fileName;
	}
	
	/**
	 * Getter for the extension of this file.<p>
	 * <b>Example</b> for a file Notes.txt this method returns "txt"
	 * @author Christopher
	 * @return {@link String} file extension
	 */
	public String getFileExtension() {
		return fileExtension;
	}
	
	/**
	 * Getter for the file name and extension of this file as one string.<p>
	 * <b>Example</b> for a file Notes.txt this method returns "Notes.txt"
	 * @author Christopher
	 * @return {@link String} the file Name with it's extension 
	 */
	public String getFullFileName() {
		return fileNameAndExtension;
	}
	
	/** Getter File Type Description of this file. */
	
	/**
	 * Getter for the file type description of this file used by the OS.
	 * @author Christopher
	 * @return {@link String} the file type description
	 */
	public String getFileTypeDescription() {
		if (Objects.nonNull(fileTypeDescription)) {
			return fileTypeDescription;
		}
		else {
			return "null description";
		}

	}

	/**
	 * Getter the icon the OS uses for this file.
	 * @author Christopher
	 * @return {@link Icon} the file's icon
	 */
	public Icon getFileIcon() {
		if (Objects.nonNull(fileIcon)) {
			return fileIcon;
		}
		else {
			return (Icon) (new ImageIcon("./icons/nullIcon"));
		}
	}
	
	/**
	 * Getter for the list of labels associated with this file.
	 * 
	 * @author Christopher
	 * @return an array of {@link AppLabel} associated to this File
	 */
	public AppLabel[] getLabelsArray() {
		AppLabel[] arr = new AppLabel[labelsArray.size()];
		int i = 0;
		for (AppLabel label : labelsArray) {
			arr[i] = label;
			i++;
		}
		
		return arr;
	}
	
	/**
	 * Adds a new {@link AppLabel} to this file's Labels collection.
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
	 * Removes a {@link AppLabel} in this file's Labels collection.
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
	 * Simply asks the user's operating system to open the file in
	 * whatever default application is associated with its file type.
	 * 
	 * @author Christopher
	 */
	public void openFileInOS() {
		try {
			Desktop.getDesktop().open(jFile);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	/**
	 * Splits the file name into a name and extension
	 * example: Words.txt -> [Words, txt]
	 * @author Christopher
	 */
	private void splitFileNameAndExt() {
		String fullFileName = jFile.getName();
		char[] fullCharArr = fullFileName.toCharArray();
		
		int pointLocation = -1;
		
		for (int i = 0; i < fullCharArr.length; i++) {
			if (fullCharArr[i] == '.') pointLocation = i;
		}
		
		fileName = fullFileName.substring(0, pointLocation);
		fileExtension = fullFileName.substring(pointLocation+1);
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
