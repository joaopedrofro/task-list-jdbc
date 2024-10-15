package main;

import java.sql.Connection;

import db.Database;
import model.dao.DaoFactory;
import model.dao.TaskDao;
import model.entities.Task;

public class Program {

	public static void main(String[] args) {
		Connection conn = Database.getConnection();
		TaskDao td = DaoFactory.getTaskDao(conn);
		Task t = td.getById(7);
		td.delete(t);
		Database.closeConnection();
	}

}
