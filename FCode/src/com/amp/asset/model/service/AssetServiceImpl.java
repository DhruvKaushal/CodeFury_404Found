package com.amp.asset.model.service;

import com.amp.asset.exception.AdminNotFound;
import com.amp.asset.exception.EmployeeFound;
import com.amp.asset.exception.EmployeeNotFound;
import com.amp.asset.exception.InvalidCredentials;
import com.amp.asset.exception.ServerDown;
import com.amp.asset.model.beans.Admin;
import com.amp.asset.model.beans.Employee;
import com.amp.asset.model.dao.AssetDao;
import com.amp.asset.model.utility.FactoryPattern;
import com.amp.asset.model.utility.Type;

// Business Logic is implemented in the Service Class

public class AssetServiceImpl implements AssetService
{

	AssetDao dao=(AssetDao)FactoryPattern.getInstance(Type.DAO);
	
	@Override
    // Employee Login
	public Employee employeeLogin(Employee employee) throws EmployeeNotFound, ServerDown, InvalidCredentials 
	{
		return dao.employeeAuthentication(employee);
	}

	
	@Override 
	//Employee Registration
	public Employee registration(Employee employee) throws EmployeeFound, ServerDown   
	{
		return dao.storeDetails(employee);
	}

	@Override
	// Admin Login
	public Admin adminLogin(Admin admin) throws AdminNotFound, ServerDown, InvalidCredentials
	{
		return dao.adminAuthentication(admin);
	}

}
