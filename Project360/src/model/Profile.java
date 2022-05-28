package model;

import java.io.Serializable;

public class Profile implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String userName;
	private String password;
	private Library userLibrary;
	
	public Profile(String theUserName, String thePassword) {
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