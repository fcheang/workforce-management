package com.suntek.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

import com.suntek.model.Account;
import com.suntek.model.AccountingReport;

public class AccountingReportDAOImpl extends SimpleJdbcDaoSupport {
	private AccountingReportRowMapper btRm = new AccountingReportRowMapper();
	private AccountRowMapper acRm = new AccountRowMapper();
	
	public AccountingReportDAOImpl(){		
	}

	public List<Account> getAllAccount(){
		String sql = "select id, name from account_name order by id";
		return getSimpleJdbcTemplate().query(sql, acRm);
	}
	
	public List<AccountingReport> getAccountingReport(Date dateOfWeek) {
		String sql = "SELECT ar.dateOfWeek, ar.accountId, n.name, ar.numBills, ar.amtPaid, ar.numChecks, ar.amtChecks, "+
		             "       ar.amtCashCollected, ar.numCheckDeposited, ar.amtDeposited "+
				     "  from accounting_report ar, account_name n "+
				     " where ar.accountId = n.id and ar.dateOfWeek = ? "+
				     "order by ar.accountId";
		return getSimpleJdbcTemplate().query(sql, btRm, getSqlDate(dateOfWeek));
	}

	public AccountingReport getAccountingReport(Date dateOfWeek, int id) {
		String sql = "SELECT ar.dateOfWeek, ar.accountId, n.name, ar.numBills, ar.amtPaid, ar.numChecks, ar.amtChecks, "+
		             "       ar.amtCashCollected, ar.numCheckDeposited, ar.amtDeposited "+
				     "  from accounting_report ar, account_name n "+
				     " where ar.accountId = n.id and ar.dateOfWeek = ? and ar.accountId = ? "+
				     " order by ar.accountId";
		List<AccountingReport> rpts = getSimpleJdbcTemplate().query(sql, btRm, getSqlDate(dateOfWeek), id);
		if (rpts.size() > 0){
			return rpts.get(0);
		}else{
			return null;
		}
	}
	
	private boolean accountingReportExist(Date dateOfWeek, int accountId) {
		String sql = "SELECT count(*) from accounting_report where dateOfWeek = ? and accountId = ?";
		return getSimpleJdbcTemplate().queryForInt(sql, getSqlDate(dateOfWeek), accountId) > 0;
	}

	public boolean updateAccountingReport(List<AccountingReport> rpts) {
		int size = rpts.size();
		for (int i=0; i<size; i++){
			AccountingReport rpt = rpts.get(i);
			if (accountingReportExist(rpt.getDateOfWeek(), rpt.getAccountId())){
				String sql = "update accounting_report set numBills = ?, amtPaid = ?, numChecks = ?, amtChecks = ?, "+
				             "amtCashCollected = ?, numCheckDeposited = ?, amtDeposited = ? " +
				             "where dateOfWeek = ? and accountId = ?";
				getSimpleJdbcTemplate().update(sql, rpt.getNumBills(), rpt.getAmtPaid(), rpt.getNumChecks(), rpt.getAmtChecks(),
						rpt.getAmtCashCollected(), rpt.getNumCheckDeposited(), rpt.getAmtDeposited(),
						getSqlDate(rpt.getDateOfWeek()), rpt.getAccountId()); 
			}else{
				String sql = "insert into accounting_report (" +
						"dateOfWeek, accountId, numBills, amtPaid, numChecks, amtChecks, "+
						"amtCashCollected, numCheckDeposited, amtDeposited ) values ( " +
						"?, ?, ?, ?, ?, ?, ?, ?, ? )";
				getSimpleJdbcTemplate().update(sql, getSqlDate(rpt.getDateOfWeek()), rpt.getAccountId(), rpt.getNumBills(), rpt.getAmtPaid(), rpt.getNumChecks(), rpt.getAmtChecks(),
						rpt.getAmtCashCollected(), rpt.getNumCheckDeposited(), rpt.getAmtDeposited());
			}
		}
		return true;
	}
	
	private java.sql.Date getSqlDate(Date date){
		return new java.sql.Date(date.getTime());		
	}

	private class AccountRowMapper implements ParameterizedRowMapper<Account>{		
		public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
			Account r = new Account();
			r.setId(rs.getInt(1));
			r.setName(rs.getString(2));
			return r;
		}
	} 
	
	private class AccountingReportRowMapper implements ParameterizedRowMapper<AccountingReport>{		
		public AccountingReport mapRow(ResultSet rs, int rowNum) throws SQLException {
			AccountingReport r = new AccountingReport();
			r.setDateOfWeek(rs.getDate(1));
			r.setAccountId(rs.getInt(2));
			r.setAccountName(rs.getString(3));
			r.setNumBills(rs.getInt(4));
			r.setAmtPaid(rs.getDouble(5));
			r.setNumChecks(rs.getInt(6));
			r.setAmtChecks(rs.getDouble(7));
			r.setAmtCashCollected(rs.getDouble(8));
			r.setNumCheckDeposited(rs.getInt(9));
			r.setAmtDeposited(rs.getDouble(10));
			return r;
		}
	}
	
}
