package com.hsbc.asset.model.utility;

import com.hsbc.asset.model.dao.AssetDaoImpl;
import com.hsbc.asset.model.service.AssetServiceImpl;

public class FactoryPattern
{
	public static Object getInstance(Type type)
	{
		Object obj=null;
		switch(type)
		{
		    case SERVICE:
		    	obj=new AssetServiceImpl();
		    	break;
		    case DAO:
		    	obj=new AssetDaoImpl();
		    	break;
		}
		return obj;
	}
}
