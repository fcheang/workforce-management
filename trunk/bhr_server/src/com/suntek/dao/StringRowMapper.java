package com.suntek.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class StringRowMapper implements ParameterizedRowMapper<String>{
	public String mapRow(ResultSet rs, int rowNum) throws SQLException {
		return rs.getString(1);
	}
}
