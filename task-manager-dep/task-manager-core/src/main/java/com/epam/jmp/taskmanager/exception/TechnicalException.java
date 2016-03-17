package com.epam.jmp.taskmanager.exception;

/**
 * Exception that is thrown while there are any technical exception.
 * 
 * @author Aliaksei_Hlazkou
 * 
 */
public class TechnicalException extends Exception {

	/** serialVersionUID */
	private static final long serialVersionUID = 6033435641584933590L;

	/**
	 * Empty constructor
	 */
	public TechnicalException() {
	}
	
	/**
	 * Constructor
	 * 
	 * @param message
	 *            the message to set
	 */
	public TechnicalException(String message) {
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
	public TechnicalException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructor
	 * 
	 * @param cause
	 *            the {@link Throwable} to set
	 */
	public TechnicalException(Throwable cause) {
		super(cause);
	}

}
