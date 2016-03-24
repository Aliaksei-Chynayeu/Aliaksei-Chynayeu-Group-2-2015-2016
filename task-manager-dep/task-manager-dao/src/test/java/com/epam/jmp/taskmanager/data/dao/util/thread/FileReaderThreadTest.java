package com.epam.jmp.taskmanager.data.dao.util.thread;

import java.util.concurrent.CountDownLatch;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;

import com.epam.jmp.taskmanager.data.dao.FileDAO;
import com.epam.jmp.taskmanager.exception.TechnicalDAOException;

@RunWith(PowerMockRunner.class)
public class FileReaderThreadTest {
	@Mock
	FileReaderThread frtt = null;
	@Mock
	CountDownLatch latch = PowerMockito.mock(CountDownLatch.class);
	@Mock
	FileDAO fd = PowerMockito.mock(FileDAO.class);
	
	private interface TestParameters {
		String TEST_THREAD_NAME = "name";
	}
	
	@Before
	public void setUp () throws Exception {
		frtt = new FileReaderThread(latch, TestParameters.TEST_THREAD_NAME, fd);
	}
	
	@Test
	public void testRun() throws InterruptedException, TechnicalDAOException {
		frtt.start();
		Mockito.verify(latch).await();
		Mockito.verify(fd).readFromFromFile();
	}
}
