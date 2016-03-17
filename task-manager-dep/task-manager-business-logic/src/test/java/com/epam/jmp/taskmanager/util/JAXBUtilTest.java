package com.epam.jmp.taskmanager.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.epam.jmp.taskmanager.exception.TechnicalException;
import com.epam.jmp.taskmanager.model.TaskStore;

/**
 * Test class for {@link JAXBUtil}
 * @author Aliaksei_Hlazkou
 *
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(JAXBUtil.class)
public class JAXBUtilTest {
	
	/** File obj to do stub */
	@Mock
	private File file;
	/** StringWriter obj to do stub */
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
		/** File path stub */
		String URL_TEST_3 = "src\\test\\resources\\store\\TaskStore3.xml";
		/** File path stub */
		String URL_TEST_4 = "src\\test\\resources\\store\\TaskStore4.xml";
		/** StringWriter stub */
		StringWriter TEST_SW = new StringWriter() {
			{
				this.append("<TaskStore [taskList=[], id=0]>");
			}
		};
	}

	/**
	 * Test method for {@link JAXBUtil#marshall(Object, Class)} with correct
	 * behaviour.
	 * 
	 * @throws TechnicalException
	 *             - unexpected exception
	 */
	@Test
	public void testMarshall () throws TechnicalException {
		StringWriter swMarhalled = JAXBUtil.marshall(new TaskStore(), TaskStore.class);
		Assert.assertFalse(swMarhalled.toString().isEmpty());
	}
	
	/**
	 * Test method for {@link JAXBUtil#marshall(Object, Class)} with wrong behaviour.
	 * @throws TechnicalException - expected exception
	 * @throws Exception  - unexpected exception
	 */
	@Test(expected=TechnicalException.class)
	public void testMarshallTechnicalException () throws TechnicalException, Exception {
		StringWriter stubsw = PowerMockito.mock(StringWriter.class);
		PowerMockito.whenNew(StringWriter.class).withAnyArguments().thenThrow(Exception.class);
		JAXBUtil.marshall(new TaskStore(), TaskStore.class);
	}


	/**
	 * Test method for {@link JAXBUtil#unmarshall(Class, java.io.File)} with correct behaviour.
	 * @throws TechnicalException 
	 *             - unexpected exception
	 */
	@Test
	public void testUnmarshall () throws TechnicalException {
		file = new File(TestParameters.URL_TEST_4);
		TaskStore unmarshalledts = JAXBUtil.unmarshall(TaskStore.class, file);
		Assert.assertNotNull(unmarshalledts);
	}
}