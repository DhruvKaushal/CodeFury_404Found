package com.hsbc.asset.model.utility;

import com.hsbc.asset.model.dao.JdbcBackedUserDao;
import com.hsbc.asset.model.service.UserServiceImpl;
import com.hsbc.asset.model.utility.Type;

public class FactoryPattern {
	
	public static Object getInstance(Type type) {
		Object obj = null;
		switch(type) {
		case DAO : 
			obj = new JdbcBackedUserDao();
			break;
		case SERVICE :
			obj = new UserServiceImpl();
			break;
		}
		return obj;
	}
}
