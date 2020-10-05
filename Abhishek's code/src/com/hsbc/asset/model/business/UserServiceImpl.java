package com.hsbc.asset.model.business;

import java.util.List;

import com.hsbc.asset.exception.AuthenticationException;
import com.hsbc.asset.exception.DuplicateUserException;
import com.hsbc.asset.exception.ItemUnavailableException;
import com.hsbc.asset.model.beans.Asset;
import com.hsbc.asset.model.beans.User;
import com.hsbc.asset.model.dao.UserDao;
import com.hsbc.asset.model.util.LayerType;
import com.hsbc.asset.model.util.UserFactory;

public class UserServiceImpl implements UserService {

	private UserDao userDao = null;
	
	public UserServiceImpl() {
		userDao = (UserDao)UserFactory.getInstance(LayerType.DAO);
	}
	
	@Override
	public User login(String email, String password) throws AuthenticationException {
		User user = userDao.authenticate(email, password);
		if(user == null) {
			throw new AuthenticationException("Sorry something went wrong");
		}
		return user;
	}

	@Override
	public User createUser(User borrower) throws DuplicateUserException {
		User newUser = userDao.createUser(borrower);
		if(borrower == null) {
			throw new DuplicateUserException("User already registered.");
		}
		return newUser;
	}
	
//	@Override
//	public List<Asset> fetchPopularAssets() {
//		return userDao.fetchPopularAssets();
//	}

	@Override
	public List<String> fetchCategory() {
		return userDao.fetchCategory();
	}

	@Override
	public List<Asset> fetchAllAssets(String assetType) {
		return userDao.fetchAllAssets(assetType);
	}

	@Override
	public String borrowAsset(int assetId) throws ItemUnavailableException {
		String ans = userDao.borrowAsset(assetId);
		if(ans.equals("")) {
			throw new ItemUnavailableException("This asset is not available right now. Kindly try again later.");
		}
		return ans;
	}

}
