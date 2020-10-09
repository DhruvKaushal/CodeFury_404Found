package com.hsbc.model.service;

import java.util.List;

import com.hsbc.exception.DataBaseDown;
import com.hsbc.exception.UserNotFound;
import com.hsbc.model.beans.*;
import com.hsbc.model.dao.UserDao;
import com.hsbc.model.utility.*;

public class UserServiceImpl implements UserService {

	
    UserDao dao=(UserDao)FactoryPattern.getInstance(Type.DAO);  // Factory Pattern

	
	@Override
	// Login Window
	public User login(User user) throws UserNotFound
	{
		User user1= dao.authentication(user);
		if(user1==null)
		{
			throw new UserNotFound("User is not Found");
		}
		return user1;
	}

	@Override
	//Registration window
	public User registration(User user) throws DataBaseDown
	{
		return dao.storeUser(user);
	}
	
	
	public User getUserInfo(User user) throws DataBaseDown
	{
		return dao.getUserInfo(user);
	}
	public List<String> getAllCategory()  throws DataBaseDown
	{
		return dao.getAllCategory();
	}
	public List<String> getAllAsset(String type) throws DataBaseDown
	{
		return dao.getAllAsset(type);
	}
}
