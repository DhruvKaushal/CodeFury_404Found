package com.hsbc.asset.exception;

public class BorrowerNotFound extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public BorrowerNotFound()
	{
		super("Borrower is NOT Found");
	}
	public BorrowerNotFound(String msg)
	{
		super(msg);
	}
}
