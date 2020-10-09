package com.hsbc.asset.model.service;

import java.util.List;

import com.hsbc.asset.exception.AdminNotFound;
import com.hsbc.asset.exception.BorrowerFound;
import com.hsbc.asset.exception.BorrowerNotFound;
import com.hsbc.asset.exception.ServerDown;
import com.hsbc.asset.exception.WrongCredentials;
import com.hsbc.asset.model.beans.Admin;
import com.hsbc.asset.model.beans.Borrower;

public interface AssetService
{
	Object borrowerLogin(Object borrower) throws BorrowerNotFound, ServerDown,WrongCredentials; // Borrower login
	Object registration(Object borrower) throws BorrowerFound,ServerDown;                      // Borrower registration
	
	Object adminLogin(Object admin) throws AdminNotFound, ServerDown,WrongCredentials;         // Admin login
	
	List<String> getAllCategory() throws ServerDown;
	List<String> getAllAssets(String type) throws ServerDown;
	
}
