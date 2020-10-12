package com.amp.asset.model.beans;

import java.sql.Timestamp;

public class Asset extends AssetType {

	private int assetId;
	private String assetName;
	private String assetDescription;
	private int quantity;
	private Timestamp dateIssued;
	private Timestamp tentativeReturn;
	private Timestamp actualReturn;
	public Timestamp getDateIssued() {
		return dateIssued;
	}

	public void setDateIssued(Timestamp dateIssued) {
		this.dateIssued = dateIssued;
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

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	private int orderId;
	
	public Asset(String typeName, double fine, int ban, int lendingPeriod) {
		super();
		// TODO Auto-generated constructor stub
	}
	 
	public Asset(String typeName, double fine, int ban, int lendingPeriod, int assetId, String assetName,
			String assetDescription, int quantity) {
		super();
		this.assetId = assetId;
		this.assetName = assetName;
		this.assetDescription = assetDescription;
		this.quantity = quantity;
	}

	public Asset() {
		super();
	}
	public int getAssetId() {
		return assetId;
	}
	public void setAssetId(int assetId) {
		this.assetId = assetId;
	}
	public String getAssetName() {
		return assetName;
	}
	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}
	public String getAssetDescription() {
		return assetDescription;
	}
	public void setAssetDescription(String assetDescription) {
		this.assetDescription = assetDescription;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
