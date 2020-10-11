package com.amp.asset.model.beans;

// This is Employee Class which will define the Employee.
public class Employee
{
	private int employeeId;
	private String employeeName;
	private long employeeContact;
	private String employeeEmail;
	private String employeeUsername;
	private String role = "Employee";
	private String employeePassword;
	private String signUpDate;
	private String signInDate;
	private String passwordSalt;
	
	public Employee() { }
	
	public Employee(String employeeName, long employeeContact, String employeeEmail,
			String employeeUsername, String employeePassword, String signUpDate )
	{
		this.employeeName = employeeName;
		this.employeeContact = employeeContact;
		this.employeeEmail = employeeEmail;
		this.employeeUsername = employeeUsername;
		this.employeePassword = employeePassword;
		this.signUpDate = signUpDate;
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
	public String getRole() {
		return role;
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
		return "Employee [employeeId=" + employeeId + ", employeeName=" + employeeName + ", employeeContact="
				+ employeeContact + ", employeeEmail=" + employeeEmail + ", employeeUsername=" + employeeUsername
				+ ", employeePassword=" + employeePassword + ", signUpDate=" + signUpDate + ", signInDate=" + signInDate
				+ ", passwordSalt=" + passwordSalt + "]";
	}
		
}
