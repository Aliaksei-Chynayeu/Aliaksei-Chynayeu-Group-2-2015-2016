package com.epam.jmp.taskmanager.util.thread;

import java.io.File;
import java.util.concurrent.CountDownLatch;

import org.apache.log4j.Logger;

import com.epam.jmp.taskmanager.data.dao.FileDAO;
import com.epam.jmp.taskmanager.exception.LogicalException;
import com.epam.jmp.taskmanager.exception.TechnicalDAOException;
import com.epam.jmp.taskmanager.model.TaskStore;

/**
 * Thread to write file
 * 
 * @author Aliaksei_Hlazkou
 * 
 */
public class FileReaderThread extends FileThread {

	/** logger */
	private static final Logger LOG = Logger.getLogger(FileReaderThread.class);

	/**
	 * Constructor
	 * 
	 * @param latch
	 *            the CountDownLatch to set
	 * @param threadName
	 *            the thread name to set
	 * @throws LogicalException
	 *             in case of exception
	 */
	public FileReaderThread(CountDownLatch latch, String threadName, FileDAO dao) throws LogicalException {
		super(latch, threadName, dao);
	}

	/**
	 * Runs thread
	 */
	public void run() {
		super.run();
		LOG.info("Thread #" + getThreadName() + " is running.");
		try {
			TaskStore ts = dao.readFromFromFile();
			LOG.info("Thread #"+ getThreadName() + " read the file. \n" + ts);
		} catch (TechnicalDAOException e) {
			LOG.warn(e);
		}
	}
}
