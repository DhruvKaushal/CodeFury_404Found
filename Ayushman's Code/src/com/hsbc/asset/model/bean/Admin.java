package com.hsbc.asset.model.bean;


//This is the Admin class who will look into the management system,add items and impose fine.
//@Author:Ayushman Mishra

import java.sql.Timestamp;

public class Admin {
private int user_id;
private String name;
private String userName;
private String password;
private long contact;
private String email;
private Timestamp date;

public Admin() {
	super();
}

public Admin(int user_id, String name, String userName, String password, long contact, String email,
		Timestamp date) {
	super();
	this.user_id = user_id;
	this.name = name;
	this.userName = userName;
	this.password = password;
	this.contact = contact;
	this.email = email;
	this.date = date;
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
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
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
public Timestamp getDate() {
	return date;
}
public void setDate(Timestamp date) {
	this.date = date;
}
@Override
public String toString() {
	return "User [user_id=" + user_id + ", name=" + name + ", userName=" + userName + ", password=" + password
			 + ", contact=" + contact + ", email=" + email + ", date=" + date + "]";
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}


}
