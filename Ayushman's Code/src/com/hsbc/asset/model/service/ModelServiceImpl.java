package com.hsbc.asset.model.service;

import java.util.List;

import com.hsbc.asset.exception.DbDown;
import com.hsbc.asset.exception.EmployeeNotFound;
import com.hsbc.asset.exception.NoProductBorrowed;
import com.hsbc.asset.exception.OrderNotAllowed;
import com.hsbc.asset.model.bean.Employee;
import com.hsbc.asset.model.bean.Item;
import com.hsbc.asset.model.dao.ModelDao;
import com.hsbc.asset.model.utility.UserFactory;


//Service Layer Implimentation

public class ModelServiceImpl implements ModelService {


	ModelDao dao;
	public ModelServiceImpl() {
		super();
		dao=(ModelDao)UserFactory.getInstance("dao");
	}
	
	@Override
	public Employee store(Employee emp) throws DbDown {
		
		try {
		Employee stored=dao.store(emp);
		return stored;
		}catch(DbDown e) {
			throw new DbDown("Something went wrong.Please try again later.");
		}
		
	}

	@Override
	public Employee Login(Employee employeeLogin) throws EmployeeNotFound {
		Employee employeeSuccess=null;
		employeeSuccess=dao.authenticate(employeeLogin);
		if(employeeSuccess==null) {
			throw new EmployeeNotFound("Sorry something went wrong please try again later.");
		}
		return employeeSuccess;
	}

	@Override
	public List<String> category() throws DbDown {
		
		List<String> allCategory=dao.getCategory();
		if(allCategory==null) {
			throw new DbDown("Sorry something went wrong please try again later.");
		}
		return allCategory;
		
	}

	@Override
	public List<Item> getItem(String category) throws DbDown {
		List<Item> allItem=dao.getItem(category);
		if(allItem==null) {
			throw new DbDown("Sorry the choosen category doesnot exist");
		}
		return allItem;
	}

	@Override
	public void order(Item itemStore, Employee userSession) throws OrderNotAllowed {
		int flag=dao.order(itemStore,userSession);
		if(flag==0) {
			throw new OrderNotAllowed("Sorry you are not allowed to place order.Please contact admin.");
		}
		
		
	}

	@Override
	public List<Item> returnItem(Employee userSession) throws NoProductBorrowed{
		List<Item> all=dao.getAllLinked(userSession);
		if(all.size()==0) {
			throw new NoProductBorrowed("You have nothing to return");
		}
		return all;
	}

	@Override
	public void returnProduct(int orderId) {
		
		dao.returnProduct( orderId);
		
	}

	@Override
	public void isBan(Employee employeeLoginSuccess) {
		dao.isBan(employeeLoginSuccess);
		
	}


}
