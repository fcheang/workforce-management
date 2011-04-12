package com.suntek.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.suntek.dao.*;
import com.suntek.model.*;

public class DataService {

	private Logger logger = Logger.getLogger(DataService.class.getName());
	private UserDAOImpl userDAO = null;
	private WorksheetDAOImpl wsDAO = null;
	private EmployeeDAOImpl empDAO = null;
	
	public DataService(){		
	}
	
	public void setUserDAO(UserDAOImpl userDAO){
		this.userDAO = userDAO;
	}
	
	public void setWorksheetDAO(WorksheetDAOImpl wsDAO){
		this.wsDAO = wsDAO;
	}

	public void setEmployeeDAO(EmployeeDAOImpl empDAO){
		this.empDAO = empDAO;
	}
	
	
	// Common 
	
	public boolean login(User u) {
		try{
			logger.debug("login("+u.getUsername()+", "+u.getPassword()+")");
			User loggedInUser = userDAO.getUser(u.getUsername(), u.getPassword());
			return loggedInUser != null;
		}catch(Throwable t){
			logger.error("Problem login with user: "+u.getUsername()+" pass: "+u.getPassword(), t);
			return false;
		}
	}

	// Employee
	
	public List<Employee> getAllEmployees(){
		logger.debug("getAllEmployees()");
		return new ArrayList<Employee>();
	}
	
	public Employee createEmployee(Employee emp){
		logger.debug("createEmployee("+emp+")");
		return emp;
	}
	
	public boolean updateEmployee(Employee emp){
		logger.debug("updateEmployee("+emp+")");
		return true;
	}

	// Worksheet
	
	public void createWorksheet(Worksheet ws){
		
	}
	
	public Worksheet getWorksheet(String clinic, Date date){
		logger.debug("getWorksheet("+clinic+", "+date+")");
		return null;
	}
		
	

}
