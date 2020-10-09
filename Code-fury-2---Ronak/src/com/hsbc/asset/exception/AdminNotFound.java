package com.hsbc.asset.exception;

public class AdminNotFound extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public AdminNotFound()
	{
		super("Admin is Not Found");
	}
	public AdminNotFound(String msg)
	{
		super(msg);
	}
}
