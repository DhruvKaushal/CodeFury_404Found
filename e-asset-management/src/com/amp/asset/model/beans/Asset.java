package com.amp.asset.model.beans;

public class Asset extends AssetType {

	private int assetId;
	private String assetName;
	private String assetDescription;
	private int quantity;
	
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
