package com.hsbc.asset.exception;

public class WrongCredentials extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public  WrongCredentials()
	{
		super("Either your username or password is incorrect.");
	}
	public  WrongCredentials(String msg)
	{
		super(msg);
	}
}
