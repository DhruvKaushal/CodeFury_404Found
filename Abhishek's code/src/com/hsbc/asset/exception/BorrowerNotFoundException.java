package com.hsbc.asset.exception;

public class BorrowerNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public BorrowerNotFoundException() {
		super("Sorry, no such User exists in our Database. Please check credentials again.");
	}

	public BorrowerNotFoundException(String message) {
		super(message);
	}
	
}
