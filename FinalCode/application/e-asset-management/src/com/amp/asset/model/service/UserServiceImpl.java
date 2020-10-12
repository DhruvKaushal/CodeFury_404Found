package com.amp.asset.model.service;

import java.util.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.amp.asset.exception.AuthenticationException;
import com.amp.asset.exception.CategoryException;
import com.amp.asset.exception.ContactNoAlreadyExistsException;
import com.amp.asset.exception.DuplicateEmployeeException;
import com.amp.asset.exception.DuplicateOrderException;
import com.amp.asset.exception.EmailAlreadyExistsException;
import com.amp.asset.exception.EmployeeNotFoundException;
import com.amp.asset.exception.NoProductBorrowedException;
import com.amp.asset.exception.OrderNotAllowedException;
import com.amp.asset.exception.ServerDownException;
import com.amp.asset.exception.UsernameAlreadyExistsException;
import com.amp.asset.model.beans.Admin;
import com.amp.asset.model.beans.Asset;
import com.amp.asset.model.beans.AssetType;
import com.amp.asset.model.beans.Employee;
import com.amp.asset.model.beans.Message;
import com.amp.asset.model.beans.Transaction;
import com.amp.asset.model.dao.UserDao;
import com.amp.asset.model.utility.FactoryPattern;
import com.amp.asset.model.utility.Type;


public class UserServiceImpl implements  UserService {

	private UserDao userDao = null;
	public UserServiceImpl() {
		userDao = (UserDao)FactoryPattern.getInstance(Type.DAO);
	}
	@Override
	public Asset addAsset(Asset asset) throws CategoryException {
	
		return userDao.storeAsset(asset);
	}

	@Override
	public AssetType addAssetType(AssetType assetType) {
		
		return userDao.addAssetType(assetType);
	}
	@Override
	public List<String> getCategoryList() {
		return userDao.getCategory();
	}
	@Override
	public List<String> getNameList() {

		return userDao.getName();
	} 

	@Override
	public void sendMessage(Message message) {
		 userDao.addMessage(message);
	}
	@Override
	public List<Message> recieveMessage(int userId) {
		return userDao.getMessage(userId);
	}
	@Override
	public void updateMessage(int messageId) {
		userDao.updateMessage(messageId);
		
	}
	@Override
	public List<Integer> getHomeStats() {
		
		List<Integer> listStats = new ArrayList<Integer>();
		
		listStats.add(userDao.noOfUsers());
		listStats.add(userDao.noOfIssue());
		listStats.add(userDao.noUsersBanned());
		listStats.add((int)userDao.getFine());
		listStats.add(userDao.assetDue());
		listStats.add(userDao.assetsToday());
		
		return listStats;
	}

	@Override
	public List<String> fetchCategory() {
		List<String> categoryList = userDao.getCategory();
		return categoryList;
	}
	

	@Override
	public List<String> fetchName() {
		List<String> nameList = userDao.getName();
		return nameList;
	}


	@Override
	public List<Transaction> getOverdueOrders(String type, String name, String defaultDate) {
		
		String zeroDate =" 00:00:00"; 
		
		
		List<Transaction> orderList = new ArrayList<Transaction>();
		
		if(type == null && defaultDate == null) {
			orderList = userDao.fetchOrdersByName(name);
		}
		
		else if((name == null) && (defaultDate == null)){
			orderList = userDao.fetchOrdersByCategory(type);
			
		}
		else if(defaultDate == null){
			orderList = userDao.fetchOrdersByCategoryAndName(type, name);
		}
		
		else if(defaultDate != null){
			defaultDate = defaultDate.concat(zeroDate);
			java.sql.Timestamp date =java.sql.Timestamp.valueOf(defaultDate);
			
			if (type == null && name == null) {
				orderList = userDao.fetchOrdersByDate(date);
			}
		
			else if((type == null) && (name != null)) {
				orderList = userDao.fetchOrdersByDateAndName(date, name);
			}
			else if((name == null)&&(type != null)) {
				orderList = userDao.fetchOrdersByDateAnCategory(date, type);
			}
			else {
				orderList = userDao.fetchOrdersByDateNameCategory(date,name,type);
			}
		}
		
		
		List<Transaction> finalList = new ArrayList<Transaction>();
		for(Transaction o: orderList) {

		      Timestamp t =  new Timestamp(new Date().getTime());
			if(t.compareTo(o.getTentativeReturn())>0)
				finalList.add(o);	
		}		
				return finalList;
	}



	@Override
	public List<Employee> fetchAllUsers() {
		List<Employee> empList = userDao.fetchAll();
		return empList;
	}



	@Override
	public List<Transaction> fetchUserDetails(int userId) {
		List<Transaction> transList = userDao.fetchDetails(userId);
		return transList;
	}



	@Override
	public List<Employee> fetchBannedUsers() {
		List<Employee> bannedList = userDao.fetchBanned();
		return bannedList;
	}
	
	
	@Override
	public Employee createUser(Employee emp) throws EmailAlreadyExistsException, UsernameAlreadyExistsException, ContactNoAlreadyExistsException {
		Employee newEmp=userDao.store(emp);
		return newEmp;
	}
	
	//login added
	@Override
	public Employee login(String userCredential, String password) throws AuthenticationException {
		
		
		//todo
		//whenver any person logs in check if its admin or borrower
		//then there should be 2 methods accordingly
		//authenticteAdmin
		//authenticteBorrower
		
		return userDao.authenticateEmployee(userCredential, password);
	}
	@Override
	public Admin loginAdmin(String userCredential, String password) throws AuthenticationException {
		return userDao.authenticateAdmin(userCredential, password);
	}


	


	@Override
	public List<String> fetchCategoryEmployee() throws ServerDownException {
		return userDao.fetchCategory();
	}

	@Override
	public List<Asset> fetchAllAssets(String assetType) throws ServerDownException {
		return userDao.fetchAllAssets(assetType);
	}
	
	@Override
	public Asset order(Asset assetStore, Employee userSession) throws OrderNotAllowedException, DuplicateOrderException {
		return userDao.order(assetStore,userSession);
	}

	@Override
	public List<Asset> fetchAllBorrowed(int empId, int flag) throws NoProductBorrowedException{
		List<Asset> all = userDao.fetchAllBorrowed(empId, flag);
		if(all.size() == 0 && flag == 0) {
			throw new NoProductBorrowedException("You have nothing to return.");
		}
		return all;
	}

	@Override
	public void returnProduct(int orderId) throws ServerDownException {
		userDao.returnProduct(orderId);
	}

	@Override
	public void isBan(Employee employeeLoginSuccess) {
		userDao.isBan(employeeLoginSuccess);
	}
}
