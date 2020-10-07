package com.hsbc.asset.exception;

public class DatabaseDownException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public DatabaseDownException() {
		super("Sorry, our Database is Down. Please try again later.");
	}

	public DatabaseDownException(String message) {
		super(message);
	}
}