package com.hsbc.asset.model.beans;

public class Admin
{
	private int admin_id;
	private String admin_name;
	private long admin_contact;
	private String admin_email;
	private String admin_username;
	private String admin_password;
	private String signup_date_time;
	private String login_date_time;
	
	
	public Admin() {
		super();
	}
	public Admin(String admin_username,String admin_password,String login_date_time)
	{
		this.admin_username=admin_username;
		this.admin_password=admin_password;
		this.login_date_time=login_date_time;
		
	}
	public Admin(int admin_id, String admin_name, long admin_contact, String admin_email, String admin_username,
			String admin_password, String signup_date_time, String login_date_time)
	{
		super();
		this.admin_id = admin_id;
		this.admin_name = admin_name;
		this.admin_contact = admin_contact;
		this.admin_email = admin_email;
		this.admin_username = admin_username;
		this.admin_password = admin_password;
		this.signup_date_time = signup_date_time;
		this.login_date_time = login_date_time;
	}

	public int getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}
	public String getAdmin_name() {
		return admin_name;
	}
	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}
	public long getAdmin_contact() {
		return admin_contact;
	}
	public void setAdmin_contact(long admin_contact) {
		this.admin_contact = admin_contact;
	}
	public String getAdmin_email() {
		return admin_email;
	}
	public void setAdmin_email(String admin_email) {
		this.admin_email = admin_email;
	}
	public String getAdmin_username() {
		return admin_username;
	}
	public void setAdmin_username(String admin_username) {
		this.admin_username = admin_username;
	}
	public String getAdmin_password() {
		return admin_password;
	}
	public void setAdmin_password(String admin_password) {
		this.admin_password = admin_password;
	}
	public String getSignup_date_time() {
		return signup_date_time;
	}
	public void setSignup_date_time(String signup_date_time) {
		this.signup_date_time = signup_date_time;
	}
	public String getLogin_date_time() {
		return login_date_time;
	}
	public void setLogin_date_time(String login_date_time) {
		this.login_date_time = login_date_time;
	}
	
	
	
}
