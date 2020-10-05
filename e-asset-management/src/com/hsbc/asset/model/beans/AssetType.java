package com.hsbc.asset.model.beans;

public class AssetType {

	private String assetType;
	private double fine;
	private int banDays;
	private int lendingPeriod;
	
	public AssetType(String assetType, double fine, int banDays, int lendingPeriod) {
		super();
		this.assetType = assetType;
		this.fine = fine;
		this.banDays = banDays;
		this.lendingPeriod = lendingPeriod;
	}
	
	public String getAssetType() {
		return assetType;
	}
	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}
	public double getFine() {
		return fine;
	}
	public void setFine(double fine) {
		this.fine = fine;
	}
	
	public int getBanDays() {
		return banDays;
	}
	public void setBanDays(int banDays) {
		this.banDays = banDays;
	}
	public int getLendingPeriod() {
		return lendingPeriod;
	}
	public void setLendingPeriod(int lendingPeriod) {
		this.lendingPeriod = lendingPeriod;
	}
	
	
}
