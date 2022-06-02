package model;

import java.io.Serializable;

/**
 * The base profile object for how the application handles different users/profiles.
 * @author Alan Thompson
 *
 */
public class Profile implements Serializable{
	
	
	/**
	 * serial Version UID.
	 * @see Serializable
	 */
	private static final long serialVersionUID = -2405511487098467910L;

	// The profiles unique name
	private String userName;
	
	// The profiles password
	private String password;
	
	// The profile's library, which is a collection of AppFile and AppLabel objects.
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