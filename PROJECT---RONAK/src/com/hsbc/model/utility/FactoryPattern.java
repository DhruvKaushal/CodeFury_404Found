package com.hsbc.model.utility;

import com.hsbc.model.dao.UserDaoImpl;
import com.hsbc.model.service.UserServiceImpl;

public class FactoryPattern
{
	public static Object getInstance(Type type)
	{
		Object obj=null;
		switch(type)
		{
		    case SERVICE:
		    	obj=new UserServiceImpl();
		    	break;
		    case DAO:
		    	obj=new UserDaoImpl();
		    	break;
		}
		return obj;
	}
}
