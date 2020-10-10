package com.hsbc.asset.model.dao;

import java.util.List;

import com.hsbc.asset.exception.CategoryException;
import com.hsbc.asset.model.beans.Asset;
import com.hsbc.asset.model.beans.AssetType;
import com.hsbc.asset.model.beans.Message;
import com.hsbc.asset.model.beans.Transaction;

public interface UserDao {
	public  Asset addAsset(Asset asset) throws CategoryException;
	public AssetType addAssetType(AssetType assetType);
	public List<String> getCategory();
	public List<String> getName();
	public int getTransactionId(String name);
	public List<Transaction> getTransactionDetails(int id);
	public void addMessage(Message message);
	public List<Message> getMessage(int userId);
	public void updateMessage(int messageId);
}
