package com.epam.jmp.taskmanager.data.dao.util.thread;

import java.util.concurrent.CountDownLatch;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;

import com.epam.jmp.taskmanager.data.dao.FileDAO;
import com.epam.jmp.taskmanager.exception.TechnicalDAOException;
import com.epam.jmp.taskmanager.model.TaskStore;

public class FileWriterThreadTest {
	@Mock
	FileWriterThread fwtt = null;
	@Mock
	CountDownLatch latch = PowerMockito.mock(CountDownLatch.class);
	@Mock
	FileDAO fd = PowerMockito.mock(FileDAO.class);
	@Mock
	TaskStore ts = PowerMockito.mock(TaskStore.class);

	private interface TestParameters {
		String TEST_THREAD_NAME = "name";
	}

	@Before
	public void setUp() throws Exception {
		fwtt = new FileWriterThread(latch, TestParameters.TEST_THREAD_NAME, fd,
				ts);
	}

	@Test
	public void testRun() throws InterruptedException, TechnicalDAOException {
		fwtt.start();
		Mockito.verify(latch).await();
		Mockito.verify(fd).writeToFile(ts);

	}
}
