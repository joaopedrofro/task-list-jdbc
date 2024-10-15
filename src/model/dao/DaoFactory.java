package model.dao;

import java.sql.Connection;

import model.dao.impl.TaskDaoJdbc;
import model.dao.impl.UserDaoJdbc;

public class DaoFactory {

	public static TaskDao getTaskDao(Connection conn) {
		return new TaskDaoJdbc(conn);
	}
	
	public static UserDao getUserDao(Connection conn) {
		return new UserDaoJdbc(conn);
	}
	
}
