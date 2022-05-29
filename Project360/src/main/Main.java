package main;

import java.awt.EventQueue;
import java.io.File;

import model.FileIO;
import model.Profile;
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
	
	public static final File PERSISTENT_DATA_FILE =  new File("PersistentData.ser");
	
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
	
	private static void initOwner() {
		appOwner = new RegisteredAppOwner();
	}
	
	public static MainWindow window;
	
	private static void initProfiles() {
		if (PERSISTENT_DATA_FILE.exists()) {
			mainProfileManger = (ProfileManager) FileIO.loadObjectFile(PERSISTENT_DATA_FILE);
		} else {
			mainProfileManger = new ProfileManager();
			mainProfileManger.addProfile(new Profile("default", "pass"));
		}

	}
	
	public static void savePersistentData() {
		FileIO.saveObjectFile(mainProfileManger, PERSISTENT_DATA_FILE);
	}
	
	
	/**
	 * Application entry point.
	 * @param args
	 */
	public static void main(String[] args) {
		initOwner();
		initProfiles();
		
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
               window = new MainWindow();
            }
        });
	}
}
