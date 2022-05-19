package model;

import java.util.ArrayList;

public class Profiles {
	
	private ArrayList<LoginProfile> ProfileList;
	
	public Profiles() {
		ProfileList = new ArrayList<LoginProfile>();
	}
	
	public ArrayList<LoginProfile> getProfileList() {
		return ProfileList;
	}
	
	public boolean createNewUser(String theUserName, String thePassword) {
		if (theUserName != null &&
			thePassword != null &&
			!thePassword.isBlank() &&
			!userAlreadyExists(theUserName)) {
			
			ProfileList.add(new LoginProfile(theUserName, thePassword));
			return true;
		}
		return false;
	}
	
	public boolean userAlreadyExists(String theUserName) {
		for (LoginProfile p : ProfileList) {
			if (theUserName.equals(p.getUserName()))
				return true;
		}
		return false;
	}
	
	public class LoginProfile {
		
		private String userName;
		private String password;
		
		private ArrayList<File> FileList;
		private ArrayList<Label> LabelList;
		
		public LoginProfile(String theUserName, String thePassword) {
			userName = theUserName;
			password = thePassword;
			FileList = new ArrayList<File>();
			LabelList = new ArrayList<Label>();
		}
		
		public String getUserName() {
			return userName;
		}
		
		public String getPassword() {
			return password;
		}
		
		public ArrayList<File> getFileList() {
			return FileList;
		}
		
		public ArrayList<Label> getLabelList() {
			return LabelList;
		}
	}
}
