package com.hsbc.asset.model.service;

import com.hsbc.asset.exception.AuthenticationException;
import com.hsbc.asset.exception.ContactNoAlreadyExistsException;
import com.hsbc.asset.exception.EmailAlreadylExistsException;
import com.hsbc.asset.exception.UsernameAlreadyExistsException;
import com.hsbc.asset.model.beans.User;
import com.hsbc.asset.model.dao.UserDao;
import com.hsbc.asset.model.util.UserFactory;

public class UserServiceImpl implements UserService {
	private UserDao userDao = null;
	
	public UserServiceImpl() {
		userDao = (UserDao) UserFactory.getInstance("dao");
	}
	@Override
	public User createUser(User user) throws EmailAlreadylExistsException, UsernameAlreadyExistsException, ContactNoAlreadyExistsException {
		User newUser=userDao.store(user);
		return newUser;
	}
	
	//login added
	@Override
	public User login(String userCredential, String password, boolean isAdmin) throws AuthenticationException {
		
		
		//todo
		//whenver any person logs in check if its admin or borrower
		//then there should be 2 methods accordingly
		//authenticteAdmin
		//authenticteBorrower
		
		User user=userDao.authenticate(userCredential, password, isAdmin);
		return user;
	}

}
