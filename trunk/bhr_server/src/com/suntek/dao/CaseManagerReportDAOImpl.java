package com.suntek.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List; 
import java.util.Date;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import com.suntek.model.CaseManagerNote; 
import com.suntek.model.CaseManagerReport; 


public class CaseManagerReportDAOImpl extends SimpleJdbcDaoSupport {

	private CaseManagerReportRowMapper rptRm = new CaseManagerReportRowMapper();
	private CaseManagerNoteRowMapper noteRm = new CaseManagerNoteRowMapper();

	public CaseManagerReportDAOImpl(){		
	}

	public CaseManagerNote getCaseManagerNote(Date date) {
		String sql = "SELECT userId, dateOfWeek, plan, action, assistanceNeeded, plansForNextWeek, other from CaseManagerNote where dateOfWeek = ?";
		List<CaseManagerNote> rs = getSimpleJdbcTemplate().query(sql, noteRm, date);
		if (rs.size() > 0){
			return rs.get(0);
		}else{
			return null;
		}
	}

	public CaseManagerReport getCaseManagerReport(Date date) {
		String sql = "SELECT userId, date, numConsumer, numVisits, numL2Ref, numL3Ref, numL2Seen, numL3Seen, numPCPReachedOut, numPCPAppts, numCM, numEpisodeOpened, "+
					 "numEpisodeClosed, numHPOnCaseloadDueToExpire, numOutsideMeeting, numVisitNextWeek, numNonCompliantChart "+
					 "from CaseManagerReport where date = ?";
		List<CaseManagerReport> rs = getSimpleJdbcTemplate().query(sql, rptRm, date);
		if (rs.size() > 0){
			return rs.get(0);			
		}else{
			return null;
		}
	}

	public boolean updateCaseManagerNote(CaseManagerNote note) {
		if (CaseManagerNoteExist(note.getDateOfWeek())){
			String sql = "update CaseManagerNote set plan = ?, action = ?, assistanceNeeded = ?, plansForNextWeek = ?, other = ?, userId = ? where dateOfWeek = ?";
			return getSimpleJdbcTemplate().update(sql, note.getPlan(), note.getAction(), note.getAssistanceNeeded(), note.getPlansForNextWeek(), note.getOther(), note.getUserId(), note.getDateOfWeek()) > 0;
		}else{
			String sql = "insert into CaseManagerNote (userId, dateOfWeek, plan, action, assistanceNeeded, plansForNextWeek, other) values (?, ?, ?, ?, ?, ?, ?)";
			return getSimpleJdbcTemplate().update(sql, note.getUserId(), note.getDateOfWeek(), note.getPlan(), note.getAction(), note.getAssistanceNeeded(), note.getPlansForNextWeek(), note.getOther()) > 0;
		}
	}
		
	private boolean CaseManagerNoteExist(Date date) {
		String sql = "SELECT count(*) from CaseManagerNote where dateOfWeek = ?";
		return getSimpleJdbcTemplate().queryForInt(sql, date) > 0;
	}

	public boolean updateCaseManagerReport(CaseManagerReport item, String userId) {
		if (CaseManagerReportExist(item.getDate())){
			String sql = "update CaseManagerReport set userId = ?, numConsumer = ?, numVisits = ?, numL2Ref = ?, numL3Ref = ?, numL2Seen = ?, numL3Seen = ?, numPCPReachedOut = ?, numPCPAppts = ?, "+
						 "numCM = ?, numEpisodeOpened = ?, numEpisodeClosed = ?, numHPOnCaseloadDueToExpire = ?, numOutsideMeeting = ?, numVisitNextWeek = ?, numNonCompliantChart = ? "+
						 "where date = ?";
			getSimpleJdbcTemplate().update(sql, userId, item.getNumConsumer(), item.getNumVisits(), item.getNumL2Ref(), item.getNumL3Ref(), item.getNumL2Seen(), item.getNumL3Seen(), 
					item.getNumPCPReachedOut(), item.getNumPCPAppts(), item.getNumCM(), item.getNumEpisodeOpened(), item.getNumEpisodeClosed(), item.getNumHPOnCaseloadDueToExpire(), 
					item.getNumOutsideMeeting(), item.getNumVisitNextWeek(), item.getNumNonCompliantChart(), item.getDate()); 
		}else{
			String sql = "insert into CaseManagerReport (userId, date, numConsumer, numVisits, numL2Ref, numL3Ref, numL2Seen, numL3Seen, numPCPReachedOut, numPCPAppts, "+
						 "numCM, numEpisodeOpened, numEpisodeClosed, numHPOnCaseloadDueToExpire, numOutsideMeeting, numVisitNextWeek, numNonCompliantChart) values "+
						 "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			getSimpleJdbcTemplate().update(sql, userId, item.getDate(), item.getNumConsumer(), item.getNumVisits(), item.getNumL2Ref(), item.getNumL3Ref(), 
					item.getNumL2Seen(), item.getNumL3Seen(), item.getNumPCPReachedOut(), item.getNumPCPAppts(), item.getNumCM(), item.getNumEpisodeOpened(), 
					item.getNumEpisodeClosed(), item.getNumHPOnCaseloadDueToExpire(), 
					item.getNumOutsideMeeting(), item.getNumVisitNextWeek(), item.getNumNonCompliantChart());				
		}		
		return true;
	}
		
	private boolean CaseManagerReportExist(Date date) {
		String sql = "SELECT count(*) from CaseManagerReport where date = ?";
		return getSimpleJdbcTemplate().queryForInt(sql, date) > 0;
	}
	
	private class CaseManagerNoteRowMapper implements ParameterizedRowMapper<CaseManagerNote>{		
		public CaseManagerNote mapRow(ResultSet rs, int rowNum) throws SQLException {
			CaseManagerNote c = new CaseManagerNote();
			c.setUserId(rs.getString(1));
			c.setDateOfWeek(rs.getDate(2));
			c.setPlan(rs.getString(3));
			c.setAction(rs.getString(4));
			c.setAssistanceNeeded(rs.getString(5));
			c.setPlansForNextWeek(rs.getString(6));
			c.setOther(rs.getString(7));
			return c;
		}
	}

	private class CaseManagerReportRowMapper implements ParameterizedRowMapper<CaseManagerReport>{		
		public CaseManagerReport mapRow(ResultSet rs, int rowNum) throws SQLException {
			CaseManagerReport i = new CaseManagerReport();
			i.setUserId(rs.getString(1));
			i.setDate(rs.getDate(2));
			i.setNumConsumer(rs.getInt(3));
			i.setNumVisits(rs.getInt(4));
			i.setNumL2Ref(rs.getInt(5));
			i.setNumL3Ref(rs.getInt(6));
			i.setNumL2Seen(rs.getInt(7));
			i.setNumL3Seen(rs.getInt(8));
			i.setNumPCPReachedOut(rs.getInt(9));
			i.setNumPCPAppts(rs.getInt(10));
			i.setNumCM(rs.getInt(11));
			i.setNumEpisodeOpened(rs.getInt(12));
			i.setNumEpisodeClosed(rs.getInt(13));
			i.setNumHPOnCaseloadDueToExpire(rs.getInt(14));
			i.setNumOutsideMeeting(rs.getInt(15));
			i.setNumVisitNextWeek(rs.getInt(16));
			i.setNumNonCompliantChart(rs.getInt(17));
			return i;
		}
	}
	
}
