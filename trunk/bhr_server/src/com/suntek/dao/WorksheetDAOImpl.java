package com.suntek.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.dao.DuplicateKeyException;

import com.suntek.model.Worksheet;

public class WorksheetDAOImpl extends SimpleJdbcDaoSupport {

	public WorksheetDAOImpl(){		
	}
	
	public Worksheet createWorksheet(Worksheet ws){
		try{
			String sql = 
				"insert into worksheet (providerId, clinic, date, hrs_worked, "+
				"county_seen, ccc_seen, hmo_seen, other_seen, "+
				"county_face_min, county_other_min, "+
				"ccc_face_min, ccc_other_min, "+
				"hmo_face_min, other_face_min, "+
				"num_scheduled, num_noshow, num_cancel, num_new, num_dropin, daily_salary, "+
				"enteredBy, dateEntered) "+
				"values "+
				"(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			if (super.getSimpleJdbcTemplate().update(sql, 
					ws.getProviderId(), ws.getClinic(), ws.getDate(), ws.getHrsWorked(), 
					ws.getCountySeen(), ws.getCccSeen(), ws.getHmoSeen(), ws.getOtherSeen(),
					ws.getCountyFaceMin(), ws.getCountyOtherMin(),
					ws.getCccFaceMin(), ws.getCccOtherMin(),
					ws.getHmoFaceMin(), ws.getOtherFaceMin(),
					ws.getNumScheduled(), ws.getNumNoShow(), ws.getNumCancel(), ws.getNumNew(), ws.getNumDropin(), ws.getDailySalary(),
					ws.getEnteredBy(), new Timestamp(new Date().getTime())) > 0){
				return ws;
			}else{
				return null;
			}
		}catch(DuplicateKeyException e){
			throw new RuntimeException("Worksheet already exist for employee "+ws.getProviderName());
		}
	}
	
	public List<Worksheet> getWorksheet(String clinic, Date date){
		String sql = 
			"select w.providerId, e.name, w.date, w.clinic, w.hrs_worked, " +
			"w.county_seen, w.ccc_seen, w.hmo_seen, w.other_seen, " +
			"w.county_face_min, w.county_other_min, " +
			"w.ccc_face_min, w.ccc_other_min, " +
			"w.hmo_face_min, w.other_face_min, " +
			"w.num_scheduled, w.num_noshow, w.num_cancel, w.num_new, w.num_dropin, w.daily_salary, "+
			"w.enteredBy, w.dateEntered " +
			"from worksheet w, provider e " +
			"where w.clinic = ? and w.date = ? and w.providerId = e.providerId "+
			"order by e.name";		
		return getSimpleJdbcTemplate().query(sql, new WorksheetRowMapper(), clinic, date);
	}
	
	public List<Worksheet> getWorksheetForClinicAndDateRange(String clinic, Date sd, Date ed){
		String sql =
			"select "+
			"w.providerId, p.name, w.date, w.clinic, w.hrs_worked, w.county_seen, w.ccc_seen, "+
			"w.hmo_seen, w.other_seen, w.county_face_min, w.county_other_min, "+
			"w.ccc_face_min, w.ccc_other_min, w.hmo_face_min, w.other_face_min, "+
			"w.num_scheduled, w.num_noshow, w.num_cancel, w.num_new, w.num_dropin, "+
			"w.daily_salary, w.enteredBy, w.dateEntered "+
			"from worksheet w, provider p "+
			"where w.providerId = p.providerId and w.date >= ? and w.date <= ? and w.clinic = ? "+
			"order by w.date, w.clinic";
		return getSimpleJdbcTemplate().query(sql, new WorksheetRowMapper(), sd, ed, clinic);			
	}

	public List<Worksheet> getWorksheetForProviderAndDateRange(int providerId, Date sd, Date ed){
		String sql =
			"select "+
			"w.providerId, p.name, w.date, w.clinic, w.hrs_worked, w.county_seen, w.ccc_seen, "+
			"w.hmo_seen, w.other_seen, w.county_face_min, w.county_other_min, "+
			"w.ccc_face_min, w.ccc_other_min, w.hmo_face_min, w.other_face_min, "+
			"w.num_scheduled, w.num_noshow, w.num_cancel, w.num_new, w.num_dropin, "+
			"w.daily_salary, w.enteredBy, w.dateEntered "+
			"from worksheet w, provider p "+
			"where w.providerId = p.providerId and w.date >= ? and w.date <= ? and w.providerId = ? "+
			"order by w.date, p.name";
		return getSimpleJdbcTemplate().query(sql, new WorksheetRowMapper(), sd, ed, providerId);			
	}

	public List<Worksheet> getWorksheetForClinicAndProviderAndDateRange(String clinic, int providerId, Date sd, Date ed){
		String sql =
			"select "+
			"w.providerId, p.name, w.date, w.clinic, w.hrs_worked, w.county_seen, w.ccc_seen, "+
			"w.hmo_seen, w.other_seen, w.county_face_min, w.county_other_min, "+
			"w.ccc_face_min, w.ccc_other_min, w.hmo_face_min, w.other_face_min, "+
			"w.num_scheduled, w.num_noshow, w.num_cancel, w.num_new, w.num_dropin, "+
			"w.daily_salary, w.enteredBy, w.dateEntered "+
			"from worksheet w, provider p"+
			"where w.providerId = p.providerId and w.date >= ? and w.date <= ? and w.clinic = ? and w.providerId = ? "+
			"order by w.date, w.clinic, p.name";
		return getSimpleJdbcTemplate().query(sql, new WorksheetRowMapper(), sd, ed, clinic, providerId);			
	}

	public List<Worksheet> getWorksheetForClinicAndDateRangeGroupByDate(String clinic, Date sd, Date ed){
		String sql =
			"select "+
			"0, 'multiple', date, clinic, sum(hrs_worked), sum(county_seen), sum(ccc_seen), "+
			"sum(hmo_seen), sum(other_seen), sum(county_face_min), sum(county_other_min), "+
			"sum(ccc_face_min), sum(ccc_other_min), sum(hmo_face_min), sum(other_face_min), "+
			"sum(num_scheduled), sum(num_noshow), sum(num_cancel), sum(num_new), sum(num_dropin), "+
			"sum(daily_salary), 'multiple', now() "+
			"from worksheet "+
			"where date >= ? and date <= ? and clinic = ? "+
			"group by clinic, date "+
			"order by date asc";
		return getSimpleJdbcTemplate().query(sql, new WorksheetRowMapper(), sd, ed, clinic);			
	}

	public List<Worksheet> getWorksheetForProviderAndDateRangeGroupByDate(int providerId, Date sd, Date ed){
		String sql =
			"select "+
			"providerId, 'multiple', date, 'all', sum(hrs_worked), sum(county_seen), sum(ccc_seen), "+
			"sum(hmo_seen), sum(other_seen), sum(county_face_min), sum(county_other_min), "+
			"sum(ccc_face_min), sum(ccc_other_min), sum(hmo_face_min), sum(other_face_min), "+
			"sum(num_scheduled), sum(num_noshow), sum(num_cancel), sum(num_new), sum(num_dropin), "+
			"sum(daily_salary), 'multiple', now() "+
			"from worksheet "+
			"where date >= ? and date <= ? and providerId = ? "+
			"group by providerId, date "+
			"order by date asc";
		return getSimpleJdbcTemplate().query(sql, new WorksheetRowMapper(), sd, ed, providerId);			
	}

	public List<Worksheet> getWorksheetForClinicAndProviderAndDateRangeGroupByDate(String clinic, int providerId, Date sd, Date ed){
		String sql =
			"select "+
			"providerId, 'multiple', date, clinic, sum(hrs_worked), sum(county_seen), sum(ccc_seen), "+
			"sum(hmo_seen), sum(other_seen), sum(county_face_min), sum(county_other_min), "+
			"sum(ccc_face_min), sum(ccc_other_min), sum(hmo_face_min), sum(other_face_min), "+
			"sum(num_scheduled), sum(num_noshow), sum(num_cancel), sum(num_new), sum(num_dropin), "+
			"sum(daily_salary), 'multiple', now() "+
			"from worksheet "+
			"where date >= ? and date <= ? and clinic = ? and providerId = ? "+
			"group by providerId, clinic, date "+
			"order by date asc";
		return getSimpleJdbcTemplate().query(sql, new WorksheetRowMapper(), sd, ed, clinic, providerId);			
	}	
	
	public boolean deleteWorksheet(int providerId, String clinic, Date date){
		String sql = "delete from worksheet where providerId = ? and clinic = ? and date = ?";
		return getSimpleJdbcTemplate().update(sql, providerId, clinic, date) > 0;
	}
	
	public boolean updateWorksheet(Worksheet ws){
		String sql = 
			"update worksheet set hrs_worked = ?, "+
			"county_seen = ?, ccc_seen = ?, hmo_seen = ?, other_seen = ?, "+
			"county_face_min = ?, county_other_min = ?, "+
			"ccc_face_min = ?, ccc_other_min = ?, "+
			"hmo_face_min = ?, other_face_min = ?, "+
			"num_scheduled = ?, num_noshow = ?, num_cancel = ?, num_new = ?, num_dropin = ?, daily_salary = ? "+
			"where providerId = ? and clinic = ? and date = ?";
		return super.getSimpleJdbcTemplate().update(sql, ws.getHrsWorked(), 
				ws.getCountySeen(), ws.getCccSeen(), ws.getHmoSeen(), ws.getOtherSeen(),
				ws.getCountyFaceMin(), ws.getCountyOtherMin(),
				ws.getCccFaceMin(), ws.getCccOtherMin(),
				ws.getHmoFaceMin(), ws.getOtherFaceMin(),
				ws.getNumScheduled(), ws.getNumNoShow(), ws.getNumCancel(), ws.getNumNew(), ws.getNumDropin(), ws.getDailySalary(),
				ws.getProviderId(), ws.getClinic(), ws.getDate()) > 0;
	}
	
	private class WorksheetRowMapper implements ParameterizedRowMapper<Worksheet>{
		public Worksheet mapRow(ResultSet rs, int rowNum) throws SQLException {
			Worksheet e = new Worksheet();
			e.setProviderId(rs.getInt(1));	
			e.setProviderName(rs.getString(2));
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
			e.setDailySalary(rs.getDouble(21));
			e.setEnteredBy(rs.getString(22));
			e.setDateEntered(rs.getDate(23));
			return e;
		}
	}	
	
}
