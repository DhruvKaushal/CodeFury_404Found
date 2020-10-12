package com.amp.asset.exception;

public class DuplicateEmployeeException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public DuplicateEmployeeException() {
		super("User already exists.");
	}

	public DuplicateEmployeeException(String message) {
		super(message);
	}
	
	
	
}
