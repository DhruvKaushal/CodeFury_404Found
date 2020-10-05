package com.hsbc.asset.model.beans;

import java.sql.Timestamp;

public abstract class User {
	private int user_id;
	private String name;
	private String username;
	private String role;
	private long contactNo;
	private String email;
	private Timestamp signup_date_and_time;
	private Timestamp login_date_and_time;
	private String password;
	
	//Setters and getters
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public long getContactNo() {
		return contactNo;
	}
	public void setContactNo(long contact) {
		this.contactNo = contact;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Timestamp getSignup_date_and_time() {
		return signup_date_and_time;
	}
	public void setSignup_date_and_time(Timestamp signup_date_and_time) {
		this.signup_date_and_time = signup_date_and_time;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", username=" + username + ", role=" + role + ", contactNo=" + contactNo
				+ ", email=" + email + ", signup_date_and_time=" + signup_date_and_time + ", password=" + password
				+ "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Timestamp getLogin_date_and_time() {
		return login_date_and_time;
	}
	public void setLogin_date_and_time(Timestamp login_date_and_time) {
		this.login_date_and_time = login_date_and_time;
	}
	
}
