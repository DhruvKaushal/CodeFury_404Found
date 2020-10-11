package com.amp.asset.model.dao;

import java.util.List;

import com.amp.asset.model.beans.Asset;
import com.amp.asset.model.beans.Employee;
import com.amp.asset.exception.AuthenticationException;
import com.amp.asset.exception.EmployeeNotFoundException;
import com.amp.asset.exception.NoProductBorrowedException;
import com.amp.asset.exception.ServerDownException;
import com.amp.asset.exception.DuplicateEmployeeException;
import com.amp.asset.exception.DuplicateOrderException;

public interface AssetDao {
	
	public Employee createEmployee(Employee employee) throws DuplicateEmployeeException, ServerDownException;
	public Employee employeeAuthentication(String username, String password) throws AuthenticationException, EmployeeNotFoundException, ServerDownException;
	// public List<Asset> fetchPopularAssets() throws DatabaseDownException;
	public List<String> fetchCategory() throws ServerDownException;
	public List<Asset> fetchAllAssets(String type) throws ServerDownException;
//	Admin adminAuthentication(Admin admin) throws AdminNotFound, ServerDown,InvalidCredentials;                // Admin login
	public List<Asset> fetchAllBorrowed(int empId, int flag) throws NoProductBorrowedException;
	public void returnProduct(int orderId) throws ServerDownException;
	public Asset order(Asset assetStore, Employee userSession) throws DuplicateOrderException;
	public void isBan(Employee employeeLoginSuccess);
}
