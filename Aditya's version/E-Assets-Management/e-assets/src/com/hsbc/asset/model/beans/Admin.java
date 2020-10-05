package com.hsbc.asset.model.beans;

import java.sql.Timestamp;

public class Admin extends User {
	public Admin(int userId, String name, String userName, String password, String role, String email, long contact,
			Timestamp date) {
		super(userId, name, userName, password, role, email, contact, date);
		// TODO Auto-generated constructor stub
	}


}
