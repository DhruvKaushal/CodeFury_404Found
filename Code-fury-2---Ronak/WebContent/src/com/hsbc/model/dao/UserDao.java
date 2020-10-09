package com.hsbc.model.dao;

import java.util.List;

import com.hsbc.exception.DataBaseDown;
import com.hsbc.exception.UserNotFound;
import com.hsbc.model.beans.*;

public interface UserDao
{
	User authentication(User user) throws UserNotFound;
	User storeUser(User user) throws DataBaseDown;
	User getUserInfo(User user)throws DataBaseDown;
	List<String> getAllCategory() throws DataBaseDown;
	List<String> getAllAsset(String type) throws DataBaseDown;
}
