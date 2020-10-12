package com.amp.asset.exception;

public class OrderNotAllowedException extends Exception {

	private static final long serialVersionUID = 1L;

	public OrderNotAllowedException(String msg) {
		super(msg);
	}
}
