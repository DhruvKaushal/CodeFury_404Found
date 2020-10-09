package com.hsbc.asset.model.beans;

import java.sql.Timestamp;

public class User {
	private int user_id;
	private String name;
	private String username;
	private long contactNo;
	private String email;
	private Timestamp signup_date_and_time;
	private Timestamp login_date_and_time;
	private String password;
	private String salt;
	
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
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", name=" + name + ", username=" + username + ", contactNo=" + contactNo
				+ ", email=" + email + ", signup_date_and_time=" + signup_date_and_time + ", login_date_and_time="
				+ login_date_and_time + ", password=" + password + ", salt=" + salt + "]";
	}
	
	
}
