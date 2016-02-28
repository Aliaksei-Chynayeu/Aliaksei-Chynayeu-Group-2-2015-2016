package com.epam.jmp.taskmanager.data.pool;

import java.io.File;
import java.io.StringWriter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

import com.epam.jmp.taskmanager.exception.ConnectionPoolException;
import com.epam.jmp.taskmanager.util.MagicNumbers;

@RunWith(PowerMockRunner.class)
@PrepareForTest({FilePool.class})
public class FilePoolTest {

	@Mock
	/** Pool to test */
	protected FilePool pool = null;
	/** File obj to stub */
	protected File file = null;

	/**
	 * Interface with some constants used for subbing
	 * 
	 * @author Aliaksei_Hlazkou
	 * 
	 */
	protected interface TestParameters {

		/** File path stub */
		String TEST_URL = "src\\test\\resources\\store\\TaskStore.xml";
		/** StringWriter stub */
		StringWriter TEST_SW = new StringWriter() {
			{
				this.append("<TaskStore [taskList=[], id=0]>");
			}
		};
		/** Long obj stub */
		long TEST_LONG = 123L;
		/** String to stub */
		String TEST_USER = "user123";
		/** String to stub */
		String TEST_PASS = "pass123321";
		/** String to stub */
		String TEST_DRIVER = "driver";
	}

	/**
	 * Method to do some setups
	 * 
	 * @throws ConnectionPoolException
	 */
	@Before
	public void setUp() throws Exception {
		pool = PowerMockito.mock(FilePool.class);
		pool.setUrl(TestParameters.TEST_URL);
		pool.setPassword(TestParameters.TEST_PASS);
		pool.setUser(TestParameters.TEST_USER);
		pool.setDriver(TestParameters.TEST_DRIVER);
		pool.setPoolSize(MagicNumbers.MAGIC_ONE);
	}
	
	@Test
	public void testGetInstance() {
		Object obj = pool.getInstance();
		Assert.assertNotNull(obj);
		Assert.assertTrue(obj instanceof FilePool);
	}
	
	@Test
	public void testInitPool () throws Exception {
		initRealFilePool();
		File fileConnection = FilePool.getInstance().takeConnection();
		Assert.assertNotNull(fileConnection);
		Assert.assertEquals(TestParameters.TEST_URL, fileConnection.getPath());
	}
	
	@Test
	public void testClosePool() throws Exception {
		initRealFilePool();
		FilePool.getInstance().closePool();
		FilePool instance = Whitebox.getInternalState(FilePool.class, "instance");
		Assert.assertNull(instance);
	}
	

	@Test
	public void test—learConnectionQueue ()  throws Exception {
		initRealFilePool();
		Whitebox.invokeMethod(FilePool.getInstance(), "clearConnectionQueue");
		Assert.assertTrue(FilePool.getInstance().getFreeConnections().isEmpty());
		Assert.assertTrue(FilePool.getInstance().getBusyConnections().isEmpty());
	}

	private void initRealFilePool () throws Exception{
		FilePool.getInstance().setUrl(TestParameters.TEST_URL);
		FilePool.getInstance().setPassword(TestParameters.TEST_PASS);
		FilePool.getInstance().setUser(TestParameters.TEST_USER);
		FilePool.getInstance().setDriver(TestParameters.TEST_DRIVER);
		FilePool.getInstance().setPoolSize(MagicNumbers.MAGIC_ONE);
		FilePool.getInstance().initPool();
	}
}
