package com.suntek.model;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CaseManagerReport {
	public static final String TOTAL = "TOTAL";
	public static final String AVG = "AVG";
	
	private String userId;
	private Date date;
	private String dateStr;
	private int numConsumer;
	private int numVisits;
	private int numL2Ref;
	private int numL3Ref;
	private int numL2Seen;
	private int numL3Seen;
	private int numPCPReachedOut;
	private int numPCPAppts;
	private int numCM;		
	private int numEpisodeOpened;
	private int numEpisodeClosed;
	private int numHPOnCaseloadDueToExpire;
	private int numOutsideMeeting;
	private int numVisitNextWeek;
	private int numNonCompliantChart;
		
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
	public int getNumConsumer() {
		return numConsumer;
	}
	public void setNumConsumer(int numConsumer) {
		this.numConsumer = numConsumer;
	}
	public int getNumVisits() {
		return numVisits;
	}
	public void setNumVisits(int numVisits) {
		this.numVisits = numVisits;
	}
	public int getNumL2Ref() {
		return numL2Ref;
	}
	public void setNumL2Ref(int numL2Ref) {
		this.numL2Ref = numL2Ref;
	}
	public int getNumL3Ref() {
		return numL3Ref;
	}
	public void setNumL3Ref(int numL3Ref) {
		this.numL3Ref = numL3Ref;
	}
	public int getNumL2Seen() {
		return numL2Seen;
	}
	public void setNumL2Seen(int numL2Seen) {
		this.numL2Seen = numL2Seen;
	}
	public int getNumL3Seen() {
		return numL3Seen;
	}
	public void setNumL3Seen(int numL3Seen) {
		this.numL3Seen = numL3Seen;
	}
	public int getNumPCPReachedOut() {
		return numPCPReachedOut;
	}
	public void setNumPCPReachedOut(int numPCPReachedOut) {
		this.numPCPReachedOut = numPCPReachedOut;
	}
	public int getNumPCPAppts() {
		return numPCPAppts;
	}
	public void setNumPCPAppts(int numPCPAppts) {
		this.numPCPAppts = numPCPAppts;
	}
	public int getNumCM() {
		return numCM;
	}
	public void setNumCM(int numCM) {
		this.numCM = numCM;
	}
	public int getNumEpisodeOpened() {
		return numEpisodeOpened;
	}
	public void setNumEpisodeOpened(int numEpisodeOpened) {
		this.numEpisodeOpened = numEpisodeOpened;
	}
	public int getNumEpisodeClosed() {
		return numEpisodeClosed;
	}
	public void setNumEpisodeClosed(int numEpisodeClosed) {
		this.numEpisodeClosed = numEpisodeClosed;
	}
	public int getNumHPOnCaseloadDueToExpire() {
		return numHPOnCaseloadDueToExpire;
	}
	public void setNumHPOnCaseloadDueToExpire(int numHPOnCaseloadDueToExpire) {
		this.numHPOnCaseloadDueToExpire = numHPOnCaseloadDueToExpire;
	}
	public int getNumOutsideMeeting() {
		return numOutsideMeeting;
	}
	public void setNumOutsideMeeting(int numOutsideMeeting) {
		this.numOutsideMeeting = numOutsideMeeting;
	}
	public int getNumVisitNextWeek() {
		return numVisitNextWeek;
	}
	public void setNumVisitNextWeek(int numVisitNextWeek) {
		this.numVisitNextWeek = numVisitNextWeek;
	}
	public int getNumNonCompliantChart() {
		return numNonCompliantChart;
	}
	public void setNumNonCompliantChart(int numNonCompliantChart) {
		this.numNonCompliantChart = numNonCompliantChart;
	}
	public String toString(){
		return "[CaseManagerReport for "+getDateStr()+"]";
	}
}
