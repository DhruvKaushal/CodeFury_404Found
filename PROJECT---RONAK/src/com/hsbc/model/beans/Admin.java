package com.hsbc.model.beans;



public class Admin extends User
{
	public Admin(String username, String password,String date_time)
	{
		super(username,password,date_time);
	}
	public Admin (String name, String role, long contact, String email, String username, String password,String date_time)
	{
		super(name,role,contact,email,username,password,date_time);
	}
	public Admin(int user_id,String name,String email)
	{
		super(user_id,name,email);
	}
	public Admin(String email,String username)
	{
		super(email,username);
	}
}
