package com.amp.asset.exception;

public class NoProductBorrowedException extends Exception {

	private static final long serialVersionUID = 1L;

	public NoProductBorrowedException(String msg) {
		super(msg);
	}
}
