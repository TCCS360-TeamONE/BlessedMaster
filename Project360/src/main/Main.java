package main;

import java.awt.EventQueue;
import model.ProfileManager;
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
	public static final String VERSION = "v0.2";
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
	public static RegisteredAppOwner appOwner;
	
	// LoginProfile stuff for the ProfilePanel
	public static ProfileManager mainProfileManger;
	
	
	private static void testWIP() {
		appOwner = new RegisteredAppOwner();
		
		mainProfileManger = new ProfileManager();
		
		/* TESTING STUFF */
		for (int i = 0; i < 10; i++) {
			mainProfileManger.createNewUser("User"+i, "password");
		}
		
		
	}
	
	/**
	 * Application entry point.
	 * @param args
	 */
	public static void main(String[] args) {
		testWIP();
		
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainWindow();
            }
        });
	}
}
