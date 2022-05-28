package model;

import java.io.File;
import java.io.Serializable;

import model.ProfileManager.Profile;


/**
 * Handles all the persistent data not hard-coded into the application.
 * 
 * @author Christopher 
 */
public class AppData {
	
	public static final File APP_DATA_FILE =  new File("data.ser");
	
	/** Stores the Database in memory. */
	private Database database;
	
	/**
	 * Initializes the database.
	 * @author Christopher
	 */
	public void start() {
		database = (Database) FileIO.loadData(APP_DATA_FILE);
		if (database == null) database = new Database();
	}
	
	/**
	 * Gets the AppOwner information.
	 * @author Christopher
	 * @return the AppOwner information
	 */
	public String[] getAppOwnerInfo() {
		return database.appOwnerInfo;
	}
	
	/**
	 * Getter for Profiles, index based.
	 * @author Christopher
	 * @param index of desired profile
	 * @return Profile[<strong>index</strong>]
	 */
	public Profile getProfile(int index) {
		return database.profiles.getProfileList().get(index);
	}
	
	/**
	 * Getter for Profiles, user name based.
	 * @author Christopher
	 * @param userName of desired profile
	 * @return Profile of <strong>userName</strong>
	 */
	public Profile getProfile(String userName) {
		for(Profile p : database.profiles.getProfileList()) {
			if (p.getUserName() == userName)
				return p;
		}
		return null;
	}
	
	/**
	 * Getter for Library, Profile based.
	 * @author Christopher
	 * @param profile with desired library
	 * @return library of <strong>profile</strong>
	 */
	public Library getLibrary(Profile profile) {
		return profile.getLibrary();
	}
	
	/**
	 * Getter for Library, index based.
	 * @author Christopher
	 * @param index of profile with desired library
	 * @return library of Profiles[<strong>index</strong>]
	 */
	public Library getLibrary(int index) {
		return getProfile(index).getLibrary();
	}
	
	/**
	 * Getter for Library, user name based.
	 * @author Christopher
	 * @param UserName of profile with desired library
	 * @return library in profile with <strong>userName</strong>
	 */
	public Library getLibrary(String userName) {
		return getProfile(userName).getLibrary();
	}
	
	/**
	 * Hold all persistent data in a object which can be
	 * serialized and saved to a file.
	 * @author Christopher
	 */
	@SuppressWarnings("serial")
	private class Database implements Serializable {
		private String[] appOwnerInfo;
		private ProfileManager profiles;
		
		private Database() {
			appOwnerInfo = new String[] {"Timmy", "Tim@poo.com"}; // TODO FIX appOwnerInfo
			profiles = new ProfileManager();
		}
	}

}