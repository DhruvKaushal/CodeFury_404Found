package com.hsbc.asset.exception;

//Exception class to handle if database is down.

public class DbDown extends Exception{
	public DbDown(String msg) {
		super(msg);
	}
}
