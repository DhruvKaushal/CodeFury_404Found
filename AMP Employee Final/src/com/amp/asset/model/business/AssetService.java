package com.amp.asset.model.business;

import java.util.List;

import com.amp.asset.model.beans.Asset;
import com.amp.asset.model.beans.Employee;
import com.amp.asset.exception.AuthenticationException;
import com.amp.asset.exception.ServerDownException;
import com.amp.asset.exception.DuplicateEmployeeException;
import com.amp.asset.exception.DuplicateOrderException;
import com.amp.asset.exception.EmployeeNotFoundException;
import com.amp.asset.exception.NoProductBorrowedException;
import com.amp.asset.exception.OrderNotAllowedException;

public interface AssetService {
	public Employee createEmployee(Employee employee) throws DuplicateEmployeeException, ServerDownException;
	public Employee employeeLogin(String email, String password) throws AuthenticationException, EmployeeNotFoundException, ServerDownException;
//  public List<Asset> fetchPopularAssets() throws DatabaseDownException;
//	public Admin adminLogin(Admin admin) throws AdminNotFound, ServerDown,InvalidCredentials; // Admin login
	public List<String> fetchCategory() throws ServerDownException;
	public List<Asset> fetchAllAssets(String assetType) throws ServerDownException;
	public Asset order(Asset assetStore, Employee userSession) throws OrderNotAllowedException, DuplicateOrderException;
	public List<Asset> fetchAllBorrowed(int empId,int flag) throws NoProductBorrowedException;
	public void returnProduct(int orderId) throws ServerDownException;
	public void isBan(Employee employeeLoginSuccess);
}

