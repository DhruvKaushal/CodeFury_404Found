package com.hsbc.asset.model.dao;

import com.hsbc.asset.exception.AuthenticationException;
import com.hsbc.asset.model.beans.User;

public interface UserDao {
	public User authenticate(String userCredential, String password,boolean isAdmin) throws AuthenticationException ;

}
