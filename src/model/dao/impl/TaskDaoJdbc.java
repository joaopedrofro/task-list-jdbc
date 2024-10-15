package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import model.dao.TaskDao;
import model.entities.Task;

public class TaskDaoJdbc implements TaskDao {
	
	private Connection connection;

	public TaskDaoJdbc(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Task getById(Integer id) {
//		PreparedStatement st = null;
		return null;
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

}
