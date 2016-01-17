package com.epam.oomsimulator.file;

import org.junit.Assert;
import org.junit.Test;

public class FileUtilTest extends Assert {

	/** Test file name */
	public static final String TEST_FILE_NAME = "D:\\trainings\\jmp2015-2016\\workspace\\OutOfMemoryErrorSimulator\\src\\test\\resources\\testfile.txt";
	/** Test data */
	public static final String TEST_DATA = "Some text is here.\n";

	@Test
	public void readFileTest() {
		String dataFromFile = null;
		try {
			dataFromFile = FileUtil.readFile(TEST_FILE_NAME).toString();
		} catch (Exception ex) {
			fail(ex.toString());
		}
		assertNotNull(dataFromFile);
		assertEquals(TEST_DATA, dataFromFile);
	}
}
