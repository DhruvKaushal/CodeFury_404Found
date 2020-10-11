package com.amp.asset.model.business;

import java.util.List;

import com.amp.asset.model.beans.Asset;
import com.amp.asset.model.beans.Employee;
import com.amp.asset.model.dao.AssetDao;
import com.amp.asset.model.util.LayerType;
import com.amp.asset.model.util.UserFactory;
import com.amp.asset.exception.AuthenticationException;
import com.amp.asset.exception.ServerDownException;
import com.amp.asset.exception.DuplicateEmployeeException;
import com.amp.asset.exception.DuplicateOrderException;
import com.amp.asset.exception.EmployeeNotFoundException;
import com.amp.asset.exception.NoProductBorrowedException;
import com.amp.asset.exception.OrderNotAllowedException;

public class AssetServiceImpl implements AssetService {

	private AssetDao ampDao = null;
	
	public AssetServiceImpl() {
		ampDao = (AssetDao)UserFactory.getInstance(LayerType.DAO);
	}
	
	@Override
	public Employee createEmployee(Employee employee) throws DuplicateEmployeeException, ServerDownException {
		return ampDao.createEmployee(employee);
	}
	
	@Override
	public Employee employeeLogin(String username, String password) throws AuthenticationException, EmployeeNotFoundException, ServerDownException {
		Employee employee=null;
		try{
			employee = ampDao.employeeAuthentication(username, password);
		}
		catch(AuthenticationException a){
			throw new AuthenticationException();
		}
		catch(EmployeeNotFoundException b) {
			throw new EmployeeNotFoundException();
		}
		return employee;
	}
	
//	@Override
//	public List<Asset> fetchPopularAssets() {
//		return ampDao.fetchPopularAssets();
//	}

	@Override
	public List<String> fetchCategory() throws ServerDownException {
		return ampDao.fetchCategory();
	}

	@Override
	public List<Asset> fetchAllAssets(String assetType) throws ServerDownException {
		return ampDao.fetchAllAssets(assetType);
	}
	
	@Override
	public Asset order(Asset assetStore, Employee userSession) throws OrderNotAllowedException, DuplicateOrderException {
		return assetStore = ampDao.order(assetStore,userSession);
	}

	@Override
	public List<Asset> fetchAllBorrowed(int empId, int flag) throws NoProductBorrowedException{
		List<Asset> all = ampDao.fetchAllBorrowed(empId, flag);
		if(all.size() == 0 && flag == 0) {
			throw new NoProductBorrowedException("You have nothing to return.");
		}
		return all;
	}

	@Override
	public void returnProduct(int orderId) throws ServerDownException {
		ampDao.returnProduct(orderId);
	}

	@Override
	public void isBan(Employee employeeLoginSuccess) {
		ampDao.isBan(employeeLoginSuccess);
	}
	
}
