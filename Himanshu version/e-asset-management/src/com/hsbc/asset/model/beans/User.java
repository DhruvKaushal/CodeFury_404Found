package com.hsbc.asset.model.beans;

import java.sql.Timestamp;

public class User {
	private int userId;
	private String name;
	private String userName;
	private String password;
	private String role;
	private String email;
	private long contact;
	private Timestamp date;
	
	
	public User(int userId, String name, String userName, String password, String role, String email, long contact,
			Timestamp date) {
		super();
		this.userId = userId;
		this.name = name;
		this.userName = userName;
		this.password = password;
		this.role = role;
		this.email = email;
		this.contact = contact;
		this.date = date;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUser_id(int userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getContact() {
		return contact;
	}
	public void setContact(long contact) {
		this.contact = contact;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	
	@Override 
	public String toString() {
		return "User [user_id ="+ userId + ", name = " + name + ", userName = " + userName +", password="+ password + ",role = "+ role + ", contact =" + contact + 
				", email = "+email + ", date=" +date + "]";
	}
}
