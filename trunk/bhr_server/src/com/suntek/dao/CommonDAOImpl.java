package com.suntek.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

public class CommonDAOImpl extends SimpleJdbcDaoSupport {

	public List<String> getLocation(){		
		String sql = "select name from clinic";		
		return getSimpleJdbcTemplate().query(sql, new StringRowMapper());
	}

	private class StringRowMapper implements ParameterizedRowMapper<String>{
		public String mapRow(ResultSet rs, int rowNum) throws SQLException {
			return rs.getString(1);
		}
	}
	
	
}
