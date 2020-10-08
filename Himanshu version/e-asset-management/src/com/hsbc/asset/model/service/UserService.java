package com.hsbc.asset.model.service;

import java.util.List;

import com.hsbc.asset.exception.CategoryException;
import com.hsbc.asset.model.beans.Asset;
import com.hsbc.asset.model.beans.AssetType;
import com.hsbc.asset.model.beans.Transaction;

public interface UserService {

	public Asset addAsset(Asset asset)  throws CategoryException;
	public AssetType addAssetType(AssetType assetType);
	public List<String> getCategoryList();
	public List<String> getNameList();
	public List<Transaction> getOrderHistory(String userName);
}
