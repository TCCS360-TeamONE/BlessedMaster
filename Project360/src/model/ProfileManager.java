package model;

import java.io.Serializable;
import java.util.ArrayList;


public class ProfileManager implements Serializable {

	private static final long serialVersionUID = -1187921616748813628L;
	
	private ArrayList<Profile> profileList;
	
	private Profile loadedProfile;
	
	public ProfileManager() {
		profileList = new ArrayList<Profile>();
	}
	
	public ArrayList<Profile> getProfileList() {
		return profileList;
	}
	
	public void addProfile(Profile theProfile) {
		profileList.add(theProfile);
	}

	public boolean userAlreadyExists(String theUserName) {
		for (Profile p : profileList) {
			if (theUserName.equals(p.getUserName()))
				return true;
		}
		return false;
	}
	
	public Profile getLoadedProfile() {
		return loadedProfile;
	}
	
	public void setloadedProfile(Profile theProfile) {
		loadedProfile = theProfile;
	}
	
	public int getProfileIndex(String userName) {
		for (int i = 0; i < profileList.size(); i++) {
			Profile p = profileList.get(i);
			if (p.getUserName().equals(userName)) {
				return i;
			}
		}
		return -1;
	}
	
	public Profile getProfile(String userName) {
		int profileIndex = getProfileIndex(userName);
		
		if (profileIndex != -1)
			return profileList.get(profileIndex);
			
		return null;
	}
	
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
