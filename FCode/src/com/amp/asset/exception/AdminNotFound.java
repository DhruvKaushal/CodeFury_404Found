package com.amp.asset.exception;

public class AdminNotFound extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public AdminNotFound()
	{
		super("Admin is not Found");
	}
	public AdminNotFound(String msg)
	{
		super(msg);
	}
}
