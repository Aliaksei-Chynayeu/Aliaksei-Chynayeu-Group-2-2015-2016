package com.epam.jmp.taskmanager.data.dao;

import java.sql.Statement;

import org.apache.log4j.Logger;

import com.epam.jmp.taskmanager.data.pool.IPool;
import com.epam.jmp.taskmanager.exception.ConnectionPoolException;
import com.epam.jmp.taskmanager.exception.TechnicalDAOException;

/**
 * 
 * @author Aliaksei_Hlazkou
 * 
 * @param <T>
 *            object to work with
 * @param <S>
 *            connection type
 */
public abstract class AbstractDAO<T, S>  implements DAOImpl<T>{
	/** logger */
	private static final Logger LOG = Logger.getLogger(AbstractDAO.class);
	/** connections pool instance */
	private IPool<S> pool;

	public IPool<S> getPool() {
		return pool;
	}

	/**
	 * Sets connections pool
	 * 
	 * @param pool
	 *            the connections pool to set
	 * @throws ConnectionPoolException
	 */
	public void setPool(IPool<S> pool)
			throws ConnectionPoolException {
		this.pool = pool;
		getLogger().info("Pool was setted - " + pool.getClass().getName());
	}

	/**
	 * Trying to get connection
	 * 
	 * @return the connections instance
	 * @throws TechnicalDAOException
	 *             in case of exception
	 */
	protected S getConnection() throws TechnicalDAOException {
		S connection = null;
		try {

			connection = this.getPool().takeConnection();
		} catch (Exception e) {
			throw new TechnicalDAOException(e);
		}
		getLogger().info("Connection was taken.");
		return connection;
	}

	/**
	 * Releases provided connections
	 * 
	 * @param connection
	 * @throws TechnicalDAOException
	 */
	protected void releaseConnection(S connection) throws TechnicalDAOException {
		try {
			getLogger().info("Trying to release connection.");
			this.getPool().releaseConnection(connection);
		} catch (Exception e) {
			throw new TechnicalDAOException(e);
		}
		LOG.info("Connection was released");
	}

	/**
	 * Closes SQL statements
	 * 
	 * @param st
	 *            statement that is need to be closed
	 * @throws TechnicalDAOException
	 *             in case of exceptions
	 */
	protected void closeStatement(Statement st) throws TechnicalDAOException {
		if (st != null) {
			try {
				getLogger().info("Trying to close statement");
				st.close();
			} catch (Throwable e) {
				throw new TechnicalDAOException(e);
			}
			getLogger().info("Statement was closed");
		}
	}

	@Override
	public Logger getLogger() {
		return LOG;
	}
}
