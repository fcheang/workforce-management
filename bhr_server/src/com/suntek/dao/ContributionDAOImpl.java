package com.suntek.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List; 
import java.util.ArrayList;
import java.util.Date;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import com.suntek.model.Contribution; 
import com.suntek.model.ContributionItem; 
import com.suntek.model.User;


public class ContributionDAOImpl extends SimpleJdbcDaoSupport {

	private ContributionRowMapper contRm = new ContributionRowMapper();
	private ContributionItemRowMapper contItemRm = new ContributionItemRowMapper();

	public ContributionDAOImpl(){		
	}

	public Contribution getContribution(Date date, User user) {
		String sql = "SELECT userId, date, hrs_worked, auths_entered, interpreters_ordered, collateral_received, other from contribution where userId = ? and date = ?";
		List<Contribution> rs = getSimpleJdbcTemplate().query(sql, contRm, user.getUsername(), date);
		if (rs.size() > 0){
			return rs.get(0);
		}else{
			return new Contribution(user, date);
		}
	}

	public List<ContributionItem> getContributionItems(Date date, User user) {
		List<ContributionItem> items = new ArrayList<ContributionItem>();
		String sql = "SELECT userId, date, type, private_pay, hmo, ac, ac_child, ccc, ccc_child, sf, other from contribution_item where userId = ? and date = ? and type = ?";
		List<ContributionItem> ic = getSimpleJdbcTemplate().query(sql, contItemRm, user.getUsername(), date, ContributionItem.INCOMING_CALLS);
		List<ContributionItem> oc = getSimpleJdbcTemplate().query(sql, contItemRm, user.getUsername(), date, ContributionItem.OUTGOING_CALLS);
		List<ContributionItem> rc = getSimpleJdbcTemplate().query(sql, contItemRm, user.getUsername(), date, ContributionItem.REMINDER_CALLS);		
		List<ContributionItem> tam = getSimpleJdbcTemplate().query(sql, contItemRm, user.getUsername(), date, ContributionItem.TOTAL_APPTS_MADE);

		if (ic.size() > 0){
			items.add(ic.get(0));
		}else{
			items.add(new ContributionItem(user, date, ContributionItem.INCOMING_CALLS));
		}

		if (oc.size() > 0){
			items.add(oc.get(0));
		}else{
			items.add(new ContributionItem(user, date, ContributionItem.OUTGOING_CALLS));
		}

		if (rc.size() > 0){
			items.add(rc.get(0));
		}else{
			items.add(new ContributionItem(user, date, ContributionItem.REMINDER_CALLS));
		}

		if (tam.size() > 0){
			items.add(tam.get(0));
		}else{
			items.add(new ContributionItem(user, date, ContributionItem.TOTAL_APPTS_MADE));
		}

		return items;
	}

	public boolean updateContribution(Contribution cont) {
		if (contributionExist(cont.getUserId(), cont.getDate())){
			String sql = "update contribution set hrs_worked = ?, auths_entered = ?, interpreters_ordered = ?, collateral_received = ?, other = ? where userId = ? and date = ?";
			return getSimpleJdbcTemplate().update(sql, cont.getHrsWorked(), cont.getAuthsEntered(), cont.getInterpretersOrdered(), cont.getCollateralReceived(), cont.getOther(), cont.getUserId(), cont.getDate()) > 0;
		}else{
			String sql = "insert into contribution (userId, date, hrs_worked, auths_entered, interpreters_ordered, collateral_received, other) values (?, ?, ?, ?, ?, ?, ?)";
			return getSimpleJdbcTemplate().update(sql, cont.getUserId(), cont.getDate(), cont.getHrsWorked(), cont.getAuthsEntered(), cont.getInterpretersOrdered(), cont.getCollateralReceived(), cont.getOther()) > 0;
		}
	}
		
	private boolean contributionExist(String userId, Date date) {
		String sql = "SELECT count(*) from contribution where userId = ? and date = ?";
		return getSimpleJdbcTemplate().queryForInt(sql, userId, date) > 0;
	}

	public boolean updateContributionItems(List items) {
		int size = items.size();
		for (int i=0; i<size; i++){
			ContributionItem item = (ContributionItem)items.get(i);
			if (contributionItemExist(item.getUserId(), item.getDate(), item.getType())){
				String sql = "update contribution_item set private_pay = ?, hmo = ?, ac = ?, ac_child = ?, ccc = ?, ccc_child = ?, sf = ?, other = ? where userId = ? and date = ? and type = ?";
				getSimpleJdbcTemplate().update(sql, item.getPrivatePay(), item.getHmo(), item.getAc(), item.getAcChild(), item.getCcc(), item.getCccChild(), item.getSf(), item.getOther(), item.getUserId(), item.getDate(), item.getType()); 
			}else{
				String sql = "insert into contribution_item (userId, date, type, private_pay, hmo, ac, ac_child, ccc, ccc_child, sf, other) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				getSimpleJdbcTemplate().update(sql, item.getUserId(), item.getDate(), item.getType(), item.getPrivatePay(), item.getHmo(), item.getAc(), item.getAcChild(), item.getCcc(), item.getCccChild(), item.getSf(), item.getOther());				
			}
		}
		return true;
	}
	
	private boolean contributionItemExist(String userId, Date date, String type) {
		String sql = "SELECT count(*) from contribution_item where userId = ? and date = ? and type = ?";
		return getSimpleJdbcTemplate().queryForInt(sql, userId, date, type) > 0;
	}

	private class ContributionRowMapper implements ParameterizedRowMapper<Contribution>{		
		public Contribution mapRow(ResultSet rs, int rowNum) throws SQLException {
			Contribution c = new Contribution();
			c.setUserId(rs.getString(1));
			c.setDate(rs.getDate(2));
			c.setHrsWorked(rs.getDouble(3));
			c.setAuthsEntered(rs.getString(4));
			c.setInterpretersOrdered(rs.getString(5));
			c.setCollateralReceived(rs.getString(6));
			c.setOther(rs.getString(7));
			return c;
		}
	}

	private class ContributionItemRowMapper implements ParameterizedRowMapper<ContributionItem>{		
		public ContributionItem mapRow(ResultSet rs, int rowNum) throws SQLException {
			ContributionItem i = new ContributionItem();
			i.setUserId(rs.getString(1));
			i.setDate(rs.getDate(2));
			i.setType(rs.getString(3));
			i.setPrivatePay(rs.getInt(4));
			i.setHmo(rs.getInt(5));
			i.setAc(rs.getInt(6));
			i.setAcChild(rs.getInt(7));
			i.setCcc(rs.getInt(8));
			i.setCccChild(rs.getInt(9));
			i.setSf(rs.getInt(10));
			i.setOther(rs.getInt(11));
			return i;
		}
	}
	
}
