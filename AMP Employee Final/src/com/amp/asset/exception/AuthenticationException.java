package com.amp.asset.exception;

public class AuthenticationException extends Exception {
	private static final long serialVersionUID = 1L;

	public AuthenticationException() {
		super("Username or Password is incorrect. Please try again.");
	}

	public AuthenticationException(String msg) {
		super(msg);
	}

}
