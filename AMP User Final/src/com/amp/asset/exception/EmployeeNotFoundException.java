package com.amp.asset.exception;

public class EmployeeNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public EmployeeNotFoundException() {
		super("Sorry, no such User exists in our Database. Please check credentials again.");
	}

	public EmployeeNotFoundException(String message) {
		super(message);
	}
	
}
