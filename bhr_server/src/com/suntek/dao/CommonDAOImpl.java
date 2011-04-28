package com.suntek.dao;

import java.util.List;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

public class CommonDAOImpl extends SimpleJdbcDaoSupport {

	private StringRowMapper strRm = new StringRowMapper();
	
	public List<String> getLocation(){		
		String sql = "select name from clinic";		
		return getSimpleJdbcTemplate().query(sql, strRm);
	}	
	
}
