package com.hsbc.asset.model.service;

import java.util.List;

//ModelService Interface

import com.hsbc.asset.exception.DbDown;
import com.hsbc.asset.exception.EmployeeNotFound;
import com.hsbc.asset.exception.NoProductBorrowed;
import com.hsbc.asset.exception.OrderNotAllowed;
import com.hsbc.asset.model.bean.Employee;
import com.hsbc.asset.model.bean.Item;

public interface ModelService {

public Employee store(Employee emp) throws DbDown;

public Employee Login(Employee employeeLogin) throws EmployeeNotFound;

public List<String> category() throws DbDown;

public List<Item> getItem(String category) throws DbDown;

public void order(Item itemStore, Employee userSession) throws OrderNotAllowed;

public List<Item> returnItem(Employee userSession) throws NoProductBorrowed;

public void returnProduct(int orderId);

public void isBan(Employee employeeLoginSuccess);

}
