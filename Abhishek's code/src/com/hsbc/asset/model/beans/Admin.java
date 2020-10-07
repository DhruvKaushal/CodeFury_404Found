package com.hsbc.asset.model.beans;

public class Admin {
	private int userId;
	private String userName;
	private String name;
	private String role;
	private String password;
	private long contact;
	private String email;
	private String signUpDate;
	private String signInDate;
	
	public Admin() {
		super();
	}

	public int getUserId() {
		return userId;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public String getName() {
		return name;
	}

	public String getRole() {
		return role;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPassword() {
		return password;
	}

	public long getContact() {
		return contact;
	}

	public String getSignUpDate() {
		return signUpDate;
	}

	public String getSignInDate() {
		return signInDate;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public void setContact(long contact) {
		this.contact = contact;
	}

	public void setSignUpDate(String signUpDate) {
		this.signUpDate = signUpDate;
	}
	
	public void setSignInDate(String signInDate) {
		this.signInDate = signInDate;
	}
	
}
