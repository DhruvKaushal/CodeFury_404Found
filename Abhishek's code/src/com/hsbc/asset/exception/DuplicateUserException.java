package com.hsbc.asset.exception;

public class DuplicateUserException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public DuplicateUserException() {
		super("User already exists.");
	}

	public DuplicateUserException(String message) {
		super(message);
	}
	
	
	
}
