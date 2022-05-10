package main;

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
		"  Alan Thompson",	// Alan's line
		"  Christopher Henderson",	// Chris's line
		"  Betelhem Bada",	// Betty's line
		"  Anthony Cabrera-Lara",	// Anthony's line
		"  Anderew Lau" 	// Andrew's line
	};

	
	public static Profile userProfile;
	
	/**
	 * Application entry point.
	 * @param args
	 */
	public static void main(String[] args) {
		
		userProfile = new Profile();
		
		new MainWindow(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
}
