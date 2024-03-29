package com.suntek.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

import com.suntek.model.Employee;

public class EmployeeDAOImpl extends SimpleJdbcDaoSupport {

	private EmployeeRowMapper rm = new EmployeeRowMapper();
	
	public EmployeeDAOImpl(){		
	}
	
	public List<Employee> getAllEmployees(){		
		String sql = "SELECT empId, title, firstName, middleName, lastName, isActive FROM employee where isActive = 1";		
		return getSimpleJdbcTemplate().query(sql, rm);
	}
	
	public Employee getEmployee(String fname, String lname){
		String sql = "select empId, title, firstName, middleName, lastName, isActive from employee where firstName = ? and lastName = ?";
		List<Employee> emp = super.getSimpleJdbcTemplate().query(sql, rm, fname, lname);
		if (emp.size() > 0){
			return emp.get(0);
		}else{
			return null;
		}
	}
	
	public void setEmployeeActive(int empId){
		String sql = "update employee set isActive = 1 where empId = ?";
		super.getSimpleJdbcTemplate().update(sql, empId);		
	}
	
	public Employee createEmployee(Employee emp){
		Employee exisitEmp = getEmployee(emp.getFirstName(), emp.getLastName());
		if (exisitEmp != null){
			if (!exisitEmp.getIsActive()){
				setEmployeeActive(exisitEmp.getEmpId());
				return exisitEmp;
			}else{
				return null;
			}
		}else{
			Connection con = null;
			try{
				String sql = "insert into employee (title, firstName, lastName, middleName, isActive) values (?, ?, ?, ?, ?)";			
				con = super.getDataSource().getConnection();
	
				PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, emp.getTitle());
				pstmt.setString(2, emp.getFirstName());
				pstmt.setString(3, emp.getLastName());
				pstmt.setString(4, emp.getMiddleName());
				pstmt.setBoolean(5, true);
				int count = pstmt.executeUpdate();
				if (count == 1){
					ResultSet rs = pstmt.getGeneratedKeys();
					if (rs.next()){
						int empId = rs.getInt(1);
						emp.setEmpId(empId);
					}				
					rs.close();
					pstmt.close();
					return emp;
				}else{
					throw new RuntimeException("Employee was not created!");
				}
			}catch(SQLException e){
				throw new RuntimeException("Problem create employee!", e);
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
	}
	
	public boolean updateEmployee(Employee emp){
		String sql = "update employee set title = ?, firstName = ?, lastName = ?, middleName = ? where empId = ?";
		int count = getSimpleJdbcTemplate().update(sql, emp.getTitle(), emp.getFirstName(), emp.getLastName(), emp.getMiddleName(), emp.getEmpId());
		return count > 0;
	}
	
	public boolean deleteEmployee(int empId){
		String sql = "select count(*) from worksheet where empId = ?";
		int numws = super.getSimpleJdbcTemplate().queryForInt(sql, empId);
		if (numws > 0){
			sql = "update employee set isActive = 0 where empId = ?";
			return super.getSimpleJdbcTemplate().update(sql, empId) > 0;
		}else{		
			sql = "delete from employee where empId = ?";
			return super.getSimpleJdbcTemplate().update(sql, empId) > 0;
		}				 
	}
		
	
	private class EmployeeRowMapper implements ParameterizedRowMapper<Employee>{
		public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
			Employee e = new Employee();
			e.setEmpId(rs.getInt(1));	
			e.setTitle(rs.getString(2));
			e.setFirstName(rs.getString(3));
			e.setMiddleName(rs.getString(4));
			e.setLastName(rs.getString(5));
			e.setIsActive(rs.getBoolean(6));
			return e;
		}
	}	
	
}
