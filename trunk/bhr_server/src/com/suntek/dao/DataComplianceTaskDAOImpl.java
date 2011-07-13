package com.suntek.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import com.suntek.model.DataComplianceTask;

public class DataComplianceTaskDAOImpl extends SimpleJdbcDaoSupport {

	private DataComplianceTaskRowMapper dctRm = new DataComplianceTaskRowMapper();

	public DataComplianceTaskDAOImpl(){		
	}

	public DataComplianceTask getDataComplianceTask(Date date, String userId) {
		String sql = "SELECT userId, date, eps_opened, bill_errs_found, charts_reviewed, chart_errs_found, items_entered from data_compliance_task where userId = ? and date = ?";
		List<DataComplianceTask> tasks = getSimpleJdbcTemplate().query(sql, dctRm, userId, date);
		if (tasks.size() > 0){
			return tasks.get(0);
		}else{
			return null;
		}
	}

	private boolean dataComplianceTaskExist(String userId, Date date) {
		String sql = "SELECT count(*) from data_compliance_task where userId = ? and date = ?";
		return getSimpleJdbcTemplate().queryForInt(sql, userId, date) > 0;
	}

	public boolean updateDataComplianceTask(List<DataComplianceTask> tasks) {
		int size = tasks.size();
		for (int i=0; i<size; i++){
			DataComplianceTask task = tasks.get(i);
			if (dataComplianceTaskExist(task.getUserId(), task.getDate())){
				String sql = "update data_compliance_task set eps_opened = ?, bill_errs_found = ?, charts_reviewed = ?, chart_errs_found = ?, items_entered = ? where userId = ? and date = ?";
				getSimpleJdbcTemplate().update(sql, task.getEpsOpened(), task.getBillErrsFound(), task.getChartsReviewed(), task.getChartErrsFound(), task.getItemsEntered(), task.getUserId(), task.getDate()); 
			}else{
				String sql = "insert into data_compliance_task (userId, date, eps_opened, bill_errs_found, charts_reviewed, chart_errs_found, items_entered) values (?, ?, ?, ?, ?, ?, ?)";
				getSimpleJdbcTemplate().update(sql, task.getUserId(), task.getDate(), task.getEpsOpened(), task.getBillErrsFound(), task.getChartsReviewed(), task.getChartErrsFound(), task.getItemsEntered());				
			}
		}
		return true;
	}
	
	private class DataComplianceTaskRowMapper implements ParameterizedRowMapper<DataComplianceTask>{		
		public DataComplianceTask mapRow(ResultSet rs, int rowNum) throws SQLException {
			DataComplianceTask c = new DataComplianceTask();
			c.setUserId(rs.getString(1));
			c.setDate(rs.getDate(2));
			c.setEpsOpened(rs.getInt(3));
			c.setBillErrsFound(rs.getInt(4));
			c.setChartsReviewed(rs.getInt(5));
			c.setChartErrsFound(rs.getInt(6));
			c.setItemsEntered(rs.getInt(7));
			return c;
		}
	}
	
}
