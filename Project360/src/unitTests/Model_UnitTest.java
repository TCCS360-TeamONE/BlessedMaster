package unitTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.AppFile;

class Model_UnitTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
	}

	@BeforeEach
	void setUp() throws Exception {
	}
	
	
	
	
	@Test
	void test() {
		fail("Not yet implemented");
	}

	
	
	
	
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
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
		assertNotNull(appFile.getLabelsArray());
	}
}
