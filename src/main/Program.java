package main;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import controller.SystemController;
import db.Database;
import db.exceptions.DatabaseConnectionException;
import util.Scan;

public class Program {

	public static void main(String[] args) throws Exception {
		if (System.console() == null) {
			throw new Exception("O program não pode iniciar pois não há um console disponível!");
		}

		try {
			Connection conn = Database.getConnection();
			initDatabase(conn);
	
			new SystemController().run();
			
			closeResources();
		} catch (DatabaseConnectionException e) {
			e.printStackTrace();
		}
	}

	private static void initDatabase(Connection conn) {
		try (Statement st = conn.createStatement()) {
			st.executeUpdate("PRAGMA foreign_keys = ON");
			st.executeUpdate(
					"CREATE TABLE IF NOT EXISTS users ("
					+ "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
					+ "name TEXT NOT NULL,"
					+ "username TEXT NOT NULL,"
					+ "password TEXT NOT NULL,"
					+ "salt TEXT NO NULL)");
			st.executeUpdate(
					"CREATE TABLE IF NOT EXISTS tasks ("
					+ "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
					+ "title TEXT NOT NULL,"
					+ "moment TEXT NOT NULL,"
					+ "done INTEGER NOT NULL,"
					+ "user_id INTEGER NOT NULL,"
					+ "CONSTRAINT fk_user_id " 
					+ "FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE)");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void closeResources() {
		Scan.closeScanner();
		try {
			Database.closeConnection();
		} catch (DatabaseConnectionException e) {
			e.printStackTrace();
		}
	}

}
