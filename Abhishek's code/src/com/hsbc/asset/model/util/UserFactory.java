package com.hsbc.asset.model.util;

import com.hsbc.asset.model.business.UserServiceImpl;
import com.hsbc.asset.model.dao.JdbcBackedUserDaoImpl;

public class UserFactory {

	public static Object getInstance(LayerType type) {
		Object obj = null;
		switch(type) {
			case SERVICE:
				obj = new UserServiceImpl();
				break;
			case DAO:
				obj = new JdbcBackedUserDaoImpl();
				break;
		}
		return obj;
	}
}
