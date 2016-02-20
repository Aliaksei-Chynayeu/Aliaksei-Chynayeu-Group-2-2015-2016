package com.epam.jmp.taskmanager.exception;

/**
 * Exception that is thrown while there are any exception on DAO level.
 * 
 * @author Aliaksei_Hlazkou
 * 
 */
public class TechnicalDAOException extends Exception {

	/** serialVersionUID */
	private static final long serialVersionUID = 6033435641584933590L;

	/**
	 * Empty constructor
	 */
	public TechnicalDAOException() {
	}
	
	/**
	 * Constructor
	 * 
	 * @param message
	 *            the message to set
	 */
	public TechnicalDAOException(String message) {
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
	public TechnicalDAOException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructor
	 * 
	 * @param cause
	 *            the {@link Throwable} to set
	 */
	public TechnicalDAOException(Throwable cause) {
		super(cause);
	}

}
