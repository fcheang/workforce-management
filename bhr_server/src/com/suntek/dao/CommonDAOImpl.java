package com.suntek.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import com.suntek.model.Provider;

public class CommonDAOImpl extends SimpleJdbcDaoSupport {

	private StringRowMapper strRm = new StringRowMapper();
	private ProviderRowMapper providerRm = new ProviderRowMapper();
	
	public List<String> getLocation(){		
		String sql = "select name from clinic";		
		return getSimpleJdbcTemplate().query(sql, strRm);
	}

	public List<String> getAllPermissionType() {
		String sql = "select name from permission_type order by name";		
		return getSimpleJdbcTemplate().query(sql, strRm);
	}

	public List<String> getAllUserId() {
		String sql = "select userId from user order by userId";		
		return getSimpleJdbcTemplate().query(sql, strRm);
	}	
	
	public List<Provider> getAllProvider() {
		String sql = "select providerId, name, title from provider where inactive = 0 order by name";
		return getSimpleJdbcTemplate().query(sql, providerRm); 
	}
	
	private class ProviderRowMapper implements ParameterizedRowMapper<Provider>{
		public Provider mapRow(ResultSet rs, int rowNum) throws SQLException {
			Provider e = new Provider();
			e.setProviderId(rs.getInt(1));	
			e.setName(rs.getString(2));
			e.setTitle(rs.getString(3));
			return e;
		}
	}	
	
}
