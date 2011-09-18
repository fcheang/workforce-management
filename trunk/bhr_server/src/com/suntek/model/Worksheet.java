package com.suntek.model;

import java.util.Date;

import com.suntek.util.Constants;

public class Worksheet {

	private int providerId;
	private String providerName;
	private Date date;
	private String clinic;
	
	private double hrsWorked;
	private int countySeen;
	private int cccSeen;
	private int hmoSeen;
	private int otherSeen;
	
	private int countyFaceMin;
	private int countyOtherMin;
	private int cccFaceMin;
	private int cccOtherMin;
	private int hmoFaceMin;
	private int otherFaceMin;	
	
	private int numScheduled;
	private int numNoShow;
	private int numCancel; 
	private int numNew;
	private int numDropin;
	private double dailySalary;
	
	private String enteredBy;
	private Date dateEntered;
	
	private boolean isAggregate = false;
	
	public boolean getIsAggregate() {
		return isAggregate;
	}
	
	public void setIsAggregate(boolean isAggregate) {
		this.isAggregate = isAggregate;
	}
	
	public String getEnteredBy() {
		return enteredBy;
	}
	public void setEnteredBy(String modifyBy) {
		this.enteredBy = modifyBy;
	}
	public Date getDateEntered() {
		return dateEntered;
	}
	public void setDateEntered(Date dateEntered) {
		this.dateEntered = dateEntered;
	}

	// calculated value
	private int totalSeen;
	private double totalFaceHours;
	private double totalOtherHours;
	private double productivity;
	private double countyFaceRevenue;
	private double countyOtherRevenue;
	private double cccFaceRevenue;
	private double totalRevenue;
	private double balance;
	
	
	public int getProviderId() {
		return providerId;
	}
	public void setProviderId(int providerId) {
		this.providerId = providerId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getClinic() {
		return clinic;
	}
	public void setClinic(String clinic) {
		this.clinic = clinic;
	}
	public double getHrsWorked() {
		return hrsWorked;
	}
	public void setHrsWorked(double hrsWorked) {
		this.hrsWorked = hrsWorked;
	}
	public int getCountySeen() {
		return countySeen;
	}
	public void setCountySeen(int countySeen) {
		this.countySeen = countySeen;
	}
	public int getCccSeen() {
		return cccSeen;
	}
	public void setCccSeen(int cccSeen) {
		this.cccSeen = cccSeen;
	}
	public int getHmoSeen() {
		return hmoSeen;
	}
	public void setHmoSeen(int hmoSeen) {
		this.hmoSeen = hmoSeen;
	}
	public int getOtherSeen() {
		return otherSeen;
	}
	public void setOtherSeen(int otherSeen) {
		this.otherSeen = otherSeen;
	}
	public int getCountyFaceMin() {
		return countyFaceMin;
	}
	public void setCountyFaceMin(int countyFaceMin) {
		this.countyFaceMin = countyFaceMin;
	}
	public int getCountyOtherMin() {
		return countyOtherMin;
	}
	public void setCountyOtherMin(int countyOtherMin) {
		this.countyOtherMin = countyOtherMin;
	}
	public int getCccFaceMin() {
		return cccFaceMin;
	}
	public void setCccFaceMin(int cccFaceMin) {
		this.cccFaceMin = cccFaceMin;
	}
	public int getCccOtherMin() {
		return cccOtherMin;
	}
	public void setCccOtherMin(int cccOtherMin) {
		this.cccOtherMin = cccOtherMin;
	}
	public int getHmoFaceMin() {
		return hmoFaceMin;
	}
	public void setHmoFaceMin(int hmoFaceMin) {
		this.hmoFaceMin = hmoFaceMin;
	}
	public int getOtherFaceMin() {
		return otherFaceMin;
	}
	public void setOtherFaceMin(int otherFaceMin) {
		this.otherFaceMin = otherFaceMin;
	}
	public int getNumScheduled() {
		return numScheduled;
	}
	public void setNumScheduled(int numScheduled) {
		this.numScheduled = numScheduled;
	}
	public int getNumNoShow() {
		return numNoShow;
	}
	public void setNumNoShow(int numNoShow) {
		this.numNoShow = numNoShow;
	}
	public int getNumCancel() {
		return numCancel;
	}
	public void setNumCancel(int numCancel) {
		this.numCancel = numCancel;
	}
	public int getNumNew() {
		return numNew;
	}
	public void setNumNew(int numNew) {
		this.numNew = numNew;
	}
	public int getNumDropin() {
		return numDropin;
	}
	public void setNumDropin(int numDropin) {
		this.numDropin = numDropin;
	}
	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}	
	public double getCountyFaceRevenue() {
		countyFaceRevenue = this.countyFaceMin * 4.66;
		return countyFaceRevenue;
	}
	public void setCountyFaceRevenue(double countyFaceRevenue) {
		this.countyFaceRevenue = countyFaceRevenue;
	}
	public double getCountyOtherRevenue() {
		countyOtherRevenue = this.countyOtherMin * 4.66;
		return countyOtherRevenue;
	}
	public void setCountyOtherRevenue(double countyOtherRevenue) {
		this.countyOtherRevenue = countyOtherRevenue;
	}
	public double getCccFaceRevenue() {
		cccFaceRevenue = this.cccFaceMin * 4.33;
		return cccFaceRevenue;
	}
	public void setCccFaceRevenue(double cccFaceRevenue) {
		this.cccFaceRevenue = cccFaceRevenue;
	}
	public double getTotalRevenue() {
		totalRevenue = this.getCountyFaceRevenue() + this.getCountyOtherRevenue() + this.getCccFaceRevenue();
		return totalRevenue;
	}
	public void setTotalRevenue(double totalRevenue) {
		this.totalRevenue = totalRevenue;
	}
	public double getBalance() {
		balance = this.getTotalRevenue() - this.dailySalary;
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public double getTotalFaceHours() {
		totalFaceHours = (this.countyFaceMin + this.cccFaceMin + this.hmoFaceMin + this.otherFaceMin)/60.0;
		return totalFaceHours;
	}
	public void setTotalFaceHours(double totalFaceHours) {
		this.totalFaceHours = totalFaceHours;
	}
	public double getTotalOtherHours() {
		totalOtherHours = (this.countyOtherMin + this.cccOtherMin)/60.0;
		return totalOtherHours;
	}
	public void setTotalOtherHours(double totalOtherHours) {
		this.totalOtherHours = totalOtherHours;
	}
	public double getProductivity() {
		if (hrsWorked > 0){
			productivity = ((this.getTotalFaceHours() + this.getTotalOtherHours()) / (this.hrsWorked)) * 100;
		}else{
			productivity = 0;
		}
		return productivity;
	}
	public void setProductivity(double productivity) {
		this.productivity = productivity;
	}	
	public double getDailySalary() {
		return dailySalary;
	}
	public void setDailySalary(double dailySalary) {
		this.dailySalary = dailySalary;
	}
	public int getTotalSeen() {
		totalSeen = this.countySeen + this.cccSeen + this.hmoSeen + this.otherSeen;
		return totalSeen;
	}
	public void setTotalSeen(int totalSeen) {
		this.totalSeen = totalSeen;
	}

	public String toString(){
		return providerId+": "+clinic+" "+Constants.df.format(date);
	}
	
}
