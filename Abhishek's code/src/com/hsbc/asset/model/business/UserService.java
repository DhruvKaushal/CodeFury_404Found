package com.hsbc.asset.model.business;

import java.util.List;

import com.hsbc.asset.exception.AuthenticationException;
import com.hsbc.asset.exception.DuplicateUserException;
import com.hsbc.asset.exception.ItemUnavailableException;
import com.hsbc.asset.model.beans.Asset;
import com.hsbc.asset.model.beans.User;

public interface UserService {
	public User login(String email, String password) throws AuthenticationException;
	public User createUser(User user) throws DuplicateUserException;
	//public List<Asset> fetchPopularAssets();
	public List<String> fetchCategory();
	public List<Asset> fetchAllAssets(String assetType);
	public String borrowAsset(int assetId) throws ItemUnavailableException;
	//public void deleteUser(int userId);
	//public User getUser(int userId);
}

