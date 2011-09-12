package com.suntek.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
	private PermissionDAOImpl permDAO;
	private ContributionDAOImpl contDAO;
	private DataComplianceTaskDAOImpl dctDAO;
	private URPersonnelTaskDAOImpl urDAO;
	private BillingTaskDAOImpl btDAO;
	private ProjectListDAOImpl plDAO;
	
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
	
	public void setPermDAO(PermissionDAOImpl permDAO){
		this.permDAO = permDAO;
	}

	public void setContDAO(ContributionDAOImpl contDAO){
		this.contDAO = contDAO;
	}
	
	public void setDctDAO(DataComplianceTaskDAOImpl dctDAO){
		this.dctDAO = dctDAO;
	}

	public void setUrDAO(URPersonnelTaskDAOImpl urDAO){
		this.urDAO = urDAO;
	}

	public void setBtDAO(BillingTaskDAOImpl btDAO){
		this.btDAO = btDAO;
	}

	public void setPlDAO(ProjectListDAOImpl plDAO){
		this.plDAO = plDAO;
	}

	// Common 
	
	public User login(User u) {
		try{
			logger.debug("login("+u.getUsername()+", "+u.getPassword()+")");
			return userDAO.getUser(u.getUsername(), u.getPassword());			
		}catch(Throwable t){
			logger.error("Problem login with user: "+u.getUsername()+" pass: "+u.getPassword(), t);
			return null;
		}
	}
	
	// Provider
	public List<Provider> getAllProvider(){
		try{
			logger.debug("getAllEmployees()");
			return commDAO.getAllProvider();
		}catch(Throwable t){
			t.printStackTrace();
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
	
	public List<Worksheet> getWorksheetForProviderAndDateRange(int empId, Date sd, Date ed){
		try{
			logger.debug("getWorksheetForProviderAndDateRange("+empId+", "+sd+", "+ed+")");
			return wsDAO.getWorksheetForProviderAndDateRange(empId, sd, ed);
		}catch(Throwable t){
			t.printStackTrace();
			return null;
		}			
	}		

	public List<Worksheet> getWorksheetForClinicAndProviderAndDateRange(String clinic, int empId, Date sd, Date ed){
		try{
			logger.debug("getWorksheetForClinicAndProviderAndDateRange("+clinic+", "+empId+", "+sd+", "+ed+")");
			return wsDAO.getWorksheetForClinicAndProviderAndDateRange(clinic, empId, sd, ed);
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
			return wsDAO.deleteWorksheet(ws.getProviderId(), ws.getClinic(), ws.getDate());
		}catch(Throwable t){
			t.printStackTrace();
			return false;
		}			
	}

	
	// Worksheet for Manager
	
	public Worksheet createWorksheetForMgr(Worksheet ws){
		return createWorksheet(ws);
	}
	
	public List<Worksheet> getWorksheetForClinicAndDateForMgr(String clinic, Date date){
		return getWorksheetForClinicAndDate(clinic, date);
	}		

	public Worksheet updateWorksheetForMgr(Worksheet ws){
		return updateWorksheet(ws);
	}
	
	public boolean deleteWorksheetForMgr(Worksheet ws){
		return deleteWorksheet(ws);
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
	
	// Permission	
	public List<String> getAllPermissionType(){
		try{
			return commDAO.getAllPermissionType();
		}catch(Throwable t){
			t.printStackTrace();
			return null;
		}
	}
	
	public List<String> getAllUserId(){
		try{
			return commDAO.getAllUserId();
		}catch(Throwable t){
			t.printStackTrace();
			return null;
		}		
	}
	
	public List<String> getPermissionObject(String userId){
		try{
			return permDAO.getPermissionObject(userId);
		}catch(RuntimeException t){
			t.printStackTrace();
			throw t;
		}
	}

	
	public List<Permission> getAllPermission(){
		try{
			return permDAO.getAllPermission();
		}catch(Throwable t){
			t.printStackTrace();
			return null;
		}				
	}
	
	public Permission createPermission(Permission perm){
		try{
			if (!permDAO.permissionExist(perm.getUserId(), perm.getObject())){
				return permDAO.createPermission(perm);
			}else{
				return null;
			}
		}catch(Throwable t){
			t.printStackTrace();
			return null;
		}						
	}
	
	public boolean updatePermission(Permission perm){
		try{
			return permDAO.updatePermission(perm);
		}catch(Throwable t){
			t.printStackTrace();
			return false;
		}								
	}
	
	public boolean deletePermission(Permission perm){
		try{
			return permDAO.deletePermission(perm);
		}catch(Throwable t){
			t.printStackTrace();
			return false;
		}								
	}
	
	// Contribution
	public Contribution getContribution(Date date, User user){
		return contDAO.getContribution(date, user);
	}
	
	public List<ContributionItem> getContributionItems(Date date, User user){
		return contDAO.getContributionItems(date, user);
	}
	
	public boolean updateContributionReport(Contribution cont, List items){
		updateContribution(cont);
		updateContributionItems(items);
		return true;
	}
	
	private boolean updateContribution(Contribution cont){
		return contDAO.updateContribution(cont);
	}
	
	private boolean updateContributionItems(List items){
		return contDAO.updateContributionItems(items);
	}
	
	// DataComplianceTask
	public List<DataComplianceTask> getDataComplianceTaskForWeek(Date date, User user){
		List<DataComplianceTask> dctList = new ArrayList<DataComplianceTask>();		
		try{
			logger.debug("getDataComplianceTaskForWeek("+date+", "+user+")");		
			Calendar cal = new GregorianCalendar();
			cal.setTime(date);
			int offsetToSunday = 1 - cal.get(Calendar.DAY_OF_WEEK);
			cal.add(Calendar.DATE, offsetToSunday);
			for (int i=0; i<6; i++){
				cal.add(Calendar.DATE, 1);
				DataComplianceTask dct = dctDAO.getDataComplianceTask(cal.getTime(), user.getUsername());
				if (dct == null){
					dct = new DataComplianceTask();
					dct.setDate(cal.getTime());
					dct.setUserId(user.getUsername());
				}
				dctList.add(dct);			
			}		
		}catch(Throwable t){
			t.printStackTrace();
		}
		return dctList;
	}
	
	public boolean updateComplianceTask(List<DataComplianceTask> tasks){
		try{
			logger.debug("updateComplianceTask("+tasks+")");
			dctDAO.updateDataComplianceTask(tasks);
			return true;
		}catch(Throwable t){
			t.printStackTrace();
			return false;
		}		
	}
	
	// URPersonnelTask
	public List<URPersonnelTask> getURPersonnelTaskForWeek(Date date, User user){
		List<URPersonnelTask> urList = new ArrayList<URPersonnelTask>();		
		try{
			logger.debug("getURPersonnelTaskForWeek("+date+", "+user+")");		
			Calendar cal = new GregorianCalendar();
			cal.setTime(date);
			int offsetToSunday = 1 - cal.get(Calendar.DAY_OF_WEEK);
			cal.add(Calendar.DATE, offsetToSunday);
			for (int i=0; i<6; i++){
				cal.add(Calendar.DATE, 1);
				URPersonnelTask ur = urDAO.getURPersonnelTask(cal.getTime(), user.getUsername());
				if (ur == null){
					ur = new URPersonnelTask();
					ur.setDate(cal.getTime());
					ur.setUserId(user.getUsername());
				}
				urList.add(ur);			
			}		
		}catch(Throwable t){
			t.printStackTrace();
		}
		return urList;
	}
	
	public boolean updateURPersonnelTask(List<URPersonnelTask> tasks){
		try{
			logger.debug("updateURPersonnelTask("+tasks+")");
			urDAO.updateURPersonnelTask(tasks);
			return true;
		}catch(Throwable t){
			t.printStackTrace();
			return false;
		}		
	}

	// Billing Task
	public List<BillingTask> getBillingTaskForWeek(Date date, User user){
		List<BillingTask> urList = new ArrayList<BillingTask>();		
		try{
			logger.debug("getBillingTaskForWeek("+date+", "+user+")");		
			Calendar cal = new GregorianCalendar();
			cal.setTime(date);
			int offsetToSunday = 1 - cal.get(Calendar.DAY_OF_WEEK);
			cal.add(Calendar.DATE, offsetToSunday);
			for (int i=0; i<6; i++){
				cal.add(Calendar.DATE, 1);
				BillingTask bt = btDAO.getBillingTask(cal.getTime(), user.getUsername());
				if (bt == null){
					bt = new BillingTask();
					bt.setDate(cal.getTime());
					bt.setUserId(user.getUsername());
				}
				urList.add(bt);			
			}		
		}catch(Throwable t){
			t.printStackTrace();
		}
		return urList;
	}
	
	public boolean updateBillingTask(List<BillingTask> tasks){
		try{
			logger.debug("updateBillingTask("+tasks+")");
			btDAO.updateBillingTask(tasks);
			return true;
		}catch(Throwable t){
			t.printStackTrace();
			return false;
		}		
	}
	
	// Project List
	public List<ProjectList> getProjectListForWeek(Date date, User user){
		List<ProjectList> urList = new ArrayList<ProjectList>();		
		try{
			logger.debug("getProjectListForWeek("+date+", "+user+")");		
			Calendar cal = new GregorianCalendar();
			cal.setTime(date);
			int offsetToSunday = 1 - cal.get(Calendar.DAY_OF_WEEK);
			cal.add(Calendar.DATE, offsetToSunday);
			for (int i=0; i<6; i++){
				cal.add(Calendar.DATE, 1);
				ProjectList pl = plDAO.getProjectList(cal.getTime(), user.getUsername());
				if (pl == null){
					pl = new ProjectList();
					pl.setDate(cal.getTime());
					pl.setUserId(user.getUsername());
				}
				urList.add(pl);			
			}		
		}catch(Throwable t){
			t.printStackTrace();
		}
		return urList;
	}
	
	public boolean updateProjectList(List<ProjectList> tasks){
		try{
			logger.debug("updateProjectList("+tasks+")");
			plDAO.updateProjectList(tasks);
			return true;
		}catch(Throwable t){
			t.printStackTrace();
			return false;
		}		
	}
	
}
