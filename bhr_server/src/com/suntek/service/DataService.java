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
	private CommonDAOImpl commDAO = null;
	
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
	
	public void setCommonDAO(CommonDAOImpl commDAO){
		this.commDAO = commDAO;
	}
	
	
	// Common 
	
	public List<String> login(User u) {
		try{
			logger.debug("login("+u.getUsername()+", "+u.getPassword()+")");
			User loggedInUser = userDAO.getUser(u.getUsername(), u.getPassword());
			if (loggedInUser != null){
				return userDAO.getRoles(u.getUsername());
			}else{
				return null;
			}
		}catch(Throwable t){
			logger.error("Problem login with user: "+u.getUsername()+" pass: "+u.getPassword(), t);
			return null;
		}
	}

	// Employee
	
	public List<Employee> getAllEmployees(){
		try{
			logger.debug("getAllEmployees()");
			return empDAO.getAllEmployees();
		}catch(Throwable t){
			t.printStackTrace();
			return null;
		}
	}
	
	public Employee createEmployee(Employee emp){
		try{
			logger.debug("createEmployee("+emp+")");
			return empDAO.createEmployee(emp);
		}catch(Throwable t){
			t.printStackTrace();
			return null;
		}
	}
	
	public boolean updateEmployee(Employee emp){
		try{
			logger.debug("updateEmployee("+emp+")");
			return empDAO.updateEmployee(emp);
		}catch(Throwable t){
			t.printStackTrace();
			return false;
		}
	}

	public boolean deleteEmployee(Employee emp){
		try{
			logger.debug("deleteEmployee("+emp+")");
			return empDAO.deleteEmployee(emp.getEmpId());
		}catch(Throwable t){
			t.printStackTrace();
			return false;
		}			
	}
	
	// Worksheet
	
	public Worksheet createWorksheet(Worksheet ws){
		try{
			logger.debug("createWorksheet("+ws+")");
			return wsDAO.createWorksheet(ws);
		}catch(Throwable t){
			t.printStackTrace();
			return null;
		}			
	}
	
	public List<Worksheet> getWorksheetForClinicAndDate(String clinic, Date date){
		try{
			logger.debug("getWorksheet("+clinic+", "+date+")");
			return wsDAO.getWorksheet(clinic, date);
		}catch(Throwable t){
			t.printStackTrace();
			return null;
		}			
	}		

	public List<Worksheet> getWorksheetForClinicAndDateRange(String clinic, Date sd, Date ed){
		try{
			logger.debug("getWorksheetForClinicAndDateRange("+clinic+", "+sd+"," + ed+")");
			return wsDAO.getWorksheetForClinicAndDateRange(clinic, sd, ed);
		}catch(Throwable t){
			t.printStackTrace();
			return null;
		}			
	}		
	
	public List<Worksheet> getWorksheetForEmployeeAndDateRange(int empId, Date sd, Date ed){
		try{
			logger.debug("getWorksheetForEmployeeAndDateRange("+empId+", "+sd+", "+ed+")");
			return wsDAO.getWorksheetForEmployeeAndDateRange(empId, sd, ed);
		}catch(Throwable t){
			t.printStackTrace();
			return null;
		}			
	}		

	public List<Worksheet> getWorksheetForClinicAndEmployeeAndDateRange(String clinic, int empId, Date sd, Date ed){
		try{
			logger.debug("getWorksheetForClinicAndEmployeeAndDateRange("+clinic+", "+empId+", "+sd+", "+ed+")");
			return wsDAO.getWorksheetForClinicAndEmployeeAndDateRange(clinic, empId, sd, ed);
		}catch(Throwable t){
			t.printStackTrace();
			return null;
		}			
	}		
	
	
	public Worksheet updateWorksheet(Worksheet ws){
		try{
			logger.debug("updateWorksheet("+ws+")");
			if (wsDAO.updateWorksheet(ws)){
				return ws;
			}else{
				return null;
			}
		}catch(Throwable t){
			t.printStackTrace();
			return null;
		}			
	}
	
	public boolean deleteWorksheet(Worksheet ws){
		try{
			logger.debug("deleteWorksheet("+ws+")");
			return wsDAO.deleteWorksheet(ws.getEmpId(), ws.getClinic(), ws.getDate());
		}catch(Throwable t){
			t.printStackTrace();
			return false;
		}			
	}

	// Clinic	
	public List<String> getLocation(){
		try{
			return commDAO.getLocation();
		}catch(Throwable t){
			t.printStackTrace();
			return null;
		}			
	}
}
