package com.hsbc.asset.model.service;

import java.util.List;

import com.hsbc.asset.exception.CategoryException;
import com.hsbc.asset.model.beans.Asset;
import com.hsbc.asset.model.beans.AssetType;
import com.hsbc.asset.model.beans.Transaction;
import com.hsbc.asset.model.beans.User;

public interface UserService {
	
	public Asset addAsset(Asset asset) throws CategoryException;
	public AssetType addAssetType(AssetType assetType);
	public List<String> fetchCategory();
	public List<String> fetchName();
	public List<User> fetchAllUsers();
	
	public List<Transaction> getOverdueOrders(String type, String name, String date); 
	public List<Transaction> fetchUserDetails(int userId);
	

}
