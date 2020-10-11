package com.amp.asset.model.beans;

import java.sql.Timestamp;

public class Employee {
	private int employeeId;
	private String employeeName;
	private long employeeContact;
	private String employeeEmail;
	private String employeeUsername;
	private String employeePassword;
	private Timestamp signUpDate;
	private Timestamp signInDate;
	private String passwordSalt;
	
	
	public Employee() {
		super();
	}

	
	public Employee(String employeeName, long employeeContact, String employeeEmail,
			String employeeUsername, String employeePassword, Timestamp signUpDate,Timestamp signInDate, String passwordSalt )
	{
		this.employeeName = employeeName;
		this.employeeContact = employeeContact;
		this.employeeEmail = employeeEmail;
		this.employeeUsername = employeeUsername;
		this.employeePassword = employeePassword;
		this.signUpDate = signUpDate;
		this.signInDate =signInDate;
		this.passwordSalt= passwordSalt;
	}
	public Employee(String employeeName, long employeeContact, String employeeEmail,
			String employeeUsername, String employeePassword, Timestamp signUpDate,String passwordSalt )
	{
		this.employeeName = employeeName;
		this.employeeContact = employeeContact;
		this.employeeEmail = employeeEmail;
		this.employeeUsername = employeeUsername;
		this.employeePassword = employeePassword;
		this.signUpDate = signUpDate;
		this.passwordSalt= passwordSalt;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public long getEmployeeContact() {
		return employeeContact;
	}
	public void setEmployeeContact(long employeeContact) {
		this.employeeContact = employeeContact;
	}
	public String getEmployeeEmail() {
		return employeeEmail;
	}
	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}
	public String getEmployeeUsername() {
		return employeeUsername;
	}
	public void setEmployeeUsername(String employeeUsername) {
		this.employeeUsername = employeeUsername;
	}
	public String getEmployeePassword() {
		return employeePassword;
	}
	public void setEmployeePassword(String employeePassword) {
		this.employeePassword = employeePassword;
	}
	public Timestamp getSignUpDate() {
		return signUpDate;
	}
	public void setSignUpDate(Timestamp signUpDate) {
		this.signUpDate = signUpDate;
	}
	public Timestamp getSignInDate() {
		return signInDate;
	}
	public void setSignInDate(Timestamp signInDate) {
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
		return "Employee [employeeId=" + employeeId + ", employeeName=" + employeeName + ", employeeContact="
				+ employeeContact + ", employeeEmail=" + employeeEmail + ", employeeUsername=" + employeeUsername
				+ ", employeePassword=" + employeePassword + ", signUpDate=" + signUpDate + ", signInDate=" + signInDate
				+ ", passwordSalt=" + passwordSalt + "]";
	}
}
