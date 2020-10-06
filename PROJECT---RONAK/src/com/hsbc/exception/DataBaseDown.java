package com.hsbc.exception;

public class DataBaseDown extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public DataBaseDown()
	{
		super("DataBase is Down, please register after some time");
	}
	public DataBaseDown(String msg)
	{
		super(msg);
	}
}
