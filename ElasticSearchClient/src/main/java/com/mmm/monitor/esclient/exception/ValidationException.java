package com.mmm.monitor.esclient.exception;

/**
 * Exception class for Validation Errors 
 * 
 * @author RamuV
 */
public class ValidationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ValidationException(String msg){
		super(msg);
	}
}
