package com.epam.jmp.taskmanager.data.pool;

import java.io.File;
import java.util.concurrent.ArrayBlockingQueue;

import org.apache.log4j.Logger;

import com.epam.jmp.taskmanager.exception.ConnectionPoolException;

/**
 * Class return, create connection, release, clear all connections. Singleton.
 * 
 * @author Aliaksei_Hlazkou
 */
public final class FilePool extends AbstractPool<File> {

	/**
	 * logger
	 */
	private static final Logger LOG = Logger.getLogger(FilePool.class);
	/**
	 * instance of the connection pool.
	 */
	private static FilePool instance = null;
	
	/** file pool size */
	public static final int FILE_POOL_SIZE = 1;

	private FilePool() {
	}

	/**
	 * Return the instanse of the connectionPooL class
	 * 
	 * @return instance og thie ConnectionPool class
	 * @throws RuntimeException
	 *             if there are any problems with connection
	 */
	public static FilePool getInstance() throws RuntimeException {
		if (isConnected.get()) {
			if (instance == null) {
				LOCK.lock();
				try {
					if (instance == null) {
						instance = new FilePool();
					}
				} finally {
					LOCK.unlock();
				}
			}
		}
		return instance;

	}

	/**
	 * Initialization of the File pool class
	 * 
	 * @throws ConnectionPoolException
	 *             in case of problems width connection or driver
	 */
	public void initPool() throws ConnectionPoolException {
		try {
			
			freeConnections = new ArrayBlockingQueue<File>(getPoolSize());
			busyConnections = new ArrayBlockingQueue<File>(getPoolSize());
			for (int i = 0; i < getPoolSize(); i++) {
				freeConnections.add(new File(this.getUrl()));
			}
		} catch (Exception e) {
			throw new ConnectionPoolException("Connection is wrong", e);
		}
	}


	/**
	 * Close ConnectionPool
	 */
	public void closePool() {
		isConnected.set(Boolean.FALSE);
		if (instance != null) {
			try {
				instance.clearConnectionQueue();
			} catch (ConnectionPoolException ex) {
				LOG.warn("Can't close connection pool", ex);
			}
			instance = null;
		}
	}
	/**
	 * Clear all connections
	 * NOTE: Do not use it manually!
	 * @throws ConnectionPoolException
	 *             in case of problem with clear all connection
	 */
	private void clearConnectionQueue() throws ConnectionPoolException {
		try {
			File file;
			while ((file = freeConnections.poll()) != null) {
				file = null;
			}
			while ((file = busyConnections.poll()) != null) {
				file = null;
			}
		} catch (Exception e) {
			throw new ConnectionPoolException(
					"Can't clear all pool connections", e);
		}
	}
}
