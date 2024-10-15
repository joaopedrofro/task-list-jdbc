package model.dao;

import java.sql.Connection;

import model.dao.impl.TaskDaoJdbc;

public class DaoFactory {

	public static TaskDao getTaskDao(Connection conn) {
		return new TaskDaoJdbc(conn);
	}
	
}
