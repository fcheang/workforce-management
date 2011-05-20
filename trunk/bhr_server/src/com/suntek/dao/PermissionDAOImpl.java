package com.suntek.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

import com.suntek.model.Permission;

public class PermissionDAOImpl extends SimpleJdbcDaoSupport {

	private PermissionRowMapper rm = new PermissionRowMapper();
	private StringRowMapper strRm = new StringRowMapper();
	
	private class PermissionRowMapper implements ParameterizedRowMapper<Permission>{
		public Permission mapRow(ResultSet rs, int rowNum) throws SQLException {
			Permission p = new Permission();
			p.setId(rs.getInt(1));
			p.setObject(rs.getString(2));
			p.setUserId(rs.getString(3));
			return p;
		}
	}

	public Permission createPermission(Permission perm) {
		Connection con = null;
		try{
			String sql = "insert into permission (object, userId) values (?, ?)";			
			con = super.getDataSource().getConnection();

			PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, perm.getObject());
			pstmt.setString(2, perm.getUserId());
			int count = pstmt.executeUpdate();
			if (count == 1){
				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next()){
					int id = rs.getInt(1);
					perm.setId(id);
				}				
				rs.close();
				pstmt.close();
				return perm;
			}else{
				throw new RuntimeException("Permission was not created!");
			}
		}catch(SQLException e){
			throw new RuntimeException("Problem create permission!", e);
		}finally{
			if (con != null){
				try{
					con.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}

	public boolean updatePermission(Permission perm) {
		String sql = "update permission set object = ?, userId = ? where id = ?";
		return getSimpleJdbcTemplate().update(sql, perm.getObject(), perm.getUserId(), perm.getId()) > 0;
	}

	public boolean deletePermission(Permission perm) {
		String sql = "delete from permission where id = ?";
		return getSimpleJdbcTemplate().update(sql,  perm.getId()) > 0;
	}

	public List<Permission> getAllPermission() {
		String sql = "SELECT id, object, userId from permission";		
		return getSimpleJdbcTemplate().query(sql, rm);
	}

	public List<String> getPermissionObject(String userId) {
		String sql = "SELECT object from permission where userId = ?";		
		return getSimpleJdbcTemplate().query(sql, strRm, userId);
	}	
	
	public boolean permissionExist(String userId, String object){
		String sql = "select count(*) from permission where userId = ? and object = ?";
		return getSimpleJdbcTemplate().queryForInt(sql, userId, object) > 0;
	}
}
