package com.suntek.model;

import java.util.Date;

import com.suntek.util.Constants;

public class Worksheet {

	private int empId;
	private Date date;
	private String clinic;
	
	private int hrsWorked;
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
	public int getHrsWorked() {
		return hrsWorked;
	}
	public void setHrsWorked(int hrsWorked) {
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

	public String toString(){
		return empId+": "+clinic+" "+Constants.df.format(date);
	}
}
