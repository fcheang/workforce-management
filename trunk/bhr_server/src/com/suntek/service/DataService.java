package com.suntek.service;

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
		return empDAO.getAllEmployees();
	}
	
	public Employee createEmployee(Employee emp){
		logger.debug("createEmployee("+emp+")");
		return empDAO.createEmployee(emp);
	}
	
	public boolean updateEmployee(Employee emp){
		logger.debug("updateEmployee("+emp+")");
		return empDAO.updateEmployee(emp);
	}

	public boolean deleteEmployee(Employee emp){
		logger.debug("deleteEmployee("+emp+")");
		return empDAO.deleteEmployee(emp.getEmpId());
	}
	
	// Worksheet
	
	public Worksheet createWorksheet(Worksheet ws){
		logger.debug("createWorksheet("+ws+")");
		return wsDAO.createWorksheet(ws);
	}
	
	public List<Worksheet> getWorksheetForClinicAndDate(String clinic, Date date){
		logger.debug("getWorksheet("+clinic+", "+date+")");
		return wsDAO.getWorksheet(clinic, date);
	}		
	
	public boolean updateWorksheet(Worksheet ws){
		logger.debug("updateWorksheet("+ws+")");
		return wsDAO.updateWorksheet(ws);
	}
	
	public boolean deleteWorksheet(Worksheet ws){
		logger.debug("deleteWorksheet("+ws+")");
		return wsDAO.deleteWorksheet(ws.getEmpId(), ws.getClinic(), ws.getDate());
	}

}
