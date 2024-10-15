package model.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.Database;
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
			
			if (rs.next()) {
				task = instantiateTask(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Database.closeResultSet(rs);
			Database.closeStatement(st);
		}
		
		return task;
	}

	@Override
	public List<Task> getAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		List<Task> tasks = new ArrayList<Task>();
		
		try {
			st = conn.prepareStatement("SELECT * FROM tasks");
			
			rs = st.executeQuery();
			
			while(rs.next()) {
				tasks.add(instantiateTask(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Database.closeResultSet(rs);
			Database.closeStatement(st);
		}
		
		return tasks;
	}

	@Override
	public void add(Task t) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("INSERT INTO tasks (title, moment, done) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			st.setString(1, t.getTitle());
			st.setDate(2, new Date(t.getMoment().getTime()));
			st.setBoolean(3, t.getDone());
			
			st.executeUpdate();
			
			rs = st.getGeneratedKeys();
			
			if (rs.next()) {
				t.setId(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Database.closeResultSet(rs);
			Database.closeStatement(st);
		}
	}

	@Override
	public void update(Task t) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement("UPDATE tasks SET title=?, moment=?, done=? WHERE id=?");
			
			st.setString(1, t.getTitle());
			st.setDate(2, new Date(t.getMoment().getTime()));
			st.setBoolean(3, t.getDone());
			st.setInt(4, t.getId());
			
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Database.closeStatement(st);
		}
	}

	@Override
	public void delete(Task t) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement("DELETE FROM tasks WHERE id=?");
			st.setInt(1, t.getId());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Database.closeStatement(st);
		}
	}
	
	private static Task instantiateTask(ResultSet rs) throws SQLException {
		Task t = new Task();
		
		t.setId(rs.getInt("id"));
		t.setMoment(rs.getDate("moment"));
		t.setTitle(rs.getString("title"));
		t.setDone(rs.getBoolean("done"));
		
		return t;
	}

}
