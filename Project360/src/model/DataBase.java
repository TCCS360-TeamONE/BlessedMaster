package model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class DataBase implements Serializable{
	
	/////////////// FOR TESTING ///////////////	
	public static void main(String[] args) {
		
		// Build and fill library with test data
		DataBase testCase = new DataBase();
		testCase.myLibrary.addFile(new File("./Files/f1"));
		testCase.myLibrary.addFile(new File("./Files/f2"));
		testCase.myLibrary.addFile(new File("./Files/f3"));
		testCase.myLibrary.addFile(new File("./Files/f4"));
		testCase.myLibrary.addLabel(new Label("Label 1"));
		testCase.myLibrary.addLabel(new Label("Label 2"));
		testCase.myLibrary.addLabel(new Label("Label 3"));
		testCase.myLibrary.addLabel(new Label("Label 4"));
		System.out.println(testCase);
		
		//save object to byte code,
		byte[] code = testCase.getLibraryBytecode();
		testCase.resetLib(); // replace old Library
		System.out.println(testCase);
		
		// load original library from byte code
		testCase.loadLibraryBytecode(code);
		System.out.println(testCase);
		
	}
	
	public void resetLib() {
		myLibrary = null;
		myLibrary = new Library();
		myLibrary.addTesty();
	}
	/////////////// END TESTING ///////////////	
	
	
	
	private Library myLibrary;
	
	public DataBase() {
		myLibrary = new Library();

	}
	
	public byte[] getLibraryBytecode() {
		ByteArrayOutputStream byteData = new ByteArrayOutputStream();
		ObjectOutputStream outStream = null;
		byte[] libraryBytecode = null;
		
		try {
			outStream = new ObjectOutputStream(byteData);
			outStream.writeObject(myLibrary);
			outStream.flush();
			libraryBytecode = byteData.toByteArray();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		return libraryBytecode;
	}
	
	public boolean loadLibraryBytecode(final byte[] theLibraryBytecode) {
		ByteArrayInputStream byteData = new ByteArrayInputStream(theLibraryBytecode);
		ObjectInputStream inStream = null;
		Object importedLibrary = null;
		try {
			inStream = new ObjectInputStream(byteData);
			importedLibrary = inStream.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (importedLibrary != null) {
			myLibrary = (Library) importedLibrary;
			return true;
		} else {
			return false;
		}
	}
	

	// TODO: check for duplicated files
	public void addFile(final String theFilePath) {
		myLibrary.addFile(new File(theFilePath));
	}
	
	public void removeFile(final File theFile) {
		myLibrary.removeFile(theFile);
	}
	
	public void addLabel(final String theLabelName) {
		myLibrary.addLabel(new Label(theLabelName));
	}
	
	public void removeLabel(final Label theLabel) {
		myLibrary.removeLabel(theLabel);
	}
	
	public File[] getFileArray() {
		return (File[]) myLibrary.fileLibrary.toArray();
	}
	
	public Label[] getLabelArray() {
		return (Label[]) myLibrary.labelLibrary.toArray();
	}
	
	@Override // TODO: Make better
	public String toString() {
		String Files = "";
		for (File f : myLibrary.fileLibrary) {
			Files += f + "   ";
		}
		
		String Labels = "";
		for (Label l : myLibrary.labelLibrary) {
			Labels += l + "   ";
		}
		
		
		return (Files + "\n" + Labels + "\n");
	}
	
	
	public class Library implements Serializable {
		
		private ArrayList<File> fileLibrary;
		
		private ArrayList<Label> labelLibrary;
		
		public Library() {
			fileLibrary = new ArrayList<File>();
			labelLibrary = new ArrayList<Label>();	
		}
		
		public void addTesty() { /// for testing only
			addFile(new File("resty Fi"));
			addLabel(new Label("resty Lab"));
		}
		
		private boolean addFile(final File theFile) {
			if (fileLibrary.contains(theFile))
				return false;
			else
				fileLibrary.add(theFile);
			return true;
		}
		
		private boolean removeFile(final File theFile) {
			return fileLibrary.remove(theFile);
		}
		
		private boolean addLabel(final Label theLabel) {
			if (labelLibrary.contains(theLabel))
				return false;
			else
				labelLibrary.add(theLabel);
			return true;
		}
		
		private boolean removeLabel(final Label theLabel) {
			return labelLibrary.remove(theLabel);
		}
		
	}

}
