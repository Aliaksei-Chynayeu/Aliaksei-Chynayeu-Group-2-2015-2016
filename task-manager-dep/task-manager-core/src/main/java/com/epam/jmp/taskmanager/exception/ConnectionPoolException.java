package com.epam.jmp.taskmanager.exception;

/**
 * Exception that is thrown while there are any exception during connection
 * process.
 * 
 * @author Aliaksei_Hlazkou
 * 
 */
public class ConnectionPoolException extends Exception {

	/** serialVersionUID */
	private static final long serialVersionUID = 2057307487137939574L;

	/**
	 * Empty constructor
	 */
	public ConnectionPoolException() {
	}
	
	/**
	 * Constructor
	 * 
	 * @param message
	 *            the message to set
	 */
	public ConnectionPoolException(String message) {
		super(message);
	}

	/**
	 * Constructor
	 * 
	 * @param message
	 *            the message to set
	 * @param cause
	 *            the {@link Throwable} to set
	 */
	public ConnectionPoolException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructor
	 * 
	 * @param cause
	 *            the {@link Throwable} to set
	 */
	public ConnectionPoolException(Throwable cause) {
		super(cause);
	}

}
