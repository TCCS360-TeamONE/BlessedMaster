package model;

import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Profiles implements Serializable {
	
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
	
	public class LoginProfile implements Serializable{
		
		private String userName;
		private String password;
		
		private Library userLibrary;
		
		public LoginProfile(String theUserName, String thePassword) {
			userName = theUserName;
			password = thePassword;
			userLibrary = new Library();
		}
		
		public String getUserName() {
			return userName;
		}
		
		public String getPassword() {
			return password;
		}
		
		public Library getLibrary() {
			return userLibrary;
		}
		
		//// TODO TESTING STUFF DELETE LATER
		public void resetLib() {
			userLibrary = new Library();
			userLibrary.addFile("resty Fi");
			userLibrary.addLabel("resty Lab");
		}
		
		
		
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append("LoginProfile {\"");
			sb.append(userName);
			sb.append("\",\"");
			sb.append(password);
			sb.append("\",");
			sb.append(userLibrary);
			sb.append("}");
			return sb.toString();
		}
		
	}
}
