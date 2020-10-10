package com.hsbc.asset.model.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.hsbc.asset.exception.CategoryException;
import com.hsbc.asset.model.beans.Asset;
import com.hsbc.asset.model.beans.AssetType;
import com.hsbc.asset.model.beans.Transaction;
import com.hsbc.asset.model.beans.User;
import com.hsbc.asset.model.dao.UserDao;
import com.hsbc.asset.model.utility.FactoryPattern;
import com.hsbc.asset.model.utility.Type;

public class UserServiceImpl implements UserService {
	private UserDao dao = null;
	
	

	public UserServiceImpl() {
		dao = (UserDao)FactoryPattern.getInstance(Type.DAO);	
	}



	@Override              
	public Asset addAsset(Asset asset) throws CategoryException{
		return dao.storeAsset(asset);
	}



	@Override
	public AssetType addAssetType(AssetType assetType) {
		
		return dao.addAssetType(assetType);
	}



	@Override
	public List<String> fetchCategory() {
		List<String> categoryList = dao.getCategory();
		return categoryList;
	}
	

	@Override
	public List<String> fetchName() {
		List<String> nameList = dao.getName();
		return nameList;
	}


	@Override
	public List<Transaction> getOverdueOrders(String type, String name, String defaultDate) {
		
		String zeroDate =" 00:00:00"; 
		
		
		List<Transaction> orderList = new ArrayList<Transaction>();
		
		if(type == null && defaultDate == null) {
			orderList = dao.fetchOrdersByName(name);
		}
		
		else if((name == null) && (defaultDate == null)){
			orderList = dao.fetchOrdersByCategory(type);
			
		}
		else if(defaultDate == null){
			orderList = dao.fetchOrdersByCategoryAndName(type, name);
		}
		
		else if(defaultDate != null){
			defaultDate = defaultDate.concat(zeroDate);
			java.sql.Timestamp date =java.sql.Timestamp.valueOf(defaultDate);
			
			if (type == null && name == null) {
				orderList = dao.fetchOrdersByDate(date);
			}
		
			else if((type == null) && (name != null)) {
				orderList = dao.fetchOrdersByDateAndName(date, name);
			}
			else if((name == null)&&(type != null)) {
				orderList = dao.fetchOrdersByDateAnCategory(date, type);
			}
			else {
				orderList = dao.fetchOrdersByDateNameCategory(date,name,type);
			}
		}
		
		
		List<Transaction> finalList = new ArrayList<Transaction>();
		for(Transaction o: orderList) {
			
			if(o.getActualReturn().compareTo(o.getTentativeReturn())>0)
				finalList.add(o);	
		}		
				return finalList;
	}



	@Override
	public List<User> fetchAllUsers() {
		List userList = dao.fetchAll();
		return userList;
	}



	@Override
	public List<Transaction> fetchUserDetails(int userId) {
		List transList = dao.fetchDetails(userId);
		return transList;
	}
	
	
	
	
	
	

}
