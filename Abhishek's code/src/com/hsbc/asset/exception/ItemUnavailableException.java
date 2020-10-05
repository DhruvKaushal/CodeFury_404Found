package com.hsbc.asset.exception;

public class ItemUnavailableException extends Exception {
	private static final long serialVersionUID = 1L;

	public ItemUnavailableException() {
		super();
	}

	public ItemUnavailableException(String msg) {
		super(msg);
	}
}
