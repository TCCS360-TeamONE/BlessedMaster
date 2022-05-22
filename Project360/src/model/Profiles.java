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
		
		private ArrayList<File> fileList;
		private ArrayList<Label> labelList;
		
		public LoginProfile(String theUserName, String thePassword) {
			userName = theUserName;
			password = thePassword;
			fileList = new ArrayList<File>();
			labelList = new ArrayList<Label>();
		}
		
		public String getUserName() {
			return userName;
		}
		
		public String getPassword() {
			return password;
		}
		
		public ArrayList<File> getFileList() {
			return fileList;
		}
		
		public ArrayList<Label> getLabelList() {
			return labelList;
		}
		
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("LoginProfile {\"");
			sb.append(userName);
			sb.append("\",\"");
			sb.append(password);
			sb.append("\",");
			sb.append(fileList.toString());
			sb.append(",");
			sb.append(labelList.toString());
			sb.append("}");
			return sb.toString();
		}
		
	}
}
