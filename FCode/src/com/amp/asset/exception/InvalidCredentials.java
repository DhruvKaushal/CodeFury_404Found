package com.amp.asset.exception;

public class InvalidCredentials extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public InvalidCredentials()
	{
		super("Credentials are invalid");
	}
	public InvalidCredentials(String msg)
	{
		super(msg);
	}
}
