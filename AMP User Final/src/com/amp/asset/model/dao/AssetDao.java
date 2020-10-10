package com.amp.asset.model.dao;

import java.util.List;

import com.amp.asset.model.beans.Asset;
import com.amp.asset.model.beans.Employee;
import com.amp.asset.exception.AuthenticationException;
import com.amp.asset.exception.EmployeeNotFoundException;
import com.amp.asset.exception.ServerDownException;
import com.amp.asset.exception.DuplicateEmployeeException;

public interface AssetDao {
	
	public Employee createEmployee(Employee employee) throws DuplicateEmployeeException, ServerDownException;
	public Employee employeeAuthentication(String username, String password) throws AuthenticationException, EmployeeNotFoundException, ServerDownException;
	// public List<Asset> fetchPopularAssets() throws DatabaseDownException;
	public List<String> fetchCategory() throws ServerDownException;
	public List<Asset> fetchAllAssets(String type) throws ServerDownException;
//	Admin adminAuthentication(Admin admin) throws AdminNotFound, ServerDown,InvalidCredentials;                // Admin login
	public List<Asset> getAllLinked(Employee userSession);
	public void returnProduct(int orderId);
	public Asset order(Asset assetStore, Employee userSession);
	public void isBan(Employee employeeLoginSuccess);
}
