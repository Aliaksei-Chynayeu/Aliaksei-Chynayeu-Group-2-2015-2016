package com.epam.jmp.taskmanager.data.dao;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.StringWriter;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.epam.jmp.taskmanager.data.pool.FilePool;
import com.epam.jmp.taskmanager.exception.ConnectionPoolException;
import com.epam.jmp.taskmanager.exception.TechnicalDAOException;
import com.epam.jmp.taskmanager.exception.TechnicalException;
import com.epam.jmp.taskmanager.model.TaskStore;
import com.epam.jmp.taskmanager.util.FileUtil;
import com.epam.jmp.taskmanager.util.JAXBUtil;
import com.mockrunner.mock.jdbc.MockStatement;

/**
 * 
 * @author Aliaksei_Hlazkou
 * 
 * @param <T>
 *            object to work with
 * @param <S>
 *            connection type
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ JAXBUtil.class, FileUtil.class, FilePool.class})
public class AbstractDAOTest<T, S> {

	@InjectMocks
	protected FileDAO fileDAO = new FileDAO();

	@Mock
	protected FilePool pool = FilePool.getInstance();
	@Mock
	protected TaskStore taskStore = new TaskStore();
	@Mock
	protected File file = new File(TestParameters.URL_TEST);

	protected interface TestParameters {
		
		// File path stub
		String URL_TEST = "src\\test\\resources\\store\\TaskStore.xml";
		// StringWriter stub
		StringWriter TEST_SW = new StringWriter() {
			{
				this.append("<TaskStore [taskList=[], id=0]>");
			}
		};
		// Long obj stub
		long TEST_LONG = 123L;
	}
	
	@Mock
	private Statement st = Mockito.mock(MockStatement.class);
	/**
	 * Setups for tests
	 * 
	 * @throws ConnectionPoolException
	 *             - unexpected exception
	 * @throws RuntimeException
	 *             - unexpected exception
	 * @throws TechnicalException
	 *             - unexpected exception
	 */
	@Before
	public void setUp() throws ConnectionPoolException, RuntimeException, TechnicalException {
		FilePool.getInstance().setPoolSize(FilePool.FILE_POOL_SIZE);
		FilePool.getInstance().setUrl(TestParameters.URL_TEST);
		FilePool.getInstance().initPool();
		fileDAO.setPool(pool);
	}
	
	/**
	 * Test method for {@link FileDAO#getConnectionPool())} with correct
	 * behaviour.
	 * 
	 * @throws ConnectionPoolException
	 *             - unexpected exception
	 */
	@Test
	public void testGgetConnectionPool() throws ConnectionPoolException{
		assertEquals(pool, fileDAO.getPool());
	}
	
	/**
	 * Test method for {@link FileDAO#getConnection())} with correct behaviour.
	 * 
	 * @throws ConnectionPoolException
	 *             - unexpected exception
	 * @throws TechnicalDAOException
	 *             - unexpected exception
	 */
	@Test
	public void testGetConnection() throws ConnectionPoolException, TechnicalDAOException{
		fileDAO.getConnection();
	}
	
	/**
	 * Test method for {@link FileDAO#closeStatement(Statement)} with correct
	 * behaviour.
	 * 
	 * @throws TechnicalDAOException
	 *             - unexpected exception
	 */
	@Test
	public void testCloseStatement () throws TechnicalDAOException {
		fileDAO.closeStatement(st);
	}
	
	/**
	 * Test method for {@link FileDAO#closeStatement(Statement)} with correct
	 * behaviour.
	 * 
	 * @throws TechnicalDAOException
	 *             - unexpected exception
	 */
	@Test
	public void testCloseStatementNull () throws TechnicalDAOException {
		fileDAO.closeStatement(null);
	}
	
	/**
	 * Test method for {@link FileDAO#closeStatement(Statement)} with wrong
	 * behaviour.
	 * 
	 * @throws TechnicalDAOException
	 *             - expected exception
	 * @throws SQLException
	 *             - unexpected exception
	 */
	@Test (expected=TechnicalDAOException.class)
	public void testCloseStatementTechnicalDAOException () throws TechnicalDAOException, SQLException {
		Mockito.doThrow(Exception.class).when(st).close();
		fileDAO.closeStatement(st);
	}

}
