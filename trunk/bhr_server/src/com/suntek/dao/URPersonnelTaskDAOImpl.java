package com.suntek.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import com.suntek.model.URPersonnelTask;

public class URPersonnelTaskDAOImpl extends SimpleJdbcDaoSupport {

	private URPersonnelTaskRowMapper urRm = new URPersonnelTaskRowMapper();

	public URPersonnelTaskDAOImpl(){		
	}

	public URPersonnelTask getURPersonnelTask(Date date, String userId) {
		String sql = "SELECT userId, date, charts_reviewed, discharge_done, bill_svc_provided, eps_opened, charts_transfered, ru_completed, md_charts_audited, mhs_charts_audited, tx_team_mtgs from ur_personnel_task where userId = ? and date = ?";
		List<URPersonnelTask> tasks = getSimpleJdbcTemplate().query(sql, urRm, userId, date);
		if (tasks.size() > 0){
			return tasks.get(0);
		}else{
			return null;
		}
	}

	private boolean URPersonnelTaskExist(String userId, Date date) {
		String sql = "SELECT count(*) from ur_personnel_task where userId = ? and date = ?";
		return getSimpleJdbcTemplate().queryForInt(sql, userId, date) > 0;
	}

	public boolean updateURPersonnelTask(List<URPersonnelTask> tasks) {
		int size = tasks.size();
		for (int i=0; i<size; i++){
			URPersonnelTask task = tasks.get(i);
			if (URPersonnelTaskExist(task.getUserId(), task.getDate())){
				String sql = "update ur_personnel_task set charts_reviewed = ?, discharge_done = ?, bill_svc_provided = ?, eps_opened = ?, charts_transfered = ?, ru_completed = ?, md_charts_audited = ?, mhs_charts_audited = ?, tx_team_mtgs = ? where userId = ? and date = ?";
				getSimpleJdbcTemplate().update(sql, task.getChartsReviewed(), task.getDischargeDone(), task.getBillSvcProvided(), task.getEpsOpened(), task.getChartsTransfered(), task.getRuCompleted(), task.getMdChartsAudited(), task.getMhsChartsAudited(), task.getTxTeamMtgs(), task.getUserId(), task.getDate()); 
			}else{
				String sql = "insert into ur_personnel_task (userId, date, charts_reviewed, discharge_done, bill_svc_provided, eps_opened, charts_transfered, ru_completed, md_charts_audited, mhs_charts_audited, tx_team_mtgs ) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				getSimpleJdbcTemplate().update(sql, task.getUserId(), task.getDate(), task.getChartsReviewed(), task.getDischargeDone(), task.getBillSvcProvided(), task.getEpsOpened(), task.getChartsTransfered(), task.getRuCompleted(), task.getMdChartsAudited(), task.getMhsChartsAudited(), task.getTxTeamMtgs());				
			}
		}
		return true;
	}
	
	private class URPersonnelTaskRowMapper implements ParameterizedRowMapper<URPersonnelTask>{		
		public URPersonnelTask mapRow(ResultSet rs, int rowNum) throws SQLException {
			URPersonnelTask c = new URPersonnelTask();
			c.setUserId(rs.getString(1));
			c.setDate(rs.getDate(2));
			c.setChartsReviewed(rs.getInt(3));
			c.setDischargeDone(rs.getInt(4));
			c.setBillSvcProvided(rs.getInt(5));
			c.setEpsOpened(rs.getInt(6));
			c.setChartsTransfered(rs.getInt(7));
			c.setRuCompleted(rs.getInt(8));
			c.setMdChartsAudited(rs.getInt(9));
			c.setMhsChartsAudited(rs.getInt(10));
			c.setTxTeamMtgs(rs.getInt(11));
			return c;
		}
	}
	
}
