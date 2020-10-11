package com.amp.asset.exception;

public class NoProductBorrowedException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public NoProductBorrowedException() {
		super("No assets have been borrowed by you. Kindly go to the Asset Page for doing so.");
	}


	public NoProductBorrowedException(String msg) {
		super(msg);
	}
}
