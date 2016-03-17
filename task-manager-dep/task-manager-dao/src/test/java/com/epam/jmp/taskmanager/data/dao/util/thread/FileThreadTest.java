package com.epam.jmp.taskmanager.data.dao.util.thread;

import java.util.concurrent.CountDownLatch;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.modules.junit4.PowerMockRunner;

import com.epam.jmp.taskmanager.data.dao.FileDAO;

@RunWith(PowerMockRunner.class)
public class FileThreadTest {

	@InjectMocks
	FileThread ft = null;
	
	@Mock
	CountDownLatch latch = PowerMockito.mock(CountDownLatch.class);
	
	private interface TestParameters {
		String TEST_THREAD_NAME = "name";
	}
	
	@Before
	public void setUp () throws Exception {
		ft = new FileThread(latch, TestParameters.TEST_THREAD_NAME, PowerMockito.mock(FileDAO.class));
	}
	@Test
	public void testRun() throws InterruptedException {
		ft.start();
		Mockito.verify(latch).await();
	}
}
