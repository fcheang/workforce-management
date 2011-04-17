package com.suntek.model;

public class Employee {

	private int empId;
	private String title;	
	private String firstName;
	private String lastName;
	private String middleName;
	private boolean isActive;
	private String fullName;
	
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getFullName() {
		if (fullName == null){
			fullName = firstName + " " + lastName;
		}
		return fullName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	public String toString(){
		return empId+": "+firstName+" "+lastName;		
	}
	
}
