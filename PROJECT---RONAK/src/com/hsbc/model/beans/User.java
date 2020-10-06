package com.hsbc.model.beans;

import java.time.LocalDateTime;

public abstract class User
{
	private int user_id;
	private String name;
	private String role;
	private long contact;
	private String email;
	private String username;
	private String password;
	private String date_time;
	public User()
	{
		super();
	}
	
	// For login Purpose
	public User(String username,String password,String date_time) 
	{
		this.username = username;
		this.password = password;
		this.date_time=date_time;
	}
	
	public User(int user_id,String name,String email)
	{
		this.user_id = user_id;
		this.name = name;
		this.email=email;
	}
	// For Registration Purpose
	public User(String name, String role, long contact, String email, String username, String password,String date_time)
	{
		this.name = name;
		this.role = role;
		this.contact = contact;
		this.email = email;
		this.username = username;
		this.password = password;
		this.date_time=date_time;
	}
	public User(String email ,String username)
	{
		this.email=email;
		this.username=username;
	}
	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public long getContact() {
		return contact;
	}

	public void setContact(long contact) {
		this.contact = contact;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDate_time() {
		return date_time;
	}

	public void setDate_time(String date_time) {
		this.date_time = date_time;
	}
	
	
	
}
