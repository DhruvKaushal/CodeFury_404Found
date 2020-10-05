package com.hsbc.asset.exception;

public class AuthenticationException extends Exception {
	private static final long serialVersionUID = 1L;

	public AuthenticationException() {
		super();
	}

	public AuthenticationException(String msg) {
		super(msg);
	}

}
