package com.hsbc.asset.model.service;

import com.hsbc.asset.exception.AuthenticationException;
import com.hsbc.asset.model.beans.User;

public interface UserService {
	public User login(String userCredential, String password,boolean isAdmin) throws AuthenticationException;
}
