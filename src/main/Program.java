package main;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import controller.SystemController;
import db.Database;

public class Program {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		Connection conn = Database.getConnection();
		initDatabase(conn);
		
		new SystemController(conn, scan).init();
		
		Database.closeConnection();
	}
	
	private static void initDatabase(Connection conn) {
		try (Statement st = conn.createStatement()) {
			st.executeUpdate("DROP TABLE IF EXISTS users");
			st.executeUpdate("DROP TABLE IF EXISTS tasks");
			st.executeUpdate(
					"CREATE TABLE users ("
					 + "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
					 + "name TEXT NOT NULL,"
					 + "username TEXT NOT NULL,"
					 + "password TEXT NOT NULL)");
			st.executeUpdate(
					"CREATE TABLE tasks ("
					 + "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
					 + "title TEXT NOT NULL,"
					 + "moment TEXT NOT NULL,"
					 + "done INTEGER NOT NULL,"
					 + "user_id INTEGER NOT NULL,"
					 + "FOREIGN KEY (user_id) REFERENCES users(id))");
			st.executeUpdate("INSERT INTO users (name, username, password) VALUES (\"admin\", \"admin\", \"123\")");
			st.executeUpdate("INSERT INTO users (name, username, password) VALUES (\"João Pedro\", \"joaop\", \"123\")");
			st.executeUpdate("INSERT INTO tasks (title, moment, done, user_id) VALUES (\"Task 1\", \"2024-10-16\", 0, 2)");
			st.executeUpdate("INSERT INTO tasks (title, moment, done, user_id) VALUES (\"Task 2\", \"2024-10-15\", 1, 2)");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
