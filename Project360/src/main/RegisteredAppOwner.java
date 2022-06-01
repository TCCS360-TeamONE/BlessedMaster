package main;

import java.io.Serializable;

/**
 * RegisteredAppOwner is essentially a user license type object so that the app can be registered to
 * a specific person/email.
 * 
 * @author Alan Thompson
 */

public class RegisteredAppOwner implements Serializable{
	
	private static final long serialVersionUID = 1L;
	/**
	 * Profile member variables
	 */
	private String name;
	private String email;
	private boolean registered;
	
	public RegisteredAppOwner() {
		name = "unregistered";
		email = "unregistered";
		registered = false;
	}
	
	/**
	 * Getter methods for member variables
	 */
	public String getName() {
		return name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public boolean isRegistered() {
		return registered;
	}
	
	
	/**
	 * Setter methods for member variables
	 */
	public void setName(String newName) {
		name = newName;
	}
	
	public void setEmail(String newEmail) {
		email = newEmail;
	}
	
	public void setRegistered(boolean status) {
		registered = status;
	}
}
