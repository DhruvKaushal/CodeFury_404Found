package com.hsbc.model.service;

import java.util.List;

import com.hsbc.exception.DataBaseDown;
import com.hsbc.exception.UserNotFound;
import com.hsbc.model.beans.*;

public interface UserService {
	User login(User user) throws UserNotFound;   //  Login
	User registration (User user) throws DataBaseDown ; // Registration
	User getUserInfo(User user) throws DataBaseDown; // User information;
	List<String> getAllCategory() throws DataBaseDown; // This is for to select any category to placed an order.
	List<String> getAllAsset(String type) throws DataBaseDown; // This is to select asset under a particular category;
}
