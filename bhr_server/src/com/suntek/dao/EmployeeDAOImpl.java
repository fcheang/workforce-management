package com.suntek.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;

import com.suntek.model.Employee;

public class EmployeeDAOImpl extends SimpleJdbcDaoSupport {

	public EmployeeDAOImpl(){		
	}
	
	public List<Employee> getAllEmployees(){
		
		String sql = "SELECT empId, firstName, middleName, lastName FROM employee where isActive = 1";		
		return getSimpleJdbcTemplate().query(sql, new EmployeeRowMapper());
	}
	
	public Employee createEmployee(Employee emp){
		String sql = "insert into employee (title, firstName, lastName, middleName, isActive) values (?, ?, ?, ?, ?)";
		int count = getSimpleJdbcTemplate().update(sql, emp.getTitle(), emp.getFirstName(), emp.getLastName(), emp.getMiddleName(), 1);
		if (count > 0){

		}
		return emp;
	}
	
	public boolean updateEmployee(Employee emp){
		logger.debug("updateEmployee("+emp+")");
		return true;
	}
	
	private class EmployeeRowMapper implements ParameterizedRowMapper<Employee>{
		public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
			Employee e = new Employee();
			e.setId(rs.getInt(1));	
			e.setFirstName(rs.getString(2));
			e.setMiddleName(rs.getString(3));
			e.setLastName(rs.getString(4));
			return e;
		}
	}	
	
}
