package com.amp.asset.exception;

public class CategoryException extends Exception {

	private static final long serialVersionUID = 1L;

	public CategoryException(String args) {
		super(args);
	}
	
	public CategoryException() {
		super("Sorry Category not found.");
	}
}
