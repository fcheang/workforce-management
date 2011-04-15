package com.suntek.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

import com.suntek.model.Worksheet;

public class WorksheetDAOImpl extends SimpleJdbcDaoSupport {

	public WorksheetDAOImpl(){		
	}
	
	public Worksheet createWorksheet(Worksheet ws){
		String sql = 
			"insert into worksheet (empId, clinic, date, hrs_worked, "+
			"county_seen, ccc_seen, hmo_seen, other_seen, "+
			"county_face_min, county_other_min, "+
			"ccc_face_min, ccc_other_min, "+
			"hmo_face_min, other_face_min, "+
			"num_scheduled, num_noshow, num_cancel, num_new, num_dropin) "+
			"values "+
			"(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		if (super.getSimpleJdbcTemplate().update(sql, 
				ws.getEmpId(), ws.getClinic(), ws.getDate(), ws.getHrsWorked(), 
				ws.getCountySeen(), ws.getCccSeen(), ws.getHmoSeen(), ws.getOtherSeen(),
				ws.getCountyFaceMin(), ws.getCountyOtherMin(),
				ws.getCccFaceMin(), ws.getCccOtherMin(),
				ws.getHmoFaceMin(), ws.getOtherFaceMin(),
				ws.getNumScheduled(), ws.getNumNoShow(), ws.getNumCancel(), ws.getNumNew(), ws.getNumDropin()) > 0){
			return ws;
		}else{
			return null;
		}
	}
	
	public List<Worksheet> getWorksheet(String clinic, Date date){
		String sql = 
			"select empId, date, clinic, hrs_worked, " +
			"county_seen, ccc_seen, hmo_seen, other_seen, " +
			"county_face_min, county_other_min, " +
			"ccc_face_min, ccc_other_min, " +
			"hmo_face_min, other_face_min, " +
			"num_scheduled, num_noshow, num_cancel, num_new, num_dropin " +
			"from worksheet " +
			"where clinic = ? and date = ?";		
		return getSimpleJdbcTemplate().query(sql, new WorksheetRowMapper(), clinic, date);
	}
	
	public boolean deleteWorksheet(int empId, String clinic, Date date){
		String sql = "delete from worksheet where empId = ? and clinic = ? and date = ?";
		return getSimpleJdbcTemplate().update(sql, empId, clinic, date) > 0;
	}
	
	public boolean updateWorksheet(Worksheet ws){
		String sql = 
			"update worksheet set hrs_worked = ?, "+
			"county_seen = ?, ccc_seen = ?, hmo_seen = ?, other_seen = ?, "+
			"county_face_min = ?, county_other_min = ?, "+
			"ccc_face_min = ?, ccc_other_min = ?, "+
			"hmo_face_min = ?, other_face_min = ?, "+
			"num_scheduled = ?, num_noshow = ?, num_cancel = ?, num_new = ?, num_dropin = ? "+
			"where empId = ? and clinic = ? and date = ?";
		return super.getSimpleJdbcTemplate().update(sql, ws.getHrsWorked(), 
				ws.getCountySeen(), ws.getCccSeen(), ws.getHmoSeen(), ws.getOtherSeen(),
				ws.getCountyFaceMin(), ws.getCountyOtherMin(),
				ws.getCccFaceMin(), ws.getCccOtherMin(),
				ws.getHmoFaceMin(), ws.getOtherFaceMin(),
				ws.getNumScheduled(), ws.getNumNoShow(), ws.getNumCancel(), ws.getNumNew(), ws.getNumDropin(),
				ws.getEmpId(), ws.getClinic(), ws.getDate()) > 0;
	}
	
	private class WorksheetRowMapper implements ParameterizedRowMapper<Worksheet>{
		public Worksheet mapRow(ResultSet rs, int rowNum) throws SQLException {
			Worksheet e = new Worksheet();
			e.setEmpId(rs.getInt(1));	
			e.setDate(rs.getDate(2));
			e.setClinic(rs.getString(3));
			e.setHrsWorked(rs.getInt(4));
			
			e.setCountySeen(rs.getInt(5));
			e.setCccSeen(rs.getInt(6));
			e.setHmoSeen(rs.getInt(7));
			e.setOtherSeen(rs.getInt(8));
			
			e.setCountyFaceMin(rs.getInt(9));
			e.setCountyOtherMin(rs.getInt(10));
			
			e.setCccFaceMin(rs.getInt(11));
			e.setCccOtherMin(rs.getInt(12));
			
			e.setHmoFaceMin(rs.getInt(13));
			e.setOtherFaceMin(rs.getInt(14));
			
			e.setNumScheduled(rs.getInt(15));
			e.setNumNoShow(rs.getInt(16));
			e.setNumCancel(rs.getInt(17));
			e.setNumNew(rs.getInt(18));
			e.setNumDropin(rs.getInt(19));			
			return e;
		}
	}	
	
}
