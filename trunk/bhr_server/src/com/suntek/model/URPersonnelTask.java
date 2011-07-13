package com.suntek.model;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class URPersonnelTask {
	
	private String userId;
	private Date date;
	private String dateStr;
	private int chartsReviewed;
	private int dischargeDone;
	private int billSvcProvided;
	private int epsOpened;
	private int chartsTransfered;
	private int ruCompleted;
	private int mdChartsAudited;
	private int mhsChartsAudited;
	private int txTeamMtgs;
	
	public int getChartsReviewed() {
		return chartsReviewed;
	}
	public void setChartsReviewed(int chartsReviewed) {
		this.chartsReviewed = chartsReviewed;
	}
	public int getDischargeDone() {
		return dischargeDone;
	}
	public void setDischargeDone(int dischargeDone) {
		this.dischargeDone = dischargeDone;
	}
	public int getBillSvcProvided() {
		return billSvcProvided;
	}
	public void setBillSvcProvided(int billSvcProvided) {
		this.billSvcProvided = billSvcProvided;
	}
	public int getEpsOpened() {
		return epsOpened;
	}
	public void setEpsOpened(int epsOpened) {
		this.epsOpened = epsOpened;
	}
	public int getChartsTransfered() {
		return chartsTransfered;
	}
	public void setChartsTransfered(int chartsTransfered) {
		this.chartsTransfered = chartsTransfered;
	}
	public int getRuCompleted() {
		return ruCompleted;
	}
	public void setRuCompleted(int ruCompleted) {
		this.ruCompleted = ruCompleted;
	}
	public int getMdChartsAudited() {
		return mdChartsAudited;
	}
	public void setMdChartsAudited(int mdChartsAudited) {
		this.mdChartsAudited = mdChartsAudited;
	}
	public int getMhsChartsAudited() {
		return mhsChartsAudited;
	}
	public void setMhsChartsAudited(int mhsChartsAudited) {
		this.mhsChartsAudited = mhsChartsAudited;
	}
	public int getTxTeamMtgs() {
		return txTeamMtgs;
	}
	public void setTxTeamMtgs(int txTeamMtgs) {
		this.txTeamMtgs = txTeamMtgs;
	}
	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
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
		dateStr = null;
	}
	public String getDateStr() {
		if (dateStr == null){
			Calendar cal = new GregorianCalendar();
			cal.setTime(date);
			int d = cal.get(Calendar.DAY_OF_WEEK);
			switch (d) {
				case Calendar.SUNDAY:
					dateStr = "Sunday";
					break;
				case Calendar.MONDAY:
					dateStr = "Monday";
					break;
				case Calendar.TUESDAY:
					dateStr = "Tuesday";
					break;
				case Calendar.WEDNESDAY:
					dateStr = "Wednesday";
					break;
				case Calendar.THURSDAY:
					dateStr = "Thursday";
					break;
				case Calendar.FRIDAY:
					dateStr = "Friday";
					break;
				case Calendar.SATURDAY:
					dateStr = "Saturday";
					break;
			}
			DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
			
			dateStr = dateStr + " " +df.format(date);
		}
		return dateStr;
	}
	public String toString(){
		return "["+userId+" "+date+"]";
	}

}
