package com.amp.asset.model.utility;

import com.amp.asset.model.beans.Admin;
import com.amp.asset.model.beans.Employee;
import com.amp.asset.model.dao.AssetDaoImpl;
import com.amp.asset.model.service.AssetServiceImpl;


// Factory Pattern is used to create object without exposing the creation logic to the client 
//  and refer to newly created object using a common interface.

public class FactoryPattern 
{
	
	public static Object getInstance(Type type)
	{
		Object obj=null;
		switch(type)
		{
		    case SERVICE:
		    	obj=new AssetServiceImpl();  // BUSINESS Layer Object
		    	break;
		    case DAO:
		    	obj=new AssetDaoImpl();     // DAO Layer Object
		    	break;
		    case ADMIN:
		    	obj=new Admin();           // ADMIN Class Object
		    	break;
		    case EMPLOYEE:
		    	obj=new Employee();       // Employee Class Object
		    	break;
		    	
		}
		return obj;
	}

}
