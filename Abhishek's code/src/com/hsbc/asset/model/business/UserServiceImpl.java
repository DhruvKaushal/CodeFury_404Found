package com.hsbc.asset.model.business;

import java.util.List;

import com.hsbc.asset.exception.AuthenticationException;
import com.hsbc.asset.exception.BorrowerNotFoundException;
import com.hsbc.asset.exception.DatabaseDownException;
import com.hsbc.asset.exception.DuplicateUserException;
import com.hsbc.asset.exception.ItemUnavailableException;
import com.hsbc.asset.model.beans.Asset;
import com.hsbc.asset.model.beans.Borrower;
import com.hsbc.asset.model.dao.UserDao;
import com.hsbc.asset.model.util.LayerType;
import com.hsbc.asset.model.util.UserFactory;

public class UserServiceImpl implements UserService {

	private UserDao userDao = null;
	
	public UserServiceImpl() {
		userDao = (UserDao)UserFactory.getInstance(LayerType.DAO);
	}
	
	@Override
	public Borrower createUser(Borrower borrower) throws DuplicateUserException, DatabaseDownException {
		Borrower newUser = userDao.createUser(borrower);
		if(newUser == null) {
			throw new DuplicateUserException("User already registered.");
		}
		return newUser;
	}
	
	@Override
	public Borrower login(String email, String password) throws AuthenticationException, BorrowerNotFoundException, DatabaseDownException {
		Borrower borrower=null;
		try{
			borrower = userDao.authenticate(email, password);
		}
		catch(AuthenticationException a){
			throw new AuthenticationException();
		}
		catch(BorrowerNotFoundException b) {
			throw new BorrowerNotFoundException();
		}
		return borrower;
	}
	
//	@Override
//	public List<Asset> fetchPopularAssets() {
//		return userDao.fetchPopularAssets();
//	}

	@Override
	public List<String> fetchCategory() throws DatabaseDownException {
		return userDao.fetchCategory();
	}

	@Override
	public List<Asset> fetchAllAssets(String assetType) throws DatabaseDownException {
		return userDao.fetchAllAssets(assetType);
	}

	@Override
	public boolean banCheck(int userId) throws DatabaseDownException {
		return userDao.banCheck(userId);
	}
	
	@Override
	public String borrowAsset(int assetId) throws ItemUnavailableException, DatabaseDownException {
		String ans = userDao.borrowAsset(assetId);
		if(ans.equals("")) {
			throw new ItemUnavailableException("This asset is not available right now. Kindly try again later.");
		}
		return ans;
	}
	
	@Override
	public void deleteUser(int userId) throws DatabaseDownException {
		userDao.deleteUser(userId);
	}



}
