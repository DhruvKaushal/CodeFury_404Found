package com.hsbc.asset.model.beans;

import java.sql.Timestamp;

public class Transaction {

	private int orderId;
	private String userName;
	private int userId;
	private int assetId;
	private String itemType;
	private String assetName;
	private Timestamp dateIssue;
	private Timestamp tentativeReturn;
	private Timestamp actualReturn;
	
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getAssetId() {
		return assetId;
	}
	public void setAssetId(int assetId) {
		this.assetId = assetId;
	}
	public String getItemType() {
		return itemType;
	}
	public void setItemType(String itemType) {
		this.itemType = itemType;
	}
	public String getAssetName() {
		return assetName;
	}
	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}
	public Timestamp getDateIssue() {
		return dateIssue;
	}
	public void setDateIssue(Timestamp dateIssue) {
		this.dateIssue = dateIssue;
	}
	public Timestamp getTentativeReturn() {
		return tentativeReturn;
	}
	public void setTentativeReturn(Timestamp tentativeReturn) {
		this.tentativeReturn = tentativeReturn;
	}
	public Timestamp getActualReturn() {
		return actualReturn;
	}
	public void setActualReturn(Timestamp actualReturn) {
		this.actualReturn = actualReturn;
	}
	
	

	
}
