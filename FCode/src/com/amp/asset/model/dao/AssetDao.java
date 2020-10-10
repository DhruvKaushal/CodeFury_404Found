package com.amp.asset.model.dao;

import com.amp.asset.exception.AdminNotFound;
import com.amp.asset.exception.EmployeeFound;
import com.amp.asset.exception.EmployeeNotFound;
import com.amp.asset.exception.InvalidCredentials;
import com.amp.asset.exception.ServerDown;
import com.amp.asset.model.beans.Admin;
import com.amp.asset.model.beans.Employee;

// DAO--->Data Access Object Interface

public interface AssetDao
{
	Employee employeeAuthentication(Employee employee) throws EmployeeNotFound, ServerDown,InvalidCredentials;  // Employee login
	Employee storeDetails(Employee employee) throws EmployeeFound,ServerDown;                                  // Employee registration
	
	Admin adminAuthentication(Admin admin) throws AdminNotFound, ServerDown,InvalidCredentials;                // Admin login
}
