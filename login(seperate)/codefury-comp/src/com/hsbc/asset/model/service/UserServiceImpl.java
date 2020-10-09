package com.hsbc.asset.model.service;

import com.hsbc.asset.exception.AuthenticationException;
import com.hsbc.asset.model.beans.User;
import com.hsbc.asset.model.dao.UserDao;
import com.hsbc.asset.model.utilities.UserFactory;

public class UserServiceImpl implements UserService {
	private UserDao userDao = null;

	public UserServiceImpl() {
		userDao = (UserDao)UserFactory.getInstance("dao");
	}
	@Override
	public User login(String userCredential, String password,boolean isAdmin) throws AuthenticationException {
		
		
		//todo
		//whenver any person logs in check if its admin or borrower
		//then there should be 2 methods accordingly
		//authenticteAdmin
		//authenticteBorrower
		
		User user=userDao.authenticate(userCredential, password,isAdmin);
		return user;
	}

}
