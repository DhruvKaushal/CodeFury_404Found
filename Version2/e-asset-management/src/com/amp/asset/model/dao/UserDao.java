package com.amp.asset.model.dao;

import java.sql.Timestamp;
import java.util.List;

import com.amp.asset.exception.AuthenticationException;
import com.amp.asset.exception.CategoryException;
import com.amp.asset.exception.ContactNoAlreadyExistsException;
import com.amp.asset.exception.DuplicateOrderException;
import com.amp.asset.exception.EmailAlreadyExistsException;
import com.amp.asset.exception.NoProductBorrowedException;
import com.amp.asset.exception.ServerDownException;
import com.amp.asset.exception.UsernameAlreadyExistsException;
import com.amp.asset.model.beans.Admin;
import com.amp.asset.model.beans.Asset;
import com.amp.asset.model.beans.AssetType;
import com.amp.asset.model.beans.Message;
import com.amp.asset.model.beans.Transaction;
import com.amp.asset.model.beans.Employee;

public interface UserDao {
	public  Asset storeAsset(Asset asset) throws CategoryException;
	public Admin authenticateAdmin(String userCredential, String password) throws AuthenticationException ;
	public Employee authenticateEmployee(String userCredential, String password) throws AuthenticationException ;

	public Employee store(Employee user) throws EmailAlreadyExistsException, UsernameAlreadyExistsException, ContactNoAlreadyExistsException;
	public AssetType addAssetType(AssetType assetType);
	public List<String> getCategory();
	public List<String> getName();
	
	public void addMessage(Message message);
	public List<Message> getMessage(int userId);
	public void updateMessage(int messageId);
	
	public List<Transaction> fetchDetails(int userId);
	public List<Employee> fetchAll();
	public List<Employee> fetchBanned();
	
	public int noOfUsers();
	public int noOfIssue();
	public int noUsersBanned();
	public double getFine();
	public int assetDue();
	public int assetsToday();
	
	
	public List<Transaction> fetchOrdersByCategory(String type);
	public List<Transaction> fetchOrdersByCategoryAndName(String type, String name);
	public List<Transaction> fetchOrdersByName(String name);
	
	public List<Transaction> fetchOrdersByDate(Timestamp date);


	public List<Transaction> fetchOrdersByDateAndName(Timestamp date, String name);
	public List<Transaction> fetchOrdersByDateAnCategory(Timestamp date, String type);

	public List<Transaction> fetchOrdersByDateNameCategory(Timestamp date, String name, String type);
	
	public List<String> fetchCategory() throws ServerDownException;
	public List<Asset> fetchAllAssets(String type) throws ServerDownException;
//	Admin adminAuthentication(Admin admin) throws AdminNotFound, ServerDown,InvalidCredentials;                // Admin login
	public List<Asset> fetchAllBorrowed(int empId, int flag) throws NoProductBorrowedException;
	public void returnProduct(int orderId) throws ServerDownException;
	public void order(Asset assetStore, Employee userSession) throws DuplicateOrderException;
	public void isBan(Employee employeeLoginSuccess);
	
}
