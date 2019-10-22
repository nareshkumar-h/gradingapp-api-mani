package com.revature.gradingapp.exception;

public class ValidatorException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ValidatorException(String message) {

		super(message);
	}

	public ValidatorException(String message, Throwable e) {

		super(message,e);
	}

}
