package com.publisap.stockStats.exception;

/**
 * For HTTP 404 errros
 */
public class NoResultFoundException extends RuntimeException {
    
	private static final long serialVersionUID = 1L;

	public NoResultFoundException() {
        super();
    }

    public NoResultFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoResultFoundException(String message) {
        super(message);
    }

    public NoResultFoundException(Throwable cause) {
        super(cause);
    }

}
