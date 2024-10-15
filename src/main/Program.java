package main;

import java.sql.Connection;
import java.util.List;

import db.Database;
import model.dao.DaoFactory;
import model.entities.Task;

public class Program {

	public static void main(String[] args) {
		Connection conn = Database.getConnection();
		List<Task> tasks = DaoFactory.getTaskDao(conn).getAll();
		tasks.forEach(System.out::println);
		Database.closeConnection();
	}

}
