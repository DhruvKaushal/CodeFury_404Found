package com.amp.asset.model.beans;

import java.sql.Timestamp;

public class Transaction {

	private int empId;
	private String userName;
	private int transId;
	private int assetId;
	private String assetName;
	private String assetType;
	private Timestamp dateIssued;
	private Timestamp tentativeReturn;
	private Timestamp actualReturn;
	private float fine;
	private boolean isReturn;
	
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public boolean getIsReturn() {
		return isReturn;
	}
	public void setReturn(boolean isReturn) {
		this.isReturn = isReturn;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getTransId() {
		return transId;
	}
	public void setTransId(int transId) {
		this.transId = transId;
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
	public String getAssetType() {
		return assetType;
	}
	public void setAssetType(String assetType) {
		this.assetType = assetType;
	}
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
	public float getFine() {
		return fine;
	}
	public void setFine(float fine) {
		this.fine = fine;
	}

}
