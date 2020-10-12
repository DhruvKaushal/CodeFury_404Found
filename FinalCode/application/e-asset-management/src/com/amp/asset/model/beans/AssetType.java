package com.amp.asset.model.beans;

public class AssetType {

	private String typeName;
	private int lendingPeriod;
	private double fine;
	private int ban;
	
		
	public AssetType() {
		super();
	}

	public AssetType(String typeName, int lendingPeriod, double fine, int ban) {
		super();
		this.typeName = typeName;
		this.lendingPeriod = lendingPeriod;
		this.fine = fine;
		this.ban = ban;
	}
	
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public int getLendingPeriod() {
		return lendingPeriod;
	}
	public void setLendingPeriod(int lendingPeriod) {
		this.lendingPeriod = lendingPeriod;
	}
	public double getFine() {
		return fine;
	}
	public void setFine(double fine) {
		this.fine = fine;
	}
	public int getBan() {
		return ban;
	}
	public void setBan(int ban) {
		this.ban = ban;
	}

}