package com.hsbc.asset.model.service;

import com.hsbc.asset.exception.CategoryException;
import com.hsbc.asset.model.beans.Asset;
import com.hsbc.asset.model.beans.AssetType;
import com.hsbc.asset.model.dao.UserDao;
import com.hsbc.asset.model.utility.Type;
import com.hsbc.asset.model.utility.UserFactory;

public class UserServiceImpl implements  UserService {

	private UserDao userDao = null;
	public UserServiceImpl() {
		userDao = (UserDao)UserFactory.getInstance(Type.DAO);
	}
	@Override
	public Asset addAsset(Asset asset) throws CategoryException {
	
		return userDao.addAsset(asset);
	}

	@Override
	public AssetType addAssetType(AssetType assetType) {
		
		return userDao.addAssetType(assetType);
	}


}
