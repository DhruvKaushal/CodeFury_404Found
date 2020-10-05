package com.hsbc.asset.model.dao;

import java.util.List;

import com.hsbc.asset.exception.AuthenticationException;
import com.hsbc.asset.exception.DuplicateUserException;
import com.hsbc.asset.model.beans.Asset;
import com.hsbc.asset.model.beans.User;

public interface UserDao {
	public User authenticate(String email, String password) throws AuthenticationException;
	public User createUser(User user) throws DuplicateUserException;
	//public List<Asset> fetchPopularAssets();
	public List<String> fetchCategory();
	public List<Asset> fetchAllAssets(String type);
	public String borrowAsset(int assetId);
	//public void deleteUser(int userId);
	//public User getUser(int userId);
}
