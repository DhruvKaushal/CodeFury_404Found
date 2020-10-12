package com.amp.asset.model.service;

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
import com.amp.asset.model.beans.Message;
import com.amp.asset.model.beans.Transaction;
import com.amp.asset.model.beans.Employee;

public interface UserService {

	public Asset addAsset(Asset asset)  throws CategoryException;
	public AssetType addAssetType(AssetType assetType);
	public List<String> getCategoryList();
	
	public List<String> getNameList();
	public void sendMessage(Message message);
	
	public List<Message> recieveMessage(int userId);
	public void updateMessage(int messageId);
	public List<Integer> getHomeStats();
	
	public List<String> fetchCategory();
	public List<String> fetchName();
	public List<Employee> fetchAllUsers();
	public List<Transaction> getOverdueOrders(String type, String name, String date); 
	public List<Transaction> fetchUserDetails(int userId);
	public List<Employee> fetchBannedUsers();
	
	public Employee createUser(Employee emp) throws EmailAlreadyExistsException, UsernameAlreadyExistsException, ContactNoAlreadyExistsException;

	public Employee login(String userCredential, String password) throws AuthenticationException;
	public Admin loginAdmin(String userCredential, String password) throws AuthenticationException;
	
//  public List<Asset> fetchPopularAssets() throws DatabaseDownException;
//	public Admin adminLogin(Admin admin) throws AdminNotFound, ServerDown,InvalidCredentials; // Admin login
	public List<String> fetchCategoryEmployee() throws ServerDownException;
	public List<Asset> fetchAllAssets(String assetType) throws ServerDownException;
	public void order(Asset assetStore, Employee userSession) throws OrderNotAllowedException, DuplicateOrderException;
	public List<Asset> fetchAllBorrowed(int empId,int flag) throws NoProductBorrowedException;
	public void returnProduct(int orderId) throws ServerDownException;
	public void isBan(Employee employeeLoginSuccess);

}
