package com.suntek.service;

import java.math.BigDecimal;
import java.math.MathContext;
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
	private AccountingReportDAOImpl arDAO;
	private CaseManagerReportDAOImpl cmDAO;
	
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

	public void setArDAO(AccountingReportDAOImpl arDAO){
		this.arDAO = arDAO;
	}

	public void setCmDAO(CaseManagerReportDAOImpl cmDAO){
		this.cmDAO = cmDAO;
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
		logger.debug("createWorksheetForMgr("+ws+")");
		return createWorksheet(ws);
	}
	
	public Worksheet updateWorksheetForMgr(Worksheet ws){
		logger.debug("updateWorksheetForMgr("+ws+")");
		return updateWorksheet(ws);
	}
	
	public boolean deleteWorksheetForMgr(Worksheet ws){
		logger.debug("deleteWorksheetForMgr("+ws+")");
		return deleteWorksheet(ws);
	}
	
	public List<Worksheet> getWorksheetForDateRange(Date sd, Date ed){
		List<Worksheet> worksheets = null;
		try{
			logger.debug("getWorksheetForDateRange("+sd+"," + ed+")");
			worksheets = wsDAO.getWorksheetForDateRange(sd, ed);
		}catch(Throwable t){
			t.printStackTrace();
		}			
		return worksheets;		
	}
	
	public List<Worksheet> getWorksheetForClinicAndDateRange(String clinic, Date sd, Date ed){
		List<Worksheet> worksheets = null;
		try{
			logger.debug("getWorksheetForClinicAndDateRange("+clinic+", "+sd+"," + ed+")");
			worksheets = wsDAO.getWorksheetForClinicAndDateRange(clinic, sd, ed);
		}catch(Throwable t){
			t.printStackTrace();
		}			
		return worksheets;
	}		
	
	public List<Worksheet> getWorksheetForProviderAndDateRange(int empId, Date sd, Date ed){
		List<Worksheet> worksheets = null;
		try{
			logger.debug("getWorksheetForProviderAndDateRange("+empId+", "+sd+", "+ed+")");
			worksheets = wsDAO.getWorksheetForProviderAndDateRange(empId, sd, ed);
		}catch(Throwable t){
			t.printStackTrace();
		}			
		return worksheets;		
	}		
	
	public List<Worksheet> getWorksheetForClinicAndProviderAndDateRange(String clinic, int empId, Date sd, Date ed){
		List<Worksheet> worksheets = null;
		try{
			logger.debug("getWorksheetForClinicAndProviderAndDateRange("+clinic+", "+empId+", "+sd+", "+ed+")");
			worksheets = wsDAO.getWorksheetForClinicAndProviderAndDateRange(clinic, empId, sd, ed);
		}catch(Throwable t){
			t.printStackTrace();
		}			
		return worksheets;		
	}		
		

	// Analytics
	public List<Worksheet> getWorksheetForClinicAndDateRangeGroupByDate(String clinic, Date sd, Date ed){
		List<Worksheet> worksheets = null;
		try{
			logger.debug("getWorksheetForClinicAndDateRangeGroupByDate("+clinic+", "+sd+"," + ed+")");
			worksheets = wsDAO.getWorksheetForClinicAndDateRangeGroupByDate(clinic, sd, ed);
			addSumOfAllDates(worksheets);
		}catch(Throwable t){
			t.printStackTrace();
		}			
		return worksheets;
	}		
	
	public List<Worksheet> getWorksheetForProviderAndDateRangeGroupByDate(int empId, Date sd, Date ed){
		List<Worksheet> worksheets = null;
		try{
			logger.debug("getWorksheetForProviderAndDateRangeGroupByDate("+empId+", "+sd+", "+ed+")");
			worksheets = wsDAO.getWorksheetForProviderAndDateRangeGroupByDate(empId, sd, ed);
			addSumOfAllDates(worksheets);
		}catch(Throwable t){
			t.printStackTrace();
		}			
		return worksheets;		
	}		
	
	public List<Worksheet> getWorksheetForClinicAndProviderAndDateRangeGroupByDate(String clinic, int empId, Date sd, Date ed){
		List<Worksheet> worksheets = null;
		try{
			logger.debug("getWorksheetForClinicAndProviderAndDateRangeGroupByDate("+clinic+", "+empId+", "+sd+", "+ed+")");
			worksheets = wsDAO.getWorksheetForClinicAndProviderAndDateRangeGroupByDate(clinic, empId, sd, ed);
			addSumOfAllDates(worksheets);
		}catch(Throwable t){
			t.printStackTrace();
		}			
		return worksheets;		
	}		
		
	private void addSumOfAllDates(List<Worksheet> worksheets) {
		if (worksheets.size() <= 1){
			return;
		}
		
		Worksheet sum = null;
		double hrsWorked = 0;
		int countySeen = 0;
		int cccSeen = 0;
		int hmoSeen = 0;
		int otherSeen = 0;	
		int countyFaceMin = 0;
		int countyOtherMin = 0;
		int cccFaceMin = 0;
		int cccOtherMin = 0;
		int hmoFaceMin = 0;
		int otherFaceMin = 0;			
		int numScheduled = 0;
		int numNoShow = 0;
		int numCancel = 0; 
		int numNew = 0;
		int numDropin = 0;
		double dailySalary = 0; 		
		for (Worksheet ws : worksheets){
			if (sum == null){
				sum = new Worksheet();
				sum.setProviderId(ws.getProviderId());
				sum.setProviderName(ws.getProviderName());
				sum.setDate(new Date());
				sum.setClinic(ws.getClinic());
				sum.setIsAggregate(true);
			}
			hrsWorked += ws.getHrsWorked();
			countySeen += ws.getCountySeen();
			cccSeen += ws.getCccSeen();
			hmoSeen += ws.getHmoSeen();
			otherSeen += ws.getOtherSeen();		
			countyFaceMin += ws.getCountyFaceMin();
			countyOtherMin += ws.getCountyOtherMin();
			cccFaceMin += ws.getCccFaceMin();
			cccOtherMin += ws.getCccOtherMin();
			hmoFaceMin += ws.getHmoFaceMin();
			otherFaceMin += ws.getOtherFaceMin();			
			numScheduled += ws.getNumScheduled();
			numNoShow += ws.getNumNoShow();
			numCancel += ws.getNumCancel(); 
			numNew += ws.getNumNew();
			numDropin += ws.getNumDropin();
			dailySalary += ws.getDailySalary(); 		
		}
		sum.setHrsWorked(hrsWorked);
		sum.setCountySeen(countySeen);
		sum.setCccSeen(cccSeen);
		sum.setHmoSeen(hmoSeen);
		sum.setOtherSeen(otherSeen);
		sum.setCountyFaceMin(countyFaceMin);
		sum.setCountyOtherMin(countyOtherMin);
		sum.setCccFaceMin(cccFaceMin);
		sum.setCccOtherMin(cccOtherMin);
		sum.setHmoFaceMin(hmoFaceMin);
		sum.setOtherFaceMin(otherFaceMin);
		sum.setNumScheduled(numScheduled);
		sum.setNumNoShow(numNoShow);
		sum.setNumCancel(numCancel);
		sum.setNumNew(numNew);
		sum.setNumDropin(numDropin);
		sum.setDailySalary(dailySalary);
		
		worksheets.add(sum);
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

	// Accounting Report
	public List<AccountingReport> getAccountingReport(Date dateOfWeek){
		List<AccountingReport> rpts = new ArrayList<AccountingReport>();
		try{
			logger.debug("getAccountingReport("+dateOfWeek+")");
			List<Account> acList = arDAO.getAllAccount();
			for (Account ac : acList){
				AccountingReport ar = arDAO.getAccountingReport(dateOfWeek, ac.getId());
				if (ar == null){
					ar = new AccountingReport();
					ar.setDateOfWeek(dateOfWeek);
					ar.setAccountId(ac.getId());
					ar.setAccountName(ac.getName());
				}
				rpts.add(ar);
			}
		}catch(Throwable t){
			t.printStackTrace();
		}			
		return rpts;				
	}
	
	public boolean updateAccountingReport(List<AccountingReport> rpts){
		try{
			logger.debug("updateAccountingReport("+rpts+")");
			arDAO.updateAccountingReport(rpts);
			return true;
		}catch(Throwable t){
			t.printStackTrace();
			return false;
		}		
	}
	
	// CaseManager Report
	public List<CaseManagerReport> getCaseManagerReportForWeek(Date date, User user){
		List<CaseManagerReport> rptList = new ArrayList<CaseManagerReport>();		
		try{
			logger.debug("getCaseManagerReportForWeek("+date+", "+user+")");		
			Calendar cal = new GregorianCalendar();
			cal.setTime(date);
			int offsetToSunday = 1 - cal.get(Calendar.DAY_OF_WEEK);
			cal.add(Calendar.DATE, offsetToSunday);
			int numDays = 6;
			for (int i=0; i<numDays; i++){
				cal.add(Calendar.DATE, 1);
				CaseManagerReport rpt = cmDAO.getCaseManagerReport(cal.getTime());
				if (rpt == null){
					rpt = new CaseManagerReport();
					rpt.setDate(cal.getTime());
					rpt.setUserId(user.getUsername());
				}
				rptList.add(rpt);			
			}	
			CaseManagerReport sum = getSumValue(rptList);
			CaseManagerReport avg = getAvgValue(sum, numDays);
			rptList.add(sum);
			rptList.add(avg);
		}catch(Throwable t){
			t.printStackTrace();
		}
		return rptList;
	}
	
	private CaseManagerReport getSumValue(List<CaseManagerReport> rptList) {
		CaseManagerReport sum = new CaseManagerReport();
		sum.setDate(null);
		sum.setDateStr(CaseManagerReport.TOTAL);

		int numConsumer = 0;
		int numVisits = 0;
		int numL2Ref = 0;
		int numL3Ref = 0;
		int numL2Seen = 0;
		int numL3Seen = 0;
		int numPCPReachedOut = 0;
		int numPCPAppts = 0;
		int numCM = 0;
		int numEpisodeOpened = 0;
		int numEpisodeClosed = 0;
		int numHPOnCaseloadDueToExpire = 0;
		int numOutsideMeeting = 0;
		int numVisitNextWeek = 0;
		int numNonCompliantChart = 0;
				
		for (CaseManagerReport rpt : rptList){
			numConsumer += rpt.getNumConsumer();
			numVisits += rpt.getNumVisits();
			numL2Ref += rpt.getNumL2Ref();
			numL3Ref += rpt.getNumL3Ref();
			numL2Seen += rpt.getNumL2Seen();
			numL3Seen += rpt.getNumL3Seen();
			numPCPReachedOut += rpt.getNumPCPReachedOut();
			numPCPAppts += rpt.getNumPCPAppts();
			numCM += rpt.getNumCM();		
			numEpisodeOpened += rpt.getNumEpisodeOpened();
			numEpisodeClosed += rpt.getNumEpisodeClosed();
			numHPOnCaseloadDueToExpire += rpt.getNumHPOnCaseloadDueToExpire();
			numOutsideMeeting += rpt.getNumOutsideMeeting();
			numVisitNextWeek += rpt.getNumVisitNextWeek();
			numNonCompliantChart += rpt.getNumNonCompliantChart();			
		}
		sum.setNumConsumer(numConsumer);
		sum.setNumVisits(numVisits);
		sum.setNumL2Ref(numL2Ref);
		sum.setNumL3Ref(numL3Ref);
		sum.setNumL2Seen(numL2Seen);
		sum.setNumL3Seen(numL3Seen);
		sum.setNumPCPReachedOut(numPCPReachedOut);
		sum.setNumPCPAppts(numPCPAppts);
		sum.setNumCM(numCM);
		sum.setNumEpisodeOpened(numEpisodeOpened);
		sum.setNumEpisodeClosed(numEpisodeClosed);
		sum.setNumHPOnCaseloadDueToExpire(numHPOnCaseloadDueToExpire);
		sum.setNumOutsideMeeting(numOutsideMeeting);
		sum.setNumVisitNextWeek(numVisitNextWeek);
		sum.setNumNonCompliantChart(numNonCompliantChart);
		return sum;
	}
	
	private CaseManagerReport getAvgValue(CaseManagerReport sum, int count) {
		CaseManagerReport avg = new CaseManagerReport();
		avg.setDate(null);
		avg.setDateStr(CaseManagerReport.AVG);
		avg.setNumConsumer(sum.getNumConsumer()/count);
		avg.setNumVisits(sum.getNumVisits()/count);
		avg.setNumL2Ref(sum.getNumL2Ref()/count);		
		avg.setNumL3Ref(sum.getNumL3Ref()/count);
		avg.setNumL2Seen(sum.getNumL2Seen()/count);
		avg.setNumL3Seen(sum.getNumL3Seen()/count);
		avg.setNumPCPReachedOut(sum.getNumPCPReachedOut()/count);
		avg.setNumPCPAppts(sum.getNumPCPAppts()/count);
		avg.setNumCM(sum.getNumCM()/count);
		avg.setNumEpisodeOpened(sum.getNumEpisodeOpened()/count);
		avg.setNumEpisodeClosed(sum.getNumEpisodeClosed()/count);
		avg.setNumHPOnCaseloadDueToExpire(sum.getNumHPOnCaseloadDueToExpire()/count);
		avg.setNumOutsideMeeting(sum.getNumOutsideMeeting()/count);
		avg.setNumVisitNextWeek(sum.getNumVisitNextWeek()/count);
		avg.setNumNonCompliantChart(sum.getNumNonCompliantChart()/count);				
		return avg;
	}

	public CaseManagerNote getCaseManagerNote(Date date, User user){
		CaseManagerNote note = null;
		try{
			logger.debug("getCaseManagerNote("+date+", "+user+")");		
			Calendar cal = new GregorianCalendar();
			cal.setTime(date);
			int offsetToSunday = 1 - cal.get(Calendar.DAY_OF_WEEK);
			cal.add(Calendar.DATE, offsetToSunday);
			note = cmDAO.getCaseManagerNote(cal.getTime());
			if (note == null){
				note = new CaseManagerNote();
				note.setDateOfWeek(cal.getTime());
				note.setUserId(user.getUsername());
			}
		}catch(Throwable t){
			t.printStackTrace();
		}
		return note;
	}
	
	public boolean updateCaseManagerReport(List<CaseManagerReport> rpts, CaseManagerNote note, User user){
		try{
			logger.debug("updateCaseManagerReport("+rpts+", "+note+", "+user+")");
			for (CaseManagerReport r : rpts){
				if (r.getDate() != null){
					cmDAO.updateCaseManagerReport(r, user.getUsername());					
				}
			}
			cmDAO.updateCaseManagerNote(note);
			return true;
		}catch(Throwable t){
			t.printStackTrace();
			return false;
		}		
	}
	
	
}
