package main;

import java.sql.Connection;
import java.util.Date;

import db.Database;
import model.dao.DaoFactory;
import model.entities.Task;

public class Program {

	public static void main(String[] args) {
		Connection conn = Database.getConnection();
		
		Task t = new Task();
		t.setTitle("Nova task");
		t.setMoment(new Date());
		t.setDone(true);
		
		DaoFactory.getTaskDao(conn).add(t);
		
		System.out.println(t);
		
		Database.closeConnection();
	}

}
