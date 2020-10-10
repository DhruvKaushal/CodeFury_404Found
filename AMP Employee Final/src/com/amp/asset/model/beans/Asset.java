package com.amp.asset.model.beans;

import java.sql.Timestamp;

//Product class for storing product related details.
public class Asset {
	private int assetId;
	private String assetName;
	private String assetType;
	private String assetDescription;
	private int quantity;
	private Timestamp dateIssued;
	private Timestamp dateReturn;
	private Timestamp actualReturn;
	private int transId;
	
	public int getAssetId() {
		return assetId;
	}
	public String getAssetName() {
		return assetName;
	}
	public String getAssetType() {
		return assetType;
	}
	public String getAssetDescription() {
		return assetDescription;
	}
	public int getQuantity() {
		return quantity;
	}
	public Timestamp getDateIssued() {
		return dateIssued;
	}
	public Timestamp getDateReturn() {
		return dateReturn;
	}
	public Timestamp getActualReturn() {
		return actualReturn;
	}
	public int getTransId() {
		return transId;
	}

	public void setAssetId(int assetId) {
		this.assetId = assetId;
	}
	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}
	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}
	public void setAssetDescription(String assetDescription) {
		this.assetDescription = assetDescription;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public void setDateIssued(Timestamp dateIssued) {
		this.dateIssued = dateIssued;
	}
	public void setDateReturn(Timestamp dateReturn) {
		this.dateReturn = dateReturn;
	}
	public void setActualReturn(Timestamp actualReturn) {
		this.actualReturn = actualReturn;
	}
	public void setTransId(int transId) {
		this.transId = transId;
	}

	@Override
	public String toString() {
		return "Asset [assetId=" + assetId + ", assetName=" + assetName + ", assetType=" + assetType
				+ ", assetDescription=" + assetDescription + ", quantity=" + quantity + ", dateIssued=" + dateIssued
				+ ", dateReturn=" + dateReturn + ", actualReturn=" + actualReturn + ", transId=" + transId
				+ "]";
	}
	
}
