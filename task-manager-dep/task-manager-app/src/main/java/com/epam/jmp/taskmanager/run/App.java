package com.epam.jmp.taskmanager.run;

import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

import org.apache.log4j.Logger;

import com.epam.jmp.taskmanager.data.dao.FileDAO;
import com.epam.jmp.taskmanager.data.dao.util.thread.FileReaderThread;
import com.epam.jmp.taskmanager.data.dao.util.thread.FileWriterThread;
import com.epam.jmp.taskmanager.data.pool.FilePool;
import com.epam.jmp.taskmanager.model.TaskStore;
import com.epam.jmp.taskmanager.util.MagicNumbers;
import com.epam.jmp.taskmanager.util.TaskStoreGenearator;

/**
 * Application runner
 * 
 * @author Aliaksei_Hlazkou
 * 
 */
public class App {
	/** logger */
	private static final Logger LOG = Logger.getLogger(App.class);

	public static void main(String[] args) throws Exception, RuntimeException {
		// we need to start all threads at the same time. so, we will create
		// latch
		final CountDownLatch latch = new CountDownLatch(MagicNumbers.MAGIC_ONE);
		// path for jar
		String path = "store//TaksStore.xml";
		LOG.info("Read from file " + path);
		// path for eclipse run
		// String path = "src\\main\\resources\\store\\TaskStore.xml";
		FilePool.getInstance().setPoolSize(FilePool.FILE_POOL_SIZE);
		FilePool.getInstance().setUrl(path);
		FilePool.getInstance().initPool();

		FileDAO fileDAO = new FileDAO();
		fileDAO.setPool(FilePool.getInstance());

		for (int i = MagicNumbers.MAGIC_ONE; i < MagicNumbers.MAGIC_FIVE; i++) {
			TaskStore ts = TaskStoreGenearator.generate();
			FileWriterThread fwt = new FileWriterThread(latch,
					String.valueOf(i), fileDAO, ts);
			fwt.start();
			LOG.info("Thread #" + fwt.getThreadName()
					+ " was created with TaskStore : " + ts);
		}
		for (int i = MagicNumbers.MAGIC_FIVE; i < MagicNumbers.MAGIC_TEN; i++) {
			FileReaderThread frt = new FileReaderThread(latch,
					String.valueOf(i), fileDAO);
			frt.start();
			LOG.info("Thread #" + frt.getThreadName()
					+ " was created to read file");
		}

		// start all created thread at the same exactly time
		latch.countDown();
	}
}
