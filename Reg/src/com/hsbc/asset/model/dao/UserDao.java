package com.hsbc.asset.model.dao;

import com.hsbc.asset.exception.ContactNoAlreadyExistsException;
import com.hsbc.asset.exception.EmailAlreadylExistsException;
import com.hsbc.asset.exception.UsernameAlreadyExistsException;
import com.hsbc.asset.model.beans.User;

public interface UserDao {
	public User store(User user) throws EmailAlreadylExistsException, UsernameAlreadyExistsException, ContactNoAlreadyExistsException;
}
