package com.suntek.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import com.suntek.model.ProjectList;

public class ProjectListDAOImpl extends SimpleJdbcDaoSupport {

	private ProjectListRowMapper plRm = new ProjectListRowMapper();

	public ProjectListDAOImpl(){		
	}

	public ProjectList getProjectList(Date date, String userId) {
		String sql = "SELECT userId, date, "+
						"task1, task2, task3, task4, task5, task6, task7, task8, task9, task10, "+
						"task11, task12, task13, task14, task15, task16, task17, task18, task19, task20, "+
						"task21, task22, task23, task24, task25, task26, task27 "+
						"from project_list "+
						"where userId = ? and date = ?";
		List<ProjectList> tasks = getSimpleJdbcTemplate().query(sql, plRm, userId, date);
		if (tasks.size() > 0){
			return tasks.get(0);
		}else{
			return null;
		}
	}

	private boolean ProjectListExist(String userId, Date date) {
		String sql = "SELECT count(*) from project_list where userId = ? and date = ?";
		return getSimpleJdbcTemplate().queryForInt(sql, userId, date) > 0;
	}

	public boolean updateProjectList(List<ProjectList> tasks) {
		int size = tasks.size();
		for (int i=0; i<size; i++){
			ProjectList task = tasks.get(i);
			if (ProjectListExist(task.getUserId(), task.getDate())){
				String sql = "update project_list set "+
								"task1 = ?, task2 = ?, task3 = ?, task4 = ?, task5 = ?, task6 = ?, task7 = ?, task8 = ?, task9 = ?, task10 = ?, "+
								"task11 = ?, task12 = ?, task13 = ?, task14 = ?, task15 = ?, task16 = ?, task17 = ?, task18 = ?, task19 = ?, task20 = ?, "+
								"task21 = ?, task22 = ?, task23 = ?, task24 = ?, task25 = ?, task26 = ?, task27  = ? "+				
								"where userId = ? and date = ?";
				getSimpleJdbcTemplate().update(sql, 
						task.getTask1(), task.getTask2(), task.getTask3(), task.getTask4(), task.getTask5(), task.getTask6(), task.getTask7(), task.getTask8(), task.getTask9(), task.getTask10(), 
						task.getTask11(), task.getTask12(), task.getTask13(), task.getTask14(), task.getTask15(), task.getTask16(), task.getTask17(), task.getTask18(), task.getTask19(), task.getTask20(),
						task.getTask21(), task.getTask22(), task.getTask23(), task.getTask24(), task.getTask25(), task.getTask26(), task.getTask27(), 						
						task.getUserId(), task.getDate()); 
			}else{
				String sql = "insert into project_list (userId, date, "+
								"task1, task2, task3, task4, task5, task6, task7, task8, task9, task10, "+
								"task11, task12, task13, task14, task15, task16, task17, task18, task19, task20, "+
								"task21, task22, task23, task24, task25, task26, task27 "+				
								") values (?, ?, " +
								"?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
								"?, ?, ?, ?, ?, ?, ?, ?, ?, ?," +
								"?, ?, ?, ?, ?, ?, ? )";
				getSimpleJdbcTemplate().update(sql, task.getUserId(), task.getDate(),
						task.getTask1(), task.getTask2(), task.getTask3(), task.getTask4(), task.getTask5(), task.getTask6(), task.getTask7(), task.getTask8(), task.getTask9(), task.getTask10(), 
						task.getTask11(), task.getTask12(), task.getTask13(), task.getTask14(), task.getTask15(), task.getTask16(), task.getTask17(), task.getTask18(), task.getTask19(), task.getTask20(),
						task.getTask21(), task.getTask22(), task.getTask23(), task.getTask24(), task.getTask25(), task.getTask26(), task.getTask27());				
			}
		}
		return true;
	}
	
	private class ProjectListRowMapper implements ParameterizedRowMapper<ProjectList>{		
		public ProjectList mapRow(ResultSet rs, int rowNum) throws SQLException {
			ProjectList c = new ProjectList();
			int i = 1;
			c.setUserId(rs.getString(i++));
			c.setDate(rs.getDate(i++));
			c.setTask1(rs.getString(i++));
			c.setTask2(rs.getString(i++));
			c.setTask3(rs.getString(i++));
			c.setTask4(rs.getString(i++));
			c.setTask5(rs.getString(i++));
			c.setTask6(rs.getString(i++));
			c.setTask7(rs.getString(i++));
			c.setTask8(rs.getString(i++));
			c.setTask9(rs.getString(i++));
			c.setTask10(rs.getString(i++));
			c.setTask11(rs.getString(i++));
			c.setTask12(rs.getString(i++));
			c.setTask13(rs.getString(i++));
			c.setTask14(rs.getString(i++));
			c.setTask15(rs.getString(i++));
			c.setTask16(rs.getString(i++));
			c.setTask17(rs.getString(i++));
			c.setTask18(rs.getString(i++));
			c.setTask19(rs.getString(i++));
			c.setTask20(rs.getString(i++));
			c.setTask21(rs.getString(i++));
			c.setTask22(rs.getString(i++));
			c.setTask23(rs.getString(i++));
			c.setTask24(rs.getString(i++));
			c.setTask25(rs.getString(i++));
			c.setTask26(rs.getString(i++));
			c.setTask27(rs.getString(i++));
			return c;
		}
	}
	
}
