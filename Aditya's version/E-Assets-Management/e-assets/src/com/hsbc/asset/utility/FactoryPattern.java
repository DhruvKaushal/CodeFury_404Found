package com.hsbc.asset.utility;

import com.hsbc.asset.model.dao.AssetDaoImpl;
import com.hsbc.asset.model.dao.UserDaoImpl;
import com.hsbc.asset.model.service.AssetServiceImpl;
import com.hsbc.asset.model.service.UserServiceImpl;

public class FactoryPattern {
	
	public static Object getInstance(Type type) {
		
		Object obj = null;
		
		switch (type) {
		case USERDAO: 
			obj = new UserDaoImpl();
			
			break;
		
		case USERSERVICE:
			obj = new UserServiceImpl();
			break;
			
		case ASSETDAO: 
			obj = new AssetDaoImpl();
			
			break;
		
		case ASSETSERVICE:
			obj = new AssetServiceImpl();
			break;	

		}
		return obj;
	}
	

}
