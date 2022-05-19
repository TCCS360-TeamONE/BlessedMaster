package main;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * 
 * @author Alan Thompson
 * 
 * AboutWindow.java
 * 
 * A class to handle the About window and its components.
 *
 */

public class AboutWindow extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * AboutWindow Constructor
	 * @param width
	 * @param height
	 */
	public AboutWindow(int width, int height) {
		initWindow(width, height);
	}
	
	/**
	 * Helper method to setup the About window frame and it's components.
	 * @param width
	 * @param height
	 */
	public void initWindow(int width, int height) {
		setTitle("About...");
		
		String lineRegistered = "This app is unregistered!";
		String lineUser = 		"    Please close and reopen the about";
		String lineEmail = 		"    window to register a new user.";
		
		registerUser();
		
		if (Main.userProfile.isRegistered()) {
			lineRegistered = "This app is registered to:";
			lineUser = 		 "  " + Main.userProfile.getName();
			lineEmail = 	 "  " + Main.userProfile.getEmail();
		}
		
		String[] strAbout = {
			"FileSorter " + Main.VERSION,
			"\n",
			lineRegistered,
		    lineUser,
		    lineEmail,
		    "",
		    "This app provided by:",
		    "  Team ONE (TCSS360b)",
		    Main.aboutDevs[0],
		    Main.aboutDevs[1],
		    Main.aboutDevs[2],
		    Main.aboutDevs[3],
		    Main.aboutDevs[4]
		};
		                     
		
		for (int i = 0; i < strAbout.length; i++) {
			JLabel label = new JLabel(strAbout[i]);
			add(label);
			label.setBounds(10, 20*i, 300, 20);
		}
		
		setSize(width, height);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLayout(null);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	/**
	 * Helper method to call the required functions for profile user registration.
	 */
	public void registerUser() {
		if (!Main.userProfile.isRegistered()) {
			
			String defaultText = "unregistered";
			
			String newUser = JOptionPane.showInputDialog("Please register a user name:", defaultText);
			String newEmail = JOptionPane.showInputDialog("Please register a user email:", defaultText);
			
			if (newUser != null &&
				!newUser.equals(defaultText) &&
				!newUser.isBlank() &&
				newEmail != null && 
				!newEmail.equals(defaultText) &&
				!newEmail.isBlank()){
				Main.userProfile.setName(newUser);
				Main.userProfile.setEmail(newEmail);
				Main.userProfile.setRegistered(true);
			}
		}
	}
}
