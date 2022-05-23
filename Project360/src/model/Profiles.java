package model;

import java.util.ArrayList;

public class Profiles {
	
	private ArrayList<LoginProfile> profileList;
	
	public Profiles() {
		profileList = new ArrayList<LoginProfile>();
	}
	
	public ArrayList<LoginProfile> getProfileList() {
		return profileList;
	}
	
	public boolean createNewUser(String theUserName, String thePassword) {
		if (theUserName != null &&
			thePassword != null &&
			!thePassword.isBlank() &&
			!userAlreadyExists(theUserName)) {
			
			profileList.add(new LoginProfile(theUserName, thePassword));
			return true;
		}
		return false;
	}
	
	public boolean userAlreadyExists(String theUserName) {
		for (LoginProfile p : profileList) {
			if (theUserName.equals(p.getUserName()))
				return true;
		}
		return false;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (LoginProfile p : profileList) {
			sb.append(p.toString());
			sb.append("\n");
		}
		return sb.toString();
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
