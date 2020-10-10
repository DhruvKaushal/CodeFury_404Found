package com.hsbc.asset.model.dao;

import java.sql.Timestamp;
import java.util.List;

import com.hsbc.asset.exception.CategoryException;
import com.hsbc.asset.model.beans.Asset;
import com.hsbc.asset.model.beans.AssetType;
import com.hsbc.asset.model.beans.Transaction;
import com.hsbc.asset.model.beans.User;

public interface UserDao {
	
	public Asset storeAsset(Asset asset) throws CategoryException;
	public AssetType addAssetType(AssetType assetType);
	public List<String> getCategory();
	public List<String> getName();
	public List<Transaction> fetchDetails(int userId);
	public List<User> fetchAll();
	
	
	public List<Transaction> fetchOrdersByCategory(String type);
	public List<Transaction> fetchOrdersByCategoryAndName(String type, String name);
	public List<Transaction> fetchOrdersByName(String name);
	
	public List<Transaction> fetchOrdersByDate(Timestamp date);


	public List<Transaction> fetchOrdersByDateAndName(Timestamp date, String name);
	public List<Transaction> fetchOrdersByDateAnCategory(Timestamp date, String type);

	public List<Transaction> fetchOrdersByDateNameCategory(Timestamp date, String name, String type);


}
