package com.amp.asset.model.dao;

import java.sql.Timestamp;
import java.util.List;

import com.amp.asset.exception.CategoryException;
import com.amp.asset.model.beans.Asset;
import com.amp.asset.model.beans.AssetType;
import com.amp.asset.model.beans.Message;
import com.amp.asset.model.beans.Transaction;
import com.amp.asset.model.beans.Employee;

public interface UserDao {
	public  Asset storeAsset(Asset asset) throws CategoryException;
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
	
}
