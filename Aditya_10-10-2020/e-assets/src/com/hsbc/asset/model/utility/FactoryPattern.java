package com.hsbc.asset.model.utility;


import com.hsbc.asset.model.dao.UserDaoImpl;

import com.hsbc.asset.model.service.UserServiceImpl;


public class FactoryPattern {
	
	public static Object getInstance(Type type) {
		
		Object obj = null;
		
		switch (type) {
		
			
		case DAO: 
			obj = new UserDaoImpl();
			break;
		
		case SERVICE:
			obj = new UserServiceImpl();
			break;	

		}
		return obj;
	}
	

}
