package com.hsbc.asset.model.beans;

public class Asset {
	private int assetId;
	private String name;
	private String type;
	private String desc;
	private int quantity;
	
	private int lendingPeriod;
	private int fine;
	private int banDays;

	public Asset() {}

	public Asset(int assetId, String name, String type, String desc, int quantity, int lendingPeriod, int fine,
			int banDays) {
		super();
		this.assetId = assetId;
		this.name = name;
		this.type = type;
		this.desc = desc;
		this.quantity = quantity;
		this.lendingPeriod = lendingPeriod;
		this.fine = fine;
		this.banDays = banDays;
	}

	public int getAssetId() {
		return assetId;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public String getDesc() {
		return desc;
	}

	public int getQuantity() {
		return quantity;
	}

	public int getLendingPeriod() {
		return lendingPeriod;
	}

	public int getFine() {
		return fine;
	}

	public int getBanDays() {
		return banDays;
	}

	public void setAssetId(int assetId) {
		this.assetId = assetId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setLendingPeriod(int lendingPeriod) {
		this.lendingPeriod = lendingPeriod;
	}

	public void setFine(int fine) {
		this.fine = fine;
	}

	public void setBanDays(int banDays) {
		this.banDays = banDays;
	}

	@Override
	public String toString() {
		return "Asset [assetId=" + assetId + ", name=" + name + ", type=" + type + ", desc=" + desc + ", quantity="
				+ quantity + ", lendingPeriod=" + lendingPeriod + ", fine=" + fine + ", banDays=" + banDays + "]";
	}

}
