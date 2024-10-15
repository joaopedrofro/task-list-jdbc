package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.dao.TaskDao;
import model.entities.Task;

public class TaskDaoJdbc implements TaskDao {
	
	private Connection conn;

	public TaskDaoJdbc(Connection conn) {
		this.conn = conn;
	}

	@Override
	public Task getById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		Task task = null;
		
		try {
			st = conn.prepareStatement("SELECT * FROM tasks WHERE id=?");
			st.setInt(1, id);
			
			rs = st.executeQuery();
			
			task = instantiateTask(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return task;
	}

	@Override
	public List<Task> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(Task t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Task t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Task t) {
		// TODO Auto-generated method stub
		
	}
	
	private static Task instantiateTask(ResultSet rs) throws SQLException {
		Task t = null;
		
		if (rs.next()) {
			t = new Task();
			t.setId(rs.getInt("id"));
			t.setMoment(rs.getDate("moment"));
			t.setTitle(rs.getString("title"));
			t.setDone(rs.getBoolean("done"));
		}
		
		return t;
	}

}
