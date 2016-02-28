package com.epam.jmp.taskmanager.data.pool;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;

import org.apache.log4j.Logger;

import com.epam.jmp.taskmanager.exception.ConnectionPoolException;

/**
 * Class return, create connection, release, clear all connections. Singleton.
 *
 * @author Aliaksei_Hlazkou
 */
public final class ConnectionPool extends AbstractPool<Connection>{

    /**
     * logger
     */
    private static final Logger LOG = Logger.getLogger(ConnectionPool.class);
    /**
     * instance of the connection pool.
     */
    private static ConnectionPool instance = null;

    
    private ConnectionPool() {
    }

    /**
     * Return the instanse of the connectionPooL class
     *
     * @return instance og thie ConnectionPool class
     * @throws RuntimeException if there are any problems with connection
     */
    public static ConnectionPool getInstance() throws RuntimeException {
        if (isConnected.get()) {
            if (instance == null) {
                LOCK.lock();
                try {
                    if (instance == null) {
                        instance = new ConnectionPool();
                    }
                } finally {
                    LOCK.unlock();
                }
            }
        }
        return instance;

    }

    /**
     * Initialization of the ConnectionPool class
     *
     * @throws ConnectionPoolException in case of problems width connection or
     * driver
     */
    public void initPool() throws ConnectionPoolException {
        try {
            Class.forName(getDriver());
			setFreeConnections(new ArrayBlockingQueue<Connection>(getPoolSize()));
			setBusyConnections(new ArrayBlockingQueue<Connection>(getPoolSize()));
            for (int i = 0; i < getPoolSize(); i++) {
                Connection connection = DriverManager.getConnection(getUrl(), getUser(), getPassword());
                getFreeConnections().add(connection);
            }
        } catch (ClassNotFoundException e) {
            throw new ConnectionPoolException("Driver  is wrong", e);
        } catch (SQLException e) {
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
			Connection connection;
			while ((connection = getFreeConnections().poll()) != null) {
				connection.close();
				;
			}
			while ((connection = getBusyConnections().poll()) != null) {
				connection.close();
			}
		} catch (Exception e) {
			throw new ConnectionPoolException(
					"Can't clear all pool connections", e);
		}
	}

}
