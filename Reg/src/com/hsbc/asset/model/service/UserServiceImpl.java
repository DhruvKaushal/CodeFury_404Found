package com.hsbc.asset.model.service;

import com.hsbc.asset.model.beans.User;
import com.hsbc.asset.model.dao.UserDao;
import com.hsbc.asset.model.util.UserFactory;

public class UserServiceImpl implements UserService {
	private UserDao userDao = null;
	
	public UserServiceImpl() {
		userDao = (UserDao) UserFactory.getInstance("dao");
	}
	@Override
	public User createUser(User user) {
		User newUser=userDao.store(user);
		return newUser;
	}

}
