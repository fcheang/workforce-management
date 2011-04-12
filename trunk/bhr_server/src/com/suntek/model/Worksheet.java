package com.suntek.model;

import java.util.Date;

public class Worksheet {

	private Date date;
	private String firstName;
	private String lastName;
	private int hrsWorked;
	private int countySeen;
	private int cccSeen;
	private int hmoSeen;
	private int otherSeen;
	private int numScheduled;
	private int numNoShow;
	private int numNew;
	private int numDropIn;
	private double productivity;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
	public int getNumNew() {
		return numNew;
	}
	public void setNumNew(int numNew) {
		this.numNew = numNew;
	}
	public int getNumDropIn() {
		return numDropIn;
	}
	public void setNumDropIn(int numDropIn) {
		this.numDropIn = numDropIn;
	}
	public double getProductivity() {
		return productivity;
	}
	public void setProductivity(double productivity) {
		this.productivity = productivity;
	}

	public void calculateProductivity(){
	}
}
