package com.suntek.model;

public class DataComplianceTask extends DailyTask {
	
	private int epsOpened;
	private int billErrsFound;
	private int chartsReviewed;
	private int chartErrsFound;
	private int itemsEntered;
	
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

}
