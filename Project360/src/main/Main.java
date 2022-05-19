package main;

import java.awt.EventQueue;

import model.Profiles;
import view.MainWindow;

/**
 * 
 * @author Alan Thompson
 * 
 * Main.java
 * 
 * The entry point for the TCSS360b TeamONE FileSorter application.
 *
 */

public class Main {
	
	/**
	 * Application constant variables
	 */
	public static final String VERSION = "v0.1";
	public static final int DEFAULT_WIDTH = 800;
	public static final int DEFAULT_HEIGHT = 600;
	
	public static final String[] aboutDevs = {
		"  Alan Thompson",
		"  Christopher Henderson",
		"  Betelhem Bada",
		"  Anthony Cabrera-Lara",
		"  Anderew Lau"
	};

	// Profile for the about screen
	public static Profile userProfile;
	
	// LoginProfile stuff for the ProfilePanel
	public static Profiles loginProfiles;
	
	/**
	 * Application entry point.
	 * @param args
	 */
	public static void main(String[] args) {
		
		userProfile = new Profile();
		
		loginProfiles = new Profiles();
		
		/* TESTING STUFF */
		for (int i = 0; i < 10; i++) {
			loginProfiles.createNewUser("User"+i, "password");
		}
		
		
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainWindow();
            }
        });
	}
}
