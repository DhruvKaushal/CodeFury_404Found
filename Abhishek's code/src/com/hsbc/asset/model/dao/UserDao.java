package com.hsbc.asset.model.dao;

import java.util.List;

import com.hsbc.asset.exception.AuthenticationException;
import com.hsbc.asset.exception.BorrowerNotFoundException;
import com.hsbc.asset.exception.DatabaseDownException;
import com.hsbc.asset.exception.DuplicateUserException;
import com.hsbc.asset.model.beans.Asset;
import com.hsbc.asset.model.beans.Borrower;

public interface UserDao {
	public Borrower createUser(Borrower borrower) throws DuplicateUserException, DatabaseDownException;
	public Borrower authenticate(String email, String password) throws AuthenticationException, BorrowerNotFoundException, DatabaseDownException;
	//public List<Asset> fetchPopularAssets() throws DatabaseDownException;
	public List<String> fetchCategory() throws DatabaseDownException;
	public List<Asset> fetchAllAssets(String type) throws DatabaseDownException;
	public String borrowAsset(int assetId) throws DatabaseDownException;
	public boolean banCheck(int userId) throws DatabaseDownException;
	public void deleteUser(int userId) throws DatabaseDownException;
	//public User getUser(int userId) throws DatabaseDownException;
}
