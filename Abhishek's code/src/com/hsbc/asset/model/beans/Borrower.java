package com.hsbc.asset.model.beans;


public class Borrower extends User {
	
	public Borrower() {}
	
	public int getUserId() {
		return super.getUserId();
	}
	
	public String getUserName() {
		return super.getUserName();
	}
	
	public String getName() {
		return super.getName();
	}

	public String getRole() {
		return super.getRole();
	}
	
	public String getEmail() {
		return super.getEmail();
	}
	
	public String getPassword() {
		return super.getPassword();
	}

	public long getContact() {
		return super.getContact();
	}

	public String getSignUpDate() {
		return super.getSignUpDate();
	}

	public String getSignInDate() {
		return super.getSignInDate();
	}
	
	public void setUserId(int userId) {
		super.setUserId(userId);
	}
	
	public void setName(String name) {
		super.setName(name);
	}
	
	public void setUserName(String userName) {
		super.setUserName(userName);
	}
	
	public void setRole(String role) {
		super.setRole(role);
	}
	
	public void setEmail(String email) {
		super.setEmail(email);
	}
	
	public void setPassword(String password) {
		super.setPassword(password);
	}

	public void setContact(long contact) {
		super.setContact(contact);
	}

	public void setSignUpDate(String signUpDate) {
		super.setSignUpDate(signUpDate);
	}
	
	public void setSignInDate(String signInDate) {
		super.setSignInDate(signInDate);
	}
}
