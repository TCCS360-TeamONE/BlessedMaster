package model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 * A utility and collection class for managing Profile objects.
 * 
 * @author Alan, Christopher
 *
 */

public class ProfileManager implements Serializable {

	private static final long serialVersionUID = -1187921616748813628L;
	
	// A collection of Profile objects
	private ArrayList<Profile> profileList;
	
	// The currently loaded profile
	private Profile loadedProfile;
	
	public ProfileManager() {
		profileList = new ArrayList<Profile>();
	}
	
	/**
	 * Get the collection of profiles.
	 * @return
	 */
	public ArrayList<Profile> getProfileList() {
		return profileList;
	}
	
	/**
	 * Add a profile in to the profile collection.
	 * @param theProfile
	 */
	public void addProfile(Profile theProfile) {
		profileList.add(theProfile);
		loadedProfile = theProfile;
	}
	
	/**
	 * Check to see if theUserName already exists in the collection of Profiles.
	 * @param theUserName
	 * @return true if successful, false otherwise
	 */
	public boolean userAlreadyExists(String theUserName) {
		for (Profile p : profileList) {
			if (theUserName.equals(p.getUserName()))
				return true;
		}
		return false;
	}
	
	/**
	 * Get the currently loaded profile.
	 * @return
	 */
	public Profile getLoadedProfile() {
		return loadedProfile;
	}
	
	/**
	 * Set the currently loaded profile.
	 * @param theProfile
	 */
	public void setloadedProfile(Profile theProfile) {
		loadedProfile = theProfile;
	}
	
	/**
	 * Get the specific index of the profile related to userName
	 * @param userName
	 * @return the index of the profile if found, -1 if the profile isn't found
	 */
	public int getProfileIndex(String userName) {
		for (int i = 0; i < profileList.size(); i++) {
			Profile p = profileList.get(i);
			if (p.getUserName().equals(userName)) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * Get a specific profile based on the related userName.
	 * @param userName
	 * @return profile related to userName, otherwise returns null
	 */
	public Profile getProfile(String userName) {
		int profileIndex = getProfileIndex(userName);
		
		if (profileIndex != -1)
			return profileList.get(profileIndex);
			
		return null;
	}
	
	/**
	 * Remove the profile related to userName from the profile collection
	 * @param userName
	 */
	public void removeProfile(String userName) {
		int profileIndex = getProfileIndex(userName);
		
		if (profileIndex != -1)
			profileList.remove(profileIndex);
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Profile p : profileList) {
			sb.append(p.toString());
			sb.append("\n");
		}
		return sb.toString();
	}
}
