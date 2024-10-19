package main;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import controller.SystemController;
import db.Database;
import util.Scan;

public class Program {

	public static void main(String[] args) throws Exception {
		if (System.console() == null) {
			throw new Exception("O program não pode iniciar pois não há console disponível!");
		}

		Connection conn = Database.getConnection();
		initDatabase(conn);

		new SystemController().run();
		
		closeResources();
	}

	private static void initDatabase(Connection conn) {
		try (Statement st = conn.createStatement()) {
			st.executeUpdate(
					"CREATE TABLE IF NOT EXISTS users ("
					+ "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
					+ "name TEXT NOT NULL,"
					+ "username TEXT NOT NULL,"
					+ "password TEXT NOT NULL)");
			st.executeUpdate(
					"CREATE TABLE IF NOT EXISTS tasks ("
					+ "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
					+ "title TEXT NOT NULL,"
					+ "moment TEXT NOT NULL,"
					+ "done INTEGER NOT NULL,"
					+ "user_id INTEGER NOT NULL,"
					+ "FOREIGN KEY (user_id) REFERENCES users(id))");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void closeResources() {
		Scan.closeScanner();
		Database.closeConnection();
	}

}
