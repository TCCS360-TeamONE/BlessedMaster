package unitTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.AppFile;
import model.AppLabel;

class Model_UnitTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
	}

	@BeforeEach
	void setUp() throws Exception {
	}
	
	@Test
	void testAppFile() {
		AppFile appFile = new AppFile("~/test.txt");
		assertNotNull(appFile);
		appFile.updateFileInfo();
		assertNotNull(appFile.getFilePath());
		assertNotNull(appFile.getFileName());
		assertNotNull(appFile.getFileExtension());
		assertNotNull(appFile.getFileTypeDescription());
		assertNotNull(appFile.getFileIcon());
	}
	
	/** @author Christopher */
	@Test
	void testAppLabel_SetGetName() {
		AppLabel testLabel = new AppLabel("Test1");
		assertNotNull(testLabel);
		assertEquals("Test1", testLabel.getMyName());
		testLabel.setMyName("Test2");
		assertEquals("Test2", testLabel.getMyName());
	}
	
	/** @author Christopher */
	@Test
	void testAppLabel_AddRemoveFile() {
		AppLabel testLabe = new AppLabel("TestLabel");
		AppFile testFile1 = new AppFile("./TestFiles/File1.txt");
		AppFile testFile2 = new AppFile("./TestFiles/File2.txt");

		assertNotNull(testLabe.getFilesArray());
		
		testLabe.addFile(testFile1);
		testLabe.addFile(testFile2);

		assertEquals(testFile1, testLabe.getFilesArray()[0]);
		assertEquals(testFile2, testLabe.getFilesArray()[1]);
		
		assertTrue(testLabe.getFilesArray().length == 2);
		testLabe.removeFile(testFile1);
		assertTrue(testLabe.getFilesArray().length == 1);
		testLabe.removeFile(testFile2);
		assertTrue(testLabe.getFilesArray().length == 0);
	}
	
	
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}
}
