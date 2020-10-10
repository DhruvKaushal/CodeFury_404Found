package com.amp.asset.exception;

public class ServerDown extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ServerDown()
	{
		super("Server is Down");
	}
	public ServerDown(String msg)
	{
		super(msg);
	}
}
