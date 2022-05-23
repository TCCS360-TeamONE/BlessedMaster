package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;

public class FileIO {
	
	public void chooseFile() {
		
	}
	
	public static void importProfile(Profiles theProfiles) throws IOException{
		String[] importedProfile = null;
		
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getDefaultDirectory());
		jfc.setDialogTitle("Import profile");
		
		int returnValue = jfc.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) 
			importedProfile = readFile(jfc.getSelectedFile().getAbsolutePath());
		
		if (importedProfile != null && importedProfile.length != 0) {
			String[] profileData = importedProfile[0].split("\"");
			String profileName = profileData[1];
			String profilePass = profileData[3];
			if (!theProfiles.userAlreadyExists(profileName)) {
				theProfiles.createNewUser(profileName, profilePass);
			} else {
				String alertMessage = 	"Profile \""
										+ profileName
										+ "\" already exists. Please try importing a different profile.";
				JOptionPane.showMessageDialog(null, alertMessage);
			}
		}
			
	}
	
	public static void exportProfile(Profiles.LoginProfile theProfile) throws IOException{
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getDefaultDirectory());
		jfc.setDialogTitle("Export profile");
		
		int returnValue = jfc.showSaveDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION)
			writeFile(jfc.getSelectedFile().getAbsolutePath(), theProfile.toString());
		
	}
	
	public void saveDatabase() {
		
	}
	
	public void loadDatabase() {
		
	}
	
	
	/**
	 * Reads in file located at thePath in to a String[] indexed per line
	 * 
	 * @author Christopher Henderson, Alan Thompson
	 * @param thePath of file to be read
	 * @return String[] indexed per line
	 */
	private static String[] readFile(String thePath) throws IOException {
		String data = "";
		BufferedReader bRead = new BufferedReader(new FileReader(thePath));
		
		String currLine;
		while ( (currLine = bRead.readLine()) != null )
			data += currLine + "\n";
		
		bRead.close();
		return data.split("\n");
	}
	
	/**
	 * Writes theData to file at thePath
	 * 
	 * @author Christopher Henderson, Alan Thompson
	 * @param thePath of the file to be written
	 * @param theData to be written
	 */
	private static void writeFile(String thePath, String theData) throws IOException {
		BufferedWriter bWrite = new BufferedWriter(new FileWriter(thePath));
		bWrite.write(theData);
		bWrite.close();
	}
	
	
	public static void main(String[] args) throws IOException {
		Profiles profiles = new Profiles();
		profiles.createNewUser("testProfile", "pass");
		
		Profiles.LoginProfile currentProfile = profiles.getProfileList().get(0);
		currentProfile.getFileList().add(new File("./testFile.txt"));
		currentProfile.getLabelList().add(new Label("Test Label 1"));
		currentProfile.getLabelList().add(new Label("Test Label 2"));
		
		
		exportProfile(currentProfile);
		
		importProfile(profiles);
	}

}
