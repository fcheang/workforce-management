package com.suntek.model;

import java.util.Date;

import com.suntek.util.Constants;

public class Worksheet {

	private int empId;
	private String empName;
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
	
	// calculated value
	private double totalFaceHours = -1;
	private double totalOtherHours = -1;
	private double productivity = -1;
	private double countyFaceRevenue = -1;
	private double countyOtherRevenue = -1;
	private double cccFaceRevenue = -1;
	private double totalRevenue = -1;
	private double balance = -1;
	
	
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
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
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}	
	public double getCountyFaceRevenue() {
		return countyFaceRevenue;
	}
	public void setCountyFaceRevenue(double countyFaceRevenue) {
		this.countyFaceRevenue = countyFaceRevenue;
	}
	public double getCountyOtherRevenue() {
		return countyOtherRevenue;
	}
	public void setCountyOtherRevenue(double countyOtherRevenue) {
		this.countyOtherRevenue = countyOtherRevenue;
	}
	public double getCccFaceRevenue() {
		return cccFaceRevenue;
	}
	public void setCccFaceRevenue(double cccFaceRevenue) {
		this.cccFaceRevenue = cccFaceRevenue;
	}
	public double getTotalRevenue() {
		return totalRevenue;
	}
	public void setTotalRevenue(double totalRevenue) {
		this.totalRevenue = totalRevenue;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public double getTotalFaceHours() {
		if (totalFaceHours == -1){
			totalFaceHours = (this.countyFaceMin + this.cccFaceMin + this.hmoFaceMin + this.otherFaceMin)/60;
		}
		return totalFaceHours;
	}
	public void setTotalFaceHours(double totalFaceHours) {
		this.totalFaceHours = totalFaceHours;
	}
	public double getTotalOtherHours() {
		if (totalOtherHours == -1){
			totalOtherHours = (this.countyOtherMin + this.cccOtherMin)/60;
		}
		return totalOtherHours;
	}
	public void setTotalOtherHours(double totalOtherHours) {
		this.totalOtherHours = totalOtherHours;
	}
	public double getProductivity() {
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

	public String toString(){
		return empId+": "+clinic+" "+Constants.df.format(date);
	}
	
}
