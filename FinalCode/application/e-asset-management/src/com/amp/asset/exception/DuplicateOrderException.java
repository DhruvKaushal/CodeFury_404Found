package com.amp.asset.exception;

public class DuplicateOrderException extends Exception {

	private static final long serialVersionUID = 1L;

	public DuplicateOrderException() {
		super("You already have an item of this category! Multiple items of same category not allowed.");
	}

	public DuplicateOrderException(String message) {
		super(message);
	}
	
}
