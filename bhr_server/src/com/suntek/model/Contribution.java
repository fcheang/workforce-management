package com.suntek.model;

import java.util.Date;

public class Contribution {

	private String userId;
	private Date date;
	private double hrsWorked;
	private String authsEntered;
	private String interpretersOrdered;
	private String collateralReceived;
	private String other;
	
	public Contribution(){		
	}
	
	public Contribution(User user, Date date2) {
		this.userId = user.getUsername();
		this.date = date2;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public double getHrsWorked() {
		return hrsWorked;
	}
	public void setHrsWorked(double hrsWorked) {
		this.hrsWorked = hrsWorked;
	}
	public String getAuthsEntered() {
		return authsEntered;
	}
	public void setAuthsEntered(String authsEntered) {
		this.authsEntered = authsEntered;
	}
	public String getInterpretersOrdered() {
		return interpretersOrdered;
	}
	public void setInterpretersOrdered(String interpretersOrdered) {
		this.interpretersOrdered = interpretersOrdered;
	}
	public String getCollateralReceived() {
		return collateralReceived;
	}
	public void setCollateralReceived(String collateralReceived) {
		this.collateralReceived = collateralReceived;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}


}
