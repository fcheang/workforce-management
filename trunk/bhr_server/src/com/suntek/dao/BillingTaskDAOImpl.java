package com.suntek.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import com.suntek.model.BillingTask;

public class BillingTaskDAOImpl extends SimpleJdbcDaoSupport {

	private BillingTaskRowMapper btRm = new BillingTaskRowMapper();

	public BillingTaskDAOImpl(){		
	}

	public BillingTask getBillingTask(Date date, String userId) {
		String sql = "SELECT userId, date, claims_dropped_c, claims_dropped_p, errors, denials, followups, appeals, hmo_amt, ac_amt, ccc_amt, private_amt, other_amt, other_task1, other_task2, other_task3 from billing_task where userId = ? and date = ?";
		List<BillingTask> tasks = getSimpleJdbcTemplate().query(sql, btRm, userId, date);
		if (tasks.size() > 0){
			return tasks.get(0);
		}else{
			return null;
		}
	}

	private boolean billingTaskExist(String userId, Date date) {
		String sql = "SELECT count(*) from billing_task where userId = ? and date = ?";
		return getSimpleJdbcTemplate().queryForInt(sql, userId, date) > 0;
	}

	public boolean updateBillingTask(List<BillingTask> tasks) {
		int size = tasks.size();
		for (int i=0; i<size; i++){
			BillingTask task = tasks.get(i);
			if (billingTaskExist(task.getUserId(), task.getDate())){
				String sql = "update billing_task set claims_dropped_c = ?, claims_dropped_p = ?, errors = ?, denials = ?, followups = ?, appeals = ?, hmo_amt = ?, ac_amt = ?, ccc_amt = ?, private_amt = ?, other_amt = ?, other_task1 = ?, other_task2 = ?, other_task3 where userId = ? and date = ?";
				getSimpleJdbcTemplate().update(sql, task.getClaimsDroppedC(), task.getClaimsDroppedP(), task.getErrors(), task.getDenials(), task.getFollowUps(), task.getAppeals(), task.getHmoAmt(), task.getAcAmt(), task.getCccAmt(), task.getPrivateAmt(), task.getOtherAmt(), task.getOtherTask1(), task.getOtherTask2(), task.getOtherTask3(), task.getUserId(), task.getDate()); 
			}else{
				String sql = "insert into billing_task (userId, date, claims_dropped_c, claims_dropped_p, errors, denials, followups, appeals, hmo_amt, ac_amt, ccc_amt, private_amt, other_amt, other_task1, other_task2, other_task3 ) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				getSimpleJdbcTemplate().update(sql, task.getUserId(), task.getDate(), task.getClaimsDroppedC(), task.getClaimsDroppedP(), task.getErrors(), task.getDenials(), task.getFollowUps(), task.getAppeals(), task.getHmoAmt(), task.getAcAmt(), task.getCccAmt(), task.getPrivateAmt(), task.getOtherAmt(), task.getOtherTask1(), task.getOtherTask2(), task.getOtherTask3());				
			}
		}
		return true;
	}
	
	private class BillingTaskRowMapper implements ParameterizedRowMapper<BillingTask>{		
		public BillingTask mapRow(ResultSet rs, int rowNum) throws SQLException {
			BillingTask c = new BillingTask();
			c.setUserId(rs.getString(1));
			c.setDate(rs.getDate(2));
			c.setClaimsDroppedC(rs.getInt(3));
			c.setClaimsDroppedP(rs.getInt(4));
			c.setErrors(rs.getInt(5));
			c.setDenials(rs.getInt(6));
			c.setFollowUps(rs.getInt(7));
			c.setAppeals(rs.getInt(8));
			c.setHmoAmt(rs.getDouble(9));
			c.setAcAmt(rs.getDouble(10));
			c.setCccAmt(rs.getDouble(11));
			c.setPrivateAmt(rs.getDouble(12));
			c.setOtherAmt(rs.getDouble(13));
			c.setOtherTask1(rs.getString(14));
			c.setOtherTask2(rs.getString(15));
			c.setOtherTask3(rs.getString(16));
			return c;
		}
	}
	
}
