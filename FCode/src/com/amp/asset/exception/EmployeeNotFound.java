package com.amp.asset.exception;

public class EmployeeNotFound extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public EmployeeNotFound()
	{
		super("You are not registered user ");
	}
	public EmployeeNotFound(String msg)
	{
		super(msg);
	}
}
