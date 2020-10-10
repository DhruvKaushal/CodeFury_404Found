package com.amp.asset.model.service;



import com.amp.asset.exception.AdminNotFound;
import com.amp.asset.exception.EmployeeFound;
import com.amp.asset.exception.EmployeeNotFound;
import com.amp.asset.exception.InvalidCredentials;
import com.amp.asset.exception.ServerDown;
import com.amp.asset.model.beans.Admin;
import com.amp.asset.model.beans.Employee;


// Business Interface

public interface AssetService
{
	Employee employeeLogin(Employee employee) throws EmployeeNotFound, ServerDown,InvalidCredentials;  // Employee login
	Employee registration(Employee employee) throws EmployeeFound,ServerDown;                         // Employee registration
	
	Admin adminLogin(Admin admin) throws AdminNotFound, ServerDown,InvalidCredentials;                // Admin login
	

}
