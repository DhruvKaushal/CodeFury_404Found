package com.hsbc.asset.model.dao;

import java.util.List;

import com.hsbc.asset.exception.AdminNotFound;
import com.hsbc.asset.exception.BorrowerFound;
import com.hsbc.asset.exception.BorrowerNotFound;
import com.hsbc.asset.exception.ServerDown;
import com.hsbc.asset.exception.WrongCredentials;
import com.hsbc.asset.model.beans.Admin;
import com.hsbc.asset.model.beans.Borrower;

public interface AssetDao
{
	Object authenticationBorrower(Object borrower) throws BorrowerNotFound, ServerDown,WrongCredentials; // Borrower authentication
	Object store(Object borrower) throws BorrowerFound, ServerDown;                                    // Borrower registration
	
	Object authenticationAdmin(Object admin) throws AdminNotFound, ServerDown,WrongCredentials;             ///Admin authentication
	List<String> getAllAssetType() throws ServerDown;
	List<String> getAllAssets(String type) throws ServerDown;
}
