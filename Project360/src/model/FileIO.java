package model;

import java.io.*;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;


public class FileIO {

	public static final File appDataFile =  new File("data.ser");
	
	public void chooseFile() {
		
	}
	
	public static void importProfile(ProfileManager theProfiles) throws IOException{
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
	
	public static void exportProfile(ProfileManager.Profile theProfile) throws IOException{
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getDefaultDirectory());
		jfc.setDialogTitle("Export profile");
		
		int returnValue = jfc.showSaveDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION)
			writeFile(jfc.getSelectedFile().getAbsolutePath(), theProfile.toString());
		
	}
	
	
	
	/**
	 * Saves all the data stored in appData.
	 * 
	 * @author Christopher
	 */
	public static boolean saveData(Object data) {
		boolean isSaved = false;
		try {
			FileOutputStream dataFile = new FileOutputStream(appDataFile);
			ObjectOutputStream dataOut = new ObjectOutputStream(dataFile);
			dataOut.writeObject(data);
			dataOut.close();
			dataFile.close();
			isSaved = true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return isSaved;
	}
	
	/**
	 * Loads all the data stored in appData.
	 * 
	 * @author Christopher
	 */
	public static Object loadData() {
		if (!appDataFile.exists()) return null;
		
		Object data = null;
		try {
			FileInputStream dataFile = new FileInputStream(appDataFile);
			ObjectInputStream dataIn = new ObjectInputStream(dataFile);
			data = dataIn.readObject();
			dataIn.close();
			dataFile.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return data;
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
	

/**
 * @deprecated
 */
/* 
	public static void saveDatabase(byte[] objectArr) {
		try {
			FileOutputStream fOut = new FileOutputStream(DATABASE_PATH); // TODO: decide real path
			fOut.write(objectArr);
			fOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static byte[] loadDatabase() {
		Path path = Paths.get(DATABASE_PATH); // TODO: decide real path
		byte[] byteCode = null;
		try {
			byteCode = Files.readAllBytes(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return byteCode;
	}
*/

}
