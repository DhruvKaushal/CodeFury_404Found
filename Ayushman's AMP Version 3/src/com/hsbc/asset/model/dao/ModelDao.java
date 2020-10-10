package com.hsbc.asset.model.dao;

import java.util.List;

import com.hsbc.asset.exception.DbDown;
import com.hsbc.asset.exception.EmployeeNotFound;
import com.hsbc.asset.model.bean.Employee;
import com.hsbc.asset.model.bean.Item;

public interface ModelDao {

public	Employee store(Employee emp) throws DbDown;

public Employee authenticate(Employee employeeLogin) throws EmployeeNotFound;

public List<String> getCategory();

public List<Item> getItem(String category);

public int order(Item itemStore, Employee userSession);

public List<Item> getAllLinked(Employee userSession);

public void returnProduct(int orderId);

public void isBan(Employee employeeLoginSuccess);

}
