package com.hsbc.asset.model.utility;

import com.hsbc.asset.model.dao.ModelDaoImpl;
import com.hsbc.asset.model.service.ModelServiceImpl;

//Factory class to provide service layer and dao layer objects.

public class UserFactory {
	public static Object getInstance(String type) {
		if(type.equals("dao")) {
			return new ModelDaoImpl();
		}
		if(type.equals("service")) {
			return new ModelServiceImpl();
		}
		return null;
	}
}
