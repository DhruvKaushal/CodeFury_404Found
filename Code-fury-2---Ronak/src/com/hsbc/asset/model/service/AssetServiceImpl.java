package com.hsbc.asset.model.service;

import java.util.List;

import com.hsbc.asset.exception.AdminNotFound;
import com.hsbc.asset.exception.BorrowerFound;
import com.hsbc.asset.exception.BorrowerNotFound;
import com.hsbc.asset.exception.ServerDown;
import com.hsbc.asset.exception.WrongCredentials;
import com.hsbc.asset.model.beans.Admin;
import com.hsbc.asset.model.beans.Borrower;
import com.hsbc.asset.model.dao.AssetDao;
import com.hsbc.asset.model.utility.FactoryPattern;
import com.hsbc.asset.model.utility.Type;

public class AssetServiceImpl implements AssetService {

	AssetDao dao=(AssetDao)FactoryPattern.getInstance(Type.DAO);
	
	
	@Override
	public Object borrowerLogin(Object borrower) throws BorrowerNotFound, ServerDown,WrongCredentials
	{
		return dao.authenticationBorrower(borrower);
	}

	@Override
	public Object registration(Object borrower) throws ServerDown,BorrowerFound {
		return dao.store(borrower);
	}

	@Override
	public Object adminLogin(Object admin) throws AdminNotFound, ServerDown,WrongCredentials
	{
		return dao.authenticationAdmin(admin);
	}

	@Override
	public List<String> getAllCategory() throws ServerDown
	{
		return dao.getAllAssetType();
	}

	@Override
	public List<String> getAllAssets(String type) throws ServerDown
	{
		return dao.getAllAssets(type);
	}

	

}
