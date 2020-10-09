package com.hsbc.asset.exception;

public class ServerDown extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ServerDown()
	{
		super("Server is not Working");
	}
	public ServerDown(String msg)
	{
		super(msg);
	}
}
