package com.hsbc.asset.model.dao;

import com.hsbc.asset.exception.CategoryException;
import com.hsbc.asset.model.beans.Asset;
import com.hsbc.asset.model.beans.AssetType;

public interface UserDao {
	public  Asset addAsset(Asset asset) throws CategoryException;
	public AssetType addAssetType(AssetType assetType);
}
