package com.epam.jmp.taskmanager.data.pool;

import com.epam.jmp.taskmanager.exception.ConnectionPoolException;

/**
 * Interface which create contract for pool objects
 * 
 * @author Aliaksei_Hlazkou
 *
 * @param <S> Connection type
 */
public interface IPool<S> {

	/**
	 * Initialization of the ConnectionPool class
	 * 
	 * @throws ConnectionPoolException
	 *             in case of problems width connection or driver
	 */
	void initPool() throws ConnectionPoolException;

	/**
	 * Gives connection
	 * 
	 * @return Connection object
	 * @throws ConnectionPoolException
	 *             in case of problem with taking connection
	 */
	S takeConnection() throws ConnectionPoolException;

	/**
	 * Release connection
	 * 
	 * @param connection
	 *            Connection that is nesseccary to be released
	 * @throws ConnectionPoolException
	 *             in case of problem with releasing connection
	 */
	void releaseConnection(S connection) throws ConnectionPoolException;


	/**
	 * Close connection pool
	 */
	void closePool();
}
