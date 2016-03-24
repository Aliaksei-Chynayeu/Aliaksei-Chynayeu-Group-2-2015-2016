package com.epam.jmp.taskmanager.data.dao.util.thread;

import java.util.concurrent.CountDownLatch;

import org.apache.log4j.Logger;

import com.epam.jmp.taskmanager.data.dao.FileDAO;
import com.epam.jmp.taskmanager.exception.LogicalException;

public class FileThread extends Thread {
	/** logger */
	private static final Logger LOG = Logger.getLogger(FileThread.class);
	/** latch */
	private CountDownLatch latch;
	/** thread name */
	private String threadName;
	/** dao */
	FileDAO dao;

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
	public FileThread(CountDownLatch latch, String threadName, FileDAO dao) throws LogicalException {
		if (latch == null) {
			throw new LogicalException("Wrong latch argument");
		}
		this.latch = latch;
		this.threadName = threadName;
		this.dao = dao;
		LOG.info("Thread #" + getThreadName() + " was created");
	}

	/**
	 * Runs thread
	 */
	public void run() {
		try {
			LOG.info("Thread" + getThreadName() + " was started.");
			LOG.info("Thread" + getThreadName() + " is waiting to proceed.");
			latch.await();
		} catch (InterruptedException e) {
			LOG.warn(e);
		}
	};

	/**
	 * Getter
	 * 
	 * @return latch
	 */
	public CountDownLatch getLatch() {
		return latch;
	}

	/**
	 * Setter
	 * 
	 * @param latch
	 *            the latch to set
	 */
	public void setLatch(CountDownLatch latch) {
		this.latch = latch;
	}

	/**
	 * Getter
	 * 
	 * @return the thread name
	 */
	public String getThreadName() {
		return threadName;
	}

	/**
	 * Setter
	 * 
	 * @param name
	 *            the thread name to set
	 */
	public void setThreadName(String name) {
		this.threadName = name;
	}

	/**
	 * Getter for DAO
	 * 
	 * @return the dao
	 */
	public FileDAO getDao() {
		return dao;
	}

	/**
	 * Setter for the dao
	 * 
	 * @param dao
	 *            the dao to return
	 */
	public void setDao(FileDAO dao) {
		this.dao = dao;
	}

}
