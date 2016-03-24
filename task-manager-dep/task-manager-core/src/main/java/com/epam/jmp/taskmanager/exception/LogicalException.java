package com.epam.jmp.taskmanager.exception;

/**
 * Exception that is thrown while there are any technical exception.
 * 
 * @author Aliaksei_Hlazkou
 * 
 */
public class LogicalException extends Exception {

	/** serialVersionUID */
	private static final long serialVersionUID = 6033435641584933590L;

	/**
	 * Empty constructor
	 */
	public LogicalException() {
	}
	
	/**
	 * Constructor
	 * 
	 * @param message
	 *            the message to set
	 */
	public LogicalException(String message) {
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
	public LogicalException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructor
	 * 
	 * @param cause
	 *            the {@link Throwable} to set
	 */
	public LogicalException(Throwable cause) {
		super(cause);
	}

}
