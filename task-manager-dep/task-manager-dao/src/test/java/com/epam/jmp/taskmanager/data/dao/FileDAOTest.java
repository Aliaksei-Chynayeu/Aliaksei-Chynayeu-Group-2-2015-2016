package com.epam.jmp.taskmanager.data.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.epam.jmp.taskmanager.exception.ConnectionPoolException;
import com.epam.jmp.taskmanager.exception.TechnicalDAOException;
import com.epam.jmp.taskmanager.exception.TechnicalException;
import com.epam.jmp.taskmanager.model.TaskStore;
import com.epam.jmp.taskmanager.util.FileUtil;
import com.epam.jmp.taskmanager.util.JAXBUtil;

/**
 * 
 * @author Aliaksei_Hlazkou
 * 
 * Test class for {@link FileDAO}
 *
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({JAXBUtil.class, FileUtil.class})
public class FileDAOTest extends AbstractDAOTest<TaskStore, File>{

	@InjectMocks
	protected FileDAO fileDAO = new FileDAO();
	
	/**
	 * Test method for {@link FileDAO#readFromFromFile()} with correct behaviour.
	 * 
	 * @throws TechnicalDAOException
	 *             - unexpected exception
	 * @throws ConnectionPoolException
	 *             - unexpected exception
	 * @throws RuntimeException
	 *             - unexpected exception
	 * @throws TechnicalException
	 *             - unexpected exception
	 */
	@Test
	public void testReadFromFromFile() throws TechnicalDAOException, ConnectionPoolException, RuntimeException, TechnicalException {
		mockStatic(JAXBUtil.class);
		when(JAXBUtil.unmarshall(TaskStore.class, file)).thenReturn(taskStore);
		assertEquals(taskStore, fileDAO.readFromFromFile());
		// test if connection was released
		assertEquals(file, fileDAO.getConnection());
	}

	/**
	 * Test method for {@link FileDAO#readFromFromFile()} with wrong behaviour.
	 * 
	 * @throws TechnicalDAOException
	 *             - expected exception
	 * @throws TechnicalException
	 *             - unexpected exception
	 */
	@Test(expected = TechnicalDAOException.class)
	public void testReadFromFromFileTechnicalDAOExcpetion() throws TechnicalDAOException, TechnicalException {
		mockStatic(JAXBUtil.class);
		when(JAXBUtil.unmarshall(TaskStore.class, file)).thenThrow(TechnicalException.class);
		fileDAO.readFromFromFile();
		fail();
	}

	/**
	 * Test method for {@link FileDAO#writeToFile(TaskStore))} with correct
	 * behaviour.
	 * 
	 * @throws TechnicalDAOException
	 *             - unexpected exception
	 * @throws TechnicalException
	 *             - unexpected exception
	 */
	@Test
	public void testWriteToFile() throws TechnicalDAOException, TechnicalException {
		mockStatic(JAXBUtil.class);
		when(JAXBUtil.marshall(taskStore, TaskStore.class)).thenReturn(TestParameters.TEST_SW);
		fileDAO.writeToFile(taskStore);
		when(JAXBUtil.unmarshall(TaskStore.class, file)).thenReturn(taskStore);
		assertEquals(taskStore, fileDAO.readFromFromFile());
	}
	
	/**
	 * Test method for {@link FileDAO#writeToFile(TaskStore)()} with wrong
	 * behaviour.
	 * 
	 * @throws TechnicalDAOException
	 *             - expected exception
	 * @throws TechnicalException
	 *             - unexpected exception
	 */
	@Test (expected = TechnicalDAOException.class)
	public void testWriteToFileMarshallFails () throws TechnicalDAOException, TechnicalException {
		mockStatic(JAXBUtil.class);
		when(JAXBUtil.marshall(taskStore, TaskStore.class)).thenThrow(TechnicalException.class);
		fileDAO.writeToFile(taskStore);
	}
	/**
	 * Test method for {@link FileDAO#writeToFile(TaskStore)()} with wrong
	 * behaviour. When {@link StringWriter#close()} fails
	 * @throws TechnicalDAOException
	 *             - expected exception
	 * @throws TechnicalException
	 *             - unexpected exception
	 * @throws IOException
	 *             - unexpected exception
	 */
	@Test (expected = TechnicalDAOException.class)
	public void testWriteToFileStringWriterCloseNull () throws TechnicalDAOException, TechnicalException, IOException {
		StringWriter sw = Mockito.mock(StringWriter.class);
		mockStatic(JAXBUtil.class);
		when(JAXBUtil.marshall(taskStore, TaskStore.class)).thenReturn(sw);
		Mockito.doThrow(Exception.class).when(sw).close();
		fileDAO.writeToFile(taskStore);
	}
	/**
	 * Test method for {@link FileDAO#writeToFile(TaskStore)()} with wrong
	 * behaviour.
	 * 
	 * @throws TechnicalDAOException
	 *             - expected exception
	 * @throws TechnicalException
	 *             - unexpected exception
	 */
	@Test (expected = TechnicalDAOException.class)
	public void testWriteToFileStringWriterNull () throws TechnicalDAOException, TechnicalException {
		PowerMockito.mockStatic(JAXBUtil.class);
		PowerMockito.when(JAXBUtil.marshall(taskStore, TaskStore.class)).thenThrow(TechnicalException.class);
		fileDAO.writeToFile(taskStore);
	}
	
	/**
	 * Test method for {@link FileDAO#getList()} with correct
	 * behaviour.
	 * @throws TechnicalDAOException
	 *             - unexpected exception
	 */
	@Test
	public void testGetList() throws TechnicalDAOException {
		assertNull(fileDAO.getList());
	}
	
	/**
	 * Test method for {@link FileDAO#fetchById(long)} with correct
	 * behaviour. 
	 * @throws TechnicalDAOException
	 *             - unexpected exception
	 */
	@Test
	public void testFetchById() throws TechnicalDAOException {
		assertNull(fileDAO.fetchById(TestParameters.TEST_LONG));
	}
}
