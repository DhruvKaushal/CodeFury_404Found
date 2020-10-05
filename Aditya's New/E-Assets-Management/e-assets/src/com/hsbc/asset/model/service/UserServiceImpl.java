package com.hsbc.asset.model.service;

import com.hsbc.asset.exception.CategoryException;
import com.hsbc.asset.model.beans.Asset;
import com.hsbc.asset.model.beans.AssetType;
import com.hsbc.asset.model.dao.UserDao;
import com.hsbc.asset.utility.FactoryPattern;
import com.hsbc.asset.utility.Type;

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
	
	

}
