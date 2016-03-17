package com.epam.jmp.taskmanager.data.dao.util.thread;

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
public class FileWriterThread extends FileThread {
	/** logger */
	private static final Logger LOG = Logger.getLogger(FileWriterThread.class);
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
	/** task store */
	private TaskStore ts;

	public FileWriterThread(CountDownLatch latch, String threadName, FileDAO dao, TaskStore ts) throws LogicalException {
		super(latch, threadName, dao);
		this.ts = ts;
	}

	/**
	 * Runs thread
	 */
	public void run() {
		super.run();
		LOG.info("Thread" + getThreadName() + " is running.");
		try {
			dao.writeToFile(ts);
			LOG.info("Thread #" + getThreadName()
					+ " saved TaskStore to file. \n" + ts);
		} catch (TechnicalDAOException e) {
			LOG.warn(e);
		}
	}

	/**
	 * Getter
	 * 
	 * @return the task store
	 */
	public TaskStore getTs() {
		return ts;
	}

	/**
	 * Setter
	 * 
	 * @param ts
	 *            teh task store to set
	 */
	public void setTs(TaskStore ts) {
		this.ts = ts;
	}

}
