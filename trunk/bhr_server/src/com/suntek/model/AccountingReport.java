package com.suntek.model;

import java.util.Date;

public class AccountingReport {

	private Date dateOfWeek;
	private int accountId;
	private String accountName;
	private int numBills;
	private double amtPaid;
	private int numChecks;
	private double amtChecks;
	private double amtCashCollected;
	private int numCheckDeposited;
	private double amtDeposited;
	
	public Date getDateOfWeek() {
		return dateOfWeek;
	}
	public void setDateOfWeek(Date dateOfWeek) {
		this.dateOfWeek = dateOfWeek;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public int getNumBills() {
		return numBills;
	}
	public void setNumBills(int numBills) {
		this.numBills = numBills;
	}
	public double getAmtPaid() {
		return amtPaid;
	}
	public void setAmtPaid(double amtPaid) {
		this.amtPaid = amtPaid;
	}
	public int getNumChecks() {
		return numChecks;
	}
	public void setNumChecks(int numChecks) {
		this.numChecks = numChecks;
	}
	public double getAmtChecks() {
		return amtChecks;
	}
	public void setAmtChecks(double amtChecks) {
		this.amtChecks = amtChecks;
	}
	public double getAmtCashCollected() {
		return amtCashCollected;
	}
	public void setAmtCashCollected(double amtCashCollected) {
		this.amtCashCollected = amtCashCollected;
	}
	public int getNumCheckDeposited() {
		return numCheckDeposited;
	}
	public void setNumCheckDeposited(int numCheckDeposited) {
		this.numCheckDeposited = numCheckDeposited;
	}
	public double getAmtDeposited() {
		return amtDeposited;
	}
	public void setAmtDeposited(double amtDeposited) {
		this.amtDeposited = amtDeposited;
	}

	public String toString(){
		return "["+this.dateOfWeek+", "+accountId+"]";
	}
}
