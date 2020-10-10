package com.amp.asset.exception;

public class EmployeeFound extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public EmployeeFound()
	{
		super("Details are already registered");
	}
	public EmployeeFound(String msg)
	{
		super(msg);
	}
}
