package main;

import java.sql.Connection;

import db.Database;
import model.dao.DaoFactory;
import model.dao.UserDao;
import model.entities.User;

public class Program {

	public static void main(String[] args) {
		Connection conn = Database.getConnection();
		UserDao ud = DaoFactory.getUserDao(conn);
		User u = ud.getById(1);
		System.out.println(u.getTasks());
		Database.closeConnection();
	}

}
