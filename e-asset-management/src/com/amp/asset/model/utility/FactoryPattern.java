package com.amp.asset.model.utility;

import com.amp.asset.model.dao.JdbcBackedUserDao;
import com.amp.asset.model.service.UserServiceImpl;
import com.amp.asset.model.utility.Type;

public class FactoryPattern {
	
	public static Object getInstance(Type type) {
		Object obj = null;
		switch(type) {
		case DAO : 			//case DAO for creating Dao object
			obj = new JdbcBackedUserDao();
			break;
		case SERVICE :
			obj = new UserServiceImpl();    //case for creating service layer
			break;
		}
		return obj;
	}
}
