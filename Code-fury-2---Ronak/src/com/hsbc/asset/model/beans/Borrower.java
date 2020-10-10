package com.hsbc.asset.model.beans;

public class Borrower
{
	private int borrower_id;
	private String borrower_name;
	private long borrower_contact;
	private String borrower_email;
	private String borrower_username;
	private String borrower_password;
	private String signup_date_time;
	private String login_date_time;
	
	
	public Borrower()
	{
		super();
	}
	
	public Borrower(String admin_username,String admin_password,String login_date_time)
	{
		this.borrower_username=admin_username;
		this.borrower_password=admin_password;
		this.login_date_time=login_date_time;
		
	}
	public Borrower(String borrower_name, long borrower_contact, String borrower_email,
			String borrower_username, String borrower_password, String signup_date_time)
	{
		
		this.borrower_name = borrower_name;
		this.borrower_contact = borrower_contact;
		this.borrower_email = borrower_email;
		this.borrower_username = borrower_username;
		this.borrower_password = borrower_password;
		this.signup_date_time = signup_date_time;
	}


	public int getBorrower_id() {
		return borrower_id;
	}
	public void setBorrower_id(int borrower_id) {
		this.borrower_id = borrower_id;
	}
	public String getBorrower_name() {
		return borrower_name;
	}
	public void setBorrower_name(String borrower_name) {
		this.borrower_name = borrower_name;
	}
	public long getBorrower_contact() {
		return borrower_contact;
	}
	public void setBorrower_contact(long borrower_contact) {
		this.borrower_contact = borrower_contact;
	}
	public String getBorrower_email() {
		return borrower_email;
	}
	public void setBorrower_email(String borrower_email) {
		this.borrower_email = borrower_email;
	}
	public String getBorrower_username() {
		return borrower_username;
	}
	public void setBorrower_username(String borrower_username) {
		this.borrower_username = borrower_username;
	}
	public String getBorrower_password() {
		return borrower_password;
	}
	public void setBorrower_password(String borrower_password) {
		this.borrower_password = borrower_password;
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

	@Override
	public String toString() {
		return "Borrower [borrower_id=" + borrower_id + ", borrower_name=" + borrower_name + ", borrower_contact="
				+ borrower_contact + ", borrower_email=" + borrower_email + ", borrower_username=" + borrower_username
				+ ", borrower_password=" + borrower_password + ", signup_date_time=" + signup_date_time
				+ ", login_date_time=" + login_date_time + "]";
	}
	
	

}
