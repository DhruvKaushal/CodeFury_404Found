package com.amp.asset.exception;

public class ItemUnavailableException extends Exception {
	private static final long serialVersionUID = 1L;

	public ItemUnavailableException() {
		super("No such item exists of this category. Kindly check back later.");
	}

	public ItemUnavailableException(String msg) {
		super(msg);
	}
}
