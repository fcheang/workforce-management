package com.suntek.model;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DataComplianceTask {
	
	private String userId;
	private Date date;
	private String dateStr;
	private int epsOpened;
	private int billErrsFound;
	private int chartsReviewed;
	private int chartErrsFound;
	private int itemsEntered;
	
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
	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}
	public int getEpsOpened() {
		return epsOpened;
	}
	public void setEpsOpened(int epsOpened) {
		this.epsOpened = epsOpened;
	}
	public int getBillErrsFound() {
		return billErrsFound;
	}
	public void setBillErrsFound(int billErrsFound) {
		this.billErrsFound = billErrsFound;
	}
	public int getChartsReviewed() {
		return chartsReviewed;
	}
	public void setChartsReviewed(int chartsReviewed) {
		this.chartsReviewed = chartsReviewed;
	}
	public int getChartErrsFound() {
		return chartErrsFound;
	}
	public void setChartErrsFound(int chartErrsFound) {
		this.chartErrsFound = chartErrsFound;
	}
	public int getItemsEntered() {
		return itemsEntered;
	}
	public void setItemsEntered(int itemsEntered) {
		this.itemsEntered = itemsEntered;
	}
	public String toString(){
		return "["+userId+" "+date+"]";
	}

}
