package com.epam.jmp.taskmanager.data.pool;

import java.io.File;
import java.io.StringWriter;
import java.util.concurrent.ArrayBlockingQueue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.epam.jmp.taskmanager.exception.ConnectionPoolException;
import com.epam.jmp.taskmanager.util.MagicNumbers;

/**
 * Test class for {@link AbstractPool}
 * @author Aliaksei_Hlazkou
 *
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({AbstractPool.class})
public class AbstractPoolTest {

	@Mock
	/** Pool to test */
	protected AbstractPool<File> pool = null;
	/** File obj to stub */
	protected File file = null;
	/**
	 * Interface with some constants used for subbing
	 * @author Aliaksei_Hlazkou
	 *
	 */
	protected interface TestParameters {
		
		/** File path stub*/
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
	 * @throws ConnectionPoolException 
	 */
	@Before
	public void setUp () throws Exception{
		pool = Mockito.mock(AbstractPool.class, Mockito.CALLS_REAL_METHODS);
		pool.setUrl(TestParameters.TEST_URL);
		pool.setPassword(TestParameters.TEST_PASS);
		pool.setUser(TestParameters.TEST_USER);
		pool.setDriver(TestParameters.TEST_DRIVER);
		pool.setPoolSize(MagicNumbers.MAGIC_ONE);
	}
	
	/**
	 * Method for getters testing
	 */
	@Test
	public void testGetters (){
		Assert.assertEquals(TestParameters.TEST_PASS, pool.getPassword());
		Assert.assertEquals(TestParameters.TEST_USER, pool.getUser());
		Assert.assertEquals(TestParameters.TEST_URL, pool.getUrl());
		Assert.assertEquals(TestParameters.TEST_DRIVER, pool.getDriver());
		Assert.assertEquals(MagicNumbers.MAGIC_ONE, pool.getPoolSize());
	}
	
	@Test
	public void testTakeConnection () throws Exception {
		// test preparation
		prepareForConnenctionTesting();
		//test
		Object obj  = pool.takeConnection();
		Assert.assertNotNull(obj);
		Assert.assertTrue(obj instanceof File);
		File f = (File) obj;
		Assert.assertEquals(file, f);
		// release connection
		pool.releaseConnection(file);
	}

	@Test(expected=ConnectionPoolException.class)
	public void testTakeConnectionConnectionPoolException () throws Exception {
		ArrayBlockingQueue<File> freeConnections = new ArrayBlockingQueue<File>(pool.getPoolSize());
		PowerMockito.when(pool.getFreeConnections()).thenThrow(Exception.class);
		
		pool.takeConnection();
	}

	
	@Test
	public void testReleaseConnection() throws ConnectionPoolException {
		// test preparation
		prepareForConnenctionTesting();
		//test
		File connection = pool.takeConnection();
		pool.releaseConnection(connection);
	}
	
	@Test (expected = ConnectionPoolException.class)
	public void testReleaseConnectionConnectionPoolException() throws ConnectionPoolException, InterruptedException {
		file = new File (TestParameters.TEST_URL);
		ArrayBlockingQueue<File> freeConnections = PowerMockito.mock(ArrayBlockingQueue.class);
		freeConnections.add(file);
		PowerMockito.when(pool.getFreeConnections()).thenReturn(freeConnections);
		PowerMockito.doThrow(new InterruptedException()).when(freeConnections).put(file);
		ArrayBlockingQueue<File> busyConnections = new ArrayBlockingQueue<File>(pool.getPoolSize());
		PowerMockito.when(pool.getBusyConnections()).thenReturn(busyConnections);
		//test
		File connection = pool.takeConnection();
		pool.releaseConnection(connection);
	}
	
	private void prepareForConnenctionTesting () {
		file = new File (TestParameters.TEST_URL);
		ArrayBlockingQueue<File> freeConnections = new ArrayBlockingQueue<File>(pool.getPoolSize());
		freeConnections.add(file);
		PowerMockito.when(pool.getFreeConnections()).thenReturn(freeConnections);
		
		ArrayBlockingQueue<File> busyConnections = new ArrayBlockingQueue<File>(pool.getPoolSize());
		PowerMockito.when(pool.getBusyConnections()).thenReturn(busyConnections);
	}
}
