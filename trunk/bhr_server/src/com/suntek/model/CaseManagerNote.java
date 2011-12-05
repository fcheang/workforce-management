package com.suntek.model;

import java.util.Date;

public class CaseManagerNote {
	private String userId;
	private Date dateOfWeek;
	private String plan;
	private String action;
	private String assistanceNeeded;
	private String plansForNextWeek;		
	private String other;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getDateOfWeek() {
		return dateOfWeek;
	}
	public void setDateOfWeek(Date dateOfWeek) {
		this.dateOfWeek = dateOfWeek;
	}
	public String getPlan() {
		return plan;
	}
	public void setPlan(String plan) {
		this.plan = plan;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getAssistanceNeeded() {
		return assistanceNeeded;
	}
	public void setAssistanceNeeded(String assistanceNeeded) {
		this.assistanceNeeded = assistanceNeeded;
	}
	public String getPlansForNextWeek() {
		return plansForNextWeek;
	}
	public void setPlansForNextWeek(String plansForNextWeek) {
		this.plansForNextWeek = plansForNextWeek;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}

}
