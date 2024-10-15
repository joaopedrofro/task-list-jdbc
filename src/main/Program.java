package main;

import java.sql.Connection;

import db.Database;
import model.dao.DaoFactory;
import model.entities.Task;

public class Program {

	public static void main(String[] args) {
		Connection conn = Database.getConnection();
		Task t = DaoFactory.getTaskDao(conn).getById(2);
		System.out.println(t);
		Database.closeConnection();
	}

}
