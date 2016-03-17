package com.epam.jmp.taskmanager.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.epam.jmp.taskmanager.exception.TechnicalException;

/**
 * Test class for {@link FileUtil}
 * @author Aliaksei_Hlazkou
 *
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest (FileUtil.class)
public class FileUtilTest {
	
	/** File object for stub */
	@Mock
	private File file;
	/** StringWriter object for stub */ 
	@Mock
	private StringWriter sw;
	
	/**
	 * Method to do some setups
	 */
	@Before
	public void setUp() {
		sw = TestParameters.TEST_SW;
		
	}
	/**
	 * Interface with some constants used for subbing
	 * @author Aliaksei_Hlazkou
	 *
	 */
	private interface TestParameters {
		
		/** File path stub*/
		String URL_TEST_1 = "src\\test\\resources\\store\\TaskStore1.xml";
		/** File path stub */
		String URL_TEST_2 = "src\\test\\resources\\store\\TaskStore2.xml";
		/** File path stub */
		String URL_TEST_3 = "src\\test\\resources\\store\\TaskStore3.xml";
		/** StringWriter stub */
		StringWriter TEST_SW = new StringWriter() {
			{
				this.append("<TaskStore [taskList=[], id=0]>");
			}
		};
		/** Long obj stub */
		long TEST_LONG = 123L;
	}
	/**
	 * Method for testing {@link FileUtil#writeToFile(StringWriter, File)} with correct behaviour.
	 * @throws TechnicalException
	 *             - unexpected exception
	 */
	@Test
	public void testWriteToFile () throws TechnicalException {
		file = new File(TestParameters.URL_TEST_1);
		FileUtil.writeToFile(sw, file);
		Assert.assertEquals(sw.toString(), FileUtil.readFormFile(file).toString());
	}
	
	/**
	 * Method for testing {@link FileUtil#writeToFile(StringWriter, File)} with wrong behaviour.
	 * @throws TechnicalException
	 *             - expected exception
	 */
	@Test(expected=TechnicalException.class)
	public void testWriteToFileFileWriterFails () throws TechnicalException {
		file = new File(TestParameters.URL_TEST_2);
		sw = Mockito.mock(StringWriter.class);
		Mockito.when(sw.toString()).thenThrow(Exception.class);
		FileUtil.writeToFile(sw, file);
	}
	/**
	 * Method for testing {@link FileUtil#writeToFile(StringWriter, File)} with wrong behaviour.
	 * @throws TechnicalException - expected exception
	 * @throws Exception - unexpected excpetion
	 */
	@Test(expected=TechnicalException.class)
	public void testWriteToFileStringWriterFails () throws TechnicalException, Exception {
		file = new File(TestParameters.URL_TEST_2);
		FileWriter stubfw = PowerMockito.mock(FileWriter.class);
		PowerMockito.doThrow(new IOException()).when(stubfw).close();
		PowerMockito.whenNew(FileWriter.class).withAnyArguments().thenReturn(stubfw);
		FileUtil.writeToFile(sw, file);
	}
	/**
	 * Method for testing {@link FileUtil#readFormFile(File)} with correct behaviour.
	 * @throws TechnicalException
	 *             - unexpected exception
	 */
	@Test
	public void testReadFromFile() throws TechnicalException{
		file = new File(TestParameters.URL_TEST_3);
		StringBuilder sbFromFile = FileUtil.readFormFile(file);
		Assert.assertEquals(sw.toString(), sbFromFile.toString());
	}
	
	/**
	 * Method for testing {@link FileUtil#readFormFile(File)} with wrong behaviour.
	 * @throws TechnicalException
	 *             - expected exception
	 * @throws Exception 
	 */
	@Test (expected=TechnicalException.class)
	public void testReadFromFileTechnicalException() throws Exception{
		file = new File(TestParameters.URL_TEST_3);
		PowerMockito.whenNew(StringBuilder.class).withNoArguments().thenReturn(null);
		FileUtil.readFormFile(file);
	}

}
