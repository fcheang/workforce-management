package com.suntek.model;

import java.util.Date;

public class ContributionItem {

	public final static String INCOMING_CALLS = "Incoming Calls";
	public final static String OUTGOING_CALLS = "Outgoing Calls";
	public final static String REMINDER_CALLS = "Reminder Calls";
	public final static String TOTAL_APPTS_MADE = "Total Appointments Made";
	
	private String userId;
	private Date date;
	private String type;
	private int privatePay;
	private int hmo;		
	private int ac;
	private int acChild;
	private int ccc;
	private int cccChild;
	private int sf;
	private int other;
	
	public ContributionItem(User user, Date date2, String type) {
		this.userId = user.getUsername();
		this.date = date2;
		this.type = type;
	}
	public ContributionItem() {
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getPrivatePay() {
		return privatePay;
	}
	public void setPrivatePay(int privatePay) {
		this.privatePay = privatePay;
	}
	public int getHmo() {
		return hmo;
	}
	public void setHmo(int hmo) {
		this.hmo = hmo;
	}
	public int getAc() {
		return ac;
	}
	public void setAc(int ac) {
		this.ac = ac;
	}
	public int getAcChild() {
		return acChild;
	}
	public void setAcChild(int acChild) {
		this.acChild = acChild;
	}
	public int getCcc() {
		return ccc;
	}
	public void setCcc(int ccc) {
		this.ccc = ccc;
	}
	public int getCccChild() {
		return cccChild;
	}
	public void setCccChild(int cccChild) {
		this.cccChild = cccChild;
	}
	public int getSf() {
		return sf;
	}
	public void setSf(int sf) {
		this.sf = sf;
	}
	public int getOther() {
		return other;
	}
	public void setOther(int other) {
		this.other = other;
	}		

}
