package model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import model.Profiles.LoginProfile;


@SuppressWarnings("serial")
/**
 * DATA MAN
 * @author Christopher
 *
 */
public class DataBase implements Serializable {
	
	/////////////// FOR TESTING ///////////////	
	public static void main(String[] args) {
		// Build and fill library with test data
		DataBase testCase = new DataBase();
		testCase.profiles.createNewUser("Timm", "1234");
		ArrayList<LoginProfile> users = testCase.profiles.getProfileList();
		LoginProfile Timmy = users.get(0);
		Library myLibrary = Timmy.getLibrary();
		
		myLibrary.addFile("./Files/f1.txt");
		myLibrary.addFile("./Files/f2.txt");
		myLibrary.addFile("./Files/f3.txt");
		myLibrary.addFile("./Files/f4.txt");
		myLibrary.addLabel("Label 1");
		myLibrary.addLabel("Label 2");
		myLibrary.addLabel("Label 3");
		myLibrary.addLabel("Label 4");
		System.out.println(testCase);
		
		//save object to byte code,
		//byte[] code = testCase.getLibraryBytecode();
		testCase.exportDataBase();
		Timmy.resetLib();
		System.out.println(testCase);
		
		// load original library from byte code
		//testCase.loadLibraryBytecode(code);
		testCase.importDataBase();
		System.out.println(testCase);
		
	} /////////////// END TESTING ///////////////	
	
	
	/** TODO: JavaDoc*/
	private Profiles profiles;
	
	public DataBase() {
		profiles = new Profiles();
	}
	
	/** TODO: JavaDoc*/
	public void exportDataBase() {
		byte[] byteCode = getLibraryBytecode();
		FileIO.saveDatabase(byteCode);
	}
	
	/** TODO: JavaDoc*/
	public void importDataBase() {
		byte[] byteCode = FileIO.loadDatabase();
		loadLibraryBytecode(byteCode);
	}
	
	/** TODO: JavaDoc*/
	public byte[] getLibraryBytecode() {
		ByteArrayOutputStream byteData = new ByteArrayOutputStream();
		ObjectOutputStream outStream = null;
		byte[] profilesBytecode = null;
		
		try {
			outStream = new ObjectOutputStream(byteData);
			outStream.writeObject(profiles);
			outStream.flush();
			profilesBytecode = byteData.toByteArray();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		return profilesBytecode;
	}
	
	/** TODO: JavaDoc*/
	public boolean loadLibraryBytecode(final byte[] theLibraryBytecode) {
		ByteArrayInputStream byteData = new ByteArrayInputStream(theLibraryBytecode);
		ObjectInputStream inStream = null;
		Object importedProfiles = null;
		try {
			inStream = new ObjectInputStream(byteData);
			importedProfiles = inStream.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (importedProfiles != null) {
			profiles= (Profiles) importedProfiles;
			return true;
		} else {
			return false;
		}
	}
	
	@Override /** TODO: JavaDoc*/
	public String toString() {
		return profiles.toString();
	}

}
