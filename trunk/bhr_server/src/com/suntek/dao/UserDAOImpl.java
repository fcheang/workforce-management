package com.suntek.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

import com.suntek.model.User;
import com.suntek.util.SecurityUtil;

public class UserDAOImpl extends SimpleJdbcDaoSupport {

	private UserRowMapper userRm = new UserRowMapper();
	private StringRowMapper strRm = new StringRowMapper();
	
	public UserDAOImpl(){		
	}
	
	public User getUser(String userName, String password){		
		password = SecurityUtil.encrypt(password);
        
		String sql = "select userId, password from user where userId = ? and password = ?";		
		List<User> users = getSimpleJdbcTemplate().query(sql, userRm, userName, password);
		if (users.size() > 0){
			return users.get(0);
		}else{
			return null;
		}
	}

	public List<String> getRoles(String userName){
		String sql = "select roleName from userRole where userId = ?";
		return super.getSimpleJdbcTemplate().query(sql, strRm, userName);
	}
	
	private class UserRowMapper implements ParameterizedRowMapper<User>{
		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User u = new User();
			u.setUsername(rs.getString("userId"));
			u.setPassword(rs.getString("password"));
			return u;
		}
	}

	public List<String> getCapability(String userId) {
		String sql = "select object from userCapability where userId = ? order by object";
		return super.getSimpleJdbcTemplate().query(sql, strRm, userId);
	}
	
}
