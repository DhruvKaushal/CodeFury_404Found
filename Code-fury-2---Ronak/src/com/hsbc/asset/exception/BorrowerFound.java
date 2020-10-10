package com.hsbc.asset.exception;

public class BorrowerFound extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public BorrowerFound()
	{
		super("Borrower is Found");
	}
	public BorrowerFound(String msg)
	{
		super(msg);
	}
}
