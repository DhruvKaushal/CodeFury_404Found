package com.hsbc.model.beans;


public class Borrower extends User
{
	public Borrower(String email,String username)
	{
		super(email,username);
	}
	public Borrower(String username, String password,String date_time)
	{
		super(username,password,date_time);
	}
	public Borrower(String name, String role, long contact, String email, String username, String password,String date_time)
	{
		super(name,role,contact,email,username,password,date_time);
	}
	public Borrower(int user_id,String name,String email)
	{
		super(user_id,name,email);
	}
}
