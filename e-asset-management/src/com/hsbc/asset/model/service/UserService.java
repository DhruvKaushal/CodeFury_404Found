package com.hsbc.asset.model.service;

import com.hsbc.asset.exception.CategoryException;
import com.hsbc.asset.model.beans.Asset;
import com.hsbc.asset.model.beans.AssetType;

public interface UserService {

	public Asset addAsset(Asset asset)  throws CategoryException;
	public AssetType addAssetType(AssetType assetType);
}
