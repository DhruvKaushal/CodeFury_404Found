package com.amp.asset.model.service;

import java.util.List;

import com.amp.asset.exception.CategoryException;
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
}
