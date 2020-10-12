package com.amp.asset.exception;

public class ServerDownException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public ServerDownException() {
		super("Sorry, our Database is Down. Please try again later.");
	}

	public ServerDownException(String message) {
		super(message);
	}
}