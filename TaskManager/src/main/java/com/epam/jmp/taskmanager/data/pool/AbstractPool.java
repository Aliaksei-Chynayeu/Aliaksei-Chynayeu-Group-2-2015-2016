package com.epam.jmp.taskmanager.data.pool;

import java.io.Closeable;
import java.sql.Connection;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.log4j.Logger;

import com.epam.jmp.taskmanager.exception.ConnectionPoolException;

/**
 * Connections to database
 * 
 * @author Aliaksei_Hlazkou
 * 
 * @param <S>
 *            Connection type
 */
public abstract class AbstractPool<S> implements IPool<S> {
	/**
	 * logger
	 */
	private static final Logger LOG = Logger.getLogger(AbstractPool.class);
	/** Object of BlockingQueue class with queue of free connections */
	protected BlockingQueue<S> freeConnections;
	/** Object of BlockingQueue class with queue of busy connections */
	protected BlockingQueue<S> busyConnections;
	/** database driver name */
	private String driver;
	/** databese url */
	private String url;
	/** user */
	private String user;
	/** password */
	private String password;
	/** pool size */
	private int poolSize;
	/** Flag that shows if pool can give connection */
	protected static AtomicBoolean isConnected = new AtomicBoolean(Boolean.TRUE);
	/** Lock class object */
	protected static final Lock LOCK = new ReentrantLock();

	/**
	 * Getter for the url field
	 * 
	 * @return teh url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Getter for the user field
	 * 
	 * @return teh user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * Getter for the password field
	 * 
	 * @return
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Setter for the url field
	 * 
	 * @param url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Setter for the user field
	 * 
	 * @param user
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * Setter for the password field
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Setter for the pool size field
	 * 
	 * @return
	 */
	public int getPoolSize() {
		return poolSize;
	}

	/**
	 * Setter for the pool size field
	 * 
	 * @param poolSize
	 */
	public void setPoolSize(int poolSize) {
		this.poolSize = poolSize;
	}

	/**
	 * Setter for the driver field
	 * 
	 * @return
	 */
	public String getDriver() {
		return driver;
	}

	/**
	 * Setter for the drive field
	 * 
	 * @param driver
	 */
	public void setDriver(String driver) {
		this.driver = driver;
	}

	/**
	 * Initialization of the ConnectionPool class
	 * 
	 * @throws ConnectionPoolException
	 *             in case of problems width connection or driver
	 */
	public abstract void initPool() throws ConnectionPoolException;


	/**
	 * Gives connection
	 * 
	 * @return Connection object
	 * @throws ConnectionPoolException
	 *             in case of problem with taking connection
	 */
	public S takeConnection() throws ConnectionPoolException {
		S connection = null;
		try {
			connection = freeConnections.take();
			busyConnections.put(connection);
		} catch (InterruptedException e) {
			throw new ConnectionPoolException(e);
		}
		return connection;
	}

	/**
	 * Release connection
	 * 
	 * @param connection
	 *            Connection that is nesseccary to be released
	 * @throws ConnectionPoolException
	 *             in case of problem with releasing connection
	 */
	public void releaseConnection(S connection)
			throws ConnectionPoolException {
		try {
			busyConnections.remove(connection);
			freeConnections.put(connection);
		} catch (InterruptedException e) {
			throw new ConnectionPoolException("Can't release connection", e);

		}
	}
}
