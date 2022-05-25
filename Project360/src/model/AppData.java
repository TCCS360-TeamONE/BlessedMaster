package model;

import java.io.Serializable;

import model.ProfileManager.Profile;


/**
 * Handles all the president data not hard-coded into the application.
 * 
 * @author Christopher
 */
public class AppData {
	
	/** Will be a private field later! */
	public static String[] appOwnerInfo;
	
	/** Will be a private field later! */
	public static ProfileManager appProfiles;
	
	private static Database appDatabase;
	
	public static void start() {
		appDatabase = (Database) FileIO.loadData();
		appOwnerInfo = appDatabase.appOwnerInfo;
		appProfiles = appDatabase.profiles;
	}
	
	public Profile getProfile(int index) {
		return appProfiles.getProfileList().get(index);
	}
	
	public Profile getProfile(String theUserName) {
		for(Profile p : appProfiles.getProfileList()) {
			if (p.getUserName() == theUserName)
				return p;
		}
		return null;
	}
	
	public Library getLibrary(Profile theProfile) {
		return theProfile.getLibrary();
	}
	
	public Library getLibrary(int index) {
		return getProfile(index).getLibrary();
	}
	
	public Library getLibrary(String theUserName) {
		return getProfile(theUserName).getLibrary();
	}
	
	@SuppressWarnings("serial")
	private class Database implements Serializable {
		private String[] appOwnerInfo;
		private ProfileManager profiles;
	}
}


//	OLD STUFF ///////////////
//
//	/////////////// FOR TESTING ///////////////	
//	public static void main(String[] args) {
//		// Build and fill library with test data
//		AppData testCase = new AppData();
//		testCase.profiles.createNewUser("Timm", "1234");
//		ArrayList<Profile> users = testCase.profiles.getProfileList();
//		Profile Timmy = users.get(0);
//		Library myLibrary = Timmy.getLibrary();
//		
//		myLibrary.addFile("./Files/f1.txt");
//		myLibrary.addFile("./Files/f2.txt");
//		myLibrary.addFile("./Files/f3.txt");
//		myLibrary.addFile("./Files/f4.txt");
//		myLibrary.addLabel("Label 1");
//		myLibrary.addLabel("Label 2");
//		myLibrary.addLabel("Label 3");
//		myLibrary.addLabel("Label 4");
//		System.out.println(testCase);
//		
//		//save object to byte code,
//		//byte[] code = testCase.getLibraryBytecode();
//		testCase.exportDataBase();
//		Timmy.resetLib();
//		System.out.println(testCase);
//		
//		// load original library from byte code
//		//testCase.loadLibraryBytecode(code);
//		testCase.importDataBase();
//		System.out.println(testCase);
//		
//	} /////////////// END TESTING ///////////////	
//	
//	
//	/** TODO: JavaDoc*/
//	private ProfileManager profiles;
//	
//	public AppData() {
//		profiles = new ProfileManager();
//	}
//	
//	/** TODO: JavaDoc*/
//	public void exportDataBase() {
//		byte[] byteCode = getLibraryBytecode();
//		FileIO.saveDatabase(byteCode);
//	}
//	
//	/** TODO: JavaDoc*/
//	public void importDataBase() {
//		byte[] byteCode = FileIO.loadDatabase();
//		loadLibraryBytecode(byteCode);
//	}
//	
//	/** TODO: JavaDoc*/
//	public byte[] getLibraryBytecode() {
//		ByteArrayOutputStream byteData = new ByteArrayOutputStream();
//		ObjectOutputStream outStream = null;
//		byte[] profilesBytecode = null;
//		
//		try {
//			outStream = new ObjectOutputStream(byteData);
//			outStream.writeObject(profiles);
//			outStream.flush();
//			profilesBytecode = byteData.toByteArray();
//		} catch (IOException ex) {
//			ex.printStackTrace();
//		}
//		
//		return profilesBytecode;
//	}
//	
//	/** TODO: JavaDoc*/
//	public boolean loadLibraryBytecode(final byte[] theLibraryBytecode) {
//		ByteArrayInputStream byteData = new ByteArrayInputStream(theLibraryBytecode);
//		ObjectInputStream inStream = null;
//		Object importedProfiles = null;
//		try {
//			inStream = new ObjectInputStream(byteData);
//			importedProfiles = inStream.readObject();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		if (importedProfiles != null) {
//			profiles= (ProfileManager) importedProfiles;
//			return true;
//		} else {
//			return false;
//		}
//	}
//	
//	@Override /** TODO: JavaDoc*/
//	public String toString() {
//		return profiles.toString();
//	}
//
//}
