package com.amp.asset.model.beans;

//This is Admin Class which will define the Admin.
public class Admin
{
	private int adminId;
	private String adminName;
	private long adminContact;
	private String adminEmail;
	private String adminUsername;
	private String adminPassword;
	private String signUpDate;
	private String signInDate;
	private String passwordSalt;
	
	
	public Admin()
	{
		
	}
	public Admin(int adminId, String adminName, long adminContact, String adminEmail, String adminUsername,
			String adminPassword, String signUpDate, String signInDate, String passwordSalt) {
		super();
		this.adminId = adminId;
		this.adminName = adminName;
		this.adminContact = adminContact;
		this.adminEmail = adminEmail;
		this.adminUsername = adminUsername;
		this.adminPassword = adminPassword;
		this.signUpDate = signUpDate;
		this.signInDate = signInDate;
		this.passwordSalt = passwordSalt;
	}
	
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public long getAdminContact() {
		return adminContact;
	}
	public void setAdminContact(long adminContact) {
		this.adminContact = adminContact;
	}
	public String getAdminEmail() {
		return adminEmail;
	}
	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}
	public String getAdminUsername() {
		return adminUsername;
	}
	public void setAdminUsername(String adminUsername) {
		this.adminUsername = adminUsername;
	}
	public String getAdminPassword() {
		return adminPassword;
	}
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
	public String getSignUpDate() {
		return signUpDate;
	}
	public void setSignUpDate(String signUpDate) {
		this.signUpDate = signUpDate;
	}
	public String getSignInDate() {
		return signInDate;
	}
	public void setSignInDate(String signInDate) {
		this.signInDate = signInDate;
	}
	public String getPasswordSalt() {
		return passwordSalt;
	}
	public void setPasswordSalt(String passwordSalt) {
		this.passwordSalt = passwordSalt;
	}

	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", adminName=" + adminName + ", adminContact=" + adminContact
				+ ", adminEmail=" + adminEmail + ", adminUsername=" + adminUsername + ", adminPassword=" + adminPassword
				+ ", signUpDate=" + signUpDate + ", signInDate=" + signInDate + ", passwordSalt=" + passwordSalt + "]";
	}
	
	
	
	
	
}
