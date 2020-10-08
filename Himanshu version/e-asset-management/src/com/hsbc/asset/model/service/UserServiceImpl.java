package com.hsbc.asset.model.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.hsbc.asset.exception.CategoryException;
import com.hsbc.asset.model.beans.Asset;
import com.hsbc.asset.model.beans.AssetType;
import com.hsbc.asset.model.beans.Transaction;
import com.hsbc.asset.model.dao.UserDao;
import com.hsbc.asset.model.utility.Type;
import com.hsbc.asset.model.utility.FactoryPattern;

public class UserServiceImpl implements  UserService {

	private UserDao userDao = null;
	public UserServiceImpl() {
		userDao = (UserDao)FactoryPattern.getInstance(Type.DAO);
	}
	@Override
	public Asset addAsset(Asset asset) throws CategoryException {
	
		return userDao.addAsset(asset);
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
		// TODO Auto-generated method stub
		return userDao.getName();
	}
	@Override
	public List<Transaction> getOrderHistory(String userName) {
		int id = userDao.getTransactionId(userName);
		
		List<Transaction> transaction = userDao.getTransactionDetails(id);
		List<Transaction> trans = new ArrayList<Transaction>();
		
		for(Transaction t : transaction) {
			if(t.getActualReturn().compareTo(t.getTentativeReturn())>0)
				trans.add(t);
		}
		return trans;
	}


}
