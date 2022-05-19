package main;

import java.awt.EventQueue;

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

	
	public static Profile userProfile;
	
	/**
	 * Application entry point.
	 * @param args
	 */
	public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainWindow();
            }
        });
		
		userProfile = new Profile();
	}
}
