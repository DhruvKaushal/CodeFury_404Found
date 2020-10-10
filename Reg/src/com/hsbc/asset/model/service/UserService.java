package com.hsbc.asset.model.service;

import com.hsbc.asset.exception.AuthenticationException;
import com.hsbc.asset.exception.ContactNoAlreadyExistsException;
import com.hsbc.asset.exception.EmailAlreadylExistsException;
import com.hsbc.asset.exception.UsernameAlreadyExistsException;
import com.hsbc.asset.model.beans.User;

public interface UserService {
	public User createUser(User user) throws EmailAlreadylExistsException, UsernameAlreadyExistsException, ContactNoAlreadyExistsException;
	
	//login added
	public User login(String userCredential, String password, boolean isAdmin) throws AuthenticationException;
}
