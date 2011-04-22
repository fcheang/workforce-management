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
		try{
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
		}catch(RuntimeException e){
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Worksheet> getWorksheet(String clinic, Date date){
		String sql = 
			"select w.empId, concat(e.firstName, ' ', e.lastName), w.date, w.clinic, w.hrs_worked, " +
			"w.county_seen, w.ccc_seen, w.hmo_seen, w.other_seen, " +
			"w.county_face_min, w.county_other_min, " +
			"w.ccc_face_min, w.ccc_other_min, " +
			"w.hmo_face_min, w.other_face_min, " +
			"w.num_scheduled, w.num_noshow, w.num_cancel, w.num_new, w.num_dropin " +
			"from worksheet w, employee e " +
			"where w.clinic = ? and w.date = ? and w.empId = e.empId";		
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
			e.setEmpName(rs.getString(2));
			e.setDate(rs.getDate(3));
			e.setClinic(rs.getString(4));
			e.setHrsWorked(rs.getDouble(5));
			
			e.setCountySeen(rs.getInt(6));
			e.setCccSeen(rs.getInt(7));
			e.setHmoSeen(rs.getInt(8));
			e.setOtherSeen(rs.getInt(9));
			
			e.setCountyFaceMin(rs.getInt(10));
			e.setCountyOtherMin(rs.getInt(11));
			
			e.setCccFaceMin(rs.getInt(12));
			e.setCccOtherMin(rs.getInt(13));
			
			e.setHmoFaceMin(rs.getInt(14));
			e.setOtherFaceMin(rs.getInt(15));
			
			e.setNumScheduled(rs.getInt(16));
			e.setNumNoShow(rs.getInt(17));
			e.setNumCancel(rs.getInt(18));
			e.setNumNew(rs.getInt(19));
			e.setNumDropin(rs.getInt(20));			
			return e;
		}
	}	
	
}
