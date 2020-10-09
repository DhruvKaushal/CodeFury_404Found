package com.hsbc.asset.model.business;

import java.util.List;

import com.hsbc.asset.exception.AuthenticationException;
import com.hsbc.asset.exception.BorrowerNotFoundException;
import com.hsbc.asset.exception.DatabaseDownException;
import com.hsbc.asset.exception.DuplicateUserException;
import com.hsbc.asset.exception.ItemUnavailableException;
import com.hsbc.asset.model.beans.Asset;
import com.hsbc.asset.model.beans.Borrower;

public interface UserService {
	public Borrower createUser(Borrower user) throws DuplicateUserException, DatabaseDownException;
	public Borrower login(String email, String password) throws AuthenticationException, BorrowerNotFoundException, DatabaseDownException;
	//public List<Asset> fetchPopularAssets() throws DatabaseDownException;
	public List<String> fetchCategory() throws DatabaseDownException;
	public List<Asset> fetchAllAssets(String assetType) throws DatabaseDownException;
	public String borrowAsset(int assetId) throws ItemUnavailableException, DatabaseDownException;
	public boolean banCheck(int userId) throws DatabaseDownException;
	//public void borrowSuccess(int userId, ) throws DatabaseDownException;
	public void deleteUser(int userId) throws DatabaseDownException;
	//public User getUser(int userId) throws DatabaseDownException;
}

